package com.example.speciallistbyjames.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;

import com.android.volley.toolbox.NetworkImageView;

@SuppressWarnings("UnusedDeclaration")
public class RoundedImageView extends NetworkImageView {

	public static final String TAG = "RoundedImageView";
	public static final float DEFAULT_RADIUS = 10f;
	public static final float DEFAULT_BORDER_WIDTH = 0f;

	private float cornerRadius = DEFAULT_RADIUS;
	private float borderWidth = DEFAULT_BORDER_WIDTH;
	private ColorStateList borderColor = ColorStateList
			.valueOf(RoundedDrawable.DEFAULT_BORDER_COLOR);
	private boolean isOval = false;
	private boolean mutateBackground = false;

	private int mResource;
	private Drawable mDrawable;
	private Drawable mBackgroundDrawable;

	private ScaleType mScaleType;

	public RoundedImageView(Context context) {
		super(context);
	}

	public RoundedImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setScaleType(ScaleType.CENTER_CROP);
		updateDrawableAttrs();
		updateBackgroundDrawableAttrs(true);
	}

	@Override
	protected void drawableStateChanged() {
		super.drawableStateChanged();
		invalidate();
	}

	/**
	 * Return the current scale type in use by this ImageView.
	 * 
	 * @attr ref android.R.styleable#ImageView_scaleType
	 * @see android.widget.ImageView.ScaleType
	 */
	@Override
	public ScaleType getScaleType() {
		return mScaleType;
	}

	/**
	 * Controls how the image should be resized or moved to match the size of
	 * this ImageView.
	 * 
	 * @param scaleType
	 *            The desired scaling mode.
	 * @attr ref android.R.styleable#ImageView_scaleType
	 */
	@Override
	public void setScaleType(ScaleType scaleType) {
		assert scaleType != null;

		if (mScaleType != scaleType) {
			mScaleType = scaleType;

			switch (scaleType) {
			case CENTER:
			case CENTER_CROP:
			case CENTER_INSIDE:
			case FIT_CENTER:
			case FIT_START:
			case FIT_END:
			case FIT_XY:
				super.setScaleType(ScaleType.FIT_XY);
				break;
			default:
				super.setScaleType(scaleType);
				break;
			}

			updateDrawableAttrs();
			updateBackgroundDrawableAttrs(false);
			invalidate();
		}
	}

	@Override
	public void setImageDrawable(Drawable drawable) {
		mResource = 0;
		mDrawable = RoundedDrawable.fromDrawable(drawable);
		updateDrawableAttrs();
		super.setImageDrawable(mDrawable);
	}

	@Override
	public void setImageBitmap(Bitmap bm) {
		mResource = 0;
		mDrawable = RoundedDrawable.fromBitmap(bm);
		updateDrawableAttrs();
		super.setImageDrawable(mDrawable);
	}

	public void setImageBitmap(Bitmap bm, boolean isImmediate) {

		mResource = 0;
		mDrawable = RoundedDrawable.fromBitmap(bm);
		updateDrawableAttrs();

		if (!isImmediate) {
			if (mDrawable != null) {
				TransitionDrawable mTransitionDrawable = new TransitionDrawable(
						new Drawable[] { new ColorDrawable(Color.TRANSPARENT),
								mDrawable });
				super.setImageDrawable(mTransitionDrawable);
				mTransitionDrawable.startTransition(250);
				return;
			}

		}
		super.setImageDrawable(mDrawable);
	}

	@Override
	public void setImageResource(int resId) {
		if (mResource != resId) {
			mResource = resId;
			mDrawable = resolveResource();
			updateDrawableAttrs();
			super.setImageDrawable(mDrawable);
		}
	}

	@Override
	public void setImageURI(Uri uri) {
		super.setImageURI(uri);
		setImageDrawable(getDrawable());
	}

	private Drawable resolveResource() {
		Resources rsrc = getResources();
		if (rsrc == null) {
			return null;
		}

		Drawable d = null;

		if (mResource != 0) {
			try {
				d = rsrc.getDrawable(mResource);
			} catch (Exception e) {
				Log.w(TAG, "Unable to find resource: " + mResource, e);
				// Don't try again.
				mResource = 0;
			}
		}
		return RoundedDrawable.fromDrawable(d);
	}

	private void updateDrawableAttrs() {
		updateAttrs(mDrawable);
	}

	private void updateBackgroundDrawableAttrs(boolean convert) {
		if (mutateBackground) {
			if (convert) {
				mBackgroundDrawable = RoundedDrawable
						.fromDrawable(mBackgroundDrawable);
			}
			updateAttrs(mBackgroundDrawable);
		}
	}

	private void updateAttrs(Drawable drawable) {
		if (drawable == null) {
			return;
		}

		if (drawable instanceof RoundedDrawable) {
			((RoundedDrawable) drawable).setScaleType(mScaleType)
					.setCornerRadius(cornerRadius).setBorderWidth(borderWidth)
					.setBorderColor(borderColor).setOval(isOval);
		} else if (drawable instanceof LayerDrawable) {
			// loop through layers to and set drawable attrs
			LayerDrawable ld = ((LayerDrawable) drawable);
			for (int i = 0, layers = ld.getNumberOfLayers(); i < layers; i++) {
				updateAttrs(ld.getDrawable(i));
			}
		}
	}

	@Override
	@Deprecated
	public void setBackgroundDrawable(Drawable background) {
		mBackgroundDrawable = background;
		updateBackgroundDrawableAttrs(true);
		super.setBackgroundDrawable(mBackgroundDrawable);
	}

	public float getCornerRadius() {
		return cornerRadius;
	}

	public void setCornerRadius(int resId) {
		setCornerRadius(getResources().getDimension(resId));
	}

	public void setCornerRadius(float radius) {
		if (cornerRadius == radius) {
			return;
		}

		cornerRadius = radius;
		updateDrawableAttrs();
		updateBackgroundDrawableAttrs(false);
	}

	public float getBorderWidth() {
		return borderWidth;
	}

	public void setBorderWidth(int resId) {
		setBorderWidth(getResources().getDimension(resId));
	}

	public void setBorderWidth(float width) {
		if (borderWidth == width) {
			return;
		}

		borderWidth = width;
		updateDrawableAttrs();
		updateBackgroundDrawableAttrs(false);
		invalidate();
	}

	public int getBorderColor() {
		return borderColor.getDefaultColor();
	}

	public void setBorderColor(int color) {
		setBorderColor(ColorStateList.valueOf(color));
	}

	public ColorStateList getBorderColors() {
		return borderColor;
	}

	public void setBorderColor(ColorStateList colors) {
		if (borderColor.equals(colors)) {
			return;
		}

		borderColor = (colors != null) ? colors : ColorStateList
				.valueOf(RoundedDrawable.DEFAULT_BORDER_COLOR);
		updateDrawableAttrs();
		updateBackgroundDrawableAttrs(false);
		if (borderWidth > 0) {
			invalidate();
		}
	}

	public boolean isOval() {
		return isOval;
	}

	public void setOval(boolean oval) {
		isOval = oval;
		updateDrawableAttrs();
		updateBackgroundDrawableAttrs(false);
		invalidate();
	}

	public boolean isMutateBackground() {
		return mutateBackground;
	}

	public void setMutateBackground(boolean mutate) {
		if (mutateBackground == mutate) {
			return;
		}

		mutateBackground = mutate;
		updateBackgroundDrawableAttrs(true);
		invalidate();
	}
}
