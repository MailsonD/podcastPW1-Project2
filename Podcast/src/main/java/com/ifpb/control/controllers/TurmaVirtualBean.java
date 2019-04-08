package com.ifpb.control.controllers;


import com.ifpb.model.dao.Exceptions.DataAccessException;
import com.ifpb.model.dao.impl.TurmaVirtualDaoImpl;
import com.ifpb.model.dao.interfaces.TurmaVirtualDao;
import com.ifpb.model.domain.Podcast;
import com.ifpb.model.domain.TurmaVirtual;
import com.ifpb.model.domain.Usuario;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
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

    @ManagedProperty("#{usuarioBean}")
    private UsuarioBean usuarioBean;



    @PostConstruct
    public void init(){
        turmaVirtualDao = new TurmaVirtualDaoImpl();
        turmas = new ArrayList<TurmaVirtual>();
        turmaVirtual = new TurmaVirtual();

    }

    public String salvar(){

        try {
            this.turmaVirtual.setCriador(usuarioBean.getLoginBean().getUser());
            this.turmaVirtual.setParticipantes(usuarioBean.getAlunos());
            turmaVirtualDao.salvar(this.turmaVirtual);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        return "turmasvirtuais";
    }

    public String remover(){
        try {
            turmaVirtualDao.remover(turmaVirtual.getNome());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return "turmasvirtuais";
    }


    public String buscarTurma() throws DataAccessException {
        try{
            turmaVirtualDao.buscar(turmaVirtual.getNome());
        }
        catch (DataAccessException e){
            e.printStackTrace();
        }
        return "turmasvirtuais";
    }

    public String adicionarAlunoTurma(){
        try {
            turmaVirtualDao.adicionarAlunoaTurma(turmaVirtual.getNome(),usuarioBean.getUsuario().getEmail());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return "turmasvirtuais";
    }

    public String removerAlunoDeTurma(){
        try {
            turmaVirtualDao.removerAlunodeTurma(turmaVirtual.getNome(),usuarioBean.getUsuario().getEmail());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return "turmasvirtuais";
    }

    public void listarTurmasCriadas(){
        try {
            turmas = turmaVirtualDao.listarTurmasCriadas(usuarioBean.getLoginBean().getUser().getEmail());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    public String listarTurmasParticipantes(){
        try {
            turmaVirtualDao.listarTurmasParticiantes(usuarioBean.getUsuario().getEmail());
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

    public UsuarioBean getUsuarioBean() {
        return usuarioBean;
    }

    public void setUsuarioBean(UsuarioBean usuarioBean) {
        this.usuarioBean = usuarioBean;
    }
}
