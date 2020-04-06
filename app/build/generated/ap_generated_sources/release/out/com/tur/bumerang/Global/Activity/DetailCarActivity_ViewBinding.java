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

public class DetailCarActivity_ViewBinding implements Unbinder {
  private DetailCarActivity target;

  private View view7f0b0088;

  @UiThread
  public DetailCarActivity_ViewBinding(DetailCarActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public DetailCarActivity_ViewBinding(final DetailCarActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.btn_rent_car, "field 'editandmessage' and method 'goRentPage'");
    target.editandmessage = Utils.castView(view, R.id.btn_rent_car, "field 'editandmessage'", Button.class);
    view7f0b0088 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.goRentPage();
      }
    });
    target.mapView = Utils.findRequiredViewAsType(source, R.id.mapView, "field 'mapView'", CustomMapView.class);
    target.detail_car_title = Utils.findRequiredViewAsType(source, R.id.detail_car_title, "field 'detail_car_title'", TextView.class);
    target.detail_car_fuel = Utils.findRequiredViewAsType(source, R.id.detail_car_fuel, "field 'detail_car_fuel'", TextView.class);
    target.detail_car_gear = Utils.findRequiredViewAsType(source, R.id.detail_car_gear, "field 'detail_car_gear'", TextView.class);
    target.detail_car_doornumber = Utils.findRequiredViewAsType(source, R.id.detail_car_doornumber, "field 'detail_car_doornumber'", TextView.class);
    target.detail_car_type = Utils.findRequiredViewAsType(source, R.id.detail_car_type, "field 'detail_car_type'", TextView.class);
    target.detail_car_price = Utils.findRequiredViewAsType(source, R.id.detail_car_price, "field 'detail_car_price'", TextView.class);
    target.detail_car_deposit = Utils.findRequiredViewAsType(source, R.id.detail_car_deposit, "field 'detail_car_deposit'", TextView.class);
    target.detail_car_des = Utils.findRequiredViewAsType(source, R.id.detail_car_des, "field 'detail_car_des'", TextView.class);
    target.detail_car_address = Utils.findRequiredViewAsType(source, R.id.detail_car_address, "field 'detail_car_address'", TextView.class);
    target.detail_car_ownerimv = Utils.findRequiredViewAsType(source, R.id.detail_owner_avatar, "field 'detail_car_ownerimv'", ImageView.class);
    target.detail_car_ownername = Utils.findRequiredViewAsType(source, R.id.detail_owner_name, "field 'detail_car_ownername'", TextView.class);
    target.detail_owner_products = Utils.findRequiredViewAsType(source, R.id.detail_owner_products, "field 'detail_owner_products'", Button.class);
    target.deletePro = Utils.findRequiredViewAsType(source, R.id.deletePro, "field 'deletePro'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    DetailCarActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.editandmessage = null;
    target.mapView = null;
    target.detail_car_title = null;
    target.detail_car_fuel = null;
    target.detail_car_gear = null;
    target.detail_car_doornumber = null;
    target.detail_car_type = null;
    target.detail_car_price = null;
    target.detail_car_deposit = null;
    target.detail_car_des = null;
    target.detail_car_address = null;
    target.detail_car_ownerimv = null;
    target.detail_car_ownername = null;
    target.detail_owner_products = null;
    target.deletePro = null;

    view7f0b0088.setOnClickListener(null);
    view7f0b0088 = null;
  }
}
