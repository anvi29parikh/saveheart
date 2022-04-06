package com.saveheart.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saveheart.PredictionService;
import com.saveheart.dto.PredictionRequestDto;
import com.saveheart.dto.ResponseDto;

@RestController
@RequestMapping("/user")
public class PredictionController {

	@Autowired
	private PredictionService predictionService;

	@PostMapping("/predict")
	public ResponseEntity<ResponseDto> fetchUsers(@Valid @RequestBody PredictionRequestDto predictionRequestDto){
		ResponseDto responseDto = new ResponseDto();
		String status = predictionService.getPredictionData(predictionRequestDto);
		responseDto.setData(status);
		responseDto.setMetadata(null);
		return new ResponseEntity<>(responseDto,HttpStatus.OK);
	}
	
}
