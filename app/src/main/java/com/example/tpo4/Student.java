package com.example.tpo4;

public class Student {
    private int _id;
    private String _studentname;
    private String _Branch;
    private float _CGPA;
    private String _emailid;

    public Student() {
    }
    public Student(int id, String studentname,String Branch,float CGPA,String Email) {
        this._id = id;
        this._studentname = studentname;
        this._Branch=Branch;
        this._CGPA=CGPA;
        this._emailid=Email;
    }
    public void setID(int id) {
        this._id = id;
    }
    public int getID() {
        return this._id;
    }
    public void setStudentName(String studentname) {
        this._studentname = studentname;
    }
    public String getStudentName() {
        return this._studentname;
    }
    public void setBranch(String Branch) {
        this._Branch = Branch;
    }
    public String getStudentBranch()
    {
        return this._Branch;
    }

    public void setCGPA(float CGPA)
    {
        this._CGPA=CGPA;
    }
    public float getStudentCGPA()
    {
        return this._CGPA;
    }
    public void setemailid(String Email)
    {
        this._emailid=Email;
    }
    public String getStudentEmail()
    {
        return this._emailid;
    }
}

