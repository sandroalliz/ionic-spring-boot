package com.sandro.cursojava.services;

import com.sandro.cursojava.domain.Category;
import com.sandro.cursojava.dto.CategoryDTO;
import com.sandro.cursojava.repository.CategoryRepository;
import com.sandro.cursojava.services.exceptions.DataIntegrityException;
import com.sandro.cursojava.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category get(Integer id){
        Optional<Category> category = categoryRepository.findById(id);
        return category.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrato! Id: " + id + ", Tipo: " + Category.class.getName()));
    }

    public Category insert(Category category){
        category.setId(null);
        return categoryRepository.save(category);
    }

    public Category update(Category category) {
        get(category.getId());
        return categoryRepository.save(category);
    }

    public void delete(Integer id){
        get(id);
        try {
            categoryRepository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Não é possivel excluir uma categoria que possui Products");
        }
    }

    public List<Category> list(){
        return categoryRepository.findAll();
    }

    public Page<Category> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
        return categoryRepository.findAll(pageRequest);
    }

    public Category fromDTO(CategoryDTO dto){
        return new Category(dto.getId(), dto.getName());
    }
}
