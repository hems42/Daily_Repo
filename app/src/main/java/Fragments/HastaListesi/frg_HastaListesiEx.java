package Fragments.HastaListesi;

import Patient.Patient;
import android.content.Context;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import com.example.esh_ajanda.R;

import java.text.ParseException;

public class frg_HastaListesiEx extends Base_frg_HastaListesi {

    public frg_HastaListesiEx(Context context)  {
        super(context,Patient.EX);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        MenuItem menuItem=menu.findItem(R.id.menu_hastalar_hasta_ekle).setVisible(false);
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
        innerlistPatient=dbsqlPatient.getAllPatientFinalSituation(Patient.EX);

        setAdapter(innerlistPatient,innerlistPatient.size());


    }



    @Override
    public void genel_guncelle() {
        updateList();
    }


}
