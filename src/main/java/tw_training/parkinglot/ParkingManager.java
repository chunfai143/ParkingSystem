package tw_training.parkinglot;

import java.util.ArrayList;
import java.util.List;

public class ParkingManager extends SuperParkingBoy {
	
	private List<ParkingBoy> parkingBoyList;

	public ParkingManager(List<ParkingLot> parkingLotList, List<ParkingBoy> parkingBoyList) {
		super(parkingLotList);
		this.parkingBoyList = new ArrayList<>();
		this.parkingBoyList.addAll(parkingBoyList);
	}
	
	@Override
	public Receipt park(Car car) {
		prioritizeParkingBoy();
		if(!parkingBoyList.isEmpty()) {			
			parkingBoyList.get(0).park(car);
		}
		return super.park(car);
	}

	private void prioritizeParkingBoy() {
		parkingBoyList.sort((parkingBoy1, parkingBoy2) -> 
			parkingBoy2.getRank() - parkingBoy1.getRank());
	}

}
