package com.example.rainbow;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.rainbow.adapters.ProductAdapter;
import com.example.rainbow.models.Product;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    LinearLayout categoryContainer, cardContainer;
    ImageView searchCamera, highlightImage, downArrow, FreshCardImg1, FreshCardImg2, firstOrderBanner, vendorLogo, juice, orange_juice, basket, product_orange_juice, granola, vendorLogo1, juice1, orange_juice1, basket1, product_orange_juice1, granola1, black_img;
    ConstraintLayout highlightCard;
    Target backgroundTarget;
    private RecyclerView fruitsRecycler, vegetablesRecycler, recommendedRecycler1, recommendedRecycler2;

    private final List<Product> fruitsList = new ArrayList<>();
    private final List<Product> vegetablesList = new ArrayList<>();
    private final List<Product> otherList = new ArrayList<>();

    private ProductAdapter fruitsAdapter, vegetablesAdapter, recommendedAdapter1, recommendedAdapter2;

    public HomeFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Bind views
        categoryContainer = view.findViewById(R.id.categoryContainer);
        cardContainer = view.findViewById(R.id.cardContainer);
        searchCamera = view.findViewById(R.id.searchCamera);
        highlightImage = view.findViewById(R.id.highlightImage);
        downArrow = view.findViewById(R.id.downArrow);
        highlightCard = view.findViewById(R.id.highlightCard);
        FreshCardImg1 = view.findViewById(R.id.FreshCardImg1);
        FreshCardImg2 = view.findViewById(R.id.FreshCardImg2);
        firstOrderBanner = view.findViewById(R.id.firstOrderBanner);
        vendorLogo = view.findViewById(R.id.vendorLogo);
        juice = view.findViewById(R.id.juice);
        orange_juice = view.findViewById(R.id.orange_juice);
        basket = view.findViewById(R.id.basket);
        product_orange_juice = view.findViewById(R.id.product_orange_juice);
        granola = view.findViewById(R.id.granola);
        vendorLogo1 = view.findViewById(R.id.vendorLogo1);
        juice1 = view.findViewById(R.id.juice1);
        orange_juice1 = view.findViewById(R.id.orange_juice1);
        basket1 = view.findViewById(R.id.basket1);
        product_orange_juice1 = view.findViewById(R.id.product_orange_juice1);
        granola1 = view.findViewById(R.id.granola1);
        black_img = view.findViewById(R.id.black_img);


        ConstraintLayout topVendorSection = view.findViewById(R.id.topVendorSection1);
        ConstraintLayout topVendorSection2 = view.findViewById(R.id.topVendorSection2);

        Picasso.get()
                .load("https://res.cloudinary.com/dgtk4rthy/image/upload/v1752762349/safeway_background_eax7bq.png")
                .into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        Drawable drawable = new BitmapDrawable(getResources(), bitmap);
                        topVendorSection2.setBackground(drawable);
                    }

                    @Override
                    public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                        // handle failure
                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {
                        topVendorSection2.setBackground(placeHolderDrawable);
                    }
                });

        Picasso.get()
                .load("https://res.cloudinary.com/dgtk4rthy/image/upload/v1752762349/organic_card_background_kifppv.png")
                .into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        Drawable drawable = new BitmapDrawable(getResources(), bitmap);
                        topVendorSection.setBackground(drawable);
                    }

                    @Override
                    public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                        // handle failure
                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {
                        topVendorSection.setBackground(placeHolderDrawable);
                    }
                });


        // Set up category boxes and promo cards
        addCategories();
        addPromoCards(view);

        // Start countdown timers for flash sales
        startCountdown(3 * 24 * 60 * 60 * 1000L, view.findViewById(R.id.flashDays1), view.findViewById(R.id.flashHours1), view.findViewById(R.id.flashMin1), view.findViewById(R.id.flashSec1));
        startCountdown(1 * 24 * 60 * 60 * 1000L, view.findViewById(R.id.flashDays2), view.findViewById(R.id.flashHours2), view.findViewById(R.id.flashMin2), view.findViewById(R.id.flashSec2));

        // Load static images via Picasso
        Picasso.get().load("https://res.cloudinary.com/dgtk4rthy/image/upload/v1752762316/camera_pxtatg.png").into(searchCamera);
        Picasso.get().load("https://res.cloudinary.com/dgtk4rthy/image/upload/v1752762254/grocery_image_w0xqsy.png").into(highlightImage);
        Picasso.get().load("https://res.cloudinary.com/dgtk4rthy/image/upload/v1752762289/ic_down_qxrdyk.png").into(downArrow);
        Picasso.get().load("https://res.cloudinary.com/dgtk4rthy/image/upload/v1752762263/flash_vegetables_pzkqsc.png").into(FreshCardImg1);
        Picasso.get().load("https://res.cloudinary.com/dgtk4rthy/image/upload/v1752762264/flash_snacks_gtl8m3.png").into(FreshCardImg2);
        Picasso.get().load("https://res.cloudinary.com/dgtk4rthy/image/upload/v1752762226/offer_bar_su7jpt.png").into(firstOrderBanner);
        Picasso.get().load("https://res.cloudinary.com/dgtk4rthy/image/upload/v1752762226/nature_food_logo_hkuczt.png").into(vendorLogo);
        Picasso.get().load("https://res.cloudinary.com/dgtk4rthy/image/upload/v1752762216/product_juice_bxmxip.png").into(juice);
        Picasso.get().load("https://res.cloudinary.com/dgtk4rthy/image/upload/v1752762203/product_orange_juice_cclspi.png").into(orange_juice);
        Picasso.get().load("https://res.cloudinary.com/dgtk4rthy/image/upload/v1752762203/product_orange_juice_cclspi.png").into(product_orange_juice);
        Picasso.get().load("https://res.cloudinary.com/dgtk4rthy/image/upload/v1752762216/product_granola_x5l3rz.png").into(granola);
        Picasso.get().load("https://res.cloudinary.com/dgtk4rthy/image/upload/v1752762217/product_basket_h7cypf.png").into(basket);
        Picasso.get().load("https://res.cloudinary.com/dgtk4rthy/image/upload/v1752762195/safeway_logo_m0hdbe.png").into(vendorLogo1);
        Picasso.get().load("https://res.cloudinary.com/dgtk4rthy/image/upload/v1752762216/product_juice_bxmxip.png").into(juice1);
        Picasso.get().load("https://res.cloudinary.com/dgtk4rthy/image/upload/v1752762203/product_orange_juice_cclspi.png").into(orange_juice1);
        Picasso.get().load("https://res.cloudinary.com/dgtk4rthy/image/upload/v1752762203/product_orange_juice_cclspi.png").into(product_orange_juice1);
        Picasso.get().load("https://res.cloudinary.com/dgtk4rthy/image/upload/v1752762216/product_granola_x5l3rz.png").into(granola1);
        Picasso.get().load("https://res.cloudinary.com/dgtk4rthy/image/upload/v1752762217/product_basket_h7cypf.png").into(basket1);
        Picasso.get().load("https://res.cloudinary.com/dgtk4rthy/image/upload/v1752762318/blank_img_suxvrj.png").into(black_img);




        backgroundTarget = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                if (highlightCard != null) {
                    highlightCard.setBackground(new BitmapDrawable(getResources(), bitmap));
                }
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
            }
        };
        Picasso.get().load("https://res.cloudinary.com/dgtk4rthy/image/upload/v1752762342/background_grocery_y5qx0g.png").into(backgroundTarget);

        final LinearLayout layout1 = view.findViewById(R.id.bg_flash_sale1);
        Picasso.get().load("https://res.cloudinary.com/dgtk4rthy/image/upload/v1752762337/bg_flash_sale_kzemil.png")
                .into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        layout1.setBackground(new BitmapDrawable(layout1.getContext().getResources(), bitmap));
                    }

                    @Override
                    public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {
                    }
                });

        final LinearLayout layout2 = view.findViewById(R.id.bg_flash_sale2);
        Picasso.get().load("https://res.cloudinary.com/dgtk4rthy/image/upload/v1752762337/bg_flash_sale_green_p1olg3.png")
                .into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        layout2.setBackground(new BitmapDrawable(layout2.getContext().getResources(), bitmap));
                    }

                    @Override
                    public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {
                    }
                });

        // Initialize RecyclerViews
        fruitsRecycler = view.findViewById(R.id.fruitsRecycler);
        vegetablesRecycler = view.findViewById(R.id.vegetablesRecycler);
        recommendedRecycler1 = view.findViewById(R.id.recommendedRecycler1);
        recommendedRecycler2 = view.findViewById(R.id.recommendedRecycler2);

        // Setup layout managers
        setupRecycler(fruitsRecycler);
        setupRecycler(vegetablesRecycler);
        setupRecycler(recommendedRecycler1);
        setupRecycler(recommendedRecycler2);

        // Fetch and assign product data
        fetchProducts();

        return view;
    }

    private void addCategories() {
        addCategory("https://res.cloudinary.com/dgtk4rthy/image/upload/v1752762267/drinks_juice_wbxjgn.png", "Drinks & Juice", "125+ Products");
        addCategory("https://res.cloudinary.com/dgtk4rthy/image/upload/v1752762321/animals_food_az4umd.png", "Animals Food", "125+ Products");
        addCategory("https://res.cloudinary.com/dgtk4rthy/image/upload/v1752762195/yummy_candy_ns7zn7.png", "Yummy Candy", "125+ Products");
        addCategory("https://res.cloudinary.com/dgtk4rthy/image/upload/v1752762255/fresh_fruit_jqbtkl.png", "Fresh Fruit", "125+ Products");
    }

    private void addCategory(String imageUrl, String title, String subtitle) {
        LinearLayout category = new LinearLayout(getContext());
        category.setOrientation(LinearLayout.VERTICAL);
        category.setLayoutParams(new LinearLayout.LayoutParams(300, LinearLayout.LayoutParams.WRAP_CONTENT));
        category.setPadding(0, 0, 32, 0);
        category.setGravity(Gravity.CENTER);
        category.setClickable(true);
        category.setFocusable(true);

        FrameLayout frameLayout = new FrameLayout(getContext());
        FrameLayout.LayoutParams frameParams = new FrameLayout.LayoutParams(144, 144);
        frameLayout.setLayoutParams(frameParams);

        ImageView border = new ImageView(getContext());
        border.setLayoutParams(frameParams);
        border.setScaleType(ImageView.ScaleType.FIT_XY);
        Picasso.get().load("https://res.cloudinary.com/dgtk4rthy/image/upload/v1752762315/circle_border_gpkqnv.png").into(border);

        ImageView image = new ImageView(getContext());
        int innerSize = 96;
        FrameLayout.LayoutParams imageParams = new FrameLayout.LayoutParams(innerSize, innerSize);
        imageParams.gravity = Gravity.CENTER;
        image.setLayoutParams(imageParams);
        image.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        Picasso.get().load(imageUrl).into(image);

        frameLayout.addView(border);
        frameLayout.addView(image);

        TextView titleText = new TextView(getContext());
        titleText.setText(title);
        titleText.setTextColor(0xFF000000);
        titleText.setTextSize(14);
        titleText.setGravity(Gravity.CENTER);

        TextView subtitleText = new TextView(getContext());
        subtitleText.setText(subtitle);
        subtitleText.setTextColor(0xFF888888);
        subtitleText.setTextSize(12);
        subtitleText.setGravity(Gravity.CENTER);

        category.addView(frameLayout);
        category.addView(titleText);
        category.addView(subtitleText);
        categoryContainer.addView(category);
    }


    private void addPromoCards(View view) {
        LinearLayout container = view.findViewById(R.id.cardContainer);
        addPromoCard(container, "Daily Fresh\nVegetables", "Shop Now →", "https://res.cloudinary.com/dgtk4rthy/image/upload/v1752762310/bg_vegetable_card_cclu2w.png");
        addPromoCard(container, "Everyday \nFresh Milk", "Shop Now →", "https://res.cloudinary.com/dgtk4rthy/image/upload/v1752762310/bg_milk_card_rmfdth.png");
        addPromoCard(container, "Fresh Organic\nVegetables", "Shop Now →", "https://res.cloudinary.com/dgtk4rthy/image/upload/v1752762310/bg_vegetable_card_cclu2w.png");
    }

    private void addPromoCard(LinearLayout container, String title, String buttonText, String imageUrl) {
        Context context = requireContext();

        FrameLayout card = new FrameLayout(context);
        LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, getResources().getDisplayMetrics()),
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 140, getResources().getDisplayMetrics())
        );
        cardParams.setMarginEnd((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 12, getResources().getDisplayMetrics()));
        card.setLayoutParams(cardParams);
        card.setPadding(20, 20, 20, 20);

        // Load background image dynamically
        Picasso.get().load(imageUrl).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                card.setBackground(new BitmapDrawable(getResources(), bitmap));
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
            }
        });

        // Title text
        TextView promoTitle = new TextView(context);
        promoTitle.setText(title);
        promoTitle.setTextSize(16);
        promoTitle.setTextColor(0xFF000000);
        promoTitle.setTypeface(null, android.graphics.Typeface.BOLD);

        FrameLayout.LayoutParams titleParams = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT,
                Gravity.TOP | Gravity.START
        );
        promoTitle.setLayoutParams(titleParams);

        // Button
        Button promoButton = new Button(context);
        promoButton.setText(buttonText);
        promoButton.setTextSize(10);
        promoButton.setTextColor(0xFFFFFFFF);
        GradientDrawable roundedBg = new GradientDrawable();
        roundedBg.setColor(0xFFFF6F00); // Orange color
        roundedBg.setCornerRadius(TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics())); // 20dp rounded corners
        promoButton.setBackground(roundedBg);


        FrameLayout.LayoutParams buttonParams = new FrameLayout.LayoutParams(
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 103, getResources().getDisplayMetrics()),
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 34, getResources().getDisplayMetrics()),
                Gravity.BOTTOM | Gravity.START
        );
        promoButton.setLayoutParams(buttonParams);

        card.addView(promoTitle);
        card.addView(promoButton);

        container.addView(card);
    }

    private void setupQuantityControls(ImageView plusBtn, ImageView minusBtn, TextView counterText) {
        if (plusBtn == null || minusBtn == null || counterText == null) return;

        final int[] counter = {0};

        plusBtn.setOnClickListener(v -> {
            counter[0]++;
            counterText.setText(String.valueOf(counter[0]));
            counterText.setVisibility(View.VISIBLE);
            minusBtn.setVisibility(View.VISIBLE);
        });

        minusBtn.setOnClickListener(v -> {
            if (counter[0] > 0) {
                counter[0]--;
                counterText.setText(String.valueOf(counter[0]));
                if (counter[0] == 0) {
                    counterText.setVisibility(View.GONE);
                    minusBtn.setVisibility(View.GONE);
                }
            }
        });
    }


    private void startCountdown(long timeInMillis, TextView daysText, TextView hoursText, TextView minutesText, TextView secondsText) {
        new CountDownTimer(timeInMillis, 1000) {
            public void onTick(long millisUntilFinished) {
                long totalSeconds = millisUntilFinished / 1000;
                long d = totalSeconds / (24 * 3600);
                long h = (totalSeconds % (24 * 3600)) / 3600;
                long m = (totalSeconds % 3600) / 60;
                long s = totalSeconds % 60;

                daysText.setText(d + " Days");
                hoursText.setText(h + " Hours");
                minutesText.setText(m + " Min");
                secondsText.setText(s + " Sec");
            }

            public void onFinish() {
                daysText.setText("0 Days");
                hoursText.setText("0 Hours");
                minutesText.setText("0 Min");
                secondsText.setText("0 Sec");
            }
        }.start();
    }


    private void setupRecycler(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
    }

    private void fetchProducts() {
        String url = "https://rainbow-three-khaki.vercel.app/api/products";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                this::parseProductData,
                this::handleApiError
        );

        request.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        ));

        RequestQueue queue = Volley.newRequestQueue(requireContext());
        queue.add(request);
    }

    private void parseProductData(JSONArray response) {
        try {
            for (int i = 0; i < response.length(); i++) {
                JSONObject obj = response.getJSONObject(i);
                String id = obj.optString("_id");
                String name = obj.optString("name");
                String price = obj.optString("price");
                String image = obj.optString("image");
                String category = obj.optString("category");

                Product product = new Product(id, name, price, image, category);

                if (category.equalsIgnoreCase("fruits")) {
                    fruitsList.add(product);
                } else if (category.equalsIgnoreCase("vegetable")) {
                    vegetablesList.add(product);
                } else {
                    otherList.add(product);
                }
            }

            List<Product> others1 = new ArrayList<>();
            List<Product> others2 = new ArrayList<>();
            for (int i = 0; i < otherList.size(); i++) {
                if (i % 2 == 0) others1.add(otherList.get(i));
                else others2.add(otherList.get(i));
            }

            fruitsAdapter = new ProductAdapter(getContext(), fruitsList);
            vegetablesAdapter = new ProductAdapter(getContext(), vegetablesList);
            recommendedAdapter1 = new ProductAdapter(getContext(), others1);
            recommendedAdapter2 = new ProductAdapter(getContext(), others2);

            fruitsRecycler.setAdapter(fruitsAdapter);
            vegetablesRecycler.setAdapter(vegetablesAdapter);
            recommendedRecycler1.setAdapter(recommendedAdapter1);
            recommendedRecycler2.setAdapter(recommendedAdapter2);

        } catch (JSONException e) {
            Log.e("PARSE_ERROR", "Error parsing product data: ", e);
        }
    }

    private void handleApiError(VolleyError error) {
        Log.e("API_ERROR", error.toString());
        Toast.makeText(getContext(), "Failed to load products", Toast.LENGTH_SHORT).show();
    }
}