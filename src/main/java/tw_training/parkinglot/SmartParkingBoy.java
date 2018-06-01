package tw_training.parkinglot;

import java.util.Comparator;
import java.util.List;

public class SmartParkingBoy extends ParkingBoy {

	public SmartParkingBoy(List<ParkingLot> parkingLotList) {
		super(parkingLotList);
	}

	@Override
	public boolean park(Car car) {
		prioritizeParkingLot();
		if(parkingLotList.get(0).isFull()) {
			return false;
		}
		parkingLotList.get(0).parkCar(car);
		return true;
	}
	
	private void prioritizeParkingLot() {
		parkingLotList.sort(new Comparator<ParkingLot>() {
			@Override
			public int compare(ParkingLot parkingLot1, ParkingLot parkingLot2) {
				return parkingLot2.getAvailableSpace() - parkingLot1.getAvailableSpace();
			}
		});
	}

}
