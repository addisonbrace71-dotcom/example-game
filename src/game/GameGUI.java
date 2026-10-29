package game;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.function.Consumer;

public class GameGUI extends JFrame {
    private final JTextArea output = new JTextArea();
    private final JTextField input = new JTextField();
    private final JButton enter = new JButton("Enter");
    private Consumer<String> commandHandler;

    public GameGUI() {
        super("The Missing Concert Ticket");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(750, 520);
        setMinimumSize(new Dimension(480, 350));
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout(12, 12));
        panel.setBorder(new EmptyBorder(16, 16, 16, 16));
        panel.setBackground(new Color(235, 240, 248));
        JLabel title = new JLabel("The Missing Concert Ticket", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 25));
        panel.add(title, BorderLayout.NORTH);

        output.setEditable(false);
        output.setLineWrap(true);
        output.setWrapStyleWord(true);
        output.setFont(new Font("SansSerif", Font.PLAIN, 16));
        output.setMargin(new Insets(12, 12, 12, 12));
        panel.add(new JScrollPane(output), BorderLayout.CENTER);

        JPanel commands = new JPanel(new BorderLayout(8, 8));
        commands.setOpaque(false);
        JLabel label = new JLabel("Command:");
        label.setLabelFor(input);
        commands.add(label, BorderLayout.WEST);
        commands.add(input, BorderLayout.CENTER);
        commands.add(enter, BorderLayout.EAST);
        panel.add(commands, BorderLayout.SOUTH);
        setContentPane(panel);
        input.addActionListener(event -> submitCommand());
        enter.addActionListener(event -> submitCommand());
        setVisible(true);
        input.requestFocusInWindow();
    }

    public void setCommandHandler(Consumer<String> handler) {
        commandHandler = handler;
    }

    private void submitCommand() {
        String command = input.getText().trim();
        if (command.isEmpty()) return;
        input.setText("");
        print("> " + command);
        Game.processCommand(command);
        input.requestFocusInWindow();
    }

    public void print(String message) {
        if (!SwingUtilities.isEventDispatchThread()) {
            SwingUtilities.invokeLater(() -> print(message));
            return;
        }
        output.append(message + "\n");
        output.setCaretPosition(output.getDocument().getLength());
    }

    public void endGame() {
        if (!SwingUtilities.isEventDispatchThread()) {
            SwingUtilities.invokeLater(this::endGame);
            return;
        }
        input.setEnabled(false);
        enter.setEnabled(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameGUI gui = new GameGUI();
            gui.print("Welcome to The Missing Concert Ticket!");
            gui.print("Explore campus, find your ticket, and reach the Parking Garage.");
            gui.print("GUI preview: connect your Game class to play.");
            gui.setCommandHandler(command -> {
                if (command.equalsIgnoreCase("quit")) {
                    gui.print("Goodbye!");
                    gui.endGame();
                } else {
                    gui.print("Preview received: " + command);
                }
            });
        });
    }
}


