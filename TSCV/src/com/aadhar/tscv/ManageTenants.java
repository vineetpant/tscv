package com.aadhar.tscv;

import android.app.Activity;
import android.content.Intent;
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
		init();
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
		Intent i;
		int id = arg0.getId();
		switch (id) {

		case R.id.btnRegisterTenant:
			i =new Intent(this,RegisterNewTenant.class);
			startActivity(i);
			break;
		case R.id.btnUnregisterTenant:
			i =new Intent(this,UnregisterTenant.class);
			startActivity(i);
			break;
		case R.id.btnViewCurrentTenants:
			i =new Intent(this,TenantsList.class);
			startActivity(i);
			break;
		case R.id.btnViewPreviousRecords:
			i =new Intent(this,TenantsList.class);
			startActivity(i);
			break;
		case R.id.btnTenantVerification:
			TenantsList.IS_PENDING_VERIF=true;
			i =new Intent(this,TenantsList.class);
			startActivity(i);
			break;

		case R.id.btnUpdateCurrentTenants:
			break;
		}

	}

}
