package rev.ca.rev_lib_core_app_plugins.rev_file_plugin.rev_plugin_views.rev_plugin_views_overrides;

import android.content.Context;
import androidx.core.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.revlibviews.REV_VIEWS_BASE_FUNCTIONS;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;

public class RevGenericSelectedItemsView {

    private Context mContext;

    RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout;

    public RevGenericSelectedItemsView(RevVarArgsData revVarArgsData) {
        this.mContext = revVarArgsData.getmContext();
        revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(mContext);
    }

    public View revSelectedItemsView(List<String> selectedItems) {
        final LinearLayout someProfileNetworkPipsWrapper = revCoreLayoutsLinearLayout.get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        ((LinearLayout.LayoutParams) someProfileNetworkPipsWrapper.getLayoutParams()).setMargins(0, RevLibGenConstantine.REV_MARGIN_TINY, 0, 0);

        final HorizontalScrollView scrollView = new HorizontalScrollView(mContext);
        scrollView.setHorizontalScrollBarEnabled(false);

        LinearLayout linearLayout = revCoreLayoutsLinearLayout.getHorizontalRevLinearLayout_WRAPPED_ALL();

        int imgSize = (int) (RevLibGenConstantine.REV_IMAGE_SIZE_SMALL * 1.4);

        for (int i = 0; i < selectedItems.size(); i++) {
            final String imgPath = selectedItems.get(i);
            File revImgFile = new File(imgPath);

            if (revImgFile.exists()) {
                ImageView imageView = new ImageView(mContext);
                REV_VIEWS_BASE_FUNCTIONS.CENTER_VIEW_VERTICALLY(imageView);

                Picasso.get()
                        .load(revImgFile)
                        .resize(RevLibGenConstantine.REV_IMAGE_SIZE_MEDIUM, RevLibGenConstantine.REV_IMAGE_SIZE_MEDIUM)
                        .centerCrop()
                        .into(imageView);

                linearLayout.addView(imageView);
            }
        }

        ImageView leftScroll_IV = new ImageView(mContext);
        leftScroll_IV.setColorFilter(ContextCompat.getColor(mContext, rev.ca.rev_lib_core_views.R.color.revExtraLightGreen_OPAQUE));
        leftScroll_IV.setPadding(0, 0, 1, 0);
        REV_VIEWS_BASE_FUNCTIONS.CENTER_VIEW_VERTICALLY(leftScroll_IV);

        Picasso.get()
                .load(rev.ca.rev_lib_core_views.R.drawable.ic_chevron_left_black_48dp)
                .resize(imgSize, imgSize)
                .centerCrop()
                .into(leftScroll_IV);

        ImageView rightScroll_IV = new ImageView(mContext);
        rightScroll_IV.setColorFilter(ContextCompat.getColor(mContext, rev.ca.rev_lib_core_views.R.color.revExtraLightGreen_OPAQUE));
        rightScroll_IV.setPadding(1, 0, 0, 0);
        REV_VIEWS_BASE_FUNCTIONS.CENTER_VIEW_VERTICALLY(rightScroll_IV);

        Picasso.get()
                .load(rev.ca.rev_lib_core_views.R.drawable.ic_chevron_right_black_48dp)
                .resize(imgSize, imgSize)
                .centerCrop()
                .into(rightScroll_IV);

        scrollView.addView(linearLayout);

        someProfileNetworkPipsWrapper.addView(leftScroll_IV);
        someProfileNetworkPipsWrapper.addView(scrollView);
        someProfileNetworkPipsWrapper.addView(REV_VIEWS_BASE_FUNCTIONS.REV_SPACER());
        someProfileNetworkPipsWrapper.addView(rightScroll_IV);

        ViewTreeObserver observer = someProfileNetworkPipsWrapper.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                LinearLayout.LayoutParams scrollView_LP = new LinearLayout.LayoutParams(
                        (int) (someProfileNetworkPipsWrapper.getWidth() - (RevLibGenConstantine.REV_IMAGE_SIZE_MEDIUM * 1.5)), ViewGroup.LayoutParams.WRAP_CONTENT);
                scrollView.setLayoutParams(scrollView_LP);
                someProfileNetworkPipsWrapper.getViewTreeObserver().removeGlobalOnLayoutListener(
                        this);
            }
        });

        return someProfileNetworkPipsWrapper;
    }
}
