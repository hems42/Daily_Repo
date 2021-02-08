package Fragments.HastaListesi;

import Patient.Patient;
import android.view.*;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import com.example.esh_ajanda.R;
import android.content.Context;


import java.text.ParseException;

public class frg_HastaListesiAktif extends Base_frg_HastaListesi {


    public frg_HastaListesiAktif(Context context) throws ParseException {
        super(context,Patient.AKTIF);


    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        boolean sonuc=false;

        switch (item.getItemId())
        {
            case R.id.menu_hastalar_:
                Toast.makeText(context,"deneme baþarýlý aktif hastalar",Toast.LENGTH_SHORT).show();

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
        innerlistPatient=dbsqlPatient.getAllPatientFinalSituation(Patient.AKTIF);

        setAdapter(innerlistPatient,innerlistPatient.size());


    }


    @Override
    public void genel_guncelle() {
        updateList();
    }

}
