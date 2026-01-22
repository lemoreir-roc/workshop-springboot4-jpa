package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.repositories.UserRepository;
import com.educandoweb.course.services.exceptions.ResourceNotFoundException;

//2 operações - buscar todos os usuarios e buscar usuario por Id
@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public List<User> findAll() {
		return repository.findAll();
	}
	
	public User findById(Long id) {
		Optional<User> obj = repository.findById(id);
		//return obj.get(); //Esse .get retorna erro 500 caso não tenha id
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
		//Caso não tenha o id, retorna erro 404 da exceção que criei
	}
	
	public User insert(User obj) {
		return repository.save(obj);
	}
	
	/*
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch(RuntimeException e) {
			e.printStackTrace();
		}
		
	}
	*/
	
	public void delete(Long id) {
        // Opção 1: Usando findById e orElseThrow (mais limpo)
        User user = repository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, 
                "Usuário não encontrado com ID: " + id
            ));
        
        repository.delete(user);
    }
	
	/*
	public void delete(Long id) {
		repository.deleteById(id);
	}
	*/
	
	public User update(Long id, User obj) {
		User entity = repository.getReferenceById(id);
		updateData(entity, obj); //Metodo criado abaixo
		return repository.save(entity);
	}

	private void updateData(User entity, User obj) {
		//Só iremos atualizar o nome, email e telefone
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
	}
	
}
