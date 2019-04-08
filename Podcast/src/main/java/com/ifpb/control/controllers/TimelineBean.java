package com.ifpb.control.controllers;

import com.ifpb.model.dao.Exceptions.DataAccessException;
import com.ifpb.model.dao.impl.PodcastDaoImpl;
import com.ifpb.model.dao.interfaces.PodcastDao;
import com.ifpb.model.domain.Podcast;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;

@ManagedBean
@ViewScoped
public class TimelineBean {

    private PodcastDao podcastDao;

    private Podcast podcast;

    private String estado;

    private List<Podcast> timeline;

    @PostConstruct
    public void init(){
        podcastDao = new PodcastDaoImpl();
        estado = "tocar";
        try {
            podcast = podcastDao.buscar(1);
            timeline = podcastDao.listarOrdenado();
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    public void tocar(){
        estado = "tocar";
    }

    public List<Podcast> getTimeline() {
        return timeline;
    }

    public void setTimeline(List<Podcast> timeline) {
        this.timeline = timeline;
    }

    public Podcast getPodcast() {
        return podcast;
    }

    public void setPodcast(Podcast podcast) {
        this.podcast = podcast;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
