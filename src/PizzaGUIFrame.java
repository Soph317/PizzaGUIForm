import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PizzaGUIFrame extends javax.swing.JFrame {
JPanel crustPnl, sizePnl, toppingsPnl, receiptPnl, buttonPnl, mainPnl, topPnl, sizeCrustPnl;
JRadioButton thinBtn, regularBtn, deepDishBtn;
ButtonGroup BtnGroup;
JComboBox sizeCmb;
JCheckBox pepperoniChk, sausageChk, mushroomsChk, onionsChk, greenPeppersChk, extraCheeseChk;
JTextArea receiptTxt;
JButton orderBtn, clearBtn, quitBtn;
JScrollPane scroll;

public PizzaGUIFrame() {
    setTitle("Pizza Order Form");
    mainPnl = new JPanel();
    mainPnl.setLayout(new BorderLayout(10, 10));
    add(mainPnl);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    createTopPnl();
    createReceiptPnl();
    createButtonPnl();

    setSize(650, 700);
    setVisible(true);
}

private void createTopPnl(){
    topPnl = new JPanel(new BorderLayout(10, 10));
    createCrustPnl();
    createSizePnl();

    topPnl.add(sizeCrustPnl, BorderLayout.NORTH);

    createToppingsPnl();

    mainPnl.add(topPnl, BorderLayout.NORTH);
}

private void createCrustPnl(){
    crustPnl = new JPanel(new GridLayout(3, 1, 5, 2));
    crustPnl.setBorder(BorderFactory.createTitledBorder("Crust Type:"));
    thinBtn = new JRadioButton("Thin");
    regularBtn = new JRadioButton("Regular");
    deepDishBtn = new JRadioButton("Deep-Dish");
    BtnGroup = new ButtonGroup();
    BtnGroup.add(thinBtn);
    BtnGroup.add(regularBtn);
    BtnGroup.add(deepDishBtn);
    crustPnl.add(thinBtn);
    crustPnl.add(regularBtn);
    crustPnl.add(deepDishBtn);
    thinBtn.setFocusPainted(false);
    regularBtn.setFocusPainted(false);
    deepDishBtn.setFocusPainted(false);
    sizeCrustPnl = new JPanel(new GridLayout(1, 2, 10, 10));
    sizeCrustPnl.add(crustPnl, BorderLayout.WEST);
}

private void createSizePnl(){
    sizePnl = new JPanel(new GridBagLayout());
    sizePnl.setBorder(BorderFactory.createTitledBorder("Size:"));
    sizeCmb = new JComboBox <String>();
    sizeCmb.addItem("Small");
    sizeCmb.addItem("Medium");
    sizeCmb.addItem("Large");
    sizeCmb.addItem("Super");
    sizePnl.add(sizeCmb);
    sizeCrustPnl.add(sizePnl, BorderLayout.EAST);
}

    private void createToppingsPnl(){
    toppingsPnl = new JPanel(new GridLayout(2, 3, 5, 5));
    toppingsPnl.setBorder(BorderFactory.createTitledBorder("Toppings ($1 Each):"));
    pepperoniChk = new JCheckBox("Pepperoni");
    sausageChk = new JCheckBox("Sausage");
    mushroomsChk = new JCheckBox("Mushrooms");
    onionsChk = new JCheckBox("Onions");
    greenPeppersChk = new JCheckBox("Green Peppers");
    extraCheeseChk = new JCheckBox("Extra Cheese");
    toppingsPnl.add(pepperoniChk);
    toppingsPnl.add(sausageChk);
    toppingsPnl.add(mushroomsChk);
    toppingsPnl.add(onionsChk);
    toppingsPnl.add(greenPeppersChk);
    toppingsPnl.add(extraCheeseChk);
    topPnl.add(toppingsPnl, BorderLayout.SOUTH);
    }

    private void createReceiptPnl(){
    receiptPnl = new JPanel();
    receiptTxt = new JTextArea(20, 50);
    scroll = new JScrollPane(receiptTxt, 20, 31);
    receiptTxt.setEditable(false);
    receiptTxt.setFocusable(false);

    receiptPnl.add(scroll);
    mainPnl.add(receiptPnl, BorderLayout.CENTER);
    }

    private void createButtonPnl(){
    buttonPnl = new JPanel();

    orderBtn = new JButton("Order");
    clearBtn = new JButton("Clear");
    quitBtn = new JButton("Quit");

    buttonPnl.add(orderBtn);
    buttonPnl.add(clearBtn);
    buttonPnl.add(quitBtn);
    clearBtn.setFocusPainted(false);
    quitBtn.setFocusPainted(false);
    orderBtn.setFocusPainted(false);
    receiptTxt.setFont(new Font("Monospaced", Font.PLAIN, 12));

    orderBtn.addActionListener((ActionEvent e) -> processOrder());
    clearBtn.addActionListener((ActionEvent e) -> clearForm());
    quitBtn.addActionListener((ActionEvent e) -> quitApplication());
    mainPnl.add(buttonPnl, BorderLayout.SOUTH);
    }

    private void processOrder(){
    String crustType = "";
    if (thinBtn.isSelected()) {
        crustType = "Thin";
    } else if (regularBtn.isSelected()) {
        crustType = "Regular";
    } else if (deepDishBtn.isSelected()) {
        crustType = "Deep Dish";
    }

    if (crustType.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please select a crust type.", "Missing Crust", JOptionPane.WARNING_MESSAGE);
        return;
    }

    String size = (String) sizeCmb.getSelectedItem();
    double basePrice = 0.0;
    if ("Small".equals(size)) {
        basePrice = 8.00;
    } else if ("Medium".equals(size)) {
        basePrice = 12.00;
    } else if ("Large".equals(size)) {
        basePrice = 16.00;
    } else if ("Super".equals(size)) {
        basePrice = 20.00;
    }

    StringBuilder toppings = new StringBuilder();
    int toppingCount = 0;

    if (pepperoniChk.isSelected()) {
            toppings.append(String.format("%-30s $%.2f\n", "Pepperoni", 1.00));
            toppingCount++;
    }
    if (sausageChk.isSelected()) {
            toppings.append(String.format("%-30s $%.2f\n", "Sausage", 1.00));
            toppingCount++;
    }
    if (mushroomsChk.isSelected()) {
        toppings.append(String.format("%-30s $%.2f\n", "Mushrooms", 1.00));
        toppingCount++;
    }
    if (onionsChk.isSelected()) {
            toppings.append(String.format("%-30s $%.2f\n", "Onions", 1.00));
            toppingCount++;
    }
    if (greenPeppersChk.isSelected()) {
            toppings.append(String.format("%-30s $%.2f\n", "Green Peppers", 1.00));
            toppingCount++;
    }
    if (extraCheeseChk.isSelected()) {
            toppings.append(String.format("%-30s $%.2f\n", "Extra Cheese", 1.00));
            toppingCount++;
    }

    if (toppingCount == 0) {
        JOptionPane.showMessageDialog(this, "An order must include at least one topping.", "Missing Toppings", JOptionPane.WARNING_MESSAGE);
        return;
    }

    double subTotal = basePrice + (toppingCount * 1.00);
    double tax = subTotal * 0.07;
    double total = subTotal + tax;

    StringBuilder receipt = new StringBuilder();
    receipt.append("=========================================\n");
    receipt.append(String.format("%-30s $%.2f\n", crustType + " & " + size, basePrice));
    receipt.append(toppings);
    receipt.append("\n");
    receipt.append(String.format("%-30s $%.2f\n", "Sub-total:", subTotal));
    receipt.append(String.format("%-30s $%.2f\n", "Tax:", tax));
    receipt.append("-----------------------------------------\n");
    receipt.append(String.format("%-30s $%.2f\n", "Total:", total));
    receipt.append("=========================================\n");

    receiptTxt.setText(receipt.toString());
    }

    private void clearForm(){
    BtnGroup.clearSelection();
    sizeCmb.setSelectedIndex(0);
    pepperoniChk.setSelected(false);
    sausageChk.setSelected(false);
    mushroomsChk.setSelected(false);
    onionsChk.setSelected(false);
    greenPeppersChk.setSelected(false);
    extraCheeseChk.setSelected(false);
    receiptTxt.setText("");
    }

    private void quitApplication(){
        int response = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to quit?",
                "Confirm Exit",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (response == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

}