package com.example.clist;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.clist.Business;
import com.example.clist.R;
import com.example.clist.Retrofit.BusinessContactModel;
import com.example.clist.Retrofit.PersonalContactModel;

import java.util.ArrayList;

public class BusinessContactAdapter extends ArrayAdapter<BusinessContactModel> {

    public BusinessContactAdapter(Context activity, ArrayList<BusinessContactModel> bcm){
        super(activity,0,bcm);
    }

    @Override
    public View getView(int position,View convertView,ViewGroup parent) {

        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_layout, parent, false);
        }

        BusinessContactModel currentBusinessContactModel = getItem(position);

        TextView nameTextView = (TextView) listItemView.findViewById(R.id.layout_name);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        nameTextView.setText(currentBusinessContactModel.getName());

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView numberTextView = (TextView) listItemView.findViewById(R.id.layoutPhone);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        numberTextView.setText(currentBusinessContactModel.getVat_no());

        // Find the ImageView in the list_item.xml layout with the ID list_item_icon
        ImageView iconView = (ImageView) listItemView.findViewById(R.id.image);
//        // Get the image resource ID from the current AndroidFlavor object and
//        // set the image to iconView
        String pic = currentBusinessContactModel.getPic_add();
        if(pic != null) {
            iconView.setImageBitmap(BitmapFactory.decodeFile(pic));
        }else{
            iconView.setImageResource(R.drawable.ic_account_circle_24px);
        }
        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;

    }




}
