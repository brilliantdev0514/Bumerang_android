// Generated code from Butter Knife. Do not modify!
package com.tur.bumerang.Global.Activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.makeramen.roundedimageview.RoundedImageView;
import com.tur.bumerang.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ProfileSettingActivity_ViewBinding implements Unbinder {
  private ProfileSettingActivity target;

  private View view7f0b0083;

  private View view7f0b01fe;

  private View view7f0b01aa;

  private View view7f0b0099;

  private View view7f0b009c;

  @UiThread
  public ProfileSettingActivity_ViewBinding(ProfileSettingActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ProfileSettingActivity_ViewBinding(final ProfileSettingActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.btn_membership, "field 'btn_membership' and method 'goMembership'");
    target.btn_membership = Utils.castView(view, R.id.btn_membership, "field 'btn_membership'", Button.class);
    view7f0b0083 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.goMembership();
      }
    });
    view = Utils.findRequiredView(source, R.id.imv_user_avatar_setting, "field 'imv_user_avatar_setting' and method 'setAvatarPhoto'");
    target.imv_user_avatar_setting = Utils.castView(view, R.id.imv_user_avatar_setting, "field 'imv_user_avatar_setting'", RoundedImageView.class);
    view7f0b01fe = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.setAvatarPhoto();
      }
    });
    target.edt_profile_fname = Utils.findRequiredViewAsType(source, R.id.edt_profile_fname, "field 'edt_profile_fname'", EditText.class);
    target.edt_profile_lname = Utils.findRequiredViewAsType(source, R.id.edt_profile_lname, "field 'edt_profile_lname'", EditText.class);
    target.edt_profile_email = Utils.findRequiredViewAsType(source, R.id.edt_profile_email, "field 'edt_profile_email'", EditText.class);
    target.edt_profile_phone = Utils.findRequiredViewAsType(source, R.id.edt_profile_phone, "field 'edt_profile_phone'", EditText.class);
    target.edt_profile_address = Utils.findRequiredViewAsType(source, R.id.edt_profile_address, "field 'edt_profile_address'", EditText.class);
    target.edt_profile_pwd = Utils.findRequiredViewAsType(source, R.id.edt_profile_pwd, "field 'edt_profile_pwd'", EditText.class);
    view = Utils.findRequiredView(source, R.id.ic_avatar_remove, "method 'removeAvatar'");
    view7f0b01aa = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.removeAvatar();
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_signout, "method 'logOut'");
    view7f0b0099 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.logOut();
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_update_profile, "method 'updateProfile'");
    view7f0b009c = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.updateProfile();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    ProfileSettingActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.btn_membership = null;
    target.imv_user_avatar_setting = null;
    target.edt_profile_fname = null;
    target.edt_profile_lname = null;
    target.edt_profile_email = null;
    target.edt_profile_phone = null;
    target.edt_profile_address = null;
    target.edt_profile_pwd = null;

    view7f0b0083.setOnClickListener(null);
    view7f0b0083 = null;
    view7f0b01fe.setOnClickListener(null);
    view7f0b01fe = null;
    view7f0b01aa.setOnClickListener(null);
    view7f0b01aa = null;
    view7f0b0099.setOnClickListener(null);
    view7f0b0099 = null;
    view7f0b009c.setOnClickListener(null);
    view7f0b009c = null;
  }
}
