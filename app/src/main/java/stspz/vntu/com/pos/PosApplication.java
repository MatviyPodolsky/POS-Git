package stspz.vntu.com.pos;

import android.app.Application;
import android.content.ContextWrapper;

import com.pixplicity.easyprefs.library.Prefs;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.exceptions.RealmMigrationNeededException;

/**
 * Created by Alexander on 29.02.2016.
 */

public class PosApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        new Prefs.Builder()
                .setContext(this)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(getPackageName())
                .setUseDefaultSharedPreference(true)
                .build();

        buildDatabase();
    }

    public Realm buildDatabase() {
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this)
                .name("data.realm")
                .schemaVersion(1)
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
        try {
            return Realm.getDefaultInstance();
        } catch (RealmMigrationNeededException e) {
            try {
                Realm.deleteRealm(realmConfiguration);
                //Realm file has been deleted.
                return Realm.getInstance(realmConfiguration);
            } catch (Exception ex) {
                throw ex;
                //No Realm file to remove.
            }
        }
    }

}
