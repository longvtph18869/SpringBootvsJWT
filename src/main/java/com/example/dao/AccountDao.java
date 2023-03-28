package com.example.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.entities.Account;


@Mapper
public interface AccountDao {
	public Account findByUsername(String username);
	
	public int insert(Account account);
}
