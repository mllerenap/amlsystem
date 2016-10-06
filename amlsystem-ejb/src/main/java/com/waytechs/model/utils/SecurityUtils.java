package com.waytechs.model.utils;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.waytechs.model.ejb.facades.AdUserFacade;
import com.waytechs.model.entities.AdUser;

@Stateless
public class SecurityUtils {   
	
	@Resource
	private SessionContext session;
	
	@Inject
	private AdUserFacade adUserFacade;

	public String getCurrentUser() {

		String currentUser = null;
		
		currentUser = session.getCallerPrincipal().getName();

		System.out.println("audit: " + currentUser);

		
		return currentUser;

	}
	
	public AdUser getAdUser(){
		return adUserFacade.findByUsername(getCurrentUser());
	}
	

}
