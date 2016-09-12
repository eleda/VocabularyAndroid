package net.davidelekes.vocabularyandroid;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ModifyWordActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_word);

        Intent intent = getIntent();
        String oldEntry = intent.getStringExtra("oldentry");
        String[] vals = oldEntry.split("=");

        final TextView wordTextView = (TextView) findViewById(R.id.editText_modify_word);
        final TextView meaningTextView = (TextView) findViewById(R.id.editText_modify_meaning);

        wordTextView.setText(vals[0]);
        meaningTextView.setText(vals[1]);

        Button okButton = (Button) findViewById(R.id.button3);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("newentry", wordTextView.getText().toString() + "=" + meaningTextView.getText().toString());
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

    }
}
