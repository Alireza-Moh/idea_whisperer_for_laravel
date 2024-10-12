package at.alirezamoh.idea_whisperer_for_laravel.settings;

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
     * Text field to input the name of the PHP Docker container
     */
    private final JBTextField phpDockerContainerNameTextField = new JBTextField();

    /**
     * Combo box to select the project type (Standard or Module-based)
     */
    private final ComboBox<String> projectTypeComboBox = new ComboBox<>(new String[]{"Standard Application", "Module based Application"});

    /**
     * Label for the root directory path field
     */
    private final JBLabel moduleRootDirectoryPathLabel = new JBLabel("Module root directory:");

    /**
     * Label for the root app path field
     */
    private final JBLabel rootAppPathLabel = new JBLabel("Root app path:");

    /**
     * Text field to input the root directory path for module-based projects
     */
    private final JBTextField moduleRootDirectoryPathTextField = new JBTextField();

    /**
     * Text field to input the root app path
     */
    private final JBTextField rootAppPathTextField = new JBTextField();

    SettingsComponent() {
        formBuilder = FormBuilder.createFormBuilder();

        hideModuleTextFields();

        addDockerSettingsComponent();
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

    /**
     * Return the preferred focused text field for the main panel
     * @return preferred focused text field
     */
    public JTextField getPreferredFocusedComponent() {
        return phpDockerContainerNameTextField;
    }

    public String getPhpDockerContainerName() {
        return phpDockerContainerNameTextField.getText();
    }

    public void setPhpDockerContainerNameTextField(String newContainerName) {
        this.phpDockerContainerNameTextField.setText(newContainerName);
    }

    public String getRootAppPath() {
        return rootAppPathTextField.getText();
    }

    public void setRootAppPathTextField(String newPath) {
        this.rootAppPathTextField.setText(newPath);
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

        rootAppPathLabel.setVisible(false);
        rootAppPathTextField.setVisible(false);
    }

    /**
     * Adds the text filed for saving the php docker container name
     */
    private void addDockerSettingsComponent() {
        formBuilder.addLabeledComponent(
            new JBLabel("PHP docker container name:"),
            phpDockerContainerNameTextField,
            0,
            false
        );
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
        )
        .addLabeledComponent(
            rootAppPathLabel,
            rootAppPathTextField,
            10,
            false
        );
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

                rootAppPathLabel.setVisible(isSimpleDirectoryModule);
                rootAppPathTextField.setVisible(isSimpleDirectoryModule);
            }
        });
    }

}