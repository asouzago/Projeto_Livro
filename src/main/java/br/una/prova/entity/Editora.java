package br.una.prova.entity;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Editora {
    @Id
    @GeneratedValue
    private Integer id;

    @NotBlank(message = "Campo obrigatório")
    private String nome;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Editora diretor = (Editora) o;
        return Objects.equals(id, diretor.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
