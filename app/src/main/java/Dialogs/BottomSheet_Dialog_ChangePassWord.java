package Dialogs;

import Utils.PassWordManager;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;

import com.example.esh_ajanda.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;


public class BottomSheet_Dialog_ChangePassWord extends BottomSheetDialog {

    PassWordManager passWordManager;

    Button btn_refresh,btn_ok,btn_cancel;
    EditText edtxt_current_password,edtxt_new_password,edtxt_new_password_again;
    int psw_current,psw_new,psw_new_again;
    private Context context;
    private Activity  activity;


    public BottomSheet_Dialog_ChangePassWord(@NonNull Context context) {
        super(context,R.style.CustomBottomSheetDialogTheme);

        passWordManager= new PassWordManager(context);
        this.context=context;
        activity= (Activity) context;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view= getLayoutInflater().inflate(R.layout.layout_change_password,activity.findViewById(R.id.layout_change_password));

        btn_ok=view.findViewById(R.id.btn_lyt_change_password_ok);
        btn_cancel=view.findViewById(R.id.btn_lyt_change_password_cancel);
        btn_refresh=view.findViewById(R.id.btn_lyt_change_password_refresh_form);

        edtxt_current_password=view.findViewById(R.id.edtxt_lyt_change_password_current_password);
        edtxt_new_password=view.findViewById(R.id.edtxt_lyt_change_password_new_password);
        edtxt_new_password_again=view.findViewById(R.id.edtxt_lyt_change_password_new_password_again);


        btn_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                edtxt_current_password.setError(null);
                edtxt_current_password.setText("");


                edtxt_new_password.setError(null);
                edtxt_new_password.setText("");


                edtxt_new_password_again.setError(null);
                edtxt_new_password_again.setText("");

            }
        });

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                psw_current=getInt(edtxt_current_password.getText().toString());
                psw_new=getInt(edtxt_new_password.getText().toString());
                psw_new_again=getInt(edtxt_new_password_again.getText().toString());

                int kayitli_psw=0;
                if(passWordManager.getCurrentUserPassWord()==PassWordManager.GECERLI_PAROLA_YOK||passWordManager.getCurrentUserPassWord()==-1)
                {
                    kayitli_psw=1234;
                }
                else
                {
                    kayitli_psw=passWordManager.getCurrentUserPassWord();
                }


                if(psw_current!=-1)
                {

                    if(psw_current==kayitli_psw)
                    {
                        if((psw_new!=1)&&(psw_new_again!=-1))
                        {
                            if(psw_new==psw_new_again)
                            {
                                if(passWordManager.changePassord(psw_new))
                                {
                                    Toast.makeText(context,"Parola Baþarýyla Deðiþtirildi!!",Toast.LENGTH_SHORT).show();
                                    dismiss();
                                }
                                else
                                {
                                    Toast.makeText(context,"Parola Deðiþtirilemedi Bilgileri Tekrar Kontrol Edin!!",Toast.LENGTH_SHORT).show();
                                }
                            }
                            else
                            {
                                edtxt_new_password.setError("parolalar uyuþmuyor!!");
                                edtxt_new_password_again.setError("parolalar uyuþmuyor!!");
                            }
                        }
                        else {
                            if(psw_new==-1)
                            {
                                edtxt_new_password.setError("parolanýzý girin!!");
                            }
                             if(psw_new_again==-1)
                            {
                                edtxt_new_password_again.setError("parolanýzý tekrar girin!!");
                            }


                        }

                    }
                    else
                    {
                        edtxt_current_password.setError("hatalý parola!!");
                    }
                }
                else
                {
                    edtxt_current_password.setError("geçerli parolayý girin!!");
                }

            }
        });


        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        setContentView(view);



    }

    private int getInt(String sayi)
    {
        if(sayi!=null&&!sayi.matches(""))
        {
            return Integer.parseInt(sayi);
        }
        else
        {
            return -1;
        }
    }
}
