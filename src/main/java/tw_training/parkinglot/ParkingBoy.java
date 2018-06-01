package tw_training.parkinglot;

import java.util.List;
import java.util.OptionalInt;

public abstract class ParkingBoy {

	protected List<ParkingLot> parkingLotList;
	protected int rank = 0;
	protected int id;

	public ParkingBoy(List<ParkingLot> parkingLotList) {
		this.parkingLotList = parkingLotList;
		this.id = this.hashCode();
	}

	public Receipt park(Car car) {
		if (!hasSlot()) {
			return null;
		}
		parkingLotList.get(0).parkCar(car);
		return issueReceipt(car.getCarNumber());
	}
	
	public boolean hasSlot() {
		prioritizeParkingLot();
		return !parkingLotList.get(0).isFull();	
	}

	public Car pick(String carNumber) {
		for (ParkingLot parkingLot : parkingLotList) {
			OptionalInt targetCarIndex = parkingLot.getCarIndex(carNumber);
			
			if(targetCarIndex.isPresent()) {
				return parkingLot.getCarList().remove(targetCarIndex.getAsInt());
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
	
	public List<ParkingLot> getParkingLotList() {
		return parkingLotList;
	}

	protected Receipt issueReceipt(String carNumber) {
		return new Receipt(carNumber, this.id);
	}

}
