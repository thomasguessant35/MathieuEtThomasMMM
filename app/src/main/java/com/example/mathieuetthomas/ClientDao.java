package com.example.mathieuetthomas;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ClientDao {
    @Query("SELECT * FROM client_table ")
    LiveData<List<Client>> getAll();

    @Query("SELECT * FROM client_table WHERE uid IN (:clientIds)")
    LiveData<List<Client>> loadAllByIds(int[] clientIds);


    @Query("SELECT * FROM client_table WHERE nom LIKE :nom AND " +
            "prenom LIKE :prenom LIMIT 1")
    Client findByName(String nom, String prenom);

    @Insert
    void insertAll(Client... clients);

    @Insert
    void insert(Client client);

    @Delete
    void delete(Client client);

    @Delete
    void deleteAll(Client... clients);

}


