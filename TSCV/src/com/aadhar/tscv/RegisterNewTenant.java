package com.aadhar.tscv;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class RegisterNewTenant extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_new_tenant);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add("Register").setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		return true;
	}
}

