package com.ifpb.control.controllers;

import com.ifpb.model.dao.impl.PodcastDaoImpl;
import com.ifpb.model.dao.interfaces.PodcastDao;
import com.ifpb.model.domain.Podcast;
import org.primefaces.model.UploadedFile;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.servlet.http.Part;

@ManagedBean
@RequestScoped
public class PodcastBean {

    private PodcastDao podcastDao;

    private Podcast podcast;

    private Part audio;

    @ManagedProperty("#{loginBean}")
    private LoginBean loginBean;

    @PostConstruct
    public void init(){
        podcastDao = new PodcastDaoImpl();
    }


    public void cadastrar(){

    }


    public Podcast getPodcast() {
        return podcast;
    }

    public void setPodcast(Podcast podcast) {
        this.podcast = podcast;
    }

    public Part getAudio() {
        return audio;
    }

    public void setAudio(Part audio) {
        this.audio = audio;
    }

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }
}
