package com.ifpb.control.controllers;

import com.ifpb.model.dao.Exceptions.DataAccessException;
import com.ifpb.model.dao.impl.PodcastDaoImpl;
import com.ifpb.model.dao.impl.TurmaVirtualDaoImpl;
import com.ifpb.model.dao.interfaces.PodcastDao;
import com.ifpb.model.dao.interfaces.TurmaVirtualDao;
import com.ifpb.model.domain.Enum.Tipo;
import com.ifpb.model.domain.Podcast;
import com.ifpb.model.domain.TurmaVirtual;


import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Pattern;

@ManagedBean
@RequestScoped
public class PodcastBean {

    private PodcastDao podcastDao;

    private Podcast podcast;

    private Part audio;

    private TurmaVirtualDao turmaVirtualDao;

    private List<SelectItem> nomeTurmas;

    private String nomeTurma;

    @ManagedProperty("#{fileBean}")
    private FileBean fileBean;

    private Pattern pattern = Pattern.compile("audio\\/.+");

    private Logger log = Logger.getLogger(PodcastBean.class.getName());

    @ManagedProperty("#{loginBean}")
    private LoginBean loginBean;


    @PostConstruct
    public void init(){
        podcast = new Podcast();
        podcastDao = new PodcastDaoImpl();
        turmaVirtualDao = new TurmaVirtualDaoImpl();
        try{
            List<TurmaVirtual> turmas;
            nomeTurmas = new ArrayList();
            if (loginBean.getUser().getTipo().equals(Tipo.PROFESSOR)) {
                turmas = turmaVirtualDao.listarTurmasCriadas(loginBean.getUser().getEmail());

            } else {
                turmas = turmaVirtualDao.listarTurmasParticiantes(loginBean.getUser().getEmail());
            }
            for (TurmaVirtual turma:turmas ) {
                nomeTurmas.add(new SelectItem(turma.getNome(),turma.getNome()));
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

    }


    public void upload(){
        String nomeArquivo = Timestamp.from(Instant.now()).toString() + "-" + audio.getSubmittedFileName();
        log.info(nomeArquivo);
        try (InputStream file = audio.getInputStream()) {
            Files.copy(file, new File(fileBean.getUploadAudioPath() + "/" + nomeArquivo).toPath(), StandardCopyOption.REPLACE_EXISTING);
            podcast.setAudioPath(nomeArquivo);
            podcast.setDono(loginBean.getUser());
            if(nomeTurma.equals("nenhuma")){
                podcastDao.salvar(podcast);
            }else{
                podcastDao.salvarEmTurma(podcast,nomeTurma);
            }

        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    public void validateFile(FacesContext ctx,UIComponent comp,Object value) {
        log.info(fileBean.getUploadAudioPath());
        List<FacesMessage> msgs = new ArrayList<>();
        Part file = (Part)value;
        log.info(file.getContentType());
        String type = file.getContentType().split("/")[0];
        log.severe(type);
        log.severe(""+file.getSize());
        if (file.getSize() > 1024 * 10000) {
            msgs.add(new FacesMessage("O arquivo é muito grande!"));
        }
        if (!pattern.matcher(file.getContentType()).matches()) {
            msgs.add(new FacesMessage("O arquivo selecionado não é um audio!"));
        }
        if (!msgs.isEmpty()) {
            throw new ValidatorException(msgs);
        }
    }

    public Podcast getPodcast() {
        return podcast;
    }

    public void setPodcast(Podcast podcast) {
        this.podcast = podcast;
    }

    public Part getAudio() {
        return audio;
    }

    public void setAudio(Part audio) {
        this.audio = audio;
    }

    public FileBean getFileBean() {
        return fileBean;
    }

    public void setFileBean(FileBean fileBean) {
        this.fileBean = fileBean;
    }

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

    public List<SelectItem> getNomeTurmas() {
        return nomeTurmas;
    }

    public void setNomeTurmas(List<SelectItem> nomeTurmas) {
        this.nomeTurmas = nomeTurmas;
    }

    public String getNomeTurma() {
        return nomeTurma;
    }

    public void setNomeTurma(String nomeTurma) {
        this.nomeTurma = nomeTurma;
    }
}
