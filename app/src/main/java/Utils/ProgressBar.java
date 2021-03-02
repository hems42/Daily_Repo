package Utils;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import androidx.appcompat.app.AlertDialog;
import com.example.esh_ajanda.R;

public class ProgressBar extends AlertDialog {

    private Context context;
    private Activity activity;
    private LayoutInflater inflater;
    private android.widget.ProgressBar progressBar;
    private View view;

    public ProgressBar(Context context) {
        super(context,R.style.AlertCustomDialog);

        this.context = context;
        inflater=LayoutInflater.from(context);
        activity= (Activity) context;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view=getLayoutInflater().inflate(R.layout.lyt_util_progres,activity.findViewById(R.id.layout_progressbar_alertdialog),false);
        progressBar=view.findViewById(R.id.progress_bar_custom);







        setContentView(view);

    }

    public void stopProgress()
    {

        dismiss();
    }
}
