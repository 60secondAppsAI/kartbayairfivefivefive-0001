package com.kartbayairfivefivefive.controller;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.ArrayList;


import com.kartbayairfivefivefive.util.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.Date;

import com.kartbayairfivefivefive.domain.Calendar;
import com.kartbayairfivefivefive.dto.CalendarDTO;
import com.kartbayairfivefivefive.dto.CalendarSearchDTO;
import com.kartbayairfivefivefive.dto.CalendarPageDTO;
import com.kartbayairfivefivefive.service.CalendarService;
import com.kartbayairfivefivefive.dto.common.RequestDTO;
import com.kartbayairfivefivefive.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/calendar")
@RestController
public class CalendarController {

	private final static Logger logger = LoggerFactory.getLogger(CalendarController.class);

	@Autowired
	CalendarService calendarService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Calendar> getAll() {

		List<Calendar> calendars = calendarService.findAll();
		
		return calendars;	
	}

	@GetMapping(value = "/{calendarId}")
	@ResponseBody
	public CalendarDTO getCalendar(@PathVariable Integer calendarId) {
		
		return (calendarService.getCalendarDTOById(calendarId));
	}

 	@RequestMapping(value = "/addCalendar", method = RequestMethod.POST)
	public ResponseEntity<?> addCalendar(@RequestBody CalendarDTO calendarDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = calendarService.addCalendar(calendarDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/calendars")
	public ResponseEntity<CalendarPageDTO> getCalendars(CalendarSearchDTO calendarSearchDTO) {
 
		return calendarService.getCalendars(calendarSearchDTO);
	}	

	@RequestMapping(value = "/updateCalendar", method = RequestMethod.POST)
	public ResponseEntity<?> updateCalendar(@RequestBody CalendarDTO calendarDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = calendarService.updateCalendar(calendarDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
