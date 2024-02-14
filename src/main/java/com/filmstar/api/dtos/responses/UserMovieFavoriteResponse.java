package com.filmstar.api.dtos.responses;

import com.filmstar.api.entities.User;

public class UserMovieFavoriteResponse {

    private Integer id;
    private String firstname;
    private String email;


    public UserMovieFavoriteResponse(User user) {
        this.id = user.getId();
        this.firstname = user.getFirstname();
        this.email = user.getEmail();
    }


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getFirstname() {
		return firstname;
	}


	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}



}
