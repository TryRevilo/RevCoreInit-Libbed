package rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_plugin_views.rev_plugin_widget_views.rev_page_widgets.rev_publications_widget_views;

import android.content.Context;
import android.text.SpannableString;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.squareup.picasso.Picasso;

import java.io.File;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_lib_core_app_plugins.R;
import rev.ca.rev_lib_core_views.REV_DEC_STRING_VIEWS_BASE_FUNCTIONS;
import rev.ca.rev_lib_gen_functions.REV_STRINGS_BASE_FUNCTIONS;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormTextView;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;

public class RevQ_AND_A_View {

    private Context mContext;

    private RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout;
    private RevCoreInputFormTextView revCoreInputFormTextView;

    public RevQ_AND_A_View(RevVarArgsData revVarArgsData) {
        this.mContext = revVarArgsData.getmContext();

        revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(mContext);
        revCoreInputFormTextView = new RevCoreInputFormTextView(mContext);
    }

    public View revGet_Q_A_View() {
        LinearLayout linearLayout = revCoreLayoutsLinearLayout.getHorizontalRevLinearLayout_WRAPPED_ALL();

        int imgSize = (int) (RevLibGenConstantine.REV_IMAGE_SIZE_MEDIUM * 1.6);

        String imgPath = "/storage/emulated/0/DCIM/Camera/IMG_20180116_172844.jpg";
        ImageView imageView = new ImageView(mContext);

        if (new File(imgPath).exists()) {
            Picasso.get()
                    .load(new File(imgPath))
                    .resize(imgSize, imgSize)
                    .transform(new RoundedCornersTransformation(4, 1))
                    .centerCrop()
                    .into(imageView);
        }

        String items_S = "This method returns true if the character sequence represented by the argument is a suffix of the character sequence represent";
        SpannableString spannableString = REV_DEC_STRING_VIEWS_BASE_FUNCTIONS.makeRevDecString(REV_STRINGS_BASE_FUNCTIONS.REV_GET_SHORT_STRING(items_S, 88));

        ImageView headerIcon_IV = new ImageView(mContext);
        headerIcon_IV.setColorFilter(ContextCompat.getColor(mContext, R.color.teal_400_dark));

        int imageSize = RevLibGenConstantine.REV_IMAGE_SIZE_TINY;

        LinearLayout.LayoutParams headerIcon_IV_LP = new LinearLayout.LayoutParams(imageSize, imageSize);
        headerIcon_IV_LP.setMargins((int) (-imageSize * .2), (int) (RevLibGenConstantine.REV_MARGIN_TINY * 1.7), 0, 0);
        headerIcon_IV_LP.gravity = Gravity.CENTER_VERTICAL;
        headerIcon_IV.setLayoutParams(headerIcon_IV_LP);

        Picasso.get()
                .load(R.drawable.icons8_triangle_arrow_24)
                .resize(imageSize, imageSize)
                .centerCrop()
                .rotate(-90)
                .into(headerIcon_IV);

        TextView textView = revCoreInputFormTextView.getRevExtraSmallNormalTextView_NO_PADDING(.8f);
        textView.setText(spannableString);
        ((LinearLayout.LayoutParams) textView.getLayoutParams()).gravity = Gravity.BOTTOM;
        ((LinearLayout.LayoutParams) textView.getLayoutParams()).setMargins(0, RevLibGenConstantine.REV_MARGIN_SMALL, 0, 0);

        linearLayout.addView(imageView);
        linearLayout.addView(headerIcon_IV);
        linearLayout.addView(textView);

        return linearLayout;
    }
}
