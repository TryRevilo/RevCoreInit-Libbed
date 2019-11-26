package rev.ca.rev_lib_core_views;

import java.util.List;
import java.util.Map;

/**
 * Created by rev on 10/24/17.
 */

public interface IRevPluginStart {

    Map<String, List> I_REV_PLUGGABLE_VIEW_SSERVICES();

    Map<String, List> I_REV_CUSTOM_PLUGIN_SERVICES();

    void revPostStartCalls();
}
