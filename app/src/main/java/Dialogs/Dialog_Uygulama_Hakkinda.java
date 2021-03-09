package Dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.esh_ajanda.R;

public class Dialog_Uygulama_Hakkinda extends AlertDialog {

    View view;

    public Dialog_Uygulama_Hakkinda(Context context) {

        super(context, R.style.AlertCustomDialog);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        view=getLayoutInflater().inflate(R.layout.lyt_about_app,null);

        Button button=view.findViewById(R.id.btn_lyt_uygulama_hakkinda_ok);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        setContentView(view);
    }


}


