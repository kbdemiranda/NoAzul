package io.github.noazul.service;

import io.github.noazul.domain.Receita;
import io.github.noazul.domain.dto.ReceitaDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReceitaService {

    Page<Receita> obterTodaReceitas(Pageable pageable, String descricao);

    Receita obterReceitaDetalhada(Long id);

    Receita cadastrarReceita(ReceitaDTO dto);

    Receita atualizarReceita(Long id, ReceitaDTO dto);

    void excluirReceita(Long id);

}
