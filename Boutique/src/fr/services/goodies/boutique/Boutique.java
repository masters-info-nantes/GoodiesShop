package fr.services.goodies.boutique;


import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.UUID;

import org.apache.axis2.AxisFault;

import fr.services.banque.PaiementStub;
import fr.services.banque.PaiementStub.EffectuerPaiement;
import fr.services.goodies.fournisseur.FournisseurStub;
import fr.services.goodies.fournisseur.FournisseurStub.AnnulerReservation;
import fr.services.goodies.fournisseur.FournisseurStub.GetReservation;
import fr.services.goodies.fournisseur.FournisseurStub.GetReservationResponse;
import fr.services.goodies.fournisseur.FournisseurStub.ListerProduits;
import fr.services.goodies.fournisseur.FournisseurStub.ListerProduitsResponse;
import fr.services.goodies.fournisseur.FournisseurStub.Produit;
import fr.services.goodies.fournisseur.FournisseurStub.ReserverProduit;
import fr.services.goodies.fournisseur.FournisseurStub.ReserverProduitResponse;

public class Boutique {
	
	private final static String fournisseurEndPoint = "http://localhost:9763/services/Fournisseur/";
	private final static String banqueEndPoint = "http://localhost:9763/services/Banque/";
	private Set<String> clients;
	/*nom utilisateur / id reservation*/
	private Map<String, Set<String>> reservations;
	/*id commande / Produits*/
	private Map<String, List<Produit>> commandes;
	
	public Boutique(){
		clients = new HashSet<String>();
		reservations = new TreeMap<String, Set<String>>();
		commandes = new TreeMap<String, List<Produit>>();
	}

	public Produit[] listerProduits(){
		
		Produit[] listeProduits = null;
		FournisseurStub stub;
		try {
			stub = new FournisseurStub(fournisseurEndPoint);
			ListerProduits requete = new ListerProduits();
			ListerProduitsResponse reponse = stub.listerProduits(requete);
			listeProduits = reponse.get_return();
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listeProduits;
	}
	
	public void validerProduitPourCommande(String nomUtilisateur, String idProduits, int quantite){
		FournisseurStub stub;
		try {
			stub = new FournisseurStub(fournisseurEndPoint);
			
			ReserverProduit requete = new ReserverProduit();
			requete.setId(idProduits);
			requete.setQuantite(quantite);
			ReserverProduitResponse reponse = stub.reserverProduit(requete);
			String idReservation = reponse.get_return();
			if(reservations.containsKey(nomUtilisateur)){
				reservations.get(nomUtilisateur).add(idReservation);
			}
			else{
				HashSet<String> temp = new HashSet<String>();
				temp.add(idReservation);
				reservations.put(nomUtilisateur, temp);
			}

		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void annulerCommandeEnCours(String nomUtilisateur){
		FournisseurStub stub;
		try {
			stub = new FournisseurStub(fournisseurEndPoint);
			for(String s : this.reservations.get(nomUtilisateur)){
				AnnulerReservation requete = new AnnulerReservation();
				requete.setId(s);
				
				stub.annulerReservation(requete);
				this.reservations.get(nomUtilisateur).remove(s);
				if(this.reservations.get(nomUtilisateur).isEmpty()){
					this.reservations.remove(nomUtilisateur);
				}
			}
		}
		catch(AxisFault e){
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String effectuerPaiement(String nomUtilisateur, String numeroCarte, String cyptogramme, String date) {
		PaiementStub stubPaiement;
		FournisseurStub stubFournisseur;
		String idCommande = null;
		try {
			stubPaiement = new PaiementStub(banqueEndPoint);
			stubFournisseur = new FournisseurStub(fournisseurEndPoint);
			
			EffectuerPaiement requete = new EffectuerPaiement();
			requete.setCryptogramme(cyptogramme);
			requete.setDate(date);
			requete.setNomUtilisateur(nomUtilisateur);
			requete.setNumeroCarte(numeroCarte);
			
			stubPaiement.effectuerPaiement(requete);
			
			idCommande = UUID.randomUUID().toString();
			
			List<Produit> p = new ArrayList<FournisseurStub.Produit>();
			for(String s : reservations.get(nomUtilisateur)){
				GetReservation request = new GetReservation();
				request.setId(nomUtilisateur);
				GetReservationResponse response = stubFournisseur.getReservation(request);
				p.add(response.get_return());
			}
			this.commandes.put(idCommande, p);
			this.reservations.remove(nomUtilisateur);
			
		}
		catch(AxisFault e){
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return idCommande;
	}
	
	public void creerClient(String name){
		clients.add(name);
	}
	
	public void supprimerCLient(String name){
		clients.remove(name);
	}
		
}
