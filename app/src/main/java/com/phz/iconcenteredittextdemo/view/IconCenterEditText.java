package com.phz.iconcenteredittextdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import androidx.appcompat.widget.AppCompatEditText;

/**
 * @author haizhuo
 * @introduction 图标居中EditText
 */
public class IconCenterEditText extends AppCompatEditText implements View.OnFocusChangeListener{

    /**
     * 是否是默认图标再左边的样式
     */
    private boolean isLeft = false;

    public IconCenterEditText(Context context) {
        this(context,null);
    }

    public IconCenterEditText(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public IconCenterEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOnFocusChangeListener(this);
    }


    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        isLeft = hasFocus;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (isLeft) {
            // 如果是默认样式，则直接绘制
            super.onDraw(canvas);
        } else {
            // 如果不是默认样式，需要将图标绘制在中间
            Drawable[] drawables = getCompoundDrawables();
            if (drawables != null) {
                Drawable drawableLeft = drawables[0];
                if (drawableLeft != null) {
                    float textWidth = getPaint().measureText(getHint().toString());
                    int drawablePadding = getCompoundDrawablePadding();
                    int drawableWidth = drawableLeft.getIntrinsicWidth();
                    float bodyWidth = textWidth + drawableWidth + drawablePadding;
                    canvas.translate((getWidth() - bodyWidth - getPaddingLeft() - getPaddingRight()) / 2, 0);
                }
            }
            super.onDraw(canvas);
        }
    }
}
