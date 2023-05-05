// Generated by view binder compiler. Do not edit!
package com.example.minichef_v1.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import com.example.minichef_v1.R;
import java.lang.NullPointerException;
import java.lang.Override;

public final class ContentAuthBinding implements ViewBinding {
  @NonNull
  private final FrameLayout rootView;

  @NonNull
  public final FrameLayout contenedorAuth;

  private ContentAuthBinding(@NonNull FrameLayout rootView, @NonNull FrameLayout contenedorAuth) {
    this.rootView = rootView;
    this.contenedorAuth = contenedorAuth;
  }

  @Override
  @NonNull
  public FrameLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ContentAuthBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ContentAuthBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.content_auth, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ContentAuthBinding bind(@NonNull View rootView) {
    if (rootView == null) {
      throw new NullPointerException("rootView");
    }

    FrameLayout contenedorAuth = (FrameLayout) rootView;

    return new ContentAuthBinding((FrameLayout) rootView, contenedorAuth);
  }
}