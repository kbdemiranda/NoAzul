package io.github.noazul.resource;

import io.github.noazul.domain.Despesa;
import io.github.noazul.domain.dto.DespesaDTO;
import io.github.noazul.service.DespesaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/despesas")
public class DespesaResource {

    private final DespesaService despesaService;

    public DespesaResource(DespesaService despesaService) {
        this.despesaService = despesaService;
    }

    @GetMapping
    public ResponseEntity<Page<Despesa>> getAllDespesas(Pageable pageable, @RequestParam(required = false) String descricao){
        Page<Despesa> despesas = despesaService.obterTodasDespesas(pageable, descricao);
        return ResponseEntity.ok().body(despesas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Despesa> getDetailsDespesa(@PathVariable Long id){
        Despesa despesa = despesaService.obterDespesaDetalhada(id);
        return ResponseEntity.ok(despesa);
    }

    @GetMapping("/ano/{ano}/mes/{mes}")
    public ResponseEntity<Page<Despesa>> obterDespesasPorMes(Pageable pageable, @PathVariable Integer ano, @PathVariable Integer mes){
        Page<Despesa> despesas = despesaService.obterDespesasPorMes(pageable, ano, mes);
        return ResponseEntity.ok().body(despesas);
    }

    @PostMapping
    public ResponseEntity<Despesa> createDespesa(@RequestBody @Valid DespesaDTO dto){
        Despesa despesa = despesaService.cadastrarDespesa(dto);
        return ResponseEntity.created(URI.create("/despesas")).body(despesa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Despesa> updateDespesa(@PathVariable Long id, @RequestBody @Valid DespesaDTO dto){
        Despesa despesa = despesaService.atualizarDespesa(id, dto);
        return ResponseEntity.accepted().body(despesa);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDespesa(@PathVariable Long id){
        despesaService.excluirDespesa(id);
        return ResponseEntity.ok("Despesa deletada");
    }
}
