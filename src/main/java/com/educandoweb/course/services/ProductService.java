package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.educandoweb.course.entities.Category;
import com.educandoweb.course.entities.Product;
import com.educandoweb.course.repositories.CategoryRepository;
import com.educandoweb.course.repositories.ProductRepository;
import com.educandoweb.course.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

//2 operações - buscar todos os usuarios e buscar usuario por Id
@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;
	
	 @Autowired
	private CategoryRepository categoryRepository;
	 
	 
	
	public List<Product> findAll() {
		return repository.findAll();
	}
	
	public Product findById(Long id) {
		Optional<Product> obj = repository.findById(id);
		return obj.get();
	}
	
	public Product insert(Product obj) {
		return repository.save(obj);
	}
	
	public void delete(Long id) {
        Product product = repository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, 
                "Produto não encontrado com ID: " + id
            ));
        
        repository.delete(product);
    }
	
	public Product update(Long id, Product obj) {
		try {
		Product entity = repository.getReferenceById(id);
		updateData(entity, obj); //Metodo criado abaixo
		updateCategories(entity, obj.getCategories());
		return repository.save(entity);
		} catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(Product entity, Product obj) {
		//Só iremos atualizar o nome, email e telefone
		entity.setName(obj.getName());
		entity.setDescription(obj.getDescription());
		entity.setPrice(obj.getPrice());
	}
	
	private void updateCategories(Product entity, Set<Category> newCategories) {
        // Limpa as categorias existentes
        entity.getCategories().clear();
        
        // Para cada categoria recebida no JSON
        for (Category cat : newCategories) {
            // Busca a categoria no banco para garantir que existe
            Category category = categoryRepository.findById(cat.getId())
                .orElseThrow(() -> new ResourceNotFoundException(cat.getId()));
            
            // Adiciona a categoria ao produto
            entity.getCategories().add(category);
        }
    }
}
