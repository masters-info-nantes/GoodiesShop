package fr.services.boutique.api.valueobject;

import fr.service.fournisseur.application.WebApplicationFournisseurStub.IProduit;

public interface IClient {
	public String getName();
	public IProduit[] getProduits(String idCommande);
	public String[] getCommandes();
}
