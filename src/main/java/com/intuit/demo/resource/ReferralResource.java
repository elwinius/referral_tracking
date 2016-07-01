package com.intuit.demo.resource;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.intuit.demo.core.Domain;
import com.intuit.demo.core.Referral;
import com.intuit.demo.exception.DomainIdNotFoundException;
import com.intuit.demo.exception.InvalidUrlException;
import com.intuit.demo.persistence.DomainRepo;
import com.intuit.demo.persistence.ReferralRepo;

/**
 * handles the requests sent by brokers
 * @author maguilar
 *
 */
@Component
public final class ReferralResource
{
	@Autowired
	private ReferralRepo referralRepo;

	@Autowired
	private DomainRepo domainRepo;

	public Referral post( final String domainName ) throws InvalidUrlException
	{
		final Domain domain = acquireDomain( domainName );
		final Referral newReferral = new Referral( domain, DateTime.now().toDate() );

		referralRepo.save( newReferral );

		return newReferral;
	}

	public List<Long> get( final Long domainId ) throws DomainIdNotFoundException
	{
		final Domain domain = domainRepo.findByDomainId( domainId );

		if ( domain == null )
		{
			throw new DomainIdNotFoundException();
		}

		final List<Long> referralCounts = new ArrayList<>();
		referralCounts.addAll( referralRepo.getMonthlyCounts( domain ) );

		return referralCounts;
	}

	private Domain acquireDomain( final String domainName ) throws InvalidUrlException
	{
		final Domain domain;

		if ( domainRepo.existsByName( domainName ) )
		{
			domain = domainRepo.findByName( domainName );
		} else
		{
			final Domain newDomain = new Domain( domainName );
			newDomain.validate();
			domain = domainRepo.save( newDomain );
		}

		return domain;
	}
}
