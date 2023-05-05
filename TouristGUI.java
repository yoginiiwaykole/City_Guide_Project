import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class TouristGUI implements ActionListener {
    private JFrame frame;
    private JLabel titleLabel, regionLabel, categoryLabel;
    private JRadioButton touristButton, hotelButton, restaurantButton;
    private JButton submitButton;
    private JComboBox<String> regionList;
    private String[] regions = {"New Market", "MP Nagar", "Lalghati", "West"};
    private Font titleFont = new Font("Arial", Font.BOLD, 24);
    private Font labelFont = new Font("Arial", Font.BOLD, 16);
    private Color primaryColor = new Color(156, 192, 219);
    private Color secondaryColor = new Color(224, 215, 223);

    public TouristGUI() {
        // Create the main frame
        frame = new JFrame("Tourist Application");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(primaryColor);

        // Create the title label and add it to the frame
        titleLabel = new JLabel("City Guide");
        titleLabel.setBounds(150, 30, 200, 50);
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(Color.WHITE);
        frame.add(titleLabel);

        // Create the region label and dropdown list and add them to the frame
        regionLabel = new JLabel("Select a region:");
        regionLabel.setBounds(50, 100, 150, 30);
        regionLabel.setFont(labelFont);
        regionLabel.setForeground(Color.WHITE);
        frame.add(regionLabel);

        regionList = new JComboBox<>(regions);
        regionList.setBounds(200, 100, 200, 30);
        frame.add(regionList);

        // Create the category label and radio buttons and add them to the frame
        categoryLabel = new JLabel("Select a category:");
        categoryLabel.setBounds(50, 150, 150, 30);
        categoryLabel.setFont(labelFont);
        categoryLabel.setForeground(Color.WHITE);
        frame.add(categoryLabel);

        touristButton = new JRadioButton("Tourist Places");
        touristButton.setBounds(200, 150, 150, 30);
        touristButton.setBackground(primaryColor);
        touristButton.setFont(labelFont);
        touristButton.setForeground(Color.WHITE);
        touristButton.setSelected(true);

        hotelButton = new JRadioButton("Hotels");
        hotelButton.setBounds(200, 190, 150, 30);
        hotelButton.setBackground(primaryColor);
        hotelButton.setFont(labelFont);
        hotelButton.setForeground(Color.WHITE);

        restaurantButton = new JRadioButton("Restaurants");
        restaurantButton.setBounds(200, 230, 150, 30);
        restaurantButton.setBackground(primaryColor);
        restaurantButton.setFont(labelFont);
        restaurantButton.setForeground(Color.WHITE);

        ButtonGroup group = new ButtonGroup();
        group.add(touristButton);
        group.add(hotelButton);
        group.add(restaurantButton);

        frame.add(touristButton);
        frame.add(hotelButton);
        frame.add(restaurantButton);

        // Create the submit button and add it to the frame
        submitButton = new JButton("Submit");
        submitButton.setBounds(200, 300, 100, 30);
        submitButton.setFont(labelFont);
        submitButton.setBackground(primaryColor);
        submitButton.setForeground(Color.WHITE);
        submitButton.addActionListener(this);
        frame.add(submitButton);

        // Set the frame to be visible
        frame.setLayout(null);
        frame.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        // get the selected region and category
        String region = (String) regionList.getSelectedItem();
        String category = "";
        if (touristButton.isSelected()) {
            category = "tourist_places";
        } else if (hotelButton.isSelected()) {
            category = "hotels";
        } else if (restaurantButton.isSelected()) {
            category = "restaurants";
        }

        // execute the SQL query to fetch data based on the selected region and category
        // execute the SQL query to fetch data based on the selected region and category
        DatabaseHandler dbHandler = new DatabaseHandler("jdbc:mysql://localhost:3306/city_guide", "root", "yoginii@1008");
        String query = "";
        if (category.equals("tourist_places")) {
            query = "SELECT place_name, location, description FROM " + category + " WHERE region='" + region + "'";
        } else if (category.equals("restaurants")) {
            query = "SELECT restaurant_name, location, cuisine FROM " + category + " WHERE region='" + region + "'";
        } else if (category.equals("hotels")) {
            query = "SELECT hotel_name, location, rating FROM " + category + " WHERE region='" + region + "'";
        }
        ResultSet rs = dbHandler.executeQuery(query);

// display the results in a new window
        StringBuilder sb = new StringBuilder();
        try {
            while (rs.next()) {
                if (category.equals("tourist_places")) {
                    sb.append("Place: " + rs.getString("place_name"));
                    sb.append("\nLocation: " + rs.getString("location"));
                    sb.append("\nDescription: " + rs.getString("description"));
                } else if (category.equals("restaurants")) {
                    sb.append("Restaurant: " + rs.getString("restaurant_name"));
                    sb.append("\nLocation: " + rs.getString("location"));
                    sb.append("\nCuisine: " + rs.getString("cuisine"));
                } else if (category.equals("hotels")) {
                    sb.append("Hotel: " + rs.getString("hotel_name"));
                    sb.append("\nLocation: " + rs.getString("location"));
                    sb.append("\nRating: " + rs.getString("rating"));
                }
                sb.append("\n\n");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        JOptionPane.showMessageDialog(frame, sb.toString());

        // close the database connection
        dbHandler.closeConnection();
    }
    public static void main(String[] args) {
        new TouristGUI();
    }
}

