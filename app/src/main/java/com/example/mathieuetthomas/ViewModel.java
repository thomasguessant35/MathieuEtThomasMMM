package com.example.mathieuetthomas;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

public class ViewModel extends AndroidViewModel {
    private Repository repository;
    private LiveData<List<Client>> allClients;

    public ViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
        allClients = repository.getAllClients();
    }

    public void insert(Client client) {
        repository.insert(client);
    }

    public void delete(Client client) {
        repository.delete(client);
    }

    public LiveData<List<Client>> getAllClients() {
        return allClients;
    }
}


