package com.umeng.soexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.umeng.soexample.adapter.RecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

public class WaterfallActivity extends AppCompatActivity {

    private ImageView mImageview;
    private TextView mTextview;
    private RecyclerView mRecyclerView;
    private RecyclerAdapter adapter;
    private Button mBtn_insert;
    private Button mBtn_delete;
    private List<Integer> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_waterfall );
        //初始化控件
        initView();
        //回调传值
        initCallbackvalue();

        adapter = new RecyclerAdapter( this, createData() );

        mRecyclerView.setLayoutManager( new StaggeredGridLayoutManager( 3, StaggeredGridLayoutManager.VERTICAL ) );
        mRecyclerView.setAdapter( adapter );

    }


    private void initCallbackvalue() {
        Intent intent = getIntent();
        //取值
        String name = intent.getStringExtra( "name" );
        String pic = intent.getStringExtra( "pic" );
        //赋值
        mTextview.setText( name );
        Picasso.with( this ).load( pic ).into( mImageview );

        mBtn_insert.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              adapter.addItem();
            }
        } );

        mBtn_delete.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.removeItem();
            }
        } );
    }

    private void initView() {
        mImageview = (ImageView) findViewById( R.id.imageview );
        mTextview = (TextView) findViewById( R.id.textview );
        mRecyclerView = (RecyclerView) findViewById( R.id.recyclerView );
        mBtn_insert = (Button) findViewById( R.id.btn_insert );
        mBtn_delete = (Button) findViewById( R.id.btn_delete );

    }

    private List<Integer> createData() {
        data = new ArrayList<Integer>();
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                data.add( R.drawable.jd );
            } else {
                data.add( R.drawable.umeng_socialize_qq );
            }
        }
        return data;

    }
}
