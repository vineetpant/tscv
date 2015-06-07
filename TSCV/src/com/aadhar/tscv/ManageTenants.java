package com.aadhar.tscv;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ManageTenants extends Activity implements OnClickListener {

	Button btnRegisterTenant, btnUnregisterTenant, btnViewCurrentTenant,
			btnViewPreviousTenants, btnViewPendingVerifications,
			btnUpdateCurrentRecords;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tenant_mangage);
	}

	private void init() {
		btnRegisterTenant = (Button) findViewById(R.id.btnRegisterTenant);
		btnUnregisterTenant = (Button) findViewById(R.id.btnUnregisterTenant);
		btnViewCurrentTenant = (Button) findViewById(R.id.btnViewCurrentTenants);
		btnViewPreviousTenants = (Button) findViewById(R.id.btnViewPreviousRecords);
		btnViewPendingVerifications = (Button) findViewById(R.id.btnTenantVerification);
		btnUpdateCurrentRecords = (Button) findViewById(R.id.btnUpdateCurrentTenants);

		btnRegisterTenant.setOnClickListener(this);
		btnUnregisterTenant.setOnClickListener(this);
		btnViewCurrentTenant.setOnClickListener(this);
		btnViewPreviousTenants.setOnClickListener(this);
		btnViewPendingVerifications.setOnClickListener(this);
		btnUpdateCurrentRecords.setOnClickListener(this);

	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		int id = arg0.getId();
		switch (id) {

		case R.id.btnRegisterTenant:
			break;
		case R.id.btnUnregisterTenant:
			break;
		case R.id.btnViewCurrentTenants:
			break;
		case R.id.btnViewPreviousRecords:
			break;
		case R.id.btnTenantVerification:
			break;

		case R.id.btnUpdateCurrentTenants:
			break;
		}

	}

}
