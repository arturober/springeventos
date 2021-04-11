package com.arturober.springeventos.entidades.dto;

public class ResponseLoginDto {
	private String accessToken;

	public ResponseLoginDto(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
}
