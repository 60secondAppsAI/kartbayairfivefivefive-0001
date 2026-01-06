package com.kartbayairfivefivefive.dao;

import java.util.List;

import com.kartbayairfivefivefive.dao.GenericDAO;
import com.kartbayairfivefivefive.domain.HostResponse;





public interface HostResponseDAO extends GenericDAO<HostResponse, Integer> {
  
	List<HostResponse> findAll();
	






}


