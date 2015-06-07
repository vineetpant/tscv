package com.aadhar.tscv.events;

import com.aadhaarconnect.bridge.gateway.model.AuthResponse;
import com.aadhaarconnect.bridge.gateway.model.KycResponse;

public interface ServerResponse {
	
	public void authResponseReceived(AuthResponse authRes);
	public void kycResponseReceived(KycResponse authRes);

}
