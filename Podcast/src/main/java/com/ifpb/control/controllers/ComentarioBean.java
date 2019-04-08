package com.ifpb.control.controllers;


import com.ifpb.model.dao.Exceptions.DataAccessException;
import com.ifpb.model.dao.impl.ComentarioDaoImpl;
import com.ifpb.model.dao.impl.UsuarioDaoImpl;
import com.ifpb.model.dao.interfaces.ComentarioDao;
import com.ifpb.model.dao.interfaces.UsuarioDao;
import com.ifpb.model.domain.Comentario;
import com.ifpb.model.domain.Podcast;
import com.ifpb.model.domain.Usuario;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class ComentarioBean {

    private ComentarioDao comentarioDao;

    private Comentario comentario;

    private int idPodcast;

    @ManagedProperty("#{playerBean}")
    private PlayerBean playerBean;

    @PostConstruct
    public void init(){
        comentario = new Comentario();
        comentarioDao = new ComentarioDaoImpl();
    }

    public void comentar(){
        try {
            comentarioDao.salvar(comentario,idPodcast);
            playerBean.atualizar_comentários();
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    public Comentario getComentario() {
        return comentario;
    }

    public void setComentario(Comentario comentario) {
        this.comentario = comentario;
    }

    public int getIdPodcast() {
        return idPodcast;
    }

    public void setIdPodcast(int idPodcast) {
        this.idPodcast = idPodcast;
    }

    public PlayerBean getPlayerBean() {
        return playerBean;
    }

    public void setPlayerBean(PlayerBean playerBean) {
        this.playerBean = playerBean;
    }
}
