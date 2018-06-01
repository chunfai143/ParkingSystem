package tw_training.parkinglot;

public class Receipt {
	
	private String carNumber;
	private int issuerId;
	
	public Receipt(String carNumber, int issueId) {
		this.carNumber = carNumber;
		this.issuerId = issueId;
	}

	public String getCarNumber() {
		return carNumber;
	}

	public int getIssuerId() {
		return issuerId;
	}
}
