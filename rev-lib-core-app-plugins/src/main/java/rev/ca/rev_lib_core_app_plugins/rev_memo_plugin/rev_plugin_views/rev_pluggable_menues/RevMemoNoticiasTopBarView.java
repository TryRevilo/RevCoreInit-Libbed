package rev.ca.rev_lib_core_app_plugins.rev_memo_plugin.rev_plugin_views.rev_pluggable_menues;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import rev.ca.rev_gen_lib_pers.rev_varags_data.REV_SESSION_SETTINGS;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_lib_core_app_plugins.R;
import rev.ca.rev_lib_core_app_plugins.my_fences_application.rev_plugin_views.rev_plugin_pages.REV_RESET_PAGE_CONTENT;
import rev.ca.rev_lib_core_app_plugins.rev_memo_plugin.rev_plugin_views.rev_plugin_views.RevMemoNoticiasListingView;
import rev.ca.rev_lib_core_views.REV_DEC_STRING_VIEWS_BASE_FUNCTIONS;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_inline_views.RevPluggableViewImpl;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.revlibviews.REV_VIEWS_BASE_FUNCTIONS;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormTextView;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;

import static android.text.Spanned.SPAN_INCLUSIVE_INCLUSIVE;

public class RevMemoNoticiasTopBarView {

    private Context mContext;

    private RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout;
    private RevCoreInputFormTextView revCoreInputFormTextView;

    public RevMemoNoticiasTopBarView(Context mContext) {
        this.mContext = mContext;

        this.revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(mContext);
        this.revCoreInputFormTextView = new RevCoreInputFormTextView(mContext);
    }

    public View getRevMemoNoticiasTopBarView() {
        LinearLayout revItemsContainer_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        ((LinearLayout.LayoutParams) revItemsContainer_LL.getLayoutParams()).setMargins(0, 0, 0, RevLibGenConstantine.REV_MARGIN_TINY);
        revItemsContainer_LL.setPadding((int) (RevLibGenConstantine.REV_MARGIN_LARGE * .7), RevLibGenConstantine.REV_MARGIN_SMALL, RevLibGenConstantine.REV_MARGIN_LARGE, RevLibGenConstantine.REV_MARGIN_SMALL);
        revItemsContainer_LL.setBackgroundColor(Color.parseColor("#f1f8e9"));

        revItemsContainer_LL.addView(this.revNoticiaItemView());

        ((ViewGroup) RevPluggableViewImpl.REV_GET_REV_PLUGGABLE_INLINE_VIEW_NO_PARENT("rev_new_noticias_listings_container"))
                .addView(revItemsContainer_LL);

        return revItemsContainer_LL;
    }

    private View revNoticiaItemView() {
        LinearLayout revEntityTitle_LL = revCoreLayoutsLinearLayout.get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        revEntityTitle_LL.setClickable(true);
        revEntityTitle_LL.setPadding(0, RevLibGenConstantine.REV_MARGIN_TINY, RevLibGenConstantine.REV_MARGIN_SMALL, RevLibGenConstantine.REV_MARGIN_TINY);

        String profileNameTtl = "uNREAD mEmos";
        SpannableString profileNameTtlSpan = new SpannableString(profileNameTtl);
        profileNameTtlSpan.setSpan(new AbsoluteSizeSpan(RevLibGenConstantine.REV_TEXT_SIZE_LARGE), 0, 1, SPAN_INCLUSIVE_INCLUSIVE);
        profileNameTtlSpan.setSpan(new AbsoluteSizeSpan((int) (RevLibGenConstantine.REV_TEXT_SIZE_TINY * .8)), 2, profileNameTtl.length() - 1, SPAN_INCLUSIVE_INCLUSIVE);
        profileNameTtlSpan.setSpan(new ForegroundColorSpan(mContext.getResources().getColor(R.color.colorPrimary)), 0, profileNameTtl.length(), 0); // set color

        /** END SPANNING **/

        int revImageSizeSmall = RevLibGenConstantine.REV_IMAGE_SIZE_SMALL;

        ImageView imageView = new ImageView(mContext);

        LinearLayout.LayoutParams userIcon_IV_LP = new LinearLayout.LayoutParams(revImageSizeSmall, revImageSizeSmall);
        userIcon_IV_LP.setMargins(1, RevLibGenConstantine.REV_MARGIN_TINY, 0, 0);
        userIcon_IV_LP.gravity = Gravity.TOP;

        imageView.setLayoutParams(userIcon_IV_LP);

        Picasso.get()
                .load(R.drawable.icons8_note_64)
                .resize(revImageSizeSmall, revImageSizeSmall)
                .centerCrop()
                .into(imageView);

        LinearLayout revEntityTopMainItems = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        revEntityTopMainItems.setBackgroundResource(rev.ca.revlibviews.R.drawable.rev_fancy_tab_left_offset_bottom_border);

        TextView revEntityTitle = revCoreInputFormTextView.getRevExtraSmallNormalTextView_NO_PADDING();
        revEntityTitle.setText(profileNameTtlSpan);
        revEntityTitle.setGravity(Gravity.TOP);
        ((LinearLayout.LayoutParams) revEntityTitle.getLayoutParams()).setMargins(0, 0, RevLibGenConstantine.REV_MARGIN_TINY, RevLibGenConstantine.REV_MARGIN_TINY);

        TextView noticiasItemMsg_TV = revCoreInputFormTextView.getRevExtraSmallNormalTextView_NO_PADDING(.8f);
        noticiasItemMsg_TV.setText(REV_DEC_STRING_VIEWS_BASE_FUNCTIONS.makeRevDecString("NoNE ( 0 ) . . . . . . . "));

        ImageView viewNoticiasDetails_IV = new ImageView(mContext);

        LinearLayout.LayoutParams viewNoticiasDetails_IV_LP = new LinearLayout.LayoutParams(revImageSizeSmall, revImageSizeSmall);
        viewNoticiasDetails_IV_LP.setMargins(1, RevLibGenConstantine.REV_MARGIN_TINY, 0, 0);
        viewNoticiasDetails_IV_LP.gravity = Gravity.TOP;

        viewNoticiasDetails_IV.setLayoutParams(viewNoticiasDetails_IV_LP);

        Picasso.get()
                .load(R.drawable.icons8_compare_git_40)
                .resize(revImageSizeSmall, revImageSizeSmall)
                .centerCrop()
                .into(viewNoticiasDetails_IV);

        TextView noticiasItemMore_TV = revCoreInputFormTextView.getRevExtraSmallNormalTextView_NO_PADDING_LINK(.8f);
        noticiasItemMore_TV.setText("Read More");
        ((LinearLayout.LayoutParams) noticiasItemMore_TV.getLayoutParams()).gravity = Gravity.CENTER_VERTICAL;

        revEntityTitle_LL.addView(imageView);
        revEntityTitle_LL.addView(revEntityTitle);
        revEntityTitle_LL.addView(noticiasItemMsg_TV);
        revEntityTitle_LL.addView(REV_VIEWS_BASE_FUNCTIONS.REV_SPACER());
        revEntityTitle_LL.addView(viewNoticiasDetails_IV);
        revEntityTitle_LL.addView(noticiasItemMore_TV);

        revEntityTitle_LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RevVarArgsData revPassRVD = REV_SESSION_SETTINGS.getRevLoggedInPageRevVarArgsData();
                revPassRVD.setRevEntity(revPassRVD.getRevEntity());
                REV_RESET_PAGE_CONTENT.REV_RESET_PAGE_CONTENT(new RevMemoNoticiasListingView(revPassRVD).getRevMemoNoticiasListingView());
            }
        });

        return revEntityTitle_LL;
    }
}
