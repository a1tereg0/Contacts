package com.example.contacts.Models;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = Contact.class, version = 1)
public abstract class ContactDatabase extends RoomDatabase {
    private static ContactDatabase instance;

    public abstract ContactDao contactDao();

    public static synchronized ContactDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), ContactDatabase.class, "contact_database")
                        .fallbackToDestructiveMigration()
                        .addCallback(roomCallback)
                        .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private ContactDao contactDao;

        private PopulateDbAsyncTask(ContactDatabase db) {
            contactDao = db.contactDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            contactDao.insert(new Contact("Prashant", "Dahal", "9843456287", "prashantdah41@gmail.com"));
            contactDao.insert(new Contact("Rikesh", "Awale", "555555555", "awalerikesh@gmail.com"));
            contactDao.insert(new Contact("Sulav", "Panday", "123222444", "sulavpanday@gmail.com"));
            contactDao.insert(new Contact("Prakriti", "Dahal", "9832555224", "dahalprakriti@gmail.com"));
            contactDao.insert(new Contact("Sujan", "Shrestha", "9841454289", "suzanshrestha1@gmail.com"));
            return null;
        }
    }

}
