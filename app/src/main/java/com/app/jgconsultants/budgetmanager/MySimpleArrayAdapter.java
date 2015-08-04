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
public class MySimpleArrayAdapter extends ArrayAdapter<FinanceItem> {

    private final Context context;
    private final FinanceItem[] items;
    private String[] ArrayOfItem;
    private String[] ArrayOfPrice;

    public MySimpleArrayAdapter(Context context, FinanceItem[] items) {
        super(context, R.layout.activity_cashflow_row, items);
        this.context = context;
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        filterFinanceItem();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.activity_cashflow_row, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.item);
        TextView priceView = (TextView) rowView.findViewById(R.id.price);
        textView.setText(ArrayOfItem[position]);
        priceView.setText(ArrayOfPrice[position]);
        return rowView;
    }

    private void filterFinanceItem() {
        ArrayOfItem = new String[items.length];
        ArrayOfPrice = new String[items.length];

        for (int i = 0; i < items.length; i++) {
            ArrayOfPrice[i] = items[i].getItem();
            Double amount = items[i].getAmount();
            String amountDisplay = Double.toString(amount);
            ArrayOfItem[i] = amountDisplay;
        }
    }
}
