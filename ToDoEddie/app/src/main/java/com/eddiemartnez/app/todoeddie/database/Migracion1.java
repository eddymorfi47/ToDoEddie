package com.eddiemartnez.app.todoeddie.database;

import com.eddiemartnez.app.todoeddie.database.models.ToDoTable;
import com.raizlabs.android.dbflow.annotation.Migration;
import com.raizlabs.android.dbflow.sql.SQLiteType;
import com.raizlabs.android.dbflow.sql.migration.AlterTableMigration;

/**
 * Created by Eddie Martínez on 26/2/2018.
 */
@Migration(version = 2, database = ToDoDatabase.class)
public class Migracion1 extends AlterTableMigration<ToDoTable>{
    public Migracion1(Class<ToDoTable> table) {
        super(table);
    }

    @Override
    public void onPreMigrate() {
        super.onPreMigrate();
        addColumn(SQLiteType.INTEGER, "estado");
    }
}
