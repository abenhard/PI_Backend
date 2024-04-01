package br.csi.PI_Backend.model.endereco;


public record CidadeDTO(String nome, Long iduf, String estado){
    public CidadeDTO(Cidade cidade)
    {
        this(cidade.getNome() , cidade.getEstado().getId(), cidade.getEstado().getNome());
    }
    public Long getIduf(){return iduf;}

}
