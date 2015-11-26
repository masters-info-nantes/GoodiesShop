package fr.services.fournisseur.api.factories;

import fr.services.fournisseur.api.valueobject.IProduit;


public interface IProduitFactory {
	
	public IProduit getProduit(String nom, double Prix, int quantite);
	public IProduit getProduit(String id, String nom, double Prix, int quantite);

}
