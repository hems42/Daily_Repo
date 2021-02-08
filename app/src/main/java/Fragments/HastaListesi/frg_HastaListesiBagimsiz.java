package Fragments.HastaListesi;

import Patient.Patient;
import android.content.Context;
import android.view.MenuItem;
import android.widget.Toast;
import com.example.esh_ajanda.R;

import java.text.ParseException;

public class frg_HastaListesiBagimsiz extends Base_frg_HastaListesi {


    public frg_HastaListesiBagimsiz(Context context) throws ParseException {
        super(context,Patient.BAGIMSIZ);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        boolean sonuc=false;

        switch (item.getItemId())
        {
            case R.id.menu_hastalar_:
                Toast.makeText(context,"deneme ba�ar�l� ba��ms�z hastalar",Toast.LENGTH_SHORT).show();

                sonuc=true;


                break;

        }
        return sonuc;
    }


    @Override
    public void onResume() {
        super.onResume();

        updateList();}




    public void updateList()
    {
        innerlistPatient=dbsqlPatient.getAllPatientDependency(Patient.BAGIMSIZ);

        setAdapter(innerlistPatient,innerlistPatient.size());


    }


    @Override
    public void genel_guncelle() {
        updateList();
    }
}
