package io.github.noazul.resource;

import io.github.noazul.domain.Despesa;
import io.github.noazul.domain.dto.DespesaDTO;
import io.github.noazul.repository.DespesaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/despesas")
public class DespesaResource {

    private final DespesaRepository despesaRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    public DespesaResource(DespesaRepository despesaRepository) {
        this.despesaRepository = despesaRepository;
    }

    @GetMapping
    public ResponseEntity<Page<Despesa>> getAllDespesas(Pageable pageable){
        Page<Despesa> despesas = despesaRepository.findAll(pageable);
        return ResponseEntity.ok().body(despesas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Despesa> getDetailsDespesa(@PathVariable Long id){
        Despesa despesa = getDespesa(id);
        return ResponseEntity.ok(despesa);
    }

    @PostMapping
    public ResponseEntity<Despesa> createDespesa(@RequestBody @Valid DespesaDTO dto){
        Despesa despesa = despesaRepository.saveAndFlush(modelMapper.map(dto, Despesa.class));
        return ResponseEntity.created(URI.create("/despesas")).body(despesa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Despesa> updateDespesa(@PathVariable Long id, @RequestBody @Valid DespesaDTO dto){
        Despesa despesa = getDespesa(id);
        modelMapper.map(dto, despesa);
        return ResponseEntity.accepted().body(despesa);
    }


    private Despesa getDespesa(Long id) {
        return despesaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
