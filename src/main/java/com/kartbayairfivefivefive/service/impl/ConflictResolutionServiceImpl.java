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
import com.kartbayairfivefivefive.dao.ConflictResolutionDAO;
import com.kartbayairfivefivefive.domain.ConflictResolution;
import com.kartbayairfivefivefive.dto.ConflictResolutionDTO;
import com.kartbayairfivefivefive.dto.ConflictResolutionSearchDTO;
import com.kartbayairfivefivefive.dto.ConflictResolutionPageDTO;
import com.kartbayairfivefivefive.dto.ConflictResolutionConvertCriteriaDTO;
import com.kartbayairfivefivefive.dto.common.RequestDTO;
import com.kartbayairfivefivefive.dto.common.ResultDTO;
import com.kartbayairfivefivefive.service.ConflictResolutionService;
import com.kartbayairfivefivefive.util.ControllerUtils;





@Service
public class ConflictResolutionServiceImpl extends GenericServiceImpl<ConflictResolution, Integer> implements ConflictResolutionService {

    private final static Logger logger = LoggerFactory.getLogger(ConflictResolutionServiceImpl.class);

	@Autowired
	ConflictResolutionDAO conflictResolutionDao;

	


	@Override
	public GenericDAO<ConflictResolution, Integer> getDAO() {
		return (GenericDAO<ConflictResolution, Integer>) conflictResolutionDao;
	}
	
	public List<ConflictResolution> findAll () {
		List<ConflictResolution> conflictResolutions = conflictResolutionDao.findAll();
		
		return conflictResolutions;	
		
	}

	public ResultDTO addConflictResolution(ConflictResolutionDTO conflictResolutionDTO, RequestDTO requestDTO) {

		ConflictResolution conflictResolution = new ConflictResolution();

		conflictResolution.setConflictResolutionId(conflictResolutionDTO.getConflictResolutionId());


		conflictResolution.setResolutionMessage(conflictResolutionDTO.getResolutionMessage());


		conflictResolution.setResolvedDate(conflictResolutionDTO.getResolvedDate());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		conflictResolution = conflictResolutionDao.save(conflictResolution);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<ConflictResolution> getAllConflictResolutions(Pageable pageable) {
		return conflictResolutionDao.findAll(pageable);
	}

	public Page<ConflictResolution> getAllConflictResolutions(Specification<ConflictResolution> spec, Pageable pageable) {
		return conflictResolutionDao.findAll(spec, pageable);
	}

	public ResponseEntity<ConflictResolutionPageDTO> getConflictResolutions(ConflictResolutionSearchDTO conflictResolutionSearchDTO) {
	
			Integer conflictResolutionId = conflictResolutionSearchDTO.getConflictResolutionId(); 
 			String resolutionMessage = conflictResolutionSearchDTO.getResolutionMessage(); 
   			String sortBy = conflictResolutionSearchDTO.getSortBy();
			String sortOrder = conflictResolutionSearchDTO.getSortOrder();
			String searchQuery = conflictResolutionSearchDTO.getSearchQuery();
			Integer page = conflictResolutionSearchDTO.getPage();
			Integer size = conflictResolutionSearchDTO.getSize();

	        Specification<ConflictResolution> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, conflictResolutionId, "conflictResolutionId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, resolutionMessage, "resolutionMessage"); 
			
 			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("resolutionMessage")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<ConflictResolution> conflictResolutions = this.getAllConflictResolutions(spec, pageable);
		
		//System.out.println(String.valueOf(conflictResolutions.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(conflictResolutions.getTotalPages()));
		
		List<ConflictResolution> conflictResolutionsList = conflictResolutions.getContent();
		
		ConflictResolutionConvertCriteriaDTO convertCriteria = new ConflictResolutionConvertCriteriaDTO();
		List<ConflictResolutionDTO> conflictResolutionDTOs = this.convertConflictResolutionsToConflictResolutionDTOs(conflictResolutionsList,convertCriteria);
		
		ConflictResolutionPageDTO conflictResolutionPageDTO = new ConflictResolutionPageDTO();
		conflictResolutionPageDTO.setConflictResolutions(conflictResolutionDTOs);
		conflictResolutionPageDTO.setTotalElements(conflictResolutions.getTotalElements());
		return ResponseEntity.ok(conflictResolutionPageDTO);
	}

	public List<ConflictResolutionDTO> convertConflictResolutionsToConflictResolutionDTOs(List<ConflictResolution> conflictResolutions, ConflictResolutionConvertCriteriaDTO convertCriteria) {
		
		List<ConflictResolutionDTO> conflictResolutionDTOs = new ArrayList<ConflictResolutionDTO>();
		
		for (ConflictResolution conflictResolution : conflictResolutions) {
			conflictResolutionDTOs.add(convertConflictResolutionToConflictResolutionDTO(conflictResolution,convertCriteria));
		}
		
		return conflictResolutionDTOs;

	}
	
	public ConflictResolutionDTO convertConflictResolutionToConflictResolutionDTO(ConflictResolution conflictResolution, ConflictResolutionConvertCriteriaDTO convertCriteria) {
		
		ConflictResolutionDTO conflictResolutionDTO = new ConflictResolutionDTO();
		
		conflictResolutionDTO.setConflictResolutionId(conflictResolution.getConflictResolutionId());

	
		conflictResolutionDTO.setResolutionMessage(conflictResolution.getResolutionMessage());

	
		conflictResolutionDTO.setResolvedDate(conflictResolution.getResolvedDate());

	

		
		return conflictResolutionDTO;
	}

	public ResultDTO updateConflictResolution(ConflictResolutionDTO conflictResolutionDTO, RequestDTO requestDTO) {
		
		ConflictResolution conflictResolution = conflictResolutionDao.getById(conflictResolutionDTO.getConflictResolutionId());

		conflictResolution.setConflictResolutionId(ControllerUtils.setValue(conflictResolution.getConflictResolutionId(), conflictResolutionDTO.getConflictResolutionId()));

		conflictResolution.setResolutionMessage(ControllerUtils.setValue(conflictResolution.getResolutionMessage(), conflictResolutionDTO.getResolutionMessage()));

		conflictResolution.setResolvedDate(ControllerUtils.setValue(conflictResolution.getResolvedDate(), conflictResolutionDTO.getResolvedDate()));



        conflictResolution = conflictResolutionDao.save(conflictResolution);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public ConflictResolutionDTO getConflictResolutionDTOById(Integer conflictResolutionId) {
	
		ConflictResolution conflictResolution = conflictResolutionDao.getById(conflictResolutionId);
			
		
		ConflictResolutionConvertCriteriaDTO convertCriteria = new ConflictResolutionConvertCriteriaDTO();
		return(this.convertConflictResolutionToConflictResolutionDTO(conflictResolution,convertCriteria));
	}







}
