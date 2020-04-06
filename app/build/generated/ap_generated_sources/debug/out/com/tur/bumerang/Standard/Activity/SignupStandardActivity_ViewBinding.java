// Generated code from Butter Knife. Do not modify!
package com.tur.bumerang.Standard.Activity;

import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.tur.bumerang.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SignupStandardActivity_ViewBinding implements Unbinder {
  private SignupStandardActivity target;

  private View view7f0b01f7;

  private View view7f0b01f6;

  private View view7f0b03a8;

  private View view7f0b009a;

  private View view7f0b03cb;

  private View view7f0b03b7;

  @UiThread
  public SignupStandardActivity_ViewBinding(SignupStandardActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SignupStandardActivity_ViewBinding(final SignupStandardActivity target, View source) {
    this.target = target;

    View view;
    target.linSignStandard = Utils.findRequiredViewAsType(source, R.id.linSignStandard, "field 'linSignStandard'", LinearLayout.class);
    target.edtFirstName = Utils.findRequiredViewAsType(source, R.id.edtFirstName, "field 'edtFirstName'", EditText.class);
    target.edtLastName = Utils.findRequiredViewAsType(source, R.id.edtLastName, "field 'edtLastName'", EditText.class);
    target.edtEmail = Utils.findRequiredViewAsType(source, R.id.edtEmail, "field 'edtEmail'", EditText.class);
    target.edtPwd = Utils.findRequiredViewAsType(source, R.id.edtPwd, "field 'edtPwd'", EditText.class);
    target.edtConfirmPwd = Utils.findRequiredViewAsType(source, R.id.edtConfirmPwd, "field 'edtConfirmPwd'", EditText.class);
    view = Utils.findRequiredView(source, R.id.imv_signup_google, "method 'onEmail'");
    view7f0b01f7 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onEmail();
      }
    });
    view = Utils.findRequiredView(source, R.id.imv_signup_facebook, "method 'onFacebook'");
    view7f0b01f6 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onFacebook();
      }
    });
    view = Utils.findRequiredView(source, R.id.txv_have_account, "method 'goLogin'");
    view7f0b03a8 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.goLogin();
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_signup2, "method 'signupWithEmail'");
    view7f0b009a = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.signupWithEmail();
      }
    });
    view = Utils.findRequiredView(source, R.id.txv_terms, "method 'terms'");
    view7f0b03cb = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.terms();
      }
    });
    view = Utils.findRequiredView(source, R.id.txv_policy, "method 'policy'");
    view7f0b03b7 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.policy();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    SignupStandardActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.linSignStandard = null;
    target.edtFirstName = null;
    target.edtLastName = null;
    target.edtEmail = null;
    target.edtPwd = null;
    target.edtConfirmPwd = null;

    view7f0b01f7.setOnClickListener(null);
    view7f0b01f7 = null;
    view7f0b01f6.setOnClickListener(null);
    view7f0b01f6 = null;
    view7f0b03a8.setOnClickListener(null);
    view7f0b03a8 = null;
    view7f0b009a.setOnClickListener(null);
    view7f0b009a = null;
    view7f0b03cb.setOnClickListener(null);
    view7f0b03cb = null;
    view7f0b03b7.setOnClickListener(null);
    view7f0b03b7 = null;
  }
}
