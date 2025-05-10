package com.microservice.auth.service.implementacion;

import com.microservice.auth.dto.ReservationDTO;
import com.microservice.auth.jpa.entity.ReservationEntity;
import com.microservice.auth.jpa.repository.IReservationRepository;
import com.microservice.auth.mapper.ReservationMapper;
import com.microservice.auth.service.IRepositoryGenery;
import com.microservice.auth.service.IReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements IReservationService {

    private final ReservationMapper reservationMapper;
    private final IReservationRepository iReservationRepository;

    @Override
    public List<ReservationDTO> findAll() {
        return iReservationRepository.findAll()
                .stream()
                .map(reservationMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ReservationDTO> findById(Integer id) {
        return iReservationRepository.findById(id)
                .map(reservationMapper::toDTO);
    }

    @Override
    public ReservationDTO create(ReservationDTO dto) {
        ReservationEntity entity = reservationMapper.toEntity(dto);
        ReservationEntity saved = iReservationRepository.save(entity);
        return reservationMapper.toDTO(saved);
    }

    @Override
    public ReservationDTO update(Integer id, ReservationDTO dto) {
        Optional<ReservationEntity> optionalEntity = iReservationRepository.findById(id);
        if (optionalEntity.isPresent()) {
            ReservationEntity entityToUpdate = reservationMapper.toEntity(dto);
            entityToUpdate.setId(id); // aseguramos que se mantenga el ID original
            ReservationEntity updated = iReservationRepository.save(entityToUpdate);
            return reservationMapper.toDTO(updated);
        }
        return null;
    }

    @Override
    public void delete(Integer id) {
        iReservationRepository.deleteById(id);
    }
}
