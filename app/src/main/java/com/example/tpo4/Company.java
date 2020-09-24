package com.example.tpo4;

public class Company {

    private String _id;
    private String _name;
    private String _Date;
    private float _CGPA;
    private String _salary;

    public Company() {
    }
    public Company(String id, String studentname, String date, float CGPA, String salary) {
        this._id = id;
        this._name = studentname;
        this._Date=date;
        this._CGPA=CGPA;
        this._salary=salary;
    }
    public void setID(String id) {
        this._id = id;
    }
    public String getID() {
        return this._id;
    }
    public void setCName(String studentname) {
        this._name = studentname;
    }
    public String getCName() {
        return this._name;
    }
    public void setDate(String date) {
        this._Date = date;
    }
    public String getDate()
    {
        return this._Date;
    }

    public void setCGPA(float CGPA)
    {
        this._CGPA=CGPA;
    }
    public float getCGPA()
    {
        return this._CGPA;
    }
    public void setsalary(String sal)
    {
        this._salary=sal;
    }
    public String getsalary()
    {
        return this._salary;
    }
}
