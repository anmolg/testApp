package com.app.jgconsultants.budgetmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import junit.framework.Test;

/**
 * Created by Anmol on 8/4/2015.
 */
public class MySimpleArrayAdapter extends ArrayAdapter<String> {

    private final Context context;
    private final String[] items;

    public MySimpleArrayAdapter(Context context, String[] items) {
        super(context, R.layout.activity_cashflow_row, items);
        this.context = context;
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.activity_cashflow_row, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.item);
        textView.setText(items[position]);
        return rowView;
    }

}
