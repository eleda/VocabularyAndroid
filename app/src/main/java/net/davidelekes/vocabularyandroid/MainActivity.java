package net.davidelekes.vocabularyandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static final int MODIFY_WORD = 2;

    private int actualIndex = -1;
    private ArrayList<String> simpleArray = new ArrayList<>();
    private ArrayAdapter arrayAdapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, simpleArray);

        simpleArray.add("word=szó");
        simpleArray.add("dog=kutya");

        listView.setAdapter(arrayAdapter);

        Button newButton = (Button) findViewById(R.id.button_new);
        newButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewWordActivity.class);
                startActivity(intent);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String val = simpleArray.get(i);
                actualIndex = i;
                Intent intent = new Intent(MainActivity.this, ModifyWordActivity.class);
                intent.putExtra("oldentry", val);
                startActivityForResult(intent, MODIFY_WORD);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == MODIFY_WORD) {

            if (resultCode == RESULT_OK) {

                simpleArray.set(actualIndex, data.getStringExtra("newentry"));
                arrayAdapter.notifyDataSetChanged();

            }
        }

    }
}
