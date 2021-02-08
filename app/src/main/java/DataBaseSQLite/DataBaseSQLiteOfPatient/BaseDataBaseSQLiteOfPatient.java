package DataBaseSQLite.DataBaseSQLiteOfPatient;


import Utils.CustomTime;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
import DataBaseSQLite.DataBaseExpressions;
import Patient.Patient;


import java.sql.SQLOutput;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public abstract class BaseDataBaseSQLiteOfPatient extends SQLiteOpenHelper  {


    private static final int dataBaseVersion = 1;
    private static final String dataBaseName = DataBaseExpressions.databaseNameOfPatient;
    private static final String dataBasePatient_id = "ID";
    private static final String dataBasePatient_TC_NO = "TC_NO";
    private static final String dataBasePatient_name = "AD";
    private static final String dataBasePatient_surname = "SOYAD";
    private static final String dataBasePatient_birthday = "DOGUM_TARIHI";
    private static final String dataBasePatient_sex = "CINSIYET";
    private static final String dataBasePatient_dependency = "BAGIMLILIK_DURUMU";
    private static final String dataBasePatient_final_situation = "SON_DURUMU";
    private static final String dataBasePatient_created_date = "ISLEM_TARIHI";

    // bu ikisini constructor la birlikte hangi tablo kullanacaksa o kullansın diye omnlara bıraktım...
    private static String dataBasePatient_table_name = null;
    public static String dataBasePatient_Create_Table = null;




    String currentTime= CustomTime.getTime();
    private Context context;

    SimpleDateFormat sekil = new SimpleDateFormat("dd/MM/yyyy");


    public BaseDataBaseSQLiteOfPatient(@Nullable Context context, String tableName) {

        super(context, dataBaseName, null, dataBaseVersion);

        this.context=context;
        // açıklama :
        // burda aynı veri tabanının farklı tablolarını kullanayım diye base veri tabanı tasarladım ammma velakin bir türlü tablo ismini altı
        //sınıftan aktaramadım ne yaptıysam null dönderdi table nama e sen öle yaparsa bende senin sorgu tablonu değiştiririm dsedim ve constructoruın içinde gelen
        // table name i aldım sorgucümlesine çaktım ve table name ide bu şekilde güncellemiş oldum sıkıntı yok yani ::))))))))))

        dataBasePatient_table_name = tableName;

        dataBasePatient_Create_Table = "CREATE TABLE IF NOT EXISTS "
                + tableName
                + " ( "
                + dataBasePatient_id
                + " INTEGER, "
                + dataBasePatient_TC_NO
                + " TEXT UNIQUE NOT NULL, "
                + dataBasePatient_name
                + " TEXT, "
                + dataBasePatient_surname
                + " TEXT, "
                + dataBasePatient_birthday
                + " TEXT, "
                +dataBasePatient_sex
                + " TEXT, "
                +dataBasePatient_dependency
                + " TEXT, "
                +dataBasePatient_final_situation
                + " TEXT, "
                + dataBasePatient_created_date
                + " TEXT, "
                + "PRIMARY KEY( "
                + dataBasePatient_id
                + " AUTOINCREMENT) );";

    }


    @Override
    public void onCreate(SQLiteDatabase db) {


        db.execSQL(dataBasePatient_Create_Table);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + dataBasePatient_table_name);
        this.onCreate(db);


    }








    // alt sınıflar için gerekli metodlar

    public boolean addPatient(Patient patient) {
        SQLiteDatabase db = this.getWritableDatabase();
        if (db != null) {
            ContentValues values = new ContentValues();


            values.put(dataBasePatient_TC_NO, patient.tc_no);
            values.put(dataBasePatient_name, patient.name);
            values.put(dataBasePatient_surname, patient.surname);
            values.put(dataBasePatient_birthday, patient.birthday);
            values.put(dataBasePatient_sex, patient.sex);
            values.put(dataBasePatient_dependency, patient.dependency);
            values.put(dataBasePatient_final_situation,patient.final_situation);
            values.put(dataBasePatient_created_date, currentTime); // işlem zamanınıve tarihini kendisi otoamtik atıcak

            long i = db.insert(dataBasePatient_table_name, null, values);
            db.close();
            if (i > 0) {

                return true;
            } else {
                return false;
            }

        } else {
            return false;
        }


    }

    public boolean deletePatient(Patient patient) {
        SQLiteDatabase db = this.getWritableDatabase();

        int i = db.delete(dataBasePatient_table_name, dataBasePatient_TC_NO + " = ?", new String[]{String.valueOf(patient.tc_no)});

        db.close();

        if (i > 0) {
            return true;
        } else {
            return false;
        }


    }

    public void deleteAllPatient() {
        SQLiteDatabase db = this.getWritableDatabase();

       String query="DELETE FROM "+dataBasePatient_table_name;

       db.execSQL(query);

    }

    public boolean updatePatient(Patient patient_old, Patient patient_new) {
        // dönen int değri güncellemeden etkilenen değr sayısıdır.
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();


        values.put(dataBasePatient_TC_NO, patient_new.tc_no);
        values.put(dataBasePatient_name, patient_new.name);
        values.put(dataBasePatient_surname, patient_new.surname);
        values.put(dataBasePatient_birthday, patient_new.birthday);
        values.put(dataBasePatient_sex, patient_new.sex);
        values.put(dataBasePatient_dependency, patient_new.dependency);
        values.put(dataBasePatient_final_situation,patient_new.final_situation);
        values.put(dataBasePatient_created_date,currentTime); // işlem zamanınıve tarihini kendisi otoamtik atıcak


        int i = db.update(dataBasePatient_table_name, values, dataBasePatient_TC_NO + "=? ", new String[]{String.valueOf(patient_old.tc_no)});
        db.close();

        if (i > 0) {
            return true;
        } else {
            return false;
        }


    }

    public boolean updatePatientDependency(Patient patient_old,String dependency) {
        // dönen int değri güncellemeden etkilenen değr sayısıdır.
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(dataBasePatient_dependency,dependency);
       // values.put(dataBasePatient_created_date, currentTime); // işlem zamanınıve tarihini kendisi otoamtik atıcak

        int i = db.update(dataBasePatient_table_name, values, dataBasePatient_TC_NO + " = ?", new String[]{String.valueOf(patient_old.tc_no)});
        db.close();

        if (i > 0) {
            return true;
        } else {
            return false;
        }


    }

    public boolean updatePatientFinalSituations(Patient patient_old,String final_situation) {
        // dönen int değri güncellemeden etkilenen değr sayısıdır.
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(dataBasePatient_final_situation,final_situation);
        //values.put(dataBasePatient_created_date, currentTime); // işlem zamanınıve tarihini kendisi otoamtik atıcak

       /* String query="UPDATE "+ dataBasePatient_table_name + " SET " +dataBasePatient_final_situation+"="+"'"+final_situation+"'" +" WHERE " + dataBasePatient_TC_NO
        +"=" +"'"+patient_old.tc_no+"'";

        db.execSQL(query);*/

        int i = db.update(dataBasePatient_table_name, values, dataBasePatient_TC_NO + " = ?", new String[]{patient_old.tc_no});
        db.close();

        if (i > 0) {
            return true;
        } else {
            return false;
        }



    }

    public Patient getPatient(String patient_tc)  {

        SQLiteDatabase db = this.getReadableDatabase();
        Patient  patient= new Patient();
        String query = "SELECT * FROM " + dataBasePatient_table_name + " WHERE "+dataBasePatient_TC_NO+"=" + "'" +patient_tc +"'";
        Cursor cursor = db.rawQuery(query, null);




        if (cursor!= null && cursor.getCount() > 0)
        {
            cursor.moveToFirst();


            patient.id=cursor.getInt(0);
            patient.tc_no=cursor.getString(1);
            patient.name=cursor.getString(2);
            patient.surname=cursor.getString(3);
            patient.birthday =cursor.getString(4);

            if(patient.birthday!=null)
            {
                String date=patient.birthday;


                try {
                    patient.age=CustomTime.getAge(sekil.parse(date));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }

            else {
                patient.age=-1;
            }
            patient.sex=cursor.getString(5);
            patient.dependency=cursor.getString(6);
            patient.final_situation=cursor.getString(7);
            patient.created_date =cursor.getString(8);


        }

        cursor.close();


        if(patient!=null)
        {
            return patient;
        }
        else
        {
            return null;
        }





    }

    public ArrayList<Patient> getAllPatient()  {

        SQLiteDatabase db = this.getReadableDatabase();
        Patient  patient;
        ArrayList<Patient> allPatient= new ArrayList<>();
        String query = "SELECT * FROM " + dataBasePatient_table_name;
        Cursor cursor = db.rawQuery(query, null);




        if (cursor!= null && cursor.getCount() > 0)
        {
            cursor.moveToFirst();
             do {
                 {
                     patient=new Patient();



                     patient.id=cursor.getInt(0);
                     patient.tc_no=cursor.getString(1);
                     patient.name=cursor.getString(2);
                     patient.surname=cursor.getString(3);
                     patient.birthday =cursor.getString(4);

                     if(patient.birthday!=null)
                     {
                         String date=patient.birthday;


                         try {
                             patient.age=CustomTime.getAge(sekil.parse(date));
                         } catch (ParseException e) {
                             e.printStackTrace();
                         }

                     }

                     else {
                         patient.age=-1;
                     }
                     patient.sex=cursor.getString(5);
                     patient.dependency=cursor.getString(6);
                     patient.final_situation=cursor.getString(7);
                     patient.created_date =cursor.getString(8);


                     allPatient.add(patient);
                 }
             }
             while (cursor.moveToNext());

        }

        cursor.close();


        if(allPatient!=null)
        {
            return allPatient;
        }
        else
        {
            return null;
        }
    }

    public ArrayList<Patient> getAllPatientDependency(String dependency)  {

        SQLiteDatabase db = this.getReadableDatabase();
        Patient  patient;
        ArrayList<Patient> allPatient= new ArrayList<>();
        String query = "SELECT * FROM " + dataBasePatient_table_name + " WHERE "+dataBasePatient_dependency+"=" + "'" +dependency+"'";
        Cursor cursor = db.rawQuery(query, null);




        if (cursor!= null && cursor.getCount() > 0)
        {
            cursor.moveToFirst();
            do {
                {
                    if(!cursor.getString(7).matches(Patient.EX))
                    {
                        patient=new Patient();



                        patient.id=cursor.getInt(0);
                        patient.tc_no=cursor.getString(1);
                        patient.name=cursor.getString(2);
                        patient.surname=cursor.getString(3);
                        patient.birthday =cursor.getString(4);

                        if(patient.birthday!=null)
                        {
                            String date=patient.birthday;


                            try {
                                patient.age=CustomTime.getAge(sekil.parse(date));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }

                        }

                        else {
                            patient.age=-1;
                        }
                        patient.sex=cursor.getString(5);
                        patient.dependency=cursor.getString(6);
                        patient.final_situation=cursor.getString(7);
                        patient.created_date =cursor.getString(8);


                        allPatient.add(patient);

                    }


                }
            }
            while (cursor.moveToNext());

        }

        cursor.close();


        if(allPatient!=null)
        {
            return allPatient;
        }
        else
        {
            return null;
        }
    }

    public ArrayList<Patient> getAllPatientFinalSituation(String final_situation) {

        SQLiteDatabase db = this.getReadableDatabase();
        Patient  patient;
        ArrayList<Patient> allPatient= new ArrayList<>();
        String query = "SELECT * FROM " + dataBasePatient_table_name + " WHERE "+dataBasePatient_final_situation+"=" + "'" +final_situation+"'";
        Cursor cursor = db.rawQuery(query, null);




        if (cursor!= null && cursor.getCount() > 0)
        {
            cursor.moveToFirst();
            do {
                {
                    patient=new Patient();



                    patient.id=cursor.getInt(0);
                    patient.tc_no=cursor.getString(1);
                    patient.name=cursor.getString(2);
                    patient.surname=cursor.getString(3);
                    patient.birthday =cursor.getString(4);

                    if(patient.birthday!=null)
                    {
                        String date=patient.birthday;


                        try {
                            patient.age=CustomTime.getAge(sekil.parse(date));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                    }

                    else {
                        patient.age=-1;
                    }
                    patient.sex=cursor.getString(5);
                    patient.dependency=cursor.getString(6);
                    patient.final_situation=cursor.getString(7);
                    patient.created_date =cursor.getString(8);


                    allPatient.add(patient);
                }
            }
            while (cursor.moveToNext());

        }

        cursor.close();


        if(allPatient!=null)
        {
            return allPatient;
        }
        else
        {
            return null;
        }
    }



    public boolean isContainPatient(Patient patient) {

        boolean sonuc=false;

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + dataBasePatient_table_name;
        Cursor cursor = db.rawQuery(query, null);


        if (cursor!= null && cursor.getCount() > 0)
        {
            cursor.moveToFirst();
            do {
                if(patient.tc_no.equals(cursor.getString(1)))

                {
                    sonuc=true;
                    break;
                }




            }
            while (cursor.moveToNext());
        }

        cursor.close();

        return sonuc;

    }


}
