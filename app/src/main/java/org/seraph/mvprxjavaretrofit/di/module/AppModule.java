package org.seraph.mvprxjavaretrofit.di.module;

import org.seraph.mvprxjavaretrofit.AppApplication;
import org.seraph.mvprxjavaretrofit.data.network.ApiManager;
import org.seraph.mvprxjavaretrofit.data.local.PreferencesManager;
import org.seraph.mvprxjavaretrofit.data.local.db.DBGreenDaoHelp;
import org.seraph.mvprxjavaretrofit.data.local.db.gen.DaoSession;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * 全局Module
 * date：2017/4/5 15:37
 * author：xiongj
 * mail：417753393@qq.com
 **/
@Module
public class AppModule {

    private final AppApplication application;

    public AppModule(AppApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    AppApplication provideApplication() {
        return application;
    }

    @Provides
    @Singleton
    PreferencesManager provideSharedPreferences() {
        return new PreferencesManager(application);
    }

    @Provides
    @Singleton
    DaoSession provideDaoSession() {
        return DBGreenDaoHelp.getSingleton().getDaoSession(application);
    }

    @Provides
    ApiManager provideApiManager() {
        return ApiManager.build(application);
    }
}
