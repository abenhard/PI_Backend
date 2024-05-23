package br.csi.PI_Backend.model.ordem_servico;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrdemDeServicoTecnicoDTO{
        String clienteCPF;
        String funcionariologin;
        String status;
        String tipo_servico;
        String descricao_problema;
        String relatorio_tecnico;
        String produto_extra;
        Date data_previsao;
        String localizacao;

}
