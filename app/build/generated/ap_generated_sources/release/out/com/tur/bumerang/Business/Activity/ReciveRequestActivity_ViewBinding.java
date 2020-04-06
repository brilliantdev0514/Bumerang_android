// Generated code from Butter Knife. Do not modify!
package com.tur.bumerang.Business.Activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.tur.bumerang.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ReciveRequestActivity_ViewBinding implements Unbinder {
  private ReciveRequestActivity target;

  @UiThread
  public ReciveRequestActivity_ViewBinding(ReciveRequestActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ReciveRequestActivity_ViewBinding(ReciveRequestActivity target, View source) {
    this.target = target;

    target.imv_req_product1 = Utils.findRequiredViewAsType(source, R.id.imv_req_product1, "field 'imv_req_product1'", ImageView.class);
    target.txv_req_title1 = Utils.findRequiredViewAsType(source, R.id.txv_req_title1, "field 'txv_req_title1'", TextView.class);
    target.txv_req_price1 = Utils.findRequiredViewAsType(source, R.id.txv_req_price1, "field 'txv_req_price1'", TextView.class);
    target.txv_req_dateunit1 = Utils.findRequiredViewAsType(source, R.id.txv_req_dateunit1, "field 'txv_req_dateunit1'", TextView.class);
    target.imv_req_avatar1 = Utils.findRequiredViewAsType(source, R.id.imv_req_avatar1, "field 'imv_req_avatar1'", ImageView.class);
    target.txv_req_name1 = Utils.findRequiredViewAsType(source, R.id.txv_req_name1, "field 'txv_req_name1'", TextView.class);
    target.txv_req_address1 = Utils.findRequiredViewAsType(source, R.id.txv_req_address1, "field 'txv_req_address1'", TextView.class);
    target.txv_req_email1 = Utils.findRequiredViewAsType(source, R.id.txv_req_email1, "field 'txv_req_email1'", TextView.class);
    target.txv_req_phonenum1 = Utils.findRequiredViewAsType(source, R.id.txv_req_phonenum1, "field 'txv_req_phonenum1'", TextView.class);
    target.imv_req_google1 = Utils.findRequiredViewAsType(source, R.id.imv_req_google1, "field 'imv_req_google1'", ImageView.class);
    target.imv_req_facebook1 = Utils.findRequiredViewAsType(source, R.id.imv_req_facebook1, "field 'imv_req_facebook1'", ImageView.class);
    target.txv_req_score1 = Utils.findRequiredViewAsType(source, R.id.txv_req_score1, "field 'txv_req_score1'", TextView.class);
    target.txv_req_fromdate1 = Utils.findRequiredViewAsType(source, R.id.txv_req_fromdate1, "field 'txv_req_fromdate1'", TextView.class);
    target.txv_req_todate1 = Utils.findRequiredViewAsType(source, R.id.txv_req_todate1, "field 'txv_req_todate1'", TextView.class);
    target.txv_req_totalprice1 = Utils.findRequiredViewAsType(source, R.id.txv_req_totalprice1, "field 'txv_req_totalprice1'", TextView.class);
    target.txv_req_message1 = Utils.findRequiredViewAsType(source, R.id.txv_req_message1, "field 'txv_req_message1'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ReciveRequestActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.imv_req_product1 = null;
    target.txv_req_title1 = null;
    target.txv_req_price1 = null;
    target.txv_req_dateunit1 = null;
    target.imv_req_avatar1 = null;
    target.txv_req_name1 = null;
    target.txv_req_address1 = null;
    target.txv_req_email1 = null;
    target.txv_req_phonenum1 = null;
    target.imv_req_google1 = null;
    target.imv_req_facebook1 = null;
    target.txv_req_score1 = null;
    target.txv_req_fromdate1 = null;
    target.txv_req_todate1 = null;
    target.txv_req_totalprice1 = null;
    target.txv_req_message1 = null;
  }
}
