package com.vinish.policeapp;

import java.util.ArrayList;
import java.util.List;

import com.social.dial.DatabaseClass;
import com.social.dial.modalhack.Tenant;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class HistoryDemo extends Activity implements OnClickListener {

	EditText txtUidNum;
	Button btnCheck,btnClose;
	ListView listHistory;
	

	public static boolean IS_TENANT;
	public static boolean IS_SERVANT;
	public static boolean IS_OWNER;
	public static boolean IS_CASE;

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
		int id=arg0.getId();
		if (id==R.id.btnClose){
			finish();
		}
		ArrayAdapter<String> channelListAdapter = new ArrayAdapter<String>(
				this, android.R.layout.test_list_item);

		listHistory.setAdapter(channelListAdapter);

		DatabaseClass db = DatabaseClass.getInstance(getApplicationContext());
		ArrayList<Tenant> tenantList = db.getAllTenantByUid("9999999");
		for (Tenant tenant : tenantList) {
			String item = tenant.getAdhaar_id() + " " + tenant.getName() + " "
					+ tenant.getPhone_num();
			if (IS_CASE) {
				item = item + " " + " Murder";
			}
			channelListAdapter.add(item);
		}
		channelListAdapter.notifyDataSetChanged();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_history_check);
		init();
	}

	private void init() {

		txtUidNum = (EditText) findViewById(R.id.txtEnterAaadhaar);
		btnCheck = (Button) findViewById(R.id.btnCheck);
		listHistory = (ListView) findViewById(R.id.listDetails);
		btnClose=(Button) findViewById(R.id.btnClose);
		btnClose.setOnClickListener(this);
		btnCheck.setOnClickListener(this);
	}

}
