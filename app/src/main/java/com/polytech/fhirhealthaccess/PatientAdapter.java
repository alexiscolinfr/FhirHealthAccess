package com.polytech.fhirhealthaccess;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.polytech.fhirhealthaccess.database.Patient;

import java.util.ArrayList;
import java.util.List;

public class PatientAdapter extends ArrayAdapter<Patient> implements Filterable {

    public List<Patient> patients;

    public PatientAdapter(Context context, List<Patient> patients){
        super(context,0,patients);
        this.patients = patients;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_list_patients,parent, false);
        }

        DetailViewHolder viewHolder = (DetailViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new DetailViewHolder();
            viewHolder.detailNom = convertView.findViewById(R.id.textViewNom);
            viewHolder.detailPrenom = convertView.findViewById(R.id.textViewPrenom);
            viewHolder.detailDateNaissance = convertView.findViewById(R.id.textViewDateNaissance);
            viewHolder.detailSexe = convertView.findViewById(R.id.textViewSexe);
            convertView.setTag(viewHolder);
        }

        Patient patient = getItem(position);

        assert patient != null;
        viewHolder.detailNom.setText(patient.getNom());
        viewHolder.detailPrenom.setText(patient.getPrenom());
        viewHolder.detailDateNaissance.setText("Date de naissance : " + patient.getDateNaissance());
        viewHolder.detailSexe.setText("Sexe : " + patient.getSexe());

        return convertView;
    }

    @Override
    public Patient getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public int getCount() {
        return patients.size();
    }

    private static class DetailViewHolder{
        public TextView detailNom;
        public TextView detailPrenom;
        public TextView detailDateNaissance;
        public TextView detailSexe;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                final FilterResults oReturn = new FilterResults();
                final List<Patient> results = new ArrayList<>();
                List<Patient> allPatients = Patient.listAll(Patient.class);
                if (constraint != null && allPatients!=null) {
                    if (allPatients.size() > 0) {
                        for (final Patient p : allPatients) {
                            if (p.getNom().toLowerCase().contains(constraint.toString().toLowerCase()) || p.getPrenom().toLowerCase().contains(constraint.toString().toLowerCase()))
                                results.add(p);
                        }
                    }
                    oReturn.values = results;
                    oReturn.count = results.size();
                }
                return oReturn;
            }

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                patients = (List<Patient>) results.values;
                if (results.count > 0) {
                    notifyDataSetChanged();
                    clear();
                    for (Patient p : patients)
                        add(p);
                } else {
                    notifyDataSetInvalidated();
                }
            }
        };
    }

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
}
