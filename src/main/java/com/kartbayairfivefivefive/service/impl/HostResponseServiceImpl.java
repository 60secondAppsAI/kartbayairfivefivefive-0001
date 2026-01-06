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
import com.kartbayairfivefivefive.dao.HostResponseDAO;
import com.kartbayairfivefivefive.domain.HostResponse;
import com.kartbayairfivefivefive.dto.HostResponseDTO;
import com.kartbayairfivefivefive.dto.HostResponseSearchDTO;
import com.kartbayairfivefivefive.dto.HostResponsePageDTO;
import com.kartbayairfivefivefive.dto.HostResponseConvertCriteriaDTO;
import com.kartbayairfivefivefive.dto.common.RequestDTO;
import com.kartbayairfivefivefive.dto.common.ResultDTO;
import com.kartbayairfivefivefive.service.HostResponseService;
import com.kartbayairfivefivefive.util.ControllerUtils;





@Service
public class HostResponseServiceImpl extends GenericServiceImpl<HostResponse, Integer> implements HostResponseService {

    private final static Logger logger = LoggerFactory.getLogger(HostResponseServiceImpl.class);

	@Autowired
	HostResponseDAO hostResponseDao;

	


	@Override
	public GenericDAO<HostResponse, Integer> getDAO() {
		return (GenericDAO<HostResponse, Integer>) hostResponseDao;
	}
	
	public List<HostResponse> findAll () {
		List<HostResponse> hostResponses = hostResponseDao.findAll();
		
		return hostResponses;	
		
	}

	public ResultDTO addHostResponse(HostResponseDTO hostResponseDTO, RequestDTO requestDTO) {

		HostResponse hostResponse = new HostResponse();

		hostResponse.setHostResponseId(hostResponseDTO.getHostResponseId());


		hostResponse.setResponseMessage(hostResponseDTO.getResponseMessage());


		hostResponse.setResponseDate(hostResponseDTO.getResponseDate());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		hostResponse = hostResponseDao.save(hostResponse);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<HostResponse> getAllHostResponses(Pageable pageable) {
		return hostResponseDao.findAll(pageable);
	}

	public Page<HostResponse> getAllHostResponses(Specification<HostResponse> spec, Pageable pageable) {
		return hostResponseDao.findAll(spec, pageable);
	}

	public ResponseEntity<HostResponsePageDTO> getHostResponses(HostResponseSearchDTO hostResponseSearchDTO) {
	
			Integer hostResponseId = hostResponseSearchDTO.getHostResponseId(); 
 			String responseMessage = hostResponseSearchDTO.getResponseMessage(); 
   			String sortBy = hostResponseSearchDTO.getSortBy();
			String sortOrder = hostResponseSearchDTO.getSortOrder();
			String searchQuery = hostResponseSearchDTO.getSearchQuery();
			Integer page = hostResponseSearchDTO.getPage();
			Integer size = hostResponseSearchDTO.getSize();

	        Specification<HostResponse> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, hostResponseId, "hostResponseId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, responseMessage, "responseMessage"); 
			
 			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("responseMessage")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<HostResponse> hostResponses = this.getAllHostResponses(spec, pageable);
		
		//System.out.println(String.valueOf(hostResponses.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(hostResponses.getTotalPages()));
		
		List<HostResponse> hostResponsesList = hostResponses.getContent();
		
		HostResponseConvertCriteriaDTO convertCriteria = new HostResponseConvertCriteriaDTO();
		List<HostResponseDTO> hostResponseDTOs = this.convertHostResponsesToHostResponseDTOs(hostResponsesList,convertCriteria);
		
		HostResponsePageDTO hostResponsePageDTO = new HostResponsePageDTO();
		hostResponsePageDTO.setHostResponses(hostResponseDTOs);
		hostResponsePageDTO.setTotalElements(hostResponses.getTotalElements());
		return ResponseEntity.ok(hostResponsePageDTO);
	}

	public List<HostResponseDTO> convertHostResponsesToHostResponseDTOs(List<HostResponse> hostResponses, HostResponseConvertCriteriaDTO convertCriteria) {
		
		List<HostResponseDTO> hostResponseDTOs = new ArrayList<HostResponseDTO>();
		
		for (HostResponse hostResponse : hostResponses) {
			hostResponseDTOs.add(convertHostResponseToHostResponseDTO(hostResponse,convertCriteria));
		}
		
		return hostResponseDTOs;

	}
	
	public HostResponseDTO convertHostResponseToHostResponseDTO(HostResponse hostResponse, HostResponseConvertCriteriaDTO convertCriteria) {
		
		HostResponseDTO hostResponseDTO = new HostResponseDTO();
		
		hostResponseDTO.setHostResponseId(hostResponse.getHostResponseId());

	
		hostResponseDTO.setResponseMessage(hostResponse.getResponseMessage());

	
		hostResponseDTO.setResponseDate(hostResponse.getResponseDate());

	

		
		return hostResponseDTO;
	}

	public ResultDTO updateHostResponse(HostResponseDTO hostResponseDTO, RequestDTO requestDTO) {
		
		HostResponse hostResponse = hostResponseDao.getById(hostResponseDTO.getHostResponseId());

		hostResponse.setHostResponseId(ControllerUtils.setValue(hostResponse.getHostResponseId(), hostResponseDTO.getHostResponseId()));

		hostResponse.setResponseMessage(ControllerUtils.setValue(hostResponse.getResponseMessage(), hostResponseDTO.getResponseMessage()));

		hostResponse.setResponseDate(ControllerUtils.setValue(hostResponse.getResponseDate(), hostResponseDTO.getResponseDate()));



        hostResponse = hostResponseDao.save(hostResponse);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public HostResponseDTO getHostResponseDTOById(Integer hostResponseId) {
	
		HostResponse hostResponse = hostResponseDao.getById(hostResponseId);
			
		
		HostResponseConvertCriteriaDTO convertCriteria = new HostResponseConvertCriteriaDTO();
		return(this.convertHostResponseToHostResponseDTO(hostResponse,convertCriteria));
	}







}
