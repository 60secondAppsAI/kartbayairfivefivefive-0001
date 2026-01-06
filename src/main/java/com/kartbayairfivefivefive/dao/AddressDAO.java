package com.kartbayairfivefivefive.dao;

import java.util.List;

import com.kartbayairfivefivefive.dao.GenericDAO;
import com.kartbayairfivefivefive.domain.Address;





public interface AddressDAO extends GenericDAO<Address, Integer> {
  
	List<Address> findAll();
	






}


