package io.github.noazul.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data

public class DespesaDTO {

    @NotNull @NotEmpty
    private String descricao;
    @NotNull
    private BigDecimal valor;
    @NotNull
    private LocalDate data;

}
