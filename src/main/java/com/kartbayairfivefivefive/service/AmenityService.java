package com.kartbayairfivefivefive.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.kartbayairfivefivefive.domain.Amenity;
import com.kartbayairfivefivefive.dto.AmenityDTO;
import com.kartbayairfivefivefive.dto.AmenitySearchDTO;
import com.kartbayairfivefivefive.dto.AmenityPageDTO;
import com.kartbayairfivefivefive.dto.AmenityConvertCriteriaDTO;
import com.kartbayairfivefivefive.service.GenericService;
import com.kartbayairfivefivefive.dto.common.RequestDTO;
import com.kartbayairfivefivefive.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface AmenityService extends GenericService<Amenity, Integer> {

	List<Amenity> findAll();

	ResultDTO addAmenity(AmenityDTO amenityDTO, RequestDTO requestDTO);

	ResultDTO updateAmenity(AmenityDTO amenityDTO, RequestDTO requestDTO);

    Page<Amenity> getAllAmenitys(Pageable pageable);

    Page<Amenity> getAllAmenitys(Specification<Amenity> spec, Pageable pageable);

	ResponseEntity<AmenityPageDTO> getAmenitys(AmenitySearchDTO amenitySearchDTO);
	
	List<AmenityDTO> convertAmenitysToAmenityDTOs(List<Amenity> amenitys, AmenityConvertCriteriaDTO convertCriteria);

	AmenityDTO getAmenityDTOById(Integer amenityId);







}





