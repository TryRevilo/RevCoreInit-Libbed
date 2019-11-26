package rev.ca.rev_lib_core_views.rev_view_widgets.rev_core_section_widgets;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

import java.io.File;

import rev.ca.rev_lib_core_views.R;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.revlibviews.REV_VIEWS_BASE_FUNCTIONS;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormTextView;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;

import static android.graphics.drawable.GradientDrawable.RECTANGLE;

/**
 * Created by rev on 2/15/18.
 */

public class RevRelatedPeopleIconsListing {

    private Context mContext;
    private RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout;
    private RevCoreInputFormTextView revCoreInputFormTextView;

    public RevRelatedPeopleIconsListing(Context mContext) {
        this.mContext = mContext;

        revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(mContext);
        revCoreInputFormTextView = new RevCoreInputFormTextView(mContext);
    }

    @SuppressLint("WrongConstant")
    public View relatedPeopleIconPics() {
        LinearLayout peoplesContainer_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();

        LinearLayout linearLayout = revCoreLayoutsLinearLayout.get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();

        int borderWidth = 1;

        GradientDrawable border = new GradientDrawable();
        border.setStroke(borderWidth, mContext.getResources().getColor(R.color.teal_100_dark));
        border.setGradientType(RECTANGLE);

        Drawable[] layers = {border};
        LayerDrawable layerDrawable = new LayerDrawable(layers);
        layerDrawable.setLayerInset(0, borderWidth, borderWidth, borderWidth, borderWidth);

        for (int i = 0; i < 122; i++) {
            LinearLayout imgView_LL = revCoreLayoutsLinearLayout.getHorizontalRevLinearLayout_WRAPPED_ALL();
            String imgPath = "/storage/emulated/0/DCIM/Camera/IMG_20180116_172844.jpg";
            ImageView imageView = new ImageView(mContext);

            int imgSize = (int) (RevLibGenConstantine.REV_IMAGE_SIZE_MEDIUM * .7);

            if (new File(imgPath).exists()) {
                Picasso.get()
                        .load(new File(imgPath))
                        .resize(imgSize, imgSize)
                        .centerCrop()
                        .into(imageView);

                LinearLayout.LayoutParams imageView_LP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                imageView_LP.gravity = Gravity.CENTER_VERTICAL;

                if (i != 0)
                    imageView_LP.setMargins((int) (RevLibGenConstantine.REV_MARGIN_TINY * .4), 0, 0, 0);

                imgView_LL.setLayoutParams(imageView_LP);

                REV_VIEWS_BASE_FUNCTIONS.REV_SAFE_SET_BACKGROUND(imgView_LL, layerDrawable);
                int padding = (int) (RevLibGenConstantine.REV_MARGIN_TINY * .4);
                imgView_LL.setPadding(padding, padding, padding, padding);

                imgView_LL.addView(imageView);
                linearLayout.addView(imgView_LL);
            }
        }

        final HorizontalScrollView scrollView = new HorizontalScrollView(mContext);
        scrollView.setHorizontalScrollBarEnabled(false);

        scrollView.addView(linearLayout);

        ImageView scroller_IV = new ImageView(mContext);

        LinearLayout.LayoutParams imageView_LP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        imageView_LP.gravity = Gravity.CENTER_VERTICAL | Gravity.RIGHT;
        imageView_LP.setMargins(0, 0, RevLibGenConstantine.REV_MARGIN_SMALL, 0);
        scroller_IV.setLayoutParams(imageView_LP);

        peoplesContainer_LL.addView(scrollView);

        return peoplesContainer_LL;
    }
}
