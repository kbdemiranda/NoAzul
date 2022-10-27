package io.github.noazul.resource;

import io.github.noazul.domain.relatorios.ResumoMensal;
import io.github.noazul.service.RelatorioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RelatorioResource {

    private final RelatorioService relatorioService;

    public RelatorioResource(RelatorioService relatorioService) {
        this.relatorioService = relatorioService;
    }

    @GetMapping("/resumo/{ano}/{mes}")
    public ResponseEntity<ResumoMensal> obterResumo(@PathVariable Integer ano, @PathVariable Integer mes){
        ResumoMensal resumoMes = relatorioService.getResumoMes(ano, mes);
        return ResponseEntity.ok(resumoMes);
    }


}
