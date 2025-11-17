package com.flightapp.entity;
//here we are shaping the input into sql table input
import jakarta.persistence.*;

@Entity // marks the data as entity for a sql table
@Table(name = "airline", uniqueConstraints = @UniqueConstraint(name = "uq_airline_code", columnNames = "code"))
public class Airline {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "airline_id")
	private Long id;

	@Column(name = "name", nullable = false, length = 100)
	private String name;

	@Column(name = "code", nullable = false, length = 10)
	private String code;

	@Column(name = "logo_url", length = 255)
	private String logoUrl;

	
	
	// getters & setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}
}
