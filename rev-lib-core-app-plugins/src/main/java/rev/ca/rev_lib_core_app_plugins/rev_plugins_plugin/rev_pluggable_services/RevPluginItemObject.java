package rev.ca.rev_lib_core_app_plugins.rev_plugins_plugin.rev_pluggable_services;

import java.util.Arrays;
import java.util.List;

import rev.ca.rev_gen_lib_pers.RevDBModels.REV_PERS_REVMETADATA_GEN_FUNCTIONS;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntity;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntityMetadata;
import rev.ca.revlibpersistence.rev_persistence.FeedReaderContract;

public class RevPluginItemObject implements IRevPluginRegisterRevPluginItem_SPI {

    private String revPluginId, revPluginName, revPluginDescription, revPluginFeatures, revPluginDependencies, revPluginPublisher;

    private RevPluginItemObject revPluginItemObject;
    private RevEntity revPluginItemObjectEntity;

    public RevPluginItemObject() {
    }

    @Override
    public String getRevPluginId() {
        return revPluginId;
    }

    public void setRevPluginId(String revPluginId) {
        this.revPluginId = revPluginId;
    }

    public String getRevPluginName() {
        return revPluginName;
    }

    public void setRevPluginName(String revPluginName) {
        this.revPluginName = revPluginName;
    }

    @Override
    public String getRevPluginDescription() {
        return revPluginDescription;
    }

    public void setRevPluginDescription(String revPluginDescription) {
        this.revPluginDescription = revPluginDescription;
    }

    @Override
    public String getRevPluginFeatures() {
        return revPluginFeatures;
    }

    public void setRevPluginFeatures(String revPluginFeatures) {
        this.revPluginFeatures = revPluginFeatures;
    }

    @Override
    public String getRevPluginDependencies() {
        return revPluginDependencies;
    }

    public void setRevPluginDependencies(String revPluginDependencies) {
        this.revPluginDependencies = revPluginDependencies;
    }

    @Override
    public String getRevPluginPublisher() {
        return revPluginPublisher;
    }

    public void setRevPluginPublisher(String revPluginPublisher) {
        this.revPluginPublisher = revPluginPublisher;
    }

    public RevEntity getRevPluginItemObjectEntity() {
        revPluginItemObjectEntity = new RevEntity();
        revPluginItemObjectEntity.set_revEntityType(FeedReaderContract.FeedEntry_REV_OBJECT_ENTITY.TABLE_NAME);
        revPluginItemObjectEntity.set_revEntitySubType("rev_plugin");

        revPluginItemObjectEntity.set_revEntityMetadataList(Arrays.asList(
                new RevEntityMetadata("rev_plugin_id", revPluginId),
                new RevEntityMetadata("rev_plugin_name", revPluginName),
                new RevEntityMetadata("rev_plugin_description", revPluginDescription),
                new RevEntityMetadata("rev_plugin_features", revPluginFeatures),
                new RevEntityMetadata("rev_plugin_dependencies", revPluginDependencies)
        ));

        return revPluginItemObjectEntity;
    }

    public RevPluginItemObject revResolveRevEntity_To_RevPluginItemObject(RevEntity revEntity) {
        this.revPluginItemObject = new RevPluginItemObject();

        List<RevEntityMetadata> revEntityMetadataList = revEntity.get_revEntityMetadataList();

        revPluginItemObject.setRevPluginId(REV_PERS_REVMETADATA_GEN_FUNCTIONS.REV_GET_METADATA_VALUE(revEntityMetadataList, "rev_plugin_id"));
        revPluginItemObject.setRevPluginName(REV_PERS_REVMETADATA_GEN_FUNCTIONS.REV_GET_METADATA_VALUE(revEntityMetadataList, "rev_plugin_name"));
        revPluginItemObject.setRevPluginDescription(REV_PERS_REVMETADATA_GEN_FUNCTIONS.REV_GET_METADATA_VALUE(revEntityMetadataList, "rev_plugin_description"));
        revPluginItemObject.setRevPluginFeatures(REV_PERS_REVMETADATA_GEN_FUNCTIONS.REV_GET_METADATA_VALUE(revEntityMetadataList, "rev_plugin_features"));
        revPluginItemObject.setRevPluginDependencies(REV_PERS_REVMETADATA_GEN_FUNCTIONS.REV_GET_METADATA_VALUE(revEntityMetadataList, "rev_plugin_dependencies"));

        return revPluginItemObject;
    }

    public void setRevPluginItemObjectEntity(RevEntity revPluginItemObjectEntity) {
        this.revPluginItemObjectEntity = revPluginItemObjectEntity;
    }
}
