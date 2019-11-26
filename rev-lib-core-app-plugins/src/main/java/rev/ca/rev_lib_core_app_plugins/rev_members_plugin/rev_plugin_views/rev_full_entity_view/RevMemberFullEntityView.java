package rev.ca.rev_lib_core_app_plugins.rev_members_plugin.rev_plugin_views.rev_full_entity_view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;

import rev.ca.rev_gen_lib_pers.c_libs_core.RevPersLibRead;
import rev.ca.rev_lib_core_app_plugins.R;
import rev.ca.rev_lib_core_views.rev_core_views.rev_activities_window_views.RevPop;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_menues.RevPluggableOptionsContainerMenuLoader;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntity;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.revlibviews.REV_VIEWS_BASE_FUNCTIONS;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormTextView;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;

import static android.graphics.drawable.GradientDrawable.RECTANGLE;
import static android.text.Spanned.SPAN_INCLUSIVE_INCLUSIVE;

public class RevMemberFullEntityView {

    private Context mContext;
    private RevVarArgsData revVarArgsData;

    private RevEntity revEntity;

    RevCoreInputFormTextView revCoreInputFormTextView;
    RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout;

    LinearLayout.LayoutParams itemTitle_LP;

    int padding = RevLibGenConstantine.REV_MARGIN_SMALL;

    public RevMemberFullEntityView(RevVarArgsData revVarArgsData) {
        this.revVarArgsData = revVarArgsData;

        this.mContext = revVarArgsData.getmContext();
        this.revEntity = revVarArgsData.getRevEntity();

        revCoreInputFormTextView = new RevCoreInputFormTextView(mContext);
        revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(mContext);

        itemTitle_LP = new LinearLayout.LayoutParams((int) (RevLibGenConstantine.REV_MARGIN_LARGE * 1.05), ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    View revItemOptionsLayoutTabs() {
        LinearLayout linearLayout = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_WRAP_ALL();

        FrameLayout.LayoutParams textView_LP = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textView_LP.setMargins(0, 0, 0, 0);
        textView_LP.gravity = Gravity.TOP | Gravity.RIGHT;
        linearLayout.setLayoutParams(textView_LP);

        linearLayout.addView(this.revCloseTab());

        LinearLayout optionsTabWrapper_LL = revCoreLayoutsLinearLayout.getHorizontalRevLinearLayout_WRAPPED_ALL();
        ((LinearLayout.LayoutParams) optionsTabWrapper_LL.getLayoutParams()).setMargins(0, RevLibGenConstantine.REV_MARGIN_TINY, 0, 0);

        if (new RevPluggableOptionsContainerMenuLoader().getOptionsMenu("rev_bag_options_menu", revVarArgsData) != null) {
            RevVarArgsData postRVD = new RevVarArgsData();
            postRVD.setmContext(revVarArgsData.getmContext());
            postRVD.setRevEntity(revEntity);

            View view = new RevPluggableOptionsContainerMenuLoader().getOptionsMenu("rev_bag_options_menu", postRVD);
            view.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            ((FrameLayout.LayoutParams) view.getLayoutParams()).setMargins((int) (RevLibGenConstantine.REV_MARGIN_SMALL * .7), RevLibGenConstantine.REV_MARGIN_SMALL, 0, 0);

            optionsTabWrapper_LL.addView(view);
        }

        linearLayout.addView(optionsTabWrapper_LL);

        return linearLayout;

    }

    private View revPublisherDetailsTab() {

        /** PUBLISHER ICOM **/

        String imgPath = "/storage/emulated/0/Music/audio_mc/Linkin Park AAC 320/Albums/2000 - Hybrid Theory/cover.jpg";
        ImageView ownerEntityImageView = new ImageView(mContext);

        int imageSize = (int) (RevLibGenConstantine.REV_IMAGE_SIZE_LARGE * .7);

        Picasso.get()
                .load(new File(imgPath))
                .placeholder(R.drawable.ic_account_circle_black_48dp)
                .resize(imageSize, 0)
                .into(ownerEntityImageView);

        int borderSize = 5;
        int ownerEntityImageView_padding = 5;

        ownerEntityImageView.setPadding((int) (ownerEntityImageView_padding * 1.5), (int) (ownerEntityImageView_padding * 1.5), 0, (int) (ownerEntityImageView_padding * 1.5));

        LinearLayout.LayoutParams ownerEntityImageView_LP = new LinearLayout.LayoutParams((int) ((imageSize + borderSize + ownerEntityImageView_padding) * .98), LinearLayout.LayoutParams.WRAP_CONTENT);
        ownerEntityImageView_LP.gravity = (Gravity.TOP | Gravity.RIGHT);

        ownerEntityImageView.setLayoutParams(ownerEntityImageView_LP);

        GradientDrawable border = new GradientDrawable();
        border.setStroke(borderSize, mContext.getResources().getColor(R.color.greyDark));
        border.setGradientType(RECTANGLE);

        Drawable[] layers = {border};
        LayerDrawable layerDrawable = new LayerDrawable(layers);
        layerDrawable.setLayerInset(0, borderSize, borderSize, -borderSize, borderSize);

        REV_VIEWS_BASE_FUNCTIONS.REV_SAFE_SET_BACKGROUND(ownerEntityImageView, layerDrawable);

        /** END PUBLISHER ICOM **/

        return ownerEntityImageView;

    }

    View revCloseTab() {

        int imgSize = RevLibGenConstantine.REV_IMAGE_SIZE_SMALL;

        /** TAB **/

        Drawable mainTagViewOptions_DR = mContext.getResources().getDrawable(R.drawable.baseline_zoom_out_map_black_48dp);
        mainTagViewOptions_DR.setBounds(0, 0, imgSize, imgSize);
        DrawableCompat.setTint(mainTagViewOptions_DR, ContextCompat.getColor(mContext, R.color.revWhite));

        TextView mainTagViewOptions_TV = new RevCoreInputFormTextView(mContext).getRevExtraSmallNormalTextView();
        mainTagViewOptions_TV.setText("CLOSE");
        mainTagViewOptions_TV.setTextColor(mContext.getResources().getColor(rev.ca.revlibviews.R.color.revWhite));
        mainTagViewOptions_TV.setCompoundDrawablePadding(0);
        mainTagViewOptions_TV.setPadding(RevLibGenConstantine.REV_MARGIN_SMALL, RevLibGenConstantine.REV_MARGIN_SMALL, RevLibGenConstantine.REV_MARGIN_MEDIUM, RevLibGenConstantine.REV_MARGIN_SMALL);
        mainTagViewOptions_TV.setBackgroundColor(ContextCompat.getColor(mContext, rev.ca.revlibviews.R.color.rcolorAccent_OPAQUE));
        mainTagViewOptions_TV.setGravity(Gravity.CENTER_VERTICAL);

        mainTagViewOptions_TV.setCompoundDrawables(mainTagViewOptions_DR, null, null, null);

        LinearLayout.LayoutParams ownerEntityImageView_LP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        ownerEntityImageView_LP.gravity = (Gravity.TOP | Gravity.RIGHT);
        mainTagViewOptions_TV.setLayoutParams(ownerEntityImageView_LP);

        mainTagViewOptions_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RevPop.getPw().dismiss();
            }
        });

        /** END TAB **/

        return mainTagViewOptions_TV;

    }

    public View getRevMemoFullEntityView() {
        FrameLayout frameLayout = new FrameLayout(mContext);
        frameLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        LinearLayout linearLayout = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        linearLayout.setBackgroundColor(mContext.getResources().getColor(R.color.revWhite));
        linearLayout.setPadding(0, RevLibGenConstantine.REV_MARGIN_TINY, 0, RevLibGenConstantine.REV_MARGIN_MEDIUM);

        /** END MEMO HEADER **/

        linearLayout.addView(revPageEntityTitle());
        linearLayout.addView(this.revItemDateTime());
        linearLayout.addView(this.revFriends());
        linearLayout.addView(this.revPublisherDetails());

        frameLayout.addView(linearLayout);
        frameLayout.addView(this.revItemOptionsLayoutTabs());

        return frameLayout;
    }

    View revItemDateTime() {

        /** MEMO DATE **/

        TextView memoDate = revCoreInputFormTextView.getRevExtraSmallNormalTextView_NO_PADDING_LINK(.7f);
        memoDate.setText(revEntity.get_timeCreated());

        LinearLayout.LayoutParams memoDate_LP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        memoDate_LP.setMargins(padding, 0, 0, 0);
        memoDate.setLayoutParams(memoDate_LP);

        return memoDate;

    }

    View revPageEntityTitle() {

        LinearLayout revEntityPageTitle_LL = revCoreLayoutsLinearLayout.get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();

        int revImageSizeSmall = RevLibGenConstantine.REV_IMAGE_SIZE_SMALL;

        ImageView imageView = new ImageView(mContext);
        Picasso.get()
                .load(R.drawable.ic_vertical_align_top_black_48dp)
                .resize(revImageSizeSmall, revImageSizeSmall)
                .centerCrop()
                .into(imageView);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(RevLibGenConstantine.REV_IMAGE_SIZE_MEDIUM, RevLibGenConstantine.REV_IMAGE_SIZE_MEDIUM);
        layoutParams.gravity = (Gravity.TOP);
        imageView.setLayoutParams(layoutParams);

        String profileNameTtl = revEntity.getRevUserEntity().get_fullNames();
        SpannableString profileNameTtlSpan = new SpannableString(profileNameTtl);
        profileNameTtlSpan.setSpan(new AbsoluteSizeSpan(RevLibGenConstantine.REV_TEXT_SIZE_LARGE), 0, 1, SPAN_INCLUSIVE_INCLUSIVE);
        profileNameTtlSpan.setSpan(new AbsoluteSizeSpan((int) (RevLibGenConstantine.REV_TEXT_SIZE_TINY * .8)), 2, profileNameTtl.length() - 1, SPAN_INCLUSIVE_INCLUSIVE);
        profileNameTtlSpan.setSpan(new ForegroundColorSpan(mContext.getResources().getColor(R.color.colorPrimary)), 0, profileNameTtl.length(), 0);

        CharSequence finalText = TextUtils.concat(profileNameTtlSpan);

        TextView revEntityPageTitle = revCoreInputFormTextView.getRevExtraSmallNormalTextView_NO_PADDING();
        revEntityPageTitle.setText(finalText);

        revEntityPageTitle.setPadding(padding, padding, RevLibGenConstantine.REV_MARGIN_LARGE, 0);

        /** SET BORDERS **/

        int borderSize = 1;

        GradientDrawable border = new GradientDrawable();
        border.setStroke(borderSize, mContext.getResources().getColor(R.color.teal_dark));
        border.setGradientType(RECTANGLE);

        Drawable[] layers = {border};
        LayerDrawable layerDrawable = new LayerDrawable(layers);
        layerDrawable.setLayerInset(0, -borderSize, -borderSize, -borderSize, borderSize);

        REV_VIEWS_BASE_FUNCTIONS.REV_SAFE_SET_BACKGROUND(revEntityPageTitle, layerDrawable);

        /** SET BORDERS END **/

        revEntityPageTitle_LL.addView(revPublisherDetailsTab());
        revEntityPageTitle_LL.addView(revEntityPageTitle);

        return revEntityPageTitle_LL;

    }

    private View revFriends() {

        LinearLayout friendsIconsWrapper_LL = revCoreLayoutsLinearLayout.getHorizontalRevLinearLayout_WRAPPED_ALL();
        ((LinearLayout.LayoutParams) friendsIconsWrapper_LL.getLayoutParams()).gravity = Gravity.CENTER_VERTICAL;

        for (int i = 0; i < 122; i++) {
            LinearLayout imgView_LL = revCoreLayoutsLinearLayout.getHorizontalRevLinearLayout_WRAPPED_ALL();
            String imgPath = "/storage/emulated/0/DCIM/Camera/IMG_20180116_172844.jpg";
            ImageView imageView = new ImageView(mContext);

            int imgSize = (int) (RevLibGenConstantine.REV_IMAGE_SIZE_MEDIUM * .75);

            if (new File(imgPath).exists()) {
                Picasso.get()
                        .load(new File(imgPath))
                        .resize(imgSize, imgSize)
                        .centerCrop()
                        .into(imageView);

                LinearLayout.LayoutParams imageView_LP = (LinearLayout.LayoutParams) imgView_LL.getLayoutParams();
                imageView_LP.setMargins(2, 0, 0, 0);
                imageView_LP.gravity = Gravity.CENTER_VERTICAL;
                imgView_LL.setLayoutParams(imageView_LP);

                imgView_LL.addView(imageView);
                friendsIconsWrapper_LL.addView(imgView_LL);
            }
        }

        final HorizontalScrollView scrollView = new HorizontalScrollView(mContext);
        scrollView.setPadding(0, 1, 0, 0);
        scrollView.setHorizontalScrollBarEnabled(false);

        scrollView.addView(friendsIconsWrapper_LL);

        return scrollView;

    }

    View revPublisherDetails() {

        /** PUBLISHER WRAPPER **/

        TextView revPublisherEntity = revCoreInputFormTextView.getRevVeryExtraSmallNormalTextView_NO_PADDING(.7f);
        revPublisherEntity.setText(new RevPersLibRead().revPersGetRevEntityName(revEntity.get_revEntityGUID()) + " ");
        revPublisherEntity.setGravity(Gravity.CENTER_VERTICAL);

        TextView revPublishTime = revCoreInputFormTextView.getRevVeryExtraSmallNormalTextView_NO_PADDING(.7f);
        revPublishTime.setText(revEntity.get_timeCreated());
        revPublishTime.setTextColor(mContext.getResources().getColor(rev.ca.revlibviews.R.color.revWhite));
        revPublishTime.setGravity(Gravity.CENTER_VERTICAL);
        revPublishTime.setPadding(RevLibGenConstantine.REV_MARGIN_TINY, 0, 0, 0);

        LinearLayout publisherWrapper_LL = revCoreLayoutsLinearLayout.get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        ((LinearLayout.LayoutParams) publisherWrapper_LL.getLayoutParams()).setMargins(0, RevLibGenConstantine.REV_MARGIN_SMALL, 0, 0);

        publisherWrapper_LL.setBackgroundColor(ContextCompat.getColor(mContext, rev.ca.revlibviews.R.color.rcolorAccent_OPAQUE));
        publisherWrapper_LL.setPadding(RevLibGenConstantine.REV_MARGIN_SMALL, RevLibGenConstantine.REV_MARGIN_TINY, RevLibGenConstantine.REV_MARGIN_SMALL, RevLibGenConstantine.REV_MARGIN_TINY);

        publisherWrapper_LL.addView(revPublisherEntity);
        publisherWrapper_LL.addView(revPublishTime);

        TextView textView = new RevCoreInputFormTextView(mContext).getRevExtraSmallNormalTextView();
        textView.setText("+" + (7) + " unread memos");

        textView.setTextColor(mContext.getResources().getColor(rev.ca.revlibviews.R.color.revWhite));
        textView.setCompoundDrawablePadding(0);
        textView.setPadding(RevLibGenConstantine.REV_MARGIN_SMALL, RevLibGenConstantine.REV_MARGIN_TINY, RevLibGenConstantine.REV_MARGIN_TINY, RevLibGenConstantine.REV_MARGIN_TINY);
        textView.setBackgroundColor(ContextCompat.getColor(mContext, rev.ca.revlibviews.R.color.rcolorAccent_OPAQUE));
        textView.setGravity(Gravity.CENTER_VERTICAL);

        publisherWrapper_LL.addView(REV_VIEWS_BASE_FUNCTIONS.REV_SPACER());
        publisherWrapper_LL.addView(textView);

        /** END PUBLISHER WRAPPER **/

        return publisherWrapper_LL;

    }
}
