package com.ifpb.control.controllers;
import com.ifpb.model.dao.Exceptions.DataAccessException;
import com.ifpb.model.dao.impl.UsuarioDaoImpl;
import com.ifpb.model.dao.interfaces.UsuarioDao;
import com.ifpb.model.domain.Enum.NivelAcesso;

import com.ifpb.model.domain.Usuario;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Pattern;

@ManagedBean
@RequestScoped
public class UsuarioBean {

    private UsuarioDao usuarioDao;

    private Usuario usuario;

    private List<Usuario> usuarios;

    private Part foto;

    private Pattern pattern = Pattern.compile("image\\/.+");

    @ManagedProperty("#{fileBean}")
    private FileBean fileBean;

    private Logger log = Logger.getLogger(UsuarioBean.class.getName());



    @ManagedProperty("#{loginBean}")
    private LoginBean loginBean;


    @PostConstruct
    public void init() {
        usuarioDao = new UsuarioDaoImpl();
        usuario = new Usuario();
        listar();
    }

    public String cadastrar(){
        try {
            usuario.setNivelAcesso(NivelAcesso.USER);
            usuarioDao.salvar(usuario);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return "";
    }

    public void listar(){
        try {
            this.usuarios = usuarioDao.listar();
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    public void remover(){
        try {
            usuarioDao.remover(usuario.getEmail());
            listar();
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
    public void setAdmin(){
        try {
            usuarioDao.setAdmin(usuario.getEmail());
            listar();
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    public String editar(){
        try {
            usuarioDao.atualizar(loginBean.getUser().getEmail(),loginBean.getUser());
            loginBean.getUser().setSenha(null);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return "";
    }

    public void atualizarFoto(){
        String nomeArquivo = Timestamp.from(Instant.now()).toString() + "-" + foto.getSubmittedFileName();
        try (InputStream file = foto.getInputStream()){
            Files.copy(file, new File(fileBean.getUploadImagePath() + "/" + nomeArquivo).toPath(), StandardCopyOption.REPLACE_EXISTING);
            usuarioDao.salvarFoto(nomeArquivo,loginBean.getEmail());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    public void validateFile(FacesContext ctx, UIComponent comp, Object value) {
        List<FacesMessage> msgs = new ArrayList<>();
        Part file = (Part)value;
        if (file.getSize() > 1024 * 5000) {
            msgs.add(new FacesMessage("O arquivo é muito grande!"));
        }
        if (!pattern.matcher(file.getContentType()).matches()) {
            msgs.add(new FacesMessage("O arquivo selecionado não é uma imagem!"));
        }
        if (!msgs.isEmpty()) {
            throw new ValidatorException(msgs);
        }
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Part getFoto() {
        return foto;
    }

    public void setFoto(Part foto) {
        this.foto = foto;
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
}
