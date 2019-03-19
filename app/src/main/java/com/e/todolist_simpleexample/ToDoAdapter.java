package com.e.todolist_simpleexample;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;


import java.util.ArrayList;

public class ToDoAdapter extends BaseAdapter {
    Context context;
    LayoutInflater lInflater;
    ArrayList<ToDoItem> toDoItems;

    ToDoAdapter(Context context, ArrayList<ToDoItem> toDoItems){
        this.context = context;
        this.toDoItems = toDoItems;
        lInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return toDoItems.size();
    }

    @Override
    public Object getItem(int position) {
        return toDoItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void add(ToDoItem item){
        toDoItems.add(item);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.item, parent, false);
        }
        ToDoItem p = getToDoItem(position);
        //set fields
        ((TextView) view.findViewById(R.id.tvDescr)).setText(p.name);
        ((TextView) view.findViewById(R.id.tvDeadLine)).setText(p.deadline.toString() + "");
        //create checkbox and set listener
        CheckBox cbBuy = (CheckBox) view.findViewById(R.id.cbBox);
        cbBuy.setOnCheckedChangeListener(myCheckChangeList);
        cbBuy.setTag(position);
        cbBuy.setChecked(p.isDone);
        return view;
    }

     public ToDoItem getToDoItem(int position){
        return ((ToDoItem)getItem(position));
    }

    ArrayList<ToDoItem> getDoneList(){
        ArrayList<ToDoItem> returnedList = new ArrayList<>();
        for (ToDoItem tdi : toDoItems){
            if (tdi.isDone) returnedList.add(tdi);
        }
        return returnedList;
    }

    OnCheckedChangeListener myCheckChangeList = new OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton buttonView,
                                     boolean isChecked) {
            getToDoItem((Integer) buttonView.getTag()).isDone = isChecked;
        }
    };


}
