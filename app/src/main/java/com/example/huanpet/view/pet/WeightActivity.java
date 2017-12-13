package com.example.huanpet.view.pet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huanpet.R;
import com.example.huanpet.custom.EditTextWithDelete;

public class WeightActivity extends AppCompatActivity {

    private EditTextWithDelete clearEditText;
    private TextView wc;
    private EditTextWithDelete searchEditText;
    private TextView button_clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight);
        initView();
        clearEditText = findViewById(R.id.searchEditText);


    }

    private void initView() {
        wc = (TextView) findViewById(R.id.wc);
        searchEditText = (EditTextWithDelete) findViewById(R.id.searchEditText);
        button_clear = (TextView) findViewById(R.id.button_clear);
        wc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(searchEditText.getText().toString())) {
                    Toast.makeText(WeightActivity.this, "昵称不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(WeightActivity.this, PetAddActivity.class);
                    intent.putExtra("cc", searchEditText.getText().toString());
                    startActivity(intent);



                    // TODO validate success, do something


                }
            }
        });
    }
}
