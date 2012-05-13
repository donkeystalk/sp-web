package com.sp.repos;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.sp.entities.SchoolPakAuthority;
import com.sp.entities.SchoolPakUserDetails;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:config/applicationContext.xml")
@Transactional
@SuppressWarnings("unchecked")
public class IntegrationTestHibernateUserRepository {

	@Autowired
	private HibernateUserRepository repo;
	
	@Test
	public void testInject()
	{
		assertNotNull(repo);
	}
	
	@Test
	@Rollback
	public void testCreateUserDetails()
	{
		SchoolPakUserDetails ud = createUserDetails();
		repo.save(ud);
		UserDetails newUd = repo.loadUserByUsername("testuser");
		assertNotNull(newUd);
		assertEquals(newUd.getUsername(), "testuser");
		assertEquals(newUd.getPassword(), "test123");
		assertEquals(newUd.getAuthorities().size(), 2);
	}
	
	@Test
	@Rollback
	public void testDeleteUserDetails()
	{
		SchoolPakUserDetails ud = createUserDetails();
		repo.save(ud);
		ud = null;
		ud = (SchoolPakUserDetails)repo.loadUserByUsername("testuser");
		assertNotNull(ud);
		assertEquals(ud.getUsername(), "testuser");
		repo.delete(ud);
		ud = null;
		ud = (SchoolPakUserDetails)repo.loadUserByUsername("testuser");
		assertNull(ud);
	}
	
	@Test
	@Rollback
	public void testUpdateAuthority()
	{
		SchoolPakUserDetails ud = createUserDetails();
		Collection<GrantedAuthority> auths = ud.getAuthorities();
		boolean first = true;
		GrantedAuthority changed = null;
		for(GrantedAuthority auth : auths)
		{
			if(first)
			{
				first = false;
				changed = auth;
			}
			else
			{
				break;
			}
		}
		repo.save(ud);
		((SchoolPakAuthority)changed).setAuthority("blarg");
		repo.save(changed);
		ud = null;
		ud = (SchoolPakUserDetails)repo.loadUserByUsername("testuser");
		boolean test = false;
		for(GrantedAuthority auth : ud.getAuthorities())
		{
			if(auth.getAuthority().contentEquals("blarg"))
			{
				test = true;
			}
		}
		assertTrue(test);
	}

	private SchoolPakUserDetails createUserDetails() {
		SchoolPakUserDetails ud = new SchoolPakUserDetails();
		ud.setUsername("testuser");
		ud.setPassword("test123");
		List<SchoolPakAuthority> authorities = new ArrayList<SchoolPakAuthority>();
		SchoolPakAuthority spa = new SchoolPakAuthority();
		spa.setAuthority("User");
		spa.setUsername(ud.getUsername());
		authorities.add(spa);
		SchoolPakAuthority spa2 = new SchoolPakAuthority();
		spa2.setAuthority("Admin");
		spa2.setUsername(ud.getUsername());
		authorities.add(spa2);
		ud.setAuthorities(authorities);
		return ud;
	}
	
}
