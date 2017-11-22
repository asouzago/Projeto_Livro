package br.una.prova.repository;

import br.una.prova.entity.Livro;
import br.una.prova.entity.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VotoRepository extends JpaRepository<Voto, Integer> {

    @Query("from Voto where livro like ?")
    List<Voto> findByLivroLike(Livro filme);

}
