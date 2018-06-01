package tw_training.parkinglot;

import java.util.List;

public class SmartParkingBoy extends ParkingBoy {
	
	List<ParkingLot> parkingLotList;

	public SmartParkingBoy(List<ParkingLot> parkingLotList) {
		super(parkingLotList);
	}

	@Override
	public boolean park(Car car) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Car pick(String carNumber) {
		// TODO Auto-generated method stub
		return null;
	}

}
