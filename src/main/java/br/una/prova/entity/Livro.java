package br.una.prova.entity;

import org.hibernate.annotations.Formula;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class Livro {
    @Id
    @GeneratedValue
    private Integer id;

    @NotBlank(message = "Campo obrigatório")
    private String nome;

    private Integer ano;

    private Integer volume;

    @ManyToOne
    @JoinColumn(name = "editora_id", referencedColumnName = "id")
    private Editora editora;


    @ManyToOne
    @JoinColumn(name = "autor_id", referencedColumnName = "id")
    private Autor autor;

    @Formula("(select AVG(v.estrela + 0.0) from voto v where v.livro_id = id)")
    private Double mediaAvaliacoes;

    public Double getMediaAvaliacoes() {
        return mediaAvaliacoes;
    }

    public void setMediaAvaliacoes(Double mediaAvaliacoes) {
        this.mediaAvaliacoes = mediaAvaliacoes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public Editora getEditora() {
        return editora;
    }

    public void setEditora(Editora editora) {
        this.editora = editora;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Livro filme = (Livro) o;
        return Objects.equals(id, filme.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
