package com.ifpb.control.controllers;


import com.ifpb.model.dao.Exceptions.DataAccessException;
import com.ifpb.model.dao.impl.TurmaVirtualDaoImpl;
import com.ifpb.model.dao.interfaces.TurmaVirtualDao;
import com.ifpb.model.domain.Podcast;
import com.ifpb.model.domain.TurmaVirtual;
import com.ifpb.model.domain.Usuario;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@RequestScoped
public class TurmaVirtualBean {

    private String nome;
    private String descricao;
    private Usuario criador;
    private List<Podcast> podcasts;
    private List<Usuario> participantes;
    private TurmaVirtualDao turmaVirtualDao;
    private List<TurmaVirtual> turmas;

    @PostConstruct
    public void init(){
        podcasts = new ArrayList<Podcast>();
        participantes = new ArrayList<Usuario>();
        turmaVirtualDao = new TurmaVirtualDaoImpl();
        turmas = new ArrayList<TurmaVirtual>();
    }

    public String salvar(TurmaVirtual tv){
        try {
            turmaVirtualDao.salvar(tv);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return "turmasvirtuais";
    }

    public String remover(String reference){
        try {
            turmaVirtualDao.remover(reference);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return "turmasvirtuais";
    }

    public List<TurmaVirtual> listar (){
        try {
            return turmas = turmaVirtualDao.listar();
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return turmas;
    }

    public String buscarTurma(String reference) throws DataAccessException {
        try{
            turmaVirtualDao.buscar(reference);
        }
        catch (DataAccessException e){
            e.printStackTrace();
        }
        return "turmasvirtuais";
    }

    public String adicionarAlunoTurma(String nomeTurma, String emailAluno){
        try {
            turmaVirtualDao.adicionarAlunoaTurma(nomeTurma, emailAluno);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return "turmasvirtuais";
    }

    public String removerAlunoDeTurma(String nomeTurma, String emailAluno){
        try {
            turmaVirtualDao.removerAlunodeTurma(nomeTurma, emailAluno);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return "turmasvirtuais";
    }

    public String listarTurmasCriadas(String criador){
        try {
            turmaVirtualDao.listarTurmasCriadas(criador);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return "turmasvirtuais";
    }

    public String listarTurmasParticipantes(String emailAluno){
        try {
            turmaVirtualDao.listarTurmasParticiantes(emailAluno);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return "turmasvirtuais";
    }

}
