package com.kartbayairfivefivefive.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.kartbayairfivefivefive.domain.Insurance;
import com.kartbayairfivefivefive.dto.InsuranceDTO;
import com.kartbayairfivefivefive.dto.InsuranceSearchDTO;
import com.kartbayairfivefivefive.dto.InsurancePageDTO;
import com.kartbayairfivefivefive.dto.InsuranceConvertCriteriaDTO;
import com.kartbayairfivefivefive.service.GenericService;
import com.kartbayairfivefivefive.dto.common.RequestDTO;
import com.kartbayairfivefivefive.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface InsuranceService extends GenericService<Insurance, Integer> {

	List<Insurance> findAll();

	ResultDTO addInsurance(InsuranceDTO insuranceDTO, RequestDTO requestDTO);

	ResultDTO updateInsurance(InsuranceDTO insuranceDTO, RequestDTO requestDTO);

    Page<Insurance> getAllInsurances(Pageable pageable);

    Page<Insurance> getAllInsurances(Specification<Insurance> spec, Pageable pageable);

	ResponseEntity<InsurancePageDTO> getInsurances(InsuranceSearchDTO insuranceSearchDTO);
	
	List<InsuranceDTO> convertInsurancesToInsuranceDTOs(List<Insurance> insurances, InsuranceConvertCriteriaDTO convertCriteria);

	InsuranceDTO getInsuranceDTOById(Integer insuranceId);







}





