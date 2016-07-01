package com.intuit.demo.persistence;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.intuit.demo.core.Domain;
import com.intuit.demo.core.Referral;

/**
 * persistence handler for Referrals
 * @author maguilar
 *
 */
@Transactional
public interface ReferralRepo extends CrudRepository<Referral, Long>
{
	public Referral findByDomain( String domain );
	
	@Override
	public <S extends Referral> S save( S entity );
	
	@Query ( "SELECT count(r) FROM Referral r WHERE domain = ?1 GROUP BY FUNCTION('MONTH', r.referralDate)" )
	public List<Long> getMonthlyCounts( Domain domain );
}
