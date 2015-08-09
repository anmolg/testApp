package com.app.jgconsultants.budgetmanager;

import android.app.ListActivity;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class CashflowRow extends ListActivity { //ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_cashflow_row);

        BudgetManagerHelper bmDbHelper = new BudgetManagerHelper(getApplicationContext());
        List<FinanceItem> lfi = bmDbHelper.getAllFinanceItem();

        FinanceItem[] arrayFinanceItem = new FinanceItem[lfi.size()];
        arrayFinanceItem = lfi.toArray(arrayFinanceItem);

        MySimpleArrayAdapter adapter = new MySimpleArrayAdapter(this, arrayFinanceItem);
        setListAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cashflow_row, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        String item = (String) getListAdapter().getItem(position);
        Toast.makeText(this, "selected", Toast.LENGTH_LONG).show();
    }
}
