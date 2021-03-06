package Fragments.HastaListesi;

import Activities.HomeActivity;
import BackUp.BackUpManager;
import Utils.PatientUtillty;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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


        activity= (Activity) context;


    }





    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        boolean sonuc=false;

        switch (item.getItemId())
        {
            case R.id.menu_hastalar_yedek_al:

                if(new BackUpManager().getBackUpDataBase())
                {
                    Toast.makeText(context,"VeriTabanı Yedeklemesi Başarılı!!",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(context,"VeriTabanı Yedeklemesi Başarısız Oldu!!",Toast.LENGTH_SHORT).show();
                }

                sonuc=true;

                break;

            case R.id.menu_hastalar_yedek_yukle:

                sonuc=true;

                if(new BackUpManager().LoadBackUpDataBase())
                {
                    Toast.makeText(context,"VeriTabanı Yedeklemesi Başarıyla Yüklendi!!",Toast.LENGTH_SHORT).show();

                    adapter.notifyItemChanged(0);
                }
                else
                {
                    Toast.makeText(context,"VeriTabanı Yedeklemesi Yüklenemedi!!",Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.menu_hastalar_hasta_ekle:


                addPatient();

                sonuc=true;

                break;


            case R.id.menu_hastalar_galeriye_git:


                Intent intent= new Intent(Intent.ACTION_GET_CONTENT);

                intent.setType("*/*");

                getActivity().startActivityForResult(intent,125);


                sonuc=true;

                break;

            case R.id.menu_hastalar_anasayfaya_git:


                Intent intent_home= new Intent(context, HomeActivity.class);

                activity.startActivity(intent_home);

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
        innerlistPatient=dbsqlPatient.getAllPatient();

       setAdapter(innerlistPatient,innerlistPatient.size());


    }


    @Override
    public void genel_guncelle() {


        updateList();

    }
}
