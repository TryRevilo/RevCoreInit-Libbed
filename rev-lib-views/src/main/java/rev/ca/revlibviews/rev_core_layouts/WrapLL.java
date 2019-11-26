package rev.ca.revlibviews.rev_core_layouts;

import android.app.Activity;
import android.content.Context;
import android.view.Display;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * Created by rev on 10/26/17.
 */

public class WrapLL {

    private Context mContext;

    public WrapLL(Context mContext) {
        this.mContext = mContext;
    }

    public void populateTabLinks(LinearLayout linearLayout, ArrayList<View> tabsCollection) {
        Activity activity = (Activity) mContext;
        Display display = activity.getWindowManager().getDefaultDisplay();
        int maxWidth = display.getWidth();
        int tabWidth = (int) (maxWidth * .5);

        LinearLayout.LayoutParams contentView_LP = new LinearLayout.LayoutParams(
                tabWidth, LinearLayout.LayoutParams.WRAP_CONTENT);

        if (tabsCollection.size() > 0) {
            LinearLayout lLAlso = new RevCoreLayoutsLinearLayout(mContext).getHorizontalFormFooterRevLinearLayout();
            lLAlso.setLayoutParams(RevCoreLinearLayoutLayoutParams.getRev_MATCH_W_Wrap_H_LP());

            int widthSoFar = 0;
            for (int i = 0; i < tabsCollection.size(); i++) {
                View tab = tabsCollection.get(i);
                if (tab == null)
                    continue;

                tab.setLayoutParams(contentView_LP);
                widthSoFar += tabWidth;

                if ((i == 0) || (widthSoFar >= maxWidth)) {
                    linearLayout.addView(lLAlso);
                    lLAlso = new RevCoreLayoutsLinearLayout(mContext).getHorizontalRevLinearLayout();
                    lLAlso.setLayoutParams(RevCoreLinearLayoutLayoutParams.getRev_MATCH_W_Wrap_H_LP());

                    lLAlso.addView(tab);
                    widthSoFar = 0;
                } else if ((widthSoFar < maxWidth) && ((i != tabsCollection.size() - 1))) {
                    lLAlso.addView(tab);
                } else if (i == tabsCollection.size() - 1) {
                    lLAlso.addView(tab);
                    linearLayout.addView(lLAlso);
                }
            }
        }
    }
}
