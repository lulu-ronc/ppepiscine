package model;

public class Formule {
	private int ID;
	private String nom;
	private int id_piscine;
	private int duree_validite;
	private int montant;
	private int nbr_personnes;
	
	public Formule(int ID, String nom, int id_piscine,int duree_validite,int montant, int nbr_personnes) {
		super();
		this.ID = ID;
		this.nom = nom;
		this.id_piscine = id_piscine;
		this.duree_validite=duree_validite;
		this.montant = montant;
		this.nbr_personnes = nbr_personnes;
		
	}
	
	
	public int getID() {
		return ID;
	}
	
	public void setID(int ID) {
		this.ID=ID;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom= nom;
	}
	
	public int getId_piscine() {
		return id_piscine;
	}
	
	//a revoir
	public void setId_piscine(int id_piscine) {
		this.id_piscine = id_piscine;
	}
	
	public int getDuree_validite() {
		return duree_validite;
	}
	
	
	public void setDuree_validite(int duree_validite) {
		this.duree_validite = duree_validite;
	}
	
	public int getMontant() {
		return montant;
	}
	
	
	public void setMontant(int montant) {
		this.montant = montant;
	}
	
	public int getNbr_personnes() {
		return nbr_personnes;
	}
	
	
	public void setNbr_personnes(int nbr_personnes) {
		this.nbr_personnes = nbr_personnes;
	}
	
}

	
