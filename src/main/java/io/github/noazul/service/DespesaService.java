package io.github.noazul.service;

import io.github.noazul.domain.Despesa;
import io.github.noazul.domain.dto.DespesaDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DespesaService {

    Page<Despesa> obterTodasDespesas(Pageable pageable, String descricao);

    Despesa obterDespesaDetalhada(Long id);

    Page<Despesa> obterDespesasPorMes(Pageable pageable, Integer ano, Integer mes);

    Despesa cadastrarDespesa(DespesaDTO dto);

    Despesa atualizarDespesa(Long id, DespesaDTO dto);

    void excluirDespesa(Long id);

}
