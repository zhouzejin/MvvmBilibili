package com.sunny.mvvmbilibili.ui.home.discover;

import android.content.Context;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.view.View;

import com.sunny.mvvmbilibili.R;
import com.sunny.mvvmbilibili.data.DataManager;
import com.sunny.mvvmbilibili.data.model.pojo.HotWord;
import com.sunny.mvvmbilibili.injection.qualifier.ApplicationContext;
import com.sunny.mvvmbilibili.injection.scope.ConfigPersistent;
import com.sunny.mvvmbilibili.ui.base.BaseViewModel;
import com.sunny.mvvmbilibili.ui.layout.SettingItemLayout;
import com.sunny.mvvmbilibili.utils.LogUtil;
import com.sunny.mvvmbilibili.utils.RxUtil;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * The type Discover view model.
 * Created by Zhou Zejin on 2017/10/17.
 */

@ConfigPersistent
public class DiscoverViewModel extends BaseViewModel<DiscoverMvvmView> {

    public final SettingItemLayout[] itemLayouts = new SettingItemLayout[7];
    public final ObservableField<Boolean> isShowMoreTag = new ObservableField<>(false);
    public final ObservableList<HotWord> items = new ObservableArrayList<>();

    private final Context mContext;
    private final DataManager mDataManager;

    private Disposable mDisposable;

    {
        itemLayouts[0] = new SettingItemLayout() {
            @Override
            public int getItemIcon() {
                return R.drawable.ic_interest;
            }

            @Override
            public int getItemText() {
                return R.string.intersecting_circle;
            }

            @Override
            public boolean isShowArrow() {
                return false;
            }
        };
        itemLayouts[1] = new SettingItemLayout() {
            @Override
            public int getItemIcon() {
                return R.drawable.ic_topic;
            }

            @Override
            public int getItemText() {
                return R.string.topic_center;
            }

            @Override
            public boolean isShowArrow() {
                return false;
            }
        };
        itemLayouts[2] = new SettingItemLayout() {
            @Override
            public int getItemIcon() {
                return R.drawable.ic_activity;
            }

            @Override
            public int getItemText() {
                return R.string.activity_center;
            }

            @Override
            public boolean isShowArrow() {
                return false;
            }
        };
        itemLayouts[3] = new SettingItemLayout() {
            @Override
            public int getItemIcon() {
                return R.drawable.ic_creation_rank;
            }

            @Override
            public int getItemText() {
                return R.string.creation_rank;
            }
        };
        itemLayouts[4] = new SettingItemLayout() {
            @Override
            public int getItemIcon() {
                return R.drawable.ic_total_rank;
            }

            @Override
            public int getItemText() {
                return R.string.total_rank;
            }
        };
        itemLayouts[5] = new SettingItemLayout() {
            @Override
            public int getItemIcon() {
                return R.drawable.ic_game_center;
            }

            @Override
            public int getItemText() {
                return R.string.game_center;
            }
        };
        itemLayouts[6] = new SettingItemLayout() {
            @Override
            public int getItemIcon() {
                return R.drawable.ic_shopping;
            }

            @Override
            public int getItemText() {
                return R.string.around_shopping;
            }
        };
    }

    @Inject
    public DiscoverViewModel(@ApplicationContext Context context, DataManager dataManager) {
        mContext = context;
        mDataManager = dataManager;
    }

    @Override
    public void attachView(DiscoverMvvmView mvvmView) {
        super.attachView(mvvmView);
    }

    @Override
    public void detachView() {
        RxUtil.dispose(mDisposable);
        super.detachView();
    }

    public void loadData() {
        checkViewAttached();
        RxUtil.dispose(mDisposable);
        mDataManager.getHotWord()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<HotWord>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(@NonNull List<HotWord> hotWords) {
                        items.clear();
                        items.addAll(hotWords);
                        getMvvmView().showHotWord();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        LogUtil.e(e, "There was an error loading the HotWord.");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void goSearch() {
        if (items.isEmpty()) return;
        getMvvmView().goSearchView(items.get(0).keyword());
    }

    public void showMoreTag() {
        isShowMoreTag.set(!isShowMoreTag.get());
    }

    public TagFlowLayout.OnTagClickListener getOnTagClickListener() {
        return new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                getMvvmView().goSearchView(items.get(position).keyword());
                return true;
            }
        };
    }

    /*****
     * Inner ViewModel
     *****/

    public class HowWordViewModel {

        public final ObservableField<HotWord> howWord = new ObservableField<>();

        public HowWordViewModel(HotWord word) {
            howWord.set(word);
        }
    }

}
