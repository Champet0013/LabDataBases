package com.example.ist_mac_01.labdatabases;

/**
 * Created by ist-mac-01 on 2/28/2018 AD.
 */

public class Contact {
    public int _id;
    public String _name ;
    public String _phone_number;

    public Contact(){}

    public Contact(String name , String _phone_number){
        this. _name = name;
        this._phone_number = _phone_number;
    }

    public Contact(int id, String name, String _phone_number){
        this. _id = id;
        this._name = name;
        this._phone_number = _phone_number;
    }
}
