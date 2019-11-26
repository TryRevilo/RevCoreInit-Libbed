package rev.ca.rev_lib_core_app_plugins.rev_kiwi_plugin.rev_plugin_views.rev_pluggable_menues;

import android.content.Context;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_menues.ICreateRevPluggableOptionsMenu;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_menues.RevPluggableOptionsMenuVM;
import rev.ca.rev_lib_gen_functions.RevDimens;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormTextView;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;

/**
 * Created by rev on 1/19/18.
 */

public class RevGenPluggableOptionsMenuVMPublisher implements ICreateRevPluggableOptionsMenu {

    private Context mContext;

    private RevDimens revDimens;

    private RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout;
    private RevCoreInputFormTextView revCoreInputFormTextView;

    public RevGenPluggableOptionsMenuVMPublisher(Context mContext) {
        this.mContext = mContext;

        revDimens = new RevDimens(mContext);

        revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(mContext);
        revCoreInputFormTextView = new RevCoreInputFormTextView(mContext);
    }

    @Override
    public RevPluggableOptionsMenuVM revCreateRevPluggableOptionsMenue() {
        final LinearLayout linearLayout = revCoreLayoutsLinearLayout.getHorizontalRevLinearLayout_WRAPPED_ALL();

        ImageView imageView = new ImageView(mContext);
        imageView.setPadding(0, 0, 0, 0);
        imageView.setImageResource(rev.ca.rev_lib_core_views.R.drawable.ic_publish_black_48dp);
        imageView.setAdjustViewBounds(true);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(revDimens.getRevImageSizeMedium(), revDimens.getRevImageSizeMedium());
        layoutParams.gravity = (Gravity.TOP);
        imageView.setLayoutParams(layoutParams);

        TextView textView = revCoreInputFormTextView.getRevExtraSmallNormalTextView_NO_PADDING();
        textView.setText("Publish");

        LinearLayout.LayoutParams textView_LP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textView_LP.gravity = (Gravity.BOTTOM);
        textView_LP.setMargins((int) (-revDimens.getRevMarginExtraSmall() * 1.4), 0, 0, (int) (revDimens.getRevMarginExtraSmall() * .5));
        textView.setLayoutParams(textView_LP);

        linearLayout.addView(imageView);
        linearLayout.addView(textView);

        RevPluggableOptionsMenuVM revPluggableOptionsMenuVM = new RevPluggableOptionsMenuVM();
        revPluggableOptionsMenuVM.setRevPluggableOptionsMenueName("rev_core_gen_publisher_options_menu");
        revPluggableOptionsMenuVM.setRevPluggableOptionsMenueView(linearLayout);
        revPluggableOptionsMenuVM.setMenuItemsViewType("drop_down");

        return revPluggableOptionsMenuVM;
    }
}
