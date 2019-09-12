package com.UnitTesting1;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.dao.UserTransactionDetailsDAO;
import com.logic.UserTransactionDetailsDAOImpl;

import jdk.internal.dynalink.beans.StaticClass;

public class TestUserTransactionDetailsDAOImpl {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetRandomNumberInRange() {
		UserTransactionDetailsDAOImpl dao = new UserTransactionDetailsDAOImpl();
		double number = dao.getRandomDoubleBetweenRange(20, 50);
		assertTrue((number > 20)&&(number < 50));
	}

	@Test
	public void testTransactionIdGenerator() {
		UserTransactionDetailsDAOImpl dao = new UserTransactionDetailsDAOImpl();
	}

	@Test
	public void testCounterpartyAccountNumberGenerator() {
	}

	@Test
	public void testGetRandomDoubleBetweenRange() {
	}

	@Test
	public void testAddTransaction() {
	}

	@Test
	public void testGetTransactionbyAccount() {
	}

	@Test
	public void testRandomGenerateCashflow() {
	}

	@Test
	public void testDeleteTransaction() {
	}

	@Test
	public void testGetCashFlows() {
	}
}
