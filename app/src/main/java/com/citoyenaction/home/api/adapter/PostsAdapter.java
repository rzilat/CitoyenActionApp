package com.citoyenaction.home.api.adapter;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.citoyenaction.home.R;
import com.citoyenaction.home.api.model.ActNonCivique;

import java.util.ArrayList;


public class PostsAdapter extends ArrayAdapter<ActNonCivique> {
    public PostsAdapter(Context context, ArrayList<ActNonCivique> actNonCiviques) {
        super(context, 0, actNonCiviques);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.acts_row, parent, false);
        }
        ActNonCivique actNonCivique = getItem(position);
        TextView titre = (TextView) convertView.findViewById(R.id.textViewTitre);
        TextView description = (TextView) convertView.findViewById(R.id.textViewDescription);
        titre.setText(actNonCivique.getTitre());
        description.setText(actNonCivique.getDescription());

        return convertView;
    }


}
