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

public class DetailcaravanActivity_ViewBinding implements Unbinder {
  private DetailcaravanActivity target;

  private View view7f0b0089;

  @UiThread
  public DetailcaravanActivity_ViewBinding(DetailcaravanActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public DetailcaravanActivity_ViewBinding(final DetailcaravanActivity target, View source) {
    this.target = target;

    View view;
    target.detail_caravan_title = Utils.findRequiredViewAsType(source, R.id.detail_caravan_title, "field 'detail_caravan_title'", TextView.class);
    target.detail_caravan_fuel = Utils.findRequiredViewAsType(source, R.id.detail_caravan_fuel, "field 'detail_caravan_fuel'", TextView.class);
    target.detail_caravan_bed = Utils.findRequiredViewAsType(source, R.id.detail_caravan_bed, "field 'detail_caravan_bed'", TextView.class);
    target.detail_caravan_deposit = Utils.findRequiredViewAsType(source, R.id.detail_caravan_deposit, "field 'detail_caravan_deposit'", TextView.class);
    target.detail_caravan_price = Utils.findRequiredViewAsType(source, R.id.detail_caravan_price, "field 'detail_caravan_price'", TextView.class);
    target.detail_caravan_des = Utils.findRequiredViewAsType(source, R.id.detail_caravan_des, "field 'detail_caravan_des'", TextView.class);
    target.detail_caravan_avatar = Utils.findRequiredViewAsType(source, R.id.detail_owner_avatar, "field 'detail_caravan_avatar'", ImageView.class);
    target.detail_caravan_ownername = Utils.findRequiredViewAsType(source, R.id.detail_owner_name, "field 'detail_caravan_ownername'", TextView.class);
    target.detail_owner_products = Utils.findRequiredViewAsType(source, R.id.detail_owner_products, "field 'detail_owner_products'", Button.class);
    view = Utils.findRequiredView(source, R.id.btn_rent_caravan, "field 'editandmessage' and method 'goRentPage'");
    target.editandmessage = Utils.castView(view, R.id.btn_rent_caravan, "field 'editandmessage'", Button.class);
    view7f0b0089 = view;
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
    DetailcaravanActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.detail_caravan_title = null;
    target.detail_caravan_fuel = null;
    target.detail_caravan_bed = null;
    target.detail_caravan_deposit = null;
    target.detail_caravan_price = null;
    target.detail_caravan_des = null;
    target.detail_caravan_avatar = null;
    target.detail_caravan_ownername = null;
    target.detail_owner_products = null;
    target.editandmessage = null;
    target.mapView = null;
    target.deletePro = null;

    view7f0b0089.setOnClickListener(null);
    view7f0b0089 = null;
  }
}
