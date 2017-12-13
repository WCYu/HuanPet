package com.example.huanpet.view.pet;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.huanpet.R;
import com.example.huanpet.view.main.HomeActivity;
import com.example.huanpet.view.pet.View.TipPop;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class PetActivity extends AppCompatActivity implements View.OnClickListener {


    private RelativeLayout title_view;
    private ListView listview;
    private TipPop tipPop;
    private View titleView;
    private ImageView tz;
    private ImageView fhhh;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet);
        initView();

    }

    @SuppressLint("WrongViewCast")
    private void initView() {

        tipPop = TipPop.getInstance(this);

        fhhh = (ImageView) findViewById(R.id.fhhh);
        fhhh.setOnClickListener(this);
        titleView = findViewById(R.id.title_view);
        tz = (ImageView) findViewById(R.id.tz);
        tz.setOnClickListener(this);
        title_view = (RelativeLayout) findViewById(R.id.title_view);
        title_view.setOnClickListener(this);

fhhh.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
      Intent in=new Intent(PetActivity.this, HomeActivity.class);
      startActivity(in);
    }
});
        tz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(PetActivity.this, PetAddActivity.class);
                startActivity(in);
            }
        });



    }


    @Override
    protected void onResume() {
        super.onResume();
        Observable.timer(500, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() {

            public void accept(Long aLong) throws Exception {
                tipPop.showAsDropDown(titleView, 320, 20);
            }
        });
    }


    @Override
    public void onClick(View v) {

    }
}
