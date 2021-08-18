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

class ServiceLocationTest {

	private static EntityManagerFactory emf;
	private EntityManager em; 
	private ServiceLocation serviceLocation;
	
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
		serviceLocation = em.find(ServiceLocation.class, 1); 
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		serviceLocation = null; 
	}

	@Test
	@DisplayName("test entity mapping")
	void test1() {
		assertNotNull(serviceLocation);
		assertEquals("Denver", serviceLocation.getLocationName()); 
	}
	
	@Test
	@DisplayName("test one to many mapping to rating")
	void test2() {
		assertNotNull(serviceLocation);
		assertTrue(!serviceLocation.getRatings().isEmpty());
	}
	
	@Test
	@DisplayName("test one to one mapping to address")
	void test3() {
		assertNotNull(serviceLocation);
		assertEquals("100 Baker Street", serviceLocation.getAddress().getStreet_address());
	}
	
	@Test
	@DisplayName("test many to many mapping to service")
	void test4() {
		assertNotNull(serviceLocation);
		assertNotNull(serviceLocation.getServices().size());
		assertTrue(!serviceLocation.getServices().isEmpty());
	}
	
	@Test
	@DisplayName("test many to many mapping to donor")
	void test5() {
		assertNotNull(serviceLocation);
		assertNotNull(serviceLocation.getDonors().size());
		assertTrue(!serviceLocation.getDonors().isEmpty());
	}
	
	@Test
	@DisplayName("test many to many mapping to recipient")
	void test6() {
		assertNotNull(serviceLocation);
		assertNotNull(serviceLocation.getRecipients().size());
		assertTrue(!serviceLocation.getRecipients().isEmpty());
	}

}
