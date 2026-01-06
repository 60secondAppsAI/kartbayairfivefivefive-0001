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
import com.kartbayairfivefivefive.dao.CalendarDAO;
import com.kartbayairfivefivefive.domain.Calendar;
import com.kartbayairfivefivefive.dto.CalendarDTO;
import com.kartbayairfivefivefive.dto.CalendarSearchDTO;
import com.kartbayairfivefivefive.dto.CalendarPageDTO;
import com.kartbayairfivefivefive.dto.CalendarConvertCriteriaDTO;
import com.kartbayairfivefivefive.dto.common.RequestDTO;
import com.kartbayairfivefivefive.dto.common.ResultDTO;
import com.kartbayairfivefivefive.service.CalendarService;
import com.kartbayairfivefivefive.util.ControllerUtils;





@Service
public class CalendarServiceImpl extends GenericServiceImpl<Calendar, Integer> implements CalendarService {

    private final static Logger logger = LoggerFactory.getLogger(CalendarServiceImpl.class);

	@Autowired
	CalendarDAO calendarDao;

	


	@Override
	public GenericDAO<Calendar, Integer> getDAO() {
		return (GenericDAO<Calendar, Integer>) calendarDao;
	}
	
	public List<Calendar> findAll () {
		List<Calendar> calendars = calendarDao.findAll();
		
		return calendars;	
		
	}

	public ResultDTO addCalendar(CalendarDTO calendarDTO, RequestDTO requestDTO) {

		Calendar calendar = new Calendar();

		calendar.setCalendarId(calendarDTO.getCalendarId());


		calendar.setDate(calendarDTO.getDate());


		calendar.setAvailable(calendarDTO.getAvailable());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		calendar = calendarDao.save(calendar);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Calendar> getAllCalendars(Pageable pageable) {
		return calendarDao.findAll(pageable);
	}

	public Page<Calendar> getAllCalendars(Specification<Calendar> spec, Pageable pageable) {
		return calendarDao.findAll(spec, pageable);
	}

	public ResponseEntity<CalendarPageDTO> getCalendars(CalendarSearchDTO calendarSearchDTO) {
	
			Integer calendarId = calendarSearchDTO.getCalendarId(); 
    			String sortBy = calendarSearchDTO.getSortBy();
			String sortOrder = calendarSearchDTO.getSortOrder();
			String searchQuery = calendarSearchDTO.getSearchQuery();
			Integer page = calendarSearchDTO.getPage();
			Integer size = calendarSearchDTO.getSize();

	        Specification<Calendar> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, calendarId, "calendarId"); 
			
 			
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

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

		Page<Calendar> calendars = this.getAllCalendars(spec, pageable);
		
		//System.out.println(String.valueOf(calendars.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(calendars.getTotalPages()));
		
		List<Calendar> calendarsList = calendars.getContent();
		
		CalendarConvertCriteriaDTO convertCriteria = new CalendarConvertCriteriaDTO();
		List<CalendarDTO> calendarDTOs = this.convertCalendarsToCalendarDTOs(calendarsList,convertCriteria);
		
		CalendarPageDTO calendarPageDTO = new CalendarPageDTO();
		calendarPageDTO.setCalendars(calendarDTOs);
		calendarPageDTO.setTotalElements(calendars.getTotalElements());
		return ResponseEntity.ok(calendarPageDTO);
	}

	public List<CalendarDTO> convertCalendarsToCalendarDTOs(List<Calendar> calendars, CalendarConvertCriteriaDTO convertCriteria) {
		
		List<CalendarDTO> calendarDTOs = new ArrayList<CalendarDTO>();
		
		for (Calendar calendar : calendars) {
			calendarDTOs.add(convertCalendarToCalendarDTO(calendar,convertCriteria));
		}
		
		return calendarDTOs;

	}
	
	public CalendarDTO convertCalendarToCalendarDTO(Calendar calendar, CalendarConvertCriteriaDTO convertCriteria) {
		
		CalendarDTO calendarDTO = new CalendarDTO();
		
		calendarDTO.setCalendarId(calendar.getCalendarId());

	
		calendarDTO.setDate(calendar.getDate());

	
		calendarDTO.setAvailable(calendar.getAvailable());

	

		
		return calendarDTO;
	}

	public ResultDTO updateCalendar(CalendarDTO calendarDTO, RequestDTO requestDTO) {
		
		Calendar calendar = calendarDao.getById(calendarDTO.getCalendarId());

		calendar.setCalendarId(ControllerUtils.setValue(calendar.getCalendarId(), calendarDTO.getCalendarId()));

		calendar.setDate(ControllerUtils.setValue(calendar.getDate(), calendarDTO.getDate()));

		calendar.setAvailable(ControllerUtils.setValue(calendar.getAvailable(), calendarDTO.getAvailable()));



        calendar = calendarDao.save(calendar);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public CalendarDTO getCalendarDTOById(Integer calendarId) {
	
		Calendar calendar = calendarDao.getById(calendarId);
			
		
		CalendarConvertCriteriaDTO convertCriteria = new CalendarConvertCriteriaDTO();
		return(this.convertCalendarToCalendarDTO(calendar,convertCriteria));
	}







}
