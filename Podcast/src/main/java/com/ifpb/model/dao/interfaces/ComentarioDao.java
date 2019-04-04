package com.ifpb.model.dao.interfaces;

import com.ifpb.model.dao.Exceptions.DataAccessException;
import com.ifpb.model.domain.Comentario;

import java.util.List;

/**
 *
 * @author Mailson Dennis
 *
 */
public interface ComentarioDao {
    void salvar(Comentario comentario,int podcast) throws DataAccessException;
    void deletar(int idComentario) throws DataAccessException;
    void deletarPorPodcast(int podcast) throws DataAccessException;
    List<Comentario> buscarPorPodcast(int podcast) throws DataAccessException;

}
