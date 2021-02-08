package Dialogs;

import DataBaseSQLite.DataBaseSQLiteOfPatient.DBSQLiteOfAllPatients;
import Patient.Patient;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.annotation.NonNull;
import com.example.esh_ajanda.R;

import java.util.ArrayList;




public class SelectDependency extends Dialog {



    private Activity activity;
    private  Context context;
    private DBSQLiteOfAllPatients dbsqLiteOfAllPatients;


    public SelectDependency(@NonNull Context context) {
        super(context);
        this.context= context;
        activity= (Activity) context;

        dbsqLiteOfAllPatients= new DBSQLiteOfAllPatients(context);
        dbsqLiteOfAllPatients.onCreate(dbsqLiteOfAllPatients.getWritableDatabase());
    }




    public void selectDependency(EditText editText)
    {

        AlertDialog.Builder builder= new AlertDialog.Builder(activity);

        ListView listView= new ListView(activity);

        ArrayList<String> dependencylist= new ArrayList<>();
        dependencylist.add(Patient.TAM_BAGIMLI);
        dependencylist.add(Patient.YARI_BAGIMLI);
        dependencylist.add(Patient.BAGIMSIZ);

        ArrayAdapter<String>  adapter= new ArrayAdapter<String>(activity, android.R.layout.simple_expandable_list_item_1,dependencylist);

        listView.setAdapter(adapter);

        builder.setTitle("Baðýmlýlýk Durumunu Seçin!");
        builder.setIcon(R.drawable.i);
        builder.setView(listView);

        builder.setNegativeButton("VAZGEÇ", new OnClickListener() {
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


    public void selectDependency(Patient  patient)
    {

        AlertDialog.Builder builder= new AlertDialog.Builder(activity);

        ListView listView= new ListView(activity);

        ArrayList<String> dependencylist= new ArrayList<>();
        dependencylist.add(Patient.TAM_BAGIMLI);
        dependencylist.add(Patient.YARI_BAGIMLI);
        dependencylist.add(Patient.BAGIMSIZ);

        ArrayAdapter<String>  adapter= new ArrayAdapter<String>(activity, android.R.layout.simple_expandable_list_item_1,dependencylist);

        listView.setAdapter(adapter);

        builder.setTitle("Baðýmlýlýk Durumunu Seçin!");
        builder.setIcon(R.drawable.i);
        builder.setView(listView);

        builder.setNegativeButton("VAZGEÇ", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });



        AlertDialog dialog= builder.create();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                if(dbsqLiteOfAllPatients.updatePatientDependency(patient,listView.getItemAtPosition(position).toString()))
                {
                    Toast.makeText(context,"Hastanýn Baðýmlýlýk Durumu Baþarýyla Deðiþtirilmiþtir..",Toast.LENGTH_SHORT).show();


                    dialog.dismiss();
                }

                else
                {
                    Toast.makeText(context,"Hastanýn Baðýmlýlýk Durumu Deðiþtirilememiþtir..",Toast.LENGTH_SHORT).show();

                }



            }
        });
        ;



        dialog.show();







    }


}
