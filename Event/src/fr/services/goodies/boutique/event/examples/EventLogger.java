package fr.services.goodies.boutique.event.examples;

import akka.actor.ActorSystem;
import eventstore.Event;
import eventstore.SubscriptionObserver;
import eventstore.j.EsConnection;
import eventstore.j.EsConnectionFactory;

import java.io.Closeable;

/**
 * Affiche les événements au fur et à mesure qu'ils sont publiés.
 * @author E15A968H
 *
 */
public class EventLogger implements Runnable {
	
	private final String streamName;
    private final ActorSystem system = ActorSystem.create();
	private final EsConnection connection = EsConnectionFactory.create(system);
	
	public EventLogger(String streamName) {
		this.streamName = streamName;
	}
	
    public static void main(String[] args) {
        final ActorSystem system = ActorSystem.create();
        final EsConnection connection = EsConnectionFactory.create(system);
        final Closeable closeable = connection.subscribeToStream(CommandePassee.STREAM_NAME, new SubscriptionObserver<Event>() {
            @Override
            public void onLiveProcessingStart(Closeable subscription) {
                system.log().info("live processing started");
            }

            @Override
            public void onError(Throwable e) {
                system.log().error(e.toString());
            }

            @Override
            public void onClose() {
                system.log().error("subscription closed");
            }

			@Override
			public void onEvent(Event arg0, Closeable arg1) {
                //system.log().info(arg0.toString());
                System.out.println(arg0.data().data().value().decodeString("utf-8"));
			}
        }, false, null);
    }
    
    @Override
    public void run() {
        final Closeable closeable = connection.subscribeToStream(streamName, new SubscriptionObserver<Event>() {
            @Override
            public void onLiveProcessingStart(Closeable subscription) {
                system.log().info("live processing started");
            }

            @Override
            public void onError(Throwable e) {
                system.log().error(e.toString());
            }

            @Override
            public void onClose() {
                system.log().error("subscription closed");
            }

			@Override
			public void onEvent(Event arg0, Closeable arg1) {
                //system.log().info(arg0.toString());
                System.out.println(arg0.data().data().value().decodeString("utf-8"));
			}
        }, false, null);

    }
}