package rev.ca.revlibviews;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;

import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;

/**
 * Created by rev on 10/26/17.
 */

public class RevPintCustWrapLL {

    private Context mContext;

    public RevPintCustWrapLL(Context mContext) {
        this.mContext = mContext;
    }

    public View populateTabLinks(ArrayList<View> tabsCollection) {
        LinearLayout linearLayout = new RevCoreLayoutsLinearLayout(mContext).getHorizontalRevLinearLayout_WRAPPED_ALL();
        LinearLayout linearLayoutLeft = new RevCoreLayoutsLinearLayout(mContext).getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        LinearLayout linearLayoutRight = new RevCoreLayoutsLinearLayout(mContext).getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();

        int tabWidth = RevLibGenConstantine.REV_IMAGE_SIZE_LARGE * 2;

        LinearLayout.LayoutParams contentView_LP = new LinearLayout.LayoutParams(
                tabWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
        contentView_LP.setMargins(RevLibGenConstantine.REV_MARGIN_SMALL, RevLibGenConstantine.REV_MARGIN_SMALL, 0, 0);

        if (tabsCollection.size() > 0) {
            for (int i = 0; i < tabsCollection.size(); i++) {
                View tab = tabsCollection.get(i);
                if (tab == null)
                    continue;

                tab.setLayoutParams(contentView_LP);

                if ((i % 2 == 0)) {
                    linearLayoutLeft.addView(tab);
                } else {
                    linearLayoutRight.addView(tab);
                }
            }

            linearLayout.addView(linearLayoutLeft);
            linearLayout.addView(linearLayoutRight);
        }

        return linearLayout;
    }
}
