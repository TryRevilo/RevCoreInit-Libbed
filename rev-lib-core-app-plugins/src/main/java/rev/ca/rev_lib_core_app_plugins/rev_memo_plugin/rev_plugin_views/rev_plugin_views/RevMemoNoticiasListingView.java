package rev.ca.rev_lib_core_app_plugins.rev_memo_plugin.rev_plugin_views.rev_plugin_views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import rev.ca.rev_gen_lib_pers.c_libs_core.RevPersLibRead;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_lib_core_app_plugins.R;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_menues.ICreateRevPluggableMenuItem;
import rev.ca.rev_lib_core_views.rev_pluggable_views_impl.RevConstantinePluggableViewsServices;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.revlibviews.REV_VIEWS_BASE_FUNCTIONS;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormTextView;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;

import static android.graphics.drawable.GradientDrawable.RECTANGLE;
import static android.text.Spanned.SPAN_INCLUSIVE_INCLUSIVE;

public class RevMemoNoticiasListingView {

    private RevVarArgsData revVarArgsData;
    private Context mContext;

    private RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout;
    private RevCoreInputFormTextView revCoreInputFormTextView;

    private RevPersLibRead revPersLibRead = new RevPersLibRead();

    private List<Long> revEntities;

    public RevMemoNoticiasListingView(RevVarArgsData revVarArgsData) {
        this.revVarArgsData = revVarArgsData;
        this.mContext = revVarArgsData.getmContext();

        this.revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(mContext);
        this.revCoreInputFormTextView = new RevCoreInputFormTextView(mContext);

        revEntities = revPersLibRead.revPersGetALLRevEntitySubtypeGUIDs_By_ResolveStatus("rev_memo", 0);
    }

    public View getRevMemoNoticiasListingView() {
        LinearLayout linearLayoutContailner = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();

        linearLayoutContailner.addView(this.getRevMemoNoticiasListingViewTtl());
        linearLayoutContailner.addView(this.getRevMemoNoticiasListingViewCct());
        linearLayoutContailner.addView(this.getRevPastMemoNoticiasListingViewTtl());
        linearLayoutContailner.addView(this.getRevPastMemoNoticiasListingViewCct());

        return linearLayoutContailner;
    }

    private View getRevMemoNoticiasListingViewTtl() {
        LinearLayout revHeaderWrapper_LL = revCoreLayoutsLinearLayout.get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        ((LinearLayout.LayoutParams) revHeaderWrapper_LL.getLayoutParams()).setMargins(RevLibGenConstantine.REV_MARGIN_TINY, RevLibGenConstantine.REV_MARGIN_SMALL, RevLibGenConstantine.REV_MARGIN_SMALL, 0);

        int revImageSizeSmall = RevLibGenConstantine.REV_IMAGE_SIZE_SMALL;

        ImageView imageView = new ImageView(mContext);

        LinearLayout.LayoutParams userIcon_IV_LP = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        userIcon_IV_LP.gravity = Gravity.CENTER_VERTICAL;

        imageView.setLayoutParams(userIcon_IV_LP);

        Picasso.get()
                .load(R.drawable.icons8_note_64)
                .resize(revImageSizeSmall, revImageSizeSmall)
                .centerCrop()
                .into(imageView);

        revHeaderWrapper_LL.addView(imageView);

        TextView revHeaderView_TV = revCoreInputFormTextView.getRevVeryExtraSmallNormalTextView_NO_PADDING((float) 1.1);
        revHeaderView_TV.setText("uNREAD mEmos");
        ((LinearLayout.LayoutParams) revHeaderView_TV.getLayoutParams()).gravity = Gravity.CENTER_VERTICAL;
        ((LinearLayout.LayoutParams) revHeaderView_TV.getLayoutParams()).setMargins((int) (RevLibGenConstantine.REV_MARGIN_TINY * .7), 0, 0, 0);
        revHeaderView_TV.setGravity(Gravity.CENTER_VERTICAL);

        revHeaderWrapper_LL.addView(revHeaderView_TV);
        revHeaderWrapper_LL.addView(REV_VIEWS_BASE_FUNCTIONS.REV_SPACER());

        if (this.newMemoPublisherWrapper_LL() != null)
            revHeaderWrapper_LL.addView(this.newMemoPublisherWrapper_LL());

        return revHeaderWrapper_LL;
    }

    public View newMemoPublisherWrapper_LL() {
        if (RevConstantinePluggableViewsServices.REV_PLUGGABLE_MENU_ITEMS_MAP.containsKey("publish_new_memo_title_bar_menu_item")) {
            revVarArgsData.setRevViewType("publish_new_memo_title_bar_menu_item");
            ICreateRevPluggableMenuItem iCreateRevPluggableMenuItem = (ICreateRevPluggableMenuItem) RevConstantinePluggableViewsServices.revViewCreator_REV_PLUGGABLE_MENU_ITEMS_MAP(revVarArgsData);
            return iCreateRevPluggableMenuItem.create_REV_PLUGGABLE_MENU_ITEM_VM().getRevPluggableMenuView();
        }

        return null;
    }

    private View getRevMemoNoticiasListingViewCct() {
        LinearLayout linearLayoutContainer = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();

        String profileNameTtl = "you Do NoT HAvE ANy iNREAD mEmos";
        SpannableString profileNameTtlSpan = new SpannableString(profileNameTtl);
        profileNameTtlSpan.setSpan(new AbsoluteSizeSpan(RevLibGenConstantine.REV_TEXT_SIZE_LARGE), 0, 1, SPAN_INCLUSIVE_INCLUSIVE);
        profileNameTtlSpan.setSpan(new AbsoluteSizeSpan((int) (RevLibGenConstantine.REV_TEXT_SIZE_TINY * .8)), 2, profileNameTtl.length() - 1, SPAN_INCLUSIVE_INCLUSIVE);
        profileNameTtlSpan.setSpan(new ForegroundColorSpan(mContext.getResources().getColor(R.color.colorPrimary)), 0, profileNameTtl.length(), 0); // set color

        TextView revNullListingView_TV = revCoreInputFormTextView.getRevVeryExtraSmallNormalTextView_NO_PADDING((float) 1.1);
        revNullListingView_TV.setText(profileNameTtlSpan);
        ((LinearLayout.LayoutParams) revNullListingView_TV.getLayoutParams()).gravity = Gravity.CENTER_VERTICAL;
        ((LinearLayout.LayoutParams) revNullListingView_TV.getLayoutParams()).setMargins((int) (RevLibGenConstantine.REV_MARGIN_LARGE * .7), 0, 0, 0);
        revNullListingView_TV.setGravity(Gravity.CENTER_VERTICAL);

        linearLayoutContainer.addView(revNullListingView_TV);

        return linearLayoutContainer;
    }


    private View getRevPastMemoNoticiasListingViewTtl() {
        LinearLayout linearLayoutContainer = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();

        int borderWidth = 1;

        GradientDrawable border = new GradientDrawable();
        border.setStroke(borderWidth, mContext.getResources().getColor(R.color.revExtraLightGreen_OPAQUE));
        border.setGradientType(RECTANGLE);

        Drawable[] layers = {border};
        LayerDrawable layerDrawable = new LayerDrawable(layers);
        layerDrawable.setLayerInset(0, -borderWidth, -borderWidth, -borderWidth, borderWidth);

        String profileNameTtl = "pAsT READ mEmos";
        SpannableString profileNameTtlSpan = new SpannableString(profileNameTtl);
        profileNameTtlSpan.setSpan(new AbsoluteSizeSpan(RevLibGenConstantine.REV_TEXT_SIZE_LARGE), 0, 1, SPAN_INCLUSIVE_INCLUSIVE);
        profileNameTtlSpan.setSpan(new AbsoluteSizeSpan((int) (RevLibGenConstantine.REV_TEXT_SIZE_TINY * .8)), 2, profileNameTtl.length() - 1, SPAN_INCLUSIVE_INCLUSIVE);
        profileNameTtlSpan.setSpan(new ForegroundColorSpan(mContext.getResources().getColor(R.color.colorPrimary)), 0, profileNameTtl.length(), 0); // set color

        TextView revNullListingView_TV = revCoreInputFormTextView.getRevVeryExtraSmallNormalTextView_NO_PADDING((float) 1.1);
        revNullListingView_TV.setText(profileNameTtlSpan);
        revNullListingView_TV.setPadding(0, 0, RevLibGenConstantine.REV_MARGIN_LARGE * 2, 0);
        ((LinearLayout.LayoutParams) revNullListingView_TV.getLayoutParams()).gravity = Gravity.CENTER_VERTICAL;
        ((LinearLayout.LayoutParams) revNullListingView_TV.getLayoutParams()).setMargins((int) (RevLibGenConstantine.REV_MARGIN_LARGE * .7), RevLibGenConstantine.REV_MARGIN_LARGE, 0, 0);
        revNullListingView_TV.setGravity(Gravity.CENTER_VERTICAL);

        REV_VIEWS_BASE_FUNCTIONS.REV_SAFE_SET_BACKGROUND(revNullListingView_TV, layerDrawable);

        linearLayoutContainer.addView(revNullListingView_TV);

        return linearLayoutContainer;
    }

    private View getRevPastMemoNoticiasListingViewCct() {
        LinearLayout linearLayoutContainer = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();

        String profileNameTtl = "NoNE To DispLAY ( + " + revEntities.size() + " count )";
        SpannableString profileNameTtlSpan = new SpannableString(profileNameTtl);
        profileNameTtlSpan.setSpan(new AbsoluteSizeSpan(RevLibGenConstantine.REV_TEXT_SIZE_LARGE), 0, 1, SPAN_INCLUSIVE_INCLUSIVE);
        profileNameTtlSpan.setSpan(new AbsoluteSizeSpan((int) (RevLibGenConstantine.REV_TEXT_SIZE_TINY * .8)), 2, profileNameTtl.length() - 1, SPAN_INCLUSIVE_INCLUSIVE);
        profileNameTtlSpan.setSpan(new ForegroundColorSpan(mContext.getResources().getColor(R.color.colorPrimary)), 0, profileNameTtl.length(), 0); // set color

        TextView revNullListingView_TV = revCoreInputFormTextView.getRevVeryExtraSmallNormalTextView_NO_PADDING((float) 1.1);
        revNullListingView_TV.setText(profileNameTtlSpan);
        ((LinearLayout.LayoutParams) revNullListingView_TV.getLayoutParams()).gravity = Gravity.CENTER_VERTICAL;
        ((LinearLayout.LayoutParams) revNullListingView_TV.getLayoutParams()).setMargins((int) (RevLibGenConstantine.REV_MARGIN_LARGE * .7), 0, 0, 0);
        revNullListingView_TV.setGravity(Gravity.CENTER_VERTICAL);

        linearLayoutContainer.addView(revNullListingView_TV);

        return linearLayoutContainer;
    }
}
