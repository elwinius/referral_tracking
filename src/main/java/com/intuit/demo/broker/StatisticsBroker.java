package com.intuit.demo.broker;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.intuit.demo.core.Referral;
import com.intuit.demo.exception.DomainIdNotFoundException;
import com.intuit.demo.exception.InvalidUrlException;
import com.intuit.demo.resource.ReferralResource;

/**
 * endpoints for site stats stuffs
 * 
 * @author maguilar
 *
 */
@RestController
@RequestMapping ( "/stat" )
public final class StatisticsBroker
{
	@Autowired
	private ReferralResource referralResource;

	/**
	 * creates a referral
	 * 
	 * @param referrer
	 *            domain name of referrer
	 * @return created Referral
	 * @throws InvalidUrlException
	 */
	@RequestMapping ( value = "referral", method = RequestMethod.POST )
	@Produces ( MediaType.APPLICATION_JSON )
	@Consumes ( MediaType.APPLICATION_FORM_URLENCODED )
	public Referral makeReferral( @RequestParam ( value = "name" ) String referrer ) throws InvalidUrlException
	{
		return referralResource.post( referrer );
	}

	/**
	 * retrieves referral counts by month for a given domain
	 * 
	 * @param domainId
	 *            domain id to query
	 * @return created list of referral counts
	 * @throws InvalidUrlException
	 * @throws DomainIdNotFoundException
	 */
	@RequestMapping ( value = "referral/{domainId}", method = RequestMethod.GET )
	@Produces ( MediaType.APPLICATION_JSON )
	public List<Long> retrieveReferralDates( @PathVariable ( value = "domainId" ) Long domainId )
			throws DomainIdNotFoundException
	{
		return referralResource.get( domainId );
	}
}
