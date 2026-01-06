package com.kartbayairfivefivefive.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.kartbayairfivefivefive.domain.ListingRule;
import com.kartbayairfivefivefive.dto.ListingRuleDTO;
import com.kartbayairfivefivefive.dto.ListingRuleSearchDTO;
import com.kartbayairfivefivefive.dto.ListingRulePageDTO;
import com.kartbayairfivefivefive.dto.ListingRuleConvertCriteriaDTO;
import com.kartbayairfivefivefive.service.GenericService;
import com.kartbayairfivefivefive.dto.common.RequestDTO;
import com.kartbayairfivefivefive.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface ListingRuleService extends GenericService<ListingRule, Integer> {

	List<ListingRule> findAll();

	ResultDTO addListingRule(ListingRuleDTO listingRuleDTO, RequestDTO requestDTO);

	ResultDTO updateListingRule(ListingRuleDTO listingRuleDTO, RequestDTO requestDTO);

    Page<ListingRule> getAllListingRules(Pageable pageable);

    Page<ListingRule> getAllListingRules(Specification<ListingRule> spec, Pageable pageable);

	ResponseEntity<ListingRulePageDTO> getListingRules(ListingRuleSearchDTO listingRuleSearchDTO);
	
	List<ListingRuleDTO> convertListingRulesToListingRuleDTOs(List<ListingRule> listingRules, ListingRuleConvertCriteriaDTO convertCriteria);

	ListingRuleDTO getListingRuleDTOById(Integer listingRuleId);







}





