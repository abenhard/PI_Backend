package br.csi.PI_Backend.model.ordem_servico;

import java.security.Timestamp;

public record OrdemDeServicoAtendenteDTO(String clienteCPF, String funcionariologin, String status, String tipo_servico, String descricao_problema){}
