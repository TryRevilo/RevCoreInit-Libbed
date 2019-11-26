package rev.ca.rev_lib_core_app_plugins.rev_translate.PluggableServicesCalls;

import rev.ca.rev_lib_core_app_plugins.rev_plugins_plugin.rev_pluggable_services.IRevPluginRegisterRevPluginItem_SPI;

public class RevPluginRegisterRevPluginItem_SP implements IRevPluginRegisterRevPluginItem_SPI {

    @Override
    public String getRevPluginId() {
        return "rev_translate";
    }

    @Override
    public String getRevPluginName() {
        return "Translation";
    }

    @Override
    public String getRevPluginDescription() {
        return "HELps users translate Camp Ann";
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
