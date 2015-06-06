package com.aadhar.tscv.utility;



import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;

public class ReadPreferences {

	private String playerName;
	private int noOfWins;
	private int noOfLoses;
	private String guid;

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public void setNoOfWins(int noOfWins) {
		this.noOfWins = noOfWins;
	}

	public void setNoOfLoses(int noOfLoses) {
		this.noOfLoses = noOfLoses;
	}

	public int getNoOfWins() {
		return noOfWins;
	}

	public int getNoOfLoses() {
		return noOfLoses;
	}

	public static ReadPreferences GetPreferences(Activity activity) {
		String defaultNameValue = "Player";
		int defaultWins = 0;
		int defaultLoses = 0;
		String guidString = "";
		ReadPreferences readPreferences = new ReadPreferences();
		SharedPreferences sharedPref = activity
				.getPreferences(Context.MODE_PRIVATE);

/*		readPreferences.guid = sharedPref.getString(
				activity.getString(R.string.saved_device_guid), guidString);
		readPreferences.playerName = sharedPref.getString(
				activity.getString(R.string.saved_player_name),
				defaultNameValue);
		readPreferences.noOfWins = sharedPref.getInt(
				activity.getString(R.string.saved_player_wins), defaultWins);
		readPreferences.noOfLoses = sharedPref.getInt(
				activity.getString(R.string.saved_player_loses), defaultLoses);*/

		return readPreferences;
	}

	

	public void savePreferences(Activity activity) {

		SharedPreferences sharedPref = activity
				.getPreferences(Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPref.edit();
		/*editor.putString(activity.getString(R.string.saved_device_guid), guid);
		editor.putString(activity.getString(R.string.saved_player_name),
				playerName);
		editor.putInt(activity.getString(R.string.saved_player_wins), noOfWins);
		editor.putInt(activity.getString(R.string.saved_player_loses),
				noOfLoses);*/
		editor.commit();

	}
}
