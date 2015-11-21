package fr.services.fournisseur.api.factories;

import fr.services.fournisseur.api.valueobject.IProduit;

public interface ProduitFactory {
	
	public IProduit getProduit(String nom, String Prix, String quantite);

}
