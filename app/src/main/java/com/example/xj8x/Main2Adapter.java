package com.example.xj8x;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import java.util.List;

/*
 *Creat by Ao on 2019/8/24--10:06
 *描述: 我是一个适配器
 */
public class Main2Adapter extends RecyclerView.Adapter<Main2Adapter.ViewHolder>{

    public interface OnClickListener {
        void onItemClick(ViewHolder viewHolder, Item2 item);
    }

    private OnClickListener listener;

    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }

    private List<Item2> items;

    public Main2Adapter(List<Item2> items) {
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item2, parent, false);
        ViewHolder vh = new ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Item2 item = items.get(position);

        holder.textView.setText(item.getNum());

        if (listener != null) {
            holder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(holder, item);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public Button button;

        public ViewHolder(View itemView) {
            super(itemView);

            textView = (TextView) itemView.findViewById(R.id.text);
            button = (Button) itemView.findViewById(R.id.button);
        }
    }

}
