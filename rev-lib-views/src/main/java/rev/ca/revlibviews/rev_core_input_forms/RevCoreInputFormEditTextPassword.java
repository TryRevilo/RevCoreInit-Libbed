package rev.ca.revlibviews.rev_core_input_forms;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import androidx.core.content.ContextCompat;
import android.text.InputType;
import android.util.TypedValue;
import android.widget.EditText;

import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.revlibviews.R;
import rev.ca.revlibviews.REV_VIEWS_BASE_FUNCTIONS;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLinearLayoutLayoutParams;

import static android.graphics.drawable.GradientDrawable.RECTANGLE;

/**
 * Created by rev on 10/17/17.
 */

public class RevCoreInputFormEditTextPassword {

    private Context mContext;

    private EditText revCoreInputFormEditTextPassword;

    public RevCoreInputFormEditTextPassword(Context context) {
        mContext = context;
    }

    public EditText getRevCoreInputFormEditText() {
        revCoreInputFormEditTextPassword = new EditText(mContext);
        revCoreInputFormEditTextPassword.setTextSize(TypedValue.COMPLEX_UNIT_PX, RevLibGenConstantine.REV_TEXT_SIZE_TINY);
        revCoreInputFormEditTextPassword.setTextColor(mContext.getResources().getColor(R.color.darkText));

        revCoreInputFormEditTextPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        revCoreInputFormEditTextPassword.setSelection(revCoreInputFormEditTextPassword.getText().length());

        return revCoreInputFormEditTextPassword;
    }

    public EditText getRevPasswordEditTextPass_ONLY_BottomBorders() {
        revCoreInputFormEditTextPassword = this.getRevCoreInputFormEditText();

        RevCoreLinearLayoutLayoutParams.setEditText_L_P(revCoreInputFormEditTextPassword);

        GradientDrawable border = new GradientDrawable();
        border.setStroke(1, ContextCompat.getColor(mContext, R.color.revPurple));
        border.setGradientType(RECTANGLE);

        Drawable[] layers = {border};
        LayerDrawable layerDrawable = new LayerDrawable(layers);
        layerDrawable.setLayerInset(0, -1, -1, -1, 1);
        REV_VIEWS_BASE_FUNCTIONS.REV_SAFE_SET_BACKGROUND(revCoreInputFormEditTextPassword, layerDrawable);

        return revCoreInputFormEditTextPassword;
    }

    public EditText getRevEditText_NO_Borders() {
        revCoreInputFormEditTextPassword = this.getRevCoreInputFormEditText();
        revCoreInputFormEditTextPassword.setTextSize(TypedValue.COMPLEX_UNIT_PX, RevLibGenConstantine.REV_TEXT_SIZE_TINY);

        GradientDrawable border = new GradientDrawable();
        border.setStroke(1, ContextCompat.getColor(mContext, R.color.revPurple));
        border.setGradientType(RECTANGLE);

        Drawable[] layers = {border};
        LayerDrawable layerDrawable = new LayerDrawable(layers);
        layerDrawable.setLayerInset(0, -1, -1, -1, -1);
        REV_VIEWS_BASE_FUNCTIONS.REV_SAFE_SET_BACKGROUND(revCoreInputFormEditTextPassword, layerDrawable);

        RevCoreLinearLayoutLayoutParams.setEditText_L_P(revCoreInputFormEditTextPassword);

        return revCoreInputFormEditTextPassword;
    }
}
