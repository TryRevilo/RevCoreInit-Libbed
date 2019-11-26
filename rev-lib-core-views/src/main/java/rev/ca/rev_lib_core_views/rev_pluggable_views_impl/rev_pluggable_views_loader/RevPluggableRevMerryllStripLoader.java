package rev.ca.rev_lib_core_views.rev_pluggable_views_impl.rev_pluggable_views_loader;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.ArrayMap;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import rev.ca.rev_lib_core_views.IRevPluggableViews;
import rev.ca.rev_lib_core_views.R;
import rev.ca.rev_lib_core_views.rev_pluggable_views_impl.RevPluggableServices;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.revlibviews.REV_VIEWS_BASE_FUNCTIONS;
import rev.ca.revlibviews.rev_core_layouts.RevCoreFrameLayoutLayoutParams;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsFrameLayouts;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;

/**
 * Created by rev on 10/28/17.
 */

public class RevPluggableRevMerryllStripLoader {

    public static View revLoadRevMerryllStripTabs() {

        final Context mContext = RevLibGenConstantine.REV_CONTEXT;

        RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(mContext);

        final FrameLayout frameLayout = new RevCoreLayoutsFrameLayouts(mContext).getRevCoreLayoutsFrameLayout();
        FrameLayout.LayoutParams layoutParams = RevCoreFrameLayoutLayoutParams.getRev_WRAP_CONTENT_MATCH_PARENT_LP();
        layoutParams.setMargins(0, RevLibGenConstantine.REV_TOPBAR_HEIGHT, RevLibGenConstantine.REV_MARGIN_SMALL, 0);
        layoutParams.gravity = Gravity.TOP | Gravity.RIGHT;
        frameLayout.setLayoutParams(layoutParams);

        final LinearLayout linearLayoutContainer_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout();

        LinearLayout topDStrip_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout();
        topDStrip_LL.setBackgroundColor(mContext.getResources().getColor(R.color.deep_purple_light));
        FrameLayout.LayoutParams topDStrip_LL_LP = RevCoreFrameLayoutLayoutParams.getRev_WRAP_CONTENT_MATCH_PARENT_LP();
        topDStrip_LL_LP.width = (int) (RevLibGenConstantine.REV_MARGIN_SMALL * .2);
        topDStrip_LL_LP.gravity = Gravity.CENTER_HORIZONTAL;

        topDStrip_LL.setLayoutParams(topDStrip_LL_LP);
        frameLayout.addView(topDStrip_LL);

        RevVarArgsData revVarArgsData = new RevVarArgsData(mContext);
        revVarArgsData.setRevViewType(mContext.getResources().getString(R.string.createRevMerryllStripMenuViewItem));

        for (Object revPluggableViewObject : RevPluggableServices.getIRevPluggableViewsObjects(revVarArgsData)) {
            IRevPluggableViews revPluggableView = (IRevPluggableViews) revPluggableViewObject;

            if (revPluggableView instanceof IRevPluggableViews) {
                ArrayMap<View, View> revAltViews = revPluggableView.createRevMerryllStripMenuViewItem();

                for (int i = 0; i < revAltViews.size(); i++) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        if (revAltViews.keyAt(i) != null) {
                            final View key = revAltViews.keyAt(i);
                            final View value = revAltViews.valueAt(i);
                            value.setId(i);

                            linearLayoutContainer_LL.addView(key);

                            Boolean aBoolean = true;

                            RevVarArgsData argsData = revPluggableView.createRevVarArgsData();

                            if (argsData.getRevVarArgsDataMetadataStrings().get("isChildable") != null) {
                                aBoolean = (Boolean) argsData.getRevVarArgsDataMetadataStrings().get("isChildable");
                            }

                            if (aBoolean) {

                                key.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        value.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
                                        Activity activity = (Activity) RevLibGenConstantine.REV_CONTEXT;

                                        value.setBackgroundColor(value.getContext().getResources().getColor(rev.ca.revlibviews.R.color.revWhite));

                                        DisplayMetrics dm = new DisplayMetrics();
                                        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);

                                        int width = dm.widthPixels;

                                        REV_VIEWS_BASE_FUNCTIONS.revRemoveParentView(value);

                                        PopupWindow pw = new PopupWindow(value, (int) (width * .8), FrameLayout.LayoutParams.WRAP_CONTENT, true);
                                        int[] location = new int[2];
                                        key.getLocationOnScreen(location);
                                        key.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);

                                        pw.showAtLocation(key, Gravity.NO_GRAVITY, 0, (int) (location[1] - RevLibGenConstantine.REV_MARGIN_SMALL * .8));

                                        View container = (View) pw.getContentView().getParent();
                                        WindowManager wm = (WindowManager) activity.getSystemService(value.getContext().WINDOW_SERVICE);
                                        WindowManager.LayoutParams p = (WindowManager.LayoutParams) container.getLayoutParams();

                                        // add flag
                                        p.flags |= WindowManager.LayoutParams.FLAG_DIM_BEHIND;
                                        p.dimAmount = 0.5f;
                                        wm.updateViewLayout(container, p);
                                    }
                                });
                            }
                        }
                    }
                }
            }
        }

        if (linearLayoutContainer_LL != null) {
            frameLayout.addView(linearLayoutContainer_LL);
            return frameLayout;
        }

        return null;
    }
}
