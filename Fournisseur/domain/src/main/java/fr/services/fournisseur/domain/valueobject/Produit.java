package fr.services.fournisseur.domain.valueobject;

import java.util.UUID;

import fr.services.fournisseur.api.valueobject.IProduit;

public class Produit implements IProduit{

	String id;
	String nom;
	
	double prix;
	int quantite;
	
	public Produit(){
		
	}
	
	public Produit(String nom, double prix, int quantite) {
		super();
		this.nom = nom;
		this.prix = prix;
		this.quantite = quantite;
		this.id = UUID.randomUUID().toString();
	}
	
	public Produit(String id, String nom, double prix, int quantite) {
		super();
		this.nom = nom;
		this.prix = prix;
		this.quantite = quantite;
		this.id = id;
	}
	
	public String getId() {
		return this.id;
	}

	public String getNom() {
		return this.nom;
	}

	public double getPrix() {
		return this.prix;
	}

	public int getQuantite() {
		return this.quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

}
