package com.example.rainbow;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;



import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.squareup.picasso.Picasso;

public class NavigationBar extends AppCompatActivity {

    ImageView hamburgerIcon, navHome, navFavorites, navCart, navNotification, navProfile, profileIcon;
    TextView pageTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_bar);

        // Top bar elements
        hamburgerIcon = findViewById(R.id.hamburgerIcon);
        pageTitle = findViewById(R.id.pageTitle);
        profileIcon = findViewById(R.id.profileIcon);

        // Bottom nav icons
        navHome = findViewById(R.id.navHome);
        navFavorites = findViewById(R.id.navFavorites);
        navCart = findViewById(R.id.navCart);
        navNotification = findViewById(R.id.navNotification);
        navProfile = findViewById(R.id.navProfile);

        // Set click listener for hamburgerIcon to open popup menu
        hamburgerIcon.setOnClickListener(v -> showMenu(v));

        // Set initial fragment to Home
        loadFragment(new HomeFragment());
        pageTitle.setText("Home");

        // Set bottom nav click listeners
        setupBottomNav();
        Picasso.get()
                .load("https://res.cloudinary.com/dgtk4rthy/image/upload/v1752762237/menu_wl9kcy.png")
                .into(hamburgerIcon);

        Picasso.get()
                .load("https://res.cloudinary.com/dgtk4rthy/image/upload/v1752762322/al_fatah_dtphq1.png")
                .into(profileIcon);

        Picasso.get()
                .load("https://res.cloudinary.com/dgtk4rthy/image/upload/v1752762280/ic_home_gf5dzn.png")
                .into(navHome);

        Picasso.get()
                .load("https://res.cloudinary.com/dgtk4rthy/image/upload/v1752762288/ic_heart_xc3fl4.png")
                .into(navFavorites);

        Picasso.get()
                .load("https://res.cloudinary.com/dgtk4rthy/image/upload/v1752762293/ic_cart_h9qrm5.png")
                .into(navCart);

        Picasso.get()
                .load("https://res.cloudinary.com/dgtk4rthy/image/upload/v1752762294/ic_bell_xdkvnc.png")
                .into(navNotification);

        Picasso.get()
                .load("https://res.cloudinary.com/dgtk4rthy/image/upload/v1752762271/ic_user_djnyp5.png")
                .into(navProfile);


        new Handler().postDelayed(() -> {
            LinearLayout cookieBanner = findViewById(R.id.cookieBanner);
            Button acceptBtn = findViewById(R.id.acceptCookiesBtn);
            Button declineBtn = findViewById(R.id.declineCookiesBtn);

            cookieBanner.setVisibility(View.INVISIBLE); // Start invisible

            // Run after layout pass to get accurate height
            cookieBanner.post(() -> {
                int bannerHeight = cookieBanner.getHeight();
                cookieBanner.setTranslationY(bannerHeight);
                cookieBanner.setVisibility(View.VISIBLE);

                cookieBanner.animate()
                        .translationY(0)
                        .setDuration(500)
                        .start();
            });

            acceptBtn.setOnClickListener(v -> {
                int bannerHeight = cookieBanner.getHeight();
                cookieBanner.animate()
                        .translationY(bannerHeight)
                        .setDuration(500)
                        .withEndAction(() -> cookieBanner.setVisibility(View.GONE))
                        .start();
            });

            declineBtn.setOnClickListener(v -> {
                Toast.makeText(NavigationBar.this, "You must accept cookies to continue.", Toast.LENGTH_SHORT).show();
            });

        }, 2000);
// 2 second delay


    }

    // Function to show popup menu
    private void showMenu(View v) {
        PopupMenu popupMenu = new PopupMenu(this, v);
        popupMenu.getMenuInflater().inflate(R.menu.top_menu, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.menu_home) {
                loadFragment(new HomeFragment());
                pageTitle.setText("Home");
                return true;
            } else if (itemId == R.id.menu_favorites) {
                loadFragment(new DescriptionFragment());
                pageTitle.setText("Favorites");
                return true;
            } else if (itemId == R.id.menu_cart) {
                loadFragment(new DescriptionFragment());
                pageTitle.setText("Cart");
                return true;
            } else if (itemId == R.id.menu_notifications) {
                loadFragment(new DescriptionFragment());
                pageTitle.setText("Notifications");
                return true;
            } else if (itemId == R.id.menu_profile) {
                loadFragment(new DescriptionFragment());
                pageTitle.setText("Profile");
                return true;
            } else {
                return false;
            }
        });

        popupMenu.show();
    }

    // Function to load fragments into contentFrame
    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.contentFrame, fragment);
        transaction.commit();
    }

    // Bottom nav icon click handlers
    private void setupBottomNav() {
        navHome.setOnClickListener(v -> {
            loadFragment(new HomeFragment());
            pageTitle.setText("Home");
        });

        navFavorites.setOnClickListener(v -> {
            loadFragment(new DescriptionFragment());
            pageTitle.setText("Favorites");
        });

        navCart.setOnClickListener(v -> {
            loadFragment(new DescriptionFragment());
            pageTitle.setText("Cart");
        });

        navNotification.setOnClickListener(v -> {
            loadFragment(new DescriptionFragment());
            pageTitle.setText("Notifications");
        });

        navProfile.setOnClickListener(v -> {
            loadFragment(new DescriptionFragment());
            pageTitle.setText("Profile");
        });
    }
}
