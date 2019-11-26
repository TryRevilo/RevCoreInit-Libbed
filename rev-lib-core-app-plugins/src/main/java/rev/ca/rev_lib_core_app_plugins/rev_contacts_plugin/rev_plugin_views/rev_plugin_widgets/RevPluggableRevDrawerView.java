package rev.ca.rev_lib_core_app_plugins.rev_contacts_plugin.rev_plugin_views.rev_plugin_widgets;

import android.content.Context;
import android.graphics.drawable.Drawable;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import rev.ca.rev_lib_core_app_plugins.R;
import rev.ca.rev_lib_core_views.AbstractIRevPluggableViews;
import rev.ca.rev_lib_gen_functions.RevDimens;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevPersGenFunctions;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormTextView;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLinearLayoutLayoutParams;

/**
 * Created by rev on 11/5/17.
 */

public class RevPluggableRevDrawerView extends AbstractIRevPluggableViews {

    private RevVarArgsData revVarArgsData;
    private Context mContext;

    private RevDimens revDimens;
    private RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout;

    private LinearLayout.LayoutParams layoutParamsTtl_LP;

    int revDimensSize10, revImageSizeSmall, revImageSizeMedium;

    private RevCoreInputFormTextView revCoreInputFormTextView;

    public RevPluggableRevDrawerView(RevVarArgsData revVarArgsData) {
        super(RevPersGenFunctions.REV_VAR_ARGS_DATA_RESOLVER(revVarArgsData));
        this.revVarArgsData = RevPersGenFunctions.REV_VAR_ARGS_DATA_RESOLVER(revVarArgsData);
        this.mContext = this.revVarArgsData.getmContext();

        revDimens = new RevDimens(mContext);
        revDimensSize10 = revDimens.getRevSize10();
        revImageSizeSmall = revDimens.getRevImageSizeSmall();
        revImageSizeMedium = revDimens.getRevImageSizeMedium();

        revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(mContext);
        revCoreInputFormTextView = new RevCoreInputFormTextView(mContext);

        layoutParamsTtl_LP = new LinearLayout.LayoutParams(
                (int) (revDimensSize10 * 5.3), LinearLayout.LayoutParams.WRAP_CONTENT
        );
    }

    @Override
    public ArrayList<View> createPluggableRevDrawerMenuView() {
        ArrayList<View> viewArrayList = new ArrayList<>();

        LinearLayout linearLayout = new RevCoreLayoutsLinearLayout(mContext).getHorizontalRevLinearLayout();
        linearLayout.setBackgroundColor(ContextCompat.getColor(mContext, R.color.greyExtraLight));
        linearLayout.setPadding(0, revDimensSize10, 0, revDimensSize10);

        LinearLayout.LayoutParams layoutParams = RevCoreLinearLayoutLayoutParams.getRev_MATCH_W_Wrap_H_LP();
        layoutParams.setMargins(0, revDimens.getRevMarginMedium(), 0, 0);
        linearLayout.setLayoutParams(layoutParams);

        linearLayout.addView(this.getTotalContactsCount());

        viewArrayList.add(linearLayout);

        return viewArrayList;
    }

    private View getTotalContactsCount() {
        Drawable generalPointingAverageImg = mContext.getResources().getDrawable(R.drawable.ic_contacts_black_48dp);
        generalPointingAverageImg.setBounds(0, 0, (int) (revImageSizeSmall * 1.5), (int) (revImageSizeSmall * 1.5));
        DrawableCompat.setTint(generalPointingAverageImg, ContextCompat.getColor(mContext, R.color.revPurple));

        TextView totalContactsCount = revCoreInputFormTextView.getRevSmallBoldTextView();
        totalContactsCount.setText("  Total added contacts ");
        totalContactsCount.setCompoundDrawables(generalPointingAverageImg, null, null, null);

        TextView averageTxtTell = revCoreInputFormTextView.getRevSmallNormalTextView();
        averageTxtTell.setText("1K +");
        averageTxtTell.setBackgroundResource(rev.ca.revlibviews.R.drawable.rev_fancy_tab_left_offset_bottom_border);

        LinearLayout linearLayout = new RevCoreLayoutsLinearLayout(mContext).getHorizontalRevLinearLayout_WRAPPED_ALL();
        LinearLayout.LayoutParams layoutParams = RevCoreLinearLayoutLayoutParams.getRev_WRAP_CONTENT_MATCH_PARENT_LP();
        layoutParams.setMargins(revDimens.getRevMarginExtraSmall(), 0, 0, 0);
        linearLayout.setLayoutParams(layoutParams);

        linearLayout.addView(totalContactsCount);

        LinearLayout totalCountsContainer = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_WRAP_ALL();
        totalCountsContainer.addView(getTotalEmailsCount());
        totalCountsContainer.addView(getTotalPhoneCount());

        linearLayout.addView(totalCountsContainer);

        return linearLayout;
    }

    private View getTotalEmailsCount() {
        TextView ctsCountTell = revCoreInputFormTextView.getRevSmallNormalTextView();
        ctsCountTell.setText("E-Mails");
        ctsCountTell.setLayoutParams(layoutParamsTtl_LP);

        TextView eMailCctsCount = revCoreInputFormTextView.getRevSmallNormalTextView();
        eMailCctsCount.setText("1K +");

        LinearLayout emailsCountWrapper = new RevCoreLayoutsLinearLayout(mContext).getHorizontalRevLinearLayout_WRAPPED_ALL();
        emailsCountWrapper.setBackgroundResource(rev.ca.revlibviews.R.drawable.rev_fancy_tab_left_offset_bottom_border);

        emailsCountWrapper.addView(ctsCountTell);
        emailsCountWrapper.addView(eMailCctsCount);

        return emailsCountWrapper;
    }

    private View getTotalPhoneCount() {
        TextView ctsCountTell = revCoreInputFormTextView.getRevSmallNormalTextView();
        ctsCountTell.setText("Phone");
        ctsCountTell.setLayoutParams(layoutParamsTtl_LP);

        TextView eMailCctsCount = revCoreInputFormTextView.getRevSmallNormalTextView();
        eMailCctsCount.setText("1K +");

        LinearLayout emailsCountWrapper = new RevCoreLayoutsLinearLayout(mContext).getHorizontalRevLinearLayout_WRAPPED_ALL();
        emailsCountWrapper.setBackgroundResource(rev.ca.revlibviews.R.drawable.rev_fancy_tab_left_offset_bottom_border);

        emailsCountWrapper.addView(ctsCountTell);
        emailsCountWrapper.addView(eMailCctsCount);

        return emailsCountWrapper;
    }
}
