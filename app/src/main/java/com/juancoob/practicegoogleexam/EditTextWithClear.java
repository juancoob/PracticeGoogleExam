package com.juancoob.practicegoogleexam;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Original example: https://google-developer-training.github.io/android-developer-advanced-course-practicals/unit-5-advanced-graphics-and-views/lesson-10-custom-views/10-1a-p-using-custom-views/10-1a-p-using-custom-views.html
 *
 * Created by Juan Antonio Cobos Obrero on 15/09/18.
 */
public class EditTextWithClear extends android.support.v7.widget.AppCompatEditText {

    private Drawable mClearButtonImage;

    public EditTextWithClear(Context context) {
        super(context);
        init();
    }

    public EditTextWithClear(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EditTextWithClear(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        mClearButtonImage = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_clear_opaque_24dp, null);

        // If the text changes, show or hide the clear (X) button.
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Do nothing.
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                showClearButtom();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Do nothing.
            }
        });

        // If the clear (X) button is tapped, clear the text.
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (getCompoundDrawablesRelative()[2] != null) {
                    float clearButtonStart; // LTR languages
                    float clearButtonEnd;   // RTL languages
                    boolean isClearButtonClicked = false;

                    // Detect the touch in RTL or LTR layout direction
                    if (getLayoutDirection() == LAYOUT_DIRECTION_RTL) {
                        // If RTL, get the end of the button on the left side
                        clearButtonEnd = mClearButtonImage.getIntrinsicWidth() + getPaddingStart();
                        // If the touch occurred before the end of the button, set isClearButtonClicked to true
                        if (motionEvent.getX() < clearButtonEnd) {
                            isClearButtonClicked = true;
                        }
                    } else {
                        // Layout is LTR
                        // Get start of the button on the right side
                        clearButtonStart = getWidth() - getPaddingEnd() - mClearButtonImage.getIntrinsicWidth();
                        // If the touch occurred after the start of the button, set isClearButtonClicked to true
                        if (motionEvent.getX() > clearButtonStart) {
                            isClearButtonClicked = true;
                        }
                    }

                    // Check for actions if the button is tapped
                    if (isClearButtonClicked) {
                        // Check for ACTION_DOWN because always occurs before ACTION_UP
                        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                            // Switch to the black version of clear button
                            mClearButtonImage = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_clear_black_24dp, null);
                            showClearButtom();
                        }
                        // Check for action up
                        if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                            mClearButtonImage = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_clear_opaque_24dp, null);
                            // Clear text and hide button
                            getText().clear();
                            hideClearButtom();
                            return true;
                        }
                    } else {
                        return false;
                    }
                }
                return false;
            }
        });
    }

    private void showClearButtom() {
        setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, mClearButtonImage, null);
    }

    private void hideClearButtom() {
        setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, null, null);
    }


}
