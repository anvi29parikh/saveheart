package com.saveheart.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PredictionRequestDto {

	@NotNull
	private Integer age;

	@NotNull
	private Integer sex;
	@NotNull
	private Integer cp;
	@NotNull
	private Integer trestbps;
	@NotNull
	private Integer chol;
	@NotNull
	private Integer fbs;
	@NotNull
	private Integer restecg;
	@NotNull
	private Integer thalach;
	@NotNull
	private Integer exang;
	@NotNull
	private Integer oldpeak;
	@NotNull
	private Integer slope;
	@NotNull
	private Integer ca;
	@NotNull
	private Integer thal;

}
