package com.example.clist;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.clist.Retrofit.PersonalContactModel;

import java.util.ArrayList;

public class ContactAdapter extends ArrayAdapter<PersonalContactModel> {

    public ContactAdapter(Activity context, ArrayList<PersonalContactModel> PersonalContactModels)
    {
        super(context,0,PersonalContactModels);
    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position The position in the list of data that should be displayed in the
     *                 list item view.
     * @param convertView The recycled view to populate.
     * @param parent The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_layout, parent, false);
        }

        // Get the {@link AndroidFlavor} object located at this position in the list
        PersonalContactModel currentPersonalContactModel = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView nameTextView = (TextView) listItemView.findViewById(R.id.layout_name);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        nameTextView.setText(currentPersonalContactModel.getName());

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView numberTextView = (TextView) listItemView.findViewById(R.id.layoutPhone);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        numberTextView.setText(currentPersonalContactModel.getPhone_number());

        // Find the ImageView in the list_item.xml layout with the ID list_item_icon
        ImageView iconView = (ImageView) listItemView.findViewById(R.id.image);
//        // Get the image resource ID from the current AndroidFlavor object and
//        // set the image to iconView
        String pic = currentPersonalContactModel.getPic_add();
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
