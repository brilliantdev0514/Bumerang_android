// Generated code from Butter Knife. Do not modify!
package com.tur.bumerang.Global.Activity;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.tur.bumerang.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class UserProductsActivity_ViewBinding implements Unbinder {
  private UserProductsActivity target;

  private View view7f0b01d8;

  @UiThread
  public UserProductsActivity_ViewBinding(UserProductsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public UserProductsActivity_ViewBinding(final UserProductsActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.imv_back, "method 'goBack'");
    view7f0b01d8 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.goBack();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    target = null;


    view7f0b01d8.setOnClickListener(null);
    view7f0b01d8 = null;
  }
}
