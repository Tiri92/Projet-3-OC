
package com.openclassrooms.entrevoisins.neighbour_list;

import android.app.Instrumentation;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.KeyEvent;
import android.view.View;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.ui.neighbour_list.MyNeighbourRecyclerViewAdapter;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withTagValue;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.core.IsNull.notNullValue;



/**
 * Test class for list of neighbours
 */
@RunWith(AndroidJUnit4.class)
public class NeighboursListTest {

    // This is fixed
    private static int ITEMS_COUNT = 12;

    private ListNeighbourActivity mActivity;
    private List<Neighbour> mNeighbours;

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule =
            new ActivityTestRule(ListNeighbourActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
        mNeighbours = DI.getNeighbourApiService().getNeighbours();
    }

    /**
     * We ensure that our recyclerview is displaying at least one item
     */
    @Test
    public void myNeighboursList_shouldNotBeEmpty() {
        // Matcher = critère de sélection d'une vue
        Matcher<View> recyclerViewMatcher = withId(R.id.list_neighbours);
        Matcher<View> minChildViewMatcher = hasMinimumChildCount(1);

        // Interaction =  c'est pour intéragir avec la vue, ici la RecyclerView
        // Assertion = C'est l'hypothèse à tester sur le fait d'avoir un nombre minimum d'enfant
        ViewInteraction recyclerViewInteraction = onView(recyclerViewMatcher);
        ViewAssertion minChildAssertion = matches(minChildViewMatcher);

        recyclerViewInteraction.check(minChildAssertion);
    }

    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void myNeighboursList_deleteAction_shouldRemoveItem() {
        // Given : We remove the element at position 2
        onView(withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of element is 11
        onView(withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT-1));
    }

    /**
     * We take the list of neighbour, we click on the fifth and we check if the name who is
     * displayed is the same name of the fifth neighbour of the list, and we make the same
     * things with the second TextView who displays the name of the neighbour on the DetailActivity
     */
    @Test
    public void TextView_IsShown_WithSuccess() {
        onView(withId(R.id.list_neighbours)).
                perform(RecyclerViewActions.actionOnItemAtPosition(5, click()));

        onView(withId(R.id.activity_neighbour_name)).
                check(matches(withText(mNeighbours.get(5).getName())));

        onView(withId(R.id.activity_neighbour_name2)).
                check(matches(withText(mNeighbours.get(5).getName())));
    }

    /**
     * We click on the first neighbour of the list and we check with the matcher isDisplayed that
     * DetailActivity is launch with success thanks to the id that we gave him
     */
    @Test
    public void myDetailActivity_IsLaunch_WithSuccess() {
        onView(withId(R.id.list_neighbours)).
                perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
        // isDisplayed est un matcher qui renvoie la liste des vues affichées
        onView(withId(R.id.neighbour_detail_id)).
                check(matches(isDisplayed()));
    }

    /**
     * We create a loop who add 4 neighbours on favorite, we click on back button and we check
     * that there is 4 neighbours in the list of FavoritesNeighbours
     */
    @Test
    public void favoriteList_ShowsOnly_FavoriteNeighbour() {
        //onView(withId(R.id.favoriteTabItem2)).perform(click());

        int favoriteCount = 4;

        for(int i = 0; i < favoriteCount; i++ ) {
            onView(withId(R.id.list_neighbours)).
                    perform(RecyclerViewActions.actionOnItemAtPosition(i, click()));

            onView(withId(R.id.Button_For_Add_Favoris)).
                    perform(click());

            Espresso.pressBack();
        }

        onView(withId(R.id.container)).
                perform(swipeLeft());

        onView(withId(R.id.favorite_list_neighbours)).check(withItemCount(favoriteCount));
    }
}
