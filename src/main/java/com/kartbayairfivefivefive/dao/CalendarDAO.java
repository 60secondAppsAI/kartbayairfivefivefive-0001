package com.kartbayairfivefivefive.dao;

import java.util.List;

import com.kartbayairfivefivefive.dao.GenericDAO;
import com.kartbayairfivefivefive.domain.Calendar;





public interface CalendarDAO extends GenericDAO<Calendar, Integer> {
  
	List<Calendar> findAll();
	






}


