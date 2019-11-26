package rev.ca.rev_lib_core_views.rev_core_views.rev_page;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.squareup.picasso.Picasso;

import rev.ca.rev_lib_core_views.R;
import rev.ca.rev_lib_core_views.REV_DEC_STRING_VIEWS_BASE_FUNCTIONS;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormTextView;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;

public class RevNetworkResolverViews {

    private Context mContext;

    private RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout;
    private RevCoreInputFormTextView revCoreInputFormTextView;

    public RevNetworkResolverViews(Context mContext) {
        this.mContext = mContext;

        revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(mContext);
        revCoreInputFormTextView = new RevCoreInputFormTextView(mContext);
    }

    public View getNoRevNetworkResolverView() {
        LinearLayout linearLayoutContainer_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();

        int imageSize = RevLibGenConstantine.REV_IMAGE_SIZE_SMALL;

        ImageView headerIcon_IV = new ImageView(mContext);
        headerIcon_IV.setColorFilter(ContextCompat.getColor(mContext, R.color.revPurple));

        LinearLayout.LayoutParams headerIcon_IV_LP = new LinearLayout.LayoutParams(imageSize, imageSize);
        headerIcon_IV.setLayoutParams(headerIcon_IV_LP);
        ((LinearLayout.LayoutParams) headerIcon_IV.getLayoutParams()).gravity = Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL;

        Picasso.get()
                .load(R.drawable.baseline_priority_high_black_48dp)
                .resize(imageSize, imageSize)
                .centerCrop()
                .into(headerIcon_IV);

        TextView textView = revCoreInputFormTextView.getRevExtraSmallNormalTextView_NO_PADDING(.9f);
        textView.setText(REV_DEC_STRING_VIEWS_BASE_FUNCTIONS.makeRevDecString("CampAnn is unable to resolve the remote server.\nPlease check your connection then try again."));
        ((LinearLayout.LayoutParams) textView.getLayoutParams()).gravity = Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL;
        textView.setGravity(Gravity.CENTER_HORIZONTAL);

        linearLayoutContainer_LL.addView(headerIcon_IV);
        linearLayoutContainer_LL.addView(textView);

        return linearLayoutContainer_LL;
    }

    public View getNoRevNullEntityNetworkResolverView() {
        LinearLayout linearLayoutContainer_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();

        int imageSize = RevLibGenConstantine.REV_IMAGE_SIZE_SMALL;

        ImageView headerIcon_IV = new ImageView(mContext);
        headerIcon_IV.setColorFilter(ContextCompat.getColor(mContext, R.color.revPurple));

        LinearLayout.LayoutParams headerIcon_IV_LP = new LinearLayout.LayoutParams(imageSize, imageSize);
        headerIcon_IV.setLayoutParams(headerIcon_IV_LP);
        ((LinearLayout.LayoutParams) headerIcon_IV.getLayoutParams()).gravity = Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL;

        Picasso.get()
                .load(R.drawable.baseline_priority_high_black_48dp)
                .resize(imageSize, imageSize)
                .centerCrop()
                .into(headerIcon_IV);

        TextView textView = revCoreInputFormTextView.getRevExtraSmallNormalTextView_NO_PADDING(.9f);
        textView.setText(REV_DEC_STRING_VIEWS_BASE_FUNCTIONS.makeRevDecString("CampAnn resolved an empty query.\nPlease check your connection then try again."));
        ((LinearLayout.LayoutParams) textView.getLayoutParams()).gravity = Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL;
        textView.setGravity(Gravity.CENTER_HORIZONTAL);

        linearLayoutContainer_LL.addView(headerIcon_IV);
        linearLayoutContainer_LL.addView(textView);

        return linearLayoutContainer_LL;
    }

    public View getNoRevNullEntityNetworkResolverView(String revErrMessage) {
        LinearLayout linearLayoutContainer_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();

        int imageSize = RevLibGenConstantine.REV_IMAGE_SIZE_SMALL;

        ImageView headerIcon_IV = new ImageView(mContext);
        headerIcon_IV.setColorFilter(ContextCompat.getColor(mContext, R.color.revPurple));

        LinearLayout.LayoutParams headerIcon_IV_LP = new LinearLayout.LayoutParams(imageSize, imageSize);
        headerIcon_IV.setLayoutParams(headerIcon_IV_LP);
        ((LinearLayout.LayoutParams) headerIcon_IV.getLayoutParams()).gravity = Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL;

        Picasso.get()
                .load(R.drawable.baseline_priority_high_black_48dp)
                .resize(imageSize, imageSize)
                .centerCrop()
                .into(headerIcon_IV);

        TextView textView = revCoreInputFormTextView.getRevExtraSmallNormalTextView_NO_PADDING(.9f);
        textView.setText(REV_DEC_STRING_VIEWS_BASE_FUNCTIONS.makeRevDecString(revErrMessage));
        ((LinearLayout.LayoutParams) textView.getLayoutParams()).gravity = Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL;
        textView.setGravity(Gravity.CENTER_HORIZONTAL);

        linearLayoutContainer_LL.addView(headerIcon_IV);
        linearLayoutContainer_LL.addView(textView);

        return linearLayoutContainer_LL;
    }
}
