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

    private TurmaVirtual turmaVirtual;
    private TurmaVirtualDao turmaVirtualDao;
    private List<TurmaVirtual> turmas;

    @PostConstruct
    public void init(){
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

    public void listar (){
        try {
            turmas = turmaVirtualDao.listar();
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

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

    public TurmaVirtual getTurmaVirtual() {
        return turmaVirtual;
    }

    public void setTurmaVirtual(TurmaVirtual turmaVirtual) {
        this.turmaVirtual = turmaVirtual;
    }

    public List<TurmaVirtual> getTurmas() {
        return turmas;
    }

    public void setTurmas(List<TurmaVirtual> turmas) {
        this.turmas = turmas;
    }
}
