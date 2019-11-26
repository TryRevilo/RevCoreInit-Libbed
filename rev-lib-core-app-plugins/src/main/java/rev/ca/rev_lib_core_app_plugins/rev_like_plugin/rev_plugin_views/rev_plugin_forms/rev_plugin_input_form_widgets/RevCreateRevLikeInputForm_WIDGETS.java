package rev.ca.rev_lib_core_app_plugins.rev_like_plugin.rev_plugin_views.rev_plugin_forms.rev_plugin_input_form_widgets;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntity;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevAnnotation;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_lib_core_views.REV_DEC_STRING_VIEWS_BASE_FUNCTIONS;
import rev.ca.rev_lib_core_views.RevViewsTapDance;
import rev.ca.rev_lib_core_views.rev_pluggable_views_impl.RevConstantinePluggableViewsServices;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormTextView;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;

/**
 * Created by rev on 1/3/18.
 */

public class RevCreateRevLikeInputForm_WIDGETS {

    private Context mContext;

    private RevEntity revEntity;
    private List<RevAnnotation> revAnnotationList;

    private RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout;

    private TextView revLikedCount_TV, revUnlikeCount_TV;
    private ImageView pointerInImageView;

    int likeCount, unlikeCount = 0;

    public RevCreateRevLikeInputForm_WIDGETS(RevVarArgsData revVarArgsData) {
        this.mContext = revVarArgsData.getmContext();

        this.revEntity = revVarArgsData.getRevEntity();
        this.revAnnotationList = revEntity.get_revAnnotations();

        likeCount = this.revAnnotationList.size();

        revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(mContext);

        revLikedCount_TV = new RevCoreInputFormTextView(mContext).getRevExtraSmallBoldTextView_NOPADDING(.7f);
        revUnlikeCount_TV = new RevCoreInputFormTextView(mContext).getRevExtraSmallBoldTextView_NOPADDING(.7f);

        pointerInImageView = new ImageView(mContext);
        pointerInImageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        ((LinearLayout.LayoutParams) pointerInImageView.getLayoutParams()).gravity = Gravity.CENTER_VERTICAL;

        int imageSize = RevLibGenConstantine.REV_IMAGE_SIZE_SMALL;

        Picasso.get()
                .load(rev.ca.rev_lib_core_views.R.drawable.icons8_vertical_line_50)
                .resize(imageSize, imageSize)
                .centerCrop()
                .into(pointerInImageView);

        revVarArgsData.getRevPassViews().get(0).setClickable(true);

        new RevViewsTapDance(new Integer[]{2, 3}, revVarArgsData.getRevPassViews().get(0), new RevViewsTapDance.IRevViewsTapDance() {
            @Override
            public void revCaller(int revTapDanceCount) {
                if (revTapDanceCount == 2) {
                    likeCount++;

                    long revLikeId = (long) RevConstantinePluggableViewsServices.REV_PLUGIN_START_REV_PERS_ACTIONS_MAP.get("RevPublishLikeAction").initRevAction(revEntity);

                    if (revLikeId > 0) {
                        revReloadLikesCountView();
                        Toast.makeText(mContext, "i LikE tHis", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(mContext, "tHERE wAs AN ERRoR posTiNG my LikE", Toast.LENGTH_SHORT).show();
                    }
                } else if (revTapDanceCount == 3) {
                    unlikeCount += 1000;
                    Toast.makeText(mContext, "unLikED", Toast.LENGTH_SHORT).show();
                    revReloadUnlikesCountView(revAnnotationList);
                }

                if ((likeCount + unlikeCount) > 0) pointerInImageView.setVisibility(View.VISIBLE);
            }
        });

        revReloadLikesCountView();
        revReloadUnlikesCountView(revAnnotationList);

        if ((likeCount + unlikeCount) == 0) {
            pointerInImageView.setVisibility(View.GONE);
        }
    }

    public View getRevInputForm() {
        LinearLayout revLikesWrapper = revCoreLayoutsLinearLayout.get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        revLikesWrapper.addView(this.revLikedCount_TV);

        revLikesWrapper.addView(pointerInImageView);
        revLikesWrapper.addView(this.revUnlikeCount_TV);

        return revLikesWrapper;
    }

    void revReloadLikesCountView() {
        if (likeCount < 1) {
            revLikedCount_TV.setVisibility(View.GONE);
        } else revLikedCount_TV.setVisibility(View.VISIBLE);

        String likesTxt = "likED by ";

        if (likeCount == 1) {
            likesTxt += likeCount;
        } else if (likeCount > 1) {
            likesTxt += " +" + likeCount;
        } else return;

        revLikedCount_TV.setText(REV_DEC_STRING_VIEWS_BASE_FUNCTIONS.makeRevDecString(likesTxt));
        revLikedCount_TV.setGravity(Gravity.CENTER_VERTICAL);
        ((LinearLayout.LayoutParams) revLikedCount_TV.getLayoutParams()).gravity = Gravity.CENTER_VERTICAL;
    }


    void revReloadUnlikesCountView(List<RevAnnotation> revAnnotationList) {
        int revTotLikes = unlikeCount;

        if (unlikeCount < 1) {
            revUnlikeCount_TV.setVisibility(View.GONE);
        } else revUnlikeCount_TV.setVisibility(View.VISIBLE);

        String likesTxt = "uNlikED by ";

        if (revTotLikes > 0) {
            if (revTotLikes == 1) {
                likesTxt += revTotLikes;
            } else if (revTotLikes > 1) {
                likesTxt += " mE +" + revTotLikes;
            }
        } else return;

        revUnlikeCount_TV.setText(REV_DEC_STRING_VIEWS_BASE_FUNCTIONS.makeRevDecString(likesTxt));
        revUnlikeCount_TV.setGravity(Gravity.CENTER_VERTICAL);
        ((LinearLayout.LayoutParams) revUnlikeCount_TV.getLayoutParams()).gravity = Gravity.CENTER_VERTICAL;
    }
}
