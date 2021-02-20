package Fragments.HastaListesi;

import Patient.Patient;
import android.content.Context;
import android.view.MenuItem;
import android.widget.Toast;
import com.example.esh_ajanda.R;

import java.text.ParseException;

public class frg_HastaListesiYariBagimli extends Base_frg_HastaListesi {

    public frg_HastaListesiYariBagimli(Context context) throws ParseException {
        super(context,Patient.YARI_BAGIMLI);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        boolean sonuc=false;

        switch (item.getItemId())
        {


        }
        return sonuc;
    }

    @Override
    public void onResume() {
        super.onResume();

        updateList();}




    public void updateList()
    {
        innerlistPatient=dbsqlPatient.getAllPatientDependency(Patient.YARI_BAGIMLI);

        setAdapter(innerlistPatient,innerlistPatient.size());


    }


    @Override
    public void genel_guncelle() {
        updateList();
    }
}
