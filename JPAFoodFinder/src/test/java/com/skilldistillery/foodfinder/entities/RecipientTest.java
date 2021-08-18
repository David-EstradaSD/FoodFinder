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
import org.junit.jupiter.api.Test;

class RecipientTest {

	private static EntityManagerFactory emf;
	private EntityManager em; 
	private Recipient recipient;
	
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
		recipient = em.find(Recipient.class, 1); 
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		recipient = null; 
	}

	@Test
	void test_mapping_recipient() {
		assertNotNull(recipient);
		assertEquals(1, recipient.getId()); 
	}
	
	@Test
	void test_recipient_joins_mapping() {
		assertNotNull(recipient);
		assertEquals("Mary", recipient.getUser().getFirstName()); 
		assertEquals("CO", recipient.getAddress().getState()); 
		assertTrue(! recipient.getComments().isEmpty()); 
		assertTrue(! recipient.getServiceLocations().isEmpty()); 
		assertTrue(! recipient.getRatings().isEmpty()); 
	}

}
