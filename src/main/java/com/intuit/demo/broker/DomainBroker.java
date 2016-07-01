package com.intuit.demo.broker;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.intuit.demo.core.DomainCollection;
import com.intuit.demo.resource.DomainResource;

/**
 * basic endpoint(s) from domain records
 * @author maguilar
 *
 */
@RestController
@RequestMapping ( "/domain" )
public final class DomainBroker
{
	@Autowired
	private DomainResource domainResource;
	
	/**
	 * retrieves all current domain names captured by referral service
	 * @return collection of domain names and their ids
	 */
	@RequestMapping ( value = "name", method = RequestMethod.GET )
	@Produces ( MediaType.APPLICATION_JSON )
	@Consumes ( MediaType.APPLICATION_FORM_URLENCODED )
	public DomainCollection retrieveDomains()
	{
		return domainResource.getAll();
	}
	
	/**
	 * retrieves top N referrer domains
	 * @param topN number of top referrers to return
	 * @return topN domains
	 */
	@RequestMapping ( value = "name/top", method = RequestMethod.GET )
	@Produces ( MediaType.APPLICATION_JSON )
	@Consumes ( MediaType.APPLICATION_FORM_URLENCODED )
	public DomainCollection retrieveTopDomains( @RequestParam("n") Integer topN )
	{
		return domainResource.getTopN( topN );
	}
}
