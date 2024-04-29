package br.csi.PI_Backend.model.funcionario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Long> {
    public Cargo getById(Long id);
    public Cargo findByNome(String nome);
}
