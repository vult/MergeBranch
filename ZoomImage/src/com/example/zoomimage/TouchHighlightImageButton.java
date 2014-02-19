package com.example.zoomimage;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageButton;

public class TouchHighlightImageButton extends ImageButton{
	private Drawable mForgroundDrawable;
	  private Rect mCachedBounds = new Rect();

	public TouchHighlightImageButton(Context context) {
		super(context);
		init();
	}
	
	public TouchHighlightImageButton(Context context,AttributeSet attributeSet) {
		super(context,attributeSet);
		init();
	}
	
	public TouchHighlightImageButton(Context context,AttributeSet attributeSet,int defStyle) {
		super(context,attributeSet,defStyle);
		init();
	}
	
	public void init() {
		 setBackgroundColor(0);
	        setPadding(0, 0, 0, 0);
	 
	        // Retrieve the drawable resource assigned to the android.R.attr.selectableItemBackground
	        // theme attribute from the current theme.
	        TypedArray a = getContext().obtainStyledAttributes(new int[]{android.R.attr.selectableItemBackground});
	        mForgroundDrawable = a.getDrawable(0);
	        mForgroundDrawable.setCallback(this);
	        a.recycle();
	}
	
	
	   @Override
	    protected void drawableStateChanged() {
	        super.drawableStateChanged();
	 
	        // Update the state of the highlight drawable to match
	        // the state of the button.
	        if (mForgroundDrawable.isStateful()) {
	            mForgroundDrawable.setState(getDrawableState());
	        }
	 
	        // Trigger a redraw.
	        invalidate();
	    }
	 
	    @Override
	    protected void onDraw(Canvas canvas) {
	        // First draw the image.
	        super.onDraw(canvas);
	 
	        // Then draw the highlight on top of it. If the button is neither focused
	        // nor pressed, the drawable will be transparent, so just the image
	        // will be drawn.
	        mForgroundDrawable.setBounds(mCachedBounds);
	        mForgroundDrawable.draw(canvas);
	    }
	 
	    @Override
	    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
	        super.onSizeChanged(w, h, oldw, oldh);
	 
	        // Cache the view bounds.
	        mCachedBounds.set(0, 0, w, h);
	    }
}
