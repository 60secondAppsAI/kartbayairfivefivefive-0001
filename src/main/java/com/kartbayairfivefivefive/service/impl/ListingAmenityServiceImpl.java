package com.kartbayairfivefivefive.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;



import com.kartbayairfivefivefive.dao.GenericDAO;
import com.kartbayairfivefivefive.service.GenericService;
import com.kartbayairfivefivefive.service.impl.GenericServiceImpl;
import com.kartbayairfivefivefive.dao.ListingAmenityDAO;
import com.kartbayairfivefivefive.domain.ListingAmenity;
import com.kartbayairfivefivefive.dto.ListingAmenityDTO;
import com.kartbayairfivefivefive.dto.ListingAmenitySearchDTO;
import com.kartbayairfivefivefive.dto.ListingAmenityPageDTO;
import com.kartbayairfivefivefive.dto.ListingAmenityConvertCriteriaDTO;
import com.kartbayairfivefivefive.dto.common.RequestDTO;
import com.kartbayairfivefivefive.dto.common.ResultDTO;
import com.kartbayairfivefivefive.service.ListingAmenityService;
import com.kartbayairfivefivefive.util.ControllerUtils;





@Service
public class ListingAmenityServiceImpl extends GenericServiceImpl<ListingAmenity, Integer> implements ListingAmenityService {

    private final static Logger logger = LoggerFactory.getLogger(ListingAmenityServiceImpl.class);

	@Autowired
	ListingAmenityDAO listingAmenityDao;

	


	@Override
	public GenericDAO<ListingAmenity, Integer> getDAO() {
		return (GenericDAO<ListingAmenity, Integer>) listingAmenityDao;
	}
	
	public List<ListingAmenity> findAll () {
		List<ListingAmenity> listingAmenitys = listingAmenityDao.findAll();
		
		return listingAmenitys;	
		
	}

	public ResultDTO addListingAmenity(ListingAmenityDTO listingAmenityDTO, RequestDTO requestDTO) {

		ListingAmenity listingAmenity = new ListingAmenity();

		listingAmenity.setListingAmenityId(listingAmenityDTO.getListingAmenityId());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		listingAmenity = listingAmenityDao.save(listingAmenity);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<ListingAmenity> getAllListingAmenitys(Pageable pageable) {
		return listingAmenityDao.findAll(pageable);
	}

	public Page<ListingAmenity> getAllListingAmenitys(Specification<ListingAmenity> spec, Pageable pageable) {
		return listingAmenityDao.findAll(spec, pageable);
	}

	public ResponseEntity<ListingAmenityPageDTO> getListingAmenitys(ListingAmenitySearchDTO listingAmenitySearchDTO) {
	
			Integer listingAmenityId = listingAmenitySearchDTO.getListingAmenityId(); 
 			String sortBy = listingAmenitySearchDTO.getSortBy();
			String sortOrder = listingAmenitySearchDTO.getSortOrder();
			String searchQuery = listingAmenitySearchDTO.getSearchQuery();
			Integer page = listingAmenitySearchDTO.getPage();
			Integer size = listingAmenitySearchDTO.getSize();

	        Specification<ListingAmenity> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, listingAmenityId, "listingAmenityId"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

		));}
		
		Sort sort = Sort.unsorted();
		if (sortBy != null && !sortBy.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
			if (sortOrder.equalsIgnoreCase("asc")) {
				sort = Sort.by(sortBy).ascending();
			} else if (sortOrder.equalsIgnoreCase("desc")) {
				sort = Sort.by(sortBy).descending();
			}
		}
		Pageable pageable = PageRequest.of(page, size, sort);

		Page<ListingAmenity> listingAmenitys = this.getAllListingAmenitys(spec, pageable);
		
		//System.out.println(String.valueOf(listingAmenitys.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(listingAmenitys.getTotalPages()));
		
		List<ListingAmenity> listingAmenitysList = listingAmenitys.getContent();
		
		ListingAmenityConvertCriteriaDTO convertCriteria = new ListingAmenityConvertCriteriaDTO();
		List<ListingAmenityDTO> listingAmenityDTOs = this.convertListingAmenitysToListingAmenityDTOs(listingAmenitysList,convertCriteria);
		
		ListingAmenityPageDTO listingAmenityPageDTO = new ListingAmenityPageDTO();
		listingAmenityPageDTO.setListingAmenitys(listingAmenityDTOs);
		listingAmenityPageDTO.setTotalElements(listingAmenitys.getTotalElements());
		return ResponseEntity.ok(listingAmenityPageDTO);
	}

	public List<ListingAmenityDTO> convertListingAmenitysToListingAmenityDTOs(List<ListingAmenity> listingAmenitys, ListingAmenityConvertCriteriaDTO convertCriteria) {
		
		List<ListingAmenityDTO> listingAmenityDTOs = new ArrayList<ListingAmenityDTO>();
		
		for (ListingAmenity listingAmenity : listingAmenitys) {
			listingAmenityDTOs.add(convertListingAmenityToListingAmenityDTO(listingAmenity,convertCriteria));
		}
		
		return listingAmenityDTOs;

	}
	
	public ListingAmenityDTO convertListingAmenityToListingAmenityDTO(ListingAmenity listingAmenity, ListingAmenityConvertCriteriaDTO convertCriteria) {
		
		ListingAmenityDTO listingAmenityDTO = new ListingAmenityDTO();
		
		listingAmenityDTO.setListingAmenityId(listingAmenity.getListingAmenityId());

	

		
		return listingAmenityDTO;
	}

	public ResultDTO updateListingAmenity(ListingAmenityDTO listingAmenityDTO, RequestDTO requestDTO) {
		
		ListingAmenity listingAmenity = listingAmenityDao.getById(listingAmenityDTO.getListingAmenityId());

		listingAmenity.setListingAmenityId(ControllerUtils.setValue(listingAmenity.getListingAmenityId(), listingAmenityDTO.getListingAmenityId()));



        listingAmenity = listingAmenityDao.save(listingAmenity);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public ListingAmenityDTO getListingAmenityDTOById(Integer listingAmenityId) {
	
		ListingAmenity listingAmenity = listingAmenityDao.getById(listingAmenityId);
			
		
		ListingAmenityConvertCriteriaDTO convertCriteria = new ListingAmenityConvertCriteriaDTO();
		return(this.convertListingAmenityToListingAmenityDTO(listingAmenity,convertCriteria));
	}







}
