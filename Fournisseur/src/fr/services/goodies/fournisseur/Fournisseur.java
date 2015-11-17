package fr.services.goodies.fournisseur;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

public class Fournisseur {

	private String deviseProduits;
	private Map<String, Produit> produitsEnStock;
	private Map<String, Produit> reservations;
	
	public Fournisseur() {
		deviseProduits = "USD";
		produitsEnStock = new TreeMap<String, Produit>();
		reservations = new TreeMap<String, Produit>();
		Produit p = new Produit("dexter", 20, 10);
		produitsEnStock.put(p.getId(), p);
	}
	
	public List<Produit> listerProduits(){
		List<Produit> list = new ArrayList<Produit>(produitsEnStock.values());
		return list;
	}
	
	public String reserverProduit(String id, int quantite) throws QuantiteInsufisante, CeProduitNExistePas{
	
		if(!produitsEnStock.containsKey(id)){
			throw new CeProduitNExistePas();
		}
		else if(produitsEnStock.get(id).getQuantite() < quantite){
			throw new QuantiteInsufisante();
		}
		else{	
			Produit p = produitsEnStock.get(id);
			String idReservation = UUID.randomUUID().toString();
			reservations.put(idReservation, new Produit(p.getNom(), id, p.getPrix(), quantite));
			p.setQuantite(p.getQuantite() - quantite);
			return idReservation;
		}
	}
			
	public void annulerReservation(String id) throws CetteReservationNExistePas{
		if(!reservations.containsKey(id)){
			throw new CetteReservationNExistePas();
		}
		else{
			Produit p = produitsEnStock.get(id);
			p.setQuantite(p.getQuantite() + reservations.get(id).getQuantite());
			reservations.remove(id);
		}
	}
	
	public Produit getReservation(String id) throws CetteReservationNExistePas{

		if(!reservations.containsKey(id)){
			throw new CetteReservationNExistePas();
		}
		return reservations.get(id);
	}
	
}
