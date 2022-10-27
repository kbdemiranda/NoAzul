package io.github.noazul.service.impl;

import io.github.noazul.domain.relatorios.DespesaCategoria;
import io.github.noazul.domain.relatorios.ResumoMensal;
import io.github.noazul.repository.DespesaRepository;
import io.github.noazul.repository.ReceitaRepository;
import io.github.noazul.service.RelatorioService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class RelatorioServiceImpl implements RelatorioService {

    private final DespesaRepository despesaRepository;
    private final ReceitaRepository receitaRepository;

    public RelatorioServiceImpl(DespesaRepository despesaRepository, ReceitaRepository receitaRepository) {
        this.despesaRepository = despesaRepository;
        this.receitaRepository = receitaRepository;
    }

    @Override
    public ResumoMensal getResumoMes(Integer ano, Integer mes) {
        BigDecimal despesaMes = despesaRepository.obterResumoMensal(ano, mes);
        BigDecimal receitaMes = receitaRepository.obterResumoMensal(ano, mes);
        List<DespesaCategoria> despesaCategorias = despesaRepository.resumoPorCategoria(ano, mes);
        BigDecimal saldoMes = receitaMes.subtract(despesaMes);

        return new ResumoMensal(despesaMes, receitaMes, saldoMes, despesaCategorias);
    }
}
