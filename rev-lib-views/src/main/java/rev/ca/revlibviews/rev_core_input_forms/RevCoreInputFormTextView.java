package rev.ca.revlibviews.rev_core_input_forms;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.revlibviews.R;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLinearLayoutLayoutParams;

/**
 * Created by rev on 10/17/17.
 */

public class RevCoreInputFormTextView {

    private Context mContext;

    private TextView textView;

    int tinyTextSize = RevLibGenConstantine.REV_TEXT_SIZE_TINY;
    int smallTextSize = RevLibGenConstantine.REV_TEXT_SIZE_SMALL;
    int mediumTextSize = RevLibGenConstantine.REV_TEXT_SIZE_MEDIUM;
    int largeTextSize = RevLibGenConstantine.REV_TEXT_SIZE_LARGE;

    public RevCoreInputFormTextView(Context context) {
        mContext = context;
    }

    public TextView getRevTextView() {
        textView = new TextView(mContext);
        textView.setTextColor(mContext.getResources().getColor(R.color.gray_text));
        textView.setLineSpacing(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1.0f, mContext.getResources().getDisplayMetrics()), 0.8f);

        LayoutParams layoutParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(layoutParams);

        return textView;
    }

    public TextView getRevTextViewNoPadding() {
        textView = this.getRevTextView();
        textView.setBackgroundColor(Color.TRANSPARENT);
        textView.setIncludeFontPadding(false);
        ((LinearLayout.LayoutParams) textView.getLayoutParams()).setMargins(0, 0, 0, 0);

        return textView;
    }

    public TextView getRevExtraSmallNormalTextView() {
        textView = this.getRevTextView();
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, tinyTextSize);
        return textView;
    }

    public TextView getRevExtraSmallNormalTextView_ITALICS() {
        textView = this.getRevExtraSmallNormalTextView_NO_PADDING();
        textView.setTypeface(null, Typeface.ITALIC);
        return textView;
    }

    public TextView getRevExtraSmallNormalTextView_NO_PADDING() {
        textView = this.getRevTextViewNoPadding();
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, tinyTextSize);
        return textView;
    }

    public TextView getRevExtraSmallNormalTextView_NO_PADDING(float textRescaleSize) {
        textView = this.getRevTextViewNoPadding();
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, tinyTextSize * textRescaleSize);
        return textView;
    }

    public TextView getRevExtraSmallNormalTextView_NO_PADDING_LINK() {
        textView = this.getRevTextViewNoPadding();
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, tinyTextSize);
        textView.setTextColor(mContext.getResources().getColor(R.color.teal_300_dark));
        return textView;
    }

    public TextView getRevExtraSmallNormalTextView_NO_PADDING_LINK(float textRescaleSize) {
        textView = this.getRevExtraSmallNormalTextView_NO_PADDING_LINK();
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, tinyTextSize * textRescaleSize);
        return textView;
    }

    public TextView getRevExtraSmallNormalBoldTextView_NO_PADDING_LINK(float textRescaleSize) {
        textView = this.getRevExtraSmallNormalTextView_NO_PADDING_LINK(textRescaleSize);
        textView.setTypeface(null, Typeface.BOLD);
        return textView;
    }

    public TextView getRevVeryExtraSmallNormalTextView_NO_PADDING() {
        textView = this.getRevTextViewNoPadding();
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, (float) (tinyTextSize * .7));
        return textView;
    }

    public TextView getRevVeryExtraSmallNormalTextView_NO_PADDING(float textRescaleSize) {
        textView = this.getRevTextViewNoPadding();
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, tinyTextSize * textRescaleSize);
        return textView;
    }

    public TextView getRevSmallNormalTextView() {
        textView = this.getRevTextView();
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
        return textView;
    }

    public TextView getRevSmallNormalTextView_NO_PADDING() {
        textView = this.getRevTextViewNoPadding();
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
        return textView;
    }

    public TextView getRevSmallBoldTextView() {
        textView = this.getRevTextViewNoPadding();
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
        textView.setTypeface(null, Typeface.BOLD);
        return textView;
    }

    public TextView getRevExtraSmallBoldTextView() {
        textView = this.getRevExtraSmallNormalTextView_NO_PADDING();
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, tinyTextSize);
        textView.setTypeface(null, Typeface.BOLD);
        return textView;
    }

    public TextView getRevExtraSmallBoldTextView(float v) {
        textView = this.getRevExtraSmallBoldTextView();
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, RevLibGenConstantine.REV_TEXT_SIZE_TINY * v);
        return textView;
    }

    public TextView getRevExtraSmallBoldTextView_NOPADDING(float v) {
        textView = this.getRevSmallNormalTextView_NO_PADDING();
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, RevLibGenConstantine.REV_TEXT_SIZE_TINY * v);
        textView.setTypeface(null, Typeface.BOLD);
        return textView;
    }

    public TextView getRevMediumNormalTextView() {
        textView = this.getRevTextView();
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, mediumTextSize);
        return textView;
    }

    public TextView getRevMediumBoldTextView() {
        textView = this.getRevMediumNormalTextView();
        textView.setTypeface(null, Typeface.BOLD);
        return textView;
    }

    public TextView getRevLargeNormalTextView() {
        textView = this.getRevTextView();
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, largeTextSize);
        return textView;
    }

    public TextView getRev_X_LargeNormalTextView() {
        textView = this.getRevTextView();
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, (float) (largeTextSize * 1.5));
        return textView;
    }

    public TextView getRevLargeBoldTextView() {
        textView = this.getRevMediumBoldTextView();
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, largeTextSize);
        return textView;
    }

    public TextView getRevFormHeader1TextView() {
        textView = this.getRevTextView();
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, mediumTextSize);
        return textView;
    }

    public TextView getRevSmallBoldTextView_InputFormTtl_COLORED() {
        textView = this.getRevSmallBoldTextView();
        textView.setBackgroundColor(mContext.getResources().getColor(R.color.greyExtraLight));
        textView.setLayoutParams(RevCoreLinearLayoutLayoutParams.getFormTtl_L_P());
        return textView;
    }

    public TextView getObjectListingFooterTabs() {
        textView = this.getRevExtraSmallNormalTextView();
        textView.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));

        return textView;
    }
}
