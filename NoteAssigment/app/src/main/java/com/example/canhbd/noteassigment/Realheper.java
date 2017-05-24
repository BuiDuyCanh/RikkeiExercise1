package com.example.canhbd.noteassigment;

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
}
