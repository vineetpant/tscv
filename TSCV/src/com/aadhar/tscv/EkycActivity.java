package com.aadhar.tscv;

import com.aadhaarconnect.bridge.gateway.model.KycResponse;
import com.social.dial.DatabaseClass;
import com.social.dial.modalhack.Owner;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class EkycActivity extends Activity implements OnClickListener {

	TextView lblName, lblEmail, lblGender, lblPhone, lblDob, lblCo, lblHouse,
			lblStreet, lblLm, lblLoc, lblPo, lblPc, lblVtc, lblDist, lblState;
	Button btnCancel, btnNext;

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
		btnNext = (Button) findViewById(R.id.btnNext);

		btnCancel.setOnClickListener(this);
		btnNext.setOnClickListener(this);

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
		case R.id.btnNext:
			// TODO add to database and move to start page
			//KYC_RESPONSE = null;
			if(IS_OWNER_SIGNUP){
				String address=KYC_RESPONSE.getKyc().getPoa().getCo()+KYC_RESPONSE.getKyc().getPoa().getHouse()+
						KYC_RESPONSE.getKyc().getPoa().getStreet()+KYC_RESPONSE.getKyc().getPoa().getDist();
				
				DatabaseClass db= DatabaseClass.getInstance(getApplicationContext());
				Owner owner=new Owner();
				owner.setUid(KYC_RESPONSE.getAadhaarId());
				owner.setPhone_num(KYC_RESPONSE.getKyc().getPoi().getPhone());
				owner.setPresent_add(address);
				db.addOwner(owner);
			}
			break;

		}

	}

}
