package tw_training.parkinglot;

import java.util.List;

public class ParkingManager extends SuperParkingBoy {
	
	private List<ParkingBoy> parkingBoyList;

	public ParkingManager(List<ParkingLot> parkingLotList, List<ParkingBoy> parkingBoyList) {
		super(parkingLotList);
		this.parkingBoyList = parkingBoyList;
		this.id = this.hashCode();
	}
	
	@Override
	public Receipt park(Car car) {
		prioritizeParkingBoy();
		ParkingBoy issuer = null;
		if (!parkingBoyList.isEmpty()) {
			issuer = parkingBoyList.get(0);
		}
		if (issuer == null) {
			return super.park(car);
		}
		return issuer.park(car);
	}

	private void prioritizeParkingBoy() {
		parkingBoyList.sort((parkingBoy1, parkingBoy2) -> 
			parkingBoy2.getRank() - parkingBoy1.getRank());
	}

}
