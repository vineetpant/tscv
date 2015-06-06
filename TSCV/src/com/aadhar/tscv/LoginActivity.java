package com.aadhar.tscv;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends Activity implements OnClickListener{

	public static final int AADHAAR_CONNECT_AUTH_REQUEST = 1000;
	private static final String BASE_URL="https://ac.khoslalabs.com/hackgate/hackathon";
	
	private EditText txtEnterAadhaarNum,txtGeneratedOtp;
	private Button btnOtpAuth,btnFingerAuth,btnSignUp,btnLogin;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signup);
		init();
	}
	
	private void init(){
		txtEnterAadhaarNum=(EditText) findViewById(R.id.txtEnterAaadhaar);
		txtGeneratedOtp=(EditText) findViewById(R.id.txtOtpReceived);
		
		btnOtpAuth=(Button) findViewById(R.id.btnOtpGen);
		btnFingerAuth=(Button)findViewById(R.id.btnFpCpture);
		btnSignUp=(Button)findViewById(R.id.btnSignUp);
		btnLogin=(Button)findViewById(R.id.btnLogin);
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int id = v.getId();
		
		switch(id){
		case R.id.btnSignUp:
			break;
		case R.id.btnLogin:
			break;
		case R.id.btnFpCpture:
			
			break;
		case R.id.btnOtpGen:
			break;
		
		}
		
		
		
	}
	
	
}
