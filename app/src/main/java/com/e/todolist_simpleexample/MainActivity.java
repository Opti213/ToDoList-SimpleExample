package com.e.todolist_simpleexample;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends Activity {
    private ArrayList<String> toDoList;
    private ArrayAdapter<String> toDoListAdapter;
    private ListView toDoListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toDoListView = (ListView) findViewById(R.id.ToDoListView);
        toDoList = new ArrayList<String>();
        toDoListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, toDoList);
        toDoListView.setAdapter(toDoListAdapter);
        setupListViewListener(); //removeListener
        toDoList.add("First Item");
        toDoList.add("Second Item");
    }

    public void onAddToDo(View view){
        EditText addToDoEditText = findViewById(R.id.addToDoEditText);
        String text = addToDoEditText.getText().toString();
        toDoListAdapter.add(text);
        addToDoEditText.setText("");
    }

    private void setupListViewListener() {
        toDoListView.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapter,
                                                   View item, int pos, long id) {
                        toDoList.remove(pos);
                        toDoListAdapter.notifyDataSetChanged();
                        return true;
                    }

                });
    }
}
