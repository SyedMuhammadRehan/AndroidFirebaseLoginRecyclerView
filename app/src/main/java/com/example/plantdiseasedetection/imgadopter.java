//package com.example.plantdiseasedetection;
//
//
//
//
//        import android.content.Context;
//        import android.view.LayoutInflater;
//        import android.view.View;
//        import android.view.ViewGroup;
//        import android.widget.ImageView;
//
//        import androidx.annotation.NonNull;
//        import androidx.recyclerview.widget.RecyclerView;
//
//        import com.bumptech.glide.Glide;
//
//        import java.util.ArrayList;
//
//public class imgadopter extends RecyclerView.Adapter<imgadopter.ImageViewHolder>{
//    private Context showAll;
//    private ArrayList<imgModel>list;
//    public imgadopter(Context showAll, ArrayList<Model> list) {
//        this.list=list;
//        this.showAll=showAll;
//    }
//
//    @NonNull
//    @Override
//    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View v= LayoutInflater.from(showAll).inflate(R.layout.item,parent,false);
//        return new ImageViewHolder(v);
//    }
//
//    @Override
//    public void onBindViewHolder(imgadopter.ImageViewHolder holder, int position) {
//        Glide.with(showAll).load(list.get(position).getImageUri()).into(holder.imgV);
//    }
//
//    @Override
//    public int getItemCount() {
//        return list.size();
//    }
//
//    public static class ImageViewHolder extends RecyclerView.ViewHolder{
//        ImageView imgV;
//        public ImageViewHolder( View itemView) {
//            super(itemView);
//            imgV=itemView.findViewById(R.id.imageView);
//        }
//    }
//
//}