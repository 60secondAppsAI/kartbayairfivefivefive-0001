package com.kartbayairfivefivefive.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.kartbayairfivefivefive.domain.CleaningService;
import com.kartbayairfivefivefive.dto.CleaningServiceDTO;
import com.kartbayairfivefivefive.dto.CleaningServiceSearchDTO;
import com.kartbayairfivefivefive.dto.CleaningServicePageDTO;
import com.kartbayairfivefivefive.dto.CleaningServiceConvertCriteriaDTO;
import com.kartbayairfivefivefive.service.GenericService;
import com.kartbayairfivefivefive.dto.common.RequestDTO;
import com.kartbayairfivefivefive.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface CleaningServiceService extends GenericService<CleaningService, Integer> {

	List<CleaningService> findAll();

	ResultDTO addCleaningService(CleaningServiceDTO cleaningServiceDTO, RequestDTO requestDTO);

	ResultDTO updateCleaningService(CleaningServiceDTO cleaningServiceDTO, RequestDTO requestDTO);

    Page<CleaningService> getAllCleaningServices(Pageable pageable);

    Page<CleaningService> getAllCleaningServices(Specification<CleaningService> spec, Pageable pageable);

	ResponseEntity<CleaningServicePageDTO> getCleaningServices(CleaningServiceSearchDTO cleaningServiceSearchDTO);
	
	List<CleaningServiceDTO> convertCleaningServicesToCleaningServiceDTOs(List<CleaningService> cleaningServices, CleaningServiceConvertCriteriaDTO convertCriteria);

	CleaningServiceDTO getCleaningServiceDTOById(Integer cleaningServiceId);







}





