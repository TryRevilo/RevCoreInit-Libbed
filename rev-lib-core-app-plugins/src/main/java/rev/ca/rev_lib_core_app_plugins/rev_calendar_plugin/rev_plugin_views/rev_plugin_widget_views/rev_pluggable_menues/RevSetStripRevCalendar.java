package rev.ca.rev_lib_core_app_plugins.rev_calendar_plugin.rev_plugin_views.rev_plugin_widget_views.rev_pluggable_menues;

import android.content.Context;
import androidx.core.content.ContextCompat;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.ArrayMap;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import rev.ca.rev_lib_core_app_plugins.R;
import rev.ca.rev_lib_core_views.AbstractIRevPluggableViews;
import rev.ca.rev_lib_gen_functions.RevDimens;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevPersGenFunctions;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormTextView;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;

import static android.text.Spanned.SPAN_INCLUSIVE_INCLUSIVE;

/**
 * Created by rev on 10/28/17.
 */

public class RevSetStripRevCalendar extends AbstractIRevPluggableViews {

    private RevVarArgsData revVarArgsData;
    private Context mContext;
    private RevDimens revDimens;

    private RevCoreInputFormTextView revCoreInputFormTextView;

    public RevSetStripRevCalendar(RevVarArgsData revVarArgsData) {
        super(RevPersGenFunctions.REV_VAR_ARGS_DATA_RESOLVER(revVarArgsData));
        this.revVarArgsData = RevPersGenFunctions.REV_VAR_ARGS_DATA_RESOLVER(revVarArgsData);
        this.mContext = this.revVarArgsData.getmContext();

        revDimens = new RevDimens(mContext);

        revCoreInputFormTextView = new RevCoreInputFormTextView(mContext);
    }

    @Override
    public ArrayMap<View, View> createRevMerryllStripMenuViewItem() {
        ArrayMap<View, View> arrayMap = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            arrayMap = new ArrayMap<>();

            LinearLayout linearLayout = new RevCoreLayoutsLinearLayout(mContext).getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
            linearLayout.setPadding(0, 0, 0, RevLibGenConstantine.REV_MARGIN_MEDIUM);

            int addCalImageSize = RevLibGenConstantine.REV_IMAGE_SIZE_MEDIUM;

            ImageView imageView = new ImageView(mContext);
            imageView.setPadding(0, RevLibGenConstantine.REV_IMAGE_SIZE_TINY, 0, 0);

            LinearLayout.LayoutParams imageView_LP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            imageView_LP.gravity = (Gravity.CENTER_HORIZONTAL);

            Picasso.get()
                    .load(R.drawable.icons8_add_96)
                    .resize(addCalImageSize, addCalImageSize)
                    .centerCrop()
                    .into(imageView);

            imageView.setLayoutParams(imageView_LP);

            CalendarView calendarView = new CalendarView(mContext);
            LinearLayout.LayoutParams calendarView_LP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            calendarView_LP.setMargins(0, (int) (-RevLibGenConstantine.REV_MARGIN_LARGE * 1.5), 0, 0);
            calendarView.setLayoutParams(calendarView_LP);

            calendarView.setScaleY(.75f);

            calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

                @Override
                public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                    Toast.makeText(mContext, "DATE : " + year + " " + dayOfMonth + ", " + month, Toast.LENGTH_LONG).show();
                }
            });

            String revStatusTell_S = "You do not have any events in your Calendar so far . . .";

            SpannableString revStatusTell_Span = new SpannableString(revStatusTell_S);
            revStatusTell_Span.setSpan(new AbsoluteSizeSpan((int) (RevLibGenConstantine.REV_TEXT_SIZE_LARGE * .8)), 0, 1, SPAN_INCLUSIVE_INCLUSIVE);
            revStatusTell_Span.setSpan(new ForegroundColorSpan(RevLibGenConstantine.REV_CONTEXT.getResources().getColor(R.color.teal_500_dark)), 0, revStatusTell_S.length(), 0); // set color

            TextView revStatusTell_TV = revCoreInputFormTextView.getRevExtraSmallNormalTextView_NO_PADDING(.8f);
            ((LinearLayout.LayoutParams) revStatusTell_TV.getLayoutParams()).width = LinearLayout.LayoutParams.MATCH_PARENT;
            revStatusTell_TV.setText(revStatusTell_Span);
            ((LinearLayout.LayoutParams) revStatusTell_TV.getLayoutParams()).setMargins(RevLibGenConstantine.REV_MARGIN_LARGE, -RevLibGenConstantine.REV_MARGIN_LARGE * 2, 0, 0);

            linearLayout.addView(imageView);
            linearLayout.addView(calendarView);
            linearLayout.addView(revStatusTell_TV);

            ScrollView scrollView = new ScrollView(mContext);
            scrollView.setHorizontalScrollBarEnabled(false);

            scrollView.addView(linearLayout);

            arrayMap.put(getRevStripTab_IV(), scrollView);
        }
        return arrayMap;
    }

    public ImageView getRevStripTab_IV() {
        int imageSize = (int) (RevLibGenConstantine.REV_IMAGE_SIZE_MEDIUM * 0.7);
        LinearLayout.LayoutParams stripTabIcon_IV_LP = new LinearLayout.LayoutParams(
                imageSize, imageSize);
        stripTabIcon_IV_LP.setMargins(0, revDimens.getRevMarginMedium(), 0, 0);
        stripTabIcon_IV_LP.gravity = Gravity.CENTER_HORIZONTAL;

        ImageView stripTabIcon_IV = new ImageView(mContext);
        stripTabIcon_IV.setImageResource(R.drawable.baseline_calendar_today_black_48dp);
        stripTabIcon_IV.setPadding(0, 0, 0, 0);
        stripTabIcon_IV.setBackgroundColor(ContextCompat.getColor(mContext, R.color.rev_default_background));
        stripTabIcon_IV.setColorFilter(ContextCompat.getColor(mContext, R.color.teal_dark));

        stripTabIcon_IV.setLayoutParams(stripTabIcon_IV_LP);

        return stripTabIcon_IV;
    }
}
