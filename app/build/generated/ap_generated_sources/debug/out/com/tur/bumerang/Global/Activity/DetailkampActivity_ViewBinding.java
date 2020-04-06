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

public class DetailkampActivity_ViewBinding implements Unbinder {
  private DetailkampActivity target;

  @UiThread
  public DetailkampActivity_ViewBinding(DetailkampActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public DetailkampActivity_ViewBinding(DetailkampActivity target, View source) {
    this.target = target;

    target.editandmessage = Utils.findRequiredViewAsType(source, R.id.btn_rent_kamp, "field 'editandmessage'", Button.class);
    target.detail_title = Utils.findRequiredViewAsType(source, R.id.detail_title, "field 'detail_title'", TextView.class);
    target.detail_price = Utils.findRequiredViewAsType(source, R.id.detail_price_camp, "field 'detail_price'", TextView.class);
    target.detail_des = Utils.findRequiredViewAsType(source, R.id.detail_des, "field 'detail_des'", TextView.class);
    target.detail_owner_name = Utils.findRequiredViewAsType(source, R.id.detail_owner_name, "field 'detail_owner_name'", TextView.class);
    target.detail_owner_products = Utils.findRequiredViewAsType(source, R.id.detail_owner_products, "field 'detail_owner_products'", Button.class);
    target.detail_owner_avatar = Utils.findRequiredViewAsType(source, R.id.detail_owner_avatar, "field 'detail_owner_avatar'", ImageView.class);
    target.txv_address_kamp = Utils.findRequiredViewAsType(source, R.id.txv_address_kamp, "field 'txv_address_kamp'", TextView.class);
    target.mapView = Utils.findRequiredViewAsType(source, R.id.mapView, "field 'mapView'", CustomMapView.class);
    target.deletePro = Utils.findRequiredViewAsType(source, R.id.deletePro, "field 'deletePro'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    DetailkampActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.editandmessage = null;
    target.detail_title = null;
    target.detail_price = null;
    target.detail_des = null;
    target.detail_owner_name = null;
    target.detail_owner_products = null;
    target.detail_owner_avatar = null;
    target.txv_address_kamp = null;
    target.mapView = null;
    target.deletePro = null;
  }
}
