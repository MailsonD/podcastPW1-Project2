package com.ifpb.control.controllers;


import com.ifpb.model.dao.Exceptions.DataAccessException;
import com.ifpb.model.dao.impl.UsuarioDaoImpl;
import com.ifpb.model.dao.interfaces.UsuarioDao;
import com.ifpb.model.domain.Usuario;
import org.apache.catalina.Session;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;

import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;

@ManagedBean
@SessionScoped
public class LoginBean {

    private UsuarioDao usuarioDao;

    private Usuario user;

    private String email;

    private String senha;

    private static LoginBean instance;

    @PostConstruct
    public void init() {
        user = new Usuario();
        usuarioDao = new UsuarioDaoImpl();
        instance = this;
    }

    public String efetuarLogin() throws DataAccessException {
        user = usuarioDao.autenticarUsuario(email,senha);
        if(user == null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login ou senha inválidos", "Login ou senha inválidos"));
            return "";
        }
        return "home";

    }

    public void logout()
    {
        this.user = null;
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }

    public static LoginBean getInstance() throws Exception
    {
        if(instance == null)
        {
            throw new Exception("Não há usuario logado no sistema");
        }
        return instance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }
}
