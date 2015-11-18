package fr.services.goodies.boutique.event;


public class Main {
	public static void main(String[] args){
		EventStore store = new EventStore("localhost", 1113);
		store.write("newstream", "event-type", "{\"coucou\":\"2\"}");
	}
}