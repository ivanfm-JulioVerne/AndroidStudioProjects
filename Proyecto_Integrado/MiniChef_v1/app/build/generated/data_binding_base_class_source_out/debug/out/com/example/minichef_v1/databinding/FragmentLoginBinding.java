// Generated by view binder compiler. Do not edit!
package com.example.minichef_v1.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.minichef_v1.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentLoginBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final Button bLoginLogin;

  @NonNull
  public final Button bLoginSignin;

  @NonNull
  public final Button bLoginSignin2;

  @NonNull
  public final EditText emailLoginEditText;

  @NonNull
  public final EditText passwordLoginEditText;

  private FragmentLoginBinding(@NonNull LinearLayout rootView, @NonNull Button bLoginLogin,
      @NonNull Button bLoginSignin, @NonNull Button bLoginSignin2,
      @NonNull EditText emailLoginEditText, @NonNull EditText passwordLoginEditText) {
    this.rootView = rootView;
    this.bLoginLogin = bLoginLogin;
    this.bLoginSignin = bLoginSignin;
    this.bLoginSignin2 = bLoginSignin2;
    this.emailLoginEditText = emailLoginEditText;
    this.passwordLoginEditText = passwordLoginEditText;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentLoginBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentLoginBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_login, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentLoginBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.bLoginLogin;
      Button bLoginLogin = ViewBindings.findChildViewById(rootView, id);
      if (bLoginLogin == null) {
        break missingId;
      }

      id = R.id.bLoginSignin;
      Button bLoginSignin = ViewBindings.findChildViewById(rootView, id);
      if (bLoginSignin == null) {
        break missingId;
      }

      id = R.id.bLoginSignin2;
      Button bLoginSignin2 = ViewBindings.findChildViewById(rootView, id);
      if (bLoginSignin2 == null) {
        break missingId;
      }

      id = R.id.emailLoginEditText;
      EditText emailLoginEditText = ViewBindings.findChildViewById(rootView, id);
      if (emailLoginEditText == null) {
        break missingId;
      }

      id = R.id.passwordLoginEditText;
      EditText passwordLoginEditText = ViewBindings.findChildViewById(rootView, id);
      if (passwordLoginEditText == null) {
        break missingId;
      }

      return new FragmentLoginBinding((LinearLayout) rootView, bLoginLogin, bLoginSignin,
          bLoginSignin2, emailLoginEditText, passwordLoginEditText);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}