package com.arpo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arpo.models.Inventory;

public interface IInventaryRepository extends JpaRepository<Inventory, Integer> {

}