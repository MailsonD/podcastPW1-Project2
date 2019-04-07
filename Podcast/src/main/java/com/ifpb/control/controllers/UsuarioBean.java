package com.ifpb.control.controllers;
import com.ifpb.model.dao.Exceptions.DataAccessException;
import com.ifpb.model.dao.impl.UsuarioDaoImpl;
import com.ifpb.model.dao.interfaces.UsuarioDao;
import com.ifpb.model.domain.Enum.NivelAcesso;

import com.ifpb.model.domain.Usuario;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;

@ManagedBean
@RequestScoped
public class UsuarioBean {

    private UsuarioDao usuarioDao;

    private Usuario usuario;

    private List<Usuario> usuarios;

    private List<Usuario> alunos;


    @PostConstruct
    public void init() {
        usuarioDao = new UsuarioDaoImpl();
        usuario = new Usuario();
        listar();
        listarAlunos();
    }

    public String cadastrar(){
        try {
            usuario.setNivelAcesso(NivelAcesso.USER);
            usuarioDao.salvar(usuario);
            System.out.println("cadastrado");
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
    public void listarAlunos(){
        try {
            this.alunos = usuarioDao.listarAlunos();
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

    public List<Usuario> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Usuario> alunos) {
        this.alunos = alunos;
    }


}
