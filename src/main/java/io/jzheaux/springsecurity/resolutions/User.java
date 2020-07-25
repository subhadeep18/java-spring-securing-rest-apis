package io.jzheaux.springsecurity.resolutions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name="users")
public class User implements Serializable {
	
	@Id
	UUID id;
	
	@Column
	String username;
	
	@Column
	String password;
	
	@Column
	boolean enabled = true;
	
	 @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	    Collection<UserAuthority> userAuthorities = new ArrayList<>();
	
	User() {	}
	
	public User(String username, String password) {
		this.id = UUID.randomUUID();
		this.username = username;
		this.password = password;
	}

	public User(User user) {
	    this.id = user.id;
	    this.username = user.username;
	    this.password = user.password;
	    this.enabled = user.enabled;
	    this.userAuthorities = user.userAuthorities;
	}
	
	 public Collection<UserAuthority> getUserAuthorities() {
	    return Collections.unmodifiableCollection(this.userAuthorities);
	}

	 public void grantAuthority(String authority) {
	    UserAuthority userAuthority = new UserAuthority(this, authority);
	    this.userAuthorities.add(userAuthority);
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

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

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	
}

