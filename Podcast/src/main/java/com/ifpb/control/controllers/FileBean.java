package com.ifpb.control.controllers;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.nio.file.Path;

@ManagedBean
@ApplicationScoped
public class FileBean {


    private String uploadAudioPath;

    private String uploadImagePath;

    @PostConstruct
    public void init(){
        String realpath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("");
        File filepath = new File(realpath);
        Path p = filepath.toPath();
        for(int i = 0;i<5;i++){
            p = p.getParent();
        }
        File pathAudio = new File(p.toString()+"/audios");
        File pathImagem = new File(p.toString()+"/imagens");
        if(!pathAudio.exists()){
            pathAudio.mkdirs();
        }
        if(!pathImagem.exists()){
            pathImagem.mkdirs();
        }
        uploadAudioPath = pathAudio.getAbsolutePath();
        uploadImagePath = pathImagem.getAbsolutePath();
    }

    public String getUploadAudioPath() {
        return uploadAudioPath;
    }

    public void setUploadAudioPath(String uploadAudioPath) {
        this.uploadAudioPath = uploadAudioPath;
    }

    public String getUploadImagePath() {
        return uploadImagePath;
    }

    public void setUploadImagePath(String uploadImagePath) {
        this.uploadImagePath = uploadImagePath;
    }
}
