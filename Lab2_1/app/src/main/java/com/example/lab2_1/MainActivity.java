package com.example.lab2_1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayAdapter<String> adapter;
    ListView lvPerson;
    TextView tvPerson;
    ArrayList<String> arrayName;
    int selectedPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvPerson = findViewById(R.id.tv_person);
        lvPerson = findViewById(R.id.lv_person);

        arrayName = new ArrayList<>();
        arrayName.add("Tèo_22521580");
        arrayName.add("Tý_Nguyễn");
        arrayName.add("Bin_Quang");
        arrayName.add("Bo_Trường");

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayName);
        lvPerson.setAdapter(adapter);
        lvPerson.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lvPerson.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedPosition = position;
                String value = lvPerson.getItemAtPosition(position).toString();
                tvPerson.setText("position :" + position + " ; value = " + value);
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        lvPerson.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, arrayName) {
            @Override
            public View getView(int position, View convertView, android.view.ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                if (position == selectedPosition) {
                    view.setBackgroundResource(R.drawable.button_selector);
                    view.setActivated(true);
                } else {
                    view.setBackgroundResource(R.drawable.button_selector);
                    view.setActivated(false);
                }
                return view;
            }
        });
    }
}
