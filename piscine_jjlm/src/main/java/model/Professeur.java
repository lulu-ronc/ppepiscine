package model;

public class Professeur {
	private int ID;
	private String nom; 
	private boolean detente;
	private boolean competitif;
	private boolean surveillance_bassin;
	private int ID_piscine;
	
	
	public Professeur(int ID, String nom, boolean detente, boolean competitif, boolean surveillance_bassin,int ID_piscine) {
		super();
		
		this.ID = ID;
		this.nom = nom;
		this.detente = detente;
		this.competitif = competitif;
		this.surveillance_bassin=surveillance_bassin;
		this.ID_piscine = ID_piscine;
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
	
	
	public boolean getDetente() {
		return detente;
	}
	
	public void setDetente(boolean detente) {
		this.detente = detente;
	}
	
	
	public boolean getCompetitif() {
		return competitif;
	}
	
	public void setCompetitif(boolean competitif) {
		this.competitif = competitif;
	}
	
	public boolean getSurveillanceBassin() {
		return surveillance_bassin;
	}
	
	public void setSurveillanceBassin(boolean surveillance_bassin) {
		this.surveillance_bassin = surveillance_bassin;
	}
	
	public int getID_piscine() {
		return ID;
	}
	
	public void setID_piscine(int ID_piscine) {
		this.ID_piscine = ID_piscine;
	}
	
	
}
