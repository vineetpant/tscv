package com.vinish.policeapp;

import java.util.ArrayList;

import com.social.dial.DatabaseClass;
import com.social.dial.modalhack.Tenant;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class TenantsList extends Activity implements OnClickListener {

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		finish();
		
	}

	public static boolean IS_PENDING_VERIF;
	ListView listViewTenant;
	Button btnClose;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tenant_grid);
		init();
	}

	private void init(){
		btnClose=(Button) findViewById(R.id.btnClose);
		btnClose.setOnClickListener(this);
		listViewTenant=(ListView) findViewById(R.id.gridPreviousTenants);
		
		ArrayAdapter<String> channelListAdapter = new ArrayAdapter<String>(this, android.R.layout.test_list_item);
        
		listViewTenant.setAdapter(channelListAdapter);

		DatabaseClass db= DatabaseClass.getInstance(getApplicationContext());
		ArrayList<Tenant> tenantList=db.getAllTenantByUid("9999999");
        for (Tenant tenant : tenantList) {
           String item=tenant.getAdhaar_id()+" "+tenant.getName()+" "+tenant.getPhone_num();
           if(IS_PENDING_VERIF){
        	   item=item+" "+" Pending";
           }
           channelListAdapter.add(item);
        }
        channelListAdapter.notifyDataSetChanged();

	}
	
}
