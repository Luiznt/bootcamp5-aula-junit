package entities;

public class Financing2 {
	/*
	 * entry = 20% totalAmount. 
	 * quota = 80% totalAmount / months. 
	 * quota <= income / 2
	 */

	public static Double ENTRY_FEE_PERCENTAGE = 0.2;
	private Double totalAmount;
	private Double income;
	private Integer months;

	public Financing2() {
	}

	public Financing2(Double totalAmount, Double income, Integer months) {
		
		if (validProperties(totalAmount, income, months)) {
			this.totalAmount  = totalAmount;
			this.income= income;
			this.months = months;
		}
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		
		validProperties(totalAmount , this.income, this.months);
		this.totalAmount = totalAmount;
	}

	public Double getIncome() {
		return income;
	}

	public void setIncome(Double income) {
		validProperties(this.totalAmount , income, this.months);
		this.income = income;
	}

	public Integer getMonths() {
		return months;
	}

	public void setMonths(Integer months) {
		validProperties(this.totalAmount , this.income, months);
		this.months = months;
	}

	public Double entry() {
		return totalAmount * ENTRY_FEE_PERCENTAGE;
	}

	public Double quota() {
		//validProperties(totalAmount , income, months);
		return totalAmount * (1 - ENTRY_FEE_PERCENTAGE) / months;
	}
	
	private Boolean validProperties(Double totalAmount, Double income, Integer months) {
		
		if (totalAmount <= 0.0) {
			throw new IllegalArgumentException("totalAmount must be greater than zero");
		}
		if (income <= 0.0) {
			throw new IllegalArgumentException("income must be greater than zero");
		}
		
		if (months <= 0) {
			throw new IllegalArgumentException("months must be greater than zero");
		}
		
		Double quota = totalAmount * (1 - ENTRY_FEE_PERCENTAGE) / months;
		if (quota > income/2) {
			throw new IllegalArgumentException("quota can not be greatter than half income");
		}
		
		return true;
	}

}
