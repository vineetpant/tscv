package com.aadhar.tscv;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class OwnerAfterLoginActivity extends Activity implements OnClickListener{
	
	Button btnManageTenants,btnManageServants;

	@Override
	protected void onCreate(Bundle savedInstanceState) {	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_owner_after_);
		init();
	}
	
	private void init(){
		btnManageServants=(Button) findViewById(R.id.btnManageServants);
		btnManageTenants=(Button) findViewById(R.id.btnManageTenants);
		
		btnManageServants.setOnClickListener(this);
		btnManageServants.setOnClickListener(this);
		
		
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		int id = arg0.getId();
		Intent i;
		switch(id){
		
		case R.id.btnManageServants:
			i=new Intent(this,ManageServants.class);
			startActivity(i);
			break;
		case R.id.btnManageTenants:
			i=new Intent(this,ManageTenants.class);
			startActivity(i);
			break;
		}
		
	}
}
