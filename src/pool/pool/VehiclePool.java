/**
 * 
 */
package pool;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2013-5-28
 */
public class VehiclePool {

	static enum State {
		IN_USE, FREE;
	}

	private static final Map<Vehicle, State> map = new HashMap<Vehicle, State>();

	static {
		map.put(new Car(1), State.FREE);
		map.put(new Car(2), State.FREE);
		map.put(new Car(3), State.FREE);
		map.put(new Car(4), State.FREE);
		map.put(new Car(5), State.FREE);
	}

	public synchronized static Vehicle getVehicle() {
		Set<Vehicle> keys = map.keySet();
		for (Vehicle vehicle : keys) {
			if (State.FREE == map.get(vehicle)) {
				map.put(vehicle, State.IN_USE);
				return vehicle;
			}
		}
		throw new RuntimeException(
				"All vehicle are in use.Please wait for a moment.");
	}

	public synchronized static void giveBack(Vehicle vehicle) {
		if (vehicle != null) {
			map.put(vehicle, State.FREE);
		}
	}
}