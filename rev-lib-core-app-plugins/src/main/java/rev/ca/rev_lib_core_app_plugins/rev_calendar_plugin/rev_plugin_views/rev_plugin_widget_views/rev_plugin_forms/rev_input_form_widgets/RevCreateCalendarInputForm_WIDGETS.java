package rev.ca.rev_lib_core_app_plugins.rev_calendar_plugin.rev_plugin_views.rev_plugin_widget_views.rev_plugin_forms.rev_input_form_widgets;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import androidx.core.content.ContextCompat;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import rev.ca.rev_lib_core_app_plugins.R;
import rev.ca.rev_lib_core_views.rev_pluggable_views_impl.RevConstantinePluggableViewsServices;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.revlibpersistence.rev_persistence.FeedReaderContract;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntity;
import rev.ca.rev_gen_lib_pers.RevDBModels.rev_entity_subtypes.RevObjectEntity;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.revlibviews.REV_VIEWS_BASE_FUNCTIONS;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormEditText;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormTextView;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;
import rev.ca.revlibviews.rev_core_menues.RevCoreColoredTabs;

import static android.graphics.drawable.GradientDrawable.RECTANGLE;

/**
 * Created by rev on 1/3/18.
 */

public class RevCreateCalendarInputForm_WIDGETS {

    private RevVarArgsData revVarArgsData;

    private Context mContext;
    private RevCoreInputFormTextView revCoreInputFormTextView;
    private RevCoreInputFormEditText revCoreInputFormEditText;
    private RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout;

    private EditText revEntityName, revEntityDescription;

    private Calendar myCalendar;

    public RevCreateCalendarInputForm_WIDGETS(RevVarArgsData revVarArgsData) {
        this.revVarArgsData = revVarArgsData;
        this.mContext = revVarArgsData.getmContext();

        revCoreInputFormTextView = new RevCoreInputFormTextView(mContext);
        revCoreInputFormEditText = new RevCoreInputFormEditText(mContext);
        revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(mContext);

        myCalendar = Calendar.getInstance();

        this.revInitFields();
    }

    private void revInitFields() {
        revEntityName = revCoreInputFormEditText.getRevEditText_NO_BORDERS_NO_PADDING();
        revEntityName.setHint("Calendar entry item name");
        ((LinearLayout.LayoutParams) revEntityName.getLayoutParams()).setMargins(0, RevLibGenConstantine.REV_MARGIN_SMALL, 0, RevLibGenConstantine.REV_MARGIN_SMALL);

        revEntityDescription = revCoreInputFormEditText.getRevEditText_NO_BORDERS_NO_PADDING();
        revEntityDescription.setHint("Description");

    }

    public View getRevInputFormWidgetsContainer() {
        LinearLayout linearLayout = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        ((LinearLayout.LayoutParams) linearLayout.getLayoutParams()).setMargins(RevLibGenConstantine.REV_MARGIN_MEDIUM, 0, 0, 0);

        linearLayout.addView(REV_VIEWS_BASE_FUNCTIONS.revRemovedParentView(revEntityName));
        linearLayout.addView(REV_VIEWS_BASE_FUNCTIONS.revRemovedParentView(revEntityDescription));
        linearLayout.addView(this.yearOfStudySelect());
        linearLayout.addView(this.submitRevInputFormBtn());

        return linearLayout;
    }

    private View submitRevInputFormBtn() {
        TextView submitFormTab = new RevCoreColoredTabs(mContext).getRevColoredTab();
        submitFormTab.setTextColor(ContextCompat.getColor(mContext, rev.ca.revlibviews.R.color.revWhite));
        submitFormTab.setBackgroundColor(ContextCompat.getColor(mContext, rev.ca.revlibviews.R.color.deep_purple_dark));
        submitFormTab.setText("Publish");

        LinearLayout.LayoutParams submitFormTab_LP = (LinearLayout.LayoutParams) submitFormTab.getLayoutParams();
        submitFormTab_LP.setMargins(0, RevLibGenConstantine.REV_MARGIN_SMALL, 0, RevLibGenConstantine.REV_MARGIN_SMALL);

        submitFormTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RevConstantinePluggableViewsServices
                        .REV_PLUGIN_START_REV_PERS_ACTIONS_MAP.get("RevPublishAppointmentAction").initRevAction(revObjectFormdata());
            }
        });

        return submitFormTab;
    }

    private View yearOfStudySelect() {
        LinearLayout yearOfStudySelectContainer = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        ((LinearLayout.LayoutParams) yearOfStudySelectContainer.getLayoutParams()).setMargins(0, RevLibGenConstantine.REV_MARGIN_SMALL, 0, RevLibGenConstantine.REV_MARGIN_MEDIUM);

        TextView yearOfStudyTell_TV = revCoreInputFormTextView.getRevExtraSmallBoldTextView();
        ((LinearLayout.LayoutParams) yearOfStudyTell_TV.getLayoutParams()).width = LinearLayout.LayoutParams.MATCH_PARENT;
        yearOfStudyTell_TV.setText("Date");

        int borderWidth = 1;

        GradientDrawable border = new GradientDrawable();
        border.setStroke(borderWidth, mContext.getResources().getColor(rev.ca.rev_lib_core_views.R.color.teal_dark));
        border.setGradientType(RECTANGLE);

        Drawable[] layers = {border};
        LayerDrawable layerDrawable = new LayerDrawable(layers);
        layerDrawable.setLayerInset(0, -borderWidth, -borderWidth, -borderWidth, borderWidth);
        REV_VIEWS_BASE_FUNCTIONS.REV_SAFE_SET_BACKGROUND(yearOfStudyTell_TV, layerDrawable);

        LinearLayout yearOfStudySelectWrapper_LL = revCoreLayoutsLinearLayout.get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        ((LinearLayout.LayoutParams) yearOfStudySelectWrapper_LL.getLayoutParams()).setMargins(0, RevLibGenConstantine.REV_MARGIN_SMALL, 0, 0);

        LinearLayout.LayoutParams dateSelect_LP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dateSelect_LP.gravity = Gravity.CENTER_VERTICAL;

        final TextView yearOfStudyFrom_TV = revCoreInputFormTextView.getRevExtraSmallNormalTextView_NO_PADDING();
        yearOfStudyFrom_TV.setText("Start Date");
        yearOfStudyFrom_TV.setGravity(Gravity.CENTER_VERTICAL);

        final DatePickerDialog.OnDateSetListener yearOfStudyFrom_TV_DATE = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel(yearOfStudyFrom_TV);
            }
        };

        yearOfStudyFrom_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(mContext, yearOfStudyFrom_TV_DATE, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        final TextView yearOfStudyTo_TV = revCoreInputFormTextView.getRevExtraSmallNormalTextView_NO_PADDING();
        yearOfStudyTo_TV.setText("End Date");
        yearOfStudyTo_TV.setGravity(Gravity.CENTER_VERTICAL);
        yearOfStudyTo_TV.setLayoutParams(dateSelect_LP);
        ((LinearLayout.LayoutParams) yearOfStudyTo_TV.getLayoutParams()).setMargins(RevLibGenConstantine.REV_IMAGE_SIZE_MEDIUM, 0, 0, 0);

        final DatePickerDialog.OnDateSetListener yearOfStudyTo_TV_DATE = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel(yearOfStudyTo_TV);
            }
        };

        yearOfStudyTo_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(mContext, yearOfStudyTo_TV_DATE, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        Drawable img = mContext.getResources().getDrawable(R.drawable.calendar_png);
        img.setBounds(0, 0, RevLibGenConstantine.REV_IMAGE_SIZE_SMALL, RevLibGenConstantine.REV_IMAGE_SIZE_SMALL);
        yearOfStudyFrom_TV.setCompoundDrawables(img, null, null, null);
        yearOfStudyTo_TV.setCompoundDrawables(img, null, null, null);

        yearOfStudySelectWrapper_LL.addView(yearOfStudyFrom_TV);
        yearOfStudySelectWrapper_LL.addView(yearOfStudyTo_TV);

        yearOfStudySelectContainer.addView(yearOfStudyTell_TV);
        yearOfStudySelectContainer.addView(yearOfStudySelectWrapper_LL);

        return yearOfStudySelectContainer;
    }

    private void updateLabel(TextView textView) {
        String myFormat = "MMM dd, yyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        textView.setText(sdf.format(myCalendar.getTime()));
    }

    public RevEntity revObjectFormdata() {

        RevEntity revObjectEntitySuper = new RevEntity();

        revObjectEntitySuper.set_revEntityType(FeedReaderContract.FeedEntry_REV_OBJECT_ENTITY.TABLE_NAME);
        revObjectEntitySuper.set_revEntitySubType("rev_calendar");
        revObjectEntitySuper.set_revEntityContainerGUID(revVarArgsData.getRevEntity().get_revEntityGUID());

        RevObjectEntity revObjectEntity = new RevObjectEntity();
        revObjectEntity.set_revObjectName(revEntityName.getText().toString());
        revObjectEntity.set_revObjectDescription(revEntityDescription.getText().toString());

        revObjectEntitySuper.setRevObjectEntity(revObjectEntity);

        return revObjectEntitySuper;
    }
}
