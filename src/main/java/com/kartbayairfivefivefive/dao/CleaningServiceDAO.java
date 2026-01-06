package com.kartbayairfivefivefive.dao;

import java.util.List;

import com.kartbayairfivefivefive.dao.GenericDAO;
import com.kartbayairfivefivefive.domain.CleaningService;





public interface CleaningServiceDAO extends GenericDAO<CleaningService, Integer> {
  
	List<CleaningService> findAll();
	






}


