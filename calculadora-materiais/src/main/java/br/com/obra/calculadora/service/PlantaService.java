package br.com.obra.calculadora.service;

import br.com.obra.calculadora.domain.Aresta;
import br.com.obra.calculadora.dto.ArestaRequest;
import br.com.obra.calculadora.repository.ArestaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PlantaService {

    private final ArestaRepository arestaRepository;
    private final ArestaMapper arestaMapper;

    public PlantaService(ArestaRepository arestaRepository, ArestaMapper arestaMapper) {
        this.arestaRepository = arestaRepository;
        this.arestaMapper = arestaMapper;
    }

    @Transactional
    public List<Aresta> salvarArestas(List<ArestaRequest> arestasRequest) {
        List<Aresta> arestas = arestasRequest.stream()
                .map(arestaMapper::toEntity)
                .toList();
        return arestaRepository.saveAll(arestas);
    }

    public List<Aresta> listarArestas() {
        return arestaRepository.findAll();
    }
}
