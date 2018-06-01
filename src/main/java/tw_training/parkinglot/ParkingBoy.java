package tw_training.parkinglot;

import java.util.List;

public abstract class ParkingBoy {
	
	protected List<ParkingLot> parkingLotList;
	
	public ParkingBoy(List<ParkingLot> parkingLotList) {
		this.parkingLotList = parkingLotList;
	}

	public abstract boolean park(Car car);

	public abstract Car pick(String carNumber);

}
