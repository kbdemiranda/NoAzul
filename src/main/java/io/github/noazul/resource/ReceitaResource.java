package io.github.noazul.resource;

import io.github.noazul.domain.Receita;
import io.github.noazul.domain.dto.ReceitaDTO;
import io.github.noazul.repository.ReceitaRepository;
import io.github.noazul.service.ReceitaService;
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


    private final ReceitaService receitaService;

    public ReceitaResource(ReceitaService receitaService) {
        this.receitaService = receitaService;
    }

    @GetMapping
    public ResponseEntity<Page<Receita>> getAllReceitas(Pageable pageable, @RequestParam(required = false) String descricao){
        Page<Receita> receitas = receitaService.obterTodaReceitas(pageable, descricao);
        return ResponseEntity.ok().body(receitas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Receita> getDetailsReceita(@PathVariable Long id){
        Receita receita = receitaService.obterReceitaDetalhada(id);
        return ResponseEntity.ok(receita);
    }

    @PostMapping
    public ResponseEntity<Receita> createReceita(@RequestBody @Valid ReceitaDTO dto){
        Receita receita = receitaService.cadastrarReceita(dto);
        return ResponseEntity.created(URI.create("/receitas")).body(receita);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Receita> updateReceita(@PathVariable Long id, @RequestBody @Valid ReceitaDTO dto){
        Receita receita = receitaService.atualizarReceita(id, dto);
        return ResponseEntity.accepted().body(receita);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReceita(@PathVariable Long id){
        receitaService.excluirReceita(id);
        return ResponseEntity.ok("Receita deletada");
    }


}
