package stspz.vntu.com.pos.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import stspz.vntu.com.pos.R;
import stspz.vntu.com.pos.adapters.models.OrderItem;
import stspz.vntu.com.pos.adapters.utils.ItemTouchHelperAdapter;
import stspz.vntu.com.pos.adapters.utils.ItemTouchHelperViewHolder;
import stspz.vntu.com.pos.utils.ListUtil;

/**
 * Created by Alexander on 01.03.2016.
 */

public class ScannerAdapter extends RecyclerView.Adapter<ScannerAdapter.ViewHolder>
        implements ItemTouchHelperAdapter {

    public static final int RESOURCE = R.layout.item_scanner_product;

    private ArrayList<OrderItem> results;
    private Context context;
    private Callback callback;

    public ScannerAdapter(Context context, ArrayList<OrderItem> results) {
        this.context = context;
        this.results = results;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(RESOURCE, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final OrderItem item = results.get(position);

        holder.tvName.setText(item.getName());
        holder.tvPrice.setText(String.valueOf(item.getPrice()));
        holder.tvCount.setText(String.valueOf(item.getCount()));
        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callback != null) {
                    callback.onItemClick(position);
                }
            }
        });
    }

    public void addItem(OrderItem additional) {
        int index = ListUtil.getItemPosInList(results, additional);
        if (index >= 0) {
            OrderItem orderItem = results.get(index);
            orderItem.increaseCount(additional.getCount());
            orderItem.increasePrice(additional.getPrice());
            notifyItemChanged(index);
        } else {
            results.add(0, additional);
            notifyItemInserted(0);
        }
    }

    public void remove(OrderItem item) {
        int position = results.indexOf(item);
        if (position >= 0) {
            results.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        results.clear();
        notifyDataSetChanged();
    }

    public int getItemPosition(OrderItem item) {
        return results.indexOf(item);
    }

    public float getTotalMoney() {
        if (results == null) {
            return 0;
        }
        float sum = 0;
        for (OrderItem item : results) {
            sum += item.getPrice();
        }
        return sum;
    }

    public ArrayList<OrderItem> getAllItems() {
        return results;
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        return false;
    }

    @Override
    public void onItemDismiss(int position) {
        if (callback != null) {
            callback.onItemDismiss(results.get(position));
        }
    }

    public interface Callback {
        void onItemClick(int position);
        void onItemDismiss(OrderItem item);
    }

    static class ViewHolder extends RecyclerView.ViewHolder
            implements ItemTouchHelperViewHolder {

        @Bind(R.id.root)
        RelativeLayout root;
        @Bind(R.id.name)
        TextView tvName;
        @Bind(R.id.price)
        TextView tvPrice;
        @Bind(R.id.tv_count)
        TextView tvCount;
        private Resources res;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            res = view.getResources();
        }

        @Override
        public void onItemSwiping() {
            root.setBackgroundColor(res.getColor(R.color.itemDismissing));
        }

        @Override
        public void onItemClear() {
            root.setBackgroundColor(res.getColor(R.color.itemDefault));
        }
    }

}
