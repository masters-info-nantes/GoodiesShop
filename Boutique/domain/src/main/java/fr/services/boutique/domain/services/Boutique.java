package fr.services.boutique.domain.services;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.UUID;

import org.apache.axis2.AxisFault;

import fr.service.banque.application.WebApplicationBanqueStub;
import fr.service.banque.application.WebApplicationBanqueStub.Payer;
import fr.services.boutique.api.factories.IClientFactory;
import fr.services.boutique.api.services.IBoutique;
import fr.services.fournisseur.domain.services.FournisseurStub;
import fr.services.fournisseur.domain.services.FournisseurStub.AnnulerReservation;
import fr.services.fournisseur.domain.services.FournisseurStub.GetReservation;
import fr.services.fournisseur.domain.services.FournisseurStub.GetReservationResponse;
import fr.services.fournisseur.domain.services.FournisseurStub.IProduit;
import fr.services.fournisseur.domain.services.FournisseurStub.ListerProduits;
import fr.services.fournisseur.domain.services.FournisseurStub.ListerProduitsResponse;
import fr.services.fournisseur.domain.services.FournisseurStub.Produit;
import fr.services.fournisseur.domain.services.FournisseurStub.ReserverProduit;
import fr.services.fournisseur.domain.services.FournisseurStub.ReserverProduitResponse;


public class Boutique implements IBoutique{

	private final static String fournisseurEndPoint = "http://localhost:9763/services/Fournisseur/";
	private final static String banqueEndPoint = "http://localhost:9763/services/WebApplicationBanque/";
	private Set<String> clients;
	/*nom utilisateur / id reservation*/
	private Map<String, Set<String>> reservations;
	/*id commande / Produits*/
	private Map<String, List<Produit>> commandes;
	IClientFactory factory = null;
	
	public Boutique(IClientFactory factory){
		this.factory = factory;
		clients = new HashSet<String>();
		reservations = new TreeMap<String, Set<String>>();
		commandes = new TreeMap<String, List<Produit>>();
	}
	
	public IProduit[] listerProduits() {
		IProduit[] listeProduits = null;
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

	public void validerProduitPourCommande(String nomUtilisateur, String idProduits, int quantite) {
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

	public void annulerCommandeEnCours(String nomUtilisateur) {
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

	public String effectuerPaiement(String nomClient, double somme) {
		WebApplicationBanqueStub stubPaiement;
		FournisseurStub stubFournisseur;
		String idCommande = null;
		try {
			stubPaiement = new WebApplicationBanqueStub(banqueEndPoint);
			stubFournisseur = new FournisseurStub(fournisseurEndPoint);
			
			Payer requete = new Payer();
			requete.setCompteDebit(nomClient);
			requete.setSomme(somme);
			requete.setCompteCredit("boutique");
			
			stubPaiement.payer(requete);
			
			idCommande = UUID.randomUUID().toString();
			
			List<Produit> p = new ArrayList<Produit>();
			
			if(reservations.containsKey(nomClient)){
				for(String s : reservations.get(nomClient)){
					GetReservation request = new GetReservation();
					request.setId(nomClient);
					GetReservationResponse response = stubFournisseur.getReservation(request);
					/*IProduit pro = (IProduit)response.get_return();
					p.add(pro);*/
				}
				this.commandes.put(idCommande, p);
				this.reservations.remove(nomClient);
			}
			
		}
		catch(AxisFault e){
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return idCommande;
	}

	public void creerClient(String name) {
		clients.add(name);
	}

	public void supprimerClient(String name) {
		clients.remove(name);
	}
	
}
