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

class DonorTest {

	private static EntityManagerFactory emf;
	private EntityManager em; 
	private Donor donor;
	
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
		donor = em.find(Donor.class, 1); 
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		donor = null; 
	}

	@Test
	void test_donor() {
		assertNotNull(donor);
		assertEquals("food", donor.getCategory()); 
	}

	@Test
	void test_donor_joins() {
		assertNotNull(donor);
		assertEquals("Mary", donor.getUser().getFirstName()); 
		assertEquals("100 Baker Street", donor.getAddress().getStreetAddress()); 
		assertEquals(1, donor.getServiceLocations().size()); 
	}

}
