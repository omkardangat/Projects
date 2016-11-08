package Controller;
import java.util.ArrayList;
import java.util.List;

import model.Elevator;

public class ElevatorManager implements Runnable {
	private List<Elevator> Elevators;
	
	public ElevatorManager() {
		this.Elevators = new ArrayList<Elevator>();
	}
	
	public void GoToFloor(Elevator elevator, int destinationFloor) {
		elevator.ProcessQueue.add(destinationFloor);
	}
	
	//TODO: emergency stop
	public void StartElevators() {
		while(true) {
			//TODO: for each elevator I need a new thread
			for(Elevator elevator: this.Elevators){
				int destinationFloor = elevator.ProcessQueue.remove();
				if (elevator.CurrentFloor < destinationFloor) {
					elevator.Ascend();
				} else if (elevator.CurrentFloor > destinationFloor) {
					elevator.Descend();
				}
			}
		}
	}

	@Override
	public void run() {
		this.StartElevators();	
	}
}
