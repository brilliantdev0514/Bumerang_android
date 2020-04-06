// Generated code from Butter Knife. Do not modify!
package com.tur.bumerang.Business.Activity;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.makeramen.roundedimageview.RoundedImageView;
import com.tur.bumerang.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class UserProfileBusinessActivity_ViewBinding implements Unbinder {
  private UserProfileBusinessActivity target;

  private View view7f0b007e;

  private View view7f0b01de;

  private View view7f0b01df;

  private View view7f0b01da;

  private View view7f0b01ff;

  private View view7f0b01ec;

  private View view7f0b01f4;

  @UiThread
  public UserProfileBusinessActivity_ViewBinding(UserProfileBusinessActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public UserProfileBusinessActivity_ViewBinding(final UserProfileBusinessActivity target,
      View source) {
    this.target = target;

    View view;
    target.imv_profile_avatar = Utils.findRequiredViewAsType(source, R.id.imv_profile_avatar, "field 'imv_profile_avatar'", RoundedImageView.class);
    target.txv_profile_name = Utils.findRequiredViewAsType(source, R.id.txv_profile_name, "field 'txv_profile_name'", TextView.class);
    view = Utils.findRequiredView(source, R.id.btn_back, "method 'goBack'");
    view7f0b007e = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.goBack();
      }
    });
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
    view = Utils.findRequiredView(source, R.id.imv_box, "method 'goBox'");
    view7f0b01da = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.goBox();
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
    view = Utils.findRequiredView(source, R.id.imv_setting_profile, "method 'goProfileSetting'");
    view7f0b01f4 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.goProfileSetting();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    UserProfileBusinessActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.imv_profile_avatar = null;
    target.txv_profile_name = null;

    view7f0b007e.setOnClickListener(null);
    view7f0b007e = null;
    view7f0b01de.setOnClickListener(null);
    view7f0b01de = null;
    view7f0b01df.setOnClickListener(null);
    view7f0b01df = null;
    view7f0b01da.setOnClickListener(null);
    view7f0b01da = null;
    view7f0b01ff.setOnClickListener(null);
    view7f0b01ff = null;
    view7f0b01ec.setOnClickListener(null);
    view7f0b01ec = null;
    view7f0b01f4.setOnClickListener(null);
    view7f0b01f4 = null;
  }
}
