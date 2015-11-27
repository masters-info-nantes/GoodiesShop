package fr.services.boutique.api.factories;

import fr.services.boutique.api.valueobject.IClient;

public interface IClientFactory {
	public IClient getClient(String name);
}
