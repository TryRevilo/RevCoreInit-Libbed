package rev.ca.rev_lib_core_views.rev_core_views.rev_page.rev_page_menue_togglers;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import com.squareup.picasso.Picasso;

import rev.ca.rev_lib_core_views.R;
import rev.ca.rev_lib_core_views.REV_CORE_VIEWS_REFACTORING_BASE_FUNCTIONS;
import rev.ca.rev_lib_core_views.rev_pluggable_views_impl.rev_pluggable_views_loader.RevEntityListingOptionsTogglerMenuViewLoader;
import rev.ca.rev_lib_core_views.rev_pluggable_views_impl.rev_pluggable_views_loader.RevPluggableCenterMainContentViewLoader;
import rev.ca.rev_lib_gen_functions.RevDimens;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.revlibviews.REV_VIEWS_BASE_FUNCTIONS;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormTextView;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;

import static android.graphics.drawable.GradientDrawable.RECTANGLE;

/**
 * Created by rev on 12/13/17.
 */

public class RevObjectListingOptionsTogglerMenu {

    private static Context mContext;

    private static RevDimens revDimens;

    static int imageSize;

    private static RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout;
    private static RevCoreInputFormTextView revCoreInputFormTextView;

    public static void initRevObjectListingOptionsTogglerMenu() {
        mContext = RevLibGenConstantine.REV_CONTEXT;
        revDimens = RevLibGenConstantine.REV_DIMENS;
        revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(mContext);
        revCoreInputFormTextView = new RevCoreInputFormTextView(mContext);

        imageSize = revDimens.getRevImageSizeExtraSmall();
    }

    public static View getRevObjectListingOptionsTogglerMenu() {
        initRevObjectListingOptionsTogglerMenu();

        LinearLayout revObjectListingOptionsTogglerMenuWrapper_LL = revCoreLayoutsLinearLayout.getHorizontalRevLinearLayout_WRAPPED_ALL();
        revObjectListingOptionsTogglerMenuWrapper_LL.setPadding(RevLibGenConstantine.REV_MARGIN_SMALL, RevLibGenConstantine.REV_MARGIN_SMALL, 0, 0);

        GradientDrawable border = new GradientDrawable();
        border.setStroke(1, ContextCompat.getColor(mContext, R.color.revPurple));
        border.setGradientType(RECTANGLE);

        Drawable[] layers = {border};
        LayerDrawable layerDrawable = new LayerDrawable(layers);
        layerDrawable.setLayerInset(0, -1, -1, 1, 1);
        REV_VIEWS_BASE_FUNCTIONS.REV_SAFE_SET_BACKGROUND(revObjectListingOptionsTogglerMenuWrapper_LL, layerDrawable);

        // revObjectListingOptionsTogglerMenuWrapper_LL.addView(allHistoryTab());
        revObjectListingOptionsTogglerMenuWrapper_LL.addView(backTab());

        View space = new View(mContext);

        // Width:0dp, Height:1 & Weight: 1.0
        LinearLayout.LayoutParams spaceLP = new LinearLayout.LayoutParams(0, 1, 1.0f);
        space.setLayoutParams(spaceLP);

        // revObjectListingOptionsTogglerMenuWrapper_LL.addView(space);

        revObjectListingOptionsTogglerMenuWrapper_LL.addView(RevEntityListingOptionsTogglerMenuViewLoader.createRevEntityListingOptionsTogglerMenuView());
        revObjectListingOptionsTogglerMenuWrapper_LL.addView(findTab());

        return revObjectListingOptionsTogglerMenuWrapper_LL;
    }

    private static View findTab() {

        LinearLayout searchTab_IV_LL = revCoreLayoutsLinearLayout.getHorizontalRevLinearLayout_WRAPPED_ALL();
        searchTab_IV_LL.setPadding(0, 0, RevLibGenConstantine.REV_MARGIN_TINY, 0);

        GradientDrawable searchTab_IV_LL_border = new GradientDrawable();
        searchTab_IV_LL_border.setStroke(1, ContextCompat.getColor(mContext, R.color.revPurple));
        searchTab_IV_LL_border.setCornerRadii(new float[]{0, 0, 0, 0, 0, 0, 0, 0});
        searchTab_IV_LL_border.setGradientType(RECTANGLE);

        Drawable[] searchTab_IV_LL_layers = {searchTab_IV_LL_border};
        LayerDrawable searchTab_IV_LL_ayerDrawable = new LayerDrawable(searchTab_IV_LL_layers);
        searchTab_IV_LL_ayerDrawable.setLayerInset(0, 1, 1, -1, -1);
        REV_VIEWS_BASE_FUNCTIONS.REV_SAFE_SET_BACKGROUND(searchTab_IV_LL, searchTab_IV_LL_ayerDrawable);

        TextView searchInput_TV = revCoreInputFormTextView.getRevExtraSmallNormalTextView_NO_PADDING();
        searchInput_TV.setText("FiND");
        searchInput_TV.setTextColor(ContextCompat.getColor(mContext, rev.ca.revlibviews.R.color.black_OPAQUE));
        searchInput_TV.setLayoutParams(searchTab_IV_LL.getLayoutParams());
        searchInput_TV.setPadding(RevLibGenConstantine.REV_MARGIN_TINY, RevLibGenConstantine.REV_MARGIN_TINY, RevLibGenConstantine.REV_MARGIN_LARGE, RevLibGenConstantine.REV_MARGIN_TINY);

        searchTab_IV_LL.addView(searchInput_TV);

        int searchTab_IVImageSize = RevLibGenConstantine.REV_IMAGE_SIZE_SMALL;

        ImageView searchTab_IV = new ImageView(mContext);
        searchTab_IV.setBackgroundColor(ContextCompat.getColor(mContext, R.color.revWhite));
        searchTab_IV.setColorFilter(ContextCompat.getColor(mContext, R.color.revPurple));

        Picasso.get()
                .load(R.drawable.ic_find_in_page_black_48dp)
                .resize(searchTab_IVImageSize, searchTab_IVImageSize)
                .centerCrop()
                .into(searchTab_IV);

        searchTab_IV_LL.addView(searchTab_IV);

        LinearLayout.LayoutParams searchTab_IV_LP = (LinearLayout.LayoutParams) searchTab_IV_LL.getLayoutParams();
        searchTab_IV_LP.gravity = Gravity.CENTER_VERTICAL;
        searchTab_IV_LP.setMargins(0, 0, 1, 0);

        searchTab_IV.setLayoutParams(searchTab_IV_LP);

        return searchTab_IV_LL;

    }

    private static View backTab() {
        TextView tab = revCoreInputFormTextView.getRevExtraSmallBoldTextView();
        tab.setText("Back");

        LinearLayout.LayoutParams tab_TV_LP = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        tab_TV_LP.gravity = Gravity.CENTER_VERTICAL;
        tab_TV_LP.setMargins(0, 0, RevLibGenConstantine.REV_MARGIN_LARGE, 0);
        tab.setLayoutParams(tab_TV_LP);

        tab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                REV_CORE_VIEWS_REFACTORING_BASE_FUNCTIONS.REV_RESET_BASE_CONTENT_CONTAINER_BY_PARENT_VIEW_ID(R.id.PLUGGABLE_REV_MAIN_CENTER_CCT_VIEW_LL_CENTER_ID, RevPluggableCenterMainContentViewLoader.revLoadCenterMainContentView(null));
            }
        });

        Drawable infoBttnImg = mContext.getResources().getDrawable(R.drawable.ic_arrow_back_black_48dp);
        infoBttnImg.setBounds(0, 0, imageSize, imageSize);
        DrawableCompat.setTint(infoBttnImg, ContextCompat.getColor(mContext, R.color.revPurple));
        tab.setCompoundDrawables(infoBttnImg, null, null, null);

        return tab;
    }

    private static View allHistoryTab() {
        final LinearLayout allHistoryTab_LL = revCoreLayoutsLinearLayout.getHorizontalRevLinearLayout_WRAPPED_ALL();
        LinearLayout.LayoutParams allHistoryTab_LLLayoutParams = (LinearLayout.LayoutParams) allHistoryTab_LL.getLayoutParams();
        allHistoryTab_LLLayoutParams.gravity = Gravity.CENTER_VERTICAL;
        allHistoryTab_LL.setPadding(4, 4, 4, 4);

        GradientDrawable gradientDrawable = REV_CORE_VIEWS_REFACTORING_BASE_FUNCTIONS.REV_GET_ALL_BORDERS_CURVED();
        if (Build.VERSION.SDK_INT >= 16)
            allHistoryTab_LL.setBackground(gradientDrawable);
        else allHistoryTab_LL.setBackgroundDrawable(gradientDrawable);

        Drawable infoBttnImg = mContext.getResources().getDrawable(R.drawable.ic_code_black_48dp);
        infoBttnImg.setBounds(0, 0, imageSize, imageSize);
        DrawableCompat.setTint(infoBttnImg, ContextCompat.getColor(mContext, R.color.revPurple));

        ImageView imageView = new ImageView(mContext);
        imageView.setPadding(0, 0, 0, 0);
        imageView.setImageDrawable(infoBttnImg);
        imageView.setAdjustViewBounds(true);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(RevLibGenConstantine.REV_IMAGE_SIZE_MEDIUM, RevLibGenConstantine.REV_IMAGE_SIZE_MEDIUM);
        layoutParams.gravity = (Gravity.CENTER_VERTICAL);
        imageView.setLayoutParams(layoutParams);

        LinearLayout.LayoutParams textView_LP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textView_LP.gravity = (Gravity.CENTER_VERTICAL);
        textView_LP.setMargins(0, 0, 0, (int) (revDimens.getRevMarginExtraSmall() * .5));

        allHistoryTab_LL.addView(imageView);

        return allHistoryTab_LL;
    }
}
