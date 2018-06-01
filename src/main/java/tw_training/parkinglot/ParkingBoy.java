package tw_training.parkinglot;

import java.util.List;

public abstract class ParkingBoy {

	protected List<ParkingLot> parkingLotList;
	protected int rank = 0;
	protected int id;

	public ParkingBoy(List<ParkingLot> parkingLotList) {
		this.parkingLotList = parkingLotList;
		this.id = this.hashCode();
	}

	public Receipt park(Car car) {
		prioritizeParkingLot();
		if (parkingLotList.get(0).isFull()) {
			return null;
		}
		parkingLotList.get(0).parkCar(car);
		return issueReceipt(car.getCarNumber(), getIssuer());
	}

	protected abstract ParkingBoy getIssuer();

	public Car pick(String carNumber) {
		for (ParkingLot parkingLot : parkingLotList) {
			Car car = parkingLot.pickCar(carNumber);
			if (car != null) {
				return car;
			}
		}
		return null;
	}
	
	public int getRank() {
		return rank;
	}

	protected abstract void prioritizeParkingLot();
	
	public int getId() {
		return this.id;
	}
	
	protected Receipt issueReceipt(String carNumber, ParkingBoy issuer) {
		System.out.println(issuer.getClass().getSimpleName());
		return new Receipt(carNumber, issuer.getId());
	}

}
