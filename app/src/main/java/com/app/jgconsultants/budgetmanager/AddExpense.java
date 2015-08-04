package com.app.jgconsultants.budgetmanager;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.Calendar;


public class AddExpense extends ActionBarActivity {
    public final static String ADD_EXPENSE = "com.app.jgconsultants.budgetmanager.ADD_EXPENSE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_expense, menu);
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

    public void addExpense(View view) {
        EditText editTextExpenseAmount = (EditText) findViewById(R.id.expense_amount);
        EditText editTextItem = (EditText) findViewById(R.id.expense_item);
        EditText editTextCategory = (EditText) findViewById(R.id.expense_category);
        EditText editTextLocation = (EditText) findViewById(R.id.expense_location);

        String addExpenseString= editTextExpenseAmount.getText().toString();
        String fiItem = editTextItem.getText().toString();
        String fiCategory = editTextCategory.getText().toString();
        String fiLocation = editTextLocation.getText().toString();

        Double fiAmount = Double.parseDouble(addExpenseString);

        int fiYear = Calendar.getInstance().get(Calendar.YEAR);
        int fiMonth = Calendar.getInstance().get(Calendar.MONTH);
        int fiDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

        FinanceItem expense = new FinanceItem(fiCategory, fiLocation, fiItem, fiYear, fiMonth, fiDay, fiAmount, false);

        BudgetManagerHelper db = new BudgetManagerHelper(this);

        db.addFinanceItem(expense);

        Intent intent = new Intent(this, OpeningPage.class);
        intent.putExtra(ADD_EXPENSE, fiAmount);
        startActivity(intent);
    }
}
