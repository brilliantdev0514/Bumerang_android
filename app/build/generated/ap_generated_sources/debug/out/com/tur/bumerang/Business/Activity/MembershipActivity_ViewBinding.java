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

public class MembershipActivity_ViewBinding implements Unbinder {
  private MembershipActivity target;

  private View view7f0b039e;

  @UiThread
  public MembershipActivity_ViewBinding(MembershipActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MembershipActivity_ViewBinding(final MembershipActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.txv_back, "method 'goBack'");
    view7f0b039e = view;
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


    view7f0b039e.setOnClickListener(null);
    view7f0b039e = null;
  }
}
