package model;

public class Formule {
	private int id_formule;
	private int id_piscine;
	private int duree_validite;
	private String nom;
	private int montant;
	
	
	public Formule() {
    }
	
	public Formule(int id_formule, int id_piscine,int duree_validite, String nom,int montant) {
		super();
		this.id_formule = id_formule;
		this.id_piscine = id_piscine;
		this.duree_validite=duree_validite;
		this.nom = nom;
		this.montant = montant;	
	}
	
	
	
	public int getID() {
		return id_formule;
	}
	
	public void setID(int id_formule) {
		this.id_formule=id_formule;
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

	@Override
	public String toString() {
		return "Formule [id_formule=" + id_formule + ", id_piscine=" + id_piscine + ", duree_validite=" + duree_validite
				+ ", nom=" + nom + ", montant=" + montant + "]";
	}
	
	
	
}

	
