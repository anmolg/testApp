package com.app.jgconsultants.budgetmanager;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.security.PublicKey;


public class AddRevenue extends ActionBarActivity {
    private float totalBudget;
    public final static String ADD_REVENUE = "com.app.jgconsultants.budgetmanager.ADD_REVENUE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_revenue);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_revenue, menu);
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

    public void addRevenue(View view) {
        BudgetManagerHelper bmDbHelper = new BudgetManagerHelper(getApplicationContext());
        SQLiteDatabase db = bmDbHelper.getWritableDatabase();


        EditText editTextRevenue = (EditText) findViewById(R.id.revenue_amount);
        String addRevenueString = editTextRevenue.getText().toString();

        EditText editTextItem = (EditText) findViewById(R.id.revenue_item);
        String addItemString = editTextItem.getText().toString();

        ContentValues values = new ContentValues();
        values.put(BudgetManagerHelper.COLUMN_NAME_ITEM, addItemString);
        values.put(BudgetManagerHelper.COLUMN_NAME_AMOUNT, addRevenueString);
        values.put(BudgetManagerHelper.COLUMN_NAME_DATE, System.currentTimeMillis());

        long newRowId;
        newRowId = db.insert(BudgetManagerHelper.TABLE_NAME, null, values);
        db.close();

        Float addRevenue = Float.parseFloat(addRevenueString);
        Intent intent = new Intent(this, OpeningPage.class);
        intent.putExtra(ADD_REVENUE, addRevenue);
        startActivity(intent);
    }
}
