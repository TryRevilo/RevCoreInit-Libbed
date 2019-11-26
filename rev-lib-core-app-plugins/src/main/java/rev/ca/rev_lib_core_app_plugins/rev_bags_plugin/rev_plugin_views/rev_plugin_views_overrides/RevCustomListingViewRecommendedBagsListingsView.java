package rev.ca.rev_lib_core_app_plugins.rev_bags_plugin.rev_plugin_views.rev_plugin_views_overrides;

import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import rev.ca.rev_gen_lib_pers.RevDBModels.REV_PERS_REVMETADATA_GEN_FUNCTIONS;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntity;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_lib_core_app_plugins.R;
import rev.ca.rev_lib_core_app_plugins.my_fences_application.rev_plugin_views.rev_plugin_pages.REV_RESET_PAGE_CONTENT;
import rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_plugin_views.rev_object.RevUserFullProfileView;
import rev.ca.rev_lib_gen_functions.REV_STRINGS_BASE_FUNCTIONS;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormTextView;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;

/**
 * Created by rev on 2/21/18.
 */

public class RevCustomListingViewRecommendedBagsListingsView {

    private RevVarArgsData revVarArgsData;
    private Context mContext;

    private RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout;
    private RevCoreInputFormTextView revCoreInputFormTextView;

    private RevEntity revEntity;

    public RevCustomListingViewRecommendedBagsListingsView(RevVarArgsData revVarArgsData) {
        this.revVarArgsData = revVarArgsData;
        this.mContext = revVarArgsData.getmContext();

        this.revEntity = revVarArgsData.getRevEntity();

        revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(mContext);
        revCoreInputFormTextView = new RevCoreInputFormTextView(mContext);
    }

    public View getRevCustomListingViewRecommendedBagsListingsView() {
        LinearLayout linearLayout = revCoreLayoutsLinearLayout.get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        linearLayout.setPadding(RevLibGenConstantine.REV_MARGIN_SMALL * 2, 0, RevLibGenConstantine.REV_MARGIN_SMALL, 0);
        linearLayout.setBackgroundColor(Color.parseColor("#e0e0e0"));
        ((LinearLayout.LayoutParams) linearLayout.getLayoutParams()).setMargins(0, 1, 0, 0);

        TextView bagDesc_TV = revCoreInputFormTextView.getRevVeryExtraSmallNormalTextView_NO_PADDING(.8f);
        bagDesc_TV.setGravity(Gravity.CENTER_VERTICAL);
        bagDesc_TV.setPadding(RevLibGenConstantine.REV_MARGIN_TINY, 0, 0, RevLibGenConstantine.REV_MARGIN_TINY * 2);

        String bagTtl = REV_STRINGS_BASE_FUNCTIONS.REV_GET_SHORT_STRING(REV_PERS_REVMETADATA_GEN_FUNCTIONS.REV_GET_METADATA_VALUE(revEntity.get_revEntityMetadataList(), "rev_entity_full_names_value"), 55);
        String bagDescText = REV_STRINGS_BASE_FUNCTIONS.REV_GET_SHORT_STRING(REV_PERS_REVMETADATA_GEN_FUNCTIONS.REV_GET_METADATA_VALUE(revEntity.get_revEntityMetadataList(), "rev_entity_full_names_value"), 100);
        String sourceString = "<br><b>" + bagTtl + "</b> " + bagDescText;

        bagDesc_TV.setText(Html.fromHtml(sourceString));

        int imgSize = RevLibGenConstantine.REV_IMAGE_SIZE_SMALL;

        ImageView imageView = new ImageView(mContext);

        imageView.setLayoutParams(new LinearLayout.LayoutParams(imgSize, imgSize));
        ((LinearLayout.LayoutParams) imageView.getLayoutParams()).gravity = Gravity.CENTER_VERTICAL;

        Picasso.get()
                .load(R.drawable.ic_add_circle_black_48dp)
                .resize(imgSize, imgSize)
                .centerCrop()
                .into(imageView);

        linearLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                RevVarArgsData passRVD = new RevVarArgsData(mContext);
                passRVD.setRevEntity(revEntity);
                REV_RESET_PAGE_CONTENT.RESET_PAGE_OWNER(new RevUserFullProfileView(passRVD).getUserMainCenterCctViewLL());
            }
        });

        linearLayout.addView(imageView);
        linearLayout.addView(bagDesc_TV);

        return linearLayout;
    }
}
