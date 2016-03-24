package stspz.vntu.com.pos.database;

import android.content.Context;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import stspz.vntu.com.pos.database.models.OrderDbItem;

public class DatabaseHelper {

    public static void addOrderItem(Context context, OrderDbItem itam) {
        Realm realm = Realm.getInstance(context);
        realm.beginTransaction();
        realm.copyToRealm(itam);
        realm.commitTransaction();
    }

    public static List<OrderDbItem> getOrderItems(Context context) {
        Realm realm = Realm.getInstance(context);
        List<OrderDbItem> items = realm.where(OrderDbItem.class).findAll();
        return items;
    }

    public static void clearOrderItems(Context context) {
        Realm realm = Realm.getInstance(context);
        RealmResults<OrderDbItem> items = realm.where(OrderDbItem.class).findAll();
        if(items != null && items.size() > 0){
            realm.beginTransaction();
            items.clear();
            realm.commitTransaction();
        }
    }

    public static OrderDbItem getOrderItemByCode(Context context, long code) {
        Realm realm = Realm.getInstance(context);
        OrderDbItem item = realm.where(OrderDbItem.class)
                .equalTo("code", code)
                .findFirst();
        return item;
    }

}
