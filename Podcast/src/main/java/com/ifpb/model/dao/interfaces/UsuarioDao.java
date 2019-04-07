package com.ifpb.model.dao.interfaces;

import com.ifpb.model.dao.Exceptions.DataAccessException;
import com.ifpb.model.domain.Usuario;

import java.util.List;

/**
 *
 * @author Mailson Dennis
 *
 */
public interface UsuarioDao extends DaoIF<Usuario> {

    List<Usuario> listarAlunos() throws DataAccessException;
    List<Usuario> listarProfessores() throws DataAccessException;
    List<Usuario> buscarAlunosPorTurma(String nomeTurma) throws DataAccessException;
    List<Usuario> buscarAlunosQueNaoParticipamDeTurma(String nomeTurma) throws DataAccessException;
    void atualizar(String email,Usuario usuario) throws DataAccessException;
    Usuario autenticarUsuario(String email, String senha) throws DataAccessException;
    void salvarFoto(String path,String emailUsuario) throws DataAccessException;
    void setAdmin(String emailUsuaio) throws DataAccessException;

}
