package com.kartbayairfivefivefive.dao;

import java.util.List;

import com.kartbayairfivefivefive.dao.GenericDAO;
import com.kartbayairfivefivefive.domain.Image;





public interface ImageDAO extends GenericDAO<Image, Integer> {
  
	List<Image> findAll();
	






}


