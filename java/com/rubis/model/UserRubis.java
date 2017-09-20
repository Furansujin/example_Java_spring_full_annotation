package com.rubis.model;

public class UserRubis {
	
	private int idUser;
	private String email;
	private String name;
	private String prenom;
	private String photo;
	private boolean online;
	
	public UserRubis() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public UserRubis(int idUser, String email, String name, String prenom) {
		super();
		this.idUser = idUser;
		this.email = email;
		this.name = name;
		this.prenom = prenom;
		this.photo = "";
	}

	

	public UserRubis(int idUser, String email, String name) {
		super();
		this.idUser = idUser;
		this.email = email;
		this.name = name;
		this.prenom = "";
		this.photo = "";
	}
	
	
	public UserRubis(int idUser, String email, String name, String prenom, String photo) {
		super();
		this.idUser = idUser;
		this.email = email;
		this.name = name;
		this.prenom = prenom;
		this.photo = photo;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	//retourne un tableau a mettre dans un json
	public String getUserTabJson(){ 
		
		return "{\"id\" :\""+idUser+"\",\"nom\" :\""+name+"\",\"prenom\" :\""+prenom+"\",\"email\" :\""+email+"\",\"photo\" :\""+photo+"\"}";
	}
	

}
