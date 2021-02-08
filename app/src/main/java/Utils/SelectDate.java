package Utils;

import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import com.example.esh_ajanda.R;

import java.util.Calendar;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class SelectDate {



    private Context context;


    public SelectDate(Context context) {
        this.context = context;
    }


    public void tarih_sec(final EditText edttarih)
    {



        final Calendar takvim = Calendar.getInstance();
        int yil = takvim.get(Calendar.YEAR);
        int ay = takvim.get(Calendar.MONTH);
        int gun = takvim.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog dpd = new DatePickerDialog(context, DatePickerDialog.THEME_TRADITIONAL,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        // ay de?eri 0 dan ba?lad??? i?in (Ocak=0, ?ubat=1,..,Aral?k=11)
                        // de?eri 1 art?rarak g?steriyoruz.
                        month += 1;
                        // year, month ve dayOfMonth de?erleri se?ilen tarihin de?erleridir.
                        // Edittextte bu de?erleri g?steriyoruz.

                        if(dayOfMonth<=9&&month>9){

                            edttarih.setText("0"+dayOfMonth + "." + month + "." + year);
                        }
                        else if(dayOfMonth>9&&month>9)
                        {
                            edttarih.setText(dayOfMonth + "." + month + "." + year);
                        }

                        else if(dayOfMonth<=9&&month<=9)
                        {
                            edttarih.setText("0"+dayOfMonth + ".0" + month + "." + year);
                        }

                        else if(dayOfMonth>9&&month<=9) {
                            edttarih.setText(dayOfMonth + ".0" + month + "." + year);
                        }



                    }
                }, yil, ay, gun);
        // datepicker a??ld???nda set edilecek de?erleri buraya yaz?yoruz.
        // ?imdiki zaman? g?stermesi i?in yukarda tanmlad???mz de??kenleri kullanyoruz.

        // dialog penceresinin button bilgilerini ayarl?yoruz ve ekranda g?steriyoruz.
        dpd.setButton(DatePickerDialog.BUTTON_POSITIVE, "TARÝH SEÇ", dpd);
        dpd.setIcon(R.drawable.calendartwo);
        dpd.setButton(DatePickerDialog.BUTTON_NEGATIVE, "ÝPTAL", dpd);
        dpd.show();







    }



    public void tarih_sec_liste_takvimli(final EditText edttarih)
    {



        final Calendar takvim = Calendar.getInstance();
        int yil = takvim.get(Calendar.YEAR);
        int ay = takvim.get(Calendar.MONTH);
        int gun = takvim.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog dpd = new DatePickerDialog(context,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        // ay de?eri 0 dan ba?lad??? i?in (Ocak=0, ?ubat=1,..,Aral?k=11)
                        // de?eri 1 art?rarak g?steriyoruz.
                        month += 1;
                        // year, month ve dayOfMonth de?erleri se?ilen tarihin de?erleridir.
                        // Edittextte bu de?erleri g?steriyoruz.

                        if(dayOfMonth<=9&&month>9){

                            edttarih.setText("0"+dayOfMonth + "." + month + "." + year);
                        }
                        else if(dayOfMonth>9&&month>9)
                        {
                            edttarih.setText(dayOfMonth + "." + month + "." + year);
                        }

                        else if(dayOfMonth<=9&&month<=9)
                        {
                            edttarih.setText("0"+dayOfMonth + ".0" + month + "." + year);
                        }

                        else if(dayOfMonth>9&&month<=9) {
                            edttarih.setText(dayOfMonth + ".0" + month + "." + year);
                        }




                    }
                }, yil, ay, gun);
        // datepicker a??ld???nda set edilecek de?erleri buraya yaz?yoruz.
        // ?imdiki zaman? g?stermesi i?in yukarda tanmlad???mz de??kenleri kullanyoruz.

        // dialog penceresinin button bilgilerini ayarl?yoruz ve ekranda g?steriyoruz.
        dpd.setButton(DatePickerDialog.BUTTON_POSITIVE, "TARÝH SEÇ", dpd);
        dpd.setIcon(R.drawable.calendartwo);
        dpd.setButton(DatePickerDialog.BUTTON_NEGATIVE, "ÝPTAL", dpd);
        dpd.show();







    }


}
