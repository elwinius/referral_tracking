package com.intuit.demo.core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.validator.routines.DomainValidator;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.intuit.demo.exception.InvalidUrlException;

/**
 * represents a referrer domain
 * 
 * @author maguilar
 *
 */
@Entity
@Table( name = "domain" )
public class Domain
{
	@Id
	@GeneratedValue
	private Long domainId;
	
	@Column
	private String name;
	
	public Domain() {}
	
	public Domain( final String name )
	{
		this.name = name;
	}
	
	@JsonProperty("id")
	public Long getDomainId()
	{
		return domainId;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(final String name)
	{
		this.name = name;
	}
	
	public void validate() throws InvalidUrlException
	{
		if ( !DomainValidator.getInstance().isValid( name ) ) 
		{
			throw new InvalidUrlException();
		}
	}
}
