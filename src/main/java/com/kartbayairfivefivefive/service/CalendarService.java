package com.kartbayairfivefivefive.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.kartbayairfivefivefive.domain.Calendar;
import com.kartbayairfivefivefive.dto.CalendarDTO;
import com.kartbayairfivefivefive.dto.CalendarSearchDTO;
import com.kartbayairfivefivefive.dto.CalendarPageDTO;
import com.kartbayairfivefivefive.dto.CalendarConvertCriteriaDTO;
import com.kartbayairfivefivefive.service.GenericService;
import com.kartbayairfivefivefive.dto.common.RequestDTO;
import com.kartbayairfivefivefive.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface CalendarService extends GenericService<Calendar, Integer> {

	List<Calendar> findAll();

	ResultDTO addCalendar(CalendarDTO calendarDTO, RequestDTO requestDTO);

	ResultDTO updateCalendar(CalendarDTO calendarDTO, RequestDTO requestDTO);

    Page<Calendar> getAllCalendars(Pageable pageable);

    Page<Calendar> getAllCalendars(Specification<Calendar> spec, Pageable pageable);

	ResponseEntity<CalendarPageDTO> getCalendars(CalendarSearchDTO calendarSearchDTO);
	
	List<CalendarDTO> convertCalendarsToCalendarDTOs(List<Calendar> calendars, CalendarConvertCriteriaDTO convertCriteria);

	CalendarDTO getCalendarDTOById(Integer calendarId);







}





