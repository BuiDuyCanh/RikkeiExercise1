package com.example.canhbd.noteassigment.utils;

import com.example.canhbd.noteassigment.realm.Information;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.exceptions.RealmException;

/**
 * Created by canhbd on 5/23/2017.
 */

public class Realheper {
    Realm realm;
    RealmResults<Information> Infor;
    Boolean saved=null;
    public Realheper(Realm realm) {
        this.realm = realm;
    }



    //SAVE
    public Boolean save(final Information information)
    {
        if(information==null)
        {
            saved=false;
        }else {

            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    try
                    {

                        Information s=realm.copyToRealm(information);
                        saved=true;

                    }catch (RealmException e)
                    {
                        e.printStackTrace();
                        saved=false;
                    }
                }
            });
        }

        return saved;
    }
    //RETRIEVE
    public void retrieveFromDB()
    {
        Infor=realm.where(Information.class).findAll();
    }

    //JUST REFRSH
    public ArrayList<Information> justRefresh()
    {
        ArrayList<Information> latest=new ArrayList<>();
        for(Information s:Infor)
        {
            latest.add(s);
        }

        return latest;
    }

    //Update Data
    public void updateData(int id,String title,String note,String date, String time,String color){
        realm.beginTransaction();
        Information information = realm.where(Information.class).equalTo("id",id).findFirst();
        information.setTitle(title);
        information.setNote(note);
        information.setDate(date);
        information.setTime(time);
        information.setColor(color);
        realm.commitTransaction();
        realm.close();
    }

}
