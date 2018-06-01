package tw_training.parkinglot;

import java.util.List;

public class NormalParkingBoy extends ParkingBoy {

	public NormalParkingBoy(List<ParkingLot> parkingLotList) {
		super(parkingLotList);
	}

	@Override
	public boolean park(Car car) {
		for(ParkingLot parkingLot : parkingLotList) {
			if (!parkingLot.isFull()) {
				parkingLot.parkCar(car);
				return true;
			}
		}
		return false;
	}
	
	@Override
	public Car pick(String carNumber) {
		for(ParkingLot parkingLot : parkingLotList) {
			Car car = parkingLot.pickCar(carNumber);
			if(car != null)
				return car;
			
		}
		return null;
	}
}
