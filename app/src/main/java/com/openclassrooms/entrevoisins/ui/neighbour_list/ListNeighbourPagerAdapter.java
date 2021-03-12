package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class ListNeighbourPagerAdapter extends FragmentPagerAdapter {
    public enum neighbourPage {
        STANDARD_NEIGHBOUR,
        FAVORITE_NEIGHBOUR
    }

    public ListNeighbourPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * getItem is called to instantiate the fragment for the given page.
     * @param position
     * @return
     */

    /**
     * The chosen fragment to be displayed is the one on which "position" points to, we used an "enum"
     * to count number of page with the method ".lenght"
     */
    @Override
    public Fragment getItem(int position) {
        if( position == neighbourPage.FAVORITE_NEIGHBOUR.ordinal()) {
            return new FavoriteNeighbourFragment();
        }
        return NeighbourFragment.newInstance();
    }

    /**
     * get the number of pages
     * @return
     */
    @Override
    public int getCount() {
        return neighbourPage.values().length;
    }
}