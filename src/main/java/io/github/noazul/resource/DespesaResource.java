package io.github.noazul.resource;

import io.github.noazul.domain.Despesa;
import io.github.noazul.repository.DespesaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/despesas")
public class DespesaResource {

    private final DespesaRepository despesaRepository;

    public DespesaResource(DespesaRepository despesaRepository) {
        this.despesaRepository = despesaRepository;
    }

    @GetMapping
    public ResponseEntity<Page<Despesa>> getAllDespesas(Pageable pageable){
        Page<Despesa> despesas = despesaRepository.findAll(pageable);
        return ResponseEntity.ok().body(despesas);
    }
}
