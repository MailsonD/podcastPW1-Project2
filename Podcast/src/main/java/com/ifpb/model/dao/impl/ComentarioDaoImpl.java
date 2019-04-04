package com.ifpb.model.dao.impl;

import com.ifpb.model.jdbc.ConnectionFactory;
import com.ifpb.model.dao.Exceptions.DataAccessException;
import com.ifpb.model.dao.interfaces.ComentarioDao;
import com.ifpb.model.dao.interfaces.UsuarioDao;
import com.ifpb.model.domain.Comentario;
import com.ifpb.model.dao.Exceptions.ConnectionException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mailson Dennis
 *
 */
public class ComentarioDaoImpl implements ComentarioDao {

    UsuarioDao usuarioDao;

    public ComentarioDaoImpl(){
        usuarioDao = new UsuarioDaoImpl();
    }

    @Override
    public void salvar(Comentario comentario,int podcast) throws DataAccessException {
        String query = "INSERT INTO avalia_podcast (usuario,podcast,comentario) VALUES (?,?,?)";
        try (Connection connection = ConnectionFactory.getInstance().getConnection()){
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,comentario.getUsuario().getEmail());
            statement.setInt(2,podcast);
            statement.setString(3,comentario.getTexto());
            statement.execute();
        }catch (SQLException e) {
            throw new DataAccessException("Falha ao tentar salvar um comentario");
        }
    }

    @Override
    public void deletar(int idComentario) throws DataAccessException {
        String query = "DELETE FROM avalia_podcast WHERE id = ?";
        try (Connection connection = ConnectionFactory.getInstance().getConnection()){
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,idComentario);
            statement.execute();
        }catch (SQLException e) {
            throw new DataAccessException("Falha ao tentar deletar um comentario");
        }
    }

    @Override
    public void deletarPorPodcast(int podcast) throws DataAccessException {
        String query = "DELETE FROM avalia_podcast WHERE podcast = ?";
        try (Connection connection = ConnectionFactory.getInstance().getConnection()){
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,podcast);
            statement.execute();
        }catch (SQLException e) {
            throw new DataAccessException("Falha ao tentar deletar todos os comentario de um podcast");
        }
    }

    @Override
    public List<Comentario> buscarPorPodcast(int podcast) throws DataAccessException {
        String query = "SELECT * FROM avalia_podcast WHERE podcast = ?";
        try (Connection connection = ConnectionFactory.getInstance().getConnection()){
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,podcast);
            ResultSet resultSet = statement.executeQuery();
            List<Comentario> comentarios = new ArrayList<>();
            while(resultSet.next()){
                comentarios.add(construirComentario(resultSet));
            }
            return comentarios;
        }catch (SQLException e) {
            throw new DataAccessException("Falha ao tentar buscar todos os comentarios de um podcast");
        }
    }

    private Comentario construirComentario(ResultSet resultSet) throws SQLException, DataAccessException {
        Comentario comentario = new Comentario();
        comentario.setTexto(resultSet.getString("comentario"));
        comentario.setUsuario(usuarioDao.buscar(resultSet.getString("usuario")));
        return comentario;
    }
}
