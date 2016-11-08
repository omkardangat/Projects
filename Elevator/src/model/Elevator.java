package model;

import java.util.LinkedList;
import java.util.Queue;

public class Elevator {
	public int ElevatorID;
	public int CurrentFloor;
	public Queue<Integer> ProcessQueue;
	
	public Elevator(int id) {
		this.ElevatorID = id;
		this.CurrentFloor = 0;
		this.ProcessQueue = new LinkedList<Integer>();
	}
	public void Ascend() {
		this.CurrentFloor++;
	}
	
	public void Descend() {
		this.CurrentFloor--;
	}
	
}
