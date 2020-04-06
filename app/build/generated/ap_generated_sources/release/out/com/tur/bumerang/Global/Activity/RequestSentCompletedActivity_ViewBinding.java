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

public class RequestSentCompletedActivity_ViewBinding implements Unbinder {
  private RequestSentCompletedActivity target;

  private View view7f0b0090;

  @UiThread
  public RequestSentCompletedActivity_ViewBinding(RequestSentCompletedActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public RequestSentCompletedActivity_ViewBinding(final RequestSentCompletedActivity target,
      View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.btn_request_complete, "method 'goProductPage'");
    view7f0b0090 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.goProductPage();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    target = null;


    view7f0b0090.setOnClickListener(null);
    view7f0b0090 = null;
  }
}
