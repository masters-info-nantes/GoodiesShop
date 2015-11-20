package fr.services.goodies.boutique.event;

import akka.actor.*;
import eventstore.*;
import eventstore.j.*;
import eventstore.tcp.ConnectionActor;

import java.net.InetSocketAddress;
import java.util.UUID;

/**
 * Classe à utiliser pour envoyer des événements sur EventStore.
 * @author E15A968H
 *
 */
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

	/**
	 * Créer et envoyer un événement sur cette instance d'EventStore.
	 * @param streamName Nom du stream d'événements (créé si inexistant).
	 * @param eventType Type d'événement (pas très important ?)
	 * @param eventData Données de l'événement (de préférence en JSON ou XML)
	 * @param eventMetadata Métadonnées, aussi en JSON ou XML si possible.
	 */
	public void writeEvent(String streamName, String eventType, String eventData, String eventMetadata){

		EventData event = new EventDataBuilder(eventType)
		        .eventId(UUID.randomUUID())
		        .data(eventData)
		        .metadata(eventMetadata)
		        .build();		

		WriteEvents writeEvents = new WriteEventsBuilder(streamName)
		        .addEvent(event)
		        .expectAnyVersion()
		        .build();

		this.connection.tell(writeEvents, this.writeResult);	
	}

	private static class WriteResult extends UntypedActor {
	    public void onReceive(Object message) throws Exception {
	        context().system().shutdown();
	    }
	}
}
