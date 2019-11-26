package rev.ca.rev_lib_core_app_plugins.rev_im_plugin.rev_plugin_views.rev_object;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import com.squareup.picasso.Picasso;

import java.io.File;

import rev.ca.rev_gen_lib_pers.rev_varags_data.REV_SESSION_SETTINGS;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_lib_core_app_plugins.R;
import rev.ca.rev_lib_core_views.AbstractIRevPluggableViews;
import rev.ca.rev_lib_core_views.rev_core_views.rev_activities_window_views.RevPop;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.revlibviews.REV_VIEWS_BASE_FUNCTIONS;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormTextView;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;

import static android.graphics.drawable.GradientDrawable.RECTANGLE;

public class OnlinePlacers extends AbstractIRevPluggableViews {

    private RevVarArgsData revVarArgsData;
    private Context mContext;
    private RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout;

    public OnlinePlacers(RevVarArgsData revVarArgsData) {
        super(revVarArgsData);
        this.revVarArgsData = revVarArgsData;
        this.mContext = this.revVarArgsData.getmContext();

        revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(mContext);

        Activity activity = (Activity) mContext;

        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    @Override
    public View createPluggableRevMainCenterCctView_LL() {

        int imgSize = (int) (RevLibGenConstantine.REV_IMAGE_SIZE_MEDIUM * .7);

        LinearLayout linearLayout = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_WRAP_ALL();
        linearLayout.setBackgroundColor(mContext.getResources().getColor(R.color.revWhite));

        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.BOTTOM | Gravity.RIGHT;
        layoutParams.setMargins(0, 0, (int) (RevLibGenConstantine.REV_MARGIN_MEDIUM * 1.25), RevLibGenConstantine.REV_MARGIN_LARGE);
        linearLayout.setLayoutParams(layoutParams);

        /** TAB **/
        int imageViewBorderSize = 8;

        GradientDrawable imageViewBorderTop = new GradientDrawable();
        imageViewBorderTop.setStroke(imageViewBorderSize, mContext.getResources().getColor(R.color.rcolorAccent_OPAQUE));
        imageViewBorderTop.setColor(ContextCompat.getColor(mContext, R.color.rcolorAccent_OPAQUE));
        imageViewBorderTop.setGradientType(RECTANGLE);

        GradientDrawable imageViewBorder = new GradientDrawable();
        imageViewBorder.setStroke(imageViewBorderSize, mContext.getResources().getColor(R.color.teal_dark));
        imageViewBorder.setColor(ContextCompat.getColor(mContext, R.color.rcolorAccent_OPAQUE));
        imageViewBorder.setGradientType(RECTANGLE);

        Drawable[] imageViewLayers = {imageViewBorderTop, imageViewBorder};
        LayerDrawable imageViewLayerDrawable = new LayerDrawable(imageViewLayers);
        imageViewLayerDrawable.setLayerInset(0, -imageViewBorderSize, -imageViewBorderSize, -imageViewBorderSize, imageViewBorderSize);
        imageViewLayerDrawable.setLayerInset(1, -imageViewBorderSize, -imageViewBorderSize, -imageViewBorderSize, -imageViewBorderSize);

        int mainTagViewOptions_DR_Size = (int) (RevLibGenConstantine.REV_IMAGE_SIZE_MEDIUM * .7);

        Drawable mainTagViewOptions_DR = mContext.getResources().getDrawable(R.drawable.baseline_zoom_out_map_black_48dp);
        mainTagViewOptions_DR.setBounds(0, 0, mainTagViewOptions_DR_Size, mainTagViewOptions_DR_Size);
        DrawableCompat.setTint(mainTagViewOptions_DR, ContextCompat.getColor(mContext, R.color.revWhite));

        int marginH = (int) (RevLibGenConstantine.REV_MARGIN_TINY * .2);

        TextView mainTagViewOptions_TV = new RevCoreInputFormTextView(mContext).getRevExtraSmallNormalTextView();
        mainTagViewOptions_TV.setText("I.M");
        mainTagViewOptions_TV.setTextColor(mContext.getResources().getColor(rev.ca.revlibviews.R.color.revWhite));
        mainTagViewOptions_TV.setCompoundDrawablePadding((int) (RevLibGenConstantine.REV_MARGIN_TINY * .5));
        mainTagViewOptions_TV.setPadding(marginH, RevLibGenConstantine.REV_MARGIN_TINY, marginH, RevLibGenConstantine.REV_MARGIN_TINY);
        mainTagViewOptions_TV.setBackgroundColor(ContextCompat.getColor(mContext, rev.ca.revlibviews.R.color.rcolorAccent_OPAQUE));
        mainTagViewOptions_TV.setGravity(Gravity.CENTER_HORIZONTAL);

        REV_VIEWS_BASE_FUNCTIONS.REV_SAFE_SET_BACKGROUND(mainTagViewOptions_TV, imageViewLayerDrawable);

        LinearLayout.LayoutParams chatSettings_TV_LP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        chatSettings_TV_LP.gravity = Gravity.CENTER_VERTICAL;
        mainTagViewOptions_TV.setLayoutParams(chatSettings_TV_LP);

        mainTagViewOptions_TV.setCompoundDrawables(null, null, null, mainTagViewOptions_DR);

        /** END TAB **/

        linearLayout.addView(mainTagViewOptions_TV);

        /** SCROLL UP **/

        ImageView moreOnline_IV_scrollUp = new ImageView(mContext);
        moreOnline_IV_scrollUp.setColorFilter(mContext.getResources().getColor(R.color.teal_dark));

        GradientDrawable moreOnline_IV_scrollUpBorderTop = new GradientDrawable();
        moreOnline_IV_scrollUpBorderTop.setStroke(imageViewBorderSize, mContext.getResources().getColor(R.color.revWhite));
        moreOnline_IV_scrollUpBorderTop.setColor(ContextCompat.getColor(mContext, R.color.revWhite));
        moreOnline_IV_scrollUpBorderTop.setGradientType(RECTANGLE);

        GradientDrawable moreOnline_IV_scrollUpBorder = new GradientDrawable();
        moreOnline_IV_scrollUpBorder.setStroke(imageViewBorderSize, mContext.getResources().getColor(R.color.teal_dark));
        moreOnline_IV_scrollUpBorder.setColor(ContextCompat.getColor(mContext, R.color.revWhite));
        moreOnline_IV_scrollUpBorder.setGradientType(RECTANGLE);

        Drawable[] moreOnline_IV_scrollUpLayers = {moreOnline_IV_scrollUpBorderTop, moreOnline_IV_scrollUpBorder};
        LayerDrawable moreOnline_IV_scrollUpLayerDrawable = new LayerDrawable(moreOnline_IV_scrollUpLayers);
        moreOnline_IV_scrollUpLayerDrawable.setLayerInset(0, -imageViewBorderSize, imageViewBorderSize, -imageViewBorderSize, -imageViewBorderSize);
        moreOnline_IV_scrollUpLayerDrawable.setLayerInset(1, -imageViewBorderSize, -imageViewBorderSize, -imageViewBorderSize, imageViewBorderSize);

        REV_VIEWS_BASE_FUNCTIONS.REV_SAFE_SET_BACKGROUND(moreOnline_IV_scrollUp, moreOnline_IV_scrollUpLayerDrawable);

        LinearLayout.LayoutParams moreOnline_IV_scrollUp_IV_LP = new LinearLayout.LayoutParams(imgSize, imgSize);
        moreOnline_IV_scrollUp_IV_LP.setMargins(0, 0, 0, 0);
        moreOnline_IV_scrollUp_IV_LP.gravity = Gravity.CENTER_HORIZONTAL;
        moreOnline_IV_scrollUp.setLayoutParams(moreOnline_IV_scrollUp_IV_LP);

        Picasso.get()
                .load(R.drawable.baseline_keyboard_arrow_up_black_48dp)
                .resize(RevLibGenConstantine.REV_IMAGE_SIZE_SMALL, RevLibGenConstantine.REV_IMAGE_SIZE_SMALL)
                .centerCrop()
                .into(moreOnline_IV_scrollUp);

        linearLayout.addView(moreOnline_IV_scrollUp);

        /** END SCROLL UP **/

        final ScrollView scrollView = new ScrollView(mContext);
        scrollView.setVerticalScrollBarEnabled(false);

        LinearLayout.LayoutParams scrollView_LP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, imgSize * 8);
        scrollView.setLayoutParams(scrollView_LP);

        LinearLayout onlineContainer = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_WRAP_ALL();
        ((LinearLayout.LayoutParams) onlineContainer.getLayoutParams()).gravity = Gravity.CENTER_VERTICAL;

        for (int i = 0; i < 22; i++) {
            LinearLayout imgView_LL = revCoreLayoutsLinearLayout.getHorizontalRevLinearLayout_WRAPPED_ALL();
            String imgPath = "/storage/emulated/0/DCIM/Camera/IMG_20180116_172844.jpg";
            ImageView imageView = new ImageView(mContext);

            if (new File(imgPath).exists()) {
                Picasso.get()
                        .load(new File(imgPath))
                        .resize(imgSize, imgSize)
                        .centerCrop()
                        .into(imageView);

                LinearLayout.LayoutParams imageView_LP = (LinearLayout.LayoutParams) imgView_LL.getLayoutParams();
                imageView_LP.setMargins(0, 0, 0, 1);
                imageView_LP.gravity = Gravity.CENTER_VERTICAL;
                imgView_LL.setLayoutParams(imageView_LP);

                imgView_LL.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        RevPop.initiatePopupWindow_MINIMAL_TOP_MATCH_WIDTH(new RevIMFullEntityView(REV_SESSION_SETTINGS.getRevCurrentPageRevVarArgsData()).getRevIMFullEntityView());
                    }
                });

                imgView_LL.addView(imageView);
                onlineContainer.addView(imgView_LL);
            }
        }

        scrollView.addView(onlineContainer);

        linearLayout.addView(scrollView);

        /** SCROLL DOWN **/

        ImageView moreOnline_IV_scrollDown = new ImageView(mContext);
        moreOnline_IV_scrollDown.setColorFilter(mContext.getResources().getColor(R.color.teal_dark));

        GradientDrawable moreImageViewBorderTop = new GradientDrawable();
        moreImageViewBorderTop.setStroke(imageViewBorderSize, mContext.getResources().getColor(R.color.revWhite));
        moreImageViewBorderTop.setColor(ContextCompat.getColor(mContext, R.color.revWhite));
        moreImageViewBorderTop.setGradientType(RECTANGLE);

        GradientDrawable moreImageViewBorder = new GradientDrawable();
        moreImageViewBorder.setStroke(imageViewBorderSize, mContext.getResources().getColor(R.color.teal_dark));
        moreImageViewBorder.setColor(ContextCompat.getColor(mContext, R.color.revWhite));
        moreImageViewBorder.setGradientType(RECTANGLE);

        Drawable[] moreImageViewLayers = {moreImageViewBorderTop, moreImageViewBorder};
        LayerDrawable moreImageViewLayerDrawable = new LayerDrawable(moreImageViewLayers);
        moreImageViewLayerDrawable.setLayerInset(0, -imageViewBorderSize, imageViewBorderSize, -imageViewBorderSize, -imageViewBorderSize);
        moreImageViewLayerDrawable.setLayerInset(1, -imageViewBorderSize, -imageViewBorderSize, -imageViewBorderSize, imageViewBorderSize);

        REV_VIEWS_BASE_FUNCTIONS.REV_SAFE_SET_BACKGROUND(moreOnline_IV_scrollDown, moreImageViewLayerDrawable);

        LinearLayout.LayoutParams moreOnline_IV_LP = new LinearLayout.LayoutParams(imgSize, imgSize);
        moreOnline_IV_LP.setMargins(0, 0, 0, 0);
        moreOnline_IV_LP.gravity = Gravity.CENTER_HORIZONTAL;
        moreOnline_IV_scrollDown.setLayoutParams(moreOnline_IV_LP);

        Picasso.get()
                .load(R.drawable.baseline_keyboard_arrow_down_black_48dp)
                .resize(RevLibGenConstantine.REV_IMAGE_SIZE_SMALL, RevLibGenConstantine.REV_IMAGE_SIZE_SMALL)
                .centerCrop()
                .into(moreOnline_IV_scrollDown);

        linearLayout.addView(moreOnline_IV_scrollDown);

        /** END SCROLL DOWN **/

        return linearLayout;
    }
}
