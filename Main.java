import javax.swing.*;
import java.awt.event.*;

class EmptyFieldException extends Exception {

    EmptyFieldException(String message) {

        super(message);
    }
}

class InvalidRollNumberException extends Exception {

    InvalidRollNumberException(String message) {

        super(message);
    }
}

class NullSelectionException extends Exception {

    NullSelectionException(String message) {

        super(message);
    }
}

class InvalidDateException extends Exception {

    InvalidDateException(String message) {

        super(message);
    }
}
class LibraryGUI extends JFrame implements ActionListener {

    JLabel lblStudentName;
    JLabel lblRollNumber;
    JLabel lblBookCategory;
    JLabel lblIssueDate;
    JLabel lblReturnDate;

    JTextField txtStudentName;
    JTextField txtRollNumber;
    JTextField txtIssueDate;
    JTextField txtReturnDate;

    JComboBox<String> cmbBookCategory;

    JRadioButton rbNewEdition;
    JRadioButton rbOldEdition;

    ButtonGroup bookTypeGroup;

    JButton btnIssueBook;
    JButton btnReset;
    JButton btnExit;

    LibraryGUI() {

        setTitle("Library Book Issue System");
        setSize(450, 450);
        setLayout(null);

        lblStudentName = new JLabel("Student Name:");
        lblRollNumber = new JLabel("Roll Number:");
        lblBookCategory = new JLabel("Book Category:");
        lblIssueDate = new JLabel("Issue Date:");
        lblReturnDate = new JLabel("Return Date:");

        txtStudentName = new JTextField();
        txtRollNumber = new JTextField();

        txtIssueDate = new JTextField();
        txtReturnDate = new JTextField();

        String[] bookCategories = {
                "Select Category",
                "Programming",
                "AI",
                "Database",
                "Networking"
        };

        cmbBookCategory = new JComboBox<>(bookCategories);

        rbNewEdition = new JRadioButton("New Edition");
        rbOldEdition = new JRadioButton("Old Edition");

        bookTypeGroup = new ButtonGroup();

        bookTypeGroup.add(rbNewEdition);
        bookTypeGroup.add(rbOldEdition);

        btnIssueBook = new JButton("Issue Book");
        btnReset = new JButton("Reset");
        btnExit = new JButton("Exit");

        lblStudentName.setBounds(30, 30, 120, 30);
        txtStudentName.setBounds(170, 30, 180, 30);

        lblRollNumber.setBounds(30, 80, 120, 30);
        txtRollNumber.setBounds(170, 80, 180, 30);

        lblBookCategory.setBounds(30, 130, 120, 30);
        cmbBookCategory.setBounds(170, 130, 180, 30);

        lblIssueDate.setBounds(30, 180, 120, 30);
        txtIssueDate.setBounds(170, 180, 180, 30);

        lblReturnDate.setBounds(30, 230, 120, 30);
        txtReturnDate.setBounds(170, 230, 180, 30);

        rbNewEdition.setBounds(170, 280, 120, 30);
        rbOldEdition.setBounds(290, 280, 120, 30);

        btnIssueBook.setBounds(30, 340, 120, 30);
        btnReset.setBounds(170, 340, 100, 30);
        btnExit.setBounds(290, 340, 100, 30);

        add(lblStudentName);
        add(txtStudentName);

        add(lblRollNumber);
        add(txtRollNumber);

        add(lblBookCategory);
        add(cmbBookCategory);

        add(lblIssueDate);
        add(txtIssueDate);

        add(lblReturnDate);
        add(txtReturnDate);

        add(rbNewEdition);
        add(rbOldEdition);

        add(btnIssueBook);
        add(btnReset);
        add(btnExit);

        btnIssueBook.addActionListener(this);
        btnReset.addActionListener(this);
        btnExit.addActionListener(this);

        setVisible(true);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == btnIssueBook) {

            try {

                String studentName =
                        txtStudentName.getText();

                String rollNumber =
                        txtRollNumber.getText();

                String issueDate =
                        txtIssueDate.getText();

                String returnDate =
                        txtReturnDate.getText();

                if(studentName.isEmpty() ||
                        rollNumber.isEmpty() ||
                        issueDate.isEmpty() ||
                        returnDate.isEmpty()) {

                    throw new EmptyFieldException(
                            "All fields are required");
                }

                if(!rollNumber.matches("\\d+")) {

                    throw new InvalidRollNumberException(
                            "Roll Number must contain digits only");
                }

                Integer.parseInt(rollNumber);

                if(cmbBookCategory.getSelectedIndex() == 0) {

                    throw new NullSelectionException(
                            "Please select book category");
                }

                if(!rbNewEdition.isSelected() &&
                        !rbOldEdition.isSelected()) {

                    throw new NullSelectionException(
                            "Please select book type");
                }

                if(returnDate.compareTo(issueDate) < 0) {

                    throw new InvalidDateException(
                            "Return Date cannot be earlier than Issue Date");
                }

                JOptionPane.showMessageDialog(
                        this,
                        "Book Issued Successfully");
            }

            catch(Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        ex.getMessage());
            }

            finally {

                JOptionPane.showMessageDialog(
                        this,
                        "Operation Completed");
            }
        }

        if(e.getSource() == btnReset) {

            txtStudentName.setText("");
            txtRollNumber.setText("");

            txtIssueDate.setText("");
            txtReturnDate.setText("");

            cmbBookCategory.setSelectedIndex(0);

            bookTypeGroup.clearSelection();
        }

        if(e.getSource() == btnExit) {

            System.exit(0);
        }
    }

    public static void main(String[] args) {

        new LibraryGUI();
    }
}
