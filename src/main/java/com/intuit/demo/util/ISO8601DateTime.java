package com.intuit.demo.util;

import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

/**
 * represents a given date and time in ISO8601 form
 * @author maguilar
 *
 */
public class ISO8601DateTime
{
	private static final DateTimeFormatter DATE_FORMATTER = ISODateTimeFormat.dateTimeNoMillis();

	private final DateTime utcDateTime;

	/**
	 * static factory method for an instance of current date/time.
	 * 
	 * @return a UtcDateTime for the current date/time.
	 */
	public static ISO8601DateTime now()
	{
		return new ISO8601DateTime( DateTime.now() );
	}
	
	public ISO8601DateTime( final Date time )
	{
		utcDateTime = new DateTime( time, DateTimeZone.UTC );
	}
	
	public ISO8601DateTime( final DateTime time )
	{
		utcDateTime = new DateTime( time, DateTimeZone.UTC );
	}
	
	/**
	 * for ISO8601 format
	 * @return time in format yyyy-MM-ddTHH:mm:ssZ
	 */
	public String asFormattedString()
	{
		return DATE_FORMATTER.print( utcDateTime );
	}
	
	/**
	 * used for plotting along the charts in the UI
	 * @return
	 */
	public String asShortDate()
	{
		final DateTimeFormatter dtf = DateTimeFormat.forPattern("MM/dd/yyyy");
		return dtf.print( utcDateTime );
	}

	public DateTime getUtcDateTime()
	{
		return utcDateTime;
	}
}
