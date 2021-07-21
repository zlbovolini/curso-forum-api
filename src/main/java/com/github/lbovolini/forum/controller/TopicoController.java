package com.github.lbovolini.forum.controller;

import com.github.lbovolini.forum.request.TopicoRequest;
import com.github.lbovolini.forum.response.TopicoResponse;
import com.github.lbovolini.forum.service.TopicoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    private final TopicoService topicoService;

    public TopicoController(TopicoService topicoService) {
        this.topicoService = topicoService;
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        boolean deleted = topicoService.delete(id);

        if (deleted) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<TopicoResponse> find(@PathVariable Long id) {
        return topicoService.find(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TopicoResponse> save(@Valid @RequestBody TopicoRequest topicoRequest) {
        TopicoResponse topicoResponse = topicoService.save(topicoRequest);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(topicoResponse.getId())
                .toUri();

        return ResponseEntity.created(location).body(topicoResponse);
    }
}
