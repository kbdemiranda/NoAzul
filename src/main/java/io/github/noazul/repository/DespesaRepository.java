package io.github.noazul.repository;

import io.github.noazul.domain.Despesa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DespesaRepository extends JpaRepository<Despesa, Long> {

    Page<Despesa> findAllByDescricao(Pageable pageable, String descricao);
}
