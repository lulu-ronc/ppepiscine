package model;

public class Professeur {
	private int ID;
	private String nom; 
	
	
	public Professeur(int ID, String nom) {
		super();
		
		this.ID = ID;
		this.nom = nom;
	}
	
	public int getID() {
		return ID;
	}
	
	public void setID(int ID) {
		this.ID = ID;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
}
