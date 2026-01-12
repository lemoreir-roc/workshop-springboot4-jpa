package com.educandoweb.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.course.entities.OrderItem;
import com.educandoweb.course.entities.pk.OrderItemPK;

//Essa interface permite trabalhar com varias operações do usuario
public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {

}
