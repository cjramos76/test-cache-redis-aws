package com.example.cloudCache.service;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.cloudCache.entity.PersonEntity;
import com.example.cloudCache.repository.PersonRepository;
import com.google.common.collect.Lists;

import ch.qos.logback.classic.Logger;


@Service
@CacheConfig(cacheNames="people")
public class PersonService {

	@Autowired
	PersonRepository personRepository;
	
	Logger logger = (Logger)  LoggerFactory.getLogger(PersonService.class);
	
	@Cacheable(key="#id")
	public PersonEntity findById(Long id) {
		logger.info("Sem cache findById");
		return this.personRepository.findById(id).orElse(null);
	}
	
	@Cacheable
	public List<PersonEntity> findAll() {
		logger.info("Sem cache findAll");
		return Lists.newArrayList(this.personRepository.findAll());
	}
	
	@CachePut(key="#newPerson.getId()")
	public PersonEntity createPerson(PersonEntity newPerson) {
		logger.info("create person");
		this.personRepository.save(newPerson);
		return newPerson;
	}
	
	@CachePut(key="#person.getId()")
	public PersonEntity  updatePerson(PersonEntity person) {
		logger.info("update person");
		PersonEntity oldPerson = this.personRepository.findById(person.getId()).orElseThrow(() -> new RuntimeException("Person Not Found"));
		oldPerson = person;
		this.personRepository.save(oldPerson);
		return oldPerson;
	}
	
	@CacheEvict(key="#id")
	public void deletePersonById(Long id) {
		logger.info("delete person");
		this.personRepository.deleteById(id);
	}
	
}
