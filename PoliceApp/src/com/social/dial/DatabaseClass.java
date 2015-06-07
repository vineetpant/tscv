package com.social.dial;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

import com.social.dial.modalhack.Crime;
import com.social.dial.modalhack.Owner;
import com.social.dial.modalhack.PoliceStation;
import com.social.dial.modalhack.Servant;
import com.social.dial.modalhack.ServentHistory;
import com.social.dial.modalhack.Tenant;

/**
 * Created by Asad on 05-Jun-15.
 */
public class DatabaseClass extends SQLiteOpenHelper {

    private static final String DATABASE_NAME=Environment.getExternalStorageDirectory() +"/verification";
    private static final int DATABASE_VERSION=1;

    public static DatabaseClass dbManager;
    private static SQLiteDatabase db;

    public static DatabaseClass getInstance(Context context){
        if(dbManager==null)
            dbManager=new DatabaseClass(context);
        return dbManager;
    }

    private DatabaseClass(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        DatabaseClass.db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        DatabaseClass.db = sqLiteDatabase;
        
    }

    public void addTenant(Tenant tenant){
        ContentValues contentValues = new ContentValues();
        contentValues.put("adhaar_id",tenant.getAdhaar_id());
        contentValues.put("present_address", tenant.getPresent_address());
        contentValues.put("occupation", tenant.getOccupation());
        contentValues.put("phone_num", tenant.getPhone_num());
        contentValues.put("name", tenant.getName());
        contentValues.put("email", tenant.getEmail());
        contentValues.put("photo", tenant.getPhoto());
        getInstance(null).db.insert("tenant", null, contentValues);
    }

    public Tenant getTenantByUID(String uid) {
        Cursor cursor = db.rawQuery("SELECT * FROM tenant WHERE adhaar_id=?",new String[]{uid});
        cursor.moveToFirst();
        Tenant contentValues = new Tenant();
        contentValues.setAdhaar_id(cursor.getString(0));
        contentValues.setPresent_address(cursor.getString(1));
        contentValues.setOccupation(cursor.getString(2));
        contentValues.setPhone_num(cursor.getString(3));
        contentValues.setName(cursor.getString(4));
        contentValues.setEmail(cursor.getString(5));
        contentValues.setPhoto(cursor.getBlob(6));
        cursor.close();
        return contentValues;
    }

  
    public void addOwner(Owner owner) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("uid",owner.getUid());
        contentValues.put("present_add", owner.getPresent_add());
        contentValues.put("present_tenant", owner.getPresent_tenant());
        contentValues.put("phone_num", owner.getPhone_num());
        getInstance(null).db.insert("owner", null, contentValues);
    }

    public Owner getOwnerDetailByUID(String uid) {
        Cursor cursor = db.rawQuery("SELECT * FROM owner WHERE uid=?",new String[]{uid});
        cursor.moveToFirst();
        Owner contentValues = new Owner();
        contentValues.setUid(cursor.getString(0));
        contentValues.setPresent_add(cursor.getString(1));
        contentValues.setPresent_tenant(cursor.getString(2));
        contentValues.setPhone_num(cursor.getString(3));
        cursor.close();
        return contentValues;
    }

    public void addCrime(Crime crime){
        ContentValues contentValues = new ContentValues();
        contentValues.put("uid",crime.getUid());
        contentValues.put("date", crime.getDate());
        contentValues.put("case_status", crime.getCase_status());
        contentValues.put("description", crime.getDescription());
        contentValues.put("jail_term", crime.getJail_term());
        contentValues.put("police_station_id", crime.getPolice_station_id());
        db.insert("crime", null, contentValues);
    }

    public ArrayList<Crime> getAllCrimeByUID(String uid) {
        Cursor cursor = db.rawQuery("SELECT * FROM crime WHERE uid=?", new String[]{uid});
        cursor.moveToFirst();
        ArrayList<Crime> list = new ArrayList<Crime>();
        do {
            Crime crime = new Crime();
            crime.setUid(cursor.getString(0));
            crime.setDate(cursor.getString(1));
            crime.setCase_status(cursor.getString(2));
            crime.setDescription(cursor.getString(3));
            crime.setJail_term(cursor.getString(4));
            crime.setPolice_station_id(cursor.getString(5));
            list.add(crime);
        } while (cursor.moveToNext());
        return list;
    }

    public void addPoliceStation(PoliceStation policeStation) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("station_id", policeStation.getStation_id());
        contentValues.put("sho_uid", policeStation.getSho_uid());
        contentValues.put("address", policeStation.getAddress());
        contentValues.put("contact_num", policeStation.getContact_num());
        getInstance(null).db.insert("police_station", null, contentValues);
    }

    public PoliceStation getPoliceStationByShoUID(String uid) {
        Cursor cursor = db.rawQuery("SELECT * FROM police_station WHERE sho_uid=?",new String[]{uid});
        cursor.moveToFirst();
        PoliceStation contentValues = new PoliceStation();
        contentValues.setStation_id(cursor.getString(0));
        contentValues.setSho_uid(cursor.getString(1));
        contentValues.setAddress(cursor.getString(2));
        contentValues.setContact_num(cursor.getString(3));
        cursor.close();
        return contentValues;
    }

    public void addServent(Servant servant) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("uid", servant.getUid());
        contentValues.put("experience", servant.getExperience());
        contentValues.put("specialization", servant.getSpecialization());
        contentValues.put("height", servant.getHeight());
        contentValues.put("complexion", servant.getComplexion());
        contentValues.put("eyes", servant.getEyes());
        contentValues.put("home_town_police_station_id", servant.getHome_town_police_station_id());
        contentValues.put("local_relative_uid", servant.getLocal_relative_uid());
        db.insert("servant", null, contentValues);
    }

    public Servant getServentByUID(String uid){
        Cursor cursor = db.rawQuery("SELECT * FROM servant WHERE uid=?",new String[]{uid});
        cursor.moveToFirst();
        Servant contentValues = new Servant();
        contentValues.setUid(cursor.getString(0));
        contentValues.setExperience(cursor.getString(1));
        contentValues.setSpecialization(cursor.getString(2));
        contentValues.setHeight(cursor.getString(3));
        contentValues.setComplexion(cursor.getString(4));
        contentValues.setEyes(cursor.getString(5));
        contentValues.setHome_town_police_station_id(cursor.getString(6));
        contentValues.setLocal_relative_uid(cursor.getString(7));
        cursor.close();
        return contentValues;
    }

    public void addServentHistory(ServentHistory serventHistory){
        ContentValues contentValues = new ContentValues();
        contentValues.put("servant_id",serventHistory.getServant_id());
        contentValues.put("employer_uid", serventHistory.getEmployer_uid());
        contentValues.put("start_date", serventHistory.getStart_date());
        contentValues.put("end_date", serventHistory.getEnd_date());
        contentValues.put("verification_status_police", serventHistory.getVerification_status_police());
        getInstance(null).db.insert("servent_history", null, contentValues);
    }

    public ArrayList<ServentHistory> getServentHistoryByServentId(String servant_id) {
        Cursor cursor = db.rawQuery("SELECT * FROM servent_history WHERE servant_id=?", new String[]{servant_id});
        cursor.moveToFirst();
        ArrayList<ServentHistory> list = new ArrayList<ServentHistory>();
        do {
            ServentHistory serventHistory = new ServentHistory();
            serventHistory.setServant_id(cursor.getString(0));
            serventHistory.setEmployer_uid(cursor.getString(1));
            serventHistory.setStart_date(cursor.getString(2));
            serventHistory.setEnd_date(cursor.getString(3));
            serventHistory.setVerification_status_police(cursor.getString(4));
            list.add(serventHistory);
        } while (cursor.moveToNext());
        return list;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    
    public ArrayList<Tenant> getAllTenantByUid(String uid) {
        Cursor cursor = db.rawQuery("SELECT * FROM tenant ",new String[]{});
        cursor.moveToFirst();
        ArrayList<Tenant> list = new ArrayList<Tenant>();
        do {
        Tenant contentValues = new Tenant();
        contentValues.setAdhaar_id(cursor.getString(0));
        contentValues.setPresent_address(cursor.getString(1));
        contentValues.setOccupation(cursor.getString(2));
        contentValues.setPhone_num(cursor.getString(3));
        contentValues.setName(cursor.getString(4));
        contentValues.setEmail(cursor.getString(5));
        contentValues.setPhoto(cursor.getBlob(6));
        list.add(contentValues);
        } while (cursor.moveToNext());
        cursor.close();
        return list;
    
    }
}
