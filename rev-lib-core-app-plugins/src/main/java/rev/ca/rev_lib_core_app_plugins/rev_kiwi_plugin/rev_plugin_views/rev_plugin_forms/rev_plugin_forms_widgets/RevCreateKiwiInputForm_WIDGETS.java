package rev.ca.rev_lib_core_app_plugins.rev_kiwi_plugin.rev_plugin_views.rev_plugin_forms.rev_plugin_forms_widgets;

import android.content.Context;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import java.util.Arrays;

import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntity;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntityMetadata;
import rev.ca.rev_gen_lib_pers.c_libs_core.rev_java_lib.RevPersJava;
import rev.ca.rev_gen_lib_pers.rev_varags_data.REV_SESSION_SETTINGS;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_lib_core_app_plugins.R;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_menues.RevPluggableOptionsContainerMenuLoader;
import rev.ca.rev_lib_core_views.rev_pluggable_views_impl.RevConstantinePluggableViewsServices;
import rev.ca.rev_lib_gen_functions.RevDimens;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.revlibpersistence.rev_persistence.FeedReaderContract;
import rev.ca.revlibviews.REV_VIEWS_BASE_FUNCTIONS;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormEditText;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormTextView;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;
import rev.ca.revlibviews.rev_core_menues.RevCoreColoredTabs;

/**
 * Created by rev on 1/3/18.
 */

public class RevCreateKiwiInputForm_WIDGETS {

    private RevVarArgsData revVarArgsData;
    private Context mContext;
    private RevDimens revDimens;
    private RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout;

    private EditText kiwiInput_ET;
    private RevCoreColoredTabs revCoreColoredTabs;

    public LinearLayout selectedMedia_LL;
    private static TextView selectedImages_TV;

    public static TextView getSelectedImages_TV() {
        return selectedImages_TV;
    }

    public RevCreateKiwiInputForm_WIDGETS(RevVarArgsData revVarArgsData) {
        this.mContext = revVarArgsData.getmContext();
        this.revVarArgsData = new RevVarArgsData();
        this.revVarArgsData.setmContext(mContext);

        revDimens = new RevDimens(mContext);
        revCoreColoredTabs = new RevCoreColoredTabs(mContext);

        revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(mContext);

        selectedMedia_LL = revCoreLayoutsLinearLayout.get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        LinearLayout.LayoutParams selectedMedia_LL_LP = (LinearLayout.LayoutParams) selectedMedia_LL.getLayoutParams();
        selectedMedia_LL_LP.setMargins((int) (RevLibGenConstantine.REV_MARGIN_MEDIUM * 1.4), 0, 0, RevLibGenConstantine.REV_MARGIN_SMALL);

        selectedImages_TV = new RevCoreInputFormTextView(mContext).getRevExtraSmallNormalTextView_NO_PADDING();
        selectedMedia_LL.addView(selectedImages_TV);
    }

    public View getRevkiwiInputForm() {
        this.initKiwi_ET();

        LinearLayout linearLayout = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        linearLayout.addView(kiwiInput_ET);

        linearLayout.addView(REV_VIEWS_BASE_FUNCTIONS.revRemovedParentView(selectedMedia_LL));

        LinearLayout kiwiFormFooter = revCoreLayoutsLinearLayout.getHorizontalRevLinearLayout_WRAPPED_ALL();

        if (kiwiOptions() != null)
            kiwiFormFooter.addView(this.kiwiOptions());

        kiwiFormFooter.addView(this.submitKiwiBtn());

        linearLayout.addView(kiwiFormFooter);

        linearLayout.setPadding(0, 0, 0, RevLibGenConstantine.REV_MARGIN_SMALL);

        return linearLayout;
    }

    private View kiwiOptions() {
        return new RevPluggableOptionsContainerMenuLoader().getOptionsMenu("rev_direct_select_menu_item", revVarArgsData);
    }

    private void initKiwi_ET() {
        kiwiInput_ET = new RevCoreInputFormEditText(mContext).getRevEditText_NO_Borders();
        kiwiInput_ET.setHint(R.string.kiwi_whats_on_your_mind);
        kiwiInput_ET.setTextSize(TypedValue.COMPLEX_UNIT_PX, revDimens.getRevTextSizeSmall());

        LinearLayout.LayoutParams kiwiInput_ET_LP = new LinearLayout.LayoutParams(
                (int) (revDimens.getPageWidth() * .8), LinearLayout.LayoutParams.MATCH_PARENT);
        kiwiInput_ET_LP.setMargins(RevLibGenConstantine.REV_MARGIN_SMALL, 0, revDimens.getRevSize5() * 3, 0);
        kiwiInput_ET.setLayoutParams(kiwiInput_ET_LP);

        kiwiInput_ET.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    String kiwi = kiwiInput_ET.getText().toString();
                    // TODO do something
                    if (!kiwi.matches("")) {
                        RevConstantinePluggableViewsServices
                                .REV_PLUGIN_START_REV_PERS_ACTIONS_MAP.get("RevPublishKiwiAction").initRevAction(revObjectFormdata());
                        handled = true;
                    }
                }
                return handled;
            }
        });
    }

    private View submitKiwiBtn() {
        TextView info_TV = revCoreColoredTabs.getRevColoredTab();
        info_TV.setTextColor(ContextCompat.getColor(mContext, rev.ca.revlibviews.R.color.revWhite));
        info_TV.setBackgroundColor(ContextCompat.getColor(mContext, rev.ca.revlibviews.R.color.deep_purple_dark));
        info_TV.setText("Post");

        LinearLayout.LayoutParams kiwiPointer_IV_LP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        kiwiPointer_IV_LP.setMargins(revDimens.getRevMarginMedium(), 0, 0, 0);
        kiwiPointer_IV_LP.gravity = Gravity.CENTER_VERTICAL;

        info_TV.setLayoutParams(kiwiPointer_IV_LP);

        info_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RevConstantinePluggableViewsServices
                        .REV_PLUGIN_START_REV_PERS_ACTIONS_MAP.get("RevPublishKiwiAction").initRevAction(revObjectFormdata());
            }
        });

        return info_TV;
    }

    public RevEntity revObjectFormdata() {
        long revEntityGUID = revVarArgsData.getRevEntity().get_revEntityGUID();

        if (revEntityGUID < 0) {
            revEntityGUID = (long) new RevPersJava().saveRevEntity_NO_REMOTE_SYNC(revVarArgsData.getRevEntity());
        }

        RevEntity revPersEntity = new RevEntity();
        revPersEntity.set_revEntityType(FeedReaderContract.FeedEntry_REV_OBJECT_ENTITY.TABLE_NAME);
        revPersEntity.set_revEntitySubType("rev_kiwi");
        revPersEntity.set_revEntityOwnerGUID(REV_SESSION_SETTINGS.getRevLoggedInEntityGuid());
        revPersEntity.set_revEntityContainerGUID(revEntityGUID);
        revPersEntity.set_revEntityChildableStatus(3);

        revPersEntity.set_revEntityMetadataList(Arrays.asList(
                new RevEntityMetadata("rev_kiwi_value", kiwiInput_ET.getText().toString())
        ));

        return revPersEntity;
    }
}
