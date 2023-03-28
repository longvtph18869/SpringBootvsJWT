package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.dao.AccountDao;
import com.example.dao.RoleDao;
import com.example.entities.Account;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {
	@Autowired
	AccountDao accountDao;

	@Autowired
	RoleDao roleDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = accountDao.findByUsername(username);

		if (account == null) {
			System.out.println("account not found! " + username);
			throw new UsernameNotFoundException("Account " + username + " was not found in the database");
		}
		System.out.println("Found Account: " + account);

		List<String> roleNames = roleDao.getRoleNames(account.getId());

		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		if (roleNames != null) {
			for (String role : roleNames) {
				GrantedAuthority authority = new SimpleGrantedAuthority(role);
				grantedAuthorities.add(authority);
			}
		}
		UserDetails userDetails = (UserDetails) new User(account.getUsername(), account.getPassword(),
				grantedAuthorities);
		return userDetails;
	}
}