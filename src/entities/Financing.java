package entities;

public class Financing {
	/*
	 * entry = 20% totalAmount. 
	 * quota = 80% totalAmount / months. 
	 * quota <= income / 2
	 */

	public static Double ENTRY_FEE_PERCENTAGE = 0.2;
	private Double totalAmount;
	private Double income;
	private Integer months;
	
	public Financing() {
	}

	public Financing(Double totalAmount, Double income, Integer months) {
		
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

	
/*  Versão anterior
 * 
	public Financing() {
	}

	public Financing(Double totalAmount, Double income, Integer months) {
		if (totalAmount <= 0.0) {
			throw new IllegalArgumentException("totalAmount must be greater than zero");
		}
		if (income <= 0.0) {
			throw new IllegalArgumentException("income must be greater than zero");
		}
		
		if (months <= 0) {
			throw new IllegalArgumentException("months must be greater than zero");
		}
		
		this.totalAmount  = totalAmount;
		this.income= income;
		this.months = months;
		if (errorQuota()) {
			this.totalAmount=0.0; this.income=0.0; this.months=0;
			throw new IllegalArgumentException("quota can not be greatter than half income");
		}	
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		if (totalAmount <= 0.0 ) {
			throw new IllegalArgumentException("totalAmount mustbe greater than zero");
		}
		Double aux =  this.totalAmount;
		this.totalAmount = totalAmount;
		if (errorQuota()) {
			this.totalAmount = aux;
			throw new IllegalArgumentException("totalAmount is high: quota can not be greatter than half income");
		}
		//this.totalAmount = totalAmount;
	}

	public Double getIncome() {
		return income;
	}

	public void setIncome(Double income) {
		if (income <= 0.0 ) {
			throw new IllegalArgumentException("income mustbe greater than zero");
		}
		Double aux = this.income;
		this.income = income;
		if(errorQuota()) {
			this.income = aux;
			throw new IllegalArgumentException("income is low: quota can not be greatter than half income");
		}		
		//this.income = income;
	}

	public Integer getMonths() {
		return months;
	}

	public void setMonths(Integer months) {
		if (months <= 0 ) {
			throw new IllegalArgumentException("months mustbe greater than zero");
		}
		Integer aux = this.months;
		this.months = months;
		if(errorQuota()) {
			this.months = aux;
			throw new IllegalArgumentException("months is low: quota can not be greatter than half income");
		}			
		
		//this.months = months;
	}

	public Double entry() {
		return totalAmount * ENTRY_FEE_PERCENTAGE;
	}

	public Double quota() {
		if (months <= 0) {
			throw new IllegalArgumentException("months must be greater than zero");
		}
		return totalAmount * (1 - ENTRY_FEE_PERCENTAGE) / months;
	}
	

	private Boolean errorQuota() {
		
		return (quota() > income/2);
		
	}
*/
	
}
