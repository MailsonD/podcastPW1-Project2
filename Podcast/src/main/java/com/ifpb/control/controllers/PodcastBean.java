package com.ifpb.control.controllers;

import com.ifpb.model.dao.impl.PodcastDaoImpl;
import com.ifpb.model.dao.interfaces.PodcastDao;
import com.ifpb.model.domain.Podcast;


import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
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

    private String audioPath;

    private Pattern pattern = Pattern.compile("audio\\/.+");

    private Logger log = Logger.getLogger(PodcastBean.class.getName());

    @ManagedProperty("#{loginBean}")
    private LoginBean loginBean;


    @PostConstruct
    public void init(){
        podcast = new Podcast();
        podcastDao = new PodcastDaoImpl();
        String realpath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("");
        File filepath = new File(realpath);
        Path p = filepath.toPath();
        for(int i = 0;i<5;i++){
            p = p.getParent();
        }
        File path = new File(p.toString()+"/audio");
        if(!path.exists()){
            path.mkdirs();
        }
        audioPath = path.getAbsolutePath();

    }


    public void upload(){
        String nomeArquivo = Timestamp.from(Instant.now()).toString() + "-" + audio.getSubmittedFileName();
        try (InputStream file = audio.getInputStream()) {
            Files.copy(file, new File(audioPath + "/" + nomeArquivo).toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void validateFile(FacesContext ctx,UIComponent comp,Object value) {
        log.info(audioPath);
        List<FacesMessage> msgs = new ArrayList<>();
        Part file = (Part)value;
        log.severe(file.getContentType());
        String type = file.getContentType().split("/")[0];
        log.severe(type);
        log.severe(""+file.getSize());
        if (file.getSize() > 1024 * 100) {
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

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }
}
