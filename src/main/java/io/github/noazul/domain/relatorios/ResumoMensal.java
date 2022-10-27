package io.github.noazul.domain.relatorios;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class ResumoMensal {

    private BigDecimal despesasMes;
    private BigDecimal receitasMes;
    private BigDecimal saldoMes;
    private List<DespesaCategoria> totalCategoria;
}
