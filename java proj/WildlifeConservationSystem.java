import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class WildlifeConservationSystem extends JFrame {

    private JTable speciesTable;
    private JButton addSpeciesButton, viewSpeciesButton;
    private List<Species> speciesList;

    public WildlifeConservationSystem() {
        speciesList = new ArrayList<>(); // Initialize species list to store data

        setTitle("Wildlife Conservation System");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Add Buttons Panel
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout());
        addSpeciesButton = new JButton("Add Species");
        viewSpeciesButton = new JButton("View Species");
        buttonsPanel.add(addSpeciesButton);
        buttonsPanel.add(viewSpeciesButton);

        add(buttonsPanel, BorderLayout.NORTH);

        // Table to display species
        speciesTable = new JTable();
        JScrollPane tableScrollPane = new JScrollPane(speciesTable);
        add(tableScrollPane, BorderLayout.CENTER);

        // Event Listeners
        addSpeciesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open Add Species Dialog
                new AddSpeciesDialog(WildlifeConservationSystem.this).setVisible(true);
            }
        });

        viewSpeciesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // View all species
                SpeciesTableModel model = new SpeciesTableModel(speciesList);
                speciesTable.setModel(model);
            }
        });

        // Default View
        viewSpeciesButton.doClick();
    }

    // Getters for species list (used in AddSpeciesDialog)
    public List<Species> getSpeciesList() {
        return speciesList;
    }

    // Main method to launch the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new WildlifeConservationSystem().setVisible(true);
            }
        });
    }

    // Species class - Represents species information
    public static class Species {
        private int speciesId;
        private String speciesName;
        private String scientificName;
        private String conservationStatus;
        private String habitatDescription;

        public Species(int speciesId, String speciesName, String scientificName, String conservationStatus, String habitatDescription) {
            this.speciesId = speciesId;
            this.speciesName = speciesName;
            this.scientificName = scientificName;
            this.conservationStatus = conservationStatus;
            this.habitatDescription = habitatDescription;
        }

        // Getters and Setters
        public int getSpeciesId() {
            return speciesId;
        }

        public void setSpeciesId(int speciesId) {
            this.speciesId = speciesId;
        }

        public String getSpeciesName() {
            return speciesName;
        }

        public void setSpeciesName(String speciesName) {
            this.speciesName = speciesName;
        }

        public String getScientificName() {
            return scientificName;
        }

        public void setScientificName(String scientificName) {
            this.scientificName = scientificName;
        }

        public String getConservationStatus() {
            return conservationStatus;
        }

        public void setConservationStatus(String conservationStatus) {
            this.conservationStatus = conservationStatus;
        }

        public String getHabitatDescription() {
            return habitatDescription;
        }

        public void setHabitatDescription(String habitatDescription) {
            this.habitatDescription = habitatDescription;
        }
    }

    // Add Species Dialog to collect user input for new species
    public static class AddSpeciesDialog extends JDialog {
        private JTextField speciesNameField, scientificNameField, conservationStatusField, habitatDescriptionField;
        private JButton saveButton;
        private WildlifeConservationSystem mainFrame;

        public AddSpeciesDialog(WildlifeConservationSystem parent) {
            super(parent, "Add New Species", true);
            this.mainFrame = parent;
            setSize(400, 300);
            setLocationRelativeTo(parent);
            setLayout(new GridLayout(5, 2));

            add(new JLabel("Species Name:"));
            speciesNameField = new JTextField();
            add(speciesNameField);

            add(new JLabel("Scientific Name:"));
            scientificNameField = new JTextField();
            add(scientificNameField);

            add(new JLabel("Conservation Status:"));
            conservationStatusField = new JTextField();
            add(conservationStatusField);

            add(new JLabel("Habitat Description:"));
            habitatDescriptionField = new JTextField();
            add(habitatDescriptionField);

            saveButton = new JButton("Save");
            add(saveButton);

            saveButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String speciesName = speciesNameField.getText();
                    String scientificName = scientificNameField.getText();
                    String conservationStatus = conservationStatusField.getText();
                    String habitatDescription = habitatDescriptionField.getText();

                    int speciesId = mainFrame.getSpeciesList().size() + 1; // Automatically generate ID
                    Species species = new Species(speciesId, speciesName, scientificName, conservationStatus, habitatDescription);

                    // Add species to the list in MainFrame
                    mainFrame.getSpeciesList().add(species);

                    JOptionPane.showMessageDialog(AddSpeciesDialog.this, "Species added successfully");
                    dispose();
                }
            });
        }
    }

    // Table model for displaying species data
    public static class SpeciesTableModel extends AbstractTableModel {
        private List<Species> speciesList;
        private String[] columnNames = {"ID", "Species Name", "Scientific Name", "Conservation Status", "Habitat"};

        public SpeciesTableModel(List<Species> speciesList) {
            this.speciesList = speciesList;
        }

        @Override
        public int getRowCount() {
            return speciesList.size();
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public String getColumnName(int columnIndex) {
            return columnNames[columnIndex];
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Species species = speciesList.get(rowIndex);
            switch (columnIndex) {
                case 0: return species.getSpeciesId();
                case 1: return species.getSpeciesName();
                case 2: return species.getScientificName();
                case 3: return species.getConservationStatus();
                case 4: return species.getHabitatDescription();
                default: return null;
            }
        }
    }
}
