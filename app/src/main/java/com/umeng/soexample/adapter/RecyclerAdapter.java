package com.umeng.soexample.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.umeng.soexample.R;

import java.util.List;

/**
 * author：张腾
 * date：2018/11/17
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>{

    private Context context;
    private List<Integer> data;

    public RecyclerAdapter(Context context, List<Integer> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from( context ).inflate( R.layout.layout_item, viewGroup, false );
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }



    @Override
    public void onBindViewHolder(@NonNull final RecyclerAdapter.MyViewHolder myViewHolder, final int i) {
        myViewHolder.image_View.setImageResource(this.data.get(i));
        if (i % 2 == 0) {
            myViewHolder.image_View.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300));
        } else {
            myViewHolder.image_View.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 400));
        }

        myViewHolder.image_View.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick( i );
            }
        } );
    }

    public void removeItem(){
        if ((data.size()-1 >= 0)) {
            data.remove( 0 );
            notifyItemRemoved( 0 );
            notifyDataSetChanged();
        }else {
            Toast.makeText( context,"沒有數據了",Toast.LENGTH_SHORT ).show();
        }
    }

    public void addItem(){
        if ((data.size()>0)) {
            data.add( R.drawable.umeng_socialize_qzone);
            notifyItemInserted( 0 );
            notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {
        return this.data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView image_View;
        private OnItemClickListener mListener;

        public MyViewHolder(@NonNull View itemView) {
            super( itemView );
            image_View = (ImageView) itemView.findViewById( R.id.image_View);
        }

    }


    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    private OnItemClickListener listener;

    public void setOnClick(OnItemClickListener listener){
        this.listener = listener;
    }
}
