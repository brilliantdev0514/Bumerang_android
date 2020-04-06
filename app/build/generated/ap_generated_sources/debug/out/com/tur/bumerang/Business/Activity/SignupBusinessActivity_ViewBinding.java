// Generated code from Butter Knife. Do not modify!
package com.tur.bumerang.Business.Activity;

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

public class SignupBusinessActivity_ViewBinding implements Unbinder {
  private SignupBusinessActivity target;

  private View view7f0b01f7;

  private View view7f0b01f6;

  private View view7f0b03a8;

  private View view7f0b0079;

  private View view7f0b03cb;

  private View view7f0b03b7;

  @UiThread
  public SignupBusinessActivity_ViewBinding(SignupBusinessActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SignupBusinessActivity_ViewBinding(final SignupBusinessActivity target, View source) {
    this.target = target;

    View view;
    target.linSignBusiness = Utils.findRequiredViewAsType(source, R.id.linSignBusiness, "field 'linSignBusiness'", LinearLayout.class);
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
    view = Utils.findRequiredView(source, R.id.btnSignup1, "method 'signupWithEmail'");
    view7f0b0079 = view;
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
    SignupBusinessActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.linSignBusiness = null;
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
    view7f0b0079.setOnClickListener(null);
    view7f0b0079 = null;
    view7f0b03cb.setOnClickListener(null);
    view7f0b03cb = null;
    view7f0b03b7.setOnClickListener(null);
    view7f0b03b7 = null;
  }
}
