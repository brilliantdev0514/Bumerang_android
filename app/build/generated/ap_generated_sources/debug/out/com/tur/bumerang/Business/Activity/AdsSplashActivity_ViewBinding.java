// Generated code from Butter Knife. Do not modify!
package com.tur.bumerang.Business.Activity;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.tur.bumerang.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AdsSplashActivity_ViewBinding implements Unbinder {
  private AdsSplashActivity target;

  private View view7f0b007c;

  @UiThread
  public AdsSplashActivity_ViewBinding(AdsSplashActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AdsSplashActivity_ViewBinding(final AdsSplashActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.btn_ads_next, "method 'goMyProduct'");
    view7f0b007c = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.goMyProduct();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    target = null;


    view7f0b007c.setOnClickListener(null);
    view7f0b007c = null;
  }
}
