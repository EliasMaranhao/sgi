package br.com.easysoftware.sgi.service;

import java.util.List;

import br.com.easysoftware.sgi.entity.Campo;
import br.com.easysoftware.sgi.entity.Igreja;

public interface CampoService {
    Campo salvar(Campo campo);
    Campo buscarPeloId(Long id);
    List<Igreja> buscarFiliais(Long id);
}
