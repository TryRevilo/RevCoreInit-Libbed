package rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_plugin_views.rev_plugin_widget_views.rev_page_widgets;

import android.content.Context;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.squareup.picasso.Picasso;

import rev.ca.rev_gen_lib_pers.RevDBModels.REV_PERS_REVMETADATA_GEN_FUNCTIONS;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntity;
import rev.ca.rev_gen_lib_pers.rev_varags_data.REV_SESSION_SETTINGS;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_lib_core_app_plugins.R;
import rev.ca.rev_lib_core_views.REV_CORE_VIEWS_REFACTORING_BASE_FUNCTIONS;
import rev.ca.rev_lib_core_views.rev_pluggable_views_impl.RevConstantinePluggableViewsServices;
import rev.ca.rev_lib_gen_functions.REV_STRINGS_BASE_FUNCTIONS;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormTextView;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLinearLayoutLayoutParams;

import static android.text.Spanned.SPAN_INCLUSIVE_INCLUSIVE;

public class RevPageTopBarPageOwnerNameWidget {

    private Context mContext;
    private RevEntity revEntity;

    private RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout;

    public RevPageTopBarPageOwnerNameWidget(RevVarArgsData revVarArgsData) {
        this.mContext = revVarArgsData.getmContext();
        this.revEntity = revVarArgsData.getRevEntity();

        this.revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(mContext);
    }

    public void revReloadPageTopBarPageOwnerNameWidget() {
        REV_CORE_VIEWS_REFACTORING_BASE_FUNCTIONS.REV_RESET_CONTENT_VIEW(
                ((ViewGroup) RevConstantinePluggableViewsServices.ALL_SIMPLE_PLUGGABLE_REV_INLINE_VIEWS.get("PLUGGABLE_REV_MAIN_CENTER_CCT_VIEW_LL_TOP_BAR_LEFT")),
                this.fullNamesView());
    }

    private View fullNamesView() {
        int revVPadding = (int) (RevLibGenConstantine.REV_MARGIN_SMALL * .6);

        LinearLayout fullNamesViewWrapper_LL = revCoreLayoutsLinearLayout.getHorizontalRevLinearLayout_WRAPPED_ALL();
        fullNamesViewWrapper_LL.setPadding(0, revVPadding, 0, revVPadding);
        ((LinearLayout.LayoutParams) fullNamesViewWrapper_LL.getLayoutParams()).setMargins((int) (RevLibGenConstantine.REV_MARGIN_SMALL * .7), 0, 0, 0);
        ((LinearLayout.LayoutParams) fullNamesViewWrapper_LL.getLayoutParams()).gravity = Gravity.CENTER_VERTICAL;

        int revUserIconSize = RevLibGenConstantine.REV_IMAGE_SIZE_SMALL;

        ImageView imageView = new ImageView(mContext);
        imageView.setColorFilter(ContextCompat.getColor(mContext, R.color.revWhite));

        Picasso.get()
                .load(rev.ca.rev_lib_core_views.R.drawable.ic_account_circle)
                .resize(revUserIconSize, revUserIconSize)
                .centerCrop()
                .into(imageView);

        LinearLayout.LayoutParams imageView_LP = new LinearLayout.LayoutParams(revUserIconSize, revUserIconSize);
        imageView_LP.gravity = Gravity.CENTER_VERTICAL;
        imageView.setLayoutParams(imageView_LP);

        TextView fullNames_TV = new RevCoreInputFormTextView(mContext).getRevSmallBoldTextView();
        fullNames_TV.setTextColor(ContextCompat.getColor(mContext, rev.ca.revlibviews.R.color.teal_500_dark));
        fullNames_TV.setPadding(RevLibGenConstantine.REV_MARGIN_TINY, 0, 0, 0);
        fullNames_TV.setGravity(Gravity.CENTER_VERTICAL);

        LinearLayout.LayoutParams vView_LL_LP = RevCoreLinearLayoutLayoutParams.getRev_WRAP_ALL_LP();
        vView_LL_LP.gravity = Gravity.CENTER_VERTICAL;
        fullNames_TV.setLayoutParams(vView_LL_LP);

        if (revEntity == null)
            revEntity = REV_SESSION_SETTINGS.getRevLoggedInPageRevVarArgsData().getRevEntity();

        if (this.revEntity != null) {
            String mainTextLeadString = REV_PERS_REVMETADATA_GEN_FUNCTIONS.REV_GET_METADATA_VALUE(revEntity.get_revEntityMetadataList(), "rev_entity_full_names_value");
            String mainTextLeadStringReduced = REV_STRINGS_BASE_FUNCTIONS.REV_GET_SHORT_STRING(String.valueOf(mainTextLeadString), 22);

            SpannableString profileNameTtlSpan = new SpannableString(mainTextLeadStringReduced);
            profileNameTtlSpan.setSpan(new AbsoluteSizeSpan((int) (RevLibGenConstantine.REV_TEXT_SIZE_LARGE * .85)), 0, 1, SPAN_INCLUSIVE_INCLUSIVE);
            profileNameTtlSpan.setSpan(new AbsoluteSizeSpan(RevLibGenConstantine.REV_TEXT_SIZE_TINY), 2, (mainTextLeadStringReduced.length() - (mainTextLeadStringReduced.length() / 2)), SPAN_INCLUSIVE_INCLUSIVE);
            profileNameTtlSpan.setSpan(new ForegroundColorSpan(RevLibGenConstantine.REV_CONTEXT.getResources().getColor(R.color.teal_500_dark)), 0, mainTextLeadStringReduced.length(), 0); // set color

            fullNames_TV.setText(profileNameTtlSpan);
        }

        fullNamesViewWrapper_LL.addView(imageView);
        fullNamesViewWrapper_LL.addView(fullNames_TV);

        return fullNamesViewWrapper_LL;
    }

}
