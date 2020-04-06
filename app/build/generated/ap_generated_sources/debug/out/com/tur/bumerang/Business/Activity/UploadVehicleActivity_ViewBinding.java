// Generated code from Butter Knife. Do not modify!
package com.tur.bumerang.Business.Activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

public class UploadVehicleActivity_ViewBinding implements Unbinder {
  private UploadVehicleActivity target;

  private View view7f0b01d3;

  private View view7f0b009d;

  private View view7f0b01cd;

  private View view7f0b01ce;

  private View view7f0b01cf;

  private View view7f0b01d0;

  private View view7f0b01d1;

  private View view7f0b01d2;

  @UiThread
  public UploadVehicleActivity_ViewBinding(UploadVehicleActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public UploadVehicleActivity_ViewBinding(final UploadVehicleActivity target, View source) {
    this.target = target;

    View view;
    target.lyt_categoty_vehicle = Utils.findRequiredViewAsType(source, R.id.lyt_categoty_vehicle, "field 'lyt_categoty_vehicle'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.img_upload_prev, "field 'imv_upload_vehicle' and method 'carImageSelect'");
    target.imv_upload_vehicle = Utils.castView(view, R.id.img_upload_prev, "field 'imv_upload_vehicle'", ImageView.class);
    view7f0b01d3 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.carImageSelect();
      }
    });
    target.spinner_vehicle_captan = Utils.findRequiredViewAsType(source, R.id.spinner_vehicle_captan, "field 'spinner_vehicle_captan'", Spinner.class);
    target.edt_vehicle_title = Utils.findRequiredViewAsType(source, R.id.edt_vehicle_title, "field 'edt_vehicle_title'", EditText.class);
    target.edt_vehicle_des = Utils.findRequiredViewAsType(source, R.id.edt_vehicle_des, "field 'edt_vehicle_des'", EditText.class);
    target.edt_address = Utils.findRequiredViewAsType(source, R.id.edt_address, "field 'edt_address'", TextView.class);
    target.radioGroup = Utils.findRequiredViewAsType(source, R.id.priceRadioGroup, "field 'radioGroup'", RadioGroup.class);
    target.daily = Utils.findRequiredViewAsType(source, R.id.daily, "field 'daily'", RadioButton.class);
    target.weekly = Utils.findRequiredViewAsType(source, R.id.weekly, "field 'weekly'", RadioButton.class);
    target.monthly = Utils.findRequiredViewAsType(source, R.id.monthly, "field 'monthly'", RadioButton.class);
    target.edt_price = Utils.findRequiredViewAsType(source, R.id.edt_price, "field 'edt_price'", EditText.class);
    target.edt_person_capacity = Utils.findRequiredViewAsType(source, R.id.edt_person_capacity, "field 'edt_person_capacity'", EditText.class);
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
  }

  @Override
  @CallSuper
  public void unbind() {
    UploadVehicleActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.lyt_categoty_vehicle = null;
    target.imv_upload_vehicle = null;
    target.spinner_vehicle_captan = null;
    target.edt_vehicle_title = null;
    target.edt_vehicle_des = null;
    target.edt_address = null;
    target.radioGroup = null;
    target.daily = null;
    target.weekly = null;
    target.monthly = null;
    target.edt_price = null;
    target.edt_person_capacity = null;
    target.btn_upload = null;

    view7f0b01d3.setOnClickListener(null);
    view7f0b01d3 = null;
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
  }
}
