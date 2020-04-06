// Generated code from Butter Knife. Do not modify!
package com.tur.bumerang.Global.Activity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.tur.bumerang.Global.CustomeView.CustomMapView;
import com.tur.bumerang.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DetaildressActivity_ViewBinding implements Unbinder {
  private DetaildressActivity target;

  private View view7f0b008a;

  @UiThread
  public DetaildressActivity_ViewBinding(DetaildressActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public DetaildressActivity_ViewBinding(final DetaildressActivity target, View source) {
    this.target = target;

    View view;
    target.detail_dress_title = Utils.findRequiredViewAsType(source, R.id.detail_dress_title, "field 'detail_dress_title'", TextView.class);
    target.detail_dress_size = Utils.findRequiredViewAsType(source, R.id.detail_dress_size, "field 'detail_dress_size'", TextView.class);
    target.detail_dress_color = Utils.findRequiredViewAsType(source, R.id.detail_dress_color, "field 'detail_dress_color'", TextView.class);
    target.detail_dress_price = Utils.findRequiredViewAsType(source, R.id.detail_dress_price, "field 'detail_dress_price'", TextView.class);
    target.detail_dress_deposit = Utils.findRequiredViewAsType(source, R.id.detail_dress_deposit, "field 'detail_dress_deposit'", TextView.class);
    target.detail_dress_des = Utils.findRequiredViewAsType(source, R.id.detail_dress_des, "field 'detail_dress_des'", TextView.class);
    target.detail_owner_avatar = Utils.findRequiredViewAsType(source, R.id.detail_owner_avatar, "field 'detail_owner_avatar'", ImageView.class);
    target.detail_owner_name = Utils.findRequiredViewAsType(source, R.id.detail_owner_name, "field 'detail_owner_name'", TextView.class);
    target.detail_owner_products = Utils.findRequiredViewAsType(source, R.id.detail_owner_products, "field 'detail_owner_products'", Button.class);
    target.detail_dress_address = Utils.findRequiredViewAsType(source, R.id.detail_dress_address, "field 'detail_dress_address'", TextView.class);
    view = Utils.findRequiredView(source, R.id.btn_rent_dress, "field 'editandmessage' and method 'goRentPage'");
    target.editandmessage = Utils.castView(view, R.id.btn_rent_dress, "field 'editandmessage'", Button.class);
    view7f0b008a = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.goRentPage();
      }
    });
    target.mapView = Utils.findRequiredViewAsType(source, R.id.mapView, "field 'mapView'", CustomMapView.class);
    target.deletePro = Utils.findRequiredViewAsType(source, R.id.deletePro, "field 'deletePro'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    DetaildressActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.detail_dress_title = null;
    target.detail_dress_size = null;
    target.detail_dress_color = null;
    target.detail_dress_price = null;
    target.detail_dress_deposit = null;
    target.detail_dress_des = null;
    target.detail_owner_avatar = null;
    target.detail_owner_name = null;
    target.detail_owner_products = null;
    target.detail_dress_address = null;
    target.editandmessage = null;
    target.mapView = null;
    target.deletePro = null;

    view7f0b008a.setOnClickListener(null);
    view7f0b008a = null;
  }
}
