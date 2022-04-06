package com.saveheart.enums;

public enum PredictionResultEnum {
	
	NOT_SAFE("Not Safe"),
	SAFE("Safe"),

    ;
    // properties
    private final String message;
    
	public String getMessage() {
		return message;
	}
	
	/**
	 * @param errorCode
	 * @param message
	 */
	private PredictionResultEnum(String message) {
		this.message = message;
	}
	
}
