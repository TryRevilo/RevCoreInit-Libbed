package rev.ca.rev_lib_core_views.rev_core_views.rev_page.rev_pers_views;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import com.squareup.picasso.Picasso;

import java.util.List;

import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntity;
import rev.ca.rev_gen_lib_pers.c_libs_core.RevPersLibCreate;
import rev.ca.rev_gen_lib_pers.c_libs_core.RevPersLibDelete;
import rev.ca.rev_gen_lib_pers.c_libs_core.RevPersLibRead;
import rev.ca.rev_gen_lib_pers.rev_server_client.rev_pers_views.RevPublisherViewTabs;
import rev.ca.rev_gen_lib_pers.rev_varags_data.REV_SESSION_SETTINGS;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_lib_core_views.R;
import rev.ca.rev_lib_core_views.REV_DEC_STRING_VIEWS_BASE_FUNCTIONS;
import rev.ca.rev_lib_core_views.rev_core_views.rev_input_form_views.IRevInputFormView;
import rev.ca.rev_lib_core_views.rev_core_views.rev_page.rev_page_menue_togglers.RevDropDownPopupMenue;
import rev.ca.rev_lib_core_views.rev_pluggable_views_impl.RevConstantinePluggableViewsServices;
import rev.ca.rev_lib_gen_functions.REV_STRINGS_BASE_FUNCTIONS;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormTextView;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;

/**
 * Created by rev on 11/26/17.
 */

public class RevObjectListingViewFooterTabs {

    private RevVarArgsData revVarArgsData;
    private Context mContext;

    RevEntity revEntity;

    private LinearLayout.LayoutParams footerTab_TV_LP;

    public RevObjectListingViewFooterTabs(RevVarArgsData revVarArgsData) {
        this.revVarArgsData = revVarArgsData;
        this.mContext = revVarArgsData.getmContext();
        this.revEntity = revVarArgsData.getRevEntity();

        footerTab_TV_LP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        footerTab_TV_LP.setMargins(0, 0, RevLibGenConstantine.REV_MARGIN_SMALL, 0);
    }

    public View getRevObjectListingViewFooterTabs() {
        LinearLayout objectListingFooterWrapper = new RevCoreLayoutsLinearLayout(mContext).getHorizontalRevLinearLayout_WRAPPED_ALL();
        LinearLayout.LayoutParams objectListingFooterWrapper_LP = (LinearLayout.LayoutParams) objectListingFooterWrapper.getLayoutParams();
        objectListingFooterWrapper_LP.setMargins(0, RevLibGenConstantine.REV_IMAGE_SIZE_TINY, 0, 0);

        // objectListingFooterWrapper.addView(this.revLikeItem());
        objectListingFooterWrapper.addView(this.revEditItem());
        objectListingFooterWrapper.addView(this.revShareItem());
        objectListingFooterWrapper.addView(this.revDeleteItem());
        objectListingFooterWrapper.addView(this.revMoreRevObjectActions());

        return objectListingFooterWrapper;
    }

    public View commentFooterView() {
        LinearLayout commentFooterViewWrapper_LL = new RevCoreLayoutsLinearLayout(mContext).get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();

        if (RevConstantinePluggableViewsServices.REV_PLUGIN_INPUT_FORMS_MAP.containsKey("RevCreateRevCommentsInputForm")) {
            RevVarArgsData postRVD = new RevVarArgsData();
            postRVD.setmContext(revVarArgsData.getmContext());
            postRVD.setRevEntity(revVarArgsData.getRevEntity());
            postRVD.setRevViewType("RevCreateRevCommentsInputForm");

            IRevInputFormView iRevInputFormView = (IRevInputFormView) RevConstantinePluggableViewsServices.revViewCreator_REV_PLUGIN_INPUT_FORMS_MAP(postRVD);
            commentFooterViewWrapper_LL.addView(iRevInputFormView.createRevInputForm());
        }

        return commentFooterViewWrapper_LL;
    }

    public View revLikeItem() {
        final String annotationName = "rev_entity_like";
        final long revEntityGUID = revEntity.get_revEntityGUID();
        final long revEntityOwnerGUID = revEntity.get_revEntityOwnerGUID();

        ImageView pointerInImageView = new ImageView(mContext);
        pointerInImageView.setColorFilter(ContextCompat.getColor(mContext, R.color.greyDark));

        int imageSize = RevLibGenConstantine.REV_IMAGE_SIZE_SMALL;

        Picasso.get()
                .load(R.drawable.ic_thumb_up_black_48dp)
                .resize(imageSize, imageSize)
                .centerCrop()
                .into(pointerInImageView);

        TextView footerTab_TV = new RevCoreInputFormTextView(mContext).getRevExtraSmallBoldTextView_NOPADDING(.7f);

        List<Long> totLikes = new RevPersLibRead().getAllRevEntityAnnoationIds_By_RevEntityContainer_GUID(annotationName, revEntityGUID);

        String likesTxt = "";

        RevPersLibRead revPersLibRead = new RevPersLibRead();

        if (totLikes.size() > 0) {
            String revLiker_S = revPersLibRead.revPersGetRevEntityName(revPersLibRead.getRevAnnotationOwnerGUID_ByAnnotationId(totLikes.get(0)));
            revLiker_S = REV_STRINGS_BASE_FUNCTIONS.REV_GET_FIRST_NAME(revLiker_S);

            if (totLikes.size() == 1)
                likesTxt = "Liked by " + revLiker_S;
            else if (totLikes.size() > 1)
                likesTxt = "Liked by " + revLiker_S + " + " + (totLikes.size() - 1) + " more";
        }

        footerTab_TV.setText(REV_DEC_STRING_VIEWS_BASE_FUNCTIONS.makeRevDecString(likesTxt));
        footerTab_TV.setGravity(Gravity.CENTER_VERTICAL);
        ((LinearLayout.LayoutParams) footerTab_TV.getLayoutParams()).gravity = Gravity.CENTER_VERTICAL;
        ((LinearLayout.LayoutParams) footerTab_TV.getLayoutParams()).setMargins(RevLibGenConstantine.REV_MARGIN_TINY, 0, RevLibGenConstantine.REV_MARGIN_SMALL, 0);

        LinearLayout linearLayout = new RevCoreLayoutsLinearLayout(mContext).getHorizontalRevLinearLayout_WRAPPED_ALL();

        linearLayout.addView(pointerInImageView);
        linearLayout.addView(footerTab_TV);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RevPersLibRead revPersLibRead = new RevPersLibRead();

                int annotaionExists = revPersLibRead.revEntityAnnotationExists(annotationName, revEntityGUID, revEntityOwnerGUID);

                if (annotaionExists > 0) {
                    revPersLibRead.getRevEntityAnnoationValueIdBy_revAnnotationName_RevEntityGUID_RevEntityOwnerGUID(annotationName, revEntityGUID, revEntityOwnerGUID);
                } else {
                    new RevPersLibCreate().revPersRevEntityAnnotationWithValues(annotationName, String.valueOf(1), revEntity.get_revEntityGUID(), REV_SESSION_SETTINGS.getRevLoggedInEntityGuid());
                }
            }
        });

        return linearLayout;
    }

    public View revCommentItem() {
        TextView footerTab_TV = new RevCoreInputFormTextView(mContext).getObjectListingFooterTabs();
        this.initFooterTab_TV(footerTab_TV, R.drawable.ic_comment_black_48dp);

        return footerTab_TV;
    }

    public View revEditItem() {
        TextView footerTab_TV = new RevCoreInputFormTextView(mContext).getObjectListingFooterTabs();
        this.initFooterTab_TV(footerTab_TV, R.drawable.ic_mode_edit_black_48dp);

        return footerTab_TV;
    }


    public View revMoreOptionsItem() {
        TextView footerTab_TV = new RevCoreInputFormTextView(mContext).getObjectListingFooterTabs();
        footerTab_TV.setText("Options");
        this.initFooterTab_TV(footerTab_TV, R.drawable.ic_view_list_black_48dp);

        footerTab_TV.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new RevDropDownPopupMenue().showStatusPopup((Activity) mContext, v);
            }
        });

        return footerTab_TV;
    }

    public View revShareItem() {
        TextView footerTab_TV = new RevCoreInputFormTextView(mContext).getObjectListingFooterTabs();
        this.initFooterTab_TV(footerTab_TV, R.drawable.ic_share_black_48dp);

        return footerTab_TV;
    }

    public View revDeleteItem() {
        TextView footerTab_TV = new RevCoreInputFormTextView(mContext).getObjectListingFooterTabs();
        this.initFooterTab_TV(footerTab_TV, R.drawable.ic_delete_black_48dp);

        footerTab_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new RevPublisherViewTabs(mContext).deleteRevEntity();
                new RevPersLibDelete().deleteRevEntityByEntityGUID(revEntity.get_revEntityGUID());
            }
        });

        return footerTab_TV;
    }

    public View revMoreRevObjectActions() {
        TextView footerTab_TV = new RevCoreInputFormTextView(mContext).getObjectListingFooterTabs();
        this.initFooterTab_TV(footerTab_TV, R.drawable.ic_expand_more_black_48dp);

        return footerTab_TV;
    }

    private void initFooterTab_TV(TextView footerTab_TV, int drawableRes) {
        footerTab_TV.setLayoutParams(footerTab_TV_LP);
        footerTab_TV.setCompoundDrawablePadding(RevLibGenConstantine.REV_IMAGE_SIZE_TINY);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(0, 0, RevLibGenConstantine.REV_MARGIN_TINY, 0);
        footerTab_TV.setLayoutParams(layoutParams);

        Drawable footerTab_DR = mContext.getResources().getDrawable(drawableRes);
        footerTab_DR.setBounds(0, 0, RevLibGenConstantine.REV_IMAGE_SIZE_SMALL, RevLibGenConstantine.REV_IMAGE_SIZE_SMALL);
        DrawableCompat.setTint(footerTab_DR, ContextCompat.getColor(mContext, R.color.greyDark));
        footerTab_TV.setCompoundDrawables(footerTab_DR, null, null, null);
    }
}
