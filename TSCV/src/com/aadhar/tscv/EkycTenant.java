package com.aadhar.tscv;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aadhaarconnect.bridge.gateway.model.KycResponse;
import com.social.dial.DatabaseClass;
import com.social.dial.modalhack.Owner;
import com.social.dial.modalhack.Tenant;

public class EkycTenant extends Activity implements OnClickListener {

	TextView lblName, lblEmail, lblGender, lblPhone, lblDob, lblCo, lblHouse,
			lblStreet, lblLm, lblLoc, lblPo, lblPc, lblVtc, lblDist, lblState;
	Button btnCancel, btnRegister;

	EditText txtOccupation;
	ImageView imgPhoto;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tenant_details_form);
		init();
	}

	public static KycResponse KYC_RESPONSE = null;

	public static boolean IS_OWNER_SIGNUP = false;

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
		txtOccupation= (EditText) findViewById(R.id.txtOccupation);

		btnCancel = (Button) findViewById(R.id.btnCancel);
		btnRegister = (Button) findViewById(R.id.btnRegisterTenant);

		btnCancel.setOnClickListener(this);
		btnRegister.setOnClickListener(this);

		
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
		case R.id.btnRegisterTenant:
			// TODO add to database and move to start page
//			
		
			if(KYC_RESPONSE!=null){
				String address = KYC_RESPONSE.getKyc().getPoa().getCo()
						+ KYC_RESPONSE.getKyc().getPoa().getHouse()
						+ KYC_RESPONSE.getKyc().getPoa().getStreet()
						+ KYC_RESPONSE.getKyc().getPoa().getDist();

				DatabaseClass db = DatabaseClass
						.getInstance(getApplicationContext());
			/*	Owner owner = new Owner();
				owner.setUid(KYC_RESPONSE.getAadhaarId());
				owner.setPhone_num(KYC_RESPONSE.getKyc().getPoi().getPhone());
				owner.setPresent_add(address);
				db.addOwner(owner);*/
				
				Tenant tenant=new Tenant();
				tenant.setAdhaar_id(KYC_RESPONSE.getAadhaarId());
				tenant.setEmail(KYC_RESPONSE.getKyc().getPoi().getPhone());
				tenant.setName(KYC_RESPONSE.getKyc().getPoi().getName());
				tenant.setOccupation(txtOccupation.getText().toString());
				tenant.setPhoto(KYC_RESPONSE.getKyc().getPhoto());
			
				db.addTenant(tenant);
			}
				showToast("Tenant Registered", Toast.LENGTH_LONG);
				finish();
				KYC_RESPONSE = null;
					break;

		}

	}
	
	private void showToast(String text, int duration) {
		Toast toast = Toast.makeText(this, text, duration);
		toast.show();
	}
}
