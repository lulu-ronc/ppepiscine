package model;


public class Piscine {
	private int ID;
	private String nom;
	private String description ;
	private int nbr_bassin;
	
	public Piscine(int ID, String nom, String description, int nbr_bassin) {
		this.ID = ID;
		this.nom = nom;
		this.description=description;
		this.nom = nom;
		this.nbr_bassin = nbr_bassin;	
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
		this.nom = nom;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String adresse) {
		this.description = adresse;
	}
	
	public int getNbrBassin() {
		return nbr_bassin;
	}
	
	public void setNbrBassin(int nbr_bassin) {
		this.nbr_bassin=nbr_bassin;
	}

	
	
	@Override
    public String toString() {
        return nom;
    }
	
		
}
