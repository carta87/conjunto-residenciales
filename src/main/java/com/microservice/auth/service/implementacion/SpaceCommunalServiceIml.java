package com.microservice.auth.service.implementacion;

import com.microservice.auth.dto.SpaceCommunalDTO;
import com.microservice.auth.jpa.entity.SpaceCommunalEntity;
import com.microservice.auth.jpa.repository.ISpaceCommunalRepository;
import com.microservice.auth.mapper.SpaceCommunalMapper;
import com.microservice.auth.service.IRepositoryGenery;
import com.microservice.auth.service.ISpaceCommunalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SpaceCommunalServiceIml implements ISpaceCommunalService {

    private final SpaceCommunalMapper spaceCommunalMapper;
    private final ISpaceCommunalRepository iSpaceCommunalRepository;

    @Override
    public List<SpaceCommunalDTO> findAll() {
        return spaceCommunalMapper.mapToDto(iSpaceCommunalRepository.findAll());
    }

    @Override
    public Optional<SpaceCommunalDTO> findById(Integer id) {
        return iSpaceCommunalRepository.findById(id)
                .map(spaceCommunalMapper::mapToDto);
    }

    @Override
    public SpaceCommunalDTO create(SpaceCommunalDTO dto) {
        SpaceCommunalEntity entity = spaceCommunalMapper.mapToEntity(dto);
        SpaceCommunalEntity saved = iSpaceCommunalRepository.save(entity);
        return spaceCommunalMapper.mapToDto(saved);
    }

    @Override
    public SpaceCommunalDTO update(Integer id, SpaceCommunalDTO dto) {
        Optional<SpaceCommunalEntity> existingEntity = iSpaceCommunalRepository.findById(id);
        if (existingEntity.isEmpty()) {
            throw new RuntimeException("Recurso con ID " + id + " no encontrado.");
        }

        // Mapea el DTO actualizado a la entidad, asegurando mantener el ID original
        SpaceCommunalEntity entityToUpdate = spaceCommunalMapper.mapToEntity(dto);
        entityToUpdate.setId(id);  // Asegúrate de tener `setId` en tu entidad

        SpaceCommunalEntity updated = iSpaceCommunalRepository.save(entityToUpdate);
        return spaceCommunalMapper.mapToDto(updated);
    }

    @Override
    public void delete(Integer id) {
        if (!iSpaceCommunalRepository.existsById(id)) {
            throw new RuntimeException("Recurso con ID " + id + " no existe.");
        }
        iSpaceCommunalRepository.deleteById(id);
    }
}
