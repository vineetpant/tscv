package com.aadhar.tscv.utility;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class DialogsNMsgs {

	
	public static void createAlertDialog(String msg,Activity ctx){
		AlertDialog alertDialog = new AlertDialog.Builder(ctx).create();
		alertDialog.setTitle("Status");
	

	
		alertDialog.setMessage(msg);
		alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});

		alertDialog.show();
		
	}
}
