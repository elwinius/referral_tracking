package com.intuit.demo.core;

import java.util.List;

/**
 * JSON serializable collection of Domains
 * @author maguilar
 *
 */
public final class DomainCollection
{
	private final List<Domain> domains;

	public DomainCollection( final List<Domain> domains )
	{
		this.domains = domains;
	}
	
	public List<Domain> getDomains()
	{
		return domains;
	}
}
