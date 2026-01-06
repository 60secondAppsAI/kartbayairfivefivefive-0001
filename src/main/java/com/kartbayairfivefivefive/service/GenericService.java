package com.kartbayairfivefivefive.service;

import com.kartbayairfivefivefive.dao.GenericDAO;

public interface GenericService<T, ID> {

    abstract GenericDAO<T, ID> getDAO();

    T getById(Integer id) ;

}