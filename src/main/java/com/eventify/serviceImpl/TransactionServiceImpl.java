package com.eventify.serviceImpl;

import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.eventify.service.TransactionService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Value("${twilio.account.sid}")
	private String accountSid;

	@Value("${twilio.auth.token}")
	private String authToken;

	@Value("${twilio.phone.number}")
	private String twilioPhoneNumber;

	@Override
	public String sendOtp(String phoneNumber) {
        Twilio.init(accountSid, authToken);
		String otp = generateOtp();

        Message message = Message.creator(
                new PhoneNumber("+91"+phoneNumber),
                new PhoneNumber(twilioPhoneNumber),
                "Your OTP is: " + otp)
                .create();

        return "OTP sent successfully. SID: " + message.getSid();
	}
	
	 private String generateOtp() {
	        Random random = new Random();
	        int otp = 100000 + random.nextInt(900000);
	        return String.valueOf(otp);
	    }

}
