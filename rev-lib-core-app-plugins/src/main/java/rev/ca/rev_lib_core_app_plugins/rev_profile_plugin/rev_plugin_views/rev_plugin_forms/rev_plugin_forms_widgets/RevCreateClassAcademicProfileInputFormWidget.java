package rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_plugin_views.rev_plugin_forms.rev_plugin_forms_widgets;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import rev.ca.rev_lib_core_app_plugins.R;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntity;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntityMetadata;
import rev.ca.rev_gen_lib_pers.RevDBModels.rev_entity_subtypes.RevGroupEntity;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormEditText;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormTextView;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;

/**
 * Created by rev on 2/5/18.
 */

public class RevCreateClassAcademicProfileInputFormWidget {

    private Context mContext;
    private RevVarArgsData revVarArgsData;

    private RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout;
    private RevCoreInputFormTextView revCoreInputFormTextView;
    private RevCoreInputFormEditText revCoreInputFormEditText;
    private EditText revEntityName_ET, revEntityDescription_ET;

    private Calendar myCalendar;

    public RevCreateClassAcademicProfileInputFormWidget(RevVarArgsData revVarArgsData) {
        this.revVarArgsData = revVarArgsData;
        this.mContext = this.revVarArgsData.getmContext();

        revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(this.mContext);

        revCoreInputFormEditText = new RevCoreInputFormEditText(this.mContext);
        revCoreInputFormTextView = new RevCoreInputFormTextView(this.mContext);

        revEntityName_ET = revCoreInputFormEditText.getRevEditText_ONLY_BottomBorders_Small_Pad_Left();
        revEntityName_ET.setHint("Place of study");

        revEntityDescription_ET = revCoreInputFormEditText.getRevEditText_ONLY_BottomBorders_Small_Pad_Left();
        revEntityDescription_ET.setHint("Certificate pursued");

        myCalendar = Calendar.getInstance();
    }

    private View yearOfStudySelect() {
        LinearLayout yearOfStudySelectContainer = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        ((LinearLayout.LayoutParams) yearOfStudySelectContainer.getLayoutParams()).setMargins((int) (RevLibGenConstantine.REV_MARGIN_LARGE * 1.1), RevLibGenConstantine.REV_MARGIN_SMALL, 0, RevLibGenConstantine.REV_MARGIN_MEDIUM);

        TextView yearOfStudyTell_TV = revCoreInputFormTextView.getRevExtraSmallBoldTextView();
        yearOfStudyTell_TV.setText("Study duration");

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

    public View revInputFormView() {
        LinearLayout revInputFormViewContainer_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();

        revInputFormViewContainer_LL.addView(revEntityName_ET);
        revInputFormViewContainer_LL.addView(revEntityDescription_ET);
        revInputFormViewContainer_LL.addView(this.yearOfStudySelect());

        return revInputFormViewContainer_LL;
    }

    public RevEntity getRevEntityData() {
        RevEntity revEntity = new RevEntity();
        revEntity.set_revEntityType("rev_container_entity");
        revEntity.set_revEntitySubType("rev_bag");

        ArrayList<RevEntityMetadata> revEntityMetadataList = new ArrayList<>();
        revEntityMetadataList.add(new RevEntityMetadata("type_of_bag", "work_bag"));

        revEntity.set_revEntityMetadataList(revEntityMetadataList);

        RevGroupEntity revGroupEntity = new RevGroupEntity();
        revGroupEntity.setName(String.valueOf(revEntityName_ET.getText()));
        revGroupEntity.setDescription(String.valueOf(revEntityDescription_ET.getText()));

        revEntity.setRevGroupEntity(revGroupEntity);

        return revEntity;
    }
}
