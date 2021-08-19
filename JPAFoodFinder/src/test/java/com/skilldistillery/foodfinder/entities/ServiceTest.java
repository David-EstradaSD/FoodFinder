package com.skilldistillery.foodfinder.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ServiceTest {

	private static EntityManagerFactory emf;
	private EntityManager em; 
	private Service service;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("JPAFoodFinder");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager(); 
		service = em.find(Service.class, 1); 
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		service = null; 
	}

	@Test
	@DisplayName("test service entity")
	void test() {
		assertNotNull(service);
		assertEquals("hot", service.getDescription()); 
	}
	
	@Test
	@DisplayName("test many to many mapping to service location")
	void test2() {
		assertNotNull(service);
		assertNotNull(service.getServiceLocations().size());
		assertTrue(!service.getServiceLocations().isEmpty());
	}

}
