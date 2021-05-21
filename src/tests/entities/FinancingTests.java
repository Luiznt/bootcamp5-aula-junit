package tests.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import entities.Financing;



public class FinancingTests {
	
	public static Double ENTRY_FEE_PERCENTAGE = 0.2;

	@Test
	public void FinancialShoudReturnFinancialObjectOklWhenQuoteLessThenHalfIncome() {
		Double totalAmount = 10000.0;
		Double income = 2000.0;
		Integer months = 12;

		Financing fin = new Financing(totalAmount, income, months);
		
		Assertions.assertEquals(totalAmount, fin.getTotalAmount());
		Assertions.assertEquals(income, fin.getIncome());
		Assertions.assertEquals(months, fin.getMonths());

	}
		
	@Test
	public void FinancialShouldThrowExceptionWhenQuotaGreaterThenHalfIncome() {
				
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Financing fin = new Financing(15001.0, 2000.0, 12);
		});
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Financing fin = new Financing(10000.0, 1300.0, 12);
		});
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Financing fin = new Financing(10000.0, 2000.0, 7);
		});
	
	}
	

	@Test
	public void quotaShouldBeCalculatedWhenLessOrEqualThenHalfIncome() {

		Double totalAmount = 10000.0;
		Double income = 2000.0;
		Integer months = 12;

		Financing fin = new Financing(totalAmount, income, months);
		Double expectedQuota = totalAmount * (1 - ENTRY_FEE_PERCENTAGE) / months;

		Assertions.assertEquals(expectedQuota, fin.quota());

	}

	@Test
	public void entryShouldBe20PercentOfTotalAmount() {

		Double totalAmount = 10000.0;
		Double income = 2000.0;
		Integer months = 12;

		Financing fin = new Financing(totalAmount, income, months);
		Double expectedEntry = totalAmount * ENTRY_FEE_PERCENTAGE;

		Assertions.assertEquals(expectedEntry, fin.entry());

	}
	
	@Test
	public void setterShouldLoadPropertiesWhenQuotaIsLessOrEqualTheHalfIncome() {		
		
		Double totalAmount = 10000.0;
		Double income = 2000.0;
		Integer months = 12;
		Financing fin = new Financing(totalAmount, income, months);
		
		fin.setTotalAmount(totalAmount-1);
		Assertions.assertEquals(totalAmount-1, fin.getTotalAmount());
		
		fin.setTotalAmount(totalAmount);
		fin.setIncome(income+1);
		Assertions.assertEquals(income+1, fin.getIncome());
		
		fin.setIncome(income);
		fin.setMonths(months+1);
		Assertions.assertEquals(months+1, fin.getMonths());
		
	}
	

	@Test
	public void setTotalAmountShouldThrowExceptionWhenIsZeroOrLess() {
		Double totalAmount = 10000.0;
		Double income = 2000.0;
		Integer months = 12;
		Financing fin = new Financing(totalAmount, income, months);
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			fin.setTotalAmount(0.0);
		});

		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			fin.setTotalAmount(-1.0);
		});
		
	}
	
	
	@Test
	public void setTotalAmountShouldThrowExceptionWhenQuotaGreaterThenHalfIncome() {
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Double totalAmount = 10000.0;
			Double income = 2000.0;
			Integer months = 12;

			Financing fin = new Financing(totalAmount, income, months);			
			// calculates the maximum totalAmout for actual income and months
			totalAmount = ((income / 2) * months) / (1 - ENTRY_FEE_PERCENTAGE);
			++totalAmount;
			fin.setTotalAmount(totalAmount);
		});

	}
	

	@Test
	public void setIncomeShouldThrowExceptionWhenIsZeroOrLess() {
		Double totalAmount = 10000.0;
		Double income = 2000.0;
		Integer months = 12;
		Financing fin = new Financing(totalAmount, income, months);
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			fin.setIncome(0.0);
		});

		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			fin.setIncome(-1.0);
		});
		
	}

	
	@Test
	public void setIncomeShouldThrowExceptionWhenQuotaGreaterThenHalfIncome() {

		Assertions.assertThrows(IllegalArgumentException.class, () -> {

			Double totalAmount = 10000.0;
			Double income = 2000.0;
			Integer months = 12;

			Financing fin = new Financing(totalAmount, income, months);
			// calculates the minimum income for actual totalAmount and months
			income = fin.quota() * 2;
			--income;
			fin.setIncome(income);
		});

	}

	@Test
	public void setMonthShouldThrowExceptionWhenMonthsIsZeroOrLess() {
		Double totalAmount = 10000.0;
		Double income = 2000.0;
		Integer months = 12;

		Financing fin = new Financing(totalAmount, income, months);

		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			fin.setMonths(0);
		});

		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			fin.setMonths(-1);
		});

	}

	@Test
	public void setMonthsShouldThrowExceptionWhenQuotaGreaterThenHalfIncome() {

		Assertions.assertThrows(IllegalArgumentException.class, () -> {

			Double totalAmount = 10000.0;
			Double income = 2000.0;
			Integer months = 12;

			Financing fin = new Financing(totalAmount, income, months);
			// calculates the minimum months for actual totalAmount and income
			Double minimunMonths = totalAmount * (1-ENTRY_FEE_PERCENTAGE) / income * 2;
			months = minimunMonths.intValue();
			--months;
			fin.setMonths(months);	
		});

	}

	
	
}
