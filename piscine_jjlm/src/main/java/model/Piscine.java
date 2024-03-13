package model;

public class Piscine {
	private int ID;
	private String nom;
	private String adresse ;
	private int nbr_bassin;
	
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
		this.nom = nom;
	}
	
	public String getAdresse() {
		return adresse;
	}
	
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	
	public int getNbrBassin() {
		return nbr_bassin;
	}
	
	public void setNbrBassin(int nbr_bassin) {
		this.nbr_bassin=nbr_bassin;
	}
	
	public Piscine(int ID, String nom, String adresse, int nbr_bassin) {
		this.ID= ID;
		this.nom = nom;
		this.adresse = adresse;
		this.nbr_bassin = nbr_bassin;	
	}

	@Override
	public String toString() {
		return "Piscine [ID=" + ID + ", nom=" + nom + ", adresse=" + adresse + ", nbr_bassin=" + nbr_bassin + "]";
	}
	
	
		
}
