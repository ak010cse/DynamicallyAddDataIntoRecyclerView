package com.example.dynamicallyrecyclerview;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerItemViewHolder> {
    private ArrayList<RecyclerData> myList;
    int mLastPosition = 0;
    private RemoveClickListner mListner;

    public RecyclerAdapter(ArrayList<RecyclerData> myList, RemoveClickListner mListner) {
        this.myList = myList;
        this.mListner = mListner;
    }


    @NonNull
    @Override
    public RecyclerItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        return new RecyclerItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerItemViewHolder holder, int position) {
        Log.d("onBindViewHoler ", myList.size() + "");

        holder.etTitleTextView.setText(myList.get(position).getTitle());
        holder.etDescriptionTextView.setText(myList.get(position).getDescription());
        holder.crossImage.setImageResource(R.drawable.ic_cross);
        mLastPosition = position;
    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    public void notifyData(ArrayList<RecyclerData> myList) {
        Log.d("notifyData ", myList.size() + "");
        this.myList = myList;
        notifyDataSetChanged();
    }


    public class RecyclerItemViewHolder extends RecyclerView.ViewHolder {
        private TextView etTitleTextView;
        private TextView etDescriptionTextView;
        private LinearLayout mainLayout;
        public ImageView crossImage;

        public RecyclerItemViewHolder(@NonNull final View itemView) {
            super(itemView);
            etTitleTextView = (TextView) itemView.findViewById(R.id.txtTitle);
            etDescriptionTextView = (TextView) itemView.findViewById(R.id.txtDescription);
            crossImage = (ImageView) itemView.findViewById(R.id.crossImage);
            mainLayout = (LinearLayout) itemView.findViewById(R.id.mainLayout);

            mainLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(itemView.getContext(), "Position:" + Integer.toString(getPosition()), Toast.LENGTH_SHORT).show();
                }
            });


            crossImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListner.OnRemoveClick(getAdapterPosition());

                }
            });
        }
    }
}
