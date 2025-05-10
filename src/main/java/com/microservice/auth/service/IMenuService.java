package com.microservice.auth.service;

import com.microservice.auth.dto.MenuDTO;
import java.util.List;

public interface IMenuService extends IRepositoryGenery<MenuDTO, Integer> {

    boolean createStatus(MenuDTO menuDTO);

}
