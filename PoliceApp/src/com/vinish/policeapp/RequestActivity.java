package com.vinish.policeapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class RequestActivity extends Activity implements OnClickListener{

	Button btnChkTenant,btnChkServant;
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		Intent i;
		int id =arg0.getId();
		switch(id){
		
		case R.id.btnCheckServantReq:
			i =new Intent(this,TenantsList.class);
			startActivity(i);
			break;
		case R.id.btnCheckTenantReq:
			i =new Intent(this,TenantsList.class);
			startActivity(i);
			break;
		}
		
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_requests);
		init();
	}
	
	private void init(){
		
		btnChkTenant=(Button) findViewById(R.id.btnCheckTenantReq);
		btnChkServant=(Button) findViewById(R.id.btnCheckServantReq);
		btnChkServant.setOnClickListener(this);
		btnChkTenant.setOnClickListener(this);
	}

}
