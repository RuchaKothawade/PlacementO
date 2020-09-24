package com.example.tpo4;

public class Tpo
{

    private String _id;
    private String _name;

    public Tpo() {
    }
    public Tpo(String id, String name) {
        this._id = id;
        this._name = name;
    }
    public void setID(String id) {
        this._id = id;
    }

    public String getID() {
        return this._id;
    }


    public void setName(String name) {
        this._name = name;
    }

    public String getName() {
        return this._name;
    }

}
