package at.alirezamoh.idea_whisperer_for_laravel.settings;


import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.NlsContexts;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class SettingConfigurable implements Configurable {
    /**
     * The project setting state
     */
    private SettingsState settingsState;

    private SettingsComponent settingsComponent;

    public SettingConfigurable(Project project) {
        settingsState = SettingsState.getInstance(project);
    }

    @Override
    public @NlsContexts.ConfigurableName String getDisplayName() {
        return "Idea Whisperer For Laravel";
    }

    @Override
    public @Nullable JComponent createComponent() {
        settingsComponent = new SettingsComponent();

        return settingsComponent.getPanel();
    }

    @Override
    public boolean isModified() {
        return !settingsComponent.getPhpDockerContainerName().equals(settingsState.getPhpDockerContainerName())

            || !settingsComponent.getProjectType().equals(settingsState.getProjectType())

            || !settingsComponent.getModuleRootDirectoryPath().equals(settingsState.getModuleRootDirectoryPath())

            || !settingsComponent.getRootAppPath().equals(settingsState.getRootAppPath());
    }

    @Override
    public void apply() {
        settingsState.setPhpDockerContainerName(settingsComponent.getPhpDockerContainerName());
        settingsState.setProjectType(settingsComponent.getProjectType());
        settingsState.setModuleRootDirectoryPath(settingsComponent.getModuleRootDirectoryPath());
        settingsState.setRootAppPath(settingsComponent.getRootAppPath());
    }

    @Override
    public void reset() {
        settingsComponent.setPhpDockerContainerNameTextField(settingsState.getPhpDockerContainerName());
        settingsComponent.setProjectTypeComboBox(settingsState.getProjectType());
        settingsComponent.setModuleRootDirectoryPathTextField(settingsState.getModuleRootDirectoryPath());
        settingsComponent.setRootAppPathTextField(settingsState.getRootAppPath());
    }

    @Override
    public @Nullable JComponent getPreferredFocusedComponent() {
        return settingsComponent.getPreferredFocusedComponent();
    }

    @Override
    public void disposeUIResources() {
        settingsComponent = null;
    }
}