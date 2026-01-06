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

import com.kartbayairfivefivefive.domain.CleaningService;
import com.kartbayairfivefivefive.dto.CleaningServiceDTO;
import com.kartbayairfivefivefive.dto.CleaningServiceSearchDTO;
import com.kartbayairfivefivefive.dto.CleaningServicePageDTO;
import com.kartbayairfivefivefive.service.CleaningServiceService;
import com.kartbayairfivefivefive.dto.common.RequestDTO;
import com.kartbayairfivefivefive.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/cleaningService")
@RestController
public class CleaningServiceController {

	private final static Logger logger = LoggerFactory.getLogger(CleaningServiceController.class);

	@Autowired
	CleaningServiceService cleaningServiceService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<CleaningService> getAll() {

		List<CleaningService> cleaningServices = cleaningServiceService.findAll();
		
		return cleaningServices;	
	}

	@GetMapping(value = "/{cleaningServiceId}")
	@ResponseBody
	public CleaningServiceDTO getCleaningService(@PathVariable Integer cleaningServiceId) {
		
		return (cleaningServiceService.getCleaningServiceDTOById(cleaningServiceId));
	}

 	@RequestMapping(value = "/addCleaningService", method = RequestMethod.POST)
	public ResponseEntity<?> addCleaningService(@RequestBody CleaningServiceDTO cleaningServiceDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = cleaningServiceService.addCleaningService(cleaningServiceDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/cleaningServices")
	public ResponseEntity<CleaningServicePageDTO> getCleaningServices(CleaningServiceSearchDTO cleaningServiceSearchDTO) {
 
		return cleaningServiceService.getCleaningServices(cleaningServiceSearchDTO);
	}	

	@RequestMapping(value = "/updateCleaningService", method = RequestMethod.POST)
	public ResponseEntity<?> updateCleaningService(@RequestBody CleaningServiceDTO cleaningServiceDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = cleaningServiceService.updateCleaningService(cleaningServiceDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
