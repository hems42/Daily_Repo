package Adapters.ListviewAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;

import Patient.Telefon;
import com.example.esh_ajanda.R;

import java.util.ArrayList;

public class AdapterOfListviewTelNumbers extends ArrayAdapter<Telefon> {

    private  LayoutInflater inflater;
    private  Context context;
    private AdapterOfListviewTelNumbers.ViewHolder holder;
    private  ArrayList<Telefon> telefons;

    public AdapterOfListviewTelNumbers(@NonNull Context context, ArrayList<Telefon> telefons)

    {
        super(context, 0,telefons);

        inflater=LayoutInflater.from(context);
        this.context=context;
        this.telefons=telefons;

    }



    @Override
    public int getCount() {
        return telefons.size();
    }

    @Override
    public Telefon getItem(int position) {
        return telefons.get(position);
    }

    @Override
    public long getItemId(int position) {
        return telefons.get(position).hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {

            convertView = inflater.inflate(R.layout.layout_listview_to_list_tel_numbers, null);

            holder = new ViewHolder();
            holder.tel_number_description =  convertView.findViewById(R.id.txtview_lyt_listview_to_list_tel_number_number_owner);
            holder.tel_number_1 = (TextView) convertView.findViewById(R.id.txtview_lyt_listview_to_list_tel_number_1);
            holder.tel_number_2= (TextView) convertView.findViewById(R.id.txtview_lyt_listview_to_list_tel_number_2);
            convertView.setTag(holder);

        }
        else{
            //Get viewholder we already created
            holder = (ViewHolder)convertView.getTag();
        }

        Telefon telefon = telefons.get(position);
        if(telefon != null){
            holder.tel_number_description.setText((telefon.tel_no_description));
            holder.tel_number_1.setText(telefon.tel_no1);
            holder.tel_number_2.setText(telefon.tel_no2);
        }
        return convertView;
    }

    //View Holder Pattern for better performance
    private static class ViewHolder {
        TextView tel_number_description;
        TextView tel_number_1;
        TextView tel_number_2;

    }

}
