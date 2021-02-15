package Fragments.ZiyaretListesi;

import Patient.VisitInformations;
import android.content.Context;
import android.view.MenuItem;
import com.example.esh_ajanda.R;

import java.util.ArrayList;

public class frg_ZiyaretListesiTum extends Base_frg_ZiyaretListesi{

    public frg_ZiyaretListesiTum(Context context) {
        super(context, VisitInformations.TUM_ZIYARETLER);
    }









    @Override
    public void genel_guncelle() {

       innerVisits=dbsqLiteOfVisit.getAllVisitsOfPatient();

        setAdapter(innerVisits,innerVisits.size());
    }


    @Override
    public void onResume() {
        super.onResume();

        genel_guncelle();
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item) {

        boolean sonuc=false;

        switch (item.getItemId())
        {
            case R.id.menu_ziyaretler_yenile:

                genel_guncelle();

                sonuc=true;

                break;

        }


        return sonuc;

    }



}
