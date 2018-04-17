package com.eddiemartnez.app.todoeddie.database.models;

import com.eddiemartnez.app.todoeddie.database.ToDoDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.UUID;

/**
 * Created by Eddie Martínez on 5/2/2018.
 */
@Table(database = ToDoDatabase.class)
public class ToDoTable extends BaseModel{
    @PrimaryKey(autoincrement = true)
    public long id;

    @Column
    public String nombre;

    @Column
    public String actividad;

    @Column
    public int estado;


}
