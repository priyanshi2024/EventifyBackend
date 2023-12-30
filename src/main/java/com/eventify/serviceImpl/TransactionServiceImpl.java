package com.eventify.serviceImpl;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.eventify.service.TransactionService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
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

		Message message = Message.creator(new PhoneNumber("+91" + phoneNumber), new PhoneNumber(twilioPhoneNumber),
				"Your OTP is: " + otp).create();

		return "OTP sent successfully. SID: " + message.getSid();
	}

	@Override
	public byte[] generateQRCode(String text) {
		int width = 200;
		int height = 200;

		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		Map<EncodeHintType, Object> hintsMap = new HashMap<>();
		hintsMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		BitMatrix bitMatrix;
		try {
			bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height, hintsMap);
		} catch (Exception e) {
			throw new RuntimeException("Failed to generate QR code image.", e);
		}

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		BufferedImage qrImage = toBufferedImage(bitMatrix);
		try {
			ImageIO.write(qrImage, "png", outputStream);
		} catch (IOException e) {
			throw new RuntimeException("Failed to write QR code image to output stream.", e);
		}

		return outputStream.toByteArray();
	}

	private BufferedImage toBufferedImage(BitMatrix matrix) {
		int width = matrix.getWidth();
		int height = matrix.getHeight();
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics = (Graphics2D) image.getGraphics();
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, width, height);
		graphics.setColor(Color.BLACK);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (matrix.get(x, y)) {
					graphics.fillRect(x, y, 1, 1);
				}
			}
		}
		return image;
	}

	private String generateOtp() {
		Random random = new Random();
		int otp = 100000 + random.nextInt(900000);
		return String.valueOf(otp);
	}
}
