package rev.ca.rev_lib_core_app_plugins.rev_mail_plugin.rev_plugin_views.rev_pluggable_menus;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import androidx.core.content.ContextCompat;
import android.util.ArrayMap;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import rev.ca.rev_lib_core_app_plugins.R;
import rev.ca.rev_lib_core_views.AbstractIRevPluggableViews;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevPersGenFunctions;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormTextView;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;

/**
 * Created by rev on 10/28/17.
 */

public class RevSetStripRevMail extends AbstractIRevPluggableViews {

    private RevVarArgsData revVarArgsData;
    private Context mContext;

    RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout;
    RevCoreInputFormTextView revCoreInputFormTextView;

    public RevSetStripRevMail(RevVarArgsData revVarArgsData) {
        super(RevPersGenFunctions.REV_VAR_ARGS_DATA_RESOLVER(revVarArgsData));
        this.revVarArgsData = RevPersGenFunctions.REV_VAR_ARGS_DATA_RESOLVER(revVarArgsData);
        this.mContext = this.revVarArgsData.getmContext();

        revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(mContext);
        revCoreInputFormTextView = new RevCoreInputFormTextView(mContext);
    }

    @Override
    public ArrayMap<View, View> createRevMerryllStripMenuViewItem() {
        ArrayMap<View, View> arrayMap = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            arrayMap = new ArrayMap<>();

            final LinearLayout linearLayout = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_ALL();

            TextView textView = new TextView(mContext);
            textView.setText("Test View");

            linearLayout.addView(textView);
            arrayMap.put(getRevStripTab_IV(), linearLayout);
        }
        return arrayMap;
    }

    public ImageView getRevStripTab_IV() {
        int imageSize = (int) (RevLibGenConstantine.REV_IMAGE_SIZE_MEDIUM * 0.7);
        LinearLayout.LayoutParams stripTabIcon_IV_LP = new LinearLayout.LayoutParams(
                imageSize, imageSize);
        stripTabIcon_IV_LP.setMargins(0, RevLibGenConstantine.REV_MARGIN_MEDIUM, 0, 0);
        stripTabIcon_IV_LP.gravity = Gravity.CENTER_HORIZONTAL;

        ImageView stripTabIcon_IV = new ImageView(mContext);
        stripTabIcon_IV.setPadding(0, 0, 0, 0);
        stripTabIcon_IV.setBackgroundColor(ContextCompat.getColor(mContext, R.color.rev_default_background));
        stripTabIcon_IV.setImageResource(R.drawable.icons8_email_64);

        stripTabIcon_IV.setColorFilter(ContextCompat.getColor(mContext, R.color.teal_dark));

        stripTabIcon_IV.setLayoutParams(stripTabIcon_IV_LP);

        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(10f); // Set corner radius
        gradientDrawable.setColor(ContextCompat.getColor(mContext, R.color.revColorAccentLight));

        return stripTabIcon_IV;
    }
}
