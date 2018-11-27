package com.umeng.soexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class AnimationActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_animation );
        initView();
        Intent intent = getIntent();
        String pic1 = intent.getStringExtra( "pic1" );
        Picasso.with( this ).load( pic1 ).into( mImage );

    }

    private void initView() {
        mImage = (ImageView) findViewById( R.id.image );
        mImage.setOnClickListener( this );
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.image:
                break;
        }
    }
}
