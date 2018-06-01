package tw_training.parkinglot;

import java.util.List;

public class SuperParkingBoy extends ParkingBoy {

	public SuperParkingBoy(List<ParkingLot> parkingLotList) {
		super(parkingLotList);
	}

	@Override
	protected void prioritizeParkingLot() {
		parkingLotList.sort(SuperParkingBoy::sortByAvailableRateDesc);
	}
	
	private static int sortByAvailableRateDesc(ParkingLot parkingLotA, ParkingLot parkingLotB) {
		double result = parkingLotB.getAvailableRate() - parkingLotA.getAvailableRate();
		return result < 0 ? -1 : result == 0 ? 0 : 1; 
	}

}
