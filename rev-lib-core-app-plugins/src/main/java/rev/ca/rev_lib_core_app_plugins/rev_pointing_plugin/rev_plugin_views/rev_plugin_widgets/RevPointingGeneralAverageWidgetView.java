package rev.ca.rev_lib_core_app_plugins.rev_pointing_plugin.rev_plugin_views.rev_plugin_widgets;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import rev.ca.rev_lib_core_app_plugins.R;
import rev.ca.rev_lib_core_views.AbstractIRevPluggableViews;
import rev.ca.rev_lib_gen_functions.RevDimens;
import rev.ca.rev_lib_gen_functions.RevFileExplorer;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevPersGenFunctions;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormTextView;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLinearLayoutLayoutParams;

/**
 * Created by rev on 11/5/17.
 */

public class RevPointingGeneralAverageWidgetView extends AbstractIRevPluggableViews {

    private RevVarArgsData revVarArgsData;
    private Context mContext;

    RevDimens revDimens;
    int revImageSizeSmall, revImageSizeMedium;

    private RevCoreInputFormTextView revCoreInputFormTextView;

    public RevPointingGeneralAverageWidgetView(RevVarArgsData revVarArgsData) {
        super(RevPersGenFunctions.REV_VAR_ARGS_DATA_RESOLVER(revVarArgsData));
        this.revVarArgsData = RevPersGenFunctions.REV_VAR_ARGS_DATA_RESOLVER(revVarArgsData);
        this.mContext = this.revVarArgsData.getmContext();

        revDimens = new RevDimens(mContext);
        revImageSizeSmall = revDimens.getRevImageSizeSmall();
        revImageSizeMedium = revDimens.getRevImageSizeMedium();

        revCoreInputFormTextView = new RevCoreInputFormTextView(mContext);
    }

    @Override
    public ArrayList<View> createPluggableRevDrawerMenuView() {
        ArrayList<View> viewArrayList = new ArrayList<>();

        LinearLayout linearLayout = new RevCoreLayoutsLinearLayout(mContext).getHorizontalRevLinearLayout_WRAPPED_ALL();
        linearLayout.setBackgroundColor(ContextCompat.getColor(mContext, R.color.greyExtraLight));
        linearLayout.setBackgroundDrawable(mContext.getResources().getDrawable(rev.ca.revlibviews.R.drawable.rev_bottom_border_only));

        LinearLayout.LayoutParams layoutParams = RevCoreLinearLayoutLayoutParams.getRev_WRAP_CONTENT_MATCH_PARENT_LP();
        layoutParams.setMargins(revDimens.getRevMarginSmall(), 0, 0, 0);
        layoutParams.gravity = Gravity.CENTER_VERTICAL;

        linearLayout.addView(this.getGeneralPointingAverage());
        linearLayout.addView(this.getGeneralPointingUpVotes(), layoutParams);
        linearLayout.addView(this.getGeneralPointingDownVotes(), layoutParams);

        viewArrayList.add(linearLayout);

        return viewArrayList;
    }

    private View getGeneralPointingAverage() {
        TextView generalPointingAverage = revCoreInputFormTextView.getRevLargeNormalTextView();
        generalPointingAverage.setText("1, 154");

        generalPointingAverage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(mContext, RevFileExplorer.class);
                mContext.startActivity(myIntent);
            }
        });

        Drawable profileBttnImg = mContext.getResources().getDrawable(R.drawable.ic_vertical_align_center_black_48dp);
        profileBttnImg.setBounds(0, 0, revImageSizeMedium, revImageSizeMedium);
        DrawableCompat.setTint(profileBttnImg, ContextCompat.getColor(mContext, R.color.revPurple));
        generalPointingAverage.setCompoundDrawables(profileBttnImg, null, null, null);

        TextView averageTxtTell = revCoreInputFormTextView.getRevSmallNormalTextView();
        averageTxtTell.setText(" Average");

        LinearLayout linearLayout = new RevCoreLayoutsLinearLayout(mContext).getHorizontalRevLinearLayout_WRAPPED_ALL();

        linearLayout.addView(generalPointingAverage);
        linearLayout.addView(averageTxtTell);

        return linearLayout;
    }

    private View getGeneralPointingUpVotes() {
        Drawable generalPointingAverageImg = mContext.getResources().getDrawable(R.drawable.ic_vertical_align_top_black_48dp);
        generalPointingAverageImg.setBounds(0, 0, revImageSizeSmall, revImageSizeSmall);
        DrawableCompat.setTint(generalPointingAverageImg, ContextCompat.getColor(mContext, R.color.revPurple));

        TextView generalPointingAverage = revCoreInputFormTextView.getRevSmallBoldTextView();
        generalPointingAverage.setText("17");
        generalPointingAverage.setBackgroundResource(rev.ca.revlibviews.R.drawable.rev_fancy_tab_left_offset_bottom_border);
        generalPointingAverage.setCompoundDrawables(generalPointingAverageImg, null, null, null);
        return generalPointingAverage;
    }

    private View getGeneralPointingDownVotes() {
        Drawable generalPointingAverageImg = mContext.getResources().getDrawable(R.drawable.ic_vertical_align_bottom_black_48dp);
        generalPointingAverageImg.setBounds(0, 0, revImageSizeSmall, revImageSizeSmall);
        DrawableCompat.setTint(generalPointingAverageImg, ContextCompat.getColor(mContext, R.color.revPurple));

        TextView generalPointingAverage = revCoreInputFormTextView.getRevSmallBoldTextView();
        generalPointingAverage.setText("4");
        generalPointingAverage.setBackgroundResource(rev.ca.revlibviews.R.drawable.rev_fancy_tab_left_offset_bottom_border);
        generalPointingAverage.setCompoundDrawables(generalPointingAverageImg, null, null, null);
        return generalPointingAverage;
    }
}
