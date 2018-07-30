package com.example.plzdosmth.vkphotos;

import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.model.VKApiUser;
import com.vk.sdk.api.model.VKList;

import java.util.List;

class VKFriendsModel {

    private VKList list;

    public VKFriendsModel() {
        list = new VKList();
    }

    public List<VKApiUser> getFriends(){
        VKRequest request = VKApi.friends().get(VKParameters.from(VKApiConst.FIELDS, "first_name, last_name, photo_200, photo_max_orig"));
        request.executeSyncWithListener(new VKRequest.VKRequestListener() {

            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);
                list = (VKList) response.parsedModel;
            }
        });
        return list;
    }
}
