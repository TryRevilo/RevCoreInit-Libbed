package rev.ca.rev_lib_core_app_plugins.rev_translate;

import android.content.Context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntity;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntityMetadata;
import rev.ca.rev_gen_lib_pers.c_libs_core.RevPersLibRead;
import rev.ca.rev_gen_lib_pers.c_libs_core.rev_java_lib.RevPersJava;
import rev.ca.rev_gen_lib_pers.rev_pers_services.rev_pers_action.IRevPersAction;
import rev.ca.rev_gen_lib_pers.rev_pers_services.rev_pers_action.IRevPluginStartRevPersAction;
import rev.ca.rev_gen_lib_pers.rev_varags_data.REV_SESSION_SETTINGS;
import rev.ca.rev_lib_core_app_plugins.rev_translate.PluggableServicesCalls.RevPluginRegisterRevPluginItem_SP;
import rev.ca.rev_lib_core_app_plugins.rev_translate.rev_actions.RevCreateNewLanguageInputFormAction;
import rev.ca.rev_lib_core_app_plugins.rev_translate.rev_plugin_views.rev_pluggable_menus.rev_reg_menu_items.RevTranslateSettingsMenuItemReg;
import rev.ca.rev_lib_core_app_plugins.rev_translate.rev_plugin_views.rev_plugin_forms.RevCreateNewLanguageInputForm;
import rev.ca.rev_lib_core_app_plugins.rev_translate.rev_plugin_views.rev_plugin_inline_views.RegisterRevPluggableInlineViewsRevTranslationBlockView;
import rev.ca.rev_lib_core_app_plugins.rev_translate.rev_plugin_views.rev_plugin_widget_views.rev_pluggable_menues.RevTranslatePluggableFooterTabLoader;
import rev.ca.rev_lib_core_views.AbstractIRevPluginStart;
import rev.ca.rev_lib_core_views.IRevPluginStartInputForms;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_inline_views.IRevPluginStartPluginInlineViews;
import rev.ca.rev_lib_core_views.rev_pluggable_views_impl.RevConstantinePluggableViewsServices;
import rev.ca.revlibpersistence.rev_persistence.FeedReaderContract;

/**
 * Created by rev on 10/21/17.
 */

public class RevPluginStart extends AbstractIRevPluginStart implements IRevPluginStartPluginInlineViews, IRevPluginStartInputForms, IRevPluginStartRevPersAction {

    private Context mContext;

    private RevPersJava revPersJava = new RevPersJava();
    private RevPersLibRead revPersLibRead = new RevPersLibRead();

    public RevPluginStart(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    public Map<String, List> I_REV_PLUGGABLE_VIEW_SSERVICES() {
        Map<String, List> stringListHashMap = new HashMap<>();

        ArrayList createRevFooterTab = new ArrayList<>();
        createRevFooterTab.add(RevTranslatePluggableFooterTabLoader.class);

        List IRevPluginRegisterRevPluginItem_SPIList = new ArrayList();
        IRevPluginRegisterRevPluginItem_SPIList.add(RevPluginRegisterRevPluginItem_SP.class);

        stringListHashMap.put("IRevPluginRegisterRevPluginItem_SPI", IRevPluginRegisterRevPluginItem_SPIList);
        stringListHashMap.put("createRevFooterTab", createRevFooterTab);

        ArrayList<Class> createRevPluggableMenuItems = new ArrayList<>();
        createRevPluggableMenuItems.add(RevTranslateSettingsMenuItemReg.class);

        stringListHashMap.put("ICreateRevPluggableMenuItem", createRevPluggableMenuItems);

        return stringListHashMap;
    }

    @Override
    public Map<String, Class> getIRevPluggableInputFormsServicesViews() {
        Map<String, Class> stringArrayListHashMap = new HashMap<>();

        stringArrayListHashMap.put("RevCreateNewLanguageInputForm", RevCreateNewLanguageInputForm.class);

        return stringArrayListHashMap;
    }

    @Override
    public Map<String, IRevPersAction> getIRevPersActionServices() {
        Map<String, IRevPersAction> stringArrayListHashMap = new HashMap<>();

        stringArrayListHashMap.put("RevCreateNewLanguageInputFormAction", new RevCreateNewLanguageInputFormAction());

        return stringArrayListHashMap;
    }

    @Override
    public List<Class> getIRevPluggableInlineViewsServices() {
        ArrayList createRevPluggableInlineView = new ArrayList<>();
        createRevPluggableInlineView.add(RegisterRevPluggableInlineViewsRevTranslationBlockView.class);

        return createRevPluggableInlineView;
    }

    @Override
    public void revPostStartCalls() {
        if (!REV_SESSION_SETTINGS.isIsNotLoggedIn()) {
            long revLoggedInUserDefaultSettingsGUID = revPersLibRead.getRevEntityGUID_By_RevEntityOwnerGUID_Subtype(REV_SESSION_SETTINGS.getRevLoggedInEntityGuid(), "rev_default_logged_in_user_settings");

            if (revLoggedInUserDefaultSettingsGUID < 0) {
                RevEntity revPersEntity = new RevEntity();
                revPersEntity.set_revEntityType(FeedReaderContract.FeedEntry_REV_OBJECT_ENTITY.TABLE_NAME);
                revPersEntity.set_revEntitySubType("rev_default_logged_in_user_settings");
                revPersEntity.set_revEntityOwnerGUID(REV_SESSION_SETTINGS.getRevLoggedInEntityGuid());
                revPersEntity.set_revEntityChildableStatus(300);

                revPersEntity.set_revEntityMetadataList(Arrays.asList(
                        new RevEntityMetadata("rev_default_loggedin_user_language_guid_value", String.valueOf(this.revPersDefaultLoggedInUserLang()))
                ));

                // revPersJava.saveRevEntity(revPersEntity);
            }
        }
    }

    private long revPersDefaultLoggedInUserLang() {
        RevEntity revPersLangLocaleEntity = new RevEntity();
        revPersLangLocaleEntity.set_revEntityType(FeedReaderContract.FeedEntry_REV_OBJECT_ENTITY.TABLE_NAME);
        revPersLangLocaleEntity.set_revEntitySubType("rev_entity_language");
        revPersLangLocaleEntity.set_revEntityOwnerGUID(REV_SESSION_SETTINGS.getRevLoggedInEntityGuid());
        revPersLangLocaleEntity.set_revEntityChildableStatus(3);

        Locale revCurrentLocale = mContext.getResources().getConfiguration().locale;
        String revLocaleLang_S = revCurrentLocale.getDisplayLanguage();
        String revLocaleLangKey_S = revCurrentLocale.getLanguage();

        revPersLangLocaleEntity.set_revEntityMetadataList(Arrays.asList(
                new RevEntityMetadata("rev_language_name_value", revLocaleLang_S),
                new RevEntityMetadata("rev_language_name_code_value", revLocaleLangKey_S)
        ));

        // return (long) RevConstantinePluggableViewsServices
                // .REV_PLUGIN_START_REV_PERS_ACTIONS_MAP.get("RevCreateNewLanguageInputFormAction").initRevAction(revPersLangLocaleEntity);

        return -1;
    }
}
