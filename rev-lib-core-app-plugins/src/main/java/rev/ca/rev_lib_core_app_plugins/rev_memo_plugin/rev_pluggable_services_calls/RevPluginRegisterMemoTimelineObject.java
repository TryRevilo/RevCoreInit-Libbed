package rev.ca.rev_lib_core_app_plugins.rev_memo_plugin.rev_pluggable_services_calls;

import rev.ca.rev_lib_core_app_plugins.rev_timeline.rev_pluggable_services.register_timeline_object.IRevPluginRegisterTimelineEntityType;

public class RevPluginRegisterMemoTimelineObject implements IRevPluginRegisterTimelineEntityType {

    @Override
    public String getRegisteredTimelineEntityType() {
        return "rev_memo";
    }
}
