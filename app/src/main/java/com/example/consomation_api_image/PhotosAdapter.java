package com.example.consomation_api_image;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.PhotoViewHolder> {

    private List<Photo> photos;

    public PhotosAdapter(List<Photo> photos) {
        this.photos = photos;
    }

    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo, parent, false);
        return new PhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PhotoViewHolder holder, int position) {
        Photo photo = photos.get(position);
        holder.titleTextView.setText(photo.getTitle());
        holder.urlTextView.setText(photo.getUrl()); // Affichage de l'URL de l'image
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    public static class PhotoViewHolder extends RecyclerView.ViewHolder {

        TextView titleTextView;
        TextView urlTextView; // TextView pour afficher l'URL

        public PhotoViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            urlTextView = itemView.findViewById(R.id.urlTextView); // Initialisation du TextView pour l'URL
        }
    }
}
