package com.ifpb.control.controllers;

import com.ifpb.model.domain.Podcast;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class PlayerBean {

    private Podcast podcast;

    @PostConstruct
    public void init(){

    }

    public Podcast getPodcast() {
        return podcast;
    }

    public void setPodcast(Podcast podcast) {
        this.podcast = podcast;
    }
}
