package io.github.noazul.resource;

import io.github.noazul.domain.Despesa;
import io.github.noazul.domain.dto.DespesaDTO;
import io.github.noazul.repository.DespesaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<Despesa> createDespesa(@RequestBody @Valid DespesaDTO dto){
        Despesa despesa = despesaRepository.saveAndFlush(modelMapper.map(dto, Despesa.class));
        return ResponseEntity.created(URI.create("/despesas")).body(despesa);
    }
}
