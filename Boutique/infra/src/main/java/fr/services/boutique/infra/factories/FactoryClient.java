package fr.services.boutique.infra.factories;

import fr.services.boutique.api.factories.IClientFactory;
import fr.services.boutique.api.valueobject.IClient;
import fr.services.boutique.domain.valueobject.Client;

public class FactoryClient implements IClientFactory{

	@Override
	public IClient getClient(String name) {
		return new Client(name);
	}

}
