package Dialogs;

import Patient.Patient;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import com.example.esh_ajanda.R;

import java.util.ArrayList;

public class SelectSex {

    private Activity activity;

    private Context context;


    public SelectSex(Context context) {
        this.context = context;
        activity= (Activity) context;
    }


    public void selectSex(EditText editText)
    {
        AlertDialog.Builder builder= new AlertDialog.Builder(activity);

        ListView listView= new ListView(activity);

        ArrayList<String> sex= new ArrayList<>();
        sex.add("KADIN");
        sex.add("ERKEK");

        ArrayAdapter<String> adapter= new ArrayAdapter<String>(activity, android.R.layout.simple_expandable_list_item_1,sex);

        listView.setAdapter(adapter);

        builder.setTitle("Cinsiyet Seçin!");
        builder.setIcon(R.drawable.sex_icon);
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

                String  gelen =listView.getItemAtPosition(position).toString();
                if(gelen.matches("KADIN"))
                {
                    editText.setText(Patient.KADIN);
                }
                else {
                    editText.setText(Patient.ERKEK);
                }


                dialog.dismiss();

            }
        });
        ;



        dialog.show();



    }
}
