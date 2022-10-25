package io.github.noazul.repository;

import io.github.noazul.domain.Despesa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DespesaRepository extends JpaRepository<Despesa, Long> {

    Page<Despesa> findAllByDescricao(Pageable pageable, String descricao);

    @Query("SELECT despesa from Despesa despesa WHERE EXTRACT(year from despesa.data) = ?1 AND EXTRACT(month from despesa.data) = ?2")
    Page<Despesa> obterDespesasPorMes(Pageable pageable, Integer ano, Integer mes);
}
