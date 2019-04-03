package com.ifpb.control.controllers;
import com.ifpb.model.dao.impl.UsuarioDaoImpl;
import com.ifpb.model.dao.interfaces.UsuarioDao;
import com.ifpb.model.domain.Usuario;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;


@ManagedBean
@RequestScoped
public class UsuarioBean {

    private UsuarioDao usuarioDao;

    private Usuario usuario;

    @PostConstruct
    public void init() {
        usuarioDao = new UsuarioDaoImpl();
        usuario = new Usuario();
    }

    public void cadastrar(){

    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
