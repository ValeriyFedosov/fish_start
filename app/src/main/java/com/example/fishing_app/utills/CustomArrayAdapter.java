package com.example.fishing_app.utills;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.fishing_app.R;

import java.util.ArrayList;
import java.util.List;

public class CustomArrayAdapter extends ArrayAdapter<ListItem> {

    private Context context;
    private List<ListItem> itemList;
    private LayoutInflater inflater;


    public CustomArrayAdapter(@NonNull Context context, int resource,
                              List<ListItem> itemList, LayoutInflater inflater) {
        super(context, resource, itemList);
        this.context = context;
        this.itemList = itemList;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        ListItem listItem = this.itemList.get(position);
        convertView = inflater.inflate(R.layout.list_view_item_1, null, false);
        if (convertView != null) {
            viewHolder = new ViewHolder();
            viewHolder.tv_name = convertView.findViewById(R.id.tv_name);
            viewHolder.tv_second_name = convertView.findViewById(R.id.tv_sec_name);
            viewHolder.im_item = convertView.findViewById(R.id.im_item);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_name.setText(listItem.getFish_name());
        viewHolder.tv_second_name.setText(listItem.getSecond_name());
        viewHolder.im_item.setImageResource(listItem.getImage_id());


        return convertView;
    }

    private class ViewHolder {
        TextView tv_name;
        TextView tv_second_name;
        ImageView im_item;
    }
}
