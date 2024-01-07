package swing;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class App extends JFrame {
    private JLabel departureCityLabel;
    private JComboBox<String> departureCityComboBox;
    private JLabel arrivalCityLabel;
    private JComboBox<String> arrivalCityComboBox;
    private JCheckBox businessClassCheckBox;
    private JCheckBox firstClassCheckBox;
    private JCheckBox isActivCheckBox;
    private JRadioButton directFlightRadioButton;
    private JRadioButton layoverFlightRadioButton;
    private JButton registerButton;
    private JTextArea area;

    public App() {
        setTitle("Регистрация рейса");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(7, 2));

        departureCityLabel = new JLabel("Город отправления:");
        departureCityComboBox = new JComboBox<>(new String[]{"Нью-Йорк", "Лондон", "Париж", "Токио"});

        arrivalCityLabel = new JLabel("Город прибытия:");
        arrivalCityComboBox = new JComboBox<>(new String[]{"Лос-Анджелес", "Сидней", "Дубай", "Москва"});
        area=new JTextArea(5,20);
        businessClassCheckBox = new JCheckBox("Бизнес-класс");
        firstClassCheckBox = new JCheckBox("Первый класс");
isActivCheckBox =new JCheckBox("нажать чтобы увидеть инфо");
        directFlightRadioButton = new JRadioButton("Прямой рейс");
        layoverFlightRadioButton = new JRadioButton("Рейс с пересадкой");
        directFlightRadioButton.setSelected(true);//по умолчанию +

        ButtonGroup flightTypeGroup = new ButtonGroup();
        flightTypeGroup.add(directFlightRadioButton);
        flightTypeGroup.add(layoverFlightRadioButton);

        registerButton = new JButton("Зарегистрировать");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerFlight();
            }
        });

        add(departureCityLabel);
        add(departureCityComboBox);
        add(arrivalCityLabel);
        add(arrivalCityComboBox);
        add(businessClassCheckBox);
        add(firstClassCheckBox);
        add(isActivCheckBox);
        add(directFlightRadioButton);
        add(layoverFlightRadioButton);
        add(area);
        add(registerButton);

        pack();
        setLocationRelativeTo(null);
    }

    private void registerFlight() {
        String departureCity = (String) departureCityComboBox.getSelectedItem();
        String arrivalCity = (String) arrivalCityComboBox.getSelectedItem();
        boolean isBusinessClassSelected = businessClassCheckBox.isSelected();
        boolean isFirstClassSelected = firstClassCheckBox.isSelected();
        boolean hasLayover = layoverFlightRadioButton.isSelected();
boolean isActiv= isActivCheckBox.isSelected();

if(isActiv){
    area.setText("Departure City: " + departureCity+"\n"+"Arrival City: " + arrivalCity+"\n"+
            "Business Class: " + isBusinessClassSelected+"\n"+"First Class: " + isFirstClassSelected+"\n"+"Layover: " + hasLayover);
}else{
    area.setText("");
}
        // Здесь добавлен код для сохранения данных в файл
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/flight_data", true))) {
            writer.write("Departure City: " + departureCity);
            writer.newLine();
            writer.write("Arrival City: " + arrivalCity);
            writer.newLine();
            writer.write("Business Class: " + isBusinessClassSelected);
            writer.newLine();
            writer.write("First Class: " + isFirstClassSelected);
            writer.newLine();
            writer.write("Layover: " + hasLayover);
            writer.newLine();
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Ошибка при сохранении данных в файл!",
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(this, "Рейс успешно зарегистрирован!",
                "Регистрация завершена", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                App app = new App();
                app.setVisible(true);
            }
        });
    }
}