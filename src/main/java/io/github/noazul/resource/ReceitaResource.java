package io.github.noazul.resource;

import io.github.noazul.domain.Receita;
import io.github.noazul.domain.dto.ReceitaDTO;
import io.github.noazul.repository.ReceitaRepository;
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
@RequestMapping("/receitas")
public class ReceitaResource {

    private final ReceitaRepository receitaRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    public ReceitaResource(ReceitaRepository receitaRepository) {
        this.receitaRepository = receitaRepository;
    }

    @GetMapping
    public ResponseEntity<Page<Receita>> getAllReceitas(Pageable pageable){
        Page<Receita> receitas = receitaRepository.findAll(pageable);
        return ResponseEntity.ok().body(receitas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Receita> getDetailsReceita(@PathVariable Long id){
        Receita receita = getReceita(id);
        return ResponseEntity.ok(receita);
    }

    @PostMapping
    public ResponseEntity<Receita> createReceita(@RequestBody @Valid ReceitaDTO dto){
        Receita receita = receitaRepository.saveAndFlush(modelMapper.map(dto, Receita.class));
        return ResponseEntity.created(URI.create("/receitas")).body(receita);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Receita> updateReceita(@PathVariable Long id, @RequestBody @Valid ReceitaDTO dto){
        Receita receita = getReceita(id);
        modelMapper.map(dto, receita);
        return ResponseEntity.accepted().body(receita);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReceita(@PathVariable Long id){
        receitaRepository.delete(getReceita(id));
        return ResponseEntity.ok("Receita deletada");
    }

    private Receita getReceita(Long id) {
        return receitaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
