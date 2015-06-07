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

import com.aadhaarconnect.bridge.capture.model.auth.AuthCaptureData;
import com.aadhaarconnect.bridge.capture.model.auth.AuthCaptureRequest;
import com.aadhaarconnect.bridge.capture.model.common.request.CertificateType;
import com.aadhaarconnect.bridge.capture.model.common.request.Modality;
import com.aadhaarconnect.bridge.capture.model.common.request.ModalityType;
import com.aadhaarconnect.bridge.gateway.model.AuthResponse;
import com.aadhaarconnect.bridge.gateway.model.KycResponse;
import com.aadhar.tscv.authentication.AadhaarAuthAsyncTaskAuth;
import com.aadhar.tscv.events.ServerResponse;
import com.aadhar.tscv.utility.DialogsNMsgs;
import com.google.gson.Gson;

import com.social.dial.DatabaseClass;
import com.social.dial.modalhack.Owner;

public class LoginActivity extends Activity implements OnClickListener,
		ServerResponse {

	public static final int AADHAAR_CONNECT_AUTH_REQUEST = 1001;
	private static final String BASE_URL="https://ac.khoslalabs.com/hackgate/hackathon";
	private boolean loginFlag = true;
	private EditText txtEnterAadhaarNum, txtGeneratedOtp;
	private Button btnOtpAuth, btnFingerAuth, btnAuthenticate, btnCancel;
	String uidNumber;
	boolean isFp = false;
	boolean isOtp = false;
	AuthCaptureData authCaptureData;

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
		switch (id) {
		case R.id.btnProceed:
			// if (isFp) {
			AadhaarAuthAsyncTaskAuth authAsyncTask = new AadhaarAuthAsyncTaskAuth(this, authCaptureData);
			authAsyncTask.setResHandler(this);
			authAsyncTask.execute(BASE_URL + "/auth");
			break;
		case R.id.btnCancle:
			finish();
			break;
		case R.id.btnFpCpture:

			// ekyc authentication
			createBioRequestAuth();

			break;
		case R.id.btnOtpGen:
			
			break;

		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK && requestCode == AADHAAR_CONNECT_AUTH_REQUEST && data != null) {
			String responseStr = data.getStringExtra("RESPONSE");
			final AuthCaptureData authCaptureData = new Gson().fromJson(responseStr, AuthCaptureData.class);
			AadhaarAuthAsyncTaskAuth authAsyncTask = new AadhaarAuthAsyncTaskAuth(this,authCaptureData);
			authAsyncTask.execute(BASE_URL + "/auth");
			return;
		}
	}

	private void showToast(String text, int duration) {
		Toast toast = Toast.makeText(this, text, duration);
		toast.show();
	}



	private void createBioRequestAuth() {

		Intent i = new Intent();
		i = new Intent("com.aadhaarconnect.bridge.action.AUTHCAPTURE");

		AuthCaptureRequest biometricAuth = new AuthCaptureRequest();
		biometricAuth.setAadhaar(txtEnterAadhaarNum.getText().toString());
		biometricAuth.setModality(Modality.biometric);
		biometricAuth.setModalityType(ModalityType.fp);
		biometricAuth.setCertificateType(CertificateType.preprod);
		biometricAuth.setNumOffingersToCapture(1);

		com.aadhaarconnect.bridge.capture.model.common.Location loc = new com.aadhaarconnect.bridge.capture.model.common.Location();
		loc.setType(com.aadhaarconnect.bridge.capture.model.common.LocationType.gps);
		loc.setLatitude(String.valueOf("12.345"));
		loc.setLongitude(String.valueOf("12.87277"));
		loc.setAltitude(String.valueOf("15.9"));

		biometricAuth.setLocation(loc);
		i.putExtra("REQUEST", new Gson().toJson(biometricAuth));

		try {
			startActivityForResult(i, AADHAAR_CONNECT_AUTH_REQUEST);
		} catch (Exception e) {
			Log.e("ERROR", e.getMessage());
		}
	}

	@Override
	public void authResponseReceived(AuthResponse authRes) {
		// TODO Auto-generated method stub
//		String msg = "Authres received "+authRes;
//
//		DialogsNMsgs.createAlertDialog(msg, this);
		if (authRes.isSuccess()) {
			
			DatabaseClass db = DatabaseClass
					.getInstance(getApplicationContext());
			Owner owner = db.getOwnerDetailByUID(uidNumber);
			if (owner != null) {


			} else {

				String msg = "Please Sign Up with Ekyc Authentication Before First Login ";

				DialogsNMsgs.createAlertDialog(msg, this);
			}

		} else {
			String msg = "Authentication failed with auth error "
					+ authRes.getErrorCode() + " \nReference Code"
					+ authRes.getReferenceCode();

			DialogsNMsgs.createAlertDialog(msg, this);
		}
	}

	@Override
	public void kycResponseReceived(KycResponse authRes) {
		
	}

}
