package com.android.charlottecao.demo1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Charlottecao on 2017/5/13.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private Context mContext;

    private List<Fruit> mFruitList;



    static class ViewHolder extends  RecyclerView.ViewHolder{

        View itemView;

        CircleImageView fruitImageView;

        TextView fruitTextView;

        public ViewHolder(View view) {
            super(view);

            itemView = view;
            fruitImageView = (CircleImageView) view.findViewById(R.id.list_item_fruit_image);
            fruitTextView = (TextView) view.findViewById(R.id.list_item_fruit_name);

        }
    }

    /*传入数据源*/
    public ListAdapter(List<Fruit> fruitList) {
        mFruitList = fruitList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }

        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false);

        final ViewHolder holder = new ViewHolder(view);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentActivity.actionStart(mContext);
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Fruit fruit = mFruitList.get(position);
        viewHolder.fruitTextView.setText(fruit.getName());
        Glide.with(mContext).load(R.drawable.sample).into(viewHolder.fruitImageView);
    }

    @Override
    public int getItemCount() {
        return mFruitList.size();
    }


}
