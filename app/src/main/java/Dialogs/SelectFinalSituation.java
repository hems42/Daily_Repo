package Dialogs;

import Adapters.RecyclerViewAdapters.Hastalar.RecyclerViewAdapterOfHastaListesi;
import DataBaseSQLite.DataBaseSQLiteOfPatient.DBSQLiteOfAllPatients;
import Patient.Patient;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.*;
import com.example.esh_ajanda.R;

import java.util.ArrayList;

public class SelectFinalSituation {

    private Activity activity;

    private DBSQLiteOfAllPatients dbsqLiteOfAllPatients;

    private Context context;


    private RecyclerViewAdapterOfHastaListesi adapter;

    public SelectFinalSituation(Context context) {
        this.context = context;

        activity= (Activity) context;

        dbsqLiteOfAllPatients= new DBSQLiteOfAllPatients(context);
        dbsqLiteOfAllPatients.onCreate(dbsqLiteOfAllPatients.getWritableDatabase());
    }




    public void selectFinalSituation(EditText editText)
    {
        AlertDialog.Builder builder= new AlertDialog.Builder(activity);

        ListView listView= new ListView(activity);

        ArrayList<String> sex= new ArrayList<>();
        sex.add(Patient.AKTIF);
        sex.add(Patient.PASIF);
        sex.add(Patient.EX);

        ArrayAdapter<String> adapter= new ArrayAdapter<String>(activity, android.R.layout.simple_expandable_list_item_1,sex);

        listView.setAdapter(adapter);

        builder.setTitle("son Durumunu Seçin!");
        builder.setIcon(R.drawable.j);
        builder.setView(listView);

        builder.setNegativeButton("VAZGEÇ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });



        AlertDialog dialog= builder.create();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



                editText.setText(listView.getItemAtPosition(position).toString());

                dialog.dismiss();

            }
        });
        ;



        dialog.show();



    }

    public  void selectFinalSituation(Patient patient)
    {
        AlertDialog.Builder builder= new AlertDialog.Builder(activity);

        ListView listView= new ListView(activity);

        ArrayList<String> sex= new ArrayList<>();
        sex.add(Patient.AKTIF);
        sex.add(Patient.PASIF);
        sex.add(Patient.EX);

        ArrayAdapter<String> adapter= new ArrayAdapter<String>(activity, android.R.layout.simple_expandable_list_item_1,sex);

        listView.setAdapter(adapter);

        builder.setTitle("son Durumunu Seçin!");
        builder.setIcon(R.drawable.j);
        builder.setView(listView);

        builder.setNegativeButton("VAZGEÇ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });



        AlertDialog dialog= builder.create();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String sonu_durum=listView.getItemAtPosition(position).toString();



                if(listView.getItemAtPosition(position).toString().matches(Patient.EX)
                        ||listView.getItemAtPosition(position).toString().matches(Patient.PASIF))
                {
                    dialog.dismiss();

                    AlertDialog.Builder builder_p_e=new AlertDialog.Builder(activity);

                    builder_p_e.setIcon(R.drawable.error);
                    builder_p_e.setTitle("DÝKKATT!!");
                    builder_p_e.setMessage("Hasta Pasif ya da Ex te iken randevü oluþturulumaz, oluþturulan randevüler iptal edilir!! Onaylýyor musunuz?");
                    builder_p_e.setNegativeButton("VAZGEÇ", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    builder.setPositiveButton("ONAYLIYORUM", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {


                        }
                    });

                    AlertDialog dialog_p_e=builder_p_e.create();

                    dialog_p_e.show();

                }

                else
                {
                    if(dbsqLiteOfAllPatients.updatePatientFinalSituations(patient,listView.getItemAtPosition(position).toString()))
                    {


                        Toast.makeText(context,"Hastanýn Son Durumu Baþarýyla Deðiþtirilmiþtir..",Toast.LENGTH_SHORT).show();
                        dialog.dismiss();

                    }
                    else
                    {
                        Toast.makeText(context,"Hastanýn Durumu Deðiþtirilememiþtir!!!",Toast.LENGTH_SHORT).show();
                    }

                }








            }
        });




        dialog.show();



    }

}
