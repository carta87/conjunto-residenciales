package com.microservice.auth.controller;

import com.microservice.auth.dto.SpaceCommunalDTO;
import com.microservice.auth.service.implementacion.SpaceCommunalServiceIml;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("espacio-comunal")
@RequiredArgsConstructor
public class SpaceCommunalController {

    private final SpaceCommunalServiceIml spaceCommunalService;

    @GetMapping
    public ResponseEntity<List<SpaceCommunalDTO>> findAll() {
        return ResponseEntity.ok(spaceCommunalService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpaceCommunalDTO> findById(@PathVariable Integer id) {
        return spaceCommunalService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<SpaceCommunalDTO> create(@RequestBody SpaceCommunalDTO dto) {
        SpaceCommunalDTO created = spaceCommunalService.create(dto);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpaceCommunalDTO> update(@PathVariable Integer id, @RequestBody SpaceCommunalDTO dto) {
        try {
            SpaceCommunalDTO updated = spaceCommunalService.update(id, dto);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        try {
            spaceCommunalService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
