package tests.factory;

import entities.Financing;

public class FinancingFactory {
		
	
	public static Financing createFinancingGood() {
		Double totalAmount = 10000.0;
		Double income = 2000.0;
		Integer months = 12;

		Financing fin = new Financing(totalAmount, income, months);

		return fin;
	}

}
