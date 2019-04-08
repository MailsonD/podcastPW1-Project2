package com.ifpb.control.controllers;


import com.ifpb.model.dao.Exceptions.DataAccessException;
import com.ifpb.model.dao.impl.TurmaVirtualDaoImpl;
import com.ifpb.model.dao.impl.UsuarioDaoImpl;
import com.ifpb.model.dao.interfaces.TurmaVirtualDao;
import com.ifpb.model.dao.interfaces.UsuarioDao;
import com.ifpb.model.domain.Enum.Tipo;
import com.ifpb.model.domain.Podcast;
import com.ifpb.model.domain.TurmaVirtual;
import com.ifpb.model.domain.Usuario;
import org.primefaces.model.DualListModel;

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
    private UsuarioDao usuarioDao;
    private DualListModel<String> emailAlunos;

    @ManagedProperty("#{usuarioBean}")
    private UsuarioBean usuarioBean;

    @ManagedProperty("#{loginBean}")
    private LoginBean loginBean;


    @PostConstruct
    public void init() {
        turmaVirtualDao = new TurmaVirtualDaoImpl();
        try {
            if (loginBean.getUser().getTipo().equals(Tipo.PROFESSOR)) {
                turmas = turmaVirtualDao.listarTurmasCriadas(loginBean.getUser().getEmail());

            } else {
                turmas = turmaVirtualDao.listarTurmasParticiantes(loginBean.getUser().getEmail());
            }

            turmaVirtual = new TurmaVirtual();
            usuarioDao = new UsuarioDaoImpl();

            List<String> alunosTarget = new ArrayList<>();
            List<String> alunosSource = new ArrayList<>();
            List<Usuario> alunos = usuarioDao.listarAlunos();
            for (Usuario aluno : alunos) {
                alunosSource.add(aluno.getEmail());
            }
            emailAlunos = new DualListModel<>(alunosSource, alunosTarget);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }


    public String salvar() {

        try {
            this.turmaVirtual.setCriador(usuarioBean.getLoginBean().getUser());
            List<Usuario> alunos = new ArrayList<>();
            for (String email : emailAlunos.getTarget()) {
                alunos.add(usuarioDao.buscar(email));
            }
            this.turmaVirtual.setParticipantes(alunos);
            turmaVirtualDao.salvar(this.turmaVirtual);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        return "";
    }

    public String remover() {
        try {
            turmaVirtualDao.remover(turmaVirtual.getNome());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return "";
    }


    public String buscarTurma() throws DataAccessException {
        try {
            turmaVirtualDao.buscar(turmaVirtual.getNome());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String adicionarAlunoTurma() {
        try {
            turmaVirtualDao.adicionarAlunoaTurma(turmaVirtual.getNome(), usuarioBean.getUsuario().getEmail());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String removerAlunoDeTurma() {
        try {
            turmaVirtualDao.removerAlunodeTurma(turmaVirtual.getNome(), usuarioBean.getUsuario().getEmail());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return "";
    }

    public void listarTurmasCriadas() {
        try {
            turmas = turmaVirtualDao.listarTurmasCriadas(usuarioBean.getLoginBean().getUser().getEmail());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    public String listarTurmasParticipantes() {
        try {
            turmaVirtualDao.listarTurmasParticiantes(usuarioBean.getUsuario().getEmail());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return "";
    }

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

    public DualListModel<String> getEmailAlunos() {
        return emailAlunos;
    }

    public void setEmailAlunos(DualListModel<String> emailAlunos) {
        this.emailAlunos = emailAlunos;
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
