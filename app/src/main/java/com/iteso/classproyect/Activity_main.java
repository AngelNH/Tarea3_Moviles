package com.iteso.classproyect;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.iteso.classproyect.beans.ItemProduct;

import java.util.ArrayList;

public class Activity_main extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        tabLayout.setupWithViewPager(mViewPager);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_logout) {
            SharedPreferences sharedPreferences = getSharedPreferences("com.iteso.USER_PREFERENCES",MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.apply();

            Intent intent = new Intent(Activity_main.this,ActivityLogin.class);
            //for cleaning all stack of the activities
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);//clean all stack, but remain the new task.
            startActivity(intent);
            finish();

            return true;
        }
        else if(id == R.id.menu_privacy){
            Intent intent = new Intent(Activity_main.this,ActivityPrivacyPolicy.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }


        ArrayList<ItemProduct> products;
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_activity_main, container, false);

            RecyclerView recyclerView = rootView.findViewById(R.id.fragment_recycler_view);

            recyclerView.setHasFixedSize(true);
            // Use a linear layout manager
            LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(mLayoutManager);

            products  = new ArrayList<>();
            //products.add(new ItemProduct("Mac", "BestBuy","Guadalajara", "3354657860", 1));
            //products.add(new ItemProduct("Alienware", "DELL", "Guadalajara", "3321234534", 2));
            //products.add(new ItemProduct("Lanix", "Saint Jhonny", "Zapopan", "3244345676", 3));

            AdapterProduct adapterProduct = new AdapterProduct(getActivity(),products,this.getContext());
            recyclerView.setAdapter(adapterProduct);

            return rootView;
        }
    }
    Fragment_Home fragmentHome;
    Fragment_Technology fragmentTechnology;
    Fragment_Electronics fragmentElectronics;
    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position){
                case 0:
                    if (fragmentTechnology == null){
                        fragmentTechnology = new Fragment_Technology();
                    }
                    return fragmentTechnology;
                case 1:
                    if (fragmentHome == null){
                        fragmentHome = new Fragment_Home();
                    }
                    return fragmentHome;
                case 2:
                    if (fragmentElectronics == null){
                    fragmentElectronics = new Fragment_Electronics();
                }
                    return fragmentElectronics;
                default: return new Fragment_Technology();
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0 : return "Technology";
                case 1 : return "Home";
                case 2 : return "Electronics";
            }
            return null;
        }



        public void onClickPhoneCard (View v){
            Intent intent = new Intent();
            intent.setAction("android.intent.action.DIAL");
            intent.putExtra("PHONE","3318275480");
            sendBroadcast(intent);

        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Activity_main.super.onActivityResult(requestCode, resultCode, data);
        Log.e("RESULT","Entered the activity result with request code: "+requestCode);
        switch (requestCode){
            case 1:
            case 2:
            case 3:
                if (resultCode == Activity.RESULT_OK) {
                    Log.e("RESULT", "Entered the case for the fragment technology");
                    //ItemProduct item = data.getParcelableExtra("ITEM");
                    //if (item != null) {
                    fragmentTechnology.onActivityResult(requestCode, resultCode, data);
                    //}
                }
                break;
            case 4://Electronics fragment
            case 5:
                Log.e("RESULT","Entered the case for the fragment electronics");
                if (resultCode == Activity.RESULT_OK) {
                    ItemProduct item = data.getParcelableExtra("ITEM");
                    if (item != null) {
                        fragmentElectronics.onActivityResult(requestCode, resultCode, data);
                    }
                }
                break;

            case 6://Home fragment
            case 7:
                Log.e("RESULT","Entered the case for the fragment home");
                if (resultCode == Activity.RESULT_OK) {
                    ItemProduct item = data.getParcelableExtra("ITEM");
                    if (item != null) {
                        fragmentHome.onActivityResult(requestCode, resultCode, data);
                    }
                }
                break;
            default:
                Log.e("RESULT","Entered the default case");
                break;
        }
    }
}







