package fr.services.goodies.boutique.event;

import java.io.Serializable;
import java.util.Map;

import fr.services.goodies.fournisseur.Produit;

/**
 * Evenement généré quand une commande est passée avec succès.
 * @author hector
 *
 */
public class CommandePassee implements Serializable {
	
	public static final String STREAM_NAME = "boutique";
	public static final String EVENT_TYPE = "achat";

	private static final long serialVersionUID = 4439038852906781357L;
	
	/**
	 * Chaque produit acheté avec sa quantité associée.
	 */
	public Map<Produit, Integer> articles;
	
	public int montant;
	public String devise;
}
