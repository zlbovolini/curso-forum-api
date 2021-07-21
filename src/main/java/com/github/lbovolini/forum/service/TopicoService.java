package com.github.lbovolini.forum.service;

import com.github.lbovolini.forum.mapper.TopicoMapper;
import com.github.lbovolini.forum.model.Topico;
import com.github.lbovolini.forum.repository.TopicoRepository;
import com.github.lbovolini.forum.request.TopicoRequest;
import com.github.lbovolini.forum.response.TopicoResponse;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class TopicoService {

    private final TopicoRepository topicoRepository;
    private final TopicoMapper topicoMapper;

    public TopicoService(TopicoRepository topicoRepository, TopicoMapper topicoMapper) {
        this.topicoRepository = topicoRepository;
        this.topicoMapper = topicoMapper;
    }

    @Transactional
    public Boolean delete(Long id) {
        Optional<Topico> optionalTopico = topicoRepository.findById(id);

        if (optionalTopico.isPresent()) {
            topicoRepository.deleteById(id);
        }

        return optionalTopico.isPresent();
    }

    public Optional<TopicoResponse> find(Long id) {
        return topicoRepository.findById(id)
                .map(topicoMapper::toResponse);
    }

    public TopicoResponse save(TopicoRequest topicoRequest) {
        Topico topico = topicoMapper.toModel(topicoRequest);
        Topico savedTopico = topicoRepository.save(topico);

        return topicoMapper.toResponse(savedTopico);
    }

    @Transactional
    public Optional<TopicoResponse> update(Long id, TopicoRequest topicoRequest) {
        boolean exists = topicoRepository.existsById(id);

        if (!exists) {
            return Optional.empty();
        }

        Topico toUpdateTopico = topicoMapper.toModel(topicoRequest);
        toUpdateTopico.setId(id);

        Topico topico = topicoRepository.save(toUpdateTopico);

        return Optional.of(topicoMapper.toResponse(topico));
    }
}
