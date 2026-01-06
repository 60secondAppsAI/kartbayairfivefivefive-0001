package com.kartbayairfivefivefive.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.kartbayairfivefivefive.domain.ListingAmenity;
import com.kartbayairfivefivefive.dto.ListingAmenityDTO;
import com.kartbayairfivefivefive.dto.ListingAmenitySearchDTO;
import com.kartbayairfivefivefive.dto.ListingAmenityPageDTO;
import com.kartbayairfivefivefive.dto.ListingAmenityConvertCriteriaDTO;
import com.kartbayairfivefivefive.service.GenericService;
import com.kartbayairfivefivefive.dto.common.RequestDTO;
import com.kartbayairfivefivefive.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface ListingAmenityService extends GenericService<ListingAmenity, Integer> {

	List<ListingAmenity> findAll();

	ResultDTO addListingAmenity(ListingAmenityDTO listingAmenityDTO, RequestDTO requestDTO);

	ResultDTO updateListingAmenity(ListingAmenityDTO listingAmenityDTO, RequestDTO requestDTO);

    Page<ListingAmenity> getAllListingAmenitys(Pageable pageable);

    Page<ListingAmenity> getAllListingAmenitys(Specification<ListingAmenity> spec, Pageable pageable);

	ResponseEntity<ListingAmenityPageDTO> getListingAmenitys(ListingAmenitySearchDTO listingAmenitySearchDTO);
	
	List<ListingAmenityDTO> convertListingAmenitysToListingAmenityDTOs(List<ListingAmenity> listingAmenitys, ListingAmenityConvertCriteriaDTO convertCriteria);

	ListingAmenityDTO getListingAmenityDTOById(Integer listingAmenityId);







}





