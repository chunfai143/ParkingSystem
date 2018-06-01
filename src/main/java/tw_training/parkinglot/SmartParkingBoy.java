package tw_training.parkinglot;

import java.util.List;

public class SmartParkingBoy extends ParkingBoy {

	public SmartParkingBoy(List<ParkingLot> parkingLotList) {
		super(parkingLotList);
		this.rank = 2;
		this.id = this.hashCode();
	}

	@Override
	protected void prioritizeParkingLot() {
		parkingLotList.sort((parkingLot1, parkingLot2) -> 
				parkingLot2.getAvailableSpace() - parkingLot1.getAvailableSpace());
	}

	@Override
	protected ParkingBoy getIssuer() {
		return this;
	}

}
