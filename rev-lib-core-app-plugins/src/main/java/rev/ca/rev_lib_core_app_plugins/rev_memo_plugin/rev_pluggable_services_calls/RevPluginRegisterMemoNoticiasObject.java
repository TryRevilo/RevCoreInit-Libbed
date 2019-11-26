package rev.ca.rev_lib_core_app_plugins.rev_memo_plugin.rev_pluggable_services_calls;

import rev.ca.rev_lib_core_app_plugins.rev_noticias.rev_pluggable_services.IRevPluginRegisterNoticiasEntityType;

public class RevPluginRegisterMemoNoticiasObject implements IRevPluginRegisterNoticiasEntityType {

    @Override
    public String getRegisteredNoticiasEntityType() {
        return "rev_memo";
    }
}
