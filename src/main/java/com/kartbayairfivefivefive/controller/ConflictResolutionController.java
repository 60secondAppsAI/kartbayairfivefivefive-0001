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

import com.kartbayairfivefivefive.domain.ConflictResolution;
import com.kartbayairfivefivefive.dto.ConflictResolutionDTO;
import com.kartbayairfivefivefive.dto.ConflictResolutionSearchDTO;
import com.kartbayairfivefivefive.dto.ConflictResolutionPageDTO;
import com.kartbayairfivefivefive.service.ConflictResolutionService;
import com.kartbayairfivefivefive.dto.common.RequestDTO;
import com.kartbayairfivefivefive.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/conflictResolution")
@RestController
public class ConflictResolutionController {

	private final static Logger logger = LoggerFactory.getLogger(ConflictResolutionController.class);

	@Autowired
	ConflictResolutionService conflictResolutionService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<ConflictResolution> getAll() {

		List<ConflictResolution> conflictResolutions = conflictResolutionService.findAll();
		
		return conflictResolutions;	
	}

	@GetMapping(value = "/{conflictResolutionId}")
	@ResponseBody
	public ConflictResolutionDTO getConflictResolution(@PathVariable Integer conflictResolutionId) {
		
		return (conflictResolutionService.getConflictResolutionDTOById(conflictResolutionId));
	}

 	@RequestMapping(value = "/addConflictResolution", method = RequestMethod.POST)
	public ResponseEntity<?> addConflictResolution(@RequestBody ConflictResolutionDTO conflictResolutionDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = conflictResolutionService.addConflictResolution(conflictResolutionDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/conflictResolutions")
	public ResponseEntity<ConflictResolutionPageDTO> getConflictResolutions(ConflictResolutionSearchDTO conflictResolutionSearchDTO) {
 
		return conflictResolutionService.getConflictResolutions(conflictResolutionSearchDTO);
	}	

	@RequestMapping(value = "/updateConflictResolution", method = RequestMethod.POST)
	public ResponseEntity<?> updateConflictResolution(@RequestBody ConflictResolutionDTO conflictResolutionDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = conflictResolutionService.updateConflictResolution(conflictResolutionDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
