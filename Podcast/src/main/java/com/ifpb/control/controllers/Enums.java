package com.ifpb.control.controllers;

import com.ifpb.model.domain.Enum.Sexo;
import com.ifpb.model.domain.Enum.Tipo;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ApplicationScoped
public class Enums {

    private List<SelectItem> generos;
    private List<SelectItem> tipos;

    @PostConstruct
    public void init(){
        generos = new ArrayList<>();
        tipos = new ArrayList<>();
        generos.add(new SelectItem(Sexo.MASCULINO,"Masculino"));
        generos.add(new SelectItem(Sexo.FEMININO,"Feminino"));
        tipos.add(new SelectItem(Tipo.ALUNO,"Aluno"));
        tipos.add(new SelectItem(Tipo.PROFESSOR,"Professor"));
    }

    public List<SelectItem> getGeneros() {
        return generos;
    }

    public void setGeneros(List<SelectItem> generos) {
        this.generos = generos;
    }

    public List<SelectItem> getTipos() {
        return tipos;
    }

    public void setTipos(List<SelectItem> tipos) {
        this.tipos = tipos;
    }
}
