package it.unipr.mobdev;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CouponAdapter extends RecyclerView.Adapter<CouponAdapter.ViewHolder> {

    private final CouponList list;

    public CouponAdapter() {
        this.list = CouponList.getInstance();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private View v;

        public ViewHolder(View v) {
            super(v);
            this.v = v;
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
