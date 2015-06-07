package com.aadhar.tscv;

import com.social.dial.DatabaseClass;
import com.social.dial.modalhack.Tenant;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class ServantAdditionalDetails extends Activity implements OnClickListener{
	Button btnCancel, btnRegister;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_servant_additional_details);
		init();
	}

	private void init(){
		
		btnCancel = (Button) findViewById(R.id.btnCancel);
		btnRegister = (Button) findViewById(R.id.btnRegister);
		
		btnCancel.setOnClickListener(this);
		btnRegister.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		finish();

	}
}
