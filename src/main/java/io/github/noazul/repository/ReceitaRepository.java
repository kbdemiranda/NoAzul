package io.github.noazul.repository;

import io.github.noazul.domain.Receita;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Long> {

    Page<Receita> findAllByDescricao(Pageable pageable, String descricao);

    @Query("SELECT r from Receita r WHERE EXTRACT(year from r.data) = ?1 AND EXTRACT(month from r.data) = ?2")
    Page<Receita> obterReceitasPorMes(Pageable pageable, Integer ano, Integer mes);

}
