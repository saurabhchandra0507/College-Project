import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class SocialWelfareSystem extends JFrame {

    private JTable beneficiariesTable;
    private JButton addBeneficiaryButton, viewBeneficiariesButton;
    private List<Beneficiary> beneficiariesList;

    public SocialWelfareSystem() {
        beneficiariesList = new ArrayList<>(); // Initialize beneficiaries list

        setTitle("Social Welfare System");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Button Panel
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout());
        addBeneficiaryButton = new JButton("Add Beneficiary");
        viewBeneficiariesButton = new JButton("View Beneficiaries");
        buttonsPanel.add(addBeneficiaryButton);
        buttonsPanel.add(viewBeneficiariesButton);

        add(buttonsPanel, BorderLayout.NORTH);

        // Table to display beneficiaries' data
        beneficiariesTable = new JTable();
        JScrollPane tableScrollPane = new JScrollPane(beneficiariesTable);
        add(tableScrollPane, BorderLayout.CENTER);

        // Event listeners for the buttons
        addBeneficiaryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open Add Beneficiary Dialog
                new AddBeneficiaryDialog(SocialWelfareSystem.this).setVisible(true);
            }
        });

        viewBeneficiariesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // View all beneficiaries
                BeneficiaryTableModel model = new BeneficiaryTableModel(beneficiariesList);
                beneficiariesTable.setModel(model);
            }
        });

        // Default View - Display all beneficiaries initially
        viewBeneficiariesButton.doClick();
    }

    // Getter for beneficiaries list (used in AddBeneficiaryDialog)
    public List<Beneficiary> getBeneficiariesList() {
        return beneficiariesList;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SocialWelfareSystem().setVisible(true);
            }
        });
    }

    // Beneficiary class - Represents an individual receiving welfare services
    public static class Beneficiary {
        private int beneficiaryId;
        private String name;
        private int age;
        private String financialAidStatus;
        private String educationStatus;
        private String healthStatus;

        public Beneficiary(int beneficiaryId, String name, int age, String financialAidStatus, String educationStatus, String healthStatus) {
            this.beneficiaryId = beneficiaryId;
            this.name = name;
            this.age = age;
            this.financialAidStatus = financialAidStatus;
            this.educationStatus = educationStatus;
            this.healthStatus = healthStatus;
        }

        // Getters and Setters
        public int getBeneficiaryId() {
            return beneficiaryId;
        }

        public void setBeneficiaryId(int beneficiaryId) {
            this.beneficiaryId = beneficiaryId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getFinancialAidStatus() {
            return financialAidStatus;
        }

        public void setFinancialAidStatus(String financialAidStatus) {
            this.financialAidStatus = financialAidStatus;
        }

        public String getEducationStatus() {
            return educationStatus;
        }

        public void setEducationStatus(String educationStatus) {
            this.educationStatus = educationStatus;
        }

        public String getHealthStatus() {
            return healthStatus;
        }

        public void setHealthStatus(String healthStatus) {
            this.healthStatus = healthStatus;
        }
    }

    // Dialog to add a new beneficiary's data
    public static class AddBeneficiaryDialog extends JDialog {
        private JTextField nameField, ageField, financialAidField, educationStatusField, healthStatusField;
        private JButton saveButton;
        private SocialWelfareSystem mainFrame;

        public AddBeneficiaryDialog(SocialWelfareSystem parent) {
            super(parent, "Add New Beneficiary", true);
            this.mainFrame = parent;
            setSize(400, 300);
            setLocationRelativeTo(parent);
            setLayout(new GridLayout(6, 2));

            add(new JLabel("Name:"));
            nameField = new JTextField();
            add(nameField);

            add(new JLabel("Age:"));
            ageField = new JTextField();
            add(ageField);

            add(new JLabel("Financial Aid Status:"));
            financialAidField = new JTextField();
            add(financialAidField);

            add(new JLabel("Education Status:"));
            educationStatusField = new JTextField();
            add(educationStatusField);

            add(new JLabel("Health Status:"));
            healthStatusField = new JTextField();
            add(healthStatusField);

            saveButton = new JButton("Save");
            add(saveButton);

            saveButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String name = nameField.getText();
                    int age = Integer.parseInt(ageField.getText());
                    String financialAidStatus = financialAidField.getText();
                    String educationStatus = educationStatusField.getText();
                    String healthStatus = healthStatusField.getText();

                    int beneficiaryId = mainFrame.getBeneficiariesList().size() + 1; // Generate beneficiary ID
                    Beneficiary beneficiary = new Beneficiary(beneficiaryId, name, age, financialAidStatus, educationStatus, healthStatus);

                    // Add the new beneficiary to the list
                    mainFrame.getBeneficiariesList().add(beneficiary);

                    JOptionPane.showMessageDialog(AddBeneficiaryDialog.this, "Beneficiary added successfully");
                    dispose();
                }
            });
        }
    }

    // Table model to display beneficiaries' data
    public static class BeneficiaryTableModel extends AbstractTableModel {
        private List<Beneficiary> beneficiariesList;
        private String[] columnNames = {"ID", "Name", "Age", "Financial Aid", "Education Status", "Health Status"};

        public BeneficiaryTableModel(List<Beneficiary> beneficiariesList) {
            this.beneficiariesList = beneficiariesList;
        }

        @Override
        public int getRowCount() {
            return beneficiariesList.size();
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
            Beneficiary beneficiary = beneficiariesList.get(rowIndex);
            switch (columnIndex) {
                case 0: return beneficiary.getBeneficiaryId();
                case 1: return beneficiary.getName();
                case 2: return beneficiary.getAge();
                case 3: return beneficiary.getFinancialAidStatus();
                case 4: return beneficiary.getEducationStatus();
                case 5: return beneficiary.getHealthStatus();
                default: return null;
            }
        }
    }
}
