package br.csi.PI_Backend.service.funcionario;

import br.csi.PI_Backend.model.funcionario.Cargo;
import br.csi.PI_Backend.model.funcionario.CargoRepository;
import org.springframework.stereotype.Service;

@Service
public class CargoService {
    private final CargoRepository cargoRepository;

    public CargoService(CargoRepository repository){ this.cargoRepository = repository;}

    public Cargo getById(Long cargo_id){
        return this.cargoRepository.getById(cargo_id);
    }
    public Cargo findByNome(String name){
        return this.cargoRepository.findByNome(name);
    }
}
