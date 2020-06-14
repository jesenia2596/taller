package com.pds.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Currency {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotEmpty(message = "Name may not be empty")
	private String name;

	@NotEmpty(message = "Symbol may not be empty")
	private String symbol;

	private Long rank;

	@OneToMany(mappedBy = "currency")
	private Set<Quote> quote;

}
