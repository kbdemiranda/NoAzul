package io.github.noazul.domain.relatorios;

import io.github.noazul.domain.Categoria;
import io.github.noazul.domain.Despesa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class DespesaCategoria {

    private Categoria categoria;
    private BigDecimal valor;

    @Deprecated
    public DespesaCategoria(Despesa despesa) {
        this.categoria = despesa.getCategoria();
        this.valor = despesa.getValor();
    }
}
