package rev.ca.revlibpersistence.rev_persistence.rev_services;

import java.util.HashMap;
import java.util.Map;

import rev.ca.rev_plugin_loader.RevPluginsObjectsRegistry;

/**
 * Created by rev on 12/25/17.
 */

public class RevConstantineMake {

    public static Map<String, Object> REV_CONSTANTS = new HashMap<>();

    public static void initRevConstantineMakeServices() {
        for (Object object : RevPluginsObjectsRegistry.getRevPluginsObjectsRegistry()) {
            if ((object instanceof IRevConstantine)) {
                for (RevPersServicesRevConstantine revPersServicesRevConstantine : ((IRevConstantine) object).createRevConstantine()) {
                    REV_CONSTANTS.put(revPersServicesRevConstantine.getRevConstantineName(), revPersServicesRevConstantine.getRevConstantineObject());
                }
            }
        }
    }
}
