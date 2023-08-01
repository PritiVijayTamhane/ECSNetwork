package com.example.final_project;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.telephony.SmsManager;

import java.time.LocalTime;

public class CompanyDatabase extends SQLiteOpenHelper {
    public  String currentname = "sayyam";
    public static String CompanyName;
    public static final String type = "2-wheeler";
    public static final String type2 = "3-wheeler";
    public static final String type3 = "4-wheeler";
    static String username, useremail, userphn, date, from, to, vehicle;

    public CompanyDatabase(Context context) {

        super(context, "ComapnyDatabase.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE Admins(Name VARCHAR2(50), Email VARCHAR2(100), MobileNo VARCHAR2(10), Password VARCHAR2(30))");
        sqLiteDatabase.execSQL("CREATE TABLE CompanyData( CompanyName VARCHAR(30) , CompanyLocation VARCHAR(30) , ManPower NUMBER(30) , TotalChargers NUMBER(20) ,  For4Wheeler NUMBER(10) , For3Wheeler NUMBER(10) , For2Wheeler NUMBER(10))");
        sqLiteDatabase.execSQL("CREATE TABLE LINFO (Uname VARCHAR(30), Umail VARCHAR(30), Password VARCHAR(10), UPhone INTEGER(10))");
        sqLiteDatabase.execSQL("CREATE TABLE BINFO (Uname VARCHAR(30),Date VARCHAR(30),FROM_T VARCHAR(5),TO_T VARCHAR(5),Vehicle VARCHAR(30))");
        sqLiteDatabase.execSQL("CREATE TABLE V2INFO(Uname VARCHAR(30),Date VARCHAR(10),FROM_T VARCHAR(5),TO_T VARCHAR(5),Vehicle VARCHAR(30))");
        sqLiteDatabase.execSQL("CREATE TABLE V3INFO(Uname VARCHAR(30),Date VARCHAR(10),FROM_T VARCHAR(5),TO_T VARCHAR(5),Vehicle VARCHAR(30))");
        sqLiteDatabase.execSQL("CREATE TABLE V4INFO(Uname VARCHAR(30),Date VARCHAR(10),FROM_T VARCHAR(5),TO_T VARCHAR(5),Vehicle VARCHAR(30))");
//        ContentValues cv = new ContentValues();
//        CompanyName = "ECS1";
//        String CompanyLocation = "Pune";
//        int ManPower = 90;
//        int TotalChargers = 90;
//        int For4Wheeler = 90;
//        int For2Wheeler = 90;
//        int For3Wheeler = 90;
//        cv.put("CompanyName", CompanyName);
//        cv.put("CompanyLocation", CompanyLocation);
//        cv.put("ManPower", ManPower);
//        cv.put("TotalChargers", TotalChargers);
//        cv.put("For4Wheeler", For4Wheeler);
//        cv.put("For3Wheeler", For3Wheeler);
//        cv.put("For2Wheeler", For2Wheeler);
//        sqLiteDatabase.insert("CompanyData", null, cv);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Admins");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS CompanyData");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS LINFO");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS BINFO");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS V2INFO");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS V3INFO");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS V4INFO");
    }

    @SuppressLint("Range")
    public String addbook(String name, String Date, String FROM_T, String TO_T, String Vehicle) {
        SQLiteDatabase mydb = this.getWritableDatabase();
//        name = currentname;
        String uphone = "9860053237";
        if (Vehicle.equals(type)) {
            if (name == null || Date == null || FROM_T == null || TO_T == null || Vehicle == null) {
                return "Invalid input";
            }
            Cursor c = mydb.rawQuery("SELECT * FROM V2INFO WHERE Uname = ? AND Date = ? AND FROM_T = ? AND TO_T = ? AND Vehicle = ?", new String[]{name, Date, FROM_T, TO_T, Vehicle});
            long result = 0;
            String slotaleready;
            if (c.moveToFirst()) {
                // If the cursor has at least one row, it means the slot is already booked
                slotaleready = "Slot already booked";
                return slotaleready;
            } else {
                ContentValues values = new ContentValues();
                values.put("Uname", name);
                values.put("Date", Date);
                values.put("FROM_T", FROM_T);
                values.put("TO_T", TO_T);
                values.put("Vehicle", Vehicle);
                int chargers = 0;
                Cursor cvail = mydb.rawQuery("SELECT * FROM CompanyData WHERE CompanyName = ?", new String[]{"ECSN1"});
                if (cvail.moveToFirst()) {
                    chargers = cvail.getInt(4);
                }
                if (chargers > 0) {
                    result = mydb.insert("V2INFO", null, values);
                    result = mydb.insert("BINFO", null, values);
                    mydb.execSQL("UPDATE CompanyData SET TotalChargers = TotalChargers - 1");
                    mydb.execSQL("UPDATE CompanyData SET For2Wheeler = For2Wheeler - 1");
                } else {
                    result = -1;
                }
                if (result == -1) {
                    return "Chargers not available";
                } else {
//                    currentname = name;
                    Cursor cursor = mydb.rawQuery("SELECT Uphone FROM LINFO WHERE Uname = ?", new String[]{name});
                    uphone = "9604472990";
                    if (cursor.moveToFirst()) {
                        uphone = cursor.getString(cursor.getColumnIndex("UPhone"));
                    }
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(uphone, null, "Hello " + name + ", your slot is booked", null, null);
                    return "Slot booked";
                }
            }
        } else if (Vehicle.equals(type2)) {

            if (name == null || Date == null || FROM_T == null || TO_T == null || Vehicle == null) {
                return "Invalid input";
            }
            Cursor c = mydb.rawQuery("SELECT * FROM V2INFO WHERE Uname = ? AND Date = ? AND FROM_T = ? AND TO_T = ? AND Vehicle = ?", new String[]{name, Date, FROM_T, TO_T, Vehicle});
            long result = 0;
            String slotaleready;
            if (c.moveToFirst()) {
                // If the cursor has at least one row, it means the slot is already booked
                slotaleready = "Slot already booked";
                return slotaleready;
            } else {
                ContentValues values = new ContentValues();
                values.put("Uname", name);
                values.put("Date", Date);
                values.put("FROM_T", FROM_T);
                values.put("TO_T", TO_T);
                values.put("Vehicle", Vehicle);
                int chargers = 0;
                Cursor cvail = mydb.rawQuery("SELECT * FROM CompanyData WHERE CompanyName = ?", new String[]{"ECSN1"});
                if (cvail.moveToFirst()) {
                    chargers = cvail.getInt(4);
                }
                if (chargers > 0) {
                    result = mydb.insert("V3INFO", null, values);
                    result = mydb.insert("BINFO", null, values);
                    mydb.execSQL("UPDATE CompanyData SET TotalChargers = TotalChargers - 1");
                    mydb.execSQL("UPDATE CompanyData SET For3Wheeler = For3Wheeler - 1");
                } else {
                    result = -1;
                }
                if (result == -1) {
                    return "Chargers not available";
                } else {
//                    currentname = name;
                    Cursor cursor = mydb.rawQuery("SELECT Uphone FROM LINFO WHERE Uname = ?", new String[]{name});
//                    uphone = "9604472990";
                    if (cursor.moveToFirst()) {
                        uphone = cursor.getString(cursor.getColumnIndex("UPhone"));
                    }
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(uphone, null, "Hello " + name + ", your slot is booked", null, null);
                    return "Slot booked";
                }
            }
        } else {
            if (name == null || Date == null || FROM_T == null || TO_T == null || Vehicle == null) {
                return "Invalid input";
            }
            Cursor c = mydb.rawQuery("SELECT * FROM V2INFO WHERE Uname = ? AND Date = ? AND FROM_T = ? AND TO_T = ? AND Vehicle = ?", new String[]{name, Date, FROM_T, TO_T, Vehicle});
            long result = 0;
            String slotaleready;
            if (c.moveToFirst()) {
                // If the cursor has at least one row, it means the slot is already booked
                slotaleready = "Slot already booked";
                return slotaleready;
            } else {
                ContentValues values = new ContentValues();
                values.put("Uname", name);
                values.put("Date", Date);
                values.put("FROM_T", FROM_T);
                values.put("TO_T", TO_T);
                values.put("Vehicle", Vehicle);
                int chargers = 0;
                Cursor cvail = mydb.rawQuery("SELECT * FROM CompanyData WHERE CompanyName = ?", new String[]{"ECSN1"});
                if (cvail.moveToFirst()) {
                    chargers = cvail.getInt(4);
                }
                if (chargers > 0) {
                    result = mydb.insert("V4INFO", null, values);
                    result = mydb.insert("BINFO", null, values);
                    mydb.execSQL("UPDATE CompanyData SET TotalChargers = TotalChargers - 1");
                    mydb.execSQL("UPDATE CompanyData SET For4Wheeler = For4Wheeler - 1");
                } else {
                    result = -1;
                }
                if (result == -1) {
                    return "Chargers not available";
                } else {
                    currentname = name;
                    Cursor cursor = mydb.rawQuery("SELECT * FROM LINFO WHERE Uname = ?", new String[]{name});
                    uphone = "9604472990";
                    if (cursor.moveToFirst()) {
                        uphone = cursor.getString(cursor.getColumnIndex("UPhone"));
                    }
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(uphone, null, "Hello " + name + ", your slot is booked", null, null);
                    return "Slot booked";
                }
            }

        }

    }

    public Boolean insertData(String compName, String compLoc, int comManPow, int chargAvl, int w4Charge, int w3Charge, int w2Charge) {
        SQLiteDatabase DB = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put("CompanyName", compName);
        contentValues.put("CompanyLocation", compLoc);
        contentValues.put("ManPower", comManPow);
        contentValues.put("TotalChargers", chargAvl);
        contentValues.put("For4Wheeler", w4Charge);
        contentValues.put("For3Wheeler", w3Charge);
        contentValues.put("For2Wheeler", w2Charge);


        long result = DB.insert("CompanyData", null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public String cancelBooking(String name, String Date, String FROM_T, String TO_T, String Vehicle) {
        SQLiteDatabase mydb = this.getWritableDatabase();
        LocalTime now = LocalTime.now();
        if (Vehicle.equals(type)) {

            if ((now.getHour() + now.getMinute()) >= (Integer.parseInt(FROM_T.substring(0, 1)) + Integer.parseInt(FROM_T.substring(3, 4)))) {

                SmsManager smsManager = SmsManager.getDefault();

                Cursor c = mydb.rawQuery("SELECT * FROM LINFO WHERE Uname = ?", new String[]{name});
                if (c.moveToFirst()) {
                    String uphone = c.getString(c.getColumnIndexOrThrow("UPhone"));
                    smsManager.sendTextMessage(uphone, null, "Hello " + name + "your slot is canceld ", null, null);
                    mydb.execSQL("DELETE  FROM V2INFO WHERE Uname =? AND  Date = ? AND FROM_T =? AND TO_T  = ? AND Vehicle = ? ", new String[]{name, Date, FROM_T, TO_T, Vehicle});
                    mydb.execSQL("UPDATE CompanyData SET For2Wheeler = For2Wheeler-1");
                } else {
                    return "error while retriving phone";
                }
            } else {
                return "You cannot cancel your booked slot now !" + FROM_T;
            }
        } else if (Vehicle.equals(type2)) {

            if ((now.getHour() + now.getMinute()) <= (Integer.parseInt(FROM_T.substring(0, 2)) + Integer.parseInt(FROM_T.substring(3, 5)))) {
                mydb.execSQL("DELETE  FROM V3INFO WHERE Uname =? AND  Date = ? AND FROM_T =? AND TO_T  = ? AND Vehicle = ? ", new String[]{name, Date, FROM_T, TO_T, Vehicle});
            } else {
                return "You cannot cancel our booked slot now !";
            }
        } else {

            if ((now.getHour() + now.getMinute()) <= (Integer.parseInt(FROM_T.substring(0, 2)) + Integer.parseInt(FROM_T.substring(3, 5)))) {
                mydb.execSQL("DELETE  FROM V4INFO WHERE Uname =? AND  Date = ? AND FROM_T =? AND TO_T  = ? AND Vehicle = ? ", new String[]{name, Date, FROM_T, TO_T, Vehicle});
            } else {
                return "You cannot cancel your booked slot now 1!";
            }
        }


        return "You cannot cancel your booked slot now 2!";
    }

    public Cursor getdata() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from CompanyData", null);
        return cursor;
    }

//    public boolean authenticateUser(String username, String password) {
//        SQLiteDatabase db = getReadableDatabase();
//        String[] columns = {"Umail"};
//        String selection = "Umail = ? AND Password = ?";
//        String[] selectionArgs = {username, password};
//        Cursor cursor = db.query("LINFO", columns, selection, selectionArgs, null, null, null);
//        boolean result = cursor.moveToFirst();
////        String[] columns2 = {"Uname"};
////        String selection2 = "Umail = ? AND Password = ?";
////        String[] selectionArgs2 = {username, password};
////        Cursor cursor2 = db.rawQuery("SELECT Uname FROM LINFO WHERE Umail = ? AND Password = ?", new String[]{username,password});
//        if (cursor.moveToFirst()) {
//            currentname = cursor.getString(0);
//        }
//        else
//        {
//            return false;
//        }
//        cursor.close();
//        return result;
//    }

    public boolean authenticateUser(String username, String password) {
        SQLiteDatabase db = getReadableDatabase();
        String[] columns = {"Umail"};
        String selection = "Umail = ? AND Password = ?";
        String[] selectionArgs = {username, password};
        Cursor cursor = db.query("LINFO", columns, selection, selectionArgs, null, null, null);
        boolean result = cursor.moveToFirst();
        Cursor cursor2 = db.rawQuery("SELECT * FROM LINFO WHERE Umail = ? AND Password = ?", new String[]{username,password});
        if (cursor2.moveToFirst()) {
            currentname = cursor2.getString(0);
        }
        else
        {
            return false;
        }
        cursor.close();
        return result;
    }

    public boolean registerUser(String name, String email, String password, String phone) {
        ContentValues values = new ContentValues();
        values.put("Uname", name);
        values.put("Umail", email);
        values.put("Password", password);
        values.put("UPhone", phone);

        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.insert("LINFO", null, values);
        db.close();
        boolean status;
        if (result == -1) {
            status = false;
        } else {
            // registration successful
            status = true;
//             currentname = getusername(name);
        }
        return status;
    }

    public boolean regAdmin(String Name, String Email, String PhnNo, String Password) {
        SQLiteDatabase DB = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put("Name", Name);
        contentValues.put("Email", Email);
        contentValues.put("MobileNo", PhnNo);
        contentValues.put("Password", Password);

        long status = DB.insert("Admins", null, contentValues);

        if (status == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean authenticateAdmin(String username, String password) {
        SQLiteDatabase db = getReadableDatabase();
        String[] columns = {"Email"};
        String selection = "Email = ? AND Password = ?";
        String[] selectionArgs = {username, password};
        Cursor cursor = db.query("Admins", columns, selection, selectionArgs, null, null, null);
        boolean result = cursor.moveToFirst();

//        currentUserEmail = username;
        cursor.close();
        return result;
    }

    @SuppressLint("Range")
    public String[] getAdminData(String email) {
        SQLiteDatabase DB = this.getReadableDatabase();

        String[] columns = {"*"};
        String selection = "Email = ?";
        String[] selectionArgs = {email};

        Cursor cursor = DB.query("Admins", columns, selection, selectionArgs, null, null, null);

        if (cursor.moveToFirst()) {
            username = cursor.getString(0);
            useremail = cursor.getString(1);
            userphn = cursor.getString(2);
        }
        cursor.close();

        String[] userData = {username, useremail, userphn};

        return userData;
    }

    @SuppressLint("Range")
    public String[] getUserData(String email) {
        SQLiteDatabase DB = this.getReadableDatabase();

        String[] columns = {"*"};
        String selection = "Umail = ?";
        String[] selectionArgs = {email};

        Cursor cursor = DB.query("LINFO", columns, selection, selectionArgs, null, null, null);

        if (cursor.moveToFirst()) {
            username = cursor.getString(0);
            useremail = cursor.getString(1);
            userphn = cursor.getString(3);
        }
        cursor.close();

        String[] userData = {username, useremail, userphn};

        return userData;
    }

    public Cursor slotbookedby() {
        SQLiteDatabase mydb = this.getWritableDatabase();

        Cursor cursor = mydb.rawQuery("SELECT * FROM BINFO WHERE Uname = ? ORDER BY Date ", new String[]{currentname});
        return cursor;
    }

}
