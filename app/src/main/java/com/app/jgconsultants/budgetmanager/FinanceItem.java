package com.app.jgconsultants.budgetmanager;


import java.util.Date;

/**
 * Created by Anmol on 7/9/2015.
 */
public class FinanceItem {

    private int _id;
    private String _Category;
    private String _Location;
    private String _Item;
    private int _Year;
    private int _Month;
    private int _Day;
    private double _Amount;
    private boolean _AddMoney; // 1 for revenue, 0 for expense

    public FinanceItem(int id, String category, String location, String description, int year, int month, int day, double amount, boolean addMoney) {
        this._id = id;
        this._Category = category;
        this._Location = location;
        this._Item = description;
        this._Year = year;
        this._Month = month;
        this._Day = day;
        this._Amount = amount;
        this._AddMoney = addMoney;
    }

    public FinanceItem(String category, String location, String description, int year, int month, int day, double amount, boolean addMoney) {
        this._Category = category;
        this._Location = location;
        this._Item = description;
        this._Year = year;
        this._Month = month;
        this._Day = day;
        this._Amount = amount;
        this._AddMoney = addMoney;
    }


    public int getID() {
        return _id;
    }

    public void setID(int _id) {
        this._id = _id;
    }

    public String getCategory() {
        return _Category;
    }

    public void setCategory(String _Category) {
        this._Category = _Category;
    }

    public String getLocation() {
        return _Location;
    }

    public void setLocation(String _Location) {
        this._Location = _Location;
    }

    public String getItem() {
        return _Item;
    }

    public void setItem(String _Item) {
        this._Item = _Item;
    }

    public int getYear() {
        return _Year;
    }

    public void setYear(int _Year) {
        this._Year = _Year;
    }

    public int getMonth() {
        return _Month;
    }

    public void setMonth(int _Month) {
        this._Month = _Month;
    }

    public int getDay() {
        return _Day;
    }

    public void setDay(int _Day) {
        this._Day = _Day;
    }

    public double getAmount() {
        return _Amount;
    }

    public void setAmount(double _Amount) {
        this._Amount = _Amount;
    }

    public boolean isAddMoney() {
        return _AddMoney;
    }

    public void setAddMoney(boolean _AddMoney) {
        this._AddMoney = _AddMoney;
    }

}
