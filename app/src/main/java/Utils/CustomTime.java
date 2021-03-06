package Utils;

import Patient.VisitInformations;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import androidx.annotation.RequiresApi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

public class CustomTime {
    private final SimpleDateFormat sekil = new SimpleDateFormat("dd.MM.yyyy - h:m:s");
    private final GregorianCalendar tarih = new GregorianCalendar();


    public static  String LOGIN_CONTROL_STARTMINUTE="LoginStartMinute";
    public static  String LOGIN_CONTROL_HOWMANY_MINUTE="LoginHowManyTime";
    public static  String LOGIN_CONTROL_GET_MINUTE="ka�_dakka_ge�mi�";
    public static  String LOGIN_CONTROL_GET_HOWMANY_LOGIN="ka�_kere_login_olmu�";



    public static int calculateAge(LocalDate birthDate, LocalDate currentDate) {
        if ((birthDate != null) && (currentDate != null)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                return Period.between(birthDate, currentDate).getYears();
            }
            else
            {
                return  0;
            }
        } else {
            return 0;


        }



    }

    public static String getTime() {
        SimpleDateFormat sekil = new SimpleDateFormat("dd.MM.yyyy - h:m:s");
        GregorianCalendar tarih = new GregorianCalendar();


        return sekil.format(tarih.getTime());
    }

    public static int getCurrentMinute()
    {
        SimpleDateFormat sekil = new SimpleDateFormat("m");
        GregorianCalendar tarih = new GregorianCalendar();


        return Integer.parseInt(sekil.format(tarih.getTime()));
    }

    public static  Date getNowDate()
    {
        SimpleDateFormat sekil = new SimpleDateFormat("dd.MM.yyyy");
        GregorianCalendar tarih = new GregorianCalendar();

        Date dateNow=null;

        try {
            dateNow=sekil.parse(sekil.format(tarih.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return dateNow;
    }

    public static String getTimeJustDate() {
        SimpleDateFormat sekil = new SimpleDateFormat("dd.MM.yyyy");
        GregorianCalendar tarih = new GregorianCalendar();


        return sekil.format(tarih.getTime());
    }

    public static String getDate() {
        SimpleDateFormat sekil = new SimpleDateFormat("dd.MM.yyyy");
        GregorianCalendar tarih = new GregorianCalendar();


        return sekil.format(tarih.getTime());
    }

    public static String getTomorrow() {
        SimpleDateFormat sekil = new SimpleDateFormat("dd.MM.yyyy");
        GregorianCalendar tarih = new GregorianCalendar();
        sekil.format(tarih.getTime());
        tarih.add(Calendar.DAY_OF_MONTH, 1);

        Date yarin = tarih.getTime();


        return sekil.format(yarin);
    }

    public static String getLastDay() {
        SimpleDateFormat sekil = new SimpleDateFormat("dd.MM.yyyy");
        GregorianCalendar tarih = new GregorianCalendar();
        sekil.format(tarih.getTime());
        tarih.add(Calendar.DAY_OF_MONTH, -1);

        Date yarin = tarih.getTime();


        return sekil.format(yarin);
    }

    public static String getAnyDay(int day) {
        SimpleDateFormat sekil = new SimpleDateFormat("dd/MM/yyyy");
        GregorianCalendar tarih = new GregorianCalendar();
        sekil.format(tarih.getTime());
        tarih.add(Calendar.DAY_OF_MONTH, day);


        Date yarin = tarih.getTime();


        return sekil.format(yarin);
    }

    public static int getAge(Date dateOfBirth) {

        Calendar today = Calendar.getInstance();
        Calendar birthDate = Calendar.getInstance();

        int age = 0;

        birthDate.setTime(dateOfBirth);
        if (birthDate.after(today)) {
            throw new IllegalArgumentException("Can't be born in the future");
        }

        age = today.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);

        // If birth date is greater than todays date (after 2 days adjustment of leap year) then decrement age one year
        if ((birthDate.get(Calendar.DAY_OF_YEAR) - today.get(Calendar.DAY_OF_YEAR) > 3) ||
                (birthDate.get(Calendar.MONTH) > today.get(Calendar.MONTH))) {
            age--;

            // If birth date and todays date are of same month and birth day of month is greater than todays day of month then decrement age
        } else if ((birthDate.get(Calendar.MONTH) == today.get(Calendar.MONTH)) &&
                (birthDate.get(Calendar.DAY_OF_MONTH) > today.get(Calendar.DAY_OF_MONTH))) {
            age--;
        }

        return age;
    }

    public static int getAge(String dateOfBirthh) {

        SimpleDateFormat simpleDateFormat;

        System.out.println(dateOfBirthh.charAt(2));

        if(dateOfBirthh.charAt(2)=='.')
        {
            simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        }
        else if(dateOfBirthh.charAt(2)=='/')
        {
           simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        }

        else
        {
            simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        }




        Date dateOfBirth = null;
        try {
            dateOfBirth = simpleDateFormat.parse(dateOfBirthh);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar today = Calendar.getInstance();
        Calendar birthDate = Calendar.getInstance();

        int age = 0;

        birthDate.setTime(dateOfBirth);
        if (birthDate.after(today)) {
            throw new IllegalArgumentException("Can't be born in the future");
        }

        age = today.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);

        // If birth date is greater than todays date (after 2 days adjustment of leap year) then decrement age one year
        if ((birthDate.get(Calendar.DAY_OF_YEAR) - today.get(Calendar.DAY_OF_YEAR) > 3) ||
                (birthDate.get(Calendar.MONTH) > today.get(Calendar.MONTH))) {
            age--;

            // If birth date and todays date are of same month and birth day of month is greater than todays day of month then decrement age
        } else if ((birthDate.get(Calendar.MONTH) == today.get(Calendar.MONTH)) &&
                (birthDate.get(Calendar.DAY_OF_MONTH) > today.get(Calendar.DAY_OF_MONTH))) {
            age--;
        }

        return age;
    }

    public static  String selectLastDate(ArrayList<Date> dateArrayList)
    {

        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd.MM.yyyy");

        String son_ziyaret_tarihi="00.00.0000";

        Date lastDate= null;
        Date bufferDate;
        try {
            lastDate = simpleDateFormat.parse("01.01.1900");
        } catch (ParseException e) {
            e.printStackTrace();
        }


        for(Date date:dateArrayList)

        {

            if(date!=null)
            {
                bufferDate=date;
                if(bufferDate.after(lastDate))
                {
                    lastDate=bufferDate;
                }

            }







        }




        son_ziyaret_tarihi=simpleDateFormat.format(lastDate);
        if(son_ziyaret_tarihi.matches("01.01.1900"))
        {
            son_ziyaret_tarihi="---";
        }

        return son_ziyaret_tarihi;



    }

    public static Date getParsedDate(String dateForParse)
    {
        SimpleDateFormat sekil = new SimpleDateFormat("dd.MM.yyyy");

        Date parsedDate=null;

        try {
            parsedDate=sekil.parse(dateForParse);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return parsedDate;
    }

    public static String getDateWithDay(String date)
    {
        String date_result= null;



        Calendar calendar=Calendar.getInstance();

       int monday=GregorianCalendar.getInstance().getFirstDayOfWeek();

       int selectedDate;

       String dateName=null;

       if(date.charAt(0)=='0')
       {

           selectedDate=Character.getNumericValue(date.charAt(1));

       }

       else
       {
           selectedDate=Integer.parseInt(date.substring(0,2));
       }


       switch (selectedDate % 7)
       {
           case 0:
               dateName= VisitInformations.PAZAR;
               break;

           case 1:
               dateName= VisitInformations.PAZARTESI;
               break;


           case 2:
               dateName= VisitInformations.SALI;
               break;


           case 3:
               dateName= VisitInformations.CARSAMBA;
               break;


           case 4:
               dateName= VisitInformations.PERSEMBE;
               break;


           case 5:
               dateName= VisitInformations.CUMA;
               break;


           case 6:
               dateName= VisitInformations.CUMARTESI;
               break;


       }


       date_result=date + " - " + dateName;

        return date_result;

    }














    public static  void startApp(Context  context)
    {
        SharedPreferences sharedPref =context.getSharedPreferences(CustomTime.LOGIN_CONTROL_STARTMINUTE, Context.MODE_PRIVATE);

        SharedPreferences.Editor  editor=sharedPref.edit();

     editor.putInt(CustomTime.LOGIN_CONTROL_STARTMINUTE,CustomTime.getCurrentMinute());

     editor.commit();

    }



    public static int  getHowManyMinute(Context context)
    {
        SharedPreferences sharedPref =context.getSharedPreferences(CustomTime.LOGIN_CONTROL_STARTMINUTE, Context.MODE_PRIVATE);
        int baslangic_dakikasi=sharedPref.getInt(CustomTime.LOGIN_CONTROL_GET_MINUTE,555);

        if(baslangic_dakikasi!=555)
        {
            return Math.abs(getCurrentMinute()-baslangic_dakikasi);

        }

        else
        {
            return 555;
        }
    }

}
