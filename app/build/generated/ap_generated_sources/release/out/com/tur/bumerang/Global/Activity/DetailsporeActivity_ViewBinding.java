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
import com.tur.bumerang.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DetailsporeActivity_ViewBinding implements Unbinder {
  private DetailsporeActivity target;

  @UiThread
  public DetailsporeActivity_ViewBinding(DetailsporeActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public DetailsporeActivity_ViewBinding(DetailsporeActivity target, View source) {
    this.target = target;

    target.editandmessage = Utils.findRequiredViewAsType(source, R.id.btn_rent_spore, "field 'editandmessage'", Button.class);
    target.detail_title = Utils.findRequiredViewAsType(source, R.id.detail_title, "field 'detail_title'", TextView.class);
    target.detail_other_address = Utils.findRequiredViewAsType(source, R.id.detail_other_address, "field 'detail_other_address'", TextView.class);
    target.detail_price = Utils.findRequiredViewAsType(source, R.id.detail_price_other, "field 'detail_price'", TextView.class);
    target.detail_des = Utils.findRequiredViewAsType(source, R.id.detail_des, "field 'detail_des'", TextView.class);
    target.detail_owner_name = Utils.findRequiredViewAsType(source, R.id.detail_owner_name, "field 'detail_owner_name'", TextView.class);
    target.detail_owner_avatar = Utils.findRequiredViewAsType(source, R.id.detail_owner_avatar, "field 'detail_owner_avatar'", ImageView.class);
    target.detail_owner_products = Utils.findRequiredViewAsType(source, R.id.detail_owner_products, "field 'detail_owner_products'", Button.class);
    target.deletePro = Utils.findRequiredViewAsType(source, R.id.deletePro, "field 'deletePro'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    DetailsporeActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.editandmessage = null;
    target.detail_title = null;
    target.detail_other_address = null;
    target.detail_price = null;
    target.detail_des = null;
    target.detail_owner_name = null;
    target.detail_owner_avatar = null;
    target.detail_owner_products = null;
    target.deletePro = null;
  }
}
