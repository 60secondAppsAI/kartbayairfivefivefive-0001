package com.kartbayairfivefivefive.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.kartbayairfivefivefive.domain.Image;
import com.kartbayairfivefivefive.dto.ImageDTO;
import com.kartbayairfivefivefive.dto.ImageSearchDTO;
import com.kartbayairfivefivefive.dto.ImagePageDTO;
import com.kartbayairfivefivefive.dto.ImageConvertCriteriaDTO;
import com.kartbayairfivefivefive.service.GenericService;
import com.kartbayairfivefivefive.dto.common.RequestDTO;
import com.kartbayairfivefivefive.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface ImageService extends GenericService<Image, Integer> {

	List<Image> findAll();

	ResultDTO addImage(ImageDTO imageDTO, RequestDTO requestDTO);

	ResultDTO updateImage(ImageDTO imageDTO, RequestDTO requestDTO);

    Page<Image> getAllImages(Pageable pageable);

    Page<Image> getAllImages(Specification<Image> spec, Pageable pageable);

	ResponseEntity<ImagePageDTO> getImages(ImageSearchDTO imageSearchDTO);
	
	List<ImageDTO> convertImagesToImageDTOs(List<Image> images, ImageConvertCriteriaDTO convertCriteria);

	ImageDTO getImageDTOById(Integer imageId);







}





