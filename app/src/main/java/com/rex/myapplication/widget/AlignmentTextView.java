package com.rex.myapplication.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextUtils;
import android.util.AttributeSet;

/**
 * @Description: 处理内容自动换行的TextView
 * @Author: 李宇峰 on 2019-02-25 2:46 PM.
 * @NO.: 146714
 * @Phone: 13756017116
 * @Email: liyufeng@syswin.com
 * @Leader：yupengfei <yupengfei@syswin.com>
 * @Charge: 李宇峰
 */
public class AlignmentTextView extends AppCompatTextView {

    private String text;
    /**
     * 结尾符号
     */
    private String ellipStr = "...";
    /**
     * 字符间距
     */
    private float mCharacterSpace = 0;
    /**
     * view的内容宽度
     */
    private int mContentWidth;

    private Layout mLayout;
    private float mTextHeight;

    public AlignmentTextView(Context context) {
        this(context, null);
    }

    public AlignmentTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
    }

    @Override
    protected void onDraw(Canvas canvas) {
        text = getText() == null ? null : getText().toString();
        mContentWidth = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();

        mLayout = getLayout();
        // layout.getLayout()在4.4.3出现NullPointerException
        if (mLayout == null) {
            return;
        }

        Paint.FontMetrics fm = getPaint().getFontMetrics();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            mTextHeight = (int) (Math.ceil(fm.descent - fm.ascent) + getLineSpacingExtra());
        } else {
            mTextHeight = (int) (Math.ceil(fm.descent - fm.ascent) + getLineSpacingExtra());
        }
        mTextHeight = (int) (mTextHeight * mLayout.getSpacingMultiplier() + mLayout
                .getSpacingAdd());

        if (TextUtils.isEmpty(text)) {
            super.onDraw(canvas);
        } else {
            drawSBCLines(canvas, text);
        }
    }

    private void drawSBCLines(Canvas canvas, String text) {
        int line = 1;  //当前绘制行数
        float mLineY = getTextSize() + getPaddingTop();
        float x = getPaddingLeft();
        for (int i = 0; i < text.length(); i++) {
            String c = String.valueOf(text.charAt(i));
            if (c.equals(" ")) {
                continue;
            }
            float cw = StaticLayout.getDesiredWidth(c, getPaint());
            if (line == getMaxLines()) {
                //判断是否为该行的倒数第二个字符（判断有些粗略）
                if (x + cw * 2 - mCharacterSpace > mContentWidth + getPaddingLeft()) {
                    c = ellipStr;//替换结尾字符部分
                }
            }
            canvas.drawText(c, x, mLineY, getPaint());
            x += cw + mCharacterSpace;
            if (x + cw - mCharacterSpace > mContentWidth + getPaddingLeft() || c.equals("\n")) {
                line++;
                mLineY += mTextHeight;
                x = getPaddingLeft();
            }
            // 一行中的最后一个字符能挤就挤，(●'◡'●)
            else if (x + cw > mContentWidth + getPaddingLeft()
                    && x - mCharacterSpace + cw <= mContentWidth + getPaddingLeft()) {
                x -= mCharacterSpace;
            }
        }
    }


}
