package com.microservice.auth.service.implementacion;

import com.microservice.auth.dto.MenuDTO;
import com.microservice.auth.jpa.entity.MenuEntity;
import com.microservice.auth.jpa.repository.IComponentRepository;
import com.microservice.auth.jpa.repository.IMenuRepository;
import com.microservice.auth.maper.MenuMapper;
import com.microservice.auth.service.IMenuService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService implements IMenuService {

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
    public boolean save(MenuDTO menuDTO) {
        MenuEntity menuEntity = menuMapper.mapToEntity(menuDTO);

        if (menuEntity.getComponentEntityList() != null) {
            menuEntity.getComponentEntityList().forEach(component -> component.setMenuEntity(menuEntity));
        }

        return !iMenuRepository.save(menuEntity).getComponentEntityList().isEmpty();
    }
}
