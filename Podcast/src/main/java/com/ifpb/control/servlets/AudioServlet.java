package com.ifpb.control.servlets;

import com.ifpb.model.dao.Exceptions.DataAccessException;
import com.ifpb.model.dao.impl.PodcastDaoImpl;
import com.ifpb.model.dao.interfaces.PodcastDao;
import com.ifpb.model.domain.Podcast;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@WebServlet("/audio")
public class AudioServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PodcastDao podcastDao = new PodcastDaoImpl();

        int idProduto = Integer.parseInt(request.getParameter("idPodcast"));

        try {
            Podcast podcast = ((PodcastDaoImpl) podcastDao).buscar(idProduto);

            String audioPath = "/media/antonio/MIDIAS/ADS/P4/PW1/audio/";

            File audio = new File(audioPath + podcast.getAudioPath()+".mp3");
            if (audio.exists()) {
                response.setContentType("audio/mp3");
                response.getOutputStream().write(Files.readAllBytes(audio.toPath()));
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
        }




    }

}
