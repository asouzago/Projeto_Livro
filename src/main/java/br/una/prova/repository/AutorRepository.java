package br.una.prova.repository;

import br.una.prova.entity.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor,Integer> {
    List<Autor> findByNomeLike(String nome);
}
