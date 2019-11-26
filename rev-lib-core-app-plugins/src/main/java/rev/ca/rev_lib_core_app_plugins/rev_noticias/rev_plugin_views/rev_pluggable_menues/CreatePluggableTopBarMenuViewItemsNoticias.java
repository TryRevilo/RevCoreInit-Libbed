package rev.ca.rev_lib_core_app_plugins.rev_noticias.rev_plugin_views.rev_pluggable_menues;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.ArrayMap;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntity;
import rev.ca.rev_gen_lib_pers.c_libs_core.RevPersLibRead;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevPersGenFunctions;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_lib_core_app_plugins.R;
import rev.ca.rev_lib_core_app_plugins.my_fences_application.rev_plugin_views.rev_plugin_pages.REV_RESET_PAGE_CONTENT;
import rev.ca.rev_lib_core_views.AbstractIRevPluggableViews;
import rev.ca.rev_lib_core_views.IRevPluggableViewProperties;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_inline_views.RevPluggableViewImpl;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.rev_gen_lib_pers.rev_varags_data.REV_SESSION_SETTINGS;
import rev.ca.revlibviews.REV_VIEWS_BASE_FUNCTIONS;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormTextView;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;

import static android.graphics.drawable.GradientDrawable.RECTANGLE;
import static android.text.Spanned.SPAN_INCLUSIVE_INCLUSIVE;

public class CreatePluggableTopBarMenuViewItemsNoticias extends AbstractIRevPluggableViews {

    private RevVarArgsData revVarArgsData;
    private Context mContext;

    private int imageSize;

    RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout;
    RevCoreInputFormTextView revCoreInputFormTextView;

    int unreadCount;

    LinearLayout revNoticiasContainer_LL;

    public CreatePluggableTopBarMenuViewItemsNoticias(RevVarArgsData revVarArgsData) {
        super(RevPersGenFunctions.REV_VAR_ARGS_DATA_RESOLVER(revVarArgsData));
        this.revVarArgsData = RevPersGenFunctions.REV_VAR_ARGS_DATA_RESOLVER(revVarArgsData);
        this.mContext = this.revVarArgsData.getmContext();

        imageSize = RevLibGenConstantine.REV_IMAGE_SIZE_SMALL;

        unreadCount = new RevPersLibRead().getNumberOfUnreadRevEntites();

        revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(mContext);
        revCoreInputFormTextView = new RevCoreInputFormTextView(mContext);

        this.infoTab();
    }

    @Override
    public ArrayMap<View, View> createPluggableALtTopBarMenuViewItem() {
        ArrayMap<View, View> arrayMap = null;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            arrayMap = new ArrayMap<>();

            LinearLayout revItemsContainer_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
            ((LinearLayout.LayoutParams) revItemsContainer_LL.getLayoutParams()).setMargins(0, (int) (RevLibGenConstantine.REV_MARGIN_TINY * 1.5), 0, 0);

            revNoticiasContainer_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
            revNoticiasContainer_LL.setPadding((int) (RevLibGenConstantine.REV_MARGIN_LARGE * .7), RevLibGenConstantine.REV_MARGIN_SMALL, RevLibGenConstantine.REV_MARGIN_LARGE, 0);

            revItemsContainer_LL.addView(RevPluggableViewImpl.REV_GET_REV_PLUGGABLE_INLINE_VIEW("rev_new_noticias_listings_container"));
            revItemsContainer_LL.addView(revNoticiasContainer_LL);

            ImageView userIcon_IV = new ImageView(mContext);

            int imgSize = (int) (RevLibGenConstantine.REV_IMAGE_SIZE_MEDIUM * .55);

            LinearLayout noticiasHeaderWrapper_LL = revCoreLayoutsLinearLayout.get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();

            Picasso.get()
                    .load(R.drawable.icons8_topic_push_notification_64)
                    .resize(imgSize, imgSize)
                    .centerCrop()
                    .into(userIcon_IV);

            noticiasHeaderWrapper_LL.addView(userIcon_IV);

            int borderWidth = 1;

            GradientDrawable border = new GradientDrawable();
            border.setStroke(borderWidth, mContext.getResources().getColor(R.color.revWhite));
            border.setGradientType(RECTANGLE);

            Drawable[] layers = {border};
            LayerDrawable layerDrawable = new LayerDrawable(layers);
            layerDrawable.setLayerInset(0, -borderWidth, -borderWidth, -borderWidth, borderWidth);

            TextView noticiasTtl_TV = revCoreInputFormTextView.getRevExtraSmallBoldTextView_NOPADDING(.8f);
            noticiasTtl_TV.setText("oTHER NoTiFicATioNs");
            noticiasTtl_TV.setPadding(0, 0, RevLibGenConstantine.REV_MARGIN_LARGE, 0);

            REV_VIEWS_BASE_FUNCTIONS.REV_SAFE_SET_BACKGROUND(noticiasTtl_TV, layerDrawable);
            REV_VIEWS_BASE_FUNCTIONS.CENTER_VIEW_VERTICALLY(noticiasTtl_TV);

            noticiasHeaderWrapper_LL.addView(noticiasTtl_TV);

            revNoticiasContainer_LL.addView(noticiasHeaderWrapper_LL);

            final ScrollView scrollView = new ScrollView(mContext);

            LinearLayout.LayoutParams scrollView_LP = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
            );
            scrollView.setLayoutParams(scrollView_LP);

            for (int i = 0; i < 5; i++)
                revNoticiasContainer_LL.addView(this.revNoticiaItemView());

            LinearLayout passContainer_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();

            scrollView.addView(revItemsContainer_LL);
            passContainer_LL.addView(scrollView);

            arrayMap.put(RevPluggableViewImpl.REV_GET_REV_PLUGGABLE_INLINE_VIEW("rev_new_noticias_count_tab"), passContainer_LL);
        }
        return arrayMap;
    }

    private View revNoticiaItemView() {
        LinearLayout revEntityTitle_LL = revCoreLayoutsLinearLayout.get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        revEntityTitle_LL.setPadding(0, RevLibGenConstantine.REV_MARGIN_TINY, RevLibGenConstantine.REV_MARGIN_SMALL, RevLibGenConstantine.REV_MARGIN_TINY);

        String profileNameTtl = "Memo";
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
                .load(R.drawable.ic_vertical_align_top_black_48dp)
                .resize(revImageSizeSmall, revImageSizeSmall)
                .centerCrop()
                .into(imageView);

        LinearLayout revEntityTopMainItems = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        revEntityTopMainItems.setBackgroundResource(rev.ca.revlibviews.R.drawable.rev_fancy_tab_left_offset_bottom_border);

        TextView revEntityTitle = revCoreInputFormTextView.getRevExtraSmallNormalTextView_NO_PADDING();
        revEntityTitle.setText(profileNameTtlSpan);
        revEntityTitle.setGravity(Gravity.TOP);
        ((LinearLayout.LayoutParams) revEntityTitle.getLayoutParams()).setMargins(0, 0, 0, RevLibGenConstantine.REV_MARGIN_TINY);

        TextView noticiasItemMsg_TV = revCoreInputFormTextView.getRevExtraSmallNormalTextView_NO_PADDING(.8f);
        noticiasItemMsg_TV.setText("The Goo Goo Dolls ara awesome . . . . . . . ");

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

        final TextView textView = revCoreInputFormTextView.getRevLargeNormalTextView();
        textView.setText("HELLO WORLD!");

        noticiasItemMore_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                REV_RESET_PAGE_CONTENT.REV_RESET_PAGE_CONTENT(textView);
            }
        });


        return revEntityTitle_LL;
    }

    private void infoTab() {
        if (unreadCount > 0) {
            String mainTextLeadString = "! " + unreadCount + " unread";
            final SpannableString profileNameTtlSpan = new SpannableString(mainTextLeadString);
            profileNameTtlSpan.setSpan(new AbsoluteSizeSpan(RevLibGenConstantine.REV_TEXT_SIZE_LARGE), 0, 1, SPAN_INCLUSIVE_INCLUSIVE);
            profileNameTtlSpan.setSpan(new AbsoluteSizeSpan(RevLibGenConstantine.REV_TEXT_SIZE_TINY), 2, (String.valueOf(unreadCount).length() + 1), SPAN_INCLUSIVE_INCLUSIVE);
            profileNameTtlSpan.setSpan(new ForegroundColorSpan(RevLibGenConstantine.REV_CONTEXT.getResources().getColor(R.color.revColorAccentRed)), 0, mainTextLeadString.length(), 0); // set color

            ((Activity) mContext).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ((TextView) RevPluggableViewImpl.REV_GET_REV_PLUGGABLE_INLINE_VIEW("rev_new_noticias_count_tab")).setText(profileNameTtlSpan);
                }
            });
        } else {
            final Drawable infoBttnImg = RevLibGenConstantine.REV_CONTEXT.getResources().getDrawable(R.drawable.ic_rounded_corner_black_48dp);
            infoBttnImg.setBounds(0, 0, imageSize, imageSize);
            DrawableCompat.setTint(infoBttnImg, ContextCompat.getColor(RevLibGenConstantine.REV_CONTEXT, R.color.teal_500_dark));

            ((Activity) mContext).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ((TextView) RevPluggableViewImpl.REV_GET_REV_PLUGGABLE_INLINE_VIEW("rev_new_noticias_count_tab")).setCompoundDrawables(null, null, infoBttnImg, null);
                }
            });
        }

        RevPluggableViewImpl.REV_GET_REV_PLUGGABLE_INLINE_VIEW("rev_new_noticias_count_tab").setBackgroundColor(Color.TRANSPARENT);
        ((TextView) RevPluggableViewImpl.REV_GET_REV_PLUGGABLE_INLINE_VIEW("rev_new_noticias_count_tab")).setGravity(Gravity.TOP);
    }

    @Override
    public void initPostPersRevAction(RevEntity revEntity) {
        if (Long.valueOf(REV_SESSION_SETTINGS.getRevLoggedInEntityGuid()) > -1)
            this.infoTab();

        TextView textView = new TextView(RevLibGenConstantine.REV_CONTEXT);
        textView.setText("TYPE -> " + revEntity.get_revEntityType() + " SUB -> " + revEntity.get_revEntitySubType());

        LinearLayout linearLayout = (LinearLayout) RevPluggableViewImpl.REV_GET_REV_PLUGGABLE_INLINE_VIEW("rev_new_noticias_listings_container");

        linearLayout.addView(textView, 0);
    }


    @Override
    public IRevPluggableViewProperties createIRevPluggableViewProperties() {
        IRevPluggableViewProperties iRevPluggableViewProperties = new IRevPluggableViewProperties(mContext);
        iRevPluggableViewProperties.setViewName("rev_noticias_tab");
        iRevPluggableViewProperties.setViewPlacement(2);
        return iRevPluggableViewProperties;
    }
}
