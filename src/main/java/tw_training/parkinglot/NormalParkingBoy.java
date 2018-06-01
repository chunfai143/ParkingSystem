package tw_training.parkinglot;

import java.util.List;

public class NormalParkingBoy extends ParkingBoy {

	public NormalParkingBoy(List<ParkingLot> parkingLotList) {
		super(parkingLotList);
		this.rank = 1;
		this.id = this.hashCode();
	}

	@Override
	public Receipt park(Car car) {
		for(ParkingLot parkingLot : parkingLotList) {
			if (!parkingLot.isFull()) {
				parkingLot.parkCar(car);
				return issueReceipt(car.getCarNumber());
			}
		}
		return null;
	}

	@Override
	protected void prioritizeParkingLot() {
		return;	
	}

}
