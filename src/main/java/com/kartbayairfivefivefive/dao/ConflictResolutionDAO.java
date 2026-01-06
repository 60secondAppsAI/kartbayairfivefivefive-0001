package com.kartbayairfivefivefive.dao;

import java.util.List;

import com.kartbayairfivefivefive.dao.GenericDAO;
import com.kartbayairfivefivefive.domain.ConflictResolution;





public interface ConflictResolutionDAO extends GenericDAO<ConflictResolution, Integer> {
  
	List<ConflictResolution> findAll();
	






}


