package fr.services.boutique.domain.valueobject;

import java.util.List;
import java.util.Map;

import fr.services.boutique.api.valueobject.IClient;
import fr.services.fournisseur.domain.services.FournisseurStub.Produit;

public class Client implements IClient{

	private String name;
	private Map<String, List<Produit>> produitsDesCommandes;
	
	public Client(String name){
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	
	public Produit[] getProduits(String idCommande) {
		return (Produit[]) produitsDesCommandes.get(idCommande).toArray();
	}

	
	public String[] getCommandes() {
		return (String[]) produitsDesCommandes.keySet().toArray();
	}

}
