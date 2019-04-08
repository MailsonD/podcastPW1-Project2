package com.ifpb.control.controllers;

import com.ifpb.model.dao.Exceptions.DataAccessException;
import com.ifpb.model.dao.impl.ComentarioDaoImpl;
import com.ifpb.model.dao.interfaces.ComentarioDao;
import com.ifpb.model.domain.Podcast;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class PlayerBean {

    private Podcast podcast;

    private ComentarioDao comentarioDao;

    @PostConstruct
    public void init(){
        comentarioDao = new ComentarioDaoImpl();
    }

    public void atualizar_comentários(){
        try {
            podcast.setComentarios(comentarioDao.buscarPorPodcast(podcast.getId()));
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    public Podcast getPodcast() {
        return podcast;
    }

    public void setPodcast(Podcast podcast) {
        this.podcast = podcast;
    }
}
