package rev.ca.rev_lib_core_app_plugins.rev_translate.rev_plugin_views.rev_plugin_forms.rev_input_form_widgets;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.Arrays;

import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntity;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntityMetadata;
import rev.ca.rev_gen_lib_pers.rev_varags_data.REV_SESSION_SETTINGS;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.revlibpersistence.rev_persistence.FeedReaderContract;
import rev.ca.revlibviews.REV_VIEWS_BASE_FUNCTIONS;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormEditText;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;

public class RevCreateNewLanguageInputForm_WIDGET {

    private RevVarArgsData revVarArgsData;

    private Context mContext;

    private RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout;
    private RevCoreInputFormEditText revCoreInputFormEditText;

    private EditText revLanguageName_ET, revLanguageNameCode_ET;

    public RevCreateNewLanguageInputForm_WIDGET(RevVarArgsData revVarArgsData) {
        this.revVarArgsData = revVarArgsData;

        this.mContext = revVarArgsData.getmContext();

        revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(mContext);
        revCoreInputFormEditText = new RevCoreInputFormEditText(mContext);

        revLanguageName_ET = revCoreInputFormEditText.getRevEditText_NO_BORDERS_NO_PADDING_TINY_SPACING();
        revLanguageName_ET.setHint("LANGuAGE NAmE");
        revLanguageName_ET.setSingleLine(true);

        revLanguageNameCode_ET = revCoreInputFormEditText.getRevEditText_NO_BORDERS_NO_PADDING_TINY_SPACING();
        revLanguageNameCode_ET.setHint("LANGuAGE coDE ED : en, fr, ger");
        revLanguageNameCode_ET.setSingleLine(true);
    }

    public View getRevInputFormWidgetsContainer() {
        LinearLayout revInputFormWidgetsContainer = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        ((LinearLayout.LayoutParams) revInputFormWidgetsContainer.getLayoutParams()).setMargins(RevLibGenConstantine.REV_MARGIN_SMALL, 0, 0, 0);

        revInputFormWidgetsContainer.addView(REV_VIEWS_BASE_FUNCTIONS.revRemovedParentView(revLanguageName_ET));
        revInputFormWidgetsContainer.addView(REV_VIEWS_BASE_FUNCTIONS.revRemovedParentView(revLanguageNameCode_ET));

        return revInputFormWidgetsContainer;
    }

    public RevEntity getRevEntityFormData() {
        RevEntity revPersEntity = new RevEntity();
        revPersEntity.set_revEntityType(FeedReaderContract.FeedEntry_REV_OBJECT_ENTITY.TABLE_NAME);
        revPersEntity.set_revEntitySubType("rev_entity_language");
        revPersEntity.set_revEntityOwnerGUID(REV_SESSION_SETTINGS.getRevLoggedInEntityGuid());
        revPersEntity.set_revEntityContainerGUID(revVarArgsData.getRevContainerEntityGUID());
        revPersEntity.set_revEntityChildableStatus(3);

        revPersEntity.set_revEntityMetadataList(Arrays.asList(
                new RevEntityMetadata("rev_language_name_value", revLanguageName_ET.getText().toString()),
                new RevEntityMetadata("rev_language_name_code_value", revLanguageNameCode_ET.getText().toString())
        ));

        return revPersEntity;
    }
}
