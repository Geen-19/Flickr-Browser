package com.example.FlickrBrowser1;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.forfinding.R;
import com.squareup.picasso.Picasso;

import java.util.List;

class FlickrRecyclerViewAdapter extends RecyclerView.Adapter<FlickrRecyclerViewAdapter.FlickrImageViewHolder> {
    private static final String TAG = "FlickrRecyclerViewAdapt";
    private List<Photo> mPhotosList;


    public FlickrRecyclerViewAdapter(List<Photo> photosList) {
        mPhotosList = photosList;
    }


    @NonNull
    @Override
    public FlickrImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // called by the layout manager when it needs a view
        Log.d(TAG, "onCreateViewHolder: new view requested");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.browse, parent, false);
        return new FlickrImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FlickrImageViewHolder holder, int position) {
        //Called by the layout when it want new data in existing row.
        if(mPhotosList == null || (mPhotosList.size() == 0)){
            holder.thumbnail.setImageResource(R.drawable.placeholder);
            holder.title.setText("No Photos match your search.\n\n Use the search icon to search more photos");

        } else {
            Photo photoItem;
            photoItem = mPhotosList.get(position);
            Log.d(TAG, "onBindViewHolder: "+photoItem.getTitle()+ "-->"+ position);
            Picasso.get().load(photoItem.getImage())
                    .error(R.drawable.placeholder)
                    .placeholder(R.drawable.placeholder)
                    .into(holder.thumbnail);

            holder.title.setText(photoItem.getTitle());
        }




    }
    @SuppressLint("NotifyDataSetChanged")
    void loadNewData(List<Photo> newPhoto){
        mPhotosList = newPhoto;
        notifyDataSetChanged();
    }

    public Photo getPhoto(int position) {
        return ((mPhotosList != null) && (mPhotosList.size() != 0) ? mPhotosList.get(position) : null);
    }

    @Override
    public int getItemCount() {
        if((mPhotosList != null) && (mPhotosList.size() != 0)) {
            return mPhotosList.size();
        } else {
            return 1;
        }
        //or
        //return ((mPhotoList != null)) && (mPhotoList.size() != 0) ? mPhotoList.size() : 0;
    }

    static class FlickrImageViewHolder extends RecyclerView.ViewHolder{
        private static final String TAG = "FlickrImageViewHolder";
        ImageView thumbnail = null;
        TextView title = null;

        public FlickrImageViewHolder(@NonNull View itemView) {
            super(itemView);
            Log.d(TAG, "FlickrImageViewHolder: starts");
            this.thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);
            this.title = (TextView) itemView.findViewById(R.id.title2);
        }
    }
}
