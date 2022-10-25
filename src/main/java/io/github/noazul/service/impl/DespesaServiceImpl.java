package io.github.noazul.service.impl;

import io.github.noazul.domain.Despesa;
import io.github.noazul.domain.dto.DespesaDTO;
import io.github.noazul.repository.DespesaRepository;
import io.github.noazul.service.DespesaService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class DespesaServiceImpl implements DespesaService{

    private final DespesaRepository despesaRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    public DespesaServiceImpl(DespesaRepository despesaRepository) {
        this.despesaRepository = despesaRepository;
    }

    @Override
    public Page<Despesa> obterTodasDespesas(Pageable pageable, String descricao) {
        if (descricao == null){
            return despesaRepository.findAll(pageable);
        }else{
            return despesaRepository.findAllByDescricao(pageable, descricao);
        }
    }

    @Override
    public Despesa obterDespesaDetalhada(Long id) {
        return getDespesa(id);
    }

    @Override
    public Despesa cadastrarDespesa(DespesaDTO dto) {
        Despesa despesa = modelMapper.map(dto, Despesa.class);
        return despesaRepository.saveAndFlush(despesa);
    }

    @Override
    public Despesa atualizarDespesa(Long id, DespesaDTO dto) {
        Despesa despesa = getDespesa(id);
        modelMapper.map(dto, despesa);
        despesaRepository.saveAndFlush(despesa);
        return despesa;
    }

    @Override
    public void excluirDespesa(Long id) {
        Despesa despesa = getDespesa(id);
        despesaRepository.delete(despesa);
    }

    private Despesa getDespesa(Long id) {
        return despesaRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
