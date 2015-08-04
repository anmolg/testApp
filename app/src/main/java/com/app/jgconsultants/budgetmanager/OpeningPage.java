package com.app.jgconsultants.budgetmanager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class OpeningPage extends ActionBarActivity {

    private static String DEFAULT_VALUE = "0";
    private double totalBudget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening_page);


        retrieveTotalBudget();


        Intent intent = getIntent();

        // Get add revenue value
        double addRevenue = intent.getDoubleExtra(AddRevenue.ADD_REVENUE, -1);
        if (addRevenue != -1) {
            totalBudget = totalBudget + addRevenue;
        }

        // Get add expense value. Because of default value handling, no need for if statement
        double addExpense = intent.getDoubleExtra(AddExpense.ADD_EXPENSE, -1);
        if (addExpense != -1) {
            totalBudget = totalBudget - addExpense;
        }

        updateTotalBudget();

    }

    private void retrieveTotalBudget() {
        SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.all_shared_pref), Context.MODE_PRIVATE);
        String strTotalBudget = sharedPref.getString(getString(R.string.saved_total_budget), DEFAULT_VALUE);
        totalBudget = Double.valueOf(strTotalBudget);
    }

    private void updateTotalBudget() {
        TextView showMoney = (TextView) findViewById(R.id.current_balance);
        showMoney.setText(String.valueOf(totalBudget));
    }

    private void saveTotalBudget() {
        SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.all_shared_pref), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(getString(R.string.saved_total_budget), Double.toString(totalBudget));
        editor.commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_opening_page, menu);
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
    protected void onStop() {
        super.onStop();
        saveTotalBudget();
    }

    public void addRevenue(View view) {
        Intent intent = new Intent(this, AddRevenue.class);
        //intent.putExtra(CURRENT_BUDGET, totalBudget);
        startActivity(intent);

        /*EditText editText = (EditText) findViewById(R.id.change_balance);
        String toChangeString = editText.getText().toString();
        Float toChange = Float.parseFloat(toChangeString);
        totalBudget = totalBudget + toChange;
        updateTotalBudget();*/
        //saveTotalBudget();

    }

    public void addExpense(View view) {
        Intent intent = new Intent(this, AddExpense.class);
        startActivity(intent);
        /*EditText editText = (EditText) findViewById(R.id.change_balance);
        String toChangeString = editText.getText().toString();
        Float toChange = Float.parseFloat(toChangeString);
        totalBudget = totalBudget - toChange;
        updateTotalBudget();*/
        //saveTotalBudget();
    }

    public void seeCashflow(View view) {
        Intent intent = new Intent(this, CashflowRow.class);
        startActivity(intent);
    }

}
