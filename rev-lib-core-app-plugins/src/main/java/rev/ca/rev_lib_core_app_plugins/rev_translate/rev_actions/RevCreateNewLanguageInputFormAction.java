package rev.ca.rev_lib_core_app_plugins.rev_translate.rev_actions;

import java.util.Arrays;
import java.util.List;

import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntity;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntityMetadata;
import rev.ca.rev_gen_lib_pers.c_libs_core.rev_java_lib.RevPersJava;
import rev.ca.rev_gen_lib_pers.rev_pers_services.rev_pers_action.IRevPersAction;
import rev.ca.rev_gen_lib_pers.rev_server_client.rev_server_client_services.RevPostPersServices;
import rev.ca.rev_gen_lib_pers.rev_varags_data.REV_SESSION_SETTINGS;
import rev.ca.rev_lib_core_app_plugins.rev_translate.rev_pluggable_services.rev_pluggable_translation_blocks_service.IRevPluginRegisterTranslationBlock;
import rev.ca.rev_lib_core_app_plugins.rev_translate.rev_pluggable_services.rev_pluggable_translation_blocks_service.RevPluginRegisterTranslationBlockLoader;
import rev.ca.rev_lib_core_app_plugins.rev_translate.rev_pluggable_services.rev_pluggable_translation_items_service.IRevPluginRegisterTranslationItems_SP_Loader;
import rev.ca.rev_lib_core_app_plugins.rev_translate.rev_pluggable_services.rev_pluggable_translation_items_service.IRevPluginRegisterTranslationObject_SPI;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.revlibpersistence.rev_persistence.FeedReaderContract;

public class RevCreateNewLanguageInputFormAction implements IRevPersAction {
    @Override
    public String registerRevActionName() {
        return "RevCreateNewLanguageInputFormAction";
    }

    @Override
    public Object initRevAction(RevEntity revEntity) {
        RevPersJava revPersJava = new RevPersJava();

        long revLangId = (long) revPersJava.saveRevEntity_NO_REMOTE_SYNC(revEntity);

        if (revLangId > 0) {
            List<IRevPluginRegisterTranslationBlock> iRevPluginRegisterTranslationBlockList = RevPluginRegisterTranslationBlockLoader.revGetPluggableTranslationBlocks();

            for (IRevPluginRegisterTranslationBlock iRevPluginRegisterTranslationBlock : iRevPluginRegisterTranslationBlockList) {
                String revTranslationBlockName = iRevPluginRegisterTranslationBlock.getRevTranslationBlockName();
                String revTranslationBlockTitle = iRevPluginRegisterTranslationBlock.getRevTranslationBloackTitle();

                RevEntity revEntityTranslationBlock = new RevEntity();
                revEntityTranslationBlock.set_revEntityType(FeedReaderContract.FeedEntry_REV_OBJECT_ENTITY.TABLE_NAME);
                revEntityTranslationBlock.set_revEntitySubType("rev_entity_lang_translation_block");
                revEntityTranslationBlock.set_revEntityOwnerGUID(REV_SESSION_SETTINGS.getRevLoggedInEntityGuid());
                revEntityTranslationBlock.set_revEntityContainerGUID(revLangId);
                revEntityTranslationBlock.set_revEntityChildableStatus(3);

                revEntityTranslationBlock.set_revEntityMetadataList(Arrays.asList(
                        new RevEntityMetadata("rev_translation_block_name", revTranslationBlockName),
                        new RevEntityMetadata("rev_translation_block_title", revTranslationBlockTitle)
                ));

                long revEntityTranslationBlockId = (long) revPersJava.saveRevEntity_NO_REMOTE_SYNC(revEntityTranslationBlock);

                if (revEntityTranslationBlockId > 0) {
                    for (IRevPluginRegisterTranslationObject_SPI iRevPluginRegisterTranslationItems_spi : IRevPluginRegisterTranslationItems_SP_Loader.revGetPluggableTranslationItems()) {
                        String revTranslationPluginBlockName = iRevPluginRegisterTranslationItems_spi.getRevTranslationPluginBlockName();

                        if (revTranslationBlockName.equals(revTranslationPluginBlockName)) {
                            String revTranslationItemName = iRevPluginRegisterTranslationItems_spi.getRevTranslationItemName();
                            String revTranslationItemValue = iRevPluginRegisterTranslationItems_spi.getRevTranslationItemValue();
                            String revTranslationItemValueDesc = iRevPluginRegisterTranslationItems_spi.getRevTranslationItemValueDesc();
                            String revTranslationPluginName = iRevPluginRegisterTranslationItems_spi.getRevTranslationPluginName();

                            RevEntity revEntityTranslationItem = new RevEntity();
                            revEntityTranslationItem.set_revEntityType(FeedReaderContract.FeedEntry_REV_OBJECT_ENTITY.TABLE_NAME);
                            revEntityTranslationItem.set_revEntitySubType("rev_entity_lang_translation_item");
                            revEntityTranslationItem.set_revEntityOwnerGUID(REV_SESSION_SETTINGS.getRevLoggedInEntityGuid());
                            revEntityTranslationItem.set_revEntityContainerGUID(revEntityTranslationBlockId);
                            revEntityTranslationItem.set_revEntityChildableStatus(1);

                            revEntityTranslationItem.set_revEntityMetadataList(Arrays.asList(
                                    new RevEntityMetadata("rev_translation_item_name", revTranslationItemName),
                                    new RevEntityMetadata("rev_translation_item_value", revTranslationItemValue),
                                    new RevEntityMetadata("rev_translation_item_value_desc", revTranslationItemValueDesc),
                                    new RevEntityMetadata("rev_translation_plugin_block_name", revTranslationPluginBlockName),
                                    new RevEntityMetadata("rev_translation_plugin_name", revTranslationPluginName)
                            ));

                            revPersJava.saveRevEntity_NO_REMOTE_SYNC(revEntityTranslationItem);
                        }
                    }
                }
            }
        }

        if (revLangId > 0)
            new RevPostPersServices(RevLibGenConstantine.REV_CONTEXT).revPostLocalPersRemoteSyncServices();

        return revLangId;
    }
}
