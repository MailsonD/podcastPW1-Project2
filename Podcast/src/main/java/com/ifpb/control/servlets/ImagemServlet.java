package com.ifpb.control.servlets;

import com.ifpb.control.util.FilePathUtil;
import com.ifpb.model.dao.Exceptions.DataAccessException;
import com.ifpb.model.dao.impl.UsuarioDaoImpl;
import com.ifpb.model.dao.interfaces.UsuarioDao;
import com.ifpb.model.domain.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Logger;

@WebServlet("/imagem")
public class ImagemServlet extends HttpServlet {

    private Logger log = Logger.getLogger(ImagemServlet.class.getName());


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UsuarioDao usuarioDao = new UsuarioDaoImpl();

        String emailUsuario = request.getParameter("emailUsuario");

        try {
            Usuario usuario= usuarioDao.buscar(emailUsuario);

            String imagemPath = FilePathUtil.getPathImagem();

            File imagem = new File(imagemPath +"/"+usuario.getFotoPath());
            if (imagem.exists()) {
                response.setContentType("image/jpeg");
                response.getOutputStream().write(Files.readAllBytes(imagem.toPath()));
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
        }


    }

}