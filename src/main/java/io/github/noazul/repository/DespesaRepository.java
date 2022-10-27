package io.github.noazul.repository;

import io.github.noazul.domain.Despesa;
import io.github.noazul.domain.relatorios.DespesaCategoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface DespesaRepository extends JpaRepository<Despesa, Long> {

    Page<Despesa> findAllByDescricao(Pageable pageable, String descricao);

    @Query("SELECT d from Despesa d WHERE EXTRACT(YEAR FROM d.data) = ?1 AND EXTRACT(MONTH FROM d.data) = ?2")
    Page<Despesa> obterDespesasPorMes(Pageable pageable, Integer ano, Integer mes);

    @Query("SELECT SUM(d.valor) FROM Despesa d WHERE EXTRACT(YEAR FROM d.data) = ?1 AND EXTRACT(MONTH FROM d.data) = ?2")
    BigDecimal obterResumoMensal(Integer ano, Integer mes);

    @Query("SELECT new Despesa(d.categoria, SUM(d.valor)) FROM Despesa d WHERE EXTRACT(YEAR FROM d.data) = ?1 AND EXTRACT(MONTH FROM d.data) = ?2 GROUP BY d.categoria")
    List<DespesaCategoria> resumoPorCategoria(Integer ano, Integer mes);
}
