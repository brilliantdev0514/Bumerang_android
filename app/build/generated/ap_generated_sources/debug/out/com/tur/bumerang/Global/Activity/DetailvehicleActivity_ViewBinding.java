// Generated code from Butter Knife. Do not modify!
package com.tur.bumerang.Global.Activity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.tur.bumerang.Global.CustomeView.CustomMapView;
import com.tur.bumerang.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DetailvehicleActivity_ViewBinding implements Unbinder {
  private DetailvehicleActivity target;

  @UiThread
  public DetailvehicleActivity_ViewBinding(DetailvehicleActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public DetailvehicleActivity_ViewBinding(DetailvehicleActivity target, View source) {
    this.target = target;

    target.editandmessage = Utils.findRequiredViewAsType(source, R.id.btn_rent_vehicle, "field 'editandmessage'", Button.class);
    target.detail_vehicle_person = Utils.findRequiredViewAsType(source, R.id.detail_vehicle_person, "field 'detail_vehicle_person'", TextView.class);
    target.detail_vehicle_title = Utils.findRequiredViewAsType(source, R.id.detail_vehicle_title, "field 'detail_vehicle_title'", TextView.class);
    target.detail_vehicle_captan = Utils.findRequiredViewAsType(source, R.id.detail_vehicle_captan, "field 'detail_vehicle_captan'", TextView.class);
    target.detail_vehicle_price = Utils.findRequiredViewAsType(source, R.id.detail_vehicle_price, "field 'detail_vehicle_price'", TextView.class);
    target.detail_des = Utils.findRequiredViewAsType(source, R.id.detail_des, "field 'detail_des'", TextView.class);
    target.txv_address = Utils.findRequiredViewAsType(source, R.id.txv_address, "field 'txv_address'", TextView.class);
    target.detail_owner_avatar = Utils.findRequiredViewAsType(source, R.id.detail_owner_avatar, "field 'detail_owner_avatar'", ImageView.class);
    target.detail_owner_name = Utils.findRequiredViewAsType(source, R.id.detail_owner_name, "field 'detail_owner_name'", TextView.class);
    target.detail_owner_products = Utils.findRequiredViewAsType(source, R.id.detail_owner_products, "field 'detail_owner_products'", Button.class);
    target.mapView = Utils.findRequiredViewAsType(source, R.id.mapView, "field 'mapView'", CustomMapView.class);
    target.deletePro = Utils.findRequiredViewAsType(source, R.id.deletePro, "field 'deletePro'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    DetailvehicleActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.editandmessage = null;
    target.detail_vehicle_person = null;
    target.detail_vehicle_title = null;
    target.detail_vehicle_captan = null;
    target.detail_vehicle_price = null;
    target.detail_des = null;
    target.txv_address = null;
    target.detail_owner_avatar = null;
    target.detail_owner_name = null;
    target.detail_owner_products = null;
    target.mapView = null;
    target.deletePro = null;
  }
}
