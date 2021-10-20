package entity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Whiteboard {
	private List<Whiteboardobserver> observers;
	
	protected Whiteboard() {
		this.observers = new ArrayList<>();
	}
	
	public void observerHinzufuegen(Whiteboardobserver observer) {
		this.observers.add(observer);
	}
	
	protected void observerInformieren(String information) {
		for(Whiteboardobserver observer: this.observers) {
			observer.update(information);
		}
	}
	
	@Override
	public String toString() {
		return this.observers.stream().map(v->v.toString()).collect(Collectors.joining(", "));
	}
}