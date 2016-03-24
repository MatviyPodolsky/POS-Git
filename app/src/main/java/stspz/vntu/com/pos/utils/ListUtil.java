package stspz.vntu.com.pos.utils;

import java.util.List;

import stspz.vntu.com.pos.adapters.models.OrderItem;

/**
 * Created by mpodolsky on 03.03.2016.
 */
public class ListUtil {

    public static int getItemPosInList(List<OrderItem> items, OrderItem item) {
        for (int i = 0; i < items.size(); i++) {
            if (item.getCode() == items.get(i).getCode()) {
                return i;
            }
        }
        return -1;
    }

}
