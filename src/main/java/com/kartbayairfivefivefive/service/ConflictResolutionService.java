package com.kartbayairfivefivefive.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.kartbayairfivefivefive.domain.ConflictResolution;
import com.kartbayairfivefivefive.dto.ConflictResolutionDTO;
import com.kartbayairfivefivefive.dto.ConflictResolutionSearchDTO;
import com.kartbayairfivefivefive.dto.ConflictResolutionPageDTO;
import com.kartbayairfivefivefive.dto.ConflictResolutionConvertCriteriaDTO;
import com.kartbayairfivefivefive.service.GenericService;
import com.kartbayairfivefivefive.dto.common.RequestDTO;
import com.kartbayairfivefivefive.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface ConflictResolutionService extends GenericService<ConflictResolution, Integer> {

	List<ConflictResolution> findAll();

	ResultDTO addConflictResolution(ConflictResolutionDTO conflictResolutionDTO, RequestDTO requestDTO);

	ResultDTO updateConflictResolution(ConflictResolutionDTO conflictResolutionDTO, RequestDTO requestDTO);

    Page<ConflictResolution> getAllConflictResolutions(Pageable pageable);

    Page<ConflictResolution> getAllConflictResolutions(Specification<ConflictResolution> spec, Pageable pageable);

	ResponseEntity<ConflictResolutionPageDTO> getConflictResolutions(ConflictResolutionSearchDTO conflictResolutionSearchDTO);
	
	List<ConflictResolutionDTO> convertConflictResolutionsToConflictResolutionDTOs(List<ConflictResolution> conflictResolutions, ConflictResolutionConvertCriteriaDTO convertCriteria);

	ConflictResolutionDTO getConflictResolutionDTOById(Integer conflictResolutionId);







}





