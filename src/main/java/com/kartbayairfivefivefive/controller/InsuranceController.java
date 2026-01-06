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

import com.kartbayairfivefivefive.domain.Insurance;
import com.kartbayairfivefivefive.dto.InsuranceDTO;
import com.kartbayairfivefivefive.dto.InsuranceSearchDTO;
import com.kartbayairfivefivefive.dto.InsurancePageDTO;
import com.kartbayairfivefivefive.service.InsuranceService;
import com.kartbayairfivefivefive.dto.common.RequestDTO;
import com.kartbayairfivefivefive.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/insurance")
@RestController
public class InsuranceController {

	private final static Logger logger = LoggerFactory.getLogger(InsuranceController.class);

	@Autowired
	InsuranceService insuranceService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Insurance> getAll() {

		List<Insurance> insurances = insuranceService.findAll();
		
		return insurances;	
	}

	@GetMapping(value = "/{insuranceId}")
	@ResponseBody
	public InsuranceDTO getInsurance(@PathVariable Integer insuranceId) {
		
		return (insuranceService.getInsuranceDTOById(insuranceId));
	}

 	@RequestMapping(value = "/addInsurance", method = RequestMethod.POST)
	public ResponseEntity<?> addInsurance(@RequestBody InsuranceDTO insuranceDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = insuranceService.addInsurance(insuranceDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/insurances")
	public ResponseEntity<InsurancePageDTO> getInsurances(InsuranceSearchDTO insuranceSearchDTO) {
 
		return insuranceService.getInsurances(insuranceSearchDTO);
	}	

	@RequestMapping(value = "/updateInsurance", method = RequestMethod.POST)
	public ResponseEntity<?> updateInsurance(@RequestBody InsuranceDTO insuranceDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = insuranceService.updateInsurance(insuranceDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
