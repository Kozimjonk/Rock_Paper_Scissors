import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Random;

/**
 *
 * @author Kozimjon
 */

public class RockPaperScissorsFrame extends JFrame{
    /* This is a program that allows the user to play rock paper scissors
    extends JFrame to create GUI
    3 buttons to choosing option, 1 quit button
    Computer always chooses randomly */

    JFrame frame = new JFrame();
    JPanel mainPnl;
    JPanel topPnl;
    JPanel midPnl;
    JPanel botPnl;

    JPanel scoreTrack;

    JLabel titleLbl;
    JTextArea displayResults;
    JScrollPane scroller;

    JButton quitButton;
    JButton paperButton;
    ImageIcon paperImage;
    JButton rockButton;
    ImageIcon rockImage;
    JButton scissorsButton;
    ImageIcon scissorsImage;

    JLabel cpuWinsLabel;
    JLabel playerWinsLabel;
    JLabel tieLabel;
    JTextArea cpuWins;
    JTextArea playerWins;
    JTextArea ties;

    JLabel totalLabel;
    JTextArea total;


    int cpuWinNumber = 0;
    int playerWinNumber = 0;
    int tieNumber = 0;

    int totalNumber = 0;

    private String results;

    /**
     * Generates the frame
     * @return RockPaperScissorsFrame Object
     */
    public RockPaperScissorsFrame()
    {
        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout());
        createTopPanel();
        mainPnl.add(topPnl, BorderLayout.NORTH);
        createMiddlePanel();
        mainPnl.add(midPnl, BorderLayout.CENTER);
        createBottomPanel();
        mainPnl.add(botPnl, BorderLayout.SOUTH);

        add(mainPnl);

        setSize(800,800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     * Generates the top panel for the frame.
     * Has an image icon and a title
     */
    private void createTopPanel()
    {
        topPnl = new JPanel();
        scoreTrack = new JPanel();
        scoreTrack.setLayout(new GridLayout(4,4));
        topPnl.setLayout(new BorderLayout());
        titleLbl = new JLabel("Rock Paper Scissors",JLabel.CENTER);
        titleLbl.setFont(new Font("SansSerif", Font.BOLD, 38));

        topPnl.add(titleLbl, BorderLayout.NORTH);


        cpuWins = new JTextArea(1,1);
        cpuWinsLabel = new JLabel("computer wins", JLabel.CENTER);
        playerWins = new JTextArea(1,1);
        playerWinsLabel = new JLabel("player wins", JLabel.CENTER);
        tieLabel = new JLabel("ties", JLabel.CENTER);
        ties = new JTextArea(1,1);
        totalLabel = new JLabel("total", JLabel.CENTER);
        total = new JTextArea(1,1);


        scoreTrack.add(cpuWinsLabel);
        scoreTrack.add(cpuWins);
        scoreTrack.add(playerWinsLabel);
        scoreTrack.add(playerWins);
        scoreTrack.add(tieLabel);
        scoreTrack.add(ties);
        scoreTrack.add(totalLabel);
        scoreTrack.add(total);
        topPnl.add(scoreTrack);
    }

    /**
     * Generates the center pannel for the frame
     * Contains a text area and a scoller when the text area gets too large
     * The results of each game are posted here.
     */
    private void createMiddlePanel()
    {
        midPnl = new JPanel();
        displayResults = new JTextArea(10,60);
        scroller = new JScrollPane(displayResults);


        midPnl.add(scroller);
    }

    /**
     * Creates the bottom panel for the frame
     * Contains 4 buttons, rock, paper scissors, and quit
     * When a rock, paper or scissors button is pressed, the computer generates a random choice
     * The player choice is compared to the computer's choice to determine the winner
     */
    private void createBottomPanel()
    {
        botPnl = new JPanel();
        botPnl.setLayout(new GridLayout(1,2));
        rockImage = new ImageIcon("src/rock.png");
        paperImage = new ImageIcon("src/paper.png");
        scissorsImage = new ImageIcon("src/scissors.png");
        rockButton = new JButton(rockImage);
        paperButton = new JButton(paperImage);
        scissorsButton = new JButton(scissorsImage);
        quitButton = new JButton("Quit");

        quitButton.addActionListener((ActionEvent ae) -> System.exit(0));
        rockButton.addActionListener(
                (ActionEvent ae) -> {
                    displayResults.append(gameRun(1) + "\n");
                });
        paperButton.addActionListener(
                (ActionEvent ae) -> {
                    displayResults.append(gameRun(2)+ "\n");
                });
        scissorsButton.addActionListener(
                (ActionEvent ae) -> {
                    displayResults.append(gameRun(3)+ "\n");
                });
        botPnl.add(rockButton);
        botPnl.add(paperButton);
        botPnl.add(scissorsButton);
        botPnl.add(quitButton);
    }


    /**
     *
     * @param Choice A number 1 through 3 that represents what the user chose.
     * @return a String that says who won the game
     * Compares the player choice to a random computer choice to determine winner
     */

    private String gameRun(int Choice) {
        Random rand = new Random();
        int computerChoice = rand.nextInt(1, 4);
        //rock = 1, paper = 2, scissors = 3
        String results = "";
        if (computerChoice == Choice) {
            results = "It's a tie!";
            tieNumber++;
            ties.setText(tieNumber + "");
        } else {
            switch (Choice) {






                case 1 -> {
                    if (computerChoice == 2) {
                        results = "Paper beats rock (Computer wins)";
                        cpuWinNumber++;
                        cpuWins.setText(cpuWinNumber + "");
                    }
                    else
                    {
                        results = "Rock beats scissors (Player wins)";
                        playerWinNumber++;
                        playerWins.setText(playerWinNumber + "");
                    }
                }
                case 2 -> {
                    if (computerChoice == 1) {
                        results = "Paper beats rock (Player Wins)";
                        playerWinNumber++;
                        playerWins.setText(playerWinNumber + "");
                    } else {
                        results = "Scissors beats paper (Computer Wins)";
                        cpuWinNumber++;
                        cpuWins.setText(cpuWinNumber + "");
                    }
                }
                case 3 -> {
                    if (computerChoice == 1) {
                        results = "Rock beats scissors (Computer Wins)";
                        cpuWinNumber++;
                        cpuWins.setText(cpuWinNumber + "");
                    } else {
                        results = "Scissors beats Rock (Player Wins)";
                        playerWinNumber++;
                        playerWins.setText(playerWinNumber + "");
                    }
                }
            }
        }
        totalNumber = cpuWinNumber + playerWinNumber + tieNumber;
        total.setText(totalNumber + "");
        return results;

    }
}