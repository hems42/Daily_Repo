package Fragments.HastaListesi;

import Utils.PatientUtillty;
import android.content.Context;
import android.view.*;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import com.example.esh_ajanda.R;

import java.text.ParseException;
import java.util.ArrayList;

public class frg_HastaListesiTum extends Base_frg_HastaListesi {



    public frg_HastaListesiTum(Context context)  {
        super(context);

        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                updateList();
            }

            @Override
            public void onItemRangeChanged(int positionStart, int itemCount) {
                super.onItemRangeChanged(positionStart, itemCount);

                updateList();
            }

            @Override
            public void onChanged() {



            }


        });




    }





    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        boolean sonuc=false;

        switch (item.getItemId())
        {
            case R.id.menu_hastalar_:

                sonuc=true;

                break;


            case R.id.menu_hastalar_hasta_ekle:


                addPatient();

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
        innerlistPatient=dbsqlPatient.getAllPatient();

       setAdapter(innerlistPatient,innerlistPatient.size());


    }


    @Override
    public void genel_guncelle() {


        updateList();

    }
}
