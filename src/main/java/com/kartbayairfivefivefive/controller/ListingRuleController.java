package com.kartbayairfivefivefive.controller;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.ArrayList;


import com.kartbayairfivefivefive.util.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.Date;

import com.kartbayairfivefivefive.domain.ListingRule;
import com.kartbayairfivefivefive.dto.ListingRuleDTO;
import com.kartbayairfivefivefive.dto.ListingRuleSearchDTO;
import com.kartbayairfivefivefive.dto.ListingRulePageDTO;
import com.kartbayairfivefivefive.service.ListingRuleService;
import com.kartbayairfivefivefive.dto.common.RequestDTO;
import com.kartbayairfivefivefive.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/listingRule")
@RestController
public class ListingRuleController {

	private final static Logger logger = LoggerFactory.getLogger(ListingRuleController.class);

	@Autowired
	ListingRuleService listingRuleService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<ListingRule> getAll() {

		List<ListingRule> listingRules = listingRuleService.findAll();
		
		return listingRules;	
	}

	@GetMapping(value = "/{listingRuleId}")
	@ResponseBody
	public ListingRuleDTO getListingRule(@PathVariable Integer listingRuleId) {
		
		return (listingRuleService.getListingRuleDTOById(listingRuleId));
	}

 	@RequestMapping(value = "/addListingRule", method = RequestMethod.POST)
	public ResponseEntity<?> addListingRule(@RequestBody ListingRuleDTO listingRuleDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = listingRuleService.addListingRule(listingRuleDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/listingRules")
	public ResponseEntity<ListingRulePageDTO> getListingRules(ListingRuleSearchDTO listingRuleSearchDTO) {
 
		return listingRuleService.getListingRules(listingRuleSearchDTO);
	}	

	@RequestMapping(value = "/updateListingRule", method = RequestMethod.POST)
	public ResponseEntity<?> updateListingRule(@RequestBody ListingRuleDTO listingRuleDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = listingRuleService.updateListingRule(listingRuleDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
