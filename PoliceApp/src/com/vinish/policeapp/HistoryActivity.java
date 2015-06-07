package com.vinish.policeapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class HistoryActivity extends Activity implements OnClickListener {

	Button btnOwnerHistory, btnTenantHistory, btnServantHistory, btnCheckCases;

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		Intent i;
		int id = arg0.getId();
		switch(id){
		
		case R.id.btnOwnerHistory:
			i =new Intent(this,HistoryDemo.class);
			startActivity(i);
			break;
		case R.id.btnTenantHistory:
			i =new Intent(this,HistoryDemo.class);
			startActivity(i);
			break;
		case R.id.btnCheckServantHistory:
			i =new Intent(this,HistoryDemo.class);
			startActivity(i);
			break;
		case R.id.btnCriminalCases:
			HistoryDemo.IS_CASE=true;
			i =new Intent(this,HistoryDemo.class);
			startActivity(i);
			break;
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_history);
		init();
	}

	private void init() {
		btnOwnerHistory = (Button) findViewById(R.id.btnOwnerHistory);
		btnTenantHistory = (Button) findViewById(R.id.btnTenantHistory);
		btnServantHistory = (Button) findViewById(R.id.btnCheckServantHistory);
		btnCheckCases = (Button) findViewById(R.id.btnCriminalCases);
		
		btnOwnerHistory.setOnClickListener(this);
		btnTenantHistory.setOnClickListener(this);
		btnServantHistory.setOnClickListener(this);
		btnCheckCases.setOnClickListener(this);
		

	}

}
