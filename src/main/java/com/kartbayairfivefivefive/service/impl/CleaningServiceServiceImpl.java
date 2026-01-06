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
import com.kartbayairfivefivefive.dao.CleaningServiceDAO;
import com.kartbayairfivefivefive.domain.CleaningService;
import com.kartbayairfivefivefive.dto.CleaningServiceDTO;
import com.kartbayairfivefivefive.dto.CleaningServiceSearchDTO;
import com.kartbayairfivefivefive.dto.CleaningServicePageDTO;
import com.kartbayairfivefivefive.dto.CleaningServiceConvertCriteriaDTO;
import com.kartbayairfivefivefive.dto.common.RequestDTO;
import com.kartbayairfivefivefive.dto.common.ResultDTO;
import com.kartbayairfivefivefive.service.CleaningServiceService;
import com.kartbayairfivefivefive.util.ControllerUtils;





@Service
public class CleaningServiceServiceImpl extends GenericServiceImpl<CleaningService, Integer> implements CleaningServiceService {

    private final static Logger logger = LoggerFactory.getLogger(CleaningServiceServiceImpl.class);

	@Autowired
	CleaningServiceDAO cleaningServiceDao;

	


	@Override
	public GenericDAO<CleaningService, Integer> getDAO() {
		return (GenericDAO<CleaningService, Integer>) cleaningServiceDao;
	}
	
	public List<CleaningService> findAll () {
		List<CleaningService> cleaningServices = cleaningServiceDao.findAll();
		
		return cleaningServices;	
		
	}

	public ResultDTO addCleaningService(CleaningServiceDTO cleaningServiceDTO, RequestDTO requestDTO) {

		CleaningService cleaningService = new CleaningService();

		cleaningService.setCleaningServiceId(cleaningServiceDTO.getCleaningServiceId());


		cleaningService.setProviderName(cleaningServiceDTO.getProviderName());


		cleaningService.setServiceDate(cleaningServiceDTO.getServiceDate());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		cleaningService = cleaningServiceDao.save(cleaningService);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<CleaningService> getAllCleaningServices(Pageable pageable) {
		return cleaningServiceDao.findAll(pageable);
	}

	public Page<CleaningService> getAllCleaningServices(Specification<CleaningService> spec, Pageable pageable) {
		return cleaningServiceDao.findAll(spec, pageable);
	}

	public ResponseEntity<CleaningServicePageDTO> getCleaningServices(CleaningServiceSearchDTO cleaningServiceSearchDTO) {
	
			Integer cleaningServiceId = cleaningServiceSearchDTO.getCleaningServiceId(); 
 			String providerName = cleaningServiceSearchDTO.getProviderName(); 
   			String sortBy = cleaningServiceSearchDTO.getSortBy();
			String sortOrder = cleaningServiceSearchDTO.getSortOrder();
			String searchQuery = cleaningServiceSearchDTO.getSearchQuery();
			Integer page = cleaningServiceSearchDTO.getPage();
			Integer size = cleaningServiceSearchDTO.getSize();

	        Specification<CleaningService> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, cleaningServiceId, "cleaningServiceId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, providerName, "providerName"); 
			
 			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("providerName")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<CleaningService> cleaningServices = this.getAllCleaningServices(spec, pageable);
		
		//System.out.println(String.valueOf(cleaningServices.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(cleaningServices.getTotalPages()));
		
		List<CleaningService> cleaningServicesList = cleaningServices.getContent();
		
		CleaningServiceConvertCriteriaDTO convertCriteria = new CleaningServiceConvertCriteriaDTO();
		List<CleaningServiceDTO> cleaningServiceDTOs = this.convertCleaningServicesToCleaningServiceDTOs(cleaningServicesList,convertCriteria);
		
		CleaningServicePageDTO cleaningServicePageDTO = new CleaningServicePageDTO();
		cleaningServicePageDTO.setCleaningServices(cleaningServiceDTOs);
		cleaningServicePageDTO.setTotalElements(cleaningServices.getTotalElements());
		return ResponseEntity.ok(cleaningServicePageDTO);
	}

	public List<CleaningServiceDTO> convertCleaningServicesToCleaningServiceDTOs(List<CleaningService> cleaningServices, CleaningServiceConvertCriteriaDTO convertCriteria) {
		
		List<CleaningServiceDTO> cleaningServiceDTOs = new ArrayList<CleaningServiceDTO>();
		
		for (CleaningService cleaningService : cleaningServices) {
			cleaningServiceDTOs.add(convertCleaningServiceToCleaningServiceDTO(cleaningService,convertCriteria));
		}
		
		return cleaningServiceDTOs;

	}
	
	public CleaningServiceDTO convertCleaningServiceToCleaningServiceDTO(CleaningService cleaningService, CleaningServiceConvertCriteriaDTO convertCriteria) {
		
		CleaningServiceDTO cleaningServiceDTO = new CleaningServiceDTO();
		
		cleaningServiceDTO.setCleaningServiceId(cleaningService.getCleaningServiceId());

	
		cleaningServiceDTO.setProviderName(cleaningService.getProviderName());

	
		cleaningServiceDTO.setServiceDate(cleaningService.getServiceDate());

	

		
		return cleaningServiceDTO;
	}

	public ResultDTO updateCleaningService(CleaningServiceDTO cleaningServiceDTO, RequestDTO requestDTO) {
		
		CleaningService cleaningService = cleaningServiceDao.getById(cleaningServiceDTO.getCleaningServiceId());

		cleaningService.setCleaningServiceId(ControllerUtils.setValue(cleaningService.getCleaningServiceId(), cleaningServiceDTO.getCleaningServiceId()));

		cleaningService.setProviderName(ControllerUtils.setValue(cleaningService.getProviderName(), cleaningServiceDTO.getProviderName()));

		cleaningService.setServiceDate(ControllerUtils.setValue(cleaningService.getServiceDate(), cleaningServiceDTO.getServiceDate()));



        cleaningService = cleaningServiceDao.save(cleaningService);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public CleaningServiceDTO getCleaningServiceDTOById(Integer cleaningServiceId) {
	
		CleaningService cleaningService = cleaningServiceDao.getById(cleaningServiceId);
			
		
		CleaningServiceConvertCriteriaDTO convertCriteria = new CleaningServiceConvertCriteriaDTO();
		return(this.convertCleaningServiceToCleaningServiceDTO(cleaningService,convertCriteria));
	}







}
