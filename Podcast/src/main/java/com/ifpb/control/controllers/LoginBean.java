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
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@ManagedBean
@SessionScoped
public class LoginBean {

    private UsuarioDao usuarioDao;

    private Usuario user;

    private String email;

    private String senha;

    @PostConstruct
    public void init() {
        user = null;
        usuarioDao = new UsuarioDaoImpl();
    }

    public String efetuarLogin(){
        try {
            if(!usuarioDao.autenticarUsuario(email, senha)){
                FacesContext.getCurrentInstance().addMessage(":formLogin:erroLogin", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login ou senha inválidos", "Login ou senha inválidos"));
                return "";
            }else{
               user = usuarioDao.buscar(email);
               user.setSenha(null);
               senha=null;
               return "timeline";
            }
        }
        catch(DataAccessException e){
            FacesContext.getCurrentInstance().addMessage(":formLogin:erroLogin", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro interno!", "Erro interno!"));;
            return "";
        }
    }

    public String logout(){
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        request.getSession(false).invalidate();
        return "login";
    }

    public String desativarConta(){
        try {
            usuarioDao.remover(user.getEmail());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        request.getSession(false).invalidate();
        return "login";
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
