package com.arudanovsky.counter.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.arudanovsky.counter.R;
import com.arudanovsky.counter.view.counter.CounterFragment;
import com.arudanovsky.counter.view.settings.SettingsFragment;

/**
 * Главная активити
 * Обрабатывает переключение между фрагментами
 */
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MainActivityInterface {
    private int mCurrentMenuElement;
    private int mSelectedMenuElement = R.id.nav_counter;

    private NavigationView mNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        mNavigation = (NavigationView) findViewById(R.id.nav_view);
        mNavigation.setNavigationItemSelectedListener(this);

        selectMenu(mSelectedMenuElement);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /**
     * Метод для обработки нажатия на элемент меню
     * @param menuItemId id пункта меню
     */
    private void selectMenu(int menuItemId) {
        checkMenuElement(menuItemId);
        showContent(menuItemId);
    }

    /**
     * Метод для отображения выбранного элемента в меню {@link MainActivity#mNavigation}
     * @param menuItemId id пункта меню
     */
    private void checkMenuElement(int menuItemId) {
        switch (menuItemId) {
            case R.id.nav_counter:
            case R.id.nav_settings:
                mNavigation.getMenu().findItem(menuItemId).setChecked(true);
                break;
            default:
                break;
        }
    }

    /**
     * Метод для отображения контента в зависимости от выбранного элемента меню
     * Если выбранный элемент уже отображается, то не происходит пересоздание фрагмента
     * @param menuItemId id пункта меню
     */
    private void showContent(int menuItemId) {
        if (mCurrentMenuElement != menuItemId) {
            Fragment fragment = null;
            switch (menuItemId) {
                case R.id.nav_counter:
                    fragment = CounterFragment.newInstance();
                    break;
                case R.id.nav_settings:
                    fragment = SettingsFragment.newInstance();
                    break;
            }
            if (fragment != null) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_container, fragment);
                transaction.commit();
                mCurrentMenuElement = menuItemId;
            }
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        mSelectedMenuElement = item.getItemId();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        selectMenu(mSelectedMenuElement);
        return true;
    }

    /**
     * Описание в {@link com.arudanovsky.counter.view.base.BaseActivityInterface}
     * @param title нужное название экрана
     */
    @Override
    public void changeTitle(String title) {
        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle(title);
    }
}
