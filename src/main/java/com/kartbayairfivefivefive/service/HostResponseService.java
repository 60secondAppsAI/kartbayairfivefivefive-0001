package com.kartbayairfivefivefive.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.kartbayairfivefivefive.domain.HostResponse;
import com.kartbayairfivefivefive.dto.HostResponseDTO;
import com.kartbayairfivefivefive.dto.HostResponseSearchDTO;
import com.kartbayairfivefivefive.dto.HostResponsePageDTO;
import com.kartbayairfivefivefive.dto.HostResponseConvertCriteriaDTO;
import com.kartbayairfivefivefive.service.GenericService;
import com.kartbayairfivefivefive.dto.common.RequestDTO;
import com.kartbayairfivefivefive.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface HostResponseService extends GenericService<HostResponse, Integer> {

	List<HostResponse> findAll();

	ResultDTO addHostResponse(HostResponseDTO hostResponseDTO, RequestDTO requestDTO);

	ResultDTO updateHostResponse(HostResponseDTO hostResponseDTO, RequestDTO requestDTO);

    Page<HostResponse> getAllHostResponses(Pageable pageable);

    Page<HostResponse> getAllHostResponses(Specification<HostResponse> spec, Pageable pageable);

	ResponseEntity<HostResponsePageDTO> getHostResponses(HostResponseSearchDTO hostResponseSearchDTO);
	
	List<HostResponseDTO> convertHostResponsesToHostResponseDTOs(List<HostResponse> hostResponses, HostResponseConvertCriteriaDTO convertCriteria);

	HostResponseDTO getHostResponseDTOById(Integer hostResponseId);







}





