package com.intuit.demo.core;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.intuit.demo.util.ISO8601DateTime;

/**
 * a website referral
 * @author maguilar
 *
 */
@Entity
@Table( name = "referral" )
public class Referral {
	@Id
	@GeneratedValue
	private Long referralId;
	
	@ManyToOne
	@JoinColumn(name="domain_id")
	private Domain domain;
	
	@Column
	private Date referralDate;
	
	public Referral() {}
	
	/**
	 * 
	 * @param domainSource domain from which the referral was made
	 * @param referralDate date and time referral was made
	 */
	public Referral(final Domain referrer, final Date referralDate) {
		this.domain = referrer;
		this.referralDate = referralDate;
	}
	
	public String getDomain() {
		return domain.getName();
	}
	
	public void setDomain(final Domain domain) {
		this.domain = domain;
	}
	
	public String getReferralDate() {
		return ISO8601DateTime.now().asFormattedString();
	}

	public void setReferralDate(final Date referralDate) {
		this.referralDate = referralDate;
	}
}
