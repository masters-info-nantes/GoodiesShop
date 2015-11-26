package fr.services.boutique.domain.valueobject;

import java.util.List;
import java.util.Map;

import fr.service.fournisseur.application.WebApplicationFournisseurStub.IProduit;
import fr.services.boutique.api.valueobject.IClient;

public class Client implements IClient{

	private String name;
	private Map<String, List<IProduit>> produitsDesCommandes;
	@Override
	public String getName() {
		return name;
	}

	@Override
	public IProduit[] getProduits(String idCommande) {
		return (IProduit[]) produitsDesCommandes.get(idCommande).toArray();
	}

	@Override
	public String[] getCommandes() {
		return (String[]) produitsDesCommandes.keySet().toArray();
	}

}
