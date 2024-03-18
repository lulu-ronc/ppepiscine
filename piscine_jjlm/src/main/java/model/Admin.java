package model;

public class Admin {
	private int id_admin;
	private String mdp;
	private int rang;
	
	public int getId_admin() {
		return id_admin;
	}
	
	public void setId_admin(int id_admin) {
		this.id_admin = id_admin;
	}
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	public int getRang() {
		return rang;
	}
	public void setRang(int rang) {
		this.rang = rang;
	}
	
	public Admin(int id_admin, String mdp, int rang) {
		this.id_admin = id_admin;
		this.mdp = mdp;
		this.rang = rang;
	}
}
