package Fragments.HastaListesi;

import Activities.HomeActivity;
import Patient.Patient;
import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;
import android.widget.Toast;
import com.example.esh_ajanda.R;

import java.text.ParseException;

public class frg_HastaListesiPasif extends Base_frg_HastaListesi {



    public frg_HastaListesiPasif(Context context) throws ParseException {
        super(context,Patient.PASIF);






    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        boolean sonuc=false;

        switch (item.getItemId())
        {
            case R.id.menu_hastalar_anasayfaya_git:


                Intent intent= new Intent(context, HomeActivity.class);

                activity.startActivity(intent);

                activity.finish();

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
        innerlistPatient=dbsqlPatient.getAllPatientFinalSituation(Patient.PASIF);

        setAdapter(innerlistPatient,innerlistPatient.size());


    }




    @Override
    public void genel_guncelle() {
        updateList();
    }

}
