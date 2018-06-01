package tw_training.parkinglot;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;

public class ParkingLot {

	List<Car> carList = new ArrayList<>();
	int capacity;

	public ParkingLot(int capacity) {
		this.capacity = capacity;
	}

	public void parkCar(Car car) {
		this.carList.add(car);
	}

	public Car pickCar(String carNumber) {		
		OptionalInt targetCarIndex = IntStream.range(0,  carList.size())
				.filter(index -> carNumber.equals(carList.get(index).getCarNumber()))
				.findFirst();
		
		if (!targetCarIndex.isPresent()) {
			return null;
		}
		
		return carList.remove(targetCarIndex.getAsInt());
	}

	public boolean isFull() {
		return carList.size() == capacity;
	}

	public int getAvailableSpace() {
		return capacity - carList.size();
	}

}
