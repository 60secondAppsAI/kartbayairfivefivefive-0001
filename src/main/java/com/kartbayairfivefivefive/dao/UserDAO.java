package com.kartbayairfivefivefive.dao;

import java.util.List;

import com.kartbayairfivefivefive.dao.GenericDAO;
import com.kartbayairfivefivefive.domain.User;

import java.util.Optional;




public interface UserDAO extends GenericDAO<User, Integer> {
  
	List<User> findAll();
	






}


