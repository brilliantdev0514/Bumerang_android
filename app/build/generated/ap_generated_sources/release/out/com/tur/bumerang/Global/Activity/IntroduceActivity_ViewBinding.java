// Generated code from Butter Knife. Do not modify!
package com.tur.bumerang.Global.Activity;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.tur.bumerang.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class IntroduceActivity_ViewBinding implements Unbinder {
  private IntroduceActivity target;

  private View view7f0b039b;

  @UiThread
  public IntroduceActivity_ViewBinding(IntroduceActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public IntroduceActivity_ViewBinding(final IntroduceActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.txvSkip, "field 'txvSkip' and method 'gotoHomeActivity'");
    target.txvSkip = Utils.castView(view, R.id.txvSkip, "field 'txvSkip'", TextView.class);
    view7f0b039b = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.gotoHomeActivity();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    IntroduceActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.txvSkip = null;

    view7f0b039b.setOnClickListener(null);
    view7f0b039b = null;
  }
}
