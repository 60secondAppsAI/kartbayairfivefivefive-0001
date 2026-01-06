package com.kartbayairfivefivefive.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ImagePageDTO {

	private Integer page = 0;
	private Long totalElements = 0L;

	private List<ImageDTO> images;
}





