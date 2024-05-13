package br.csi.PI_Backend.model.pessoa;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoDTO{
    public EnderecoDTO(String cpf, String rua, String bairro, String complemento, String cep, String numero, String cidade, String estado){
        this.cpf =cpf;
        this.rua =rua;
        this.bairro = bairro;
        this.complemento =complemento;
        this.cep = cep;
        this.numero= numero;
        this.cidade=cidade;
        this.estado=estado;
    }

    String cpf;
    String rua;
    String bairro;
    String complemento;
    String cep;
    String numero;
    String cidade;
    String estado;
}
