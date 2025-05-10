package com.microservice.auth.service;

import com.microservice.auth.dto.ProductDTO;
import com.microservice.auth.jpa.entity.ProductEntity;

public interface IProductService extends IRepositoryGenery<ProductDTO, Integer>{
}
