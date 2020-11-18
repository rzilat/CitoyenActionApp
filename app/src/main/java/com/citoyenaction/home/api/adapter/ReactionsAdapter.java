package com.citoyenaction.home.api.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import androidx.annotation.NonNull;

import com.citoyenaction.home.R;
import com.citoyenaction.home.api.model.ActNonCivique;
import com.citoyenaction.home.api.model.Reaction;

import java.util.ArrayList;

public class ReactionsAdapter extends ArrayAdapter<Reaction> {
    public ReactionsAdapter(Context context, ArrayList<Reaction> reactions) {
        super(context, 0, reactions);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.reactions_row, parent, false);
        }
        Reaction reaction = getItem(position);
        TextView titre = (TextView) convertView.findViewById(R.id.textViewTitre);
        TextView commentaire= (TextView) convertView.findViewById(R.id.textViewCommentaire);
        TextView evaluation= (TextView) convertView.findViewById(R.id.textViewEvaluation);
        titre.setText(reaction.getTitre());
        commentaire.setText(reaction.getCommentaire());
        evaluation.setText(reaction.getEvaluation());

        return convertView;
    }

}
