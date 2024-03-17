package model;

public class Admin {
	private int Id_admin;
	private String mdp;
	private int rang;
	
	public int getId_admin() {
		return Id_admin;
	}
	
	public void setId_admin(int Id_admin) {
		this.Id_admin = Id_admin;
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
		Id_admin = id_admin;
		this.mdp = mdp;
		this.rang = rang;
	}
}
