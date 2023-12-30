package com.eventify.service;

public interface TransactionService {

	public String sendOtp(String phoneNumber);
	public byte[] generateQRCode(String text);

}
