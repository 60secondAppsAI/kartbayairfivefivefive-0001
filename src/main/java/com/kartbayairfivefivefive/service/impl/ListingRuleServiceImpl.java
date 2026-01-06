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
import com.kartbayairfivefivefive.dao.ListingRuleDAO;
import com.kartbayairfivefivefive.domain.ListingRule;
import com.kartbayairfivefivefive.dto.ListingRuleDTO;
import com.kartbayairfivefivefive.dto.ListingRuleSearchDTO;
import com.kartbayairfivefivefive.dto.ListingRulePageDTO;
import com.kartbayairfivefivefive.dto.ListingRuleConvertCriteriaDTO;
import com.kartbayairfivefivefive.dto.common.RequestDTO;
import com.kartbayairfivefivefive.dto.common.ResultDTO;
import com.kartbayairfivefivefive.service.ListingRuleService;
import com.kartbayairfivefivefive.util.ControllerUtils;





@Service
public class ListingRuleServiceImpl extends GenericServiceImpl<ListingRule, Integer> implements ListingRuleService {

    private final static Logger logger = LoggerFactory.getLogger(ListingRuleServiceImpl.class);

	@Autowired
	ListingRuleDAO listingRuleDao;

	


	@Override
	public GenericDAO<ListingRule, Integer> getDAO() {
		return (GenericDAO<ListingRule, Integer>) listingRuleDao;
	}
	
	public List<ListingRule> findAll () {
		List<ListingRule> listingRules = listingRuleDao.findAll();
		
		return listingRules;	
		
	}

	public ResultDTO addListingRule(ListingRuleDTO listingRuleDTO, RequestDTO requestDTO) {

		ListingRule listingRule = new ListingRule();

		listingRule.setListingRuleId(listingRuleDTO.getListingRuleId());


		listingRule.setRuleDescription(listingRuleDTO.getRuleDescription());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		listingRule = listingRuleDao.save(listingRule);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<ListingRule> getAllListingRules(Pageable pageable) {
		return listingRuleDao.findAll(pageable);
	}

	public Page<ListingRule> getAllListingRules(Specification<ListingRule> spec, Pageable pageable) {
		return listingRuleDao.findAll(spec, pageable);
	}

	public ResponseEntity<ListingRulePageDTO> getListingRules(ListingRuleSearchDTO listingRuleSearchDTO) {
	
			Integer listingRuleId = listingRuleSearchDTO.getListingRuleId(); 
 			String ruleDescription = listingRuleSearchDTO.getRuleDescription(); 
 			String sortBy = listingRuleSearchDTO.getSortBy();
			String sortOrder = listingRuleSearchDTO.getSortOrder();
			String searchQuery = listingRuleSearchDTO.getSearchQuery();
			Integer page = listingRuleSearchDTO.getPage();
			Integer size = listingRuleSearchDTO.getSize();

	        Specification<ListingRule> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, listingRuleId, "listingRuleId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, ruleDescription, "ruleDescription"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("ruleDescription")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<ListingRule> listingRules = this.getAllListingRules(spec, pageable);
		
		//System.out.println(String.valueOf(listingRules.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(listingRules.getTotalPages()));
		
		List<ListingRule> listingRulesList = listingRules.getContent();
		
		ListingRuleConvertCriteriaDTO convertCriteria = new ListingRuleConvertCriteriaDTO();
		List<ListingRuleDTO> listingRuleDTOs = this.convertListingRulesToListingRuleDTOs(listingRulesList,convertCriteria);
		
		ListingRulePageDTO listingRulePageDTO = new ListingRulePageDTO();
		listingRulePageDTO.setListingRules(listingRuleDTOs);
		listingRulePageDTO.setTotalElements(listingRules.getTotalElements());
		return ResponseEntity.ok(listingRulePageDTO);
	}

	public List<ListingRuleDTO> convertListingRulesToListingRuleDTOs(List<ListingRule> listingRules, ListingRuleConvertCriteriaDTO convertCriteria) {
		
		List<ListingRuleDTO> listingRuleDTOs = new ArrayList<ListingRuleDTO>();
		
		for (ListingRule listingRule : listingRules) {
			listingRuleDTOs.add(convertListingRuleToListingRuleDTO(listingRule,convertCriteria));
		}
		
		return listingRuleDTOs;

	}
	
	public ListingRuleDTO convertListingRuleToListingRuleDTO(ListingRule listingRule, ListingRuleConvertCriteriaDTO convertCriteria) {
		
		ListingRuleDTO listingRuleDTO = new ListingRuleDTO();
		
		listingRuleDTO.setListingRuleId(listingRule.getListingRuleId());

	
		listingRuleDTO.setRuleDescription(listingRule.getRuleDescription());

	

		
		return listingRuleDTO;
	}

	public ResultDTO updateListingRule(ListingRuleDTO listingRuleDTO, RequestDTO requestDTO) {
		
		ListingRule listingRule = listingRuleDao.getById(listingRuleDTO.getListingRuleId());

		listingRule.setListingRuleId(ControllerUtils.setValue(listingRule.getListingRuleId(), listingRuleDTO.getListingRuleId()));

		listingRule.setRuleDescription(ControllerUtils.setValue(listingRule.getRuleDescription(), listingRuleDTO.getRuleDescription()));



        listingRule = listingRuleDao.save(listingRule);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public ListingRuleDTO getListingRuleDTOById(Integer listingRuleId) {
	
		ListingRule listingRule = listingRuleDao.getById(listingRuleId);
			
		
		ListingRuleConvertCriteriaDTO convertCriteria = new ListingRuleConvertCriteriaDTO();
		return(this.convertListingRuleToListingRuleDTO(listingRule,convertCriteria));
	}







}
