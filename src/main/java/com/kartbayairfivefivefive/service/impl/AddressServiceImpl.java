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
import com.kartbayairfivefivefive.dao.AddressDAO;
import com.kartbayairfivefivefive.domain.Address;
import com.kartbayairfivefivefive.dto.AddressDTO;
import com.kartbayairfivefivefive.dto.AddressSearchDTO;
import com.kartbayairfivefivefive.dto.AddressPageDTO;
import com.kartbayairfivefivefive.dto.AddressConvertCriteriaDTO;
import com.kartbayairfivefivefive.dto.common.RequestDTO;
import com.kartbayairfivefivefive.dto.common.ResultDTO;
import com.kartbayairfivefivefive.service.AddressService;
import com.kartbayairfivefivefive.util.ControllerUtils;





@Service
public class AddressServiceImpl extends GenericServiceImpl<Address, Integer> implements AddressService {

    private final static Logger logger = LoggerFactory.getLogger(AddressServiceImpl.class);

	@Autowired
	AddressDAO addressDao;

	


	@Override
	public GenericDAO<Address, Integer> getDAO() {
		return (GenericDAO<Address, Integer>) addressDao;
	}
	
	public List<Address> findAll () {
		List<Address> addresss = addressDao.findAll();
		
		return addresss;	
		
	}

	public ResultDTO addAddress(AddressDTO addressDTO, RequestDTO requestDTO) {

		Address address = new Address();

		address.setAddressId(addressDTO.getAddressId());


		address.setStreet(addressDTO.getStreet());


		address.setCity(addressDTO.getCity());


		address.setState(addressDTO.getState());


		address.setZipCode(addressDTO.getZipCode());


		address.setCountry(addressDTO.getCountry());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		address = addressDao.save(address);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Address> getAllAddresss(Pageable pageable) {
		return addressDao.findAll(pageable);
	}

	public Page<Address> getAllAddresss(Specification<Address> spec, Pageable pageable) {
		return addressDao.findAll(spec, pageable);
	}

	public ResponseEntity<AddressPageDTO> getAddresss(AddressSearchDTO addressSearchDTO) {
	
			Integer addressId = addressSearchDTO.getAddressId(); 
 			String street = addressSearchDTO.getStreet(); 
 			String city = addressSearchDTO.getCity(); 
 			String state = addressSearchDTO.getState(); 
 			String zipCode = addressSearchDTO.getZipCode(); 
 			String country = addressSearchDTO.getCountry(); 
 			String sortBy = addressSearchDTO.getSortBy();
			String sortOrder = addressSearchDTO.getSortOrder();
			String searchQuery = addressSearchDTO.getSearchQuery();
			Integer page = addressSearchDTO.getPage();
			Integer size = addressSearchDTO.getSize();

	        Specification<Address> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, addressId, "addressId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, street, "street"); 
			
			spec = ControllerUtils.andIfNecessary(spec, city, "city"); 
			
			spec = ControllerUtils.andIfNecessary(spec, state, "state"); 
			
			spec = ControllerUtils.andIfNecessary(spec, zipCode, "zipCode"); 
			
			spec = ControllerUtils.andIfNecessary(spec, country, "country"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("street")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("city")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("state")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("zipCode")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("country")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<Address> addresss = this.getAllAddresss(spec, pageable);
		
		//System.out.println(String.valueOf(addresss.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(addresss.getTotalPages()));
		
		List<Address> addresssList = addresss.getContent();
		
		AddressConvertCriteriaDTO convertCriteria = new AddressConvertCriteriaDTO();
		List<AddressDTO> addressDTOs = this.convertAddresssToAddressDTOs(addresssList,convertCriteria);
		
		AddressPageDTO addressPageDTO = new AddressPageDTO();
		addressPageDTO.setAddresss(addressDTOs);
		addressPageDTO.setTotalElements(addresss.getTotalElements());
		return ResponseEntity.ok(addressPageDTO);
	}

	public List<AddressDTO> convertAddresssToAddressDTOs(List<Address> addresss, AddressConvertCriteriaDTO convertCriteria) {
		
		List<AddressDTO> addressDTOs = new ArrayList<AddressDTO>();
		
		for (Address address : addresss) {
			addressDTOs.add(convertAddressToAddressDTO(address,convertCriteria));
		}
		
		return addressDTOs;

	}
	
	public AddressDTO convertAddressToAddressDTO(Address address, AddressConvertCriteriaDTO convertCriteria) {
		
		AddressDTO addressDTO = new AddressDTO();
		
		addressDTO.setAddressId(address.getAddressId());

	
		addressDTO.setStreet(address.getStreet());

	
		addressDTO.setCity(address.getCity());

	
		addressDTO.setState(address.getState());

	
		addressDTO.setZipCode(address.getZipCode());

	
		addressDTO.setCountry(address.getCountry());

	

		
		return addressDTO;
	}

	public ResultDTO updateAddress(AddressDTO addressDTO, RequestDTO requestDTO) {
		
		Address address = addressDao.getById(addressDTO.getAddressId());

		address.setAddressId(ControllerUtils.setValue(address.getAddressId(), addressDTO.getAddressId()));

		address.setStreet(ControllerUtils.setValue(address.getStreet(), addressDTO.getStreet()));

		address.setCity(ControllerUtils.setValue(address.getCity(), addressDTO.getCity()));

		address.setState(ControllerUtils.setValue(address.getState(), addressDTO.getState()));

		address.setZipCode(ControllerUtils.setValue(address.getZipCode(), addressDTO.getZipCode()));

		address.setCountry(ControllerUtils.setValue(address.getCountry(), addressDTO.getCountry()));



        address = addressDao.save(address);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public AddressDTO getAddressDTOById(Integer addressId) {
	
		Address address = addressDao.getById(addressId);
			
		
		AddressConvertCriteriaDTO convertCriteria = new AddressConvertCriteriaDTO();
		return(this.convertAddressToAddressDTO(address,convertCriteria));
	}







}
