package rev.ca.rev_lib_core_app_plugins.rev_plugins_plugin.rev_pluggable_services;

public interface IRevPluginRegisterRevPluginItem_SPI {

    String getRevPluginId();

    String getRevPluginName();

    String getRevPluginDescription();

    String getRevPluginFeatures();

    String getRevPluginDependencies();

    String getRevPluginPublisher();
}
