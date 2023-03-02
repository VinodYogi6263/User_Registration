package com.ss.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user_details")
public class User_Entity {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	
	@Column(name="email")
	private String email;
	
	@Column(name="first_name")
	private String first_name;
	
	@Column(name="last_name")
	private String last_name;
	
	@Column(name="password")
	private String password;
	
	@Column(name="phone_no")
	private String phone_no;
	
	@Column(name="status")
	private int status;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-mm-yyyy")
	@Column(name="created_at")
	private Date created_at;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-mm-yyyy")
	@Column(name="updated_at")
	private Date updated_at;
	
	@Column(name="role")
	private String role;
	
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-mm-yyyy")
	@Column(name="password_updated_at")
	private Date password_updated_at;

	public User_Entity(String email, String first_name, String last_name, String password, String phone_no,Date created_at, String role) 
	{
		super();
		this.email = email;
		this.first_name = first_name;
		this.last_name = last_name;
		this.password = password;
		this.phone_no = phone_no;
		this.created_at = created_at;
		this.role = role;
	}

	public User_Entity(long id, String email, String first_name, String last_name, String password,Date updated_at, String phone_no, String role)
	{
		super();
		this.id = id;
		this.email = email;
		this.first_name = first_name;
		this.last_name = last_name;
		this.password = password;
		this.phone_no = phone_no;
		this.updated_at = updated_at;
		this.role = role;
	}

	public User_Entity(String email, String first_name, String last_name, String password, String role) {
		super();
		this.email = email;
		this.first_name = first_name;
		this.last_name = last_name;
		this.password = password;
		this.role = role;
	}

	public User_Entity(long id, String email, String first_name, String last_name, String password,
			Date updated_at, String phone_no) {
		super();
		this.id = id;
		this.email = email;
		this.first_name = first_name;
		this.last_name = last_name;
		this.password = password;
		this.phone_no = phone_no;
		this.updated_at = updated_at;
	}

	public User_Entity(String email, String first_name, String last_name, Date updated_at,
			String phone_no) {
		this.email = email;
		this.first_name = first_name;
		this.last_name = last_name;
		this.phone_no = phone_no;
		this.updated_at = updated_at;
		
	}
	
	

	
		
}
