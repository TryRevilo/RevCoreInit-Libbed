package rev.ca.revlibviews.rev_core_input_forms;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.util.TypedValue;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import java.lang.reflect.Field;

import rev.ca.rev_lib_gen_functions.RevDimens;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.revlibviews.R;
import rev.ca.revlibviews.REV_VIEWS_BASE_FUNCTIONS;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLinearLayoutLayoutParams;

import static android.graphics.drawable.GradientDrawable.RECTANGLE;

/**
 * Created by rev on 10/17/17.
 */

public class RevCoreInputFormEditText {

    private Context mContext;
    private RevDimens revDimens;

    private EditText revCoreInputFormEditText;

    public RevCoreInputFormEditText(Context context) {
        mContext = context;
        revDimens = new RevDimens(mContext);
    }

    public EditText getRevCoreInputFormEditText() {
        revCoreInputFormEditText = new EditText(mContext);
        revCoreInputFormEditText.setTextColor(mContext.getResources().getColor(R.color.greyDark));

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        revCoreInputFormEditText.setLayoutParams(layoutParams);

        RevCoreInputFormEditText.setCursorDrawableColor(revCoreInputFormEditText, mContext.getResources().getColor(R.color.greyDark));
        return revCoreInputFormEditText;
    }

    public EditText getRevEditText_ONLY_BottomBorders_Small() {
        revCoreInputFormEditText = this.getRevCoreInputFormEditText();
        revCoreInputFormEditText.setTextSize(TypedValue.COMPLEX_UNIT_PX, RevLibGenConstantine.REV_TEXT_SIZE_TINY);
        revCoreInputFormEditText.setBackgroundResource(R.drawable.rev_bottom_border_only);
        RevCoreLinearLayoutLayoutParams.setEditText_L_P(revCoreInputFormEditText);

        return revCoreInputFormEditText;
    }

    public EditText getRevEditText_ONLY_BottomBorders_Small_Pad_Left() {
        revCoreInputFormEditText = this.getRevCoreInputFormEditText();
        revCoreInputFormEditText.setTextSize(TypedValue.COMPLEX_UNIT_PX, RevLibGenConstantine.REV_TEXT_SIZE_TINY);

        GradientDrawable border = new GradientDrawable();
        border.setStroke(1, ContextCompat.getColor(mContext, R.color.revPurple));
        border.setGradientType(RECTANGLE);

        Drawable[] layers = {border};
        LayerDrawable layerDrawable = new LayerDrawable(layers);
        layerDrawable.setLayerInset(0, -1, -1, -1, 1);
        REV_VIEWS_BASE_FUNCTIONS.REV_SAFE_SET_BACKGROUND(revCoreInputFormEditText, layerDrawable);

        revCoreInputFormEditText.setPadding((int) (RevLibGenConstantine.REV_MARGIN_LARGE * 1.1), RevLibGenConstantine.REV_MARGIN_TINY, RevLibGenConstantine.REV_MARGIN_SMALL, RevLibGenConstantine.REV_MARGIN_TINY);

        return revCoreInputFormEditText;
    }

    public EditText getRevEditText_ONLY_BottomBorders_Small_Pad_Left_Right() {
        revCoreInputFormEditText = this.getRevCoreInputFormEditText();
        revCoreInputFormEditText.setTextSize(TypedValue.COMPLEX_UNIT_PX, RevLibGenConstantine.REV_TEXT_SIZE_TINY);

        GradientDrawable border = new GradientDrawable();
        border.setStroke(1, ContextCompat.getColor(mContext, R.color.revPurple));
        border.setGradientType(RECTANGLE);

        Drawable[] layers = {border};
        LayerDrawable layerDrawable = new LayerDrawable(layers);
        layerDrawable.setLayerInset(0, -1, -1, -1, 1);
        REV_VIEWS_BASE_FUNCTIONS.REV_SAFE_SET_BACKGROUND(revCoreInputFormEditText, layerDrawable);

        revCoreInputFormEditText.setPadding((int) (RevLibGenConstantine.REV_MARGIN_LARGE * 1.1), RevLibGenConstantine.REV_MARGIN_TINY, (int) (RevLibGenConstantine.REV_MARGIN_LARGE * 1.1), RevLibGenConstantine.REV_MARGIN_TINY);

        return revCoreInputFormEditText;
    }

    public EditText getRevEditText_L_R_B_PartialBorders() {
        revCoreInputFormEditText = this.getRevCoreInputFormEditText();
        revCoreInputFormEditText.setTextSize(TypedValue.COMPLEX_UNIT_PX, RevLibGenConstantine.REV_TEXT_SIZE_TINY);
        revCoreInputFormEditText.setBackgroundResource(R.drawable.rev_partial_left_right_bottom_border);
        RevCoreLinearLayoutLayoutParams.setEditText_L_P(revCoreInputFormEditText);

        return revCoreInputFormEditText;
    }

    public EditText getRevEditText_FOOTER_COMMENT() {
        revCoreInputFormEditText = this.getRevCoreInputFormEditText();
        revCoreInputFormEditText.setTextSize(TypedValue.COMPLEX_UNIT_PX, revDimens.getRevTextSizeMedium());
        revCoreInputFormEditText.setBackgroundResource(R.drawable.borders_footer_comment_edit_text);
        RevCoreLinearLayoutLayoutParams.setEditText_L_P(revCoreInputFormEditText);

        return revCoreInputFormEditText;
    }

    public EditText getRevEditText_NO_Borders() {
        revCoreInputFormEditText = this.getRevCoreInputFormEditText();
        revCoreInputFormEditText.setTextSize(TypedValue.COMPLEX_UNIT_PX, RevLibGenConstantine.REV_TEXT_SIZE_TINY);

        GradientDrawable border = new GradientDrawable();
        border.setStroke(1, ContextCompat.getColor(mContext, R.color.revPurple));
        border.setGradientType(RECTANGLE);

        Drawable[] layers = {border};
        LayerDrawable layerDrawable = new LayerDrawable(layers);
        layerDrawable.setLayerInset(0, -1, -1, -1, -1);
        REV_VIEWS_BASE_FUNCTIONS.REV_SAFE_SET_BACKGROUND(revCoreInputFormEditText, layerDrawable);

        RevCoreLinearLayoutLayoutParams.setEditText_L_P(revCoreInputFormEditText);

        return revCoreInputFormEditText;
    }

    public EditText getRevEditText_NO_PADDING_RIGHT_BORDER_ONLY() {
        revCoreInputFormEditText = this.getRevEditText_NO_Borders();
        revCoreInputFormEditText.setPadding(0, 0, 0, 0);

        GradientDrawable border = new GradientDrawable();
        border.setStroke(1, ContextCompat.getColor(mContext, R.color.revPurple));
        border.setGradientType(RECTANGLE);

        Drawable[] layers = {border};
        LayerDrawable layerDrawable = new LayerDrawable(layers);
        layerDrawable.setLayerInset(0, -1, -1, 1, -1);
        REV_VIEWS_BASE_FUNCTIONS.REV_SAFE_SET_BACKGROUND(revCoreInputFormEditText, layerDrawable);

        ((LinearLayout.LayoutParams) revCoreInputFormEditText.getLayoutParams()).setMargins(0, 0, 0, 0);

        return revCoreInputFormEditText;
    }

    public EditText getRevEditText_NO_BORDERS_NO_PADDING() {
        revCoreInputFormEditText = this.getRevEditText_NO_Borders();
        revCoreInputFormEditText.setPadding(0, 0, 0, 0);

        GradientDrawable border = new GradientDrawable();
        border.setStroke(1, ContextCompat.getColor(mContext, R.color.revPurple));
        border.setGradientType(RECTANGLE);

        Drawable[] layers = {border};
        LayerDrawable layerDrawable = new LayerDrawable(layers);
        layerDrawable.setLayerInset(0, -1, -1, -1, -1);
        REV_VIEWS_BASE_FUNCTIONS.REV_SAFE_SET_BACKGROUND(revCoreInputFormEditText, layerDrawable);

        ((LinearLayout.LayoutParams) revCoreInputFormEditText.getLayoutParams()).setMargins(0, 0, 0, 0);

        return revCoreInputFormEditText;
    }

    public EditText getRevEditText_NO_BORDERS_NO_PADDING_TINY_SPACING() {
        revCoreInputFormEditText = this.getRevEditText_NO_Borders();
        revCoreInputFormEditText.setPadding((int) (RevLibGenConstantine.REV_MARGIN_SMALL * .75), RevLibGenConstantine.REV_MARGIN_TINY, 0, RevLibGenConstantine.REV_MARGIN_TINY);

        ((LinearLayout.LayoutParams) revCoreInputFormEditText.getLayoutParams()).setMargins(0, RevLibGenConstantine.REV_MARGIN_TINY, 0, RevLibGenConstantine.REV_MARGIN_TINY);

        return revCoreInputFormEditText;
    }

    public static void setCursorDrawableColor(EditText editText, int color) {
        try {
            Field fCursorDrawableRes = TextView.class.getDeclaredField("mCursorDrawableRes");
            fCursorDrawableRes.setAccessible(true);
            int mCursorDrawableRes = fCursorDrawableRes.getInt(editText);
            Field fEditor = TextView.class.getDeclaredField("mEditor");
            fEditor.setAccessible(true);
            Object editor = fEditor.get(editText);
            Class<?> clazz = editor.getClass();
            Field fCursorDrawable = clazz.getDeclaredField("mCursorDrawable");
            fCursorDrawable.setAccessible(true);

            Drawable[] drawables = new Drawable[2];
            Resources res = editText.getContext().getResources();
            drawables[0] = res.getDrawable(mCursorDrawableRes);
            drawables[1] = res.getDrawable(mCursorDrawableRes);
            drawables[0].setColorFilter(color, PorterDuff.Mode.SRC_IN);
            drawables[1].setColorFilter(color, PorterDuff.Mode.SRC_IN);
            fCursorDrawable.set(editor, drawables);
        } catch (final Throwable ignored) {
        }
    }

}
