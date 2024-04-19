package main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import interfaces.List;
import interfaces.Map;

/**
 * The TrainStationGUI class creates a graphical user interface for displaying train station schedules.
 */
public class TrainStationGUI extends JFrame {
    private TrainStationManager manager;
    private JTextField searchField;
    private JTextArea routeTextArea;

    /**
     * Constructs a TrainStationGUI object with the specified TrainStationManager.
     *
     * @param manager The TrainStationManager to use for accessing station data.
     */
    public TrainStationGUI(TrainStationManager manager) {
        super("Train Station Schedule");
        this.manager = manager;

        JPanel panel = new JPanel(new BorderLayout());

        // Create search panel with text field and search button
        JPanel searchPanel = createSearchPanel();

        // Create route display area
        routeTextArea = new JTextArea(10, 20);
        routeTextArea.setEditable(false);
        JScrollPane routeScrollPane = new JScrollPane(routeTextArea);

        // Create a JTable to display the schedule
        JTable table = createScheduleTable(manager.getTravelTimes());
        JScrollPane scrollPane = new JScrollPane(table);

        panel.add(searchPanel, BorderLayout.NORTH);
        panel.add(routeScrollPane, BorderLayout.WEST);
        panel.add(scrollPane, BorderLayout.CENTER);

        add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Creates and returns a JPanel containing the search components.
     *
     * @return The search panel.
     */
    private JPanel createSearchPanel() {
        JPanel searchPanel = new JPanel(new FlowLayout());

        JLabel searchLabel = new JLabel("Enter Station:");
        searchField = new JTextField(15);
        JButton searchButton = new JButton("Search");

        // Add action listener to the search button
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String stationName = searchField.getText();
                String route = manager.traceRoute(stationName);
                routeTextArea.setText(route);
            }
        });

        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        return searchPanel;
    }

    /**
     * Creates and returns a JTable containing the schedule data.
     *
     * @param travelTimes The map containing travel times to each station.
     * @return The schedule table.
     */
    private JTable createScheduleTable(Map<String, Double> travelTimes) {
        String[] columnNames = {"Station", "Departure", "Arrival"};
        Object[][] data = new Object[travelTimes.size()][3];
        int rowIndex = 0;

        List<String> stationNames = manager.getStations().getKeys();

        for (String stationName : stationNames) {
            Double travelTime = travelTimes.get(stationName);
            String departureTime = manager.getDepartureTime(stationName);
            String arrivalTime = calculateArrivalTime(departureTime, travelTime);

            data[rowIndex][0] = stationName;
            data[rowIndex][1] = departureTime;
            data[rowIndex][2] = arrivalTime;

            rowIndex++;
        }

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(model);

        table.getColumnModel().getColumn(0).setPreferredWidth(150);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);

        return table;
    }

    /**
     * Calculates the arrival time based on the departure time and travel time.
     *
     * @param departureTime The departure time.
     * @param travelTime    The travel time.
     * @return The arrival time.
     */
    private String calculateArrivalTime(String departureTime, double travelTime) {
        if (departureTime.equals("Unknown")) {
            return "We are getting out of here :)";
        }

        String[] timeParts = departureTime.split(":");
        int hour = Integer.parseInt(timeParts[0]);
        int minute = Integer.parseInt(timeParts[1].substring(0, 2));

        int totalMinutes = hour * 60 + minute;
        totalMinutes += (int) (travelTime * 60);

        totalMinutes %= (24 * 60);

        hour = totalMinutes / 60;
        minute = totalMinutes % 60;

        String arrivalTime = String.format("%02d:%02d", hour, minute);
        String period = hour < 12 ? "am" : "pm";

        return arrivalTime + " " + period;
    }

    /**
     * The main method creates a TrainStationManager object and a TrainStationGUI object.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        TrainStationManager manager = new TrainStationManager("stations.csv");
        TrainStationGUI gui = new TrainStationGUI(manager);
    }
}
