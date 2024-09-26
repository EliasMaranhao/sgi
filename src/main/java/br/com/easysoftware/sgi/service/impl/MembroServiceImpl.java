package br.com.easysoftware.sgi.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.easysoftware.sgi.dto.CargoDTO;
import br.com.easysoftware.sgi.dto.CargoDTOMapper;
import br.com.easysoftware.sgi.dto.ContatoDTO;
import br.com.easysoftware.sgi.dto.ContatoDTOMapper;
import br.com.easysoftware.sgi.dto.MembroDTO;
import br.com.easysoftware.sgi.dto.ParenteDTO;
import br.com.easysoftware.sgi.dto.ParenteDTOMapper;
import br.com.easysoftware.sgi.entity.Membro;
import br.com.easysoftware.sgi.exception.MembroNaoEncontradoException;
import br.com.easysoftware.sgi.repository.MembroRepository;
import br.com.easysoftware.sgi.service.MembroService;

@Service
public class MembroServiceImpl implements MembroService{

    @Autowired
    private MembroRepository membroRepository;
    @Autowired
    private ContatoDTOMapper contatoDTOMapper;
    @Autowired
    private CargoDTOMapper cargoDTOMapper;
    @Autowired
    private ParenteDTOMapper parenteDTOMapper;

    @Override
    public Membro salvar(Membro membro) {
        Membro salvo = membroRepository.save(membro);
        return salvo;
    }

    @Override
    public MembroDTO buscarPorId(Long id) {
        Membro membro =  buscar(id);

        List<ContatoDTO> contatos = membro.getContatos().stream().map(contatoDTOMapper).collect(Collectors.toList());
        List<CargoDTO> cargos = membro.getCargos().stream().map(cargoDTOMapper).collect(Collectors.toList());
        List<ParenteDTO> parentes = membro.getParentes().stream().map(parenteDTOMapper).collect(Collectors.toList());

        return new MembroDTO(membro.getNome(), 
                             membro.getDataConversao(), 
                             membro.getDataBatismo(), 
                             membro.getDataNascimento(), 
                             membro.getIgreja().getNome(), 
                             contatos, 
                             cargos, 
                             parentes);
    }
    
    private Membro buscar(Long id){
        //Optional<MembroDTO> optional = membroRepository.findById(id).map(membroDTOMapper);
        Optional<Membro> optional = membroRepository.findById(id);

        if(optional.isEmpty()){
            throw new MembroNaoEncontradoException("Membro não encontrado.");
        }

        return optional.get();
    }

    @Override
    public Membro buscarPeloNome(String nome) {
        Membro membro = membroRepository.findByNome(nome);

        if(membro == null){
            throw new MembroNaoEncontradoException("Membro não encontrado.");
        }

       return membro;
    }

    @Override
    public Membro atualizar(Long id, Membro membro) {
        Membro salvo = buscar(id);
        BeanUtils.copyProperties(membro, salvo, "id");
        return membroRepository.save(salvo);
    }
}
