package rev.ca.rev_lib_core_app_plugins.rev_announcement_plugin.rev_plugin_views.rev_plugin_widget_views.rev_plugin_forms.rev_input_form_widgets;

import android.content.Context;
import androidx.core.content.ContextCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import rev.ca.rev_lib_core_views.rev_pluggable_views_impl.RevConstantinePluggableViewsServices;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.revlibpersistence.rev_persistence.FeedReaderContract;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntity;
import rev.ca.rev_gen_lib_pers.RevDBModels.rev_entity_subtypes.RevObjectEntity;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.revlibviews.REV_VIEWS_BASE_FUNCTIONS;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormEditText;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;
import rev.ca.revlibviews.rev_core_menues.RevCoreColoredTabs;

/**
 * Created by rev on 1/3/18.
 */

public class RevCreateAnnouncementInputForm_WIDGETS {

    private RevVarArgsData revVarArgsData;

    private Context mContext;
    private RevCoreInputFormEditText revCoreInputFormEditText;
    private RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout;

    private EditText to_ET, from_ET, cc_ET, subject_ET, body_ET;

    public RevCreateAnnouncementInputForm_WIDGETS(RevVarArgsData revVarArgsData) {
        this.revVarArgsData = revVarArgsData;
        this.mContext = revVarArgsData.getmContext();

        revCoreInputFormEditText = new RevCoreInputFormEditText(mContext);
        revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(mContext);

        this.revInitFields();
    }

    private void revInitFields() {
        to_ET = revCoreInputFormEditText.getRevEditText_NO_BORDERS_NO_PADDING_TINY_SPACING();
        to_ET.setHint("To");

        from_ET = revCoreInputFormEditText.getRevEditText_NO_BORDERS_NO_PADDING_TINY_SPACING();
        from_ET.setHint("From");

        cc_ET = revCoreInputFormEditText.getRevEditText_NO_BORDERS_NO_PADDING_TINY_SPACING();
        cc_ET.setHint("cc");

        subject_ET = revCoreInputFormEditText.getRevEditText_NO_BORDERS_NO_PADDING_TINY_SPACING();
        subject_ET.setHint("Subject");

        body_ET = revCoreInputFormEditText.getRevEditText_NO_BORDERS_NO_PADDING_TINY_SPACING();
        body_ET.setHint("Message");

    }

    public View getRevInputFormWidgetsContainer() {
        LinearLayout linearLayout = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        ((LinearLayout.LayoutParams) linearLayout.getLayoutParams()).setMargins(RevLibGenConstantine.REV_MARGIN_SMALL, 0, 0, 0);

        linearLayout.addView(REV_VIEWS_BASE_FUNCTIONS.revRemovedParentView(to_ET));
        linearLayout.addView(REV_VIEWS_BASE_FUNCTIONS.revRemovedParentView(from_ET));
        linearLayout.addView(REV_VIEWS_BASE_FUNCTIONS.revRemovedParentView(subject_ET));
        linearLayout.addView(REV_VIEWS_BASE_FUNCTIONS.revRemovedParentView(body_ET));
        linearLayout.addView(this.submitRevInputFormBtn());

        return linearLayout;
    }

    private View submitRevInputFormBtn() {
        TextView submitFormTab = new RevCoreColoredTabs(mContext).getRevColoredTab();
        submitFormTab.setTextColor(ContextCompat.getColor(mContext, rev.ca.revlibviews.R.color.revWhite));
        submitFormTab.setBackgroundColor(ContextCompat.getColor(mContext, rev.ca.revlibviews.R.color.deep_purple_dark));
        submitFormTab.setText("Publish");

        LinearLayout.LayoutParams submitFormTab_LP = (LinearLayout.LayoutParams) submitFormTab.getLayoutParams();
        submitFormTab_LP.setMargins((int) (RevLibGenConstantine.REV_MARGIN_SMALL * .75), RevLibGenConstantine.REV_MARGIN_SMALL, 0, RevLibGenConstantine.REV_MARGIN_SMALL);

        submitFormTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RevConstantinePluggableViewsServices
                        .REV_PLUGIN_START_REV_PERS_ACTIONS_MAP.get("RevPublishAppointmentAction").initRevAction(revObjectFormdata());
            }
        });

        return submitFormTab;
    }

    public RevEntity revObjectFormdata() {

        RevEntity revObjectEntitySuper = new RevEntity();

        revObjectEntitySuper.set_revEntityType(FeedReaderContract.FeedEntry_REV_OBJECT_ENTITY.TABLE_NAME);
        revObjectEntitySuper.set_revEntitySubType("rev_announcement");
        revObjectEntitySuper.set_revEntityContainerGUID(revVarArgsData.getRevEntity().get_revEntityGUID());

        RevObjectEntity revObjectEntity = new RevObjectEntity();
        revObjectEntity.set_revObjectName(subject_ET.getText().toString());
        revObjectEntity.set_revObjectDescription(body_ET.getText().toString());

        revObjectEntitySuper.setRevObjectEntity(revObjectEntity);

        return revObjectEntitySuper;
    }
}
