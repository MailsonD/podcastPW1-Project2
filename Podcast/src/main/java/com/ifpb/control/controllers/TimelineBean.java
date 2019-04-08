package com.ifpb.control.controllers;

import com.ifpb.model.dao.Exceptions.DataAccessException;
import com.ifpb.model.dao.impl.PodcastDaoImpl;
import com.ifpb.model.dao.interfaces.PodcastDao;
import com.ifpb.model.domain.Podcast;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.util.List;

@ManagedBean
@ViewScoped
public class TimelineBean {

    private PodcastDao podcastDao;

    private Podcast podcast;

    private List<Podcast> timeline;


    @ManagedProperty("#{playerBean}")
    private PlayerBean playerBean;

    @ManagedProperty("#{searchBean}")
    private SearchBean searchBean;

    @PostConstruct
    public void init(){
        podcastDao = new PodcastDaoImpl();
        try {
            if(searchBean.getFilter()==null){
                timeline = podcastDao.listarOrdenado();
            }else{
                timeline = podcastDao.buscarPodcastsPorFiltro("%"+searchBean.getFilter()+"%");
                searchBean.setFilter(null);
            }

        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    public String tocar(){
        playerBean.setPodcast(podcast);
        return "tocar";
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

    public PlayerBean getPlayerBean() {
        return playerBean;
    }

    public void setPlayerBean(PlayerBean playerBean) {
        this.playerBean = playerBean;
    }


    public SearchBean getSearchBean() {
        return searchBean;
    }

    public void setSearchBean(SearchBean searchBean) {
        this.searchBean = searchBean;
    }
}
