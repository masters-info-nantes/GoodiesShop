package fr.services.fournisseur.infra.factories;

import fr.services.fournisseur.api.factories.IProduitFactory;
import fr.services.fournisseur.api.valueobject.IProduit;
import fr.services.fournisseur.domain.valueobject.Produit;


public class ProduitFactory implements IProduitFactory{

	public IProduit getProduit(String nom, double prix, int quantite) {
		// TODO Auto-generated method stub
		return new Produit(nom, prix, quantite);
	}

	public IProduit getProduit(String id, String nom, double prix, int quantite) {
		// TODO Auto-generated method stub
		return new Produit(id, nom, prix, quantite);
	}
}
