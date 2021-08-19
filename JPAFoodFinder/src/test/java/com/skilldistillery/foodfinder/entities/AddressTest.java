package com.skilldistillery.foodfinder.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AddressTest {

	private static EntityManagerFactory emf;
	private EntityManager em; 
	private Address address;
	
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
		address = em.find(Address.class, 1); 
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		address = null; 
	}

	@Test
	void test_address_class_fields() {
		assertNotNull(address);
		assertEquals("100 Baker Street", address.getStreetAddress()); 
		assertEquals("Denver", address.getCity()); 
		assertEquals("CO", address.getState()); 
		assertEquals("12345", address.getZip()); 		
	}
	
//	@Test
//	void test_address_joins_mapping() {
//		assertNotNull(address);
//		assertEquals("Denver", address.getCity()); 
//	}

}
