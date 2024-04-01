package br.csi.PI_Backend.model.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    public Usuario getById(Long id);
    public Usuario findByLogin(String login);
    public Usuario findByCpf(String login);

}
