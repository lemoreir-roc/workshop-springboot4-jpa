package com.educandoweb.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.course.entities.Product;

//Essa interface permite trabalhar com varias operações de categoria
public interface ProductRepository extends JpaRepository<Product, Long> {

}
