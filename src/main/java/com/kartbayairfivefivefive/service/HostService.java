package com.kartbayairfivefivefive.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.kartbayairfivefivefive.domain.Host;
import com.kartbayairfivefivefive.dto.HostDTO;
import com.kartbayairfivefivefive.dto.HostSearchDTO;
import com.kartbayairfivefivefive.dto.HostPageDTO;
import com.kartbayairfivefivefive.dto.HostConvertCriteriaDTO;
import com.kartbayairfivefivefive.service.GenericService;
import com.kartbayairfivefivefive.dto.common.RequestDTO;
import com.kartbayairfivefivefive.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface HostService extends GenericService<Host, Integer> {

	List<Host> findAll();

	ResultDTO addHost(HostDTO hostDTO, RequestDTO requestDTO);

	ResultDTO updateHost(HostDTO hostDTO, RequestDTO requestDTO);

    Page<Host> getAllHosts(Pageable pageable);

    Page<Host> getAllHosts(Specification<Host> spec, Pageable pageable);

	ResponseEntity<HostPageDTO> getHosts(HostSearchDTO hostSearchDTO);
	
	List<HostDTO> convertHostsToHostDTOs(List<Host> hosts, HostConvertCriteriaDTO convertCriteria);

	HostDTO getHostDTOById(Integer hostId);







}





