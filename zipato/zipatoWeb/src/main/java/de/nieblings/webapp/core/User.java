/*
 * Copyright (C) SUBITO AG, all rights reserved.
 * 
 * $Id: User.java 84539 2015-08-25 10:55:37Z oliver $
 */
package de.nieblings.webapp.core;

import java.beans.Beans;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

import de.nieblings.webapp.core.User;

import javax.ws.rs.core.SecurityContext;

import org.springframework.security.core.GrantedAuthority;

/**
 * Entity für eine Benutzer Datensatz.
 * 
 * @author $Author: oliver $
 * @version $Revision: 84539 $
 */
public class User extends Beans implements SecurityContext {

	public static final String USER_TYP = "USER";

	private static final long serialVersionUID = 3832626162173359411L;

	private String username;

	private String password;

	private String vorname;

	private String nachname;

	private String titel;

	private String schriftzeichen;

	private BigDecimal verfuegbarkeitProzent;

	private String telefon;

	private String telefax;

	private String email;

	private boolean enabled;

	private Date accountExpiresOn;

	private Date credentialsExpiresOn;

	private Date letzterLogin;

	private Integer fehlerhafteLogins;

	private Date letzterLoginVersuch;

	private Boolean externVerwaltet = false;

	private Boolean externAuthentifiziert = false;

	private Long foreignUserNumber;

	private String loginPassword;

	private String durchwahl;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public String getTitel() {
		return titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}

	public String getSchriftzeichen() {
		return schriftzeichen;
	}

	public void setSchriftzeichen(String schriftzeichen) {
		this.schriftzeichen = schriftzeichen;
	}

	public BigDecimal getVerfuegbarkeitProzent() {
		return verfuegbarkeitProzent;
	}

	public void setVerfuegbarkeitProzent(BigDecimal verfuegbarkeitProzent) {
		this.verfuegbarkeitProzent = verfuegbarkeitProzent;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getTelefax() {
		return telefax;
	}

	public void setTelefax(String telefax) {
		this.telefax = telefax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Date getAccountExpiresOn() {
		return accountExpiresOn;
	}

	public void setAccountExpiresOn(Date accountExpiresOn) {
		this.accountExpiresOn = accountExpiresOn;
	}

	public Date getCredentialsExpiresOn() {
		return credentialsExpiresOn;
	}

	public void setCredentialsExpiresOn(Date credentialsExpiresOn) {
		this.credentialsExpiresOn = credentialsExpiresOn;
	}

	public Date getLetzterLogin() {
		return letzterLogin;
	}

	public void setLetzterLogin(Date letzterLogin) {
		this.letzterLogin = letzterLogin;
	}

	public Integer getFehlerhafteLogins() {
		return fehlerhafteLogins;
	}

	public void setFehlerhafteLogins(Integer fehlerhafteLogins) {
		this.fehlerhafteLogins = fehlerhafteLogins;
	}

	public Date getLetzterLoginVersuch() {
		return letzterLoginVersuch;
	}

	public void setLetzterLoginVersuch(Date letzterLoginVersuch) {
		this.letzterLoginVersuch = letzterLoginVersuch;
	}

	public Boolean getExternVerwaltet() {
		return externVerwaltet;
	}

	public void setExternVerwaltet(Boolean externVerwaltet) {
		this.externVerwaltet = externVerwaltet;
	}

	public Boolean getExternAuthentifiziert() {
		return externAuthentifiziert;
	}

	public void setExternAuthentifiziert(Boolean externAuthentifiziert) {
		this.externAuthentifiziert = externAuthentifiziert;
	}

	public Long getForeignUserNumber() {
		return foreignUserNumber;
	}

	public void setForeignUserNumber(Long foreignUserNumber) {
		this.foreignUserNumber = foreignUserNumber;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public String getDurchwahl() {
		return durchwahl;
	}

	public void setDurchwahl(String durchwahl) {
		this.durchwahl = durchwahl;
	}

	public static String getUserTyp() {
		return USER_TYP;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getName() {
		return vorname + " " + nachname;
	}

	@Override
	public Principal getUserPrincipal() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isUserInRole(String role) {
		// TODO anpassen
		return true;
	}

	@Override
	public boolean isSecure() {
return true;
		
//ToDo RN		return false;
	}

	@Override
	public String getAuthenticationScheme() {
		
		return "Default";
	}

	public Collection<Rolle> getRollen() {
		// TODO Auto-generated method stub
		return null;
	}

	public Collection<? extends GrantedAuthority> getUserAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<UserGroup> getGroups() {
		// TODO Auto-generated method stub
		return null;
	}

	public Collection<GrantedAuthority> getAdditionalAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	public Mandant getDefaultMandant() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
