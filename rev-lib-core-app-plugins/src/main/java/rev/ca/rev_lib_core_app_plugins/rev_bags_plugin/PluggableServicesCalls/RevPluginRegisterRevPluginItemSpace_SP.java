package rev.ca.rev_lib_core_app_plugins.rev_bags_plugin.PluggableServicesCalls;

import rev.ca.rev_lib_core_app_plugins.rev_plugins_plugin.rev_pluggable_services.IRevPluginRegisterRevPluginItem_SPI;

public class RevPluginRegisterRevPluginItemSpace_SP implements IRevPluginRegisterRevPluginItem_SPI {

    @Override
    public String getRevPluginId() {
        return "rev_space";
    }

    @Override
    public String getRevPluginName() {
        return "Spaces";
    }

    @Override
    public String getRevPluginDescription() {
        return "HELps people share content with BLocks of people in their LivEs via Camp Ann utilities called Spaces";
    }

    @Override
    public String getRevPluginFeatures() {
        return null;
    }

    @Override
    public String getRevPluginDependencies() {
        return null;
    }

    @Override
    public String getRevPluginPublisher() {
        return "Camp Ann";
    }
}
