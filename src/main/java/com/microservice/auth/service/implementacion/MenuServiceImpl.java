package com.microservice.auth.service.implementacion;

import com.microservice.auth.dto.MenuDTO;
import com.microservice.auth.jpa.entity.MenuEntity;
import com.microservice.auth.jpa.repository.IComponentRepository;
import com.microservice.auth.jpa.repository.IMenuRepository;
import com.microservice.auth.mapper.MenuMapper;
import com.microservice.auth.service.IMenuService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements IMenuService {

    private final IMenuRepository iMenuRepository;
    private final MenuMapper menuMapper;
    private final IComponentRepository iComponentRepository;

    @Override
    public List<MenuDTO> findAll() {
        List<MenuEntity> menuEntities = iMenuRepository.findAll();
        return menuEntities.stream()
                .map(menuMapper::mapToDto)
                .toList();
    }

    @Override
    @Transactional
    public boolean createStatus(MenuDTO entity) {
        MenuEntity menuEntity = menuMapper.mapToEntity(entity);

        if (menuEntity.getComponentEntityList() != null) {
            menuEntity.getComponentEntityList().forEach(component -> component.setMenuEntity(menuEntity));
        }

        return menuMapper.mapToDto(iMenuRepository.save(menuEntity)).getComponentDTOList().isEmpty();
    }

    @Override
    public Optional<MenuDTO> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public MenuDTO create(MenuDTO entity) {
        return null;
    }



    @Override
    public MenuDTO update(Integer id, MenuDTO entity) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }

}
