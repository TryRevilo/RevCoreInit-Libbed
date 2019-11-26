package rev.ca.rev_lib_core_app_plugins.rev_memo_plugin.rev_plugin_views.rev_plugin_forms.rev_input_form_widgets;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import java.util.Arrays;

import rev.ca.rev_gen_lib_pers.RevDBModels.REV_PERS_REVMETADATA_GEN_FUNCTIONS;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntity;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntityMetadata;
import rev.ca.rev_gen_lib_pers.rev_varags_data.REV_SESSION_SETTINGS;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_lib_core_views.rev_pluggable_views_impl.RevConstantinePluggableViewsServices;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.revlibpersistence.rev_persistence.FeedReaderContract;
import rev.ca.revlibviews.REV_VIEWS_BASE_FUNCTIONS;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormEditText;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;
import rev.ca.revlibviews.rev_core_menues.RevCoreColoredTabs;

/**
 * Created by rev on 1/3/18.
 */

public class RevCreateMemoInputForm_WIDGETS {

    private RevVarArgsData revVarArgsData;

    private Context mContext;
    private RevCoreInputFormEditText revCoreInputFormEditText;
    private RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout;

    private EditText to_ET, from_ET, cc_ET, date_ET, subject_ET, body_ET;

    public RevCreateMemoInputForm_WIDGETS(RevVarArgsData revVarArgsData) {
        this.revVarArgsData = revVarArgsData;
        this.mContext = revVarArgsData.getmContext();

        revCoreInputFormEditText = new RevCoreInputFormEditText(mContext);
        revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(mContext);

        this.revInitFields();
    }

    private void revInitFields() {
        to_ET = revCoreInputFormEditText.getRevEditText_NO_BORDERS_NO_PADDING_TINY_SPACING();

        if (revVarArgsData.getRevEntity().get_revEntityMetadataList() != null) {
            to_ET.setHint("To . . . " + REV_PERS_REVMETADATA_GEN_FUNCTIONS.REV_GET_METADATA_VALUE(revVarArgsData.getRevEntity().get_revEntityMetadataList(), "rev_entity_full_names_value"));
        }

        from_ET = revCoreInputFormEditText.getRevEditText_NO_BORDERS_NO_PADDING_TINY_SPACING();
        from_ET.setHint("From . . . . " + REV_PERS_REVMETADATA_GEN_FUNCTIONS.REV_GET_METADATA_VALUE(REV_SESSION_SETTINGS.getRevLoggedInPageRevVarArgsData().getRevEntity().get_revEntityMetadataList(), "rev_entity_full_names_value"));

        cc_ET = revCoreInputFormEditText.getRevEditText_NO_BORDERS_NO_PADDING_TINY_SPACING();
        cc_ET.setHint("cc");

        date_ET = revCoreInputFormEditText.getRevEditText_NO_BORDERS_NO_PADDING_TINY_SPACING();
        date_ET.setHint("Date");

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
        linearLayout.addView(REV_VIEWS_BASE_FUNCTIONS.revRemovedParentView(date_ET));
        linearLayout.addView(REV_VIEWS_BASE_FUNCTIONS.revRemovedParentView(subject_ET));
        linearLayout.addView(REV_VIEWS_BASE_FUNCTIONS.revRemovedParentView(body_ET));
        linearLayout.addView(this.submitRevInputFormBtn());

        ScrollView scrollView = new ScrollView(mContext);
        scrollView.addView(linearLayout);

        return scrollView;
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
                        .REV_PLUGIN_START_REV_PERS_ACTIONS_MAP.get("RevPublishMemoAction").initRevAction(revObjectFormdata());
            }
        });

        return submitFormTab;
    }

    public RevEntity revObjectFormdata() {
        RevEntity revPersEntity = new RevEntity();
        revPersEntity.set_revEntityType(FeedReaderContract.FeedEntry_REV_OBJECT_ENTITY.TABLE_NAME);
        revPersEntity.set_revEntitySubType("rev_memo");
        revPersEntity.set_revEntityOwnerGUID(REV_SESSION_SETTINGS.getRevLoggedInEntityGuid());
        revPersEntity.set_revEntityContainerGUID(revVarArgsData.getRevEntity().get_revEntityGUID());
        revPersEntity.set_revEntityChildableStatus(3);

        revPersEntity.set_revEntityMetadataList(Arrays.asList(
                new RevEntityMetadata("rev_memo_to_value", to_ET.getText().toString()),
                new RevEntityMetadata("rev_memo_from_value", from_ET.getText().toString()),
                new RevEntityMetadata("rev_memo_cc_value", cc_ET.getText().toString()),
                new RevEntityMetadata("rev_memo_date_value", date_ET.getText().toString()),
                new RevEntityMetadata("rev_memo_subject_value", subject_ET.getText().toString()),
                new RevEntityMetadata("rev_memo_message_value", body_ET.getText().toString())
        ));

        return revPersEntity;
    }
}
