package com.ifpb.control.servlets;

import com.ifpb.control.controllers.FileBean;
import com.ifpb.control.util.FilePathUtil;
import com.ifpb.model.dao.Exceptions.DataAccessException;
import com.ifpb.model.dao.impl.PodcastDaoImpl;
import com.ifpb.model.dao.interfaces.PodcastDao;
import com.ifpb.model.domain.Podcast;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Logger;

@WebServlet("/audio")
public class AudioServlet extends HttpServlet {

    private Logger log = Logger.getLogger(AudioServlet.class.getName());


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PodcastDao podcastDao = new PodcastDaoImpl();

        int idPodcast = Integer.parseInt(request.getParameter("idPodcast"));

        try {
            Podcast podcast = ((PodcastDaoImpl) podcastDao).buscar(idPodcast);

            String audioPath = FilePathUtil.getPathAudio();

            log.info(audioPath);

            File audio = new File(audioPath +"/"+podcast.getAudioPath());
            if (audio.exists()) {
                response.setContentType("audio/mp3");
                response.getOutputStream().write(Files.readAllBytes(audio.toPath()));
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
        }




    }

}
