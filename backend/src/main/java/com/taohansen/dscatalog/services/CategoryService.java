package com.taohansen.dscatalog.services;

import com.taohansen.dscatalog.entities.Category;
import com.taohansen.dscatalog.entities.CategoryDTO;
import com.taohansen.dscatalog.repositories.CategoryRepository;
import com.taohansen.dscatalog.services.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository repository;

    @Transactional(readOnly = true)
    public List<CategoryDTO> findAll() {
        List<Category> list =  repository.findAll();
        return list.stream().map(CategoryDTO::new).collect(Collectors.toList());
}

    @Transactional(readOnly = true)
    public CategoryDTO findById(Long id) {
        Optional<Category> obj = repository.findById(id);
        Category entity = obj.orElseThrow(() -> new EntityNotFoundException("Entity not Found."));
        return new CategoryDTO(entity);
    }
}
