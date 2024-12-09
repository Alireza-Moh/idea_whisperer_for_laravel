package at.alirezamoh.idea_whisperer_for_laravel.settings;

import com.intellij.ide.HelpTooltip;
import com.intellij.openapi.ui.ComboBox;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBTextField;
import com.intellij.util.ui.FormBuilder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsComponent {
    /**
     * The form builder factory
     */
    private final FormBuilder formBuilder;

    /**
     * Combo box to select the project type (Standard or Module-based)
     */
    private final ComboBox<String> projectTypeComboBox = new ComboBox<>(new String[]{"Standard Application", "Module based Application"});

    /**
     * Label for the root directory path
     */
    private final JBLabel moduleRootDirectoryPathLabel = new JBLabel("Module root directory path:");

    /**
     * Text field to input the root directory path for module-based projects
     */
    private final JBTextField moduleRootDirectoryPathTextField = new JBTextField();

    SettingsComponent() {
        formBuilder = FormBuilder.createFormBuilder();

        hideModuleTextFields();

        addModuleSettingsComponent();
        fillPanel();

        addEventListenerToProjectTypeComboBox();
    }

    /**
     * Returns main panel with the necessary components
     * @return main panel
     */
    public JPanel getPanel() {
        return formBuilder.getPanel();
    }

    public String getModuleRootDirectoryPath() {
        return moduleRootDirectoryPathTextField.getText();
    }

    public void setModuleRootDirectoryPathTextField(String newPath) {
        this.moduleRootDirectoryPathTextField.setText(newPath);
    }

    public String getProjectType() {
        return projectTypeComboBox.getItem();
    }

    public void setProjectTypeComboBox(String selectedItem) {
        this.projectTypeComboBox.setSelectedItem(selectedItem);
    }

    private void hideModuleTextFields() {
        projectTypeComboBox.setPreferredSize(new java.awt.Dimension(300, projectTypeComboBox.getPreferredSize().height));

        moduleRootDirectoryPathLabel.setVisible(false);
        moduleRootDirectoryPathTextField.setVisible(false);
    }

    /**
     * Adds the text fields for saving the based module data
     */
    private void addModuleSettingsComponent() {
        formBuilder.addLabeledComponent(
            new JBLabel("Project type:"),
            projectTypeComboBox,
            20,
            false
        )
        .addLabeledComponent(
            moduleRootDirectoryPathLabel,
            moduleRootDirectoryPathTextField,
            10,
            false
        );

        new HelpTooltip()
            .setLocation(HelpTooltip.Alignment.RIGHT)
            .setDescription("The main folder where all your project modules are located")
            .installOn(moduleRootDirectoryPathTextField);
    }

    private void fillPanel() {
        formBuilder.addComponentFillVertically(new JPanel(), 0);
    }

    private void addEventListenerToProjectTypeComboBox() {
        projectTypeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedItem = (String) projectTypeComboBox.getSelectedItem();
                boolean isSimpleDirectoryModule = "Module based Application".equals(selectedItem);

                moduleRootDirectoryPathLabel.setVisible(isSimpleDirectoryModule);
                moduleRootDirectoryPathTextField.setVisible(isSimpleDirectoryModule);
                moduleRootDirectoryPathTextField.setText("/Modules/");
                moduleRootDirectoryPathTextField.setText("/app/");
            }
        });
    }

}
