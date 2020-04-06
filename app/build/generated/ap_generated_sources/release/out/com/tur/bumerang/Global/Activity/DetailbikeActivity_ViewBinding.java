// Generated code from Butter Knife. Do not modify!
package com.tur.bumerang.Global.Activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class DetailbikeActivity_ViewBinding implements Unbinder {
  private DetailbikeActivity target;

  private View view7f0b0086;

  @UiThread
  public DetailbikeActivity_ViewBinding(DetailbikeActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public DetailbikeActivity_ViewBinding(final DetailbikeActivity target, View source) {
    this.target = target;

    View view;
    target.detail_bike_title = Utils.findRequiredViewAsType(source, R.id.detail_bike_title, "field 'detail_bike_title'", TextView.class);
    target.detail_bike_price = Utils.findRequiredViewAsType(source, R.id.detail_bike_price, "field 'detail_bike_price'", TextView.class);
    target.detail_bike_des = Utils.findRequiredViewAsType(source, R.id.detail_bike_des, "field 'detail_bike_des'", EditText.class);
    target.detail_owner_avatar = Utils.findRequiredViewAsType(source, R.id.detail_owner_avatar, "field 'detail_owner_avatar'", ImageView.class);
    target.detail_owner_name = Utils.findRequiredViewAsType(source, R.id.detail_owner_name, "field 'detail_owner_name'", TextView.class);
    target.detail_owner_products = Utils.findRequiredViewAsType(source, R.id.detail_owner_products, "field 'detail_owner_products'", Button.class);
    target.txv_address = Utils.findRequiredViewAsType(source, R.id.txv_address, "field 'txv_address'", TextView.class);
    target.mapView = Utils.findRequiredViewAsType(source, R.id.mapView, "field 'mapView'", CustomMapView.class);
    target.deletePro = Utils.findRequiredViewAsType(source, R.id.deletePro, "field 'deletePro'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.btn_rent_bike, "field 'editandmessage' and method 'goRentPage'");
    target.editandmessage = Utils.castView(view, R.id.btn_rent_bike, "field 'editandmessage'", Button.class);
    view7f0b0086 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.goRentPage();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    DetailbikeActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.detail_bike_title = null;
    target.detail_bike_price = null;
    target.detail_bike_des = null;
    target.detail_owner_avatar = null;
    target.detail_owner_name = null;
    target.detail_owner_products = null;
    target.txv_address = null;
    target.mapView = null;
    target.deletePro = null;
    target.editandmessage = null;

    view7f0b0086.setOnClickListener(null);
    view7f0b0086 = null;
  }
}
