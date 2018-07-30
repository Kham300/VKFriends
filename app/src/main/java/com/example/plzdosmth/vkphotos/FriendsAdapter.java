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

    private CallBack callBack;



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
        String name =  userFull.first_name;
        String lastname =  userFull.last_name;
        vh.textViewName.setText(name);
        vh.textViewSureName.setText(lastname);
        String  s =  userFull.photo_50;
        vh.imageView.setTag(s);
        ImageDownloader.getInstance().download(vh.imageView, userFull.photo_200);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class VH extends RecyclerView.ViewHolder{

        private TextView textViewName;
        private TextView textViewSureName;
        private ImageView imageView;

        public VH(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.first_name);
            textViewSureName = itemView.findViewById(R.id.last_name);
            imageView = itemView.findViewById(R.id.image);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //https://developer.android.com/training/animation/zoom
                    String urlBigPhoto = list.get(getAdapterPosition()).photo_max_orig;

                    callBack.zoomImageFromThumb(imageView, urlBigPhoto);
                }
            });
        }
    }

    public interface CallBack{
        void  zoomImageFromThumb(ImageView imageView, String urlBigPhoto);
    }

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    public void setList(VKList<VKApiUser> list) {
        this.list = list;
    }
}
