package com.example.lab2_2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayAdapter<String> adapter;
    ListView lvPerson;
    TextView tvPerson;
    ArrayList<String> arrayName;

    Button btnAdd;
    EditText edtName;
    int selectedPosition = -1; // Lưu trữ vị trí mục được chọn

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvPerson = (TextView) findViewById(R.id.tv_person);
        lvPerson = (ListView) findViewById(R.id.lv_person);
        edtName = (EditText) findViewById(R.id.edtName);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        arrayName = new ArrayList<>();
        arrayName.add("Tèo_22521580");
        arrayName.add("Tý_Nguyễn");
        arrayName.add("Bin_Quang");
        arrayName.add("Bo_Trường");

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayName);
        lvPerson.setAdapter(adapter);

        // Đặt chế độ để ListView cho phép chọn các mục
        lvPerson.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ten = edtName.getText().toString();
                arrayName.add(ten);
                adapter.notifyDataSetChanged();
            }
        });

        lvPerson.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String value = lvPerson.getItemAtPosition(position).toString();
                tvPerson.setText("position :" + position + " ; value =" + value);

                // Đặt mục đã chọn và làm sáng nó
                lvPerson.setItemChecked(position, true);

                // Cập nhật trạng thái cho các mục đã chọn
                selectedPosition = position;

                // Đặt selector cho view để mục được chọn phát sáng
                view.setBackgroundResource(R.drawable.button_selector);

                // Đặt lại trạng thái cho các mục khác trong danh sách
                for (int i = 0; i < parent.getChildCount(); i++) {
                    if (i != position) {
                        parent.getChildAt(i).setBackgroundResource(android.R.color.transparent);
                    }
                }
            }
        });

        lvPerson.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                arrayName.remove(position);
                adapter.notifyDataSetChanged();
                return false;
            }
        });
    }
}
