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
	
	public OptionalInt getCarIndex(String carNumber) {		
		return IntStream.range(0,  carList.size())
				.filter(index -> carNumber.equals(carList.get(index).getCarNumber()))
				.findFirst();
	}
	
	public boolean containsCar(String carNumber) {
		return getCarIndex(carNumber).isPresent();
	}
	
	public List<Car> getCarList() {
		return carList;
	}

	public boolean isFull() {
		return carList.size() == capacity;
	}

	public int getAvailableSpace() {
		return capacity - carList.size();
	}
	
	public double getAvailableRate() {
		if (capacity == 0) {
			return 0.0d;
		}
		return (getAvailableSpace() / capacity) * 100.0d;
	}

}
