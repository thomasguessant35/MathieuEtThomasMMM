package com.example.mathieuetthomas;

import android.content.Context;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Client.class}, version = 2)
public abstract class AppDataBase extends RoomDatabase {

    private static AppDataBase instance;

    public abstract ClientDao clientDao();

    public static synchronized AppDataBase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDataBase.class, "note_database")
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
        private ClientDao clientDao;

        private PopulateDbAsyncTask(AppDataBase db) {
            clientDao = db.clientDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            Client u1 = new Client("Ada","Lovelace", "07/05/1997", "Quimper", "0645988745");
            Client u2 = new Client("Charles","Babbage", "25/02/2001", "Rennes", "0715485698");

            clientDao.insert(u1);
            clientDao.insert(u2);
            clientDao.insert(u1);
            clientDao.insert(u2);

            return null;
        }
    }

}
