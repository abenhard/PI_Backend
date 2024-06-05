package br.csi.PI_Backend.model.ordem_servico;


import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;


@Getter
@Setter
public class OrdemDeServicoExibicaoDTO extends OrdemDeServicoDTO{
        String clienteNome;
        String funcionarioNome;
        List<String> imageUrls;

        public OrdemDeServicoExibicaoDTO(Long id, String clienteCPF, String funcionarioLogin, String status, String tipo_servico, String descricao_problema, String produto_extra, String relatorio_tecnico, BigDecimal custo_total, Timestamp data_criacao, Date data_previsao, Date data_entrega, String imagem_caminho, String localizacao, String clienteNome, String funcionarioNome, List<String> imageUrls) {
                super(id, clienteCPF, funcionarioLogin, status, tipo_servico, descricao_problema, produto_extra, relatorio_tecnico, custo_total, data_criacao, data_previsao, data_entrega, imagem_caminho, localizacao);
                this.clienteNome = clienteNome;
                this.funcionarioNome = funcionarioNome;
                this.imageUrls = imageUrls;
        }

        public OrdemDeServicoExibicaoDTO(String clienteCPF, String funcionarioLogin, String status, String tipo_servico, String descricao_problema, String relatorio_tecnico, String produto_extra, Date data_previsao, String localizacao, String clienteNome, String funcionarioNome, List<String> imageUrls) {
                super(clienteCPF, funcionarioLogin, status, tipo_servico, descricao_problema, relatorio_tecnico, produto_extra, data_previsao, localizacao);
                this.clienteNome = clienteNome;
                this.funcionarioNome = funcionarioNome;
                this.imageUrls = imageUrls;
        }
}
