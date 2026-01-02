package com.educandoweb.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.course.entities.Order;

//Essa interface permite trabalhar com varias operações do usuario
public interface OrderRepository extends JpaRepository<Order, Long> {

}
