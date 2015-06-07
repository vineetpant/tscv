package com.aadhar.tscv;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class LoginChoice extends Activity implements OnClickListener {

	private Button  btnSignUp, btnLogin;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_choice);
		init();
	}

	private void init() {
		
		btnSignUp = (Button) findViewById(R.id.btnLogin);
		btnLogin = (Button) findViewById(R.id.btnSignUp);

		btnSignUp.setOnClickListener(this);
		btnLogin.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int id = v.getId();
		Intent i;
		switch (id) {
		case R.id.btnSignUp:
			i=new Intent(this,SignupActivity.class);
			startActivity(i);
			
			break;

		case R.id.btnLogin:
			i=new Intent(this,LoginActivity.class);
			startActivity(i);
			
			break;
		}
	}
}
