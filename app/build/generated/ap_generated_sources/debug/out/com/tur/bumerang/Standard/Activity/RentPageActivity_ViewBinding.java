// Generated code from Butter Knife. Do not modify!
package com.tur.bumerang.Standard.Activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.tur.bumerang.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class RentPageActivity_ViewBinding implements Unbinder {
  private RentPageActivity target;

  @UiThread
  public RentPageActivity_ViewBinding(RentPageActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public RentPageActivity_ViewBinding(RentPageActivity target, View source) {
    this.target = target;

    target.btn_rent = Utils.findRequiredViewAsType(source, R.id.btn_rent, "field 'btn_rent'", Button.class);
    target.edt_rent_msg = Utils.findRequiredViewAsType(source, R.id.edt_rent_msg, "field 'edt_rent_msg'", EditText.class);
    target.total_date = Utils.findRequiredViewAsType(source, R.id.total_date, "field 'total_date'", TextView.class);
    target.from_date = Utils.findRequiredViewAsType(source, R.id.from_date, "field 'from_date'", TextView.class);
    target.to_date = Utils.findRequiredViewAsType(source, R.id.to_date, "field 'to_date'", TextView.class);
    target.total_price = Utils.findRequiredViewAsType(source, R.id.total_price, "field 'total_price'", TextView.class);
    target.rental_price = Utils.findRequiredViewAsType(source, R.id.rental_price, "field 'rental_price'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    RentPageActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.btn_rent = null;
    target.edt_rent_msg = null;
    target.total_date = null;
    target.from_date = null;
    target.to_date = null;
    target.total_price = null;
    target.rental_price = null;
  }
}
