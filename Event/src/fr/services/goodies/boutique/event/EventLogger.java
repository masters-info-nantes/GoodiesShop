package fr.services.goodies.boutique.event;

import akka.actor.ActorSystem;
import eventstore.Event;
import eventstore.SubscriptionObserver;
import eventstore.j.EsConnection;
import eventstore.j.EsConnectionFactory;

import java.io.Closeable;

public class EventLogger {
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
}