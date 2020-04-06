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

public class UploadApartmentActivity_ViewBinding implements Unbinder {
  private UploadApartmentActivity target;

  private View view7f0b009d;

  private View view7f0b01f8;

  private View view7f0b01f9;

  private View view7f0b01fa;

  private View view7f0b01fb;

  private View view7f0b01fc;

  private View view7f0b01fd;

  private View view7f0b01d3;

  @UiThread
  public UploadApartmentActivity_ViewBinding(UploadApartmentActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public UploadApartmentActivity_ViewBinding(final UploadApartmentActivity target, View source) {
    this.target = target;

    View view;
    target.lyt_categoty_apart = Utils.findRequiredViewAsType(source, R.id.lyt_categoty_apart, "field 'lyt_categoty_apart'", LinearLayout.class);
    target.edt_roomnumber = Utils.findRequiredViewAsType(source, R.id.edt_roomnumber, "field 'edt_roomnumber'", EditText.class);
    target.txvApartTitle = Utils.findRequiredViewAsType(source, R.id.txv_upload_apart_title, "field 'txvApartTitle'", TextView.class);
    target.spinner_heating = Utils.findRequiredViewAsType(source, R.id.spinner_heating, "field 'spinner_heating'", Spinner.class);
    target.spinner_furbished = Utils.findRequiredViewAsType(source, R.id.spinner_furbished, "field 'spinner_furbished'", Spinner.class);
    target.spinner_deposit = Utils.findRequiredViewAsType(source, R.id.spinner_deposit, "field 'spinner_deposit'", Spinner.class);
    target.edt_apart_des = Utils.findRequiredViewAsType(source, R.id.edt_apart_des, "field 'edt_apart_des'", EditText.class);
    target.edt_address = Utils.findRequiredViewAsType(source, R.id.edt_address, "field 'edt_address'", EditText.class);
    target.priceRadioGroup = Utils.findRequiredViewAsType(source, R.id.priceRadioGroup, "field 'priceRadioGroup'", RadioGroup.class);
    target.radioDaily = Utils.findRequiredViewAsType(source, R.id.daily, "field 'radioDaily'", RadioButton.class);
    target.radioWeekly = Utils.findRequiredViewAsType(source, R.id.weekly, "field 'radioWeekly'", RadioButton.class);
    target.radioMonthly = Utils.findRequiredViewAsType(source, R.id.monthly, "field 'radioMonthly'", RadioButton.class);
    target.edt_price = Utils.findRequiredViewAsType(source, R.id.edt_price, "field 'edt_price'", EditText.class);
    view = Utils.findRequiredView(source, R.id.btn_upload, "field 'btn_upload' and method 'Upload'");
    target.btn_upload = Utils.castView(view, R.id.btn_upload, "field 'btn_upload'", Button.class);
    view7f0b009d = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.Upload();
      }
    });
    view = Utils.findRequiredView(source, R.id.imv_upload_apart_0, "method 'carImageSelect0'");
    view7f0b01f8 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.carImageSelect0();
      }
    });
    view = Utils.findRequiredView(source, R.id.imv_upload_apart_1, "method 'carImageSelect1'");
    view7f0b01f9 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.carImageSelect1();
      }
    });
    view = Utils.findRequiredView(source, R.id.imv_upload_apart_2, "method 'carImageSelect2'");
    view7f0b01fa = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.carImageSelect2();
      }
    });
    view = Utils.findRequiredView(source, R.id.imv_upload_apart_3, "method 'carImageSelect3'");
    view7f0b01fb = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.carImageSelect3();
      }
    });
    view = Utils.findRequiredView(source, R.id.imv_upload_apart_4, "method 'carImageSelect4'");
    view7f0b01fc = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.carImageSelect4();
      }
    });
    view = Utils.findRequiredView(source, R.id.imv_upload_apart_5, "method 'carImageSelect5'");
    view7f0b01fd = view;
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
    UploadApartmentActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.lyt_categoty_apart = null;
    target.edt_roomnumber = null;
    target.txvApartTitle = null;
    target.spinner_heating = null;
    target.spinner_furbished = null;
    target.spinner_deposit = null;
    target.edt_apart_des = null;
    target.edt_address = null;
    target.priceRadioGroup = null;
    target.radioDaily = null;
    target.radioWeekly = null;
    target.radioMonthly = null;
    target.edt_price = null;
    target.btn_upload = null;

    view7f0b009d.setOnClickListener(null);
    view7f0b009d = null;
    view7f0b01f8.setOnClickListener(null);
    view7f0b01f8 = null;
    view7f0b01f9.setOnClickListener(null);
    view7f0b01f9 = null;
    view7f0b01fa.setOnClickListener(null);
    view7f0b01fa = null;
    view7f0b01fb.setOnClickListener(null);
    view7f0b01fb = null;
    view7f0b01fc.setOnClickListener(null);
    view7f0b01fc = null;
    view7f0b01fd.setOnClickListener(null);
    view7f0b01fd = null;
    view7f0b01d3.setOnClickListener(null);
    view7f0b01d3 = null;
  }
}
