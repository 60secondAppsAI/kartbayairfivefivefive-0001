package com.kartbayairfivefivefive.dao;

import java.util.List;

import com.kartbayairfivefivefive.dao.GenericDAO;
import com.kartbayairfivefivefive.domain.ListingAmenity;





public interface ListingAmenityDAO extends GenericDAO<ListingAmenity, Integer> {
  
	List<ListingAmenity> findAll();
	






}


