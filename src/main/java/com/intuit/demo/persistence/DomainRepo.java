package com.intuit.demo.persistence;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.intuit.demo.core.Domain;

/**
 * persistence handler for Domains
 * 
 * @author maguilar
 *
 */
@Transactional
public interface DomainRepo extends CrudRepository<Domain, Long>
{
	public Domain findByName( String name );

	@Query ( "SELECT CASE WHEN COUNT(d) > 0 THEN 'true' ELSE 'false' END FROM Domain d WHERE d.name = ?1" )
	public Boolean existsByName( String name );

	@Override
	public <S extends Domain> S save( S Domain );
	
	@Query ( "SELECT d FROM Domain d ORDER BY d.name ASC" )
	public List<Domain> findAll();
	
	@Query(value = "SELECT d.* FROM domain d INNER JOIN referral r ON d.domain_id = r.domain_id GROUP BY r.domain_id ORDER BY count(*) DESC LIMIT ?1", nativeQuery = true)
	public List<Domain> findTopN( Integer topN );
	
	public Domain findByDomainId( Long domainId );
}
