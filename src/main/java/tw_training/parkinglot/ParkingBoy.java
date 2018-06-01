package tw_training.parkinglot;

import java.util.List;

public abstract class ParkingBoy {

	protected List<ParkingLot> parkingLotList;

	public ParkingBoy(List<ParkingLot> parkingLotList) {
		this.parkingLotList = parkingLotList;
	}

	public boolean park(Car car) {
		prioritizeParkingLot();
		if (parkingLotList.get(0).isFull()) {
			return false;
		}
		parkingLotList.get(0).parkCar(car);
		return true;
	}

	public Car pick(String carNumber) {
		for (ParkingLot parkingLot : parkingLotList) {
			Car car = parkingLot.pickCar(carNumber);
			if (car != null) {
				return car;
			}
		}
		return null;
	}
	
	protected abstract void prioritizeParkingLot();

}
