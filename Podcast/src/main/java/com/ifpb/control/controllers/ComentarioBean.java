package com.ifpb.control.controllers;


import com.ifpb.model.dao.Exceptions.DataAccessException;
import com.ifpb.model.dao.impl.ComentarioDaoImpl;
import com.ifpb.model.dao.impl.UsuarioDaoImpl;
import com.ifpb.model.dao.interfaces.ComentarioDao;
import com.ifpb.model.dao.interfaces.UsuarioDao;
import com.ifpb.model.domain.Comentario;
import com.ifpb.model.domain.Usuario;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class ComentarioBean {

//    ComentarioDao comentarios;
//
//    @PostConstruct
//    public void init(){
//        comentarios = new ComentarioDaoImpl();
//    }
//
//    public String salvar(Comentario comentario, String podcast){
//        try{
//            comentarios.salvar(comentario, podcast);
//        }
//        catch (DataAccessException e){
//            e.printStackTrace();
//        }
//        return "";
//    }
//
//    public String deletar (String usuario, String podcast) {
//        try {
//            comentarios.deletar(usuario, podcast);
//        } catch (DataAccessException e) {
//            e.printStackTrace();
//        }
//        return "";
//    }
//
//    public String deletarPorPodcast(String podcast){
//        try {
//            comentarios.deletarPorPodcast(podcast);
//        } catch (DataAccessException e) {
//            e.printStackTrace();
//        }
//        return "";
//    }
//
//    public String buscarPorPodcast(String podcast){
//        try {
//            comentarios.buscarPorPodcast(podcast);
//        } catch (DataAccessException e) {
//            e.printStackTrace();
//        }
//        return "";
//    }

}
