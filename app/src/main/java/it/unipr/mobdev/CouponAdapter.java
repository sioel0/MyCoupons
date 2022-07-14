package it.unipr.mobdev;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

// TODO: make expired coupons appear in red color

public class CouponAdapter extends RecyclerView.Adapter<CouponAdapter.ViewHolder> {

    private final CouponList list;

    public CouponAdapter() {
        this.list = CouponList.getInstance();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // strings that will be use inside intent to switch to activity_detail view
        public static final String COUPON_CODE = "it.unipr.mobdev.CouponAdapter.ViewHolder.COUPON_CODE";
        public static final String COMPANY_NAME = "it.unipr.mobdev.CouponAdapter.ViewHolder.COMPANY_NAME";
        public static final String COUPON_EXPIRATION = "it.unipr.mobdev.CouponAdapter.ViewHolder.COUPON_EXTRA";

        private View v;

        public ViewHolder(View v) {
            super(v);
            this.v = v;
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), DetailActivity.class);
                    int position = getLayoutPosition();
                    intent.putExtra(COUPON_CODE, list.couponAtIndex(position).getCode());
                    intent.putExtra(COMPANY_NAME, list.couponAtIndex(position).getCompany());
                    if(list.couponAtIndex(position).getExpiration() != null)
                        intent.putExtra(COUPON_EXPIRATION, list.couponAtIndex(position).getExpiration().toString());
                    view.getContext().startActivity(intent);
                }
            });

            v.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    return false;
                }
            });
        }

        public void setText(String text) {
            TextView textView = (TextView) v.findViewById(R.id.textView);
            textView.setText(text);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.coupon_cell, parent, false);

        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setText(list.couponAtIndex(position).getCompany());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
