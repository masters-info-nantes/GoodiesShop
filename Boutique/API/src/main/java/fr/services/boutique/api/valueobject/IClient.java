package fr.services.boutique.api.valueobject;

import fr.services.fournisseur.domain.services.FournisseurStub.Produit;


public interface IClient {
	public String getName();
	public Produit[] getProduits(String idCommande);
	public String[] getCommandes();
}
