package rev.ca.rev_lib_core_app_plugins.rev_translate.rev_plugin_libs;

import rev.ca.rev_gen_lib_pers.RevDBModels.REV_PERS_REVMETADATA_GEN_FUNCTIONS;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntity;
import rev.ca.rev_gen_lib_pers.c_libs_core.RevPersLibRead;
import rev.ca.rev_gen_lib_pers.rev_varags_data.REV_SESSION_SETTINGS;

public class RevTranslateGenFunctions {

    private RevPersLibRead revPersLibRead = new RevPersLibRead();

    public String revDefLoggedInLangName() {
        RevEntity revDefaultLoggedInUserSettingsEntity = revPersLibRead.revPersGetRevEntityByGUID(this.revDefLoggedInLangSettingsGUID());

        if (revDefaultLoggedInUserSettingsEntity == null || revDefaultLoggedInUserSettingsEntity.get_revEntityMetadataList().isEmpty())
            return null;

        long revDefaultUserLangEntityGUID = Long.valueOf(REV_PERS_REVMETADATA_GEN_FUNCTIONS.REV_GET_METADATA_VALUE(revDefaultLoggedInUserSettingsEntity.get_revEntityMetadataList(), "rev_default_loggedin_user_language_guid_value"));
        RevEntity revDefaultLangEntity = revPersLibRead.revPersGetRevEntityByGUID(revDefaultUserLangEntityGUID);

        return REV_PERS_REVMETADATA_GEN_FUNCTIONS.REV_GET_METADATA_VALUE(revDefaultLangEntity.get_revEntityMetadataList(), "rev_language_name_value");

    }

    public long revDefLoggedInLangSettingsGUID() {
        return revPersLibRead.getRevEntityGUID_By_RevEntityOwnerGUID_Subtype(REV_SESSION_SETTINGS.getRevLoggedInEntityGuid(), "rev_default_logged_in_user_settings");

    }
}
