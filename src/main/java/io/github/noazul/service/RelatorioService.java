package io.github.noazul.service;

import io.github.noazul.domain.relatorios.ResumoMensal;

public interface RelatorioService {

    ResumoMensal getResumoMes(Integer ano, Integer mes);

}
