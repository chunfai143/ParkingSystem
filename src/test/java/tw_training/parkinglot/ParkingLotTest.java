package tw_training.parkinglot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class ParkingLotTest {

	/**
	 * Given I have a car
	 * When I park car
	 * Then car is parked
	 */
	@Test
	public void givenACar_whenParkCar_thenCarIsParked() {
		Car car = new Car("carA");		
		ParkingLot parkingLot = new ParkingLot(1);
		ParkingBoy parkingBoy = new NormalParkingBoy(Arrays.asList(parkingLot));
		assertNotNull(parkingBoy.park(car));
	}
	
	/**
	 * Given I have a car and there is one parking lot
	 * When I park car
	 * Then parking lot is full
	 */
	@Test
	public void givenACarAndAParkingLot_whenParkCar_ThenParkingLotIsFull() {
		Car car = new Car("carA");
		
		ParkingLot parkingLot = new ParkingLot(1);
		ParkingBoy parkingBoy = new NormalParkingBoy(Arrays.asList(parkingLot));
		parkingBoy.park(car);
		assertTrue(parkingLot.isFull());
	}
	
	/**
	 * Given I have a car and there is one parking lot
	 * When I park car and parking lot is full
	 * Then car is not parked
	 */
	@Test
	public void givenACarAndAParkingLot_whenParkCarAndParkingLotIsFull_thenCarIsNotParked() {
		Car car = new Car("carA");
		ParkingLot parkingLot = new ParkingLot(1);
		ParkingBoy parkingBoy = new NormalParkingBoy(Arrays.asList(parkingLot));
		parkingBoy.park(car);
		assertNull(parkingBoy.park(car));
	}
		
	/**
     * Given I have a parking lot and a car
     * When I park the car to the parking lot
     * Then I will be able to pick the car from the parking lot
     */
	@Test
	public void givenParkingLotAndACar_whenParkCarToParkingLot_thenCanPickTheCarFromParkingLot() {
		Car car = new Car("carA");
		
		ParkingLot parkingLot = new ParkingLot(1);
		
		ParkingBoy parkingBoy = new NormalParkingBoy(Arrays.asList(parkingLot));
		
		parkingBoy.park(car);
		
		assertEquals(car, parkingBoy.pick("carA"));
	}

    /**
     * Given I pick a car parked in the parking lot
     * When I try to pick the same car form the parking lot 
     * Then I will fail to pick the car
     */
	@Test
	public void givenCarAlreadyPickedFromParkingLot_whenPickCarAgain_thenCannotPickCar() {
		Car car = new Car("carA");
		ParkingLot parkingLot = new ParkingLot(1);		
		ParkingBoy parkingBoy = new NormalParkingBoy(Arrays.asList(parkingLot));
		
		parkingBoy.park(car);
		parkingBoy.pick("carA");
		
		assertNull(parkingBoy.pick("carA"));
	}

    /**
     * Given I have a parking lot
     * When I try to pick a not-parked car form the parking lot
     * Then I will fail to pick the car
     */
	@Test
	public void givenParkingLotWithoutCar_whenPickCar_thenCannotPickCar() {
		ParkingLot parkingLot = new ParkingLot(1);		
		ParkingBoy parkingBoy = new NormalParkingBoy(Arrays.asList(parkingLot));
		
		assertNull(parkingBoy.pick("carA"));
	}

	/**
	 * Given Parking Lot has 2 Space
	 * When I park two car to parking lot
	 * Then I can pick cars from the parking lot
	 */
	@Test
	public void givenParkingLotWithTwoSpace_whenParkTwoCars_thenCanPickCars() {
		Car car1 = new Car("carA");
		Car car2 = new Car("carB");
		ParkingLot parkingLot = new ParkingLot(2);		
		ParkingBoy parkingBoy = new NormalParkingBoy(Arrays.asList(parkingLot));
		
		parkingBoy.park(car1);
		parkingBoy.park(car2);
		
		assertEquals(car1, parkingBoy.pick("carA"));
		assertEquals(car2, parkingBoy.pick("carB"));
	}
	
	/**
	 * Given Parking Lot A has 3 available slots and Parking Lot B has 5 available slots
	 * When A Smart Parking Boy park car
	 * Then Car will be parked in Parking Lot B
	 */
	@Test
	public void givenParkingLotAWith3SpaceAndParkingLotBWith5Space_whenSmartParkingBoyPark_thenCarIsParkedInLotB() {
		Car car1 = new Car("carA");
		ParkingLot parkingLotA = new ParkingLot(3);
		ParkingLot parkingLotB = new ParkingLot(5);
		List<ParkingLot> parkingLotList = Arrays.asList(parkingLotA, parkingLotB);
		ParkingBoy parkingBoy = new SmartParkingBoy(parkingLotList);
		
		parkingBoy.park(car1);
		
		assertEquals(car1, parkingLotB.pickCar("carA"));
	}
	
	/**
	 * Given Parking Lot A B Both have 0 available slot
	 * When A Smart Parking Boy park car
	 * Then Cannot Park Car
	 */
	@Test
	public void givenAllParkingSlotsHaveNoSpace_whenSmartParkingBoyPark_thenCannotParkCar() {
		Car car1 = new Car("carA");
		Car car2 = new Car("carB");
		Car car3 = new Car("carC");
		ParkingLot parkingLotA = new ParkingLot(1);
		ParkingLot parkingLotB = new ParkingLot(1);
		List<ParkingLot> parkingLotList = Arrays.asList(parkingLotA, parkingLotB);
		ParkingBoy parkingBoy = new SmartParkingBoy(parkingLotList);
		
		parkingBoy.park(car1);
		parkingBoy.park(car2);
		
		assertNull(parkingBoy.park(car3));
	}
	
	/**
	 * Given Parking Lot A B Both have same available slot
	 * When A Smart Parking Boy park car
	 * Then Car is Parked in Parking Lot A
	 */
	@Test
	public void givenAllParkingSlotsHaveSameSpace_whenSmartParkingBoyPark_thenParkInFirstParkingLot() {
		Car car1 = new Car("carA");
		ParkingLot parkingLotA = new ParkingLot(1);
		ParkingLot parkingLotB = new ParkingLot(1);
		List<ParkingLot> parkingLotList = Arrays.asList(parkingLotA, parkingLotB);
		ParkingBoy parkingBoy = new SmartParkingBoy(parkingLotList);
		
		parkingBoy.park(car1);
		
		assertEquals(car1, parkingLotA.pickCar("carA"));
	}
	
	/**
	 * Given Parking Lot A has 0 available slot and Parking Lot B has 1 available slot
	 * When A Smart Parking Boy park car
	 * Then Car is Parked in Parking Lot B
	 */
	@Test
	public void givenParkingLotAHas0SlotAndParkingBHas1Slot_whenSmartParkingBoyPark_thenParkInParkingLotB() {
		Car car1 = new Car("carA");
		Car car2 = new Car("carB");
		ParkingLot parkingLotA = new ParkingLot(1);
		ParkingLot parkingLotB = new ParkingLot(1);
		List<ParkingLot> parkingLotList = Arrays.asList(parkingLotA, parkingLotB);
		ParkingBoy parkingBoy = new SmartParkingBoy(parkingLotList);
		
		parkingBoy.park(car1);
		parkingBoy.park(car2);
		
		assertEquals(car2, parkingLotB.pickCar("carB"));
	}
	
	/**
	 * Given Parking Lot A has 3 Capacity and 1 Available Space
	 * And Parking Lot B has 2 Capacity and 1 Available Space
	 * When A Super Parking Boy park car
	 * Then Car is Parked in Parking Lot B
	 */
	@Test
	public void givenParkingLotBHasHigherAvailableRate_whenSuperParkingBoyPark_thenParkInParkingLotB() {
		Car car1 = new Car("carA");
		Car car2 = new Car("carB");
		Car car3 = new Car("carC");
		Car car4 = new Car("carD");
		ParkingLot parkingLotA = new ParkingLot(3);
		ParkingLot parkingLotB = new ParkingLot(2);
		List<ParkingLot> parkingLotList = Arrays.asList(parkingLotA, parkingLotB);
		ParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLotList);
		ParkingBoy superParkingBoy = new SuperParkingBoy(parkingLotList);
		
		smartParkingBoy.park(car1);
		smartParkingBoy.park(car2);
		smartParkingBoy.park(car3);
		superParkingBoy.park(car4);
		
		assertEquals(car4, parkingLotB.pickCar("carD"));
	}
	
	/**
	 * Given Parking Lot A B both Have 1 Capacity and both Have No Available Space
	 * When A Super Parking Boy park car
	 * Then Car Cannot Be Parked
	 */
	@Test
	public void givenAllParkingLotSameCapacityAndHaveNoAvailable_whenSuperParkingBoyPark_thenCannotParkCar() {
		Car car1 = new Car("carA");
		Car car2 = new Car("carB");
		Car car3 = new Car("carC");
		ParkingLot parkingLotA = new ParkingLot(1);
		ParkingLot parkingLotB = new ParkingLot(1);
		List<ParkingLot> parkingLotList = Arrays.asList(parkingLotA, parkingLotB);
		ParkingBoy superParkingBoy = new SuperParkingBoy(parkingLotList);
		
		superParkingBoy.park(car1);
		superParkingBoy.park(car2);
		
		assertNull(superParkingBoy.park(car3));
	}
	
	/**
	 * Given Parking Lot B has 1 Capacity and 0 Available Slot
	 * And Parking Lot A has 1 Capacity and 1 Available Slot
	 * When A Super Parking Boy park car
	 * Then Car is Parked in Parking Lot A
	 */
	@Test
	public void givenAllParkingLotsHaveSameCapacityExceptAHasMoreAvailableSlot_whenSuperParkingBoyPark_thenParkedInParkingLotA() {
		Car car1 = new Car("carA");
		Car car2 = new Car("carB");
		ParkingLot parkingLotA = new ParkingLot(1);
		ParkingLot parkingLotB = new ParkingLot(1);
		List<ParkingLot> parkingLotList = Arrays.asList(parkingLotB, parkingLotA);
		ParkingBoy superParkingBoy = new SuperParkingBoy(parkingLotList);
		
		superParkingBoy.park(car1);
		superParkingBoy.park(car2);
		
		assertEquals(car2, parkingLotA.pickCar("carB"));
	}
	
	/**
	 * Given Parking Lot A B both have same capacity and available Slots
	 * When A Super Parking Boy park car
	 * Then Car is parked in Parking Lot B
	 */
	@Test
	public void givenAllParkingLotsHaveSameCapacityAndAvilableSlots_whenSuperParkingBoyPark_thenParkedInParkingLotB() {
		Car car1 = new Car("carA");
		Car car2 = new Car("carB");
		Car car3 = new Car("carC");
		ParkingLot parkingLotA = new ParkingLot(5);
		ParkingLot parkingLotB = new ParkingLot(5);
		List<ParkingLot> parkingLotList = Arrays.asList(parkingLotA, parkingLotB);
		ParkingBoy superParkingBoy = new SuperParkingBoy(parkingLotList);
		
		superParkingBoy.park(car1);
		superParkingBoy.park(car2);
		superParkingBoy.park(car3);
		
		assertEquals(car3, parkingLotB.pickCar("carC"));
	}
	
	/**
	 * Given Parking Manager manages no parking boy
	 * When a Parking Manager park car
	 * Then car is parked by Parking Manager
	 */
	@Test
	public void givenParkingManagerManagesNoParkingBoy_whenParkingManagerParkCar_thenCarIsParkedByParkingManager() {
		Car car1 = new Car("carA");
		ParkingLot parkingLotA = new ParkingLot(5);
		List<ParkingLot> parkingLotList = Arrays.asList(parkingLotA);
		ParkingBoy parkingManager = new ParkingManager(parkingLotList, new ArrayList<>());
		
		Receipt receipt1 = parkingManager.park(car1);
		
		assertEquals("carA", receipt1.getCarNumber());
		assertEquals(parkingManager.hashCode(), receipt1.getIssuerId());
	}

}
