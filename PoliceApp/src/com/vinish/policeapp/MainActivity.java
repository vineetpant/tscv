package com.vinish.policeapp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener{

	Button btnHistory,btnRequest;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
	}

	private void init(){
		
		btnHistory=(Button) findViewById(R.id.btnHistory);
		btnRequest=(Button) findViewById(R.id.btnRequest);
		btnHistory.setOnClickListener(this);
		btnRequest.setOnClickListener(this);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		Intent i;
		int id =arg0.getId();
		switch(id){
		
		case R.id.btnHistory:
			i=new Intent(this,HistoryActivity.class);
			startActivity(i);
			break;
		case R.id.btnRequest:
			i=new Intent(this,RequestActivity.class);
			startActivity(i);
			break;
		}
		
	}

	
}
