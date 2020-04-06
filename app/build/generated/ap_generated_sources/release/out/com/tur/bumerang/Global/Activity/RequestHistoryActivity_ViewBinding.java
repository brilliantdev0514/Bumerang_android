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

public class RequestHistoryActivity_ViewBinding implements Unbinder {
  private RequestHistoryActivity target;

  private View view7f0b01de;

  private View view7f0b01df;

  private View view7f0b01ff;

  private View view7f0b01ec;

  @UiThread
  public RequestHistoryActivity_ViewBinding(RequestHistoryActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public RequestHistoryActivity_ViewBinding(final RequestHistoryActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.imv_chart, "method 'goMainHome'");
    view7f0b01de = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.goMainHome();
      }
    });
    view = Utils.findRequiredView(source, R.id.imv_chat, "method 'goChat'");
    view7f0b01df = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.goChat();
      }
    });
    view = Utils.findRequiredView(source, R.id.imv_userinfo, "method 'goUserProfile'");
    view7f0b01ff = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.goUserProfile();
      }
    });
    view = Utils.findRequiredView(source, R.id.imv_plus, "method 'goSelectCategory'");
    view7f0b01ec = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.goSelectCategory();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    target = null;


    view7f0b01de.setOnClickListener(null);
    view7f0b01de = null;
    view7f0b01df.setOnClickListener(null);
    view7f0b01df = null;
    view7f0b01ff.setOnClickListener(null);
    view7f0b01ff = null;
    view7f0b01ec.setOnClickListener(null);
    view7f0b01ec = null;
  }
}
