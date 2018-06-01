package tw_training.parkinglot;

public class ParkingBoy {
	
	ParkingLot parkingLot;
	
	public ParkingBoy(ParkingLot parkingLot) {
		this.parkingLot = parkingLot;
	}

	public boolean park(Car car) {
		if (parkingLot.isFull()) {
			return false;
		}
		parkingLot.parkCar(car);
		return true;
	}

	public Car pick(String carNumber) {
		Car car = parkingLot.pickCar(carNumber);	
		return car;
	}

}
