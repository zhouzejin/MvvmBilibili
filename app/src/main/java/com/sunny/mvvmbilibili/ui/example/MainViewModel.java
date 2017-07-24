package com.sunny.mvvmbilibili.ui.example;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import com.sunny.mvvmbilibili.data.DataManager;
import com.sunny.mvvmbilibili.data.model.bean.Subject;
import com.sunny.mvvmbilibili.injection.scope.ConfigPersistent;
import com.sunny.mvvmbilibili.ui.base.BaseViewModel;
import com.sunny.mvvmbilibili.utils.LogUtil;
import com.sunny.mvvmbilibili.utils.RxUtil;
import com.sunny.mvvmbilibili.utils.imageloader.ImageLoader;

@ConfigPersistent
public class MainViewModel extends BaseViewModel<MainMvvmView> {

    // These observable fields will update Views automatically
    public final ObservableList<Subject> items = new ObservableArrayList<>();

    private final DataManager mDataManager;

    private Subscription mSubscription;

    @Inject
    public MainViewModel(DataManager dataManager, ImageLoader imageLoader) {
        sImageLoader = imageLoader;
        mDataManager = dataManager;
    }

    @Override
    public void attachView(MainMvvmView mvvmView) {
        super.attachView(mvvmView);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mSubscription != null) mSubscription.unsubscribe();
    }

    public void loadSubjects() {
        checkViewAttached();
        RxUtil.unsubscribe(mSubscription);
        mSubscription = mDataManager.getSubjects()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<List<Subject>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e(e, "There was an error loading the subjects.");
                        getMvvmView().showError();
                    }

                    @Override
                    public void onNext(List<Subject> subjects) {
                        items.clear();
                        if (subjects.isEmpty()) {
                            getMvvmView().showSubjectsEmpty();
                        } else {
                            items.addAll(subjects);
                        }
                    }
                });
    }

    /*****
     * Inner ViewModel
     *****/

    public static class SubjectViewModel extends BaseViewModel {

        public final ObservableField<String> title = new ObservableField<>();
        public final ObservableField<String> genres = new ObservableField<>();
        public final ObservableField<String> imageUrl = new ObservableField<>();

        public SubjectViewModel(Subject subject) {
            title.set(subject.title());
            genres.set(subject.genres().toString());
            imageUrl.set(subject.images().small());
        }
    }

}
