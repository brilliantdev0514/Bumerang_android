// Generated code from Butter Knife. Do not modify!
package com.tur.bumerang.Business.Activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.tur.bumerang.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class UploadSporeActivity_ViewBinding implements Unbinder {
  private UploadSporeActivity target;

  private View view7f0b009d;

  private View view7f0b01d3;

  @UiThread
  public UploadSporeActivity_ViewBinding(UploadSporeActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public UploadSporeActivity_ViewBinding(final UploadSporeActivity target, View source) {
    this.target = target;

    View view;
    target.lyt_categoty_spore = Utils.findRequiredViewAsType(source, R.id.lyt_categoty_spore, "field 'lyt_categoty_spore'", LinearLayout.class);
    target.edt_upload_spore_title = Utils.findRequiredViewAsType(source, R.id.edt_upload_spore_title, "field 'edt_upload_spore_title'", EditText.class);
    target.edt_spore_des = Utils.findRequiredViewAsType(source, R.id.edt_spore_des, "field 'edt_spore_des'", EditText.class);
    target.edt_price = Utils.findRequiredViewAsType(source, R.id.edt_price, "field 'edt_price'", EditText.class);
    target.radioGroup = Utils.findRequiredViewAsType(source, R.id.priceRadioGroup, "field 'radioGroup'", RadioGroup.class);
    target.radioDaily = Utils.findRequiredViewAsType(source, R.id.daily, "field 'radioDaily'", RadioButton.class);
    target.radioWeekly = Utils.findRequiredViewAsType(source, R.id.weekly, "field 'radioWeekly'", RadioButton.class);
    target.radioMonthly = Utils.findRequiredViewAsType(source, R.id.monthly, "field 'radioMonthly'", RadioButton.class);
    view = Utils.findRequiredView(source, R.id.btn_upload, "field 'btn_upload' and method 'Upload'");
    target.btn_upload = Utils.castView(view, R.id.btn_upload, "field 'btn_upload'", Button.class);
    view7f0b009d = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.Upload();
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
    UploadSporeActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.lyt_categoty_spore = null;
    target.edt_upload_spore_title = null;
    target.edt_spore_des = null;
    target.edt_price = null;
    target.radioGroup = null;
    target.radioDaily = null;
    target.radioWeekly = null;
    target.radioMonthly = null;
    target.btn_upload = null;

    view7f0b009d.setOnClickListener(null);
    view7f0b009d = null;
    view7f0b01d3.setOnClickListener(null);
    view7f0b01d3 = null;
  }
}
