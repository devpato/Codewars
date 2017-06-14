package com.csx.webview.view.user;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.text.WordUtils;

import com.csx.enterprise.webframework.security.LDAPUser;
import com.csx.enterprise.webframework.util.Log;
import com.csx.jsf.extension.CSXFacesUtil;

/**
 * CSX LDAP Authenticated User class.
 */
@ManagedBean
@SessionScoped
public class AuthenticatedUser implements Serializable, HttpSessionBindingListener {

	/**
	 * Serial Version UID.
	 */
	private static final long serialVersionUID = -6816169686709362064L;

	/**
	 * CSX authenticated user.
	 */
	private LDAPUser user = null;

	public enum UserGroup {
		POWER_USER("gateway-acsxopen");

		public final String description;

		UserGroup(String description) {
			this.description = description;
		}
	}

	/**
	 * Constructs an instance of this class.
	 */
	public AuthenticatedUser() {
		// Store ldap user on first successful retrieval.
		getUser();
	}

	/**
	 * Get the user.
	 * 
	 * @return LDAPUser
	 */
	private LDAPUser getUser() {
		if (user == null) {
			user = CSXFacesUtil.getLDAPUser();
		}
		return user;
	}

	/**
	 * Get the user's id.
	 * 
	 * @return String of User ID
	 */
	public String getUserId() {
		return (getUser() != null) ? StringUtils.upperCase(StringUtils.defaultString(getUser().getUserid())) : "";
	}

	/**
	 * Get the user's first name.
	 * 
	 * @return String of First Name
	 */
	public String getFirstName() {
		return (getUser() != null) ? WordUtils.capitalizeFully(StringUtils.defaultString(getUser().getFirstName()))
				: "";
	}

	/**
	 * Get the user's last name.
	 * 
	 * @return String of Last Name
	 */
	public String getLastName() {
		return (getUser() != null) ? WordUtils.capitalizeFully(StringUtils.defaultString(getUser().getLastName())) : "";
	}

	/**
	 * Get the authenticated user's full name.
	 * 
	 * @return User's full name, if it exists, empty string otherwise
	 */
	public String getFullName() {
		String name = String.format("%s %s", getFirstName(), getLastName());
		return name.trim();
	}

	/**
	 * Get the user's email address.
	 * 
	 * 
	 * @return String of Email address
	 */
	public String getEmailAddress() {
		return (getUser() != null) ? StringUtils.lowerCase(StringUtils.defaultString(getUser().getEmailAddress())) : "";
	}

	/**
	 * Method to get the user's ldap membership groups.
	 * 
	 * @return Map of groups user is a member of
	 */
	public String getGroups() {
		return (getUser() != null) ? getUser().getAllGroups().toString() : null;
	}

	/*
	 * Methos to check if the user belogs to any of this groups to have access
	 * to the App.
	 */
	public Boolean getValidateAccess() {

		if (getGroups().matches(
				"^.*?(MZ-ENGPMT-MGR|MZ-ENGPMT-FRMAN|MZ-ENGPMT-BPADM|MZ-ENGSIG-ADE|MZ-ENGSIG-AFADM|MZ-ENGSIG-ASSTCHFENG|"
						+ "MZ-ENGSIG-BPADM|MZ-ENGSIG-CHFENG|MZ-ENGSIG-CONST|MZ-ENGSIG-DIVENG|MZ-ENGSIG-MGR|MZ-ENGSIG-NEWPROJ|MZ-ENGSIG-SIGMEN"
						+ "MZ-ENGSIG-SUPPTSTF|MZ-ENGSIG-SVNRPR|MZ-ENGSIG-VEND).*$")) {
			return true;
		} else {
			return false;
		}

	}

	/*
	 * Message for user without access to the application.
	 */
	private void addInfoMessage() {
		FacesContext.getCurrentInstance().addMessage("infoMessage",
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "This is an info message"));
	}

	/**
	 * @return the access level
	 */
	public String getAccessLevel() {
		String accessLevel = null;
		if (isPowerUser()) {
			accessLevel = "Admin";
		}

		return accessLevel;
	}

	/**
	 * Determines if user is in the Napa User group.
	 * 
	 * @return True/False
	 */
	public boolean isPowerUser() {
		return isInGroup(UserGroup.POWER_USER.description);
	}

	/**
	 * Method to validate ldap group membership.
	 * 
	 * @param group
	 *            UserGroup enumeration value
	 * @return
	 */
	public boolean isInGroup(String group) {
		return user.hasToken(group);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpSessionBindingListener#valueBound(javax.servlet
	 * .http.HttpSessionBindingEvent)
	 */
	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		Log.logInfo("AUDIT", String.format("%S session %s created.", this.getUserId(), event.getSession().getId()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpSessionBindingListener#valueUnbound(javax.servlet
	 * .http.HttpSessionBindingEvent)
	 */
	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		Log.logInfo("AUDIT", String.format("%S session %s timeout.", this.getUserId(), event.getSession().getId()));
	}

	/**
	 * Get reflection of object.
	 * 
	 * @return String representation of object
	 */
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}