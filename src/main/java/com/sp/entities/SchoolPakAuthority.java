package com.sp.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class SchoolPakAuthority implements GrantedAuthority{

	private static final long serialVersionUID = 5803280349840734201L;
	@Id
	private AuthorityPK authorityPK;

	public String getAuthority() {
		return authorityPK.getAuthority();
	}
	
	public void setUsername(String username)
	{
		if(authorityPK == null)
		{
			authorityPK = new AuthorityPK();
		}
		authorityPK.setUsername(username);
	}
	
	public String getUsername()
	{
		return authorityPK.getUsername();
	}
	
	public void setAuthority(String authority)
	{
		if(authorityPK == null)
		{
			authorityPK = new AuthorityPK();
		}
		authorityPK.setAuthority(authority);
	}

}

@Embeddable
class AuthorityPK implements Serializable{

	private static final long serialVersionUID = -7657153988204628941L;
	
	private String authority;
	private String username;
	
	public String getAuthority() {
		return authority;
	}
	
	public void setAuthority(String authority) {
		this.authority = authority;
	}
		
	public void setUsername(String username)
	{
		this.username = username;
	}
	
	public String getUsername()
	{
		return username;
	}
}
