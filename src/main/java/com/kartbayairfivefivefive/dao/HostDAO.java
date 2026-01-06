package com.kartbayairfivefivefive.dao;

import java.util.List;

import com.kartbayairfivefivefive.dao.GenericDAO;
import com.kartbayairfivefivefive.domain.Host;





public interface HostDAO extends GenericDAO<Host, Integer> {
  
	List<Host> findAll();
	






}


