package com.microservice.auth.service.implementacion;


import com.microservice.auth.dto.UserDTO;
import com.microservice.auth.jpa.entity.UserEntity;
import com.microservice.auth.jpa.repository.IApartmentRepository;
import com.microservice.auth.jpa.repository.IUserRepository;
import com.microservice.auth.mapper.UserMapper;
import com.microservice.auth.service.IRepositoryGenery;
import com.microservice.auth.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final UserMapper userMapper;
    private final IUserRepository userRepository;
    private final IApartmentRepository iApartmentRepository;


    @Override
    public List<UserDTO> findAll() {
        return userRepository.findAll().stream()
                .map(userMapper::toDTO)
                .toList();
    }

    @Override
    public Optional<UserDTO> findById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toDTO);
    }

    @Override
    public UserDTO create(UserDTO dto) {
        UserEntity entity = userMapper.toEntity(dto);
        entity.setApartmentEntity(iApartmentRepository.save(entity.getApartmentEntity()));
        return userMapper.toDTO(userRepository.save(entity));
    }

    @Override
    public UserDTO update(Long id, UserDTO dto) {
        Optional<UserEntity> optional = userRepository.findById(id);
        if (optional.isPresent()) {
            UserEntity updated = userMapper.toEntity(dto);
            updated.setId(id);
            return userMapper.toDTO(userRepository.save(updated));
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}