package at.alirezamoh.idea_whisperer_for_laravel.actions.models;

import at.alirezamoh.idea_whisperer_for_laravel.support.ProjectDefaultPaths;

/**
 * Model representing a middleware class
 */
public class DBSeederModel extends BaseModel {
    /**
     * @param name                      The name of the db seeder
     * @param unformattedModuleFullPath The unformatted module full path
     * @param formattedModuleFullPath   The formatted module full path
     * @param moduleSrcPath             The module src path
     */
    public DBSeederModel(
        String name,
        String unformattedModuleFullPath,
        String formattedModuleFullPath,
        String moduleSrcPath
    )
    {
        super(
            name,
            unformattedModuleFullPath,
            formattedModuleFullPath,
            ProjectDefaultPaths.DB_SEEDER_PATH,
            "Seeder",
            ".php",
            "Database\\Seeders",
            moduleSrcPath
        );

        if (unformattedModuleFullPath.equals("/app") || unformattedModuleFullPath.isEmpty()) {
            this.formattedModuleFullPath = "";
            initDestination("", ProjectDefaultPaths.DB_SEEDER_PATH, "");
            initNamespace("Database\\Seeders");
            initFileName();
        }
        else {
            initDestination(unformattedModuleFullPath, ProjectDefaultPaths.DB_SEEDER_PATH, "");
            initNamespace("Database\\Seeders");
            initFileName();
        }
    }
}