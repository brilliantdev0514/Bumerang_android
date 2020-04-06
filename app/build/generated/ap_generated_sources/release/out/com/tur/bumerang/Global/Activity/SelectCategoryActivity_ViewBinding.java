// Generated code from Butter Knife. Do not modify!
package com.tur.bumerang.Global.Activity;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.tur.bumerang.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SelectCategoryActivity_ViewBinding implements Unbinder {
  private SelectCategoryActivity target;

  private View view7f0b02d5;

  private View view7f0b02d2;

  private View view7f0b02d3;

  private View view7f0b02d9;

  private View view7f0b02d4;

  private View view7f0b02d0;

  private View view7f0b02d1;

  private View view7f0b02d6;

  private View view7f0b02d8;

  private View view7f0b02d7;

  @UiThread
  public SelectCategoryActivity_ViewBinding(SelectCategoryActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SelectCategoryActivity_ViewBinding(final SelectCategoryActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.rounded_category_home, "method 'goUploadApartment'");
    view7f0b02d5 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.goUploadApartment();
      }
    });
    view = Utils.findRequiredView(source, R.id.rounded_category_car, "method 'goUploadRentalcar'");
    view7f0b02d2 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.goUploadRentalcar();
      }
    });
    view = Utils.findRequiredView(source, R.id.rounded_category_caravan, "method 'goUploadCaravan'");
    view7f0b02d3 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.goUploadCaravan();
      }
    });
    view = Utils.findRequiredView(source, R.id.rounded_category_vehicle, "method 'goUploadVehicle'");
    view7f0b02d9 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.goUploadVehicle();
      }
    });
    view = Utils.findRequiredView(source, R.id.rounded_category_dress, "method 'goUploadDress'");
    view7f0b02d4 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.goUploadDress();
      }
    });
    view = Utils.findRequiredView(source, R.id.rounded_category_bike, "method 'goUploadBike'");
    view7f0b02d0 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.goUploadBike();
      }
    });
    view = Utils.findRequiredView(source, R.id.rounded_category_camera, "method 'goUploadCamera'");
    view7f0b02d1 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.goUploadCamera();
      }
    });
    view = Utils.findRequiredView(source, R.id.rounded_category_kamp, "method 'goUploadKamp'");
    view7f0b02d6 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.goUploadKamp();
      }
    });
    view = Utils.findRequiredView(source, R.id.rounded_category_music, "method 'goUploadMusic'");
    view7f0b02d8 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.goUploadMusic();
      }
    });
    view = Utils.findRequiredView(source, R.id.rounded_category_more, "method 'goUploadMore'");
    view7f0b02d7 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.goUploadMore();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    target = null;


    view7f0b02d5.setOnClickListener(null);
    view7f0b02d5 = null;
    view7f0b02d2.setOnClickListener(null);
    view7f0b02d2 = null;
    view7f0b02d3.setOnClickListener(null);
    view7f0b02d3 = null;
    view7f0b02d9.setOnClickListener(null);
    view7f0b02d9 = null;
    view7f0b02d4.setOnClickListener(null);
    view7f0b02d4 = null;
    view7f0b02d0.setOnClickListener(null);
    view7f0b02d0 = null;
    view7f0b02d1.setOnClickListener(null);
    view7f0b02d1 = null;
    view7f0b02d6.setOnClickListener(null);
    view7f0b02d6 = null;
    view7f0b02d8.setOnClickListener(null);
    view7f0b02d8 = null;
    view7f0b02d7.setOnClickListener(null);
    view7f0b02d7 = null;
  }
}
