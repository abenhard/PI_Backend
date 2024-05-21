package br.csi.PI_Backend.model.ordem_servico;

import java.sql.Date;

public record OrdemDeServicoTecnicoDTO(
        String clienteCPF,
        String funcionariologin,
        String status,
        String tipo_servico,
        String descricao_problema,
        String relatorio_tecnico,
        String produto_extra,
        Date data_previsao,
        String localizacao) {
}
