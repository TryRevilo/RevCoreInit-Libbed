package rev.ca.rev_lib_core_app_plugins.rev_members_plugin.PluggableServicesCalls;

import rev.ca.rev_lib_core_app_plugins.rev_timeline.rev_pluggable_services.register_timeline_object.IRevPluginRegisterTimelineEntityType;

public class RevPluginRegisterMemberTimelineObject implements IRevPluginRegisterTimelineEntityType {

    @Override
    public String getRegisteredTimelineEntityType() {
        return "rev_member";
    }
}
