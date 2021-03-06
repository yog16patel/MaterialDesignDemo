package com.citymart.yg.materialdesigndemo;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawerFragment extends Fragment {

    public static final String PREF_FILE_NAME="testpref";
    public static final String KEY_USER_LEARNED_DRAWER="user_learned_drawer";
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private View mContainerView;
    private boolean mUserLearnedDrawer;
    private boolean mFromSavedInstanceState;
    private RecyclerView mRecyclerView;

    private VivzAdapter mVivzAdapter;

    public NavigationDrawerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        mUserLearnedDrawer = Boolean.valueOf(readFromPreferences(getActivity(),KEY_USER_LEARNED_DRAWER,"false"));
        if(savedInstanceState != null)
        {
            mFromSavedInstanceState = true;
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.e("Test :","1");
        View layout  = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        mRecyclerView = (RecyclerView)layout.findViewById(R.id.recycler_view);
        mVivzAdapter = new VivzAdapter(getActivity(),getData());
        mRecyclerView.setAdapter(mVivzAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        Log.e("Test :", "2");

        return layout;
    }
    public static List<Information> getData()
    {
            List<Information> data = new ArrayList<Information>();
            int []icons = {R.drawable.ic_card_18dp,R.drawable.ic_card_18dp,R.drawable.ic_card_18dp,R.drawable.ic_card_18dp};
            String[] titles = {"Number 1","Number 2","Number 3","Number 4"};

            for(int i=0 ; i < 100 ;i++)
            {
                Information information = new Information();
                information.setIconId(icons[i%icons.length]);
                information.setTittle(titles[i%titles.length]);
                data.add(information);
            }
        return data;
    }

    public void setUp(int fragmentId,DrawerLayout drawerLayout, final Toolbar toolbar)
    {
        mContainerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(),drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close)
        {
            @Override
            public void onDrawerOpened(View drawerView)
            {
                super.onDrawerOpened(drawerView);
                if(!mUserLearnedDrawer)
                {
                    mUserLearnedDrawer = true;
                    saveToPreferences(getActivity(),KEY_USER_LEARNED_DRAWER,"true");
                }
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView)
            {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }


        };

        if(!mUserLearnedDrawer && !mFromSavedInstanceState)
        {
            mDrawerLayout.openDrawer(mContainerView);
        }
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });
    }
    public static void saveToPreferences(Context context, String preferenceName,String preferenceValue)
    {
        SharedPreferences preferences = context.getSharedPreferences(PREF_FILE_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(preferenceName,preferenceValue);
        editor.apply();

    }
    public static String readFromPreferences(Context context, String preferenceName,String defaultValue) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        return preferences.getString(preferenceName,defaultValue);
    }
}
