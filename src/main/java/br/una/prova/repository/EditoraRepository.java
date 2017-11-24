package br.una.prova.repository;

import br.una.prova.entity.Editora;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EditoraRepository extends JpaRepository<Editora,Integer> {
    List<Editora> findByNomeLike(String nome);
}
