package com.educandoweb.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.course.entities.User;

//Essa interface permite trabalhar com varias operações do usuario
public interface UserRepository extends JpaRepository<User, Long> {

}
