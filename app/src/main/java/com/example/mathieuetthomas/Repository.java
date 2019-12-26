package com.example.mathieuetthomas;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class Repository {
    private ClientDao clientDao;
    private LiveData<List<Client>> allClients;

    public Repository(Application application) {
        AppDataBase database = AppDataBase.getInstance(application);
        clientDao = database.clientDao();
        allClients = clientDao.getAll();
    }

    public void insert(Client note) {
        new InsertClientAsyncTask(clientDao).execute(note);
    }


    public void delete(Client note) {
        new DeleteClientAsyncTask(clientDao).execute(note);
    }

    public void deleteAllClients() {
        new DeleteAllClientsAsyncTask(clientDao).execute();
    }

    public LiveData<List<Client>> getAllClients() {
        return allClients;
    }

    private static class InsertClientAsyncTask extends AsyncTask<Client, Void, Void> {
        private ClientDao clientDao;

        private InsertClientAsyncTask(ClientDao clientDao) {
            this.clientDao = clientDao;
        }

        @Override
        protected Void doInBackground(Client... clients) {
            clientDao.insert(clients[0]);
            return null;
        }
    }


    private static class DeleteClientAsyncTask extends AsyncTask<Client, Void, Void> {
        private ClientDao clientDao;

        private DeleteClientAsyncTask(ClientDao clientDao) {
            this.clientDao = clientDao;
        }

        @Override
        protected Void doInBackground(Client... clients) {
            clientDao.delete(clients[0]);
            return null;
        }
    }

    private static class DeleteAllClientsAsyncTask extends AsyncTask<Void, Void, Void> {
        private ClientDao noteDao;

        private DeleteAllClientsAsyncTask(ClientDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deleteAll();
            return null;
        }
    }
}