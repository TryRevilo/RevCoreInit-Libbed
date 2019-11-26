package rev.ca.rev_lib_core_app_plugins.rev_members_plugin.rev_plugin_views.rev_plugin_views_overrides;

import android.content.Context;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import rev.ca.rev_gen_lib_pers.RevDBModels.REV_PERS_REVMETADATA_GEN_FUNCTIONS;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntity;
import rev.ca.rev_gen_lib_pers.rev_server_client.rev_pers.rev_pers_file.DownloadImageTask;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_lib_core_app_plugins.R;
import rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_plugin_views.rev_object.RevUserFullProfileView;
import rev.ca.rev_lib_core_views.AbstractIRevPluggableViews;
import rev.ca.rev_lib_core_views.rev_core_views.rev_page.rev_pers_views.RevObjectListingViewFooterTabs;
import rev.ca.rev_lib_core_views.rev_core_views.rev_page.rev_pers_views.rev_plugin_view_override.IOverrideRevEntityListingView;
import rev.ca.rev_lib_core_views.rev_core_views.rev_page.rev_pers_views.rev_plugin_view_override.RevEntityListingViewOverrideVOM;
import rev.ca.rev_lib_core_views.rev_view_widgets.rev_core_section_widgets.rev_view_widget_services.IRevObjectListingFooterOptions;
import rev.ca.rev_lib_gen_functions.REV_STRINGS_BASE_FUNCTIONS;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormTextView;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;

import static android.text.Spanned.SPAN_INCLUSIVE_INCLUSIVE;

/**
 * Created by rev on 12/1/17.
 */

public class RevMemberRevCustomObjectListingView extends AbstractIRevPluggableViews implements IRevObjectListingFooterOptions, IOverrideRevEntityListingView {

    private RevVarArgsData revVarArgsData;
    private Context mContext;

    private RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout;
    private RevCoreInputFormTextView revCoreInputFormTextView;

    public RevMemberRevCustomObjectListingView(RevVarArgsData revVarArgsData) {
        super(revVarArgsData);
        this.revVarArgsData = revVarArgsData;
        this.mContext = RevLibGenConstantine.REV_CONTEXT;

        revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(mContext);
        revCoreInputFormTextView = new RevCoreInputFormTextView(mContext);
    }

    @Override
    public String registerRevPluggableCustomObjectListingView() {
        return "rev_user_entity";
    }

    @Override
    public RevEntityListingViewOverrideVOM create_OVERRIDE_ITEM(final RevEntity revEntity) {
        LinearLayout itemContainer_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        itemContainer_LL.setClickable(true);
        ((LinearLayout.LayoutParams) itemContainer_LL.getLayoutParams()).setMargins(RevLibGenConstantine.REV_MARGIN_SMALL, 0, 0, RevLibGenConstantine.REV_MARGIN_MEDIUM);

        itemContainer_LL.addView(this.getGeneralPointingUpVotes(revEntity));
        itemContainer_LL.addView(this.revFriends());

        itemContainer_LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RevVarArgsData postRVD = new RevVarArgsData(mContext);
                postRVD.setRevEntity(revEntity);
                new RevUserFullProfileView(postRVD).revResetUserMainCenterCctViewLL();
            }
        });

        RevEntityListingViewOverrideVOM revEntityListingViewOverrideVOM = new RevEntityListingViewOverrideVOM();

        revEntityListingViewOverrideVOM.setOverrideName("rev_user_entity");
        revEntityListingViewOverrideVOM.setRevEntity(revEntity);
        revEntityListingViewOverrideVOM.setOverrideView(itemContainer_LL);

        RevVarArgsData argsData = new RevVarArgsData();

        Map<Object, Object> revVarArgsDataMetadataStrings = new HashMap<>();
        revVarArgsDataMetadataStrings.put("isDecorated", "false");

        argsData.setRevVarArgsDataMetadataStrings(revVarArgsDataMetadataStrings);

        revEntityListingViewOverrideVOM.setRevVarArgsData(argsData);

        return revEntityListingViewOverrideVOM;
    }

    private View revFriends() {
        LinearLayout friendsIconsWrapper_LL = revCoreLayoutsLinearLayout.getHorizontalRevLinearLayout_WRAPPED_ALL();
        ((LinearLayout.LayoutParams) friendsIconsWrapper_LL.getLayoutParams()).gravity = Gravity.CENTER_VERTICAL;

        int imageSize = (int) (RevLibGenConstantine.REV_IMAGE_SIZE_MEDIUM * .75);

        for (int i = 0; i < 122; i++) {
            LinearLayout imgView_LL = revCoreLayoutsLinearLayout.getHorizontalRevLinearLayout_WRAPPED_ALL();
            String imgPath = "/storage/emulated/0/DCIM/Camera/IMG_20180116_172844.jpg";

            ImageView imageView = new ImageView(mContext);

            File userIconFIle = new File(imgPath);

            if (userIconFIle.exists()) {
                Picasso.get()
                        .load(userIconFIle)
                        .placeholder(R.drawable.ic_account_circle_black_48dp)
                        .resize(imageSize, 0)
                        .into(imageView);
            } else {
                Picasso.get()
                        .load(R.drawable.ic_account_circle_black_48dp)
                        .placeholder(R.drawable.ic_account_circle_black_48dp)
                        .resize(RevLibGenConstantine.REV_IMAGE_SIZE_MEDIUM, 0)
                        .into(imageView);

                imageView.setColorFilter(ContextCompat.getColor(mContext, R.color.rcolorAccent_OPAQUE));
            }

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

    @Override
    public View createRevObjectListingFooterOptionView() {
        LinearLayout revObjectListingFooterOptionsWrapper_LL = revCoreLayoutsLinearLayout.get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        RevObjectListingViewFooterTabs revObjectListingViewFooterTabs = new RevObjectListingViewFooterTabs(revVarArgsData);

        LinearLayout.LayoutParams revObjectListingFooterOptionsWrapper_LL_LP = (LinearLayout.LayoutParams) revObjectListingFooterOptionsWrapper_LL.getLayoutParams();
        revObjectListingFooterOptionsWrapper_LL_LP.setMargins(0, RevLibGenConstantine.REV_MARGIN_TINY, 0, 0);

        revObjectListingFooterOptionsWrapper_LL.addView(revObjectListingViewFooterTabs.revLikeItem());
        revObjectListingFooterOptionsWrapper_LL.addView(revObjectListingViewFooterTabs.revCommentItem());
        revObjectListingFooterOptionsWrapper_LL.addView(revObjectListingViewFooterTabs.revMoreOptionsItem());

        return revObjectListingFooterOptionsWrapper_LL;
    }

    private View getGeneralPointingUpVotes(RevEntity revEntity) {
        LinearLayout revEntityTopMainItems = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();

        /* PROFILE */

        /**  REV ENTITY NAME **/

        String profileNameTtl = REV_PERS_REVMETADATA_GEN_FUNCTIONS.REV_GET_METADATA_VALUE(revEntity.get_revEntityMetadataList(), "rev_entity_full_names_value");

        SpannableString profileNameTtlSpan = new SpannableString(profileNameTtl);
        profileNameTtlSpan.setSpan(new AbsoluteSizeSpan(RevLibGenConstantine.REV_TEXT_SIZE_LARGE), 0, 1, SPAN_INCLUSIVE_INCLUSIVE);
        profileNameTtlSpan.setSpan(new AbsoluteSizeSpan((int) (RevLibGenConstantine.REV_TEXT_SIZE_TINY * .8)), 2, profileNameTtl.length() - 1, SPAN_INCLUSIVE_INCLUSIVE);
        profileNameTtlSpan.setSpan(new ForegroundColorSpan(mContext.getResources().getColor(R.color.colorPrimary)), 0, profileNameTtl.length(), 0); // set color

        /**  REV ENTITY NAME **/

        String revEntityDesc_S = REV_STRINGS_BASE_FUNCTIONS.REV_GET_SHORT_STRING(
                "However in certain situations, you may need to pick up a set of records from a particular offset. Here is an example, which picks up 3 records starting from the 3rd position.",
                85);

        revEntityDesc_S = revEntityDesc_S.replace("\n", " ").replace("\r", " ");

        SpannableString revEntityDesc_S_Span = new SpannableString(revEntityDesc_S);
        revEntityDesc_S_Span.setSpan(new AbsoluteSizeSpan(RevLibGenConstantine.REV_TEXT_SIZE_TINY), 0, revEntityDesc_S.length(), SPAN_INCLUSIVE_INCLUSIVE);
        revEntityDesc_S_Span.setSpan(new AbsoluteSizeSpan((int) (RevLibGenConstantine.REV_TEXT_SIZE_TINY * .8)), 2, revEntityDesc_S.length() - 1, SPAN_INCLUSIVE_INCLUSIVE);

        /** END SPANNING **/

        /** TITLE **/

        // let's put both spans together with a separator and all
        CharSequence finalText = TextUtils.concat(profileNameTtlSpan, " ", revEntityDesc_S_Span);

        String imgPath = "/storage/emulated/0/DCIM/Camera/IMG_20180116_172844.jpg";
        ImageView imageView = new ImageView(mContext);

        int imageSize = (int) (RevLibGenConstantine.REV_IMAGE_SIZE_LARGE * .7);

        File userIconFIle = new File(imgPath);

        if (userIconFIle.exists()) {
            Picasso.get()
                    .load(userIconFIle)
                    .placeholder(R.drawable.ic_account_circle_black_48dp)
                    .resize(imageSize, 0)
                    .into(imageView);
        } else {
            Picasso.get()
                    .load(R.drawable.ic_account_circle_black_48dp)
                    .placeholder(R.drawable.ic_account_circle_black_48dp)
                    .resize(RevLibGenConstantine.REV_IMAGE_SIZE_MEDIUM, 0)
                    .into(imageView);

            imageView.setColorFilter(ContextCompat.getColor(mContext, R.color.rcolorAccent_OPAQUE));

            new DownloadImageTask("http://i.imgur.com/DvpvklR.png", imageView).execute();
        }

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(RevLibGenConstantine.REV_IMAGE_SIZE_MEDIUM, RevLibGenConstantine.REV_IMAGE_SIZE_MEDIUM);
        layoutParams.gravity = (Gravity.TOP);
        imageView.setLayoutParams(layoutParams);

        LinearLayout revEntityTitle_LL = revCoreLayoutsLinearLayout.get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();

        TextView revEntityTitle = revCoreInputFormTextView.getRevExtraSmallNormalTextView_NO_PADDING();
        revEntityTitle.setGravity(Gravity.TOP);
        revEntityTitle.setText(finalText);
        revEntityTitle.setPadding(RevLibGenConstantine.REV_MARGIN_TINY, 0, 0, 0);

        LinearLayout.LayoutParams revEntityTitle_LP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        revEntityTitle_LP.setMargins(0, 0, 0, RevLibGenConstantine.REV_MARGIN_TINY);
        revEntityTitle.setLayoutParams(revEntityTitle_LP);

        revEntityTitle_LL.addView(imageView);
        revEntityTitle_LL.addView(revEntityTitle);

        /** TIME PUBLISHED SPAN **/

        String revEntityTimePublished_S = profileNameTtl + "'s profile pictures";

        SpannableString revEntityTimePublished_Span = new SpannableString(revEntityTimePublished_S);
        revEntityTimePublished_Span.setSpan(new AbsoluteSizeSpan((int) (RevLibGenConstantine.REV_TEXT_SIZE_TINY * .7)), 0, revEntityTimePublished_S.length(), SPAN_INCLUSIVE_INCLUSIVE);

        // let's put both spans together with a separator and all
        CharSequence revEntityTimeFinalText = TextUtils.concat(revEntityTimePublished_Span);

        TextView revEntityTime = revCoreInputFormTextView.getRevExtraSmallNormalTextView_NO_PADDING();
        revEntityTime.setText(revEntityTimeFinalText);
        revEntityTime.setPadding(0, RevLibGenConstantine.REV_MARGIN_TINY, 0, 1);

        revEntityTopMainItems.addView(revEntityTitle_LL);
        revEntityTopMainItems.addView(revEntityTime);

        return revEntityTopMainItems;
    }

}
