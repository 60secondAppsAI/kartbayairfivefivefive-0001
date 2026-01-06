package com.kartbayairfivefivefive.dao;

import java.util.List;

import com.kartbayairfivefivefive.dao.GenericDAO;
import com.kartbayairfivefivefive.domain.Discount;





public interface DiscountDAO extends GenericDAO<Discount, Integer> {
  
	List<Discount> findAll();
	






}


