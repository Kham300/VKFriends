package com.example.plzdosmth.vkphotos;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.vk.sdk.api.model.VKApiUser;
import com.vk.sdk.api.model.VKList;

@StateStrategyType(AddToEndStrategy.class)
public interface VkFriendsView extends MvpView{
    void show(final VKList<VKApiUser> list);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void login();

}
