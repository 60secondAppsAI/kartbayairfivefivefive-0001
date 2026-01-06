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
import com.kartbayairfivefivefive.dao.AmenityDAO;
import com.kartbayairfivefivefive.domain.Amenity;
import com.kartbayairfivefivefive.dto.AmenityDTO;
import com.kartbayairfivefivefive.dto.AmenitySearchDTO;
import com.kartbayairfivefivefive.dto.AmenityPageDTO;
import com.kartbayairfivefivefive.dto.AmenityConvertCriteriaDTO;
import com.kartbayairfivefivefive.dto.common.RequestDTO;
import com.kartbayairfivefivefive.dto.common.ResultDTO;
import com.kartbayairfivefivefive.service.AmenityService;
import com.kartbayairfivefivefive.util.ControllerUtils;





@Service
public class AmenityServiceImpl extends GenericServiceImpl<Amenity, Integer> implements AmenityService {

    private final static Logger logger = LoggerFactory.getLogger(AmenityServiceImpl.class);

	@Autowired
	AmenityDAO amenityDao;

	


	@Override
	public GenericDAO<Amenity, Integer> getDAO() {
		return (GenericDAO<Amenity, Integer>) amenityDao;
	}
	
	public List<Amenity> findAll () {
		List<Amenity> amenitys = amenityDao.findAll();
		
		return amenitys;	
		
	}

	public ResultDTO addAmenity(AmenityDTO amenityDTO, RequestDTO requestDTO) {

		Amenity amenity = new Amenity();

		amenity.setAmenityId(amenityDTO.getAmenityId());


		amenity.setName(amenityDTO.getName());


		amenity.setDescription(amenityDTO.getDescription());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		amenity = amenityDao.save(amenity);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Amenity> getAllAmenitys(Pageable pageable) {
		return amenityDao.findAll(pageable);
	}

	public Page<Amenity> getAllAmenitys(Specification<Amenity> spec, Pageable pageable) {
		return amenityDao.findAll(spec, pageable);
	}

	public ResponseEntity<AmenityPageDTO> getAmenitys(AmenitySearchDTO amenitySearchDTO) {
	
			Integer amenityId = amenitySearchDTO.getAmenityId(); 
 			String name = amenitySearchDTO.getName(); 
 			String description = amenitySearchDTO.getDescription(); 
 			String sortBy = amenitySearchDTO.getSortBy();
			String sortOrder = amenitySearchDTO.getSortOrder();
			String searchQuery = amenitySearchDTO.getSearchQuery();
			Integer page = amenitySearchDTO.getPage();
			Integer size = amenitySearchDTO.getSize();

	        Specification<Amenity> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, amenityId, "amenityId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, name, "name"); 
			
			spec = ControllerUtils.andIfNecessary(spec, description, "description"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("name")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("description")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<Amenity> amenitys = this.getAllAmenitys(spec, pageable);
		
		//System.out.println(String.valueOf(amenitys.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(amenitys.getTotalPages()));
		
		List<Amenity> amenitysList = amenitys.getContent();
		
		AmenityConvertCriteriaDTO convertCriteria = new AmenityConvertCriteriaDTO();
		List<AmenityDTO> amenityDTOs = this.convertAmenitysToAmenityDTOs(amenitysList,convertCriteria);
		
		AmenityPageDTO amenityPageDTO = new AmenityPageDTO();
		amenityPageDTO.setAmenitys(amenityDTOs);
		amenityPageDTO.setTotalElements(amenitys.getTotalElements());
		return ResponseEntity.ok(amenityPageDTO);
	}

	public List<AmenityDTO> convertAmenitysToAmenityDTOs(List<Amenity> amenitys, AmenityConvertCriteriaDTO convertCriteria) {
		
		List<AmenityDTO> amenityDTOs = new ArrayList<AmenityDTO>();
		
		for (Amenity amenity : amenitys) {
			amenityDTOs.add(convertAmenityToAmenityDTO(amenity,convertCriteria));
		}
		
		return amenityDTOs;

	}
	
	public AmenityDTO convertAmenityToAmenityDTO(Amenity amenity, AmenityConvertCriteriaDTO convertCriteria) {
		
		AmenityDTO amenityDTO = new AmenityDTO();
		
		amenityDTO.setAmenityId(amenity.getAmenityId());

	
		amenityDTO.setName(amenity.getName());

	
		amenityDTO.setDescription(amenity.getDescription());

	

		
		return amenityDTO;
	}

	public ResultDTO updateAmenity(AmenityDTO amenityDTO, RequestDTO requestDTO) {
		
		Amenity amenity = amenityDao.getById(amenityDTO.getAmenityId());

		amenity.setAmenityId(ControllerUtils.setValue(amenity.getAmenityId(), amenityDTO.getAmenityId()));

		amenity.setName(ControllerUtils.setValue(amenity.getName(), amenityDTO.getName()));

		amenity.setDescription(ControllerUtils.setValue(amenity.getDescription(), amenityDTO.getDescription()));



        amenity = amenityDao.save(amenity);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public AmenityDTO getAmenityDTOById(Integer amenityId) {
	
		Amenity amenity = amenityDao.getById(amenityId);
			
		
		AmenityConvertCriteriaDTO convertCriteria = new AmenityConvertCriteriaDTO();
		return(this.convertAmenityToAmenityDTO(amenity,convertCriteria));
	}







}
