package com.kartbayairfivefivefive.dao;

import java.util.List;

import com.kartbayairfivefivefive.dao.GenericDAO;
import com.kartbayairfivefivefive.domain.Event;





public interface EventDAO extends GenericDAO<Event, Integer> {
  
	List<Event> findAll();
	






}


