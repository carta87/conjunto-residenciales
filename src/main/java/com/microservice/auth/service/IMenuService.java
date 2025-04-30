package com.microservice.auth.service;

import com.microservice.auth.dto.MenuDTO;
import java.util.List;

public interface IMenuService {

    List<MenuDTO> findAll();

    boolean save(MenuDTO menuDTO);

}
