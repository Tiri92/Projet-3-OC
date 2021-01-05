// Generated code from Butter Knife. Do not modify!
package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.openclassrooms.entrevoisins.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class NeighbourDetailActivity_ViewBinding implements Unbinder {
  private NeighbourDetailActivity target;

  @UiThread
  public NeighbourDetailActivity_ViewBinding(NeighbourDetailActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public NeighbourDetailActivity_ViewBinding(NeighbourDetailActivity target, View source) {
    this.target = target;

    target.mPhoto = Utils.findRequiredViewAsType(source, R.id.activity_profile_picture, "field 'mPhoto'", ImageView.class);
    target.mName = Utils.findRequiredViewAsType(source, R.id.activity_neighbour_name, "field 'mName'", TextView.class);
    target.mNumberPhone = Utils.findRequiredViewAsType(source, R.id.activity_number_phone, "field 'mNumberPhone'", TextView.class);
    target.mAdress = Utils.findRequiredViewAsType(source, R.id.activity_neighbour_home, "field 'mAdress'", TextView.class);
    target.mAboutMe = Utils.findRequiredViewAsType(source, R.id.activity_about_me, "field 'mAboutMe'", TextView.class);
    target.mName2 = Utils.findRequiredViewAsType(source, R.id.activity_neighbour_name2, "field 'mName2'", TextView.class);
    target.mAboutMeTitle = Utils.findRequiredViewAsType(source, R.id.activity_about_me_title, "field 'mAboutMeTitle'", TextView.class);
    target.mFacebookLink = Utils.findRequiredViewAsType(source, R.id.activity_facebook_link, "field 'mFacebookLink'", TextView.class);
    target.mFavoritesButton = Utils.findRequiredViewAsType(source, R.id.Button_For_Add_Favoris, "field 'mFavoritesButton'", FloatingActionButton.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    NeighbourDetailActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mPhoto = null;
    target.mName = null;
    target.mNumberPhone = null;
    target.mAdress = null;
    target.mAboutMe = null;
    target.mName2 = null;
    target.mAboutMeTitle = null;
    target.mFacebookLink = null;
    target.mFavoritesButton = null;
  }
}
