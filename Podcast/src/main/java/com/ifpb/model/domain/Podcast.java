package com.ifpb.model.domain;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Mailson Dennis
 *
 */

public class Podcast {

    private int id;
    private String titulo;
    private String descricao;
    private String categoria;
    private String audioPath;
    private Usuario dono;
    private List<Comentario> comentarios;

    public Podcast() {
        comentarios = new ArrayList<>();
    }

    public Podcast(String titulo, String descricao, String categoria, String audioPath, Usuario dono, List<Comentario> comentarios) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.categoria = categoria;
        this.audioPath = audioPath;
        this.dono = dono;
        this.comentarios = comentarios;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getAudioPath() {
        return audioPath;
    }

    public void setAudioPath(String audioPath) {
        this.audioPath = audioPath;
    }

    public Usuario getDono() {
        return dono;
    }

    public void setDono(Usuario dono) {
        this.dono = dono;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Podcast podcast = (Podcast) o;
        return id == podcast.id &&
                Objects.equals(titulo, podcast.titulo) &&
                Objects.equals(descricao, podcast.descricao) &&
                Objects.equals(categoria, podcast.categoria) &&
                Objects.equals(audioPath, podcast.audioPath) &&
                Objects.equals(dono, podcast.dono) &&
                Objects.equals(comentarios, podcast.comentarios);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, descricao, categoria, audioPath, dono, comentarios);
    }

    @Override
    public String toString() {
        return "Podcast{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", categoria='" + categoria + '\'' +
                ", audioPath='" + audioPath + '\'' +
                ", dono=" + dono +
                ", comentarios=" + comentarios +
                '}';
    }
}
