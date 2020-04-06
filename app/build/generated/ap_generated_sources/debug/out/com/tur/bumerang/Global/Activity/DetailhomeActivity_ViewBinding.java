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
import butterknife.internal.Utils;
import com.makeramen.roundedimageview.RoundedImageView;
import com.tur.bumerang.Global.CustomeView.CustomMapView;
import com.tur.bumerang.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DetailhomeActivity_ViewBinding implements Unbinder {
  private DetailhomeActivity target;

  @UiThread
  public DetailhomeActivity_ViewBinding(DetailhomeActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public DetailhomeActivity_ViewBinding(DetailhomeActivity target, View source) {
    this.target = target;

    target.mapView = Utils.findRequiredViewAsType(source, R.id.mapView, "field 'mapView'", CustomMapView.class);
    target.detail_flat_title = Utils.findRequiredViewAsType(source, R.id.detail_flat_title, "field 'detail_flat_title'", TextView.class);
    target.detail_flat_roomnumber = Utils.findRequiredViewAsType(source, R.id.detail_flat_roomnumber, "field 'detail_flat_roomnumber'", TextView.class);
    target.detail_flat_heating = Utils.findRequiredViewAsType(source, R.id.detail_flat_heating, "field 'detail_flat_heating'", TextView.class);
    target.detail_owner_products = Utils.findRequiredViewAsType(source, R.id.detail_owner_products, "field 'detail_owner_products'", Button.class);
    target.detail_flat_fubished = Utils.findRequiredViewAsType(source, R.id.detail_flat_fubished, "field 'detail_flat_fubished'", TextView.class);
    target.detail_flat_price = Utils.findRequiredViewAsType(source, R.id.detail_flat_price, "field 'detail_flat_price'", TextView.class);
    target.detail_flat_deposit = Utils.findRequiredViewAsType(source, R.id.detail_flat_deposit, "field 'detail_flat_deposit'", TextView.class);
    target.detail_flat_des = Utils.findRequiredViewAsType(source, R.id.detail_flat_des, "field 'detail_flat_des'", EditText.class);
    target.detail_flat_address = Utils.findRequiredViewAsType(source, R.id.detail_flat_address, "field 'detail_flat_address'", TextView.class);
    target.detail_owner_avatar = Utils.findRequiredViewAsType(source, R.id.detail_owner_avatar, "field 'detail_owner_avatar'", RoundedImageView.class);
    target.detail_owner_name = Utils.findRequiredViewAsType(source, R.id.detail_owner_name, "field 'detail_owner_name'", TextView.class);
    target.deletePro = Utils.findRequiredViewAsType(source, R.id.deletePro, "field 'deletePro'", ImageView.class);
    target.editandmessage = Utils.findRequiredViewAsType(source, R.id.btn_rent_home, "field 'editandmessage'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    DetailhomeActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mapView = null;
    target.detail_flat_title = null;
    target.detail_flat_roomnumber = null;
    target.detail_flat_heating = null;
    target.detail_owner_products = null;
    target.detail_flat_fubished = null;
    target.detail_flat_price = null;
    target.detail_flat_deposit = null;
    target.detail_flat_des = null;
    target.detail_flat_address = null;
    target.detail_owner_avatar = null;
    target.detail_owner_name = null;
    target.deletePro = null;
    target.editandmessage = null;
  }
}
