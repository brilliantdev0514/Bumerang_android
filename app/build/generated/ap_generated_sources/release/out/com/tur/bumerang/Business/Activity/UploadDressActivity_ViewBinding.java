// Generated code from Butter Knife. Do not modify!
package com.tur.bumerang.Business.Activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.tur.bumerang.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class UploadDressActivity_ViewBinding implements Unbinder {
  private UploadDressActivity target;

  private View view7f0b009d;

  private View view7f0b01cd;

  private View view7f0b01ce;

  private View view7f0b01cf;

  private View view7f0b01d0;

  private View view7f0b01d1;

  private View view7f0b01d2;

  private View view7f0b01d3;

  @UiThread
  public UploadDressActivity_ViewBinding(UploadDressActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public UploadDressActivity_ViewBinding(final UploadDressActivity target, View source) {
    this.target = target;

    View view;
    target.lyt_categoty_dress = Utils.findRequiredViewAsType(source, R.id.lyt_categoty_dress, "field 'lyt_categoty_dress'", LinearLayout.class);
    target.edt_upload_dress_title = Utils.findRequiredViewAsType(source, R.id.edt_upload_dress_title, "field 'edt_upload_dress_title'", EditText.class);
    target.spinner_gender = Utils.findRequiredViewAsType(source, R.id.spinner_gender, "field 'spinner_gender'", Spinner.class);
    target.spinner_size = Utils.findRequiredViewAsType(source, R.id.spinner_size, "field 'spinner_size'", Spinner.class);
    target.spinner_color = Utils.findRequiredViewAsType(source, R.id.spinner_color, "field 'spinner_color'", Spinner.class);
    target.edt_upload_dress_des = Utils.findRequiredViewAsType(source, R.id.edt_upload_dress_des, "field 'edt_upload_dress_des'", EditText.class);
    target.radioGroup = Utils.findRequiredViewAsType(source, R.id.priceRadioGroup, "field 'radioGroup'", RadioGroup.class);
    target.radioDaily = Utils.findRequiredViewAsType(source, R.id.daily, "field 'radioDaily'", RadioButton.class);
    target.radioWeekly = Utils.findRequiredViewAsType(source, R.id.weekly, "field 'radioWeekly'", RadioButton.class);
    target.radioMonthly = Utils.findRequiredViewAsType(source, R.id.monthly, "field 'radioMonthly'", RadioButton.class);
    target.edt_price = Utils.findRequiredViewAsType(source, R.id.edt_price, "field 'edt_price'", EditText.class);
    target.edt_address = Utils.findRequiredViewAsType(source, R.id.edt_address, "field 'edt_address'", TextView.class);
    view = Utils.findRequiredView(source, R.id.btn_upload, "field 'btn_upload' and method 'Upload'");
    target.btn_upload = Utils.castView(view, R.id.btn_upload, "field 'btn_upload'", Button.class);
    view7f0b009d = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.Upload();
      }
    });
    view = Utils.findRequiredView(source, R.id.img_upload_0, "method 'carImageSelect0'");
    view7f0b01cd = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.carImageSelect0();
      }
    });
    view = Utils.findRequiredView(source, R.id.img_upload_1, "method 'carImageSelect1'");
    view7f0b01ce = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.carImageSelect1();
      }
    });
    view = Utils.findRequiredView(source, R.id.img_upload_2, "method 'carImageSelect2'");
    view7f0b01cf = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.carImageSelect2();
      }
    });
    view = Utils.findRequiredView(source, R.id.img_upload_3, "method 'carImageSelect3'");
    view7f0b01d0 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.carImageSelect3();
      }
    });
    view = Utils.findRequiredView(source, R.id.img_upload_4, "method 'carImageSelect4'");
    view7f0b01d1 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.carImageSelect4();
      }
    });
    view = Utils.findRequiredView(source, R.id.img_upload_5, "method 'carImageSelect5'");
    view7f0b01d2 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.carImageSelect5();
      }
    });
    view = Utils.findRequiredView(source, R.id.img_upload_prev, "method 'carImageSelect'");
    view7f0b01d3 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.carImageSelect();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    UploadDressActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.lyt_categoty_dress = null;
    target.edt_upload_dress_title = null;
    target.spinner_gender = null;
    target.spinner_size = null;
    target.spinner_color = null;
    target.edt_upload_dress_des = null;
    target.radioGroup = null;
    target.radioDaily = null;
    target.radioWeekly = null;
    target.radioMonthly = null;
    target.edt_price = null;
    target.edt_address = null;
    target.btn_upload = null;

    view7f0b009d.setOnClickListener(null);
    view7f0b009d = null;
    view7f0b01cd.setOnClickListener(null);
    view7f0b01cd = null;
    view7f0b01ce.setOnClickListener(null);
    view7f0b01ce = null;
    view7f0b01cf.setOnClickListener(null);
    view7f0b01cf = null;
    view7f0b01d0.setOnClickListener(null);
    view7f0b01d0 = null;
    view7f0b01d1.setOnClickListener(null);
    view7f0b01d1 = null;
    view7f0b01d2.setOnClickListener(null);
    view7f0b01d2 = null;
    view7f0b01d3.setOnClickListener(null);
    view7f0b01d3 = null;
  }
}