package com.github.lbovolini.forum.request;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class TopicoRequest {

    @NotBlank
    private String titulo;
    @NotBlank
    private String mensagem;
    @NotBlank
    private String nomeCurso;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TopicoRequest that = (TopicoRequest) o;

        return Objects.equals(titulo, that.titulo)
                && Objects.equals(mensagem, that.mensagem)
                && Objects.equals(nomeCurso, that.nomeCurso);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo, mensagem, nomeCurso);
    }
}
