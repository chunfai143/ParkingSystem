package tw_training.parkinglot;

import java.util.List;

public class SmartParkingBoy extends ParkingBoy {

	public SmartParkingBoy(List<ParkingLot> parkingLotList) {
		super(parkingLotList);
	}

	@Override
	public boolean park(Car car) {
		prioritizeParkingLot();
		if (parkingLotList.get(0).isFull()) {
			return false;
		}
		parkingLotList.get(0).parkCar(car);
		return true;
	}

	@Override
	protected void prioritizeParkingLot() {
		parkingLotList.sort((parkingLot1, parkingLot2) -> 
				parkingLot2.getAvailableSpace() - parkingLot1.getAvailableSpace());
	}

}
