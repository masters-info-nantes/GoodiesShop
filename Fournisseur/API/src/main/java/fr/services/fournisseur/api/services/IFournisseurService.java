package fr.services.fournisseur.api.services;

import java.util.List;

import fr.services.fournisseur.api.valueobject.IProduit;

public interface IFournisseurService {

	public List<IProduit> listerProduits();
	public String reserverProduit(String idProduit, int quantite);
	public void annulerReservation(String idReservation);
	public IProduit getReservation(String idReservation);
}
