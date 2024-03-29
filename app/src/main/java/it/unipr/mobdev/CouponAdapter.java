package it.unipr.mobdev;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

public class CouponAdapter extends RecyclerView.Adapter<CouponAdapter.ViewHolder> {

    private final CouponList list;

    public CouponAdapter() {
        this.list = CouponList.getInstance();
    }

    // recyclerView element
    public class ViewHolder extends RecyclerView.ViewHolder {

        // strings that will be use inside intent to switch to activity_detail view
        public static final String COUPON_CODE = "it.unipr.mobdev.CouponAdapter.ViewHolder.COUPON_CODE";
        public static final String IDENTIFIER = "it.unipr.mobdev.CouponAdapter.ViewHolder.COMPANY_NAME";
        public static final String COUPON_EXPIRATION = "it.unipr.mobdev.CouponAdapter.ViewHolder.COUPON_EXTRA";

        private View v;

        public ViewHolder(View v) {
            super(v);
            this.v = v;

            // click on single cell opens detail activity
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), DetailActivity.class);
                    int position = getLayoutPosition();
                    // add additional data which will be displayed in intent destination activity
                    intent.putExtra(COUPON_CODE, list.couponAtIndex(position).getCode());
                    intent.putExtra(IDENTIFIER, list.couponAtIndex(position).getIdentifier());
                    // send coupon expiration date only if necessary
                    if(list.couponAtIndex(position).getExpiration() != null)
                        intent.putExtra(COUPON_EXPIRATION, list.couponAtIndex(position).getExpiration().toString());
                    // start new activity
                    view.getContext().startActivity(intent);
                }
            });

            // long click on a cell deletes it
            v.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int position = getLayoutPosition();
                    notifyItemRemoved(position);
                    Coupon removed = CouponList.getInstance().couponAtIndex(position);
                    CouponList.getInstance().removeElement(position);
                    // display toast to give the possibility to undo changes
                    Snackbar undo = Snackbar.make(view, "\"" + removed.getIdentifier() + "\" removed", Snackbar.LENGTH_LONG);
                    undo.setAction("undo", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            CouponList.getInstance().addElement(removed);
                            notifyItemInserted(position);
                        }
                    });
                    undo.show();
                    return false;
                }
            });
        }

        public void setText(String text, boolean expired) {
            TextView textView = (TextView) v.findViewById(R.id.textView);
            textView.setText(text);

            // if coupon is expired display it in red
            if(expired)
                textView.setTextColor(0xFFFF0000);
        }
    }

    // create a new viewHolder for the current that have to be displayed
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.coupon_cell, parent, false);

        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    // initialize a viewHolder based on its position
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        boolean expired = false;
        Coupon c = list.couponAtIndex(position);
        if(c.getExpiration() != null && c.getExpiration() != "")
            expired = DateManager.compareDates(DateManager.currentDate(), c.getExpiration());
        holder.setText(list.couponAtIndex(position).getIdentifier(), expired);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
