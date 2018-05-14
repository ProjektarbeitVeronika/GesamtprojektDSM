package testJUnitTests;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import jUnitTests.SuperTest;

public class SuperTestTest {
	
	private SuperTest classUnderTest;
	
	@Before
	public void setUp() throws Exception {
		classUnderTest = new SuperTest();
		
	}

	@Test
	public void testCalculate() {
		double initialValue = 1.1;
		
		BigDecimal expectedValue = BigDecimal.valueOf(1.1*100 / Math.PI);
	
		assertTrue(expectedValue.equals(classUnderTest.calculate(initialValue)));
	}
}
