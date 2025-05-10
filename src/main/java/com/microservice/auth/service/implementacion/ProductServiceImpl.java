package com.microservice.auth.service.implementacion;

import com.microservice.auth.dto.ProductDTO;
import com.microservice.auth.jpa.entity.ProductEntity;
import com.microservice.auth.jpa.repository.ICommentRepository;
import com.microservice.auth.jpa.repository.IProductRepository;
import com.microservice.auth.mapper.CommentMapper;
import com.microservice.auth.mapper.ProductMapper;
import com.microservice.auth.service.IProductService;
import com.microservice.auth.util.Constantes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {

    private final ProductMapper productMapper;
    private final CommentMapper commentMapper;
    private final IProductRepository iProductRepository;
    private final ICommentRepository iCommentRepository;

    @Override
    public List<ProductDTO> findAll() {
        return iProductRepository.findAll()
                .stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProductDTO> findById(Integer id) {
        return iProductRepository.findById(id)
                .map(productMapper::toDTO);
    }

    @Override
    public ProductDTO create(ProductDTO dto) {
        ProductEntity entity = productMapper.toEntity(dto);

        // Asignar relación producto a los comentarios si existen
        if (entity.getComments() != null && !entity.getComments().isEmpty()) {
            entity.getComments().forEach(comment -> comment.setProduct(entity));
        }

        // Guardar el producto (se guardan automáticamente los comentarios por cascada)
        ProductEntity saved = iProductRepository.save(entity);

        return productMapper.toDTO(saved);
    }


    @Override
    public ProductDTO update(Integer id, ProductDTO dto) {
        Optional<ProductEntity> existingOpt = iProductRepository.findById(id);
        if (existingOpt.isEmpty()) {
            throw new RuntimeException(Constantes.PRODUCTO_NO_ENCONTRADO + id);
        }

        ProductEntity existing = existingOpt.get();
        ProductEntity updated = productMapper.toEntity(dto);

        // Actualizar campos del producto
        existing.setName(updated.getName());
        existing.setDescription(updated.getDescription());
        existing.setImageBase64(updated.getImageBase64());
        existing.setImageUrl(updated.getImageUrl());
        existing.setActive(updated.getActive());
        existing.setPrice(updated.getPrice());

        // Actualizar comentarios si vienen
        if (updated.getComments() != null) {
            updated.getComments().forEach(comment -> comment.setProduct(existing));
            existing.setComments(updated.getComments());
        }

        ProductEntity saved = iProductRepository.save(existing);
        return productMapper.toDTO(saved);
    }

    @Override
    public void delete(Integer id) {
        if (!iProductRepository.existsById(id)) {
            throw new RuntimeException(Constantes.PRODUCTO_NO_ENCONTRADO + id);
        }
        iProductRepository.deleteById(id);
    }
}
