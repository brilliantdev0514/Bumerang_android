// Generated code from Butter Knife. Do not modify!
package com.tur.bumerang.Business.Activity;

import android.view.View;
import android.widget.RelativeLayout;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.tur.bumerang.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SelectCategoryRegisterActivity_ViewBinding implements Unbinder {
  private SelectCategoryRegisterActivity target;

  private View view7f0b00b8;

  private View view7f0b00b5;

  private View view7f0b00b6;

  private View view7f0b00b9;

  private View view7f0b00b7;

  private View view7f0b03a1;

  @UiThread
  public SelectCategoryRegisterActivity_ViewBinding(SelectCategoryRegisterActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SelectCategoryRegisterActivity_ViewBinding(final SelectCategoryRegisterActivity target,
      View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.card_sel_cate_house, "field 'card_sel_cate_house' and method 'onSelectedHouse'");
    target.card_sel_cate_house = Utils.castView(view, R.id.card_sel_cate_house, "field 'card_sel_cate_house'", RelativeLayout.class);
    view7f0b00b8 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onSelectedHouse();
      }
    });
    view = Utils.findRequiredView(source, R.id.card_sel_cate_car, "field 'card_sel_cate_car' and method 'onSelectedCar'");
    target.card_sel_cate_car = Utils.castView(view, R.id.card_sel_cate_car, "field 'card_sel_cate_car'", RelativeLayout.class);
    view7f0b00b5 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onSelectedCar();
      }
    });
    view = Utils.findRequiredView(source, R.id.card_sel_cate_caravan, "field 'card_sel_cate_caravan' and method 'onSelectedCaravan'");
    target.card_sel_cate_caravan = Utils.castView(view, R.id.card_sel_cate_caravan, "field 'card_sel_cate_caravan'", RelativeLayout.class);
    view7f0b00b6 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onSelectedCaravan();
      }
    });
    view = Utils.findRequiredView(source, R.id.card_sel_cate_vehicle, "field 'card_sel_cate_vehicle' and method 'onSelectedVehicle'");
    target.card_sel_cate_vehicle = Utils.castView(view, R.id.card_sel_cate_vehicle, "field 'card_sel_cate_vehicle'", RelativeLayout.class);
    view7f0b00b9 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onSelectedVehicle();
      }
    });
    view = Utils.findRequiredView(source, R.id.card_sel_cate_dress, "field 'card_sel_cate_dress' and method 'onSelectedDress'");
    target.card_sel_cate_dress = Utils.castView(view, R.id.card_sel_cate_dress, "field 'card_sel_cate_dress'", RelativeLayout.class);
    view7f0b00b7 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onSelectedDress();
      }
    });
    view = Utils.findRequiredView(source, R.id.txv_close, "method 'onClose'");
    view7f0b03a1 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClose();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    SelectCategoryRegisterActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.card_sel_cate_house = null;
    target.card_sel_cate_car = null;
    target.card_sel_cate_caravan = null;
    target.card_sel_cate_vehicle = null;
    target.card_sel_cate_dress = null;

    view7f0b00b8.setOnClickListener(null);
    view7f0b00b8 = null;
    view7f0b00b5.setOnClickListener(null);
    view7f0b00b5 = null;
    view7f0b00b6.setOnClickListener(null);
    view7f0b00b6 = null;
    view7f0b00b9.setOnClickListener(null);
    view7f0b00b9 = null;
    view7f0b00b7.setOnClickListener(null);
    view7f0b00b7 = null;
    view7f0b03a1.setOnClickListener(null);
    view7f0b03a1 = null;
  }
}
