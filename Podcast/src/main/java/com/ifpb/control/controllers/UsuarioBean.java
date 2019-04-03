package com.ifpb.control.controllers;

import com.ifpb.model.dao.Exceptions.DataAccessException;
import com.ifpb.model.dao.impl.UsuarioDaoImpl;
import com.ifpb.model.dao.interfaces.UsuarioDao;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class UsuarioBean {

    private UsuarioDao usuarioDao;

    private String email;

    private String senha;

    @PostConstruct
    public void init() {
        usuarioDao = new UsuarioDaoImpl();
    }

    public String efetuarLogin(){

        try {
            if(usuarioDao.autenticarUsuario(email,senha)){
                
            }else{
                System.out.println("não foi!");
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

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
}
