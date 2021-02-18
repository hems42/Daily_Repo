package Dialogs;

import Manager.PatientInnerManager;
import Patient.*;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import com.example.esh_ajanda.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class Dialog_Show_ResultOfVisit extends BottomSheetDialog {

    private Activity activity;
    private Context context;
    TextView txt_treatments;
    ImageView img_sign,img_geri_don;
    View view;
    PatientInnerManager patientInnerManager;
    Patient patient;
    VisitInformations visitInformations,current_visit;
    Bitmap bitmap_sign;



    public Dialog_Show_ResultOfVisit(Context context,Patient patient,
                                     PatientInnerManager  patientInnerManager,
                                     VisitInformations  visitInformations) {
        super(context);

        this.context=context;
        activity= (Activity) context;

        this.patient=patient;
        this.patientInnerManager=patientInnerManager;
        this.visitInformations=visitInformations;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        AlertDialog.Builder builder= new AlertDialog.Builder(activity);


        view=getLayoutInflater().inflate(R.layout.layout_result_of_visit,activity.findViewById(R.id.bottom_sheet_lyt_visit_result_of_patient));

        txt_treatments=view.findViewById(R.id.txt_result_of_visit);

        img_sign=view.findViewById(R.id.img_result_of_visit);
        img_geri_don=view.findViewById(R.id.img_layout_visit_result_dialog_geri_don);


        if(visitInformations.visitResult.matches(VisitInformations.TAMAMLANDI))
        {
           current_visit=patientInnerManager.ziyareti_getir(patient,visitInformations.visitDate);

           if(current_visit.sign!=null)
           {
               bitmap_sign= BitmapFactory.decodeByteArray(current_visit.sign,0,current_visit.sign.length);

               img_sign.setImageBitmap(bitmap_sign);
           }
           else
           {
               Toast.makeText(context,"Ýmza Bilgisine Ulaþýlamadý!!",Toast.LENGTH_SHORT).show();
           }


           txt_treatments.setText(current_visit.notes);
        }


        builder.setView(view);

        AlertDialog dialog= builder.create();


        setContentView(view);

        img_geri_don.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });








    }



}
