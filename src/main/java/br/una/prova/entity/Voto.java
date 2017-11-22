package br.una.prova.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class Voto {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "livro_id", referencedColumnName = "id")
    @NotNull(message = "Escolha o livro para votar")
    private Livro livro;

    @NotNull(message = "Preencha o seu voto")
    private Integer estrela;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Integer getEstrela() {
        return estrela;
    }

    public void setEstrela(Integer estrela) {
        this.estrela = estrela;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Voto voto = (Voto) o;
        return Objects.equals(id, voto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
