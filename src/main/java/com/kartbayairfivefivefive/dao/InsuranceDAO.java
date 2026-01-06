package com.kartbayairfivefivefive.dao;

import java.util.List;

import com.kartbayairfivefivefive.dao.GenericDAO;
import com.kartbayairfivefivefive.domain.Insurance;





public interface InsuranceDAO extends GenericDAO<Insurance, Integer> {
  
	List<Insurance> findAll();
	






}


