package com.ifpb.control.controllers;

import com.ifpb.model.dao.Exceptions.DataAccessException;
import com.ifpb.model.dao.impl.ComentarioDaoImpl;
import com.ifpb.model.dao.impl.PodcastDaoImpl;
import com.ifpb.model.dao.interfaces.ComentarioDao;
import com.ifpb.model.dao.interfaces.PodcastDao;
import com.ifpb.model.domain.Podcast;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class PlayerBean {

    private Podcast podcast;

    private PodcastDao podcastDao;

    @PostConstruct
    public void init(){
        podcastDao = new PodcastDaoImpl();
    }

    public void atualizar_comentários(){
        try {
            podcast  = podcastDao.buscar(podcast.getId());
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
