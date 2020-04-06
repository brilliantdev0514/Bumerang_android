// Generated code from Butter Knife. Do not modify!
package com.tur.bumerang.Global.Activity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.ogaclejapan.arclayout.ArcLayout;
import com.tur.bumerang.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class HomeActivity_ViewBinding implements Unbinder {
  private HomeActivity target;

  private View view7f0b01ec;

  private View view7f0b0080;

  private View view7f0b01de;

  private View view7f0b01df;

  private View view7f0b01da;

  private View view7f0b01ff;

  private View view7f0b03e5;

  @UiThread
  public HomeActivity_ViewBinding(HomeActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public HomeActivity_ViewBinding(final HomeActivity target, View source) {
    this.target = target;

    View view;
    target.lyt_bottom_bar = Utils.findRequiredViewAsType(source, R.id.lyt_bottom_bar, "field 'lyt_bottom_bar'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.imv_plus, "field 'imv_plus' and method 'goSelectCategory'");
    target.imv_plus = Utils.castView(view, R.id.imv_plus, "field 'imv_plus'", ImageView.class);
    view7f0b01ec = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.goSelectCategory();
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_home_signup, "field 'btn_home_signup' and method 'goRegister'");
    target.btn_home_signup = Utils.castView(view, R.id.btn_home_signup, "field 'btn_home_signup'", Button.class);
    view7f0b0080 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.goRegister();
      }
    });
    view = Utils.findRequiredView(source, R.id.imv_chart, "field 'imv_chart' and method 'goMainHome'");
    target.imv_chart = Utils.castView(view, R.id.imv_chart, "field 'imv_chart'", ImageView.class);
    view7f0b01de = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.goMainHome();
      }
    });
    view = Utils.findRequiredView(source, R.id.imv_chat, "field 'imv_chat' and method 'goChat'");
    target.imv_chat = Utils.castView(view, R.id.imv_chat, "field 'imv_chat'", ImageView.class);
    view7f0b01df = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.goChat();
      }
    });
    view = Utils.findRequiredView(source, R.id.imv_box, "field 'imv_box' and method 'goBox'");
    target.imv_box = Utils.castView(view, R.id.imv_box, "field 'imv_box'", ImageView.class);
    view7f0b01da = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.goBox();
      }
    });
    view = Utils.findRequiredView(source, R.id.imv_userinfo, "field 'imv_userinfo' and method 'goUserProfile'");
    target.imv_userinfo = Utils.castView(view, R.id.imv_userinfo, "field 'imv_userinfo'", ImageView.class);
    view7f0b01ff = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.goUserProfile();
      }
    });
    target.arcLayout = Utils.findRequiredViewAsType(source, R.id.arcLayout, "field 'arcLayout'", ArcLayout.class);
    view = Utils.findRequiredView(source, R.id.viewBlackTransparent, "field 'viewBlackTransparent' and method 'hideBlackTransparent'");
    target.viewBlackTransparent = view;
    view7f0b03e5 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.hideBlackTransparent();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    HomeActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.lyt_bottom_bar = null;
    target.imv_plus = null;
    target.btn_home_signup = null;
    target.imv_chart = null;
    target.imv_chat = null;
    target.imv_box = null;
    target.imv_userinfo = null;
    target.arcLayout = null;
    target.viewBlackTransparent = null;

    view7f0b01ec.setOnClickListener(null);
    view7f0b01ec = null;
    view7f0b0080.setOnClickListener(null);
    view7f0b0080 = null;
    view7f0b01de.setOnClickListener(null);
    view7f0b01de = null;
    view7f0b01df.setOnClickListener(null);
    view7f0b01df = null;
    view7f0b01da.setOnClickListener(null);
    view7f0b01da = null;
    view7f0b01ff.setOnClickListener(null);
    view7f0b01ff = null;
    view7f0b03e5.setOnClickListener(null);
    view7f0b03e5 = null;
  }
}
