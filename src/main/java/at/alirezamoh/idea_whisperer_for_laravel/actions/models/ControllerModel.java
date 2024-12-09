package at.alirezamoh.idea_whisperer_for_laravel.actions.models;

import at.alirezamoh.idea_whisperer_for_laravel.support.ProjectDefaultPaths;

/**
 * Model representing a controller
 */
public class ControllerModel extends BaseModel {
    /**
     * @param name                      The name of the controller
     * @param unformattedModuleFullPath The unformatted module full path
     * @param formattedModuleFullPath   The formatted module full path
     */
    public ControllerModel(
        String name,
        String unformattedModuleFullPath,
        String formattedModuleFullPath
    )
    {
        super(
            name,
            unformattedModuleFullPath,
            formattedModuleFullPath,
            ProjectDefaultPaths.CONTROLLER_PATH,
            "Controller",
            ".php",
            "Http\\Controllers"
        );
    }
}
