package rev.ca.revlibviews.rev_core_menues;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import androidx.core.content.ContextCompat;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import rev.ca.rev_lib_gen_functions.RevDimens;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.revlibviews.R;

/**
 * Created by rev on 10/24/17.
 */

public class RevCoreColoredTabs {

    private Context mContext;
    private TextView coloredTab_TV;
    private RevDimens revDimens;

    public RevCoreColoredTabs(Context mContext) {
        this.mContext = mContext;
        revDimens = new RevDimens(mContext);
    }

    public TextView getRevColoredTab() {
        LinearLayout.LayoutParams coloredTab_TV_LP = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        coloredTab_TV = new TextView(mContext);
        coloredTab_TV.setTextSize(TypedValue.COMPLEX_UNIT_PX, revDimens.getRevTextSizeTiny());
        coloredTab_TV.setLayoutParams(coloredTab_TV_LP);

        int paddingH = RevLibGenConstantine.REV_MARGIN_SMALL;
        int paddingV = (int) (RevLibGenConstantine.REV_MARGIN_TINY * .7);

        coloredTab_TV.setPadding(paddingH, paddingV, paddingH, paddingV);

        // Use a GradientDrawable with only one color set, to make it a solid color
        GradientDrawable border = new GradientDrawable();
        border.setColor(ContextCompat.getColor(mContext, R.color.revColorAccentLight)); //white background
        border.setStroke(0, ContextCompat.getColor(mContext, R.color.darkText)); // black border with full opacity
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            coloredTab_TV.setBackgroundDrawable(border);
        } else {
            coloredTab_TV.setBackground(border);
        }

        return coloredTab_TV;
    }
}
