package br.com.easysoftware.sgiapi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.easysoftware.sgiapi.dto.MembroDTO;
import br.com.easysoftware.sgiapi.dto.ParenteDTO;
import br.com.easysoftware.sgiapi.model.Membro;
import br.com.easysoftware.sgiapi.model.Parente;
import br.com.easysoftware.sgiapi.service.MembroService;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/membros")
public class MembroController {
    
    @Autowired
    private MembroService membroService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @GetMapping
    public ResponseEntity<List<Membro>> listar(){
        List<Membro> membros = membroService.listar();
        return ResponseEntity.ok(membros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MembroDTO> buscarPorId(@PathVariable Long id){
        Membro membro = membroService.buscarPorId(id);
        return ResponseEntity.ok().body(getMembroDTO(membro));
    }

    @PostMapping
    public ResponseEntity<Membro> criar(@RequestBody Membro membro){
        Membro salvo = membroService.salvar(membro);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @GetMapping("/parentes")
    public ResponseEntity<List<ParenteDTO>> buscarParentes(@RequestBody Membro membro){
        List<Parente> parentes = membroService.buscarParentes(membro);
        return ResponseEntity.ok(getParentes(parentes));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Membro> atualizarParcial(@PathVariable Long id, @RequestBody Map<String, Object> campos){
        Membro atualizado = membroService.atualizarParcial(id, campos, httpServletRequest);
        return ResponseEntity.ok().body(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id){
        membroService.remover(id);
        return ResponseEntity.noContent().build();
    }

    private MembroDTO getMembroDTO(Membro membro){
        MembroDTO membroDTO = MembroDTO.builder()
                                            .dataBatismo(membro.getDataBatismo())
                                            .dataConversao(membro.getDataConversao())
                                            .dataNascimento(membro.getDataBatismo())
                                            .id(membro.getId())
                                            .nome(membro.getNome())
                                            .build();

        return membroDTO;
    }

    private List<ParenteDTO> getParentes(List<Parente> parentes){

        List<ParenteDTO> parentesDTO = new ArrayList<>();
        for(Parente parente : parentes){
            ParenteDTO parenteDTO = ParenteDTO.builder()
                                                .parentescoEnum(parente.getParentesco())
                                                .nome(parente.getNome())
                                                .build();

            parentesDTO.add(parenteDTO);
        }

        return parentesDTO;
    }
}
