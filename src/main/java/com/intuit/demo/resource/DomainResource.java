package com.intuit.demo.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.intuit.demo.core.DomainCollection;
import com.intuit.demo.persistence.DomainRepo;

/**
 * handles the requests sent by brokers
 * @author maguilar
 *
 */
@Component
public final class DomainResource
{
	@Autowired
	private DomainRepo domainRepo;

	/**
	 * 
	 * @return all domains
	 */
	public DomainCollection getAll()
	{
		return new DomainCollection( domainRepo.findAll() );
	}

	/**
	 * 
	 * @param topN number of top domains to return
	 * @return top N domains based on referral count
	 */
	public DomainCollection getTopN( final Integer topN )
	{
		return new DomainCollection( domainRepo.findTopN( topN ) );
	}
}
