package fr.services.goodies.boutique.event;

import akka.actor.*;
import eventstore.*;
import eventstore.j.*;
import eventstore.tcp.ConnectionActor;

import java.net.InetSocketAddress;
import java.util.UUID;

public class EventStore {

	private ActorRef connection;
	private ActorRef writeResult;

	public EventStore(String host, int port){
		ActorSystem system = ActorSystem.create();

		Settings settings = new SettingsBuilder()
		        .address(new InetSocketAddress(host, port))
		        .defaultCredentials("admin", "changeit")
		        .build();

		this.connection = system.actorOf(ConnectionActor.getProps(settings));
		this.writeResult = system.actorOf(Props.create(WriteResult.class));		
	}

	public void write(String streamName, String eventType, String eventData){

		EventData event = new EventDataBuilder(eventType)
		        .eventId(UUID.randomUUID())
		        .data(eventData)
		        //.metadata(eventData)
		        .build();		

		WriteEvents writeEvents = new WriteEventsBuilder(streamName)
		        .addEvent(event)
		        .expectAnyVersion()
		        .build();

		this.connection.tell(writeEvents, this.writeResult);	
	}

	public static class WriteResult extends UntypedActor {
	    public void onReceive(Object message) throws Exception {
	        context().system().shutdown();
	    }
	}
}
