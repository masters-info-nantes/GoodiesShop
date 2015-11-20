package fr.services.goodies.boutique.event.examples;

import fr.services.goodies.boutique.event.EventStore;

public class Main {
	
	private EventStore store = new EventStore("localhost", 1113);
	
	public static void main(String[] args){
		Main myInstance = new Main();
		myInstance.write();
	}
	
	public String write() {
		store.writeEvent(CommandePassee.STREAM_NAME, CommandePassee.EVENT_TYPE, "{\"coucou\":\"2\"}", "");
		return "Request !";
	}
}