// Generated code from Butter Knife. Do not modify!
package com.tur.bumerang.Global.Activity;

import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.tur.bumerang.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LoginActivity_ViewBinding implements Unbinder {
  private LoginActivity target;

  private View view7f0b0082;

  private View view7f0b0015;

  private View view7f0b0014;

  private View view7f0b03a3;

  @UiThread
  public LoginActivity_ViewBinding(LoginActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LoginActivity_ViewBinding(final LoginActivity target, View source) {
    this.target = target;

    View view;
    target.linSignStandard = Utils.findRequiredViewAsType(source, R.id.linSignStandard, "field 'linSignStandard'", RelativeLayout.class);
    target.edt_login_email = Utils.findRequiredViewAsType(source, R.id.edt_login_email, "field 'edt_login_email'", EditText.class);
    target.edit_login_pwd = Utils.findRequiredViewAsType(source, R.id.edt_login_pwd, "field 'edit_login_pwd'", EditText.class);
    target.checkbox_remember = Utils.findRequiredViewAsType(source, R.id.checkbox_remember, "field 'checkbox_remember'", CheckBox.class);
    view = Utils.findRequiredView(source, R.id.btn_login, "method 'goHome'");
    view7f0b0082 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.goHome();
      }
    });
    view = Utils.findRequiredView(source, R.id.RR_login_google, "method 'onGmail'");
    view7f0b0015 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onGmail();
      }
    });
    view = Utils.findRequiredView(source, R.id.RR_login_facebook, "method 'onFacebook'");
    view7f0b0014 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onFacebook();
      }
    });
    view = Utils.findRequiredView(source, R.id.txv_forgot, "method 'forgot'");
    view7f0b03a3 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.forgot();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    LoginActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.linSignStandard = null;
    target.edt_login_email = null;
    target.edit_login_pwd = null;
    target.checkbox_remember = null;

    view7f0b0082.setOnClickListener(null);
    view7f0b0082 = null;
    view7f0b0015.setOnClickListener(null);
    view7f0b0015 = null;
    view7f0b0014.setOnClickListener(null);
    view7f0b0014 = null;
    view7f0b03a3.setOnClickListener(null);
    view7f0b03a3 = null;
  }
}
