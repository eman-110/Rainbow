package com.example.rainbow.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rainbow.models.Product;
import com.example.rainbow.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private Context context;
    private List<Product> productList;

    ImageView minusBtn, plusBtn;

    public ProductAdapter(Context context, List<Product> products) {
        this.context = context;
        this.productList = products;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product_card, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.name.setText(product.getName());
        holder.price.setText(product.getPrice() + " PKR");

        Picasso.get().load(product.getImage()).into(holder.image);

        holder.plusBtn.setOnClickListener(v -> {
            int count = Integer.parseInt(holder.counterText.getText().toString());
            count++;
            holder.counterText.setText(String.valueOf(count));
            holder.counterText.setVisibility(View.VISIBLE);
            holder.minusBtn.setVisibility(View.VISIBLE);
        });
        Picasso.get().load("https://res.cloudinary.com/dgtk4rthy/image/upload/v1752762272/ic_sub_nndqn1.png").into(holder.minusBtn);
        Picasso.get().load("https://res.cloudinary.com/dgtk4rthy/image/upload/v1752762304/ic_add_ylih4p.png").into(holder.plusBtn);

        holder.minusBtn.setOnClickListener(v -> {
            int count = Integer.parseInt(holder.counterText.getText().toString());
            if (count > 1) {
                count--;
                holder.counterText.setText(String.valueOf(count));
            } else {
                holder.counterText.setVisibility(View.GONE);
                holder.minusBtn.setVisibility(View.GONE);
                holder.counterText.setText("0");
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView name, price, counterText;
        ImageView image, plusBtn, minusBtn;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.productName);
            price = itemView.findViewById(R.id.productPrice);
            image = itemView.findViewById(R.id.productImage);
            plusBtn = itemView.findViewById(R.id.plusBtn);
            minusBtn = itemView.findViewById(R.id.minusBtn);
            counterText = itemView.findViewById(R.id.counterText);
        }
    }
}
