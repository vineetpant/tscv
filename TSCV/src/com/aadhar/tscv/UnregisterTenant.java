package com.aadhar.tscv;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aadhaarconnect.bridge.gateway.model.KycResponse;
import com.social.dial.DatabaseClass;
import com.social.dial.modalhack.Owner;

public class UnregisterTenant extends Activity implements OnClickListener {

	TextView lblName, lblEmail, lblGender, lblPhone, lblDob, lblCo, lblHouse,
			lblStreet, lblLm, lblLoc, lblPo, lblPc, lblVtc, lblDist, lblState;
	Button btnCancel, btnUnregister;

	ImageView imgPhoto;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_kyc_details);
		init();
	}

	public static KycResponse KYC_RESPONSE = null;

	public static boolean IS_OWNER_SIGNUP=false;
	private void init() {

		lblName = (TextView) findViewById(R.id.lblName);
		lblEmail = (TextView) findViewById(R.id.lblEmail);
		lblGender = (TextView) findViewById(R.id.lblGender);
		lblPhone = (TextView) findViewById(R.id.lblPhone);
		lblDob = (TextView) findViewById(R.id.lblDob);
		lblCo = (TextView) findViewById(R.id.lblCo);
		lblHouse = (TextView) findViewById(R.id.lblHouse);
		lblStreet = (TextView) findViewById(R.id.lblStreet);
		lblLm = (TextView) findViewById(R.id.lblLm);
		lblLoc = (TextView) findViewById(R.id.lblLoc);
		lblPo = (TextView) findViewById(R.id.lblPo);
		lblPc = (TextView) findViewById(R.id.lblPc);
		lblVtc = (TextView) findViewById(R.id.lblVtc);
		lblDist = (TextView) findViewById(R.id.lblDist);
		lblState = (TextView) findViewById(R.id.lblState);

		btnCancel = (Button) findViewById(R.id.btnCancel);
		btnUnregister = (Button) findViewById(R.id.btnUnregisterTenant);

		btnCancel.setOnClickListener(this);
		btnUnregister.setOnClickListener(this);

		imgPhoto = (ImageView) findViewById(R.id.imgPhoto);
		if (KYC_RESPONSE != null) {

			lblName.setText(KYC_RESPONSE.getKyc().getPoi().getName());
			lblEmail.setText(KYC_RESPONSE.getKyc().getPoi().getEmail());
			lblGender.setText(KYC_RESPONSE.getKyc().getPoi().getGender()
					.toString());
			lblPhone.setText(KYC_RESPONSE.getKyc().getPoi().getPhone());
			lblDob.setText(KYC_RESPONSE.getKyc().getPoi().getDob());

			lblCo.setText(KYC_RESPONSE.getKyc().getPoa().getCo());
			lblHouse.setText(KYC_RESPONSE.getKyc().getPoa().getHouse());
			lblStreet.setText(KYC_RESPONSE.getKyc().getPoa().getStreet());
			lblLm.setText(KYC_RESPONSE.getKyc().getPoa().getLm());
			lblLoc.setText(KYC_RESPONSE.getKyc().getPoa().getLc());
			lblPo.setText(KYC_RESPONSE.getKyc().getPoa().getPo());
			lblPc.setText(KYC_RESPONSE.getKyc().getPoa().getPc());
			lblVtc.setText(KYC_RESPONSE.getKyc().getPoa().getVtc());
			lblDist.setText(KYC_RESPONSE.getKyc().getPoa().getDist());
			lblState.setText(KYC_RESPONSE.getKyc().getPoa().getState());

			imgPhoto.setImageBitmap(BitmapFactory.decodeByteArray(KYC_RESPONSE
					.getKyc().getPhoto(), 0,
					KYC_RESPONSE.getKyc().getPhoto().length, null));

		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int id = v.getId();

		switch (id) {
		case R.id.btnCancel:
			finish();
			break;
		case R.id.btnUnregisterTenant:
			// TODO add to database and move to start page
			showToast("Tenant Unregistered",Toast.LENGTH_LONG);
			finish();
			
			break;

		}

	}
	
	private void showToast(String text, int duration) {
		Toast toast = Toast.makeText(this, text, duration);
		toast.show();
	}

}
