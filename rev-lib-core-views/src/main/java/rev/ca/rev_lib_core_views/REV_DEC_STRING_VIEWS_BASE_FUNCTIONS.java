package rev.ca.rev_lib_core_views;

import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;

import rev.ca.rev_lib_gen_functions.REV_STRINGS_BASE_FUNCTIONS;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;

import static android.text.Spanned.SPAN_INCLUSIVE_INCLUSIVE;

public class REV_DEC_STRING_VIEWS_BASE_FUNCTIONS {

    public static SpannableString makeRevDecString(String revDec) {
        if (REV_STRINGS_BASE_FUNCTIONS.REV_IS_NULL_OR_EMPTY_STRING(revDec))
            return SpannableString.valueOf("");

        if (revDec.length() < 3) {
            while (revDec.length() < 4) {
                revDec = revDec + " .";
            }
        }

        SpannableString revDecSpan = new SpannableString(revDec);

        if (revDec.length() > 1) {
            StyleSpan boldSpan = new StyleSpan(Typeface.BOLD);
            revDecSpan.setSpan(boldSpan, 0, revDec.length() / 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            if (revDec.length() <= 3) {
                revDecSpan.setSpan(new AbsoluteSizeSpan((int) (RevLibGenConstantine.REV_TEXT_SIZE_LARGE * .35)), 0, revDec.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            } else {
                revDecSpan.setSpan(new AbsoluteSizeSpan((int) (RevLibGenConstantine.REV_TEXT_SIZE_LARGE * .35)), 0, 4, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            }

            if (revDec.length() > 8)
                revDecSpan.setSpan(new AbsoluteSizeSpan(RevLibGenConstantine.REV_TEXT_SIZE_TINY), 5, (revDec.length() - (revDec.length() / 2)), Spannable.SPAN_INCLUSIVE_INCLUSIVE);

            revDecSpan.setSpan(new ForegroundColorSpan(RevLibGenConstantine.REV_CONTEXT.getResources().getColor(R.color.teal_500_dark)), 0, revDec.length(), 0); // set color
        }

        return revDecSpan;
    }

    public static SpannableString makeRevDecStringWhite(String revDec) {
        SpannableString revDecSpan = new SpannableString(revDec);
        StyleSpan boldSpan = new StyleSpan(Typeface.BOLD);
        revDecSpan.setSpan(boldSpan, 0, revDec.length() / 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        revDecSpan.setSpan(new AbsoluteSizeSpan((int) (RevLibGenConstantine.REV_TEXT_SIZE_LARGE * .35)), 0, 4, SPAN_INCLUSIVE_INCLUSIVE);

        if (revDec.length() > 8)
            revDecSpan.setSpan(new AbsoluteSizeSpan(RevLibGenConstantine.REV_TEXT_SIZE_TINY), 5, (revDec.length() - (revDec.length() / 2)), SPAN_INCLUSIVE_INCLUSIVE);

        revDecSpan.setSpan(new ForegroundColorSpan(RevLibGenConstantine.REV_CONTEXT.getResources().getColor(R.color.rev_default_background)), 0, revDec.length(), 0); // set color

        return revDecSpan;
    }
}
