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

import com.kartbayairfivefivefive.domain.Booking;
import com.kartbayairfivefivefive.dto.BookingDTO;
import com.kartbayairfivefivefive.dto.BookingSearchDTO;
import com.kartbayairfivefivefive.dto.BookingPageDTO;
import com.kartbayairfivefivefive.service.BookingService;
import com.kartbayairfivefivefive.dto.common.RequestDTO;
import com.kartbayairfivefivefive.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/booking")
@RestController
public class BookingController {

	private final static Logger logger = LoggerFactory.getLogger(BookingController.class);

	@Autowired
	BookingService bookingService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Booking> getAll() {

		List<Booking> bookings = bookingService.findAll();
		
		return bookings;	
	}

	@GetMapping(value = "/{bookingId}")
	@ResponseBody
	public BookingDTO getBooking(@PathVariable Integer bookingId) {
		
		return (bookingService.getBookingDTOById(bookingId));
	}

 	@RequestMapping(value = "/addBooking", method = RequestMethod.POST)
	public ResponseEntity<?> addBooking(@RequestBody BookingDTO bookingDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = bookingService.addBooking(bookingDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/bookings")
	public ResponseEntity<BookingPageDTO> getBookings(BookingSearchDTO bookingSearchDTO) {
 
		return bookingService.getBookings(bookingSearchDTO);
	}	

	@RequestMapping(value = "/updateBooking", method = RequestMethod.POST)
	public ResponseEntity<?> updateBooking(@RequestBody BookingDTO bookingDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = bookingService.updateBooking(bookingDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
