import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RockPaperScissorsFrame extends JFrame implements ActionListener {

    private int playerWins = 0;
    private int computerWins = 0;
    private int ties = 0;

    private JTextField playerWinsField;
    private JTextField computerWinsField;
    private JTextField tiesField;
    private JTextArea resultsArea;

    private JButton rockButton;
    private JButton paperButton;
    private JButton scissorsButton;
    private JButton quitButton;

    public RockPaperScissorsFrame() {
        super("Rock Paper Scissors Game");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();

        pack();

        setVisible(true);
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        mainPanel.add(createButtonPanel(), BorderLayout.NORTH);

        mainPanel.add(createStatsPanel(), BorderLayout.WEST);

        mainPanel.add(createResultsPanel(), BorderLayout.CENTER);

        add(mainPanel);

        rockButton.addActionListener(this);
        paperButton.addActionListener(this);
        scissorsButton.addActionListener(this);
        quitButton.addActionListener(this);
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createTitledBorder("Choose Your Move"));

        rockButton = new JButton("Rock");
        paperButton = new JButton("Paper");
        scissorsButton = new JButton("Scissors");
        quitButton = new JButton("Quit");

        rockButton.setIcon(new ImageIcon("rock.png"));
        paperButton.setIcon(new ImageIcon("paper.png"));
        scissorsButton.setIcon(new ImageIcon("scissors.png"));
        quitButton.setIcon(new ImageIcon("quit.png"));

        buttonPanel.add(rockButton);
        buttonPanel.add(paperButton);
        buttonPanel.add(scissorsButton);
        buttonPanel.add(quitButton);

        return buttonPanel;
    }

    private JPanel createStatsPanel() {
        JPanel statsPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        statsPanel.setBorder(BorderFactory.createTitledBorder("Game Stats"));

        JLabel playerWinsLabel = new JLabel("Player Wins:");
        JLabel computerWinsLabel = new JLabel("Computer Wins:");
        JLabel tiesLabel = new JLabel("Ties:");

        playerWinsField = new JTextField("0", 5);
        computerWinsField = new JTextField("0", 5);
        tiesField = new JTextField("0", 5);


        playerWinsField.setEditable(false);
        computerWinsField.setEditable(false);
        tiesField.setEditable(false);


        statsPanel.add(playerWinsLabel);
        statsPanel.add(playerWinsField);
        statsPanel.add(computerWinsLabel);
        statsPanel.add(computerWinsField);
        statsPanel.add(tiesLabel);
        statsPanel.add(tiesField);

        return statsPanel;
    }

    private JPanel createResultsPanel() {
        JPanel resultsPanel = new JPanel();
        resultsPanel.setBorder(BorderFactory.createTitledBorder("Game Results"));

        resultsArea = new JTextArea(10, 30);
        resultsArea.setEditable(false); // Read-only

        JScrollPane scrollPane = new JScrollPane(resultsArea);

        resultsPanel.add(scrollPane);

        return resultsPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == quitButton) {
            System.exit(0);
        } else {
            String playerChoice = "";
            if (e.getSource() == rockButton) {
                playerChoice = "Rock";
            } else if (e.getSource() == paperButton) {
                playerChoice = "Paper";
            } else if (e.getSource() == scissorsButton) {
                playerChoice = "Scissors";
            }

            playGame(playerChoice);
        }
    }

    private void playGame(String playerChoice) {
        String[] choices = {"Rock", "Paper", "Scissors"};
        String computerChoice = choices[(int)(Math.random() * 3)];

        String result = determineWinner(playerChoice, computerChoice);

        resultsArea.append(result + "\n");

        playerWinsField.setText(String.valueOf(playerWins));
        computerWinsField.setText(String.valueOf(computerWins));
        tiesField.setText(String.valueOf(ties));
    }

    private String determineWinner(String playerChoice, String computerChoice) {
        String outcome = "";

        if (playerChoice.equals(computerChoice)) {
            ties++;
            outcome = playerChoice + " vs. " + computerChoice + " (Tie)";
        } else if ((playerChoice.equals("Rock") && computerChoice.equals("Scissors")) ||
                (playerChoice.equals("Paper") && computerChoice.equals("Rock")) ||
                (playerChoice.equals("Scissors") && computerChoice.equals("Paper"))) {
            playerWins++;
            outcome = playerChoice + " beats " + computerChoice + " (Player wins)";
        } else {
            computerWins++;
            outcome = computerChoice + " beats " + playerChoice + " (Computer wins)";
        }

        return outcome;
    }
}
