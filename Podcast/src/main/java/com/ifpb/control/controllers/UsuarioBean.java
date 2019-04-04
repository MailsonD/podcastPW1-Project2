package com.ifpb.control.controllers;
import com.ifpb.model.dao.Exceptions.DataAccessException;
import com.ifpb.model.dao.impl.UsuarioDaoImpl;
import com.ifpb.model.dao.interfaces.UsuarioDao;
import com.ifpb.model.domain.Enum.NivelAcesso;
import com.ifpb.model.domain.Enum.Sexo;
import com.ifpb.model.domain.Enum.Tipo;
import com.ifpb.model.domain.Usuario;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.time.LocalDate;

@ManagedBean
@RequestScoped
public class UsuarioBean {

    private UsuarioDao usuarioDao;

    private Usuario usuario;

    private String sexo;

    private String tipo;

    @PostConstruct
    public void init() {
        usuarioDao = new UsuarioDaoImpl();
        usuario = new Usuario();
    }

    public void cadastrar(){
        try {
            usuario.setNivelAcesso(NivelAcesso.USER);
            if(sexo.equals("f")){
                usuario.setSexo(Sexo.FEMININO);
            }else{
                usuario.setSexo(Sexo.MASCULINO);
            }
            if(tipo.equals("A")){
                usuario.setTipo(Tipo.ALUNO);
            }else{
                usuario.setTipo(Tipo.PROFESSOR);
            }
            usuario.setNascimento(LocalDate.now());
            usuarioDao.salvar(usuario);
            System.out.println("cadastrado");
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}