package jUnitTests;

import java.math.BigDecimal;


public class SuperTest {
	public BigDecimal calculate(double operand){
		BigDecimal ret = null;
		
		double calculatedValue = operand * 100 / Math.PI;
		ret = BigDecimal.valueOf(calculatedValue);
		return ret;
	}

}

