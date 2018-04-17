package com.eddiemartnez.app.todoeddie.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.eddiemartnez.app.todoeddie.R;
import com.eddiemartnez.app.todoeddie.database.models.ToDoTable;
import com.eddiemartnez.app.todoeddie.Subclase.ToDoViewHolder;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import org.sufficientlysecure.htmltextview.HtmlResImageGetter;

import java.util.List;

public class MainActivity extends AppCompatActivity {
private RecyclerView lista;
private static Context QuickContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        QuickContext = this;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent actividad = new Intent(getApplicationContext(), Formulario.class);
                actividad.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(actividad);
                Snackbar.make(view, "", Snackbar.LENGTH_LONG)
                        .setAction("", null).show();
            }
        });

        lista = findViewById(R.id.lista);
        lista.setLayoutManager(new LinearLayoutManager(this));

        List<ToDoTable> info = SQLite.select().from(ToDoTable.class).queryList();
        lista.setAdapter(new ToDoAdapter(info));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public static class ToDoAdapter extends RecyclerView.Adapter<ToDoViewHolder> {
        private final List<ToDoTable> listToDoTable;
        private final LayoutInflater inflater;

        public ToDoAdapter(List<ToDoTable> listToDoTables) {
            this.inflater = LayoutInflater.from(QuickContext);
            this.listToDoTable = listToDoTables;
        }

        @Override
        public ToDoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.activity_objeto, parent, false);
            return new ToDoViewHolder(view);
        }

        public void animateTo(List<ToDoTable> models) {
            applyAndAnimateRemovals(models);
            applyAndAnimateAdditions(models);
            applyAndAnimateMovedItems(models);
        }

        private void applyAndAnimateRemovals(List<ToDoTable> newModels) {
            for (int i = listToDoTable.size() - 1; i >= 0; i--) {
                final ToDoTable model = listToDoTable.get(i);
                if (!newModels.contains(model)) {
                    removeItem(i);
                }
            }
        }

        private void applyAndAnimateAdditions(List<ToDoTable> newModels) {
            for (int i = 0, count = newModels.size(); i < count; i++) {
                final ToDoTable model = newModels.get(i);
                if (!listToDoTable.contains(model)) {
                    addItem(i, model);
                }
            }
        }

        private void applyAndAnimateMovedItems(List<ToDoTable> newModels) {
            for (int toPosition = newModels.size() - 1; toPosition >= 0; toPosition--) {
                final ToDoTable model = newModels.get(toPosition);
                final int fromPosition = listToDoTable.indexOf(model);
                if (fromPosition >= 0 && fromPosition != toPosition) {
                    moveItem(fromPosition, toPosition);
                }
            }
        }

        public ToDoTable removeItem(int position) {
            final ToDoTable model = listToDoTable.remove(position);
            notifyItemRemoved(position);
            return model;
        }

        public void addItem(int position, ToDoTable model) {
            listToDoTable.add(position, model);
            notifyItemInserted(position);
        }

        public void moveItem(int fromPosition, int toPosition) {
            final ToDoTable model = listToDoTable.remove(fromPosition);
            listToDoTable.add(toPosition, model);
            notifyItemMoved(fromPosition, toPosition);
        }

        @Override
        public void onBindViewHolder(final ToDoViewHolder holder, final int position) {
            ToDoTable current = listToDoTable.get(position);
            holder.html.setHtml(ActividadAString(current),
                    new HtmlResImageGetter(holder.html));


        }
        private String ActividadAString(ToDoTable todo){
            String html = "<a><big><b>" + todo.nombre+"b><big>";
            html+= "<br>" +todo.actividad+"</a>";
            return html;
        }


        @Override
        public int getItemCount() {
            return listToDoTable.size();
        }




    }
}
