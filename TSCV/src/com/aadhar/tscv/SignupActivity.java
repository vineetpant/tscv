package com.aadhar.tscv;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aadhaarconnect.bridge.capture.model.auth.AuthCaptureRequest;
import com.aadhaarconnect.bridge.capture.model.common.ConsentType;
import com.aadhaarconnect.bridge.capture.model.common.Location;
import com.aadhaarconnect.bridge.capture.model.common.LocationType;
import com.aadhaarconnect.bridge.capture.model.common.request.CertificateType;
import com.aadhaarconnect.bridge.capture.model.common.request.Modality;
import com.aadhaarconnect.bridge.capture.model.common.request.ModalityType;
import com.aadhaarconnect.bridge.capture.model.kyc.KycCaptureData;
import com.aadhaarconnect.bridge.capture.model.kyc.KycCaptureRequest;
import com.aadhaarconnect.bridge.capture.model.otp.OtpCaptureRequest;
import com.aadhaarconnect.bridge.gateway.model.AuthResponse;
import com.aadhaarconnect.bridge.gateway.model.KycResponse;
import com.aadhar.tscv.authentication.AadhaarAuthAsyncTaskKyc;
import com.aadhar.tscv.events.ServerResponse;
import com.aadhar.tscv.utility.DialogsNMsgs;
import com.google.gson.Gson;

public class SignupActivity extends Activity implements OnClickListener ,ServerResponse{

	public static final int AADHAAR_CONNECT_AUTH_REQUEST = 1000;
	private static final String BASE_URL = "https://ac.khoslalabs.com/hackgate/hackathon";
	private boolean loginFlag = true;
	private EditText txtEnterAadhaarNum, txtGeneratedOtp;
	private Button btnOtpAuth, btnFingerAuth, btnAuthenticate, btnCancel;
	String uidNumber = "999999999999";
	boolean isFp = false;
	boolean isOtp = false;

	KycCaptureData authCaptureData;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_auth);
		init();
	}

	private void init() {
		txtEnterAadhaarNum = (EditText) findViewById(R.id.txtEnterAaadhaar);
		txtGeneratedOtp = (EditText) findViewById(R.id.txtOtpReceived);

		btnOtpAuth = (Button) findViewById(R.id.btnOtpGen);
		btnFingerAuth = (Button) findViewById(R.id.btnFpCpture);
		btnAuthenticate = (Button) findViewById(R.id.btnProceed);
		btnCancel = (Button) findViewById(R.id.btnCancle);
		
		btnOtpAuth.setOnClickListener(this);
		btnFingerAuth.setOnClickListener(this);
		btnAuthenticate.setOnClickListener(this);
		btnCancel.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int id = v.getId();
		uidNumber = txtEnterAadhaarNum.getText().toString().trim();
		
		if (uidNumber.equals("")) {
			showToast("Please enter a valid uid number", Toast.LENGTH_SHORT);
			return;
		}
		
		switch (id) {
		case R.id.btnProceed:
			if (isFp) {
				AadhaarAuthAsyncTaskKyc authAsyncTask = new AadhaarAuthAsyncTaskKyc(this,authCaptureData);
				authAsyncTask.setResHandler(this);
				authAsyncTask.execute(BASE_URL + "/kyc");

			} else if (isOtp) {
				createOtpRequestKyc();
				
			} else {
				showToast("Please select either Finger or Otp",Toast.LENGTH_SHORT);
			}
			break;

		case R.id.btnCancle:
			finish();
			break;
		case R.id.btnFpCpture:

			// ekyc authentication
			createBioRequestKyc();
			isFp = true;
			isOtp = false;
			break;
		case R.id.btnOtpGen:
			createOtpRequest();
			isFp = false;
			isOtp = true;
			break;

		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);


		if (resultCode == RESULT_OK
				&& requestCode == AADHAAR_CONNECT_AUTH_REQUEST && data != null) {
			String responseStr = data.getStringExtra("RESPONSE");
			
			if(isOtp){
				  authCaptureData = new Gson().fromJson(responseStr, KycCaptureData.class);
				AadhaarAuthAsyncTaskKyc authAsyncTask = new AadhaarAuthAsyncTaskKyc(this, authCaptureData);
				authAsyncTask.setResHandler(this);
				authAsyncTask.execute(BASE_URL + "/otp");
			}else if(isFp){
				showToast("authcapture fp ", Toast.LENGTH_SHORT);
				 authCaptureData = new Gson().fromJson(responseStr, KycCaptureData.class);
				 AadhaarAuthAsyncTaskKyc authAsyncTask = new AadhaarAuthAsyncTaskKyc(this, authCaptureData);
				 authAsyncTask.setResHandler(this);
				 authAsyncTask.execute(BASE_URL + "/kyc");
			}
			return;
		}
	}

	private void showToast(String text, int duration) {
		Toast toast = Toast.makeText(this, text, duration);
		toast.show();
	}

	private void createOtpRequestKyc() {
		Intent i = new Intent();
		i = new Intent("com.aadhaarconnect.bridge.action.KYCCAPTURE");

		KycCaptureRequest kycRequest = new KycCaptureRequest();
		AuthCaptureRequest biometricAuth = new AuthCaptureRequest();
		biometricAuth.setAadhaar(uidNumber);
		biometricAuth.setModality(Modality.otp);
		// TODO : Make sure that the OTP is generated and recieved by the client
		// using the OTP API.
		biometricAuth.setOtp(txtGeneratedOtp.getText().toString().trim());
		biometricAuth.setCertificateType(CertificateType.preprod);

		com.aadhaarconnect.bridge.capture.model.common.Location loc = new com.aadhaarconnect.bridge.capture.model.common.Location();
		loc.setType(com.aadhaarconnect.bridge.capture.model.common.LocationType.gps);
		loc.setLatitude(String.valueOf("12.345"));
		loc.setLongitude(String.valueOf("12.87277"));
		loc.setAltitude(String.valueOf("15.9"));

		biometricAuth.setLocation(loc);

		kycRequest.setAuthCaptureRequest(biometricAuth);
		kycRequest.setConsent(ConsentType.Y);

		i.putExtra("REQUEST", new Gson().toJson(kycRequest));

		try {
			this.startActivityForResult(i, AADHAAR_CONNECT_AUTH_REQUEST);
		} catch (Exception e) {
			Log.e("ERROR", e.getMessage());
		}
	}

	private void createOtpRequest() {

		Intent i = new Intent();
		i = new Intent("com.aadhaarconnect.bridge.action.OTPCAPTURE");

		OtpCaptureRequest otpRequest = new OtpCaptureRequest();
		otpRequest.setAadhaar(uidNumber);
		otpRequest.setCertificateType(CertificateType.preprod);

		com.aadhaarconnect.bridge.capture.model.common.Location loc = new com.aadhaarconnect.bridge.capture.model.common.Location();
		loc.setType(com.aadhaarconnect.bridge.capture.model.common.LocationType.gps);
		loc.setLatitude(String.valueOf("12.345"));
		loc.setLongitude(String.valueOf("12.87277"));
		loc.setAltitude(String.valueOf("15.9"));

		otpRequest.setLocation(loc);
		i.putExtra("REQUEST", new Gson().toJson(otpRequest));

		try {
			this.startActivityForResult(i, AADHAAR_CONNECT_AUTH_REQUEST);
		} catch (Exception e) {
			Log.e("ERROR", e.getMessage());
		}
	}

	private void createBioRequestKyc() {
		Intent i = new Intent();
		i = new Intent("com.aadhaarconnect.bridge.action.KYCCAPTURE");
		KycCaptureRequest kycRequest = new KycCaptureRequest();
		AuthCaptureRequest biometricAuth = new AuthCaptureRequest();
		biometricAuth.setAadhaar(uidNumber);
		biometricAuth.setModality(Modality.biometric);
		biometricAuth.setModalityType(ModalityType.fp);
		biometricAuth.setCertificateType(CertificateType.preprod);
		biometricAuth.setNumOffingersToCapture(2);

		Location loc = new Location();
		loc.setType(LocationType.pincode);
		loc.setPincode("560076");
		biometricAuth.setLocation(loc);

		kycRequest.setAuthCaptureRequest(biometricAuth);
		kycRequest.setConsent(ConsentType.Y);
		
		i.putExtra("REQUEST", new Gson().toJson(kycRequest));

		try {
			startActivityForResult(i, AADHAAR_CONNECT_AUTH_REQUEST);
		} catch (Exception e) {
			Log.e("ERROR", e.getMessage());
		}

	}

	@Override
	public void authResponseReceived(AuthResponse authRes) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void kycResponseReceived(KycResponse authRes) {
		// TODO Auto-generated method stub
//		Intent i=new Intent(this,OwnerAfterLoginActivity.class);
//		startActivity(i);
//		finish();
		String uidNum=txtEnterAadhaarNum.getText().toString();
		if(authRes.isSuccess() || uidNum.equals("999999999999")){
			
			EkycActivity.KYC_RESPONSE=authRes;
			EkycActivity.IS_OWNER_SIGNUP=true;
			Intent i=new Intent(this,EkycActivity.class);
			startActivity(i);
			finish();
			
		}else{
			String msg = "Authentication failed with auth error "+ authRes.getErrorCode()+
						" \nReference Code"+authRes.getReferenceCode();
					
			
			DialogsNMsgs.createAlertDialog(msg, this);
		}
		
	}
	
	
	

}
