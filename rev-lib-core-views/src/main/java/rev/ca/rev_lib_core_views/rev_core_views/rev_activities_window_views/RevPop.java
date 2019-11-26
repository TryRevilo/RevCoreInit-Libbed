package rev.ca.rev_lib_core_views.rev_core_views.rev_activities_window_views;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import androidx.core.content.ContextCompat;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import java.util.ArrayList;
import java.util.List;

import rev.ca.rev_lib_core_views.R;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.revlibviews.REV_VIEWS_BASE_FUNCTIONS;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;

import static android.graphics.drawable.GradientDrawable.RECTANGLE;

/**
 * Created by rev on 10/26/17.
 */

public class RevPop {

    public static List<PopupWindow> REV_POPUP_WIN_LIST = new ArrayList<>();

    public static void closerevPopUpWin(int revPopPos) {
        REV_POPUP_WIN_LIST.get(revPopPos - 1).dismiss();
        RevPop.REV_POPUP_WIN_LIST.remove(RevPop.REV_POPUP_WIN_LIST.size() - 1);
    }

    public static void initiatePopupWindow_MATCH_WIDTH(View view) {
        Activity activity = (Activity) view.getContext();

        view.setBackgroundColor(view.getContext().getResources().getColor(rev.ca.revlibviews.R.color.revWhite));

        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;

        android.view.ViewGroup.LayoutParams view_LP = view.getLayoutParams();
        view_LP.height = android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

        view_LP.height = android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
        PopupWindow pw = new PopupWindow(view, width, view_LP.height, true);
        pw.showAtLocation(view, Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL, 0, 0);

        View container = (View) pw.getContentView().getParent();
        WindowManager wm = (WindowManager) activity.getSystemService(view.getContext().WINDOW_SERVICE);
        WindowManager.LayoutParams p = (WindowManager.LayoutParams) container.getLayoutParams();

        // add flag
        p.flags |= WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        p.dimAmount = 0.5f;
        wm.updateViewLayout(container, p);

        if (REV_POPUP_WIN_LIST == null) REV_POPUP_WIN_LIST = new ArrayList<>();
        REV_POPUP_WIN_LIST.add(pw);
    }

    public static void initiatePopupWindow_MINIMAL_TOP_MATCH_WIDTH(View view) {
        Activity activity = (Activity) view.getContext();

        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;

        android.view.ViewGroup.LayoutParams view_LP = view.getLayoutParams();
        view_LP.height = android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

        LinearLayout revPopItemsContainer = new RevCoreLayoutsLinearLayout(activity).getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        revPopItemsContainer.addView(view);

        int revMarginSmall = RevLibGenConstantine.REV_MARGIN_SMALL;

        revPopItemsContainer.setPadding((int) (revMarginSmall * 1.3), revMarginSmall, revMarginSmall, revMarginSmall);
        revPopItemsContainer.setBackgroundColor(activity.getResources().getColor(R.color.rcolorAccent_OPAQUE));

        GradientDrawable border = new GradientDrawable();
        border.setStroke(5, activity.getResources().getColor(R.color.white_OPAQUE));
        border.setGradientType(RECTANGLE);

        Drawable[] layers = {border};
        LayerDrawable layerDrawable = new LayerDrawable(layers);
        layerDrawable.setLayerInset(0, 5, 5, 5, 5);

        view_LP.height = android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
        PopupWindow pw = new PopupWindow(revPopItemsContainer, (int) (width * .9), view_LP.height, true);
        pw.showAtLocation(view, (Gravity.TOP | Gravity.CENTER_HORIZONTAL), 0, (int) (RevLibGenConstantine.REV_TOPBAR_HEIGHT * 1.38));

        View container = (View) pw.getContentView().getParent();
        WindowManager wm = (WindowManager) activity.getSystemService(view.getContext().WINDOW_SERVICE);
        WindowManager.LayoutParams p = (WindowManager.LayoutParams) container.getLayoutParams();

        // add flag
        p.flags |= WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        p.dimAmount = 0.5f;
        wm.updateViewLayout(container, p);

        if (REV_POPUP_WIN_LIST == null) REV_POPUP_WIN_LIST = new ArrayList<>();
        REV_POPUP_WIN_LIST.add(pw);

    }

    public void initiatePopupWindow(View view) {
        Activity activity = (Activity) view.getContext();

        GradientDrawable revCurvedBorderBackground_GD = new GradientDrawable();
        revCurvedBorderBackground_GD.setStroke(1, ContextCompat.getColor(activity, rev.ca.rev_lib_core_views.R.color.revPurple));
        revCurvedBorderBackground_GD.setColor(ContextCompat.getColor(activity, R.color.revWhite));
        revCurvedBorderBackground_GD.setCornerRadii(new float[]{10, 10, 10, 10, 10, 10, 10, 10});
        revCurvedBorderBackground_GD.setGradientType(RECTANGLE);

        Drawable[] revCurvedBorderBackground_GD_Layers = {revCurvedBorderBackground_GD};
        LayerDrawable searchTab_IV_LL_ayerDrawable = new LayerDrawable(revCurvedBorderBackground_GD_Layers);
        searchTab_IV_LL_ayerDrawable.setLayerInset(0, 1, 1, -1, -1);
        REV_VIEWS_BASE_FUNCTIONS.REV_SAFE_SET_BACKGROUND(view, searchTab_IV_LL_ayerDrawable);

        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;

        android.view.ViewGroup.LayoutParams view_LP = view.getLayoutParams();
        view_LP.height = android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

        PopupWindow pw = new PopupWindow(view, (int) (width * .778), view_LP.height, true);
        pw.showAtLocation(view, Gravity.TOP | Gravity.LEFT, (int) (RevLibGenConstantine.REV_MARGIN_MEDIUM * .578), (int) (RevLibGenConstantine.REV_TOPBAR_HEIGHT * 3.1));

        View container = (View) pw.getContentView().getParent();
        WindowManager wm = (WindowManager) activity.getSystemService(activity.WINDOW_SERVICE);
        WindowManager.LayoutParams p = (WindowManager.LayoutParams) container.getLayoutParams();

        // add flag
        p.flags |= WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        p.dimAmount = 0.5f;
        wm.updateViewLayout(container, p);

        if (REV_POPUP_WIN_LIST == null) REV_POPUP_WIN_LIST = new ArrayList<>();
        REV_POPUP_WIN_LIST.add(pw);
    }

    public void initiatePopupWindow_ClearBackground(View view) {
        Activity activity = (Activity) view.getContext();

        GradientDrawable revCurvedBorderBackground_GD = new GradientDrawable();
        revCurvedBorderBackground_GD.setStroke(0, ContextCompat.getColor(activity, R.color.colorTransparent));
        revCurvedBorderBackground_GD.setCornerRadii(new float[]{10, 10, 10, 10, 10, 10, 10, 10});
        revCurvedBorderBackground_GD.setGradientType(RECTANGLE);

        Drawable[] revCurvedBorderBackground_GD_Layers = {revCurvedBorderBackground_GD};
        LayerDrawable searchTab_IV_LL_ayerDrawable = new LayerDrawable(revCurvedBorderBackground_GD_Layers);
        searchTab_IV_LL_ayerDrawable.setLayerInset(0, -1, -1, -1, -1);
        REV_VIEWS_BASE_FUNCTIONS.REV_SAFE_SET_BACKGROUND(view, searchTab_IV_LL_ayerDrawable);

        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;

        android.view.ViewGroup.LayoutParams view_LP = view.getLayoutParams();
        view_LP.height = android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

        PopupWindow pw = new PopupWindow(view, (int) (width * .773), view_LP.height, true);
        pw.showAtLocation(view, Gravity.TOP | Gravity.LEFT, (int) (RevLibGenConstantine.REV_MARGIN_MEDIUM * .645), (int) (RevLibGenConstantine.REV_TOPBAR_HEIGHT * 3.1));

        View container = (View) pw.getContentView().getParent();
        WindowManager wm = (WindowManager) activity.getSystemService(activity.WINDOW_SERVICE);
        WindowManager.LayoutParams p = (WindowManager.LayoutParams) container.getLayoutParams();

        // add flag
        p.flags |= WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        p.dimAmount = 0.5f;
        wm.updateViewLayout(container, p);

        if (REV_POPUP_WIN_LIST == null) REV_POPUP_WIN_LIST = new ArrayList<>();
        REV_POPUP_WIN_LIST.add(pw);
    }

    public static PopupWindow getPw() {
        return REV_POPUP_WIN_LIST.get(REV_POPUP_WIN_LIST.size() - 1);
    }
}