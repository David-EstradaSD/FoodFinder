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

class UserTest {

	private static EntityManagerFactory emf;
	private EntityManager em; 
	private User user;
	
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
		user = em.find(User.class, 1); 
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		user = null; 
	}

	@Test
	@DisplayName("test user entity mapping")
	void test1() {
		assertNotNull(user);
		assertEquals("username", user.getUsername()); 
	}
	
	@Test
	@DisplayName("test one to many mapping to service location")
	void test2() {
		assertNotNull(user);
		assertNotNull(user.getServiceLocation().size()); 
		assertTrue(!user.getServiceLocation().isEmpty());
	}
	
	@Test
	@DisplayName("test one to many mapping to recipients")
	void test3() {
		assertNotNull(user);
		assertNotNull(user.getRecipients().size()); 
		assertTrue(!user.getRecipients().isEmpty());
	}
	
	@Test
	@DisplayName("test one to many mapping to donors")
	void test4() {
		assertNotNull(user);
		assertNotNull(user.getDonors().size()); 
		assertTrue(!user.getDonors().isEmpty());
	}

}
