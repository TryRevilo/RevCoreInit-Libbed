package rev.ca.revlibviews.rev_core_input_forms;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import androidx.core.content.ContextCompat;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.revlibviews.R;

/**
 * Created by rev on 10/17/17.
 */

public class RevCoreInputFormButton {

    private Context mContext;

    private Button button;
    private int paddingH, paddingV;

    public RevCoreInputFormButton(Context context) {
        this.mContext = context;

        paddingH = RevLibGenConstantine.REV_MARGIN_LARGE;
        paddingV = RevLibGenConstantine.REV_MARGIN_TINY;
    }

    private Button getButton() {
        LinearLayout.LayoutParams tab_LP = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        button = new Button(mContext);
        button.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.font_size_small));
        button.setTextColor(mContext.getResources().getColor(R.color.revWhite));
        button.setBackgroundColor(Color.TRANSPARENT);

        button.setPadding(paddingH, paddingV, paddingH, paddingV);
        button.setLayoutParams(tab_LP);

        return button;
    }

    private Button getButton_EVEN_ALL() {
        LinearLayout.LayoutParams tab_LP = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, RevLibGenConstantine.REV_DIMENS.getRevButtonHeightSmall());

        button = new Button(mContext);
        button.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.font_size_small));
        button.setTextColor(mContext.getResources().getColor(R.color.revWhite));
        button.setBackgroundColor(Color.TRANSPARENT);

        button.setPadding(paddingV, paddingV, paddingV, paddingV);
        button.setLayoutParams(tab_LP);

        return button;
    }

    public Button getButtonPlain() {
        Button button = this.getButton();
        button.setPadding(0, 0, 0, 0);
        return button;
    }

    public Button getFormInputSubmitButton_SMALL_EVEN_ALL() {
        button = this.getButton_EVEN_ALL();
        button.setTextColor(mContext.getResources().getColor(R.color.revWhite));

        // Use a GradientDrawable with only one color set, to make it a solid color
        GradientDrawable border = new GradientDrawable();
        border.setColor(ContextCompat.getColor(mContext, R.color.lime_primary_OPAQUE)); //white background
        border.setStroke(0, ContextCompat.getColor(mContext, R.color.darkText)); // black border with full opacity
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            button.setBackgroundDrawable(border);
        } else {
            button.setBackground(border);
        }

        return button;
    }

    public Button getFormInputSubmitButton_BRIGHT() {
        button = this.getButton();
        button.setTextColor(mContext.getResources().getColor(R.color.revWhite));

        // Use a GradientDrawable with only one color set, to make it a solid color
        GradientDrawable border = new GradientDrawable();
        border.setColor(ContextCompat.getColor(mContext, R.color.ight_green_light)); //white background
        border.setStroke(0, ContextCompat.getColor(mContext, R.color.darkText)); // black border with full opacity
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            button.setBackgroundDrawable(border);
        } else {
            button.setBackground(border);
        }

        return button;
    }

    public Button getFormInputSubmitButton() {
        button = this.getButton();

        // Use a GradientDrawable with only one color set, to make it a solid color
        GradientDrawable border = new GradientDrawable();
        border.setColor(ContextCompat.getColor(mContext, R.color.light_green_primary)); //white background
        border.setStroke(0, ContextCompat.getColor(mContext, R.color.darkText)); // black border with full opacity
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            button.setBackgroundDrawable(border);
        } else {
            button.setBackground(border);
        }

        return button;
    }

    public Button getFormInputCancelButton(final PopupWindow popupWindow) {
        button = this.getButton();
        button.setTextColor(mContext.getResources().getColor(R.color.revWhite));

        // Use a GradientDrawable with only one color set, to make it a solid color
        GradientDrawable border = new GradientDrawable();
        border.setColor(ContextCompat.getColor(mContext, R.color.ight_green_light)); //white background
        border.setStroke(0, ContextCompat.getColor(mContext, R.color.darkText)); // black border with full opacity
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            button.setBackgroundDrawable(border);
        } else {
            button.setBackground(border);
        }

        return button;
    }
}
