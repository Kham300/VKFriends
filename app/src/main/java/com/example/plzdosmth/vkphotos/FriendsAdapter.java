package com.example.plzdosmth.vkphotos;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.plzdosmth.vkphotos.imageUtil.ImageDownloader;
import com.vk.sdk.api.model.VKApiUser;
import com.vk.sdk.api.model.VKList;

import java.util.ArrayList;
import java.util.List;

public class FriendsAdapter extends RecyclerView.Adapter<FriendsAdapter.VH>{

    private List<VKApiUser> list;

    public FriendsAdapter() {
        this.list = new ArrayList<>();
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.vk_friends_items, viewGroup, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH vh, int i) {
        VKApiUser userFull = list.get(i);
        vh.textView.setText(userFull.first_name);
        String  s =  userFull.photo_200;
        vh.imageView.setTag(s);
        ImageDownloader.getInstance().download(vh.imageView, userFull.photo_200);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class VH extends RecyclerView.ViewHolder{

        private TextView textView;
        private ImageView imageView;

        public VH(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.name);
            imageView = itemView.findViewById(R.id.image);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    //https://developer.android.com/training/animation/zoom
                }
            });
        }
    }

    public void setList(VKList<VKApiUser> list) {
        this.list = list;
    }
}
