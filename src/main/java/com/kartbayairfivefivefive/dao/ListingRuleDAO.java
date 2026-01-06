package com.kartbayairfivefivefive.dao;

import java.util.List;

import com.kartbayairfivefivefive.dao.GenericDAO;
import com.kartbayairfivefivefive.domain.ListingRule;





public interface ListingRuleDAO extends GenericDAO<ListingRule, Integer> {
  
	List<ListingRule> findAll();
	






}


