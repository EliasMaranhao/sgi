package br.com.easysoftware.sgi.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.easysoftware.sgi.dto.CargoDTO;
import br.com.easysoftware.sgi.dto.mapper.CargoDTOMapper;
import br.com.easysoftware.sgi.entity.Cargo;
import br.com.easysoftware.sgi.entity.CargoFuncaoId;
import br.com.easysoftware.sgi.entity.Funcao;
import br.com.easysoftware.sgi.entity.Membro;
import br.com.easysoftware.sgi.exception.RecursoNaoExisteException;
import br.com.easysoftware.sgi.repository.CargosRepository;
import br.com.easysoftware.sgi.service.CargoService;
import br.com.easysoftware.sgi.service.FuncaoService;
import br.com.easysoftware.sgi.service.MembroService;

@Service
public class CargoServiceImpl implements CargoService{

    @Autowired
    private CargosRepository cargosRepository;

    @Autowired
    private FuncaoService funcaoService;

    @Autowired
    private MembroService membroService;

    @Autowired
    private CargoDTOMapper cargoDTOMapper;

    @Override
    public List<CargoDTO> salvar(Cargo cargo) {
        Cargo salvo = cargosRepository.save(cargo);
        return buscarCargosPorMembro(salvo.getMembro());
    }

    @Override
    public List<CargoDTO> getCargos() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCargos'");
    }

    @Override
    public List<CargoDTO> buscarCargosPorMembro(Membro membro) {
        List<Cargo> cargos = cargosRepository.findByMembro(membro);
        List<CargoDTO> cargos_dto = null;

        if(!cargos.isEmpty()){
            cargos_dto = cargos.stream().map(cargoDTOMapper).collect(Collectors.toList());
        }
        return cargos_dto;
    }

    @Override
    public Cargo editarCargo(Long membroId, Long funcaoId, Cargo cargo){
        Cargo salvo = buscarCargo(new CargoFuncaoId(membroId, funcaoId));

        Funcao funcao = funcaoService.buscarPorId(funcaoId);
        cargo.setFuncao(funcao);

        Membro membro = membroService.buscarMembroPorId(membroId);
        cargo.setMembro(membro);

        BeanUtils.copyProperties(cargo, salvo, "id");
        return cargosRepository.save(salvo);
    }
    
    private Cargo buscarCargo(CargoFuncaoId id){
        Optional<Cargo> optional = cargosRepository.findById(id);
        if(optional.isEmpty()){
            throw new RecursoNaoExisteException("Recurso buscado não foi localizado");
        }

        return optional.get();
    }

    @Override
    public Cargo buscarPorCodigo(CargoFuncaoId id){
       Cargo cargo = buscarCargo(id);
       return cargo;
    }

    @Override
    public void deletarCargo(Long membroId, Long funcaoId){
       cargosRepository.deleteById(new CargoFuncaoId(membroId, funcaoId));
    }
}
