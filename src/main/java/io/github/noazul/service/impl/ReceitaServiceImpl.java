package io.github.noazul.service.impl;

import io.github.noazul.domain.Receita;
import io.github.noazul.domain.dto.ReceitaDTO;
import io.github.noazul.repository.ReceitaRepository;
import io.github.noazul.service.ReceitaService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ReceitaServiceImpl implements ReceitaService {

    private final ReceitaRepository receitaRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    public ReceitaServiceImpl(ReceitaRepository receitaRepository) {
        this.receitaRepository = receitaRepository;
    }

    @Override
    public Page<Receita> obterTodaReceitas(Pageable pageable, String descricao) {
        if (descricao == null){
            return receitaRepository.findAll(pageable);
        }else {
            return receitaRepository.findAllByDescricao(pageable,descricao);
        }
    }

    @Override
    public Receita obterReceitaDetalhada(Long id) {
        return getReceita(id);
    }

    @Override
    public Receita cadastrarReceita(ReceitaDTO dto) {
        Receita receita = modelMapper.map(dto, Receita.class);
        return receitaRepository.saveAndFlush(receita);
    }

    @Override
    public Receita atualizarReceita(Long id, ReceitaDTO dto) {
        Receita receita = getReceita(id);
        modelMapper.map(dto, receita);
        receitaRepository.saveAndFlush(receita);
        return receita;
    }

    @Override
    public void excluirReceita(Long id) {
        Receita receita = getReceita(id);
        receitaRepository.delete(receita);
    }

    private Receita getReceita(Long id) {
        return receitaRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
