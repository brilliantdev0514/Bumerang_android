// Generated code from Butter Knife. Do not modify!
package com.tur.bumerang.Global.Activity;

import android.view.View;
import android.widget.Button;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.tur.bumerang.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class RegisterActivity_ViewBinding implements Unbinder {
  private RegisterActivity target;

  private View view7f0b009b;

  private View view7f0b03ab;

  @UiThread
  public RegisterActivity_ViewBinding(RegisterActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public RegisterActivity_ViewBinding(final RegisterActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.btn_std_signup, "field 'btn_std_signup' and method 'goSignup'");
    target.btn_std_signup = Utils.castView(view, R.id.btn_std_signup, "field 'btn_std_signup'", Button.class);
    view7f0b009b = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.goSignup();
      }
    });
    view = Utils.findRequiredView(source, R.id.txv_login, "field 'txv_login' and method 'goLogin'");
    target.txv_login = Utils.castView(view, R.id.txv_login, "field 'txv_login'", Button.class);
    view7f0b03ab = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.goLogin();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    RegisterActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.btn_std_signup = null;
    target.txv_login = null;

    view7f0b009b.setOnClickListener(null);
    view7f0b009b = null;
    view7f0b03ab.setOnClickListener(null);
    view7f0b03ab = null;
  }
}
