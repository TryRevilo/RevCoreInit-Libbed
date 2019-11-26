package rev.ca.revlibviews.rev_core_layouts;

import android.util.DisplayMetrics;
import android.widget.EditText;
import android.widget.LinearLayout;

import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.rev_lib_gen_functions.RevViewsBaseFunctions;

/**
 * Created by rev on 10/17/17.
 */

public class RevCoreLinearLayoutLayoutParams {

    public static LinearLayout.LayoutParams getRev_MATCH_ALL_LP() {
        LinearLayout.LayoutParams revLogInFormItemsInputs_LP = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        return revLogInFormItemsInputs_LP;
    }

    public static LinearLayout.LayoutParams getRev_MATCH_W_Wrap_H_LP() {
        LinearLayout.LayoutParams revLogInFormItemsInputs_LP = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        return revLogInFormItemsInputs_LP;
    }

    public static LinearLayout.LayoutParams getRev_WRAP_W_Wrap_H_LP() {
        LinearLayout.LayoutParams revLogInFormItemsInputs_LP = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        revLogInFormItemsInputs_LP.setMargins(22, 22, 22, 22);

        return revLogInFormItemsInputs_LP;
    }

    public static LinearLayout.LayoutParams getRev_WRAP_ALL_LP() {
        LinearLayout.LayoutParams revLogInFormItemsInputs_LP = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        return revLogInFormItemsInputs_LP;
    }

    public static LinearLayout.LayoutParams getRev_WRAP_CONTENT_MATCH_PARENT_LP() {
        LinearLayout.LayoutParams revLogInFormItemsInputs_LP = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
        return revLogInFormItemsInputs_LP;
    }

    public static LinearLayout.LayoutParams getFormTtl_L_P() {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(55, 0, 55, 0);

        return layoutParams;
    }

    public static LinearLayout.LayoutParams getInputFormFooter_LP() {
        LinearLayout.LayoutParams revLogInFormItemsInputs_LP = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        revLogInFormItemsInputs_LP.setMargins(55, 55, 0, 0);
        return revLogInFormItemsInputs_LP;
    }

    public static LinearLayout.LayoutParams getTiles_LP() {
        LinearLayout.LayoutParams revLogInFormItemsInputs_LP = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        return revLogInFormItemsInputs_LP;
    }

    public static LinearLayout.LayoutParams getFormEditText_L_P() {
        LinearLayout.LayoutParams layoutParams = getRev_WRAP_W_Wrap_H_LP();
        layoutParams.setMargins(22, 22, 22, 22);

        return layoutParams;
    }

    public static void setEditText_L_P(EditText editText) {
        editText.setLayoutParams(getFormEditText_L_P());
    }

    public static LinearLayout.LayoutParams getRevDrawerContentContainer_LL_LP() {
        DisplayMetrics dm = RevViewsBaseFunctions.getRevDisplayMetrics();

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        LinearLayout.LayoutParams slideLL_LP = new LinearLayout.LayoutParams(
                (int) (width * .8), height);
        slideLL_LP.setMargins(0, RevLibGenConstantine.REV_TOPBAR_HEIGHT, 0, 0);

        return slideLL_LP;
    }
}
