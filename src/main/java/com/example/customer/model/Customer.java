package com.example.customer.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table
@Entity
public class Customer {
	@Id
	@Column
	public int id;
	@Column
	public String nama;
	@Column
	public String dob;
	@Column
	public String gender;
	@Column
	public String ngik;
}
