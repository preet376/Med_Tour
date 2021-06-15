package com.jaypal.navigation_drawer.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jaypal.navigation_drawer.R;
import com.jaypal.navigation_drawer.doctor_detail_final;
import com.jaypal.navigation_drawer.model.Doctor;
import com.jaypal.navigation_drawer.model.hospital;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class frag_doctor_recycler extends RecyclerView.Adapter<frag_doctor_recycler.frag_doctor_holder> implements Filterable {
    Context context;
    ArrayList<Doctor> Doctors=new ArrayList<>();
    ArrayList<Doctor>filtered;

    public frag_doctor_recycler(Context context, ArrayList<Doctor> doctors) {
        this.context = context;
        Doctors = doctors;
        filtered=Doctors;
    }

    @NonNull
    @Override
    public frag_doctor_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_doctor_model,parent,false);
        return new frag_doctor_holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final frag_doctor_holder holder, final int position) {
        String str="";
        final String ptr=Doctors.get(position).getName();
        str+=Doctors.get(position).getHospital_name();
        str+=","+Doctors.get(position).getLocation();
        holder.name.setText(Doctors.get(position).getName());
        holder.addr.setText(str);
        holder.edu.setText(Doctors.get(position).getEducation());
        holder.exp.setText(Doctors.get(position).getExperience());
        holder.fee.setText(String.valueOf(Doctors.get(position).getFees()));
        holder.sp.setText(Doctors.get(position).getSpeciality());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, doctor_detail_final.class);
                intent.putExtra("vname",ptr);
                context.startActivity(intent);
            }
        });

    }
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString=charSequence.toString();
                if (charString.isEmpty())
                {
                    Doctors=filtered;
                }
                else {
                    ArrayList<Doctor> d=new ArrayList<>();
                    for (Doctor a :filtered)
                    {
                        if (a.getSpeciality().toLowerCase().trim().contains(charString.toLowerCase().trim()))
                        {
                            d.add(a);
                            Log.d(TAG, "performFiltering: "+"matched");
                        }else {
                            Log.d(TAG, "performFiltering: "+"not matched");
                        }
                        Log.d(TAG, "performFiltering: "+a.getSpeciality()+"\t"+charString);
                    }
                    Doctors=d;
                }
                FilterResults filterResults=new FilterResults();
                filterResults.values=Doctors;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                Doctors=(ArrayList<Doctor>)filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
    @Override
    public int getItemCount() {
        return Doctors.size();
    }

    public class frag_doctor_holder  extends RecyclerView.ViewHolder{
        TextView name,edu,exp,fee,addr,sp;
        public frag_doctor_holder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.item_name_doctor);
            edu=itemView.findViewById(R.id.item_doctor_education);
            exp=itemView.findViewById(R.id.item_doctor_experiance);
            fee=itemView.findViewById(R.id.item_doctor_fee);
            addr=itemView.findViewById(R.id.doctor_addr);
            sp=itemView.findViewById(R.id.item_doctor_special);
        }
    }
}
