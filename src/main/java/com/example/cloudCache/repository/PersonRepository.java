package com.example.cloudCache.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.cloudCache.entity.PersonEntity;

public interface PersonRepository extends CrudRepository<PersonEntity, Long>{

}
