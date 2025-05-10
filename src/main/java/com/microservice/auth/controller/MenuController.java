package com.microservice.auth.controller;

import com.microservice.auth.dto.MenuDTO;
import com.microservice.auth.service.IMenuService;
import com.microservice.auth.util.Constantes;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("menusComponentes")
@RequiredArgsConstructor
public class MenuController {

    private final IMenuService menuService;

    @GetMapping
    public ResponseEntity<List<MenuDTO>> findAll() {
        List<MenuDTO> menus = menuService.findAll();
        return ResponseEntity.ok(menus);
    }

    @PostMapping
    public ResponseEntity<String> save(@RequestBody MenuDTO menuDTO) {
        return  menuService.createStatus(menuDTO) ?
                ResponseEntity.ok(Constantes.EXITO_CREAR_INFORMACION):
                ResponseEntity.badRequest().body(Constantes.ERROR_CREAR_INFORMACION);
    }
}

