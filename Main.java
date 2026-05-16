import javax.swing.*;
import java.awt.*;
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
    JLabel lblBookType;

    JTextField txtStudentName;
    JTextField txtRollNumber;
    JTextField txtIssueDate;
    JTextField txtReturnDate;

    JComboBox<String> cmbBookCategory;

    JRadioButton rbNewEdition;
    JRadioButton rbOldEdition;

    ButtonGroup bookTypeGroup;

    JButton btnIssueBook;
    JButton btnClear;
    JButton btnClose;

    LibraryGUI() {

        setTitle("Library Management System");
        setSize(500, 500);
        setLayout(null);
        getContentPane().setBackground(Color.LIGHT_GRAY);

        lblStudentName = new JLabel("Student Name:");
        lblRollNumber = new JLabel("Roll Number:");
        lblBookCategory = new JLabel("Book Category:");
        lblIssueDate = new JLabel("Issue Date:");
        lblReturnDate = new JLabel("Return Date:");
        lblBookType = new JLabel("Book Type:");

        txtStudentName = new JTextField();
        txtRollNumber = new JTextField();
        txtIssueDate = new JTextField();
        txtReturnDate = new JTextField();

        String[] categories = {
                "Choose Category",
                "Programming",
                "AI",
                "Database",
                "Networking"
        };

        cmbBookCategory = new JComboBox<>(categories);

        rbNewEdition = new JRadioButton("New");
        rbOldEdition = new JRadioButton("Old");

        rbNewEdition.setBackground(Color.LIGHT_GRAY);
        rbOldEdition.setBackground(Color.LIGHT_GRAY);

        bookTypeGroup = new ButtonGroup();

        bookTypeGroup.add(rbNewEdition);
        bookTypeGroup.add(rbOldEdition);

        btnIssueBook = new JButton("Issue");
        btnClear = new JButton("Clear");
        btnClose = new JButton("Close");

        lblStudentName.setBounds(40, 40, 120, 30);
        txtStudentName.setBounds(180, 40, 200, 30);

        lblRollNumber.setBounds(40, 90, 120, 30);
        txtRollNumber.setBounds(180, 90, 200, 30);

        lblBookCategory.setBounds(40, 140, 120, 30);
        cmbBookCategory.setBounds(180, 140, 200, 30);

        lblIssueDate.setBounds(40, 190, 120, 30);
        txtIssueDate.setBounds(180, 190, 200, 30);

        lblReturnDate.setBounds(40, 240, 120, 30);
        txtReturnDate.setBounds(180, 240, 200, 30);

        lblBookType.setBounds(40, 290, 120, 30);

        rbNewEdition.setBounds(180, 290, 80, 30);
        rbOldEdition.setBounds(270, 290, 80, 30);

        btnIssueBook.setBounds(40, 370, 100, 35);
        btnClear.setBounds(180, 370, 100, 35);
        btnClose.setBounds(320, 370, 100, 35);

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

        add(lblBookType);
        add(rbNewEdition);
        add(rbOldEdition);

        add(btnIssueBook);
        add(btnClear);
        add(btnClose);

        btnIssueBook.addActionListener(this);
        btnClear.addActionListener(this);
        btnClose.addActionListener(this);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == btnIssueBook) {

            try {

                String studentName = txtStudentName.getText();
                String rollNumber = txtRollNumber.getText();
                String issueDate = txtIssueDate.getText();
                String returnDate = txtReturnDate.getText();

                if(studentName.isEmpty() ||
                        rollNumber.isEmpty() ||
                        issueDate.isEmpty() ||
                        returnDate.isEmpty()) {

                    throw new EmptyFieldException(
                            "Please fill all fields");
                }

                if(!rollNumber.matches("\\d+")) {

                    throw new InvalidRollNumberException(
                            "Invalid Roll Number");
                }

                Integer.parseInt(rollNumber);

                if(cmbBookCategory.getSelectedIndex() == 0) {

                    throw new NullSelectionException(
                            "Select Book Category");
                }

                if(!rbNewEdition.isSelected() &&
                        !rbOldEdition.isSelected()) {

                    throw new NullSelectionException(
                            "Select Book Type");
                }

                if(returnDate.compareTo(issueDate) < 0) {

                    throw new InvalidDateException(
                            "Return Date is invalid");
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

        if(e.getSource() == btnClear) {

            txtStudentName.setText("");
            txtRollNumber.setText("");
            txtIssueDate.setText("");
            txtReturnDate.setText("");

            cmbBookCategory.setSelectedIndex(0);

            bookTypeGroup.clearSelection();
        }

        if(e.getSource() == btnClose) {

            System.exit(0);
        }
    }

    public static void main(String[] args) {

        new LibraryGUI();
    }
}