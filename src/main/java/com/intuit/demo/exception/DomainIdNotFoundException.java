package com.intuit.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings ( "serial" )
@ResponseStatus ( value = HttpStatus.NOT_FOUND, reason = "Domain ID do you even exist?" )
public class DomainIdNotFoundException extends Exception
{
}
