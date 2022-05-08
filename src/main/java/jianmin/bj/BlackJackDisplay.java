package jianmin.bj;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;

public class BlackJackDisplay extends JComponent implements ActionListener{
	Logger logger = LogManager.getLogger(BlackJackDisplay.class);
	private static final int ARROW_WIDTH = 29;
	private static final int ARROW_HEIGHT = 29;
	private static final int CARD_WIDTH = 104;
	private static final int CARD_HEIGHT = 159;
	private static final int CARD_VERTICAL_SPACING = 50;
	private static final int CARD_HORIZONTAL_SPACING = 30;
	private static final int SPACING = 5; // distance between cards
	private static final int PLAYER_SPACING = 30; // distance between cards

	private static final int BUTTEN_WIDTH = 75;
	private static final int BUTTEN_HEIGHT = 30;
	private static final int MONEY_BUTTEN_WIDTH = 50;
	private static final int MONEY_BUTTEN_HEIGHT = 30;
	private static final int JLABEL_WIDTH = 180;
	private static final int JLABEL_HEIGHT = 28;
	private static final int INFO_AREA_X = CARD_WIDTH * 9 + SPACING * 7;
	private static final int INFO_AREA_Y = SPACING;
	
	private static final int DEALER_CARD_START_X = SPACING;
	private static final int DEALER_CARD_START_Y = JLABEL_HEIGHT + 5 * SPACING;
	
	private static final int PLAYER_LABEL_START_X = SPACING;
	private static final int PLAYER_LABEL_START_Y = DEALER_CARD_START_Y + CARD_HEIGHT + 8 * SPACING;
	
	private static final int PLAYER_CARD_START_X = SPACING;
	private static final int PLAYER_CARD_START_Y = PLAYER_LABEL_START_Y + JLABEL_HEIGHT + 3 * SPACING;
	private static final int PLAYER_HAND_SPACING = 365;
	
	private static final int CHECK_BOX_WIDTH = 190;
	private static final int CHECK_BOX_HEIGHT = 28;
	private static final int OPTION_START_X = INFO_AREA_X + JLABEL_WIDTH + SPACING * 11;
	private static final int OPTION_START_Y = PLAYER_CARD_START_Y + 30;

	private static final int BETTING_AREA_HEIGHT = 168;
	
	private static final int BUTTEN_AREA_HEIGHT = 50;
	private static final int BUTTEN_AREA_X = CARD_WIDTH * 8  + SPACING * 6 ;
	private static final int BUTTEN_AREA_Y = (CARD_HEIGHT * 3 + SPACING * 2) + BETTING_AREA_HEIGHT;
	
	private static final int BETTING_BUTTON_X = SPACING;
	private static final int BETTING_BUTTON_Y = BUTTEN_AREA_Y;
	
	private JFrame frame;
	private BlackJack game;
	private double currentBetAmount = 0;
	private boolean infoDisplayed = false;
	
	//below properties are used for animation
	private Timer timer;
    private final int INITIAL_X = CARD_WIDTH * 8;
    private final int INITIAL_Y = -CARD_WIDTH;
    private final int DELAY = 10;
	private int x, y;
	private int final_x, final_y;
	private boolean inAnimation;
	Card dealtCard;
	
	private Hand currentHand = new Hand();
	private ArrayList<Hand> playerHands = new ArrayList<>();
	private int playerCompletedHands = 0;
	private int playerNumberOfHands = 0;
	
	private boolean isBettingPeriod = true;
	private TableBet tableBet = new TableBet();
	
	private int gamePlayed = 1;
	
	/**
	 * Info area 
	 */
	JButton shoeStateButton = jButton("Shoe Stats", INFO_AREA_X + JLABEL_WIDTH + SPACING, INFO_AREA_Y, BUTTEN_WIDTH + 20, BUTTEN_HEIGHT);
	JButton shuffleButton = jButton("Shuffle", shoeStateButton.getX() + shoeStateButton.getWidth()  + SPACING , INFO_AREA_Y, BUTTEN_WIDTH, BUTTEN_HEIGHT);

	JLabel usedCardCount = jLabel("Used Decks:", INFO_AREA_X, INFO_AREA_Y, JLABEL_WIDTH , JLABEL_HEIGHT);
	JLabel infoDecks = jLabel("Decks:", INFO_AREA_X + JLABEL_WIDTH + SPACING, INFO_AREA_Y * 2 + JLABEL_HEIGHT * 1 + SPACING , JLABEL_WIDTH , JLABEL_HEIGHT);
	JLabel infoNextCard = jLabel("Next Card:", INFO_AREA_X + JLABEL_WIDTH + SPACING, INFO_AREA_Y * 2 + JLABEL_HEIGHT * 2 + SPACING, JLABEL_WIDTH * 2 , JLABEL_HEIGHT);
	JLabel infoPenetration = jLabel("Penetration:", INFO_AREA_X + JLABEL_WIDTH + SPACING, INFO_AREA_Y * 2 + JLABEL_HEIGHT * 3 + SPACING, JLABEL_WIDTH * 2  , JLABEL_HEIGHT);
	JLabel infoHiLoCount = jLabel("HiLo Count:", INFO_AREA_X + JLABEL_WIDTH + SPACING, INFO_AREA_Y * 2 + JLABEL_HEIGHT * 4 + SPACING, JLABEL_WIDTH * 2  , JLABEL_HEIGHT);
	JLabel infoTrueCount = jLabel("True Count:", INFO_AREA_X + JLABEL_WIDTH + SPACING, INFO_AREA_Y * 2 + JLABEL_HEIGHT* 5 + SPACING , JLABEL_WIDTH * 2  , JLABEL_HEIGHT);
	JLabel infoBetMultiplier = jLabel("Bet Multiplier:", INFO_AREA_X + JLABEL_WIDTH + SPACING, INFO_AREA_Y * 2 + JLABEL_HEIGHT* 6 + SPACING , JLABEL_WIDTH * 2  , JLABEL_HEIGHT);
	JLabel infoBetAmount = jLabel("Bet Amount:", INFO_AREA_X + JLABEL_WIDTH + SPACING, INFO_AREA_Y * 2 + JLABEL_HEIGHT* 7 + SPACING , JLABEL_WIDTH * 2  , JLABEL_HEIGHT);
	
	/**
	 * Message label
	 */
	JLabel message = jLabel("", JLABEL_WIDTH * 2 + 60, SPACING, JLABEL_WIDTH * 4, JLABEL_HEIGHT);
	
	/**
	 * Dealer score labels
	 */
	JLabel dealerScoreLabel = jLabel("Dealer Score:", SPACING, SPACING, JLABEL_WIDTH, JLABEL_HEIGHT);
	
	/** 
	 * Player score and message labels
	 * These labels stay together with the cards
	 */
	PlayerGameInfo playerGameInfo = new PlayerGameInfo();
	ArrayList<PlayerGameInfo> gameInfos = new ArrayList<>();
	
	/**
	 * Betting Management area
	 */
	JLabel playerBalance = jLabel("$", BETTING_BUTTON_X  + (MONEY_BUTTEN_WIDTH + SPACING) * 9 + SPACING, BETTING_BUTTON_Y, JLABEL_WIDTH, JLABEL_HEIGHT);

	JButton bet1DollarButton = jButton("$1", BETTING_BUTTON_X, BETTING_BUTTON_Y, MONEY_BUTTEN_WIDTH, MONEY_BUTTEN_HEIGHT);
	JButton bet5DollarButton = jButton("$5", BETTING_BUTTON_X  + (MONEY_BUTTEN_WIDTH + SPACING) * 1, BETTING_BUTTON_Y, MONEY_BUTTEN_WIDTH, MONEY_BUTTEN_HEIGHT);
	JButton bet10DollarButton = jButton("$10", BETTING_BUTTON_X  + (MONEY_BUTTEN_WIDTH + SPACING) * 2, BETTING_BUTTON_Y, MONEY_BUTTEN_WIDTH, MONEY_BUTTEN_HEIGHT);
	JButton bet25DollarButton = jButton("$25", BETTING_BUTTON_X  + (MONEY_BUTTEN_WIDTH + SPACING) * 3, BETTING_BUTTON_Y, MONEY_BUTTEN_WIDTH, MONEY_BUTTEN_HEIGHT);
	JButton bet50DollarButton = jButton("$50", BETTING_BUTTON_X  + (MONEY_BUTTEN_WIDTH + SPACING) * 4, BETTING_BUTTON_Y, MONEY_BUTTEN_WIDTH, MONEY_BUTTEN_HEIGHT);
	JButton bet100DollarButton = jButton("$100", BETTING_BUTTON_X  + (MONEY_BUTTEN_WIDTH + SPACING) * 5, BETTING_BUTTON_Y, MONEY_BUTTEN_WIDTH, MONEY_BUTTEN_HEIGHT);
	JButton bet500DollarButton = jButton("$500", BETTING_BUTTON_X  + (MONEY_BUTTEN_WIDTH + SPACING) * 6, BETTING_BUTTON_Y, MONEY_BUTTEN_WIDTH, MONEY_BUTTEN_HEIGHT);
	JButton clearBetButton = jButton("Clear Bet", BETTING_BUTTON_X  + (MONEY_BUTTEN_WIDTH + SPACING) * 7, BETTING_BUTTON_Y, MONEY_BUTTEN_WIDTH * 2, MONEY_BUTTEN_HEIGHT);

	/**
	 * Button Area
	 */
	JButton dealButton = jButton("Deal", BUTTEN_AREA_X, BUTTEN_AREA_Y, BUTTEN_WIDTH, BUTTEN_HEIGHT);
	JButton hitButton = jButton("Hit", BUTTEN_AREA_X + BUTTEN_WIDTH + SPACING, BUTTEN_AREA_Y, BUTTEN_WIDTH, BUTTEN_HEIGHT);
	JButton stayButton = jButton("Stay", BUTTEN_AREA_X + (BUTTEN_WIDTH + SPACING) * 2, BUTTEN_AREA_Y, BUTTEN_WIDTH, BUTTEN_HEIGHT);
	JButton doubleButton = jButton("Double", BUTTEN_AREA_X + (BUTTEN_WIDTH + SPACING) * 3, BUTTEN_AREA_Y, BUTTEN_WIDTH, BUTTEN_HEIGHT);
	JButton surrenderButton = jButton("Surr.", BUTTEN_AREA_X + (BUTTEN_WIDTH + SPACING) * 4, BUTTEN_AREA_Y, BUTTEN_WIDTH, BUTTEN_HEIGHT);
	JButton splitButton = jButton("Split", BUTTEN_AREA_X + (BUTTEN_WIDTH + SPACING) * 5, BUTTEN_AREA_Y, BUTTEN_WIDTH, BUTTEN_HEIGHT);

	/**
	 * Option area
	 */
	JRadioButton basicStrategyRadioButton = jRadioButton("Basic Strategy", OPTION_START_X, OPTION_START_Y , CHECK_BOX_WIDTH, CHECK_BOX_HEIGHT);
	JRadioButton indexStrategyRadioButton = jRadioButton("Index Strategy", OPTION_START_X, OPTION_START_Y + CHECK_BOX_HEIGHT, CHECK_BOX_WIDTH, CHECK_BOX_HEIGHT);
	JCheckBox strategyInclusiveCheckBox = jCheckBox("Inclusive", OPTION_START_X + 30, OPTION_START_Y + CHECK_BOX_HEIGHT * 3, CHECK_BOX_WIDTH, CHECK_BOX_HEIGHT);
	JRadioButton strategyLevel1RadioButton = jRadioButton("Level 1", OPTION_START_X + 30, OPTION_START_Y + CHECK_BOX_HEIGHT * 4, CHECK_BOX_WIDTH, CHECK_BOX_HEIGHT);
	JRadioButton strategyLevel2RadioButton = jRadioButton("Level 2", OPTION_START_X + 30, OPTION_START_Y + CHECK_BOX_HEIGHT * 5, CHECK_BOX_WIDTH, CHECK_BOX_HEIGHT);
	JRadioButton strategyLevel3RadioButton = jRadioButton("Level 3", OPTION_START_X + 30, OPTION_START_Y + CHECK_BOX_HEIGHT * 6, CHECK_BOX_WIDTH, CHECK_BOX_HEIGHT);
	JRadioButton strategyLevel4RadioButton = jRadioButton("Level 4", OPTION_START_X + 30, OPTION_START_Y + CHECK_BOX_HEIGHT * 7, CHECK_BOX_WIDTH, CHECK_BOX_HEIGHT);
	JRadioButton strategyLevel5RadioButton = jRadioButton("Level 5", OPTION_START_X + 30, OPTION_START_Y + CHECK_BOX_HEIGHT * 8, CHECK_BOX_WIDTH, CHECK_BOX_HEIGHT);
	JCheckBox strategyTrainingCheckBox = jCheckBox("Training", OPTION_START_X + 30, OPTION_START_Y + CHECK_BOX_HEIGHT * 10, CHECK_BOX_WIDTH, CHECK_BOX_HEIGHT);

	public BlackJackDisplay(BlackJack game) {
		this.game = game;

		frame = new JFrame("BlackJack Trainer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(this);

		this.setPreferredSize(new Dimension((CARD_WIDTH * 3 + SPACING * 2) * 4 + PLAYER_SPACING * 2 + SPACING,
				(CARD_HEIGHT * 3 + SPACING * 2) + BETTING_AREA_HEIGHT + BUTTEN_AREA_HEIGHT));
		
		//Initialize the timer
        //timer = new Timer(DELAY, this);
		
		//dealer score
		this.add(dealerScoreLabel);
		
		//We need to create Hand 1 Game Info
		//so the betting can get started before game starts.
		createGameInfo(1);

		//message for verifying decisions
		this.add(message);
		message.setFont(new Font("Times", Font.BOLD, 30));
		message.setForeground(Color.YELLOW);
		message.setSize(message.getWidth(), message.getHeight() + 10);

		//Info management area
		this.add(usedCardCount);
		this.add(infoDecks);
		this.add(infoNextCard);
		this.add(infoPenetration);
		this.add(infoHiLoCount);
		this.add(infoTrueCount);
		this.add(infoBetMultiplier);
		this.add(infoBetAmount);
		
		setInfoDisplay(this.infoDisplayed);
		
		this.add(shoeStateButton);
		shoeStateButton.addActionListener(e -> {
			this.infoDisplayed = !this.infoDisplayed;
			setInfoDisplay(this.infoDisplayed);
			updateLabels();
		});

		this.add(shuffleButton);
		shuffleButton.addActionListener(e -> {
			game.getTable().getCardShoe().shuffle();
			updateLabels();
		});
		
		//Option area
		this.add(strategyTrainingCheckBox);
		strategyTrainingCheckBox.addActionListener(e -> {
			if(strategyTrainingCheckBox.isSelected()) {
				basicStrategyRadioButton.setVisible(true);
				indexStrategyRadioButton.setVisible(true);
				strategyInclusiveCheckBox.setVisible(true);
				strategyLevel1RadioButton.setVisible(true);
				strategyLevel2RadioButton.setVisible(true);
				strategyLevel3RadioButton.setVisible(true);
				strategyLevel4RadioButton.setVisible(true);
				strategyLevel5RadioButton.setVisible(true);
			} else {
				basicStrategyRadioButton.setVisible(false);
				indexStrategyRadioButton.setVisible(false);
				strategyInclusiveCheckBox.setVisible(false);
				strategyLevel1RadioButton.setVisible(false);
				strategyLevel2RadioButton.setVisible(false);
				strategyLevel3RadioButton.setVisible(false);
				strategyLevel4RadioButton.setVisible(false);
				strategyLevel5RadioButton.setVisible(false);				
			}
			game.getTable().setGuiStrategyTraining(strategyTrainingCheckBox.isSelected());
		});
		strategyTrainingCheckBox.setSelected(true);
		strategyTrainingCheckBox.doClick();
		
		this.add(strategyInclusiveCheckBox);
		strategyInclusiveCheckBox.setFont(new Font("Times", Font.PLAIN, 18));
		strategyInclusiveCheckBox.addActionListener(e -> {
			game.getTable().setGuiInclusiveTrainingHands(strategyInclusiveCheckBox.isSelected());
		});
		strategyInclusiveCheckBox.doClick();
		
		ButtonGroup strategyGroup = new ButtonGroup();
		strategyGroup.add(basicStrategyRadioButton);
		strategyGroup.add(indexStrategyRadioButton);
		this.add(basicStrategyRadioButton);
		this.add(indexStrategyRadioButton);
		
		indexStrategyRadioButton.addActionListener(e -> {
			if(indexStrategyRadioButton.isSelected()) {
				game.getTable().setStrategyTraining(new StrategyTrainingIndex());
			}
		});
		
		basicStrategyRadioButton.addActionListener(e -> {
			if(basicStrategyRadioButton.isSelected()) {
				game.getTable().setStrategyTraining(new StrategyTrainingBasic());
			}
		});
		
		//trigger the radio button click event
		indexStrategyRadioButton.doClick();
		
		ButtonGroup strategyLevelGroup = new ButtonGroup();
		strategyLevelGroup.add(strategyLevel1RadioButton);
		strategyLevelGroup.add(strategyLevel2RadioButton);
		strategyLevelGroup.add(strategyLevel3RadioButton);
		strategyLevelGroup.add(strategyLevel4RadioButton);
		strategyLevelGroup.add(strategyLevel5RadioButton);
		
		this.add(strategyLevel1RadioButton);
		this.add(strategyLevel2RadioButton);
		this.add(strategyLevel3RadioButton);
		this.add(strategyLevel4RadioButton);
		this.add(strategyLevel5RadioButton);
		
		strategyLevel1RadioButton.addActionListener(e -> {
			if(strategyLevel1RadioButton.isSelected()) {
				game.getTable().getPlayer().getStrategy().setStrategyOverrideSelectLevel(1);
			}
		});
		
		strategyLevel2RadioButton.addActionListener(e -> {
			if(strategyLevel2RadioButton.isSelected()) {
				game.getTable().getPlayer().getStrategy().setStrategyOverrideSelectLevel(2);
			}
		});
		
		strategyLevel3RadioButton.addActionListener(e -> {
			if(strategyLevel3RadioButton.isSelected()) {
				game.getTable().getPlayer().getStrategy().setStrategyOverrideSelectLevel(3);
			}
		});
		
		strategyLevel4RadioButton.addActionListener(e -> {
			if(strategyLevel4RadioButton.isSelected()) {
				game.getTable().getPlayer().getStrategy().setStrategyOverrideSelectLevel(4);
			}
		});
		
		strategyLevel5RadioButton.addActionListener(e -> {
			if(strategyLevel5RadioButton.isSelected()) {
				game.getTable().getPlayer().getStrategy().setStrategyOverrideSelectLevel(5);
			}
		});
		
		strategyLevel1RadioButton.doClick();

		//Betting area. The balance has to be set first.
		playerBalance.setText("$" + (game.getTable().getBetting().getCurrentBalance(game.getTable().getBetting().getBettingProperties()) - currentBetAmount));
		this.add(playerBalance);

		this.add(bet1DollarButton);
		this.add(bet5DollarButton);
		this.add(bet10DollarButton);
		this.add(bet25DollarButton);
		this.add(bet50DollarButton);
		this.add(bet100DollarButton);
		this.add(bet500DollarButton);
		this.add(clearBetButton);
		
		bet1DollarButton.addActionListener(e -> {
			this.bet(1.0);
		});
		
		bet5DollarButton.addActionListener(e -> {
			this.bet(5.0);
		});
		
		bet10DollarButton.addActionListener(e -> {
			this.bet(10.0);
		});
		
		bet25DollarButton.addActionListener(e -> {
			this.bet(25.0);
		});
		
		bet50DollarButton.addActionListener(e -> {
			this.bet(50.0);
		});
		
		bet100DollarButton.addActionListener(e -> {
			this.bet(100.0);;
		});
		
		bet500DollarButton.addActionListener(e -> {
			this.bet(500.0);
		});

		clearBetButton.addActionListener(e -> {
			game.getTable().getBetting().returnBet(game.getTable().getBetting().getBettingProperties(),	tableBet.removeTableBet());
			updateLabels();
		});
		
		//Play Button Area
		this.add(dealButton);
		this.add(hitButton);
		this.add(doubleButton);
		this.add(stayButton);
		this.add(surrenderButton);
		this.add(splitButton);
		
		dealButton.setEnabled(true);
		hitButton.setEnabled(false);
		doubleButton.setEnabled(false);
		stayButton.setEnabled(false);
		surrenderButton.setEnabled(false);
		splitButton.setEnabled(false);
		
		/**
		 * Tried to animating the card dealing. It's not working due
		 * to the current thread and Timer thread are different thread.
		 * 
		dealtCard = game.getTable().getCardShoe().getNextCard();
		dealtCard.setX(INITIAL_X); 	
		dealtCard.setY(INITIAL_Y);
		dealtCard.setFinal_x(getPlayerCardPosition(0).x); 
		dealtCard.setFinal_y(getPlayerCardPosition(0).y);
		
		//this.timer.start();
		moveCard(dealtCard);
		
		game.getTable().getPlayer().getHand1().receiveACard(dealtCard);
		*/
		
		dealButton.addActionListener(e -> {
			/**********************************
			 ** Prepare a new round of game. **
			 **********************************/
				this.isBettingPeriod = false;
				playerNumberOfHands = 1;
				playerCompletedHands = 0;
				
				//clear all hands
				playerHands.clear();
				
				//clear all gameInfo except for Hand1; Hand1 info always stay
				for(int i = gameInfos.size(); i > 1; i--) {
					removeGameInfo(i);
				}
				
				//Clear up the game message from previous game
				gameInfos.get(0).playerMessage.setText("");
				
				//Clear up the dealer score
				dealerScoreLabel.setText("Dealer Score:");
				
				//Clear up the Decision Message board
				message.setText("");
				
			/**********************************************
			 ** Start playing this round game by dealing **
			 **********************************************/
				game.getTable().deal();
				currentHand = game.getTable().getPlayer().getHand1();
		        playerHands.add(currentHand);
		        
		        //Player commit the Table Bet here
				currentHand.commitTableBet(tableBet.removeTableBet()); 
				
				enableDisableButtons(dealButton);
				updateLabels();
				
				if(currentHand.isBlackJack()) { 
					stayButton.doClick();
				}
				
				if(game.getTable().getDealer().isBlackJack()) { 
					stayButton.doClick();
				}
				
		});

		hitButton.addActionListener(e -> {
			verifyDecision(Strategy.HIT);
			game.getTable().playerHit(currentHand);
			currentHand.setLastHandDecision(MapUtil.DECISION_HIT);
			enableDisableButtons(hitButton);
			updateLabels();
			
			//If this hand is a split Ace, it can only get one card.
			//The only exception is if it is another pair of Ace, then allow split again.
			if(currentHand.isSplitedAceHand() && !currentHand.isPairOfAce()) {
				stayButton.doClick();
			}
			
			if(currentHand.score() > 21) { //bust
				stayButton.doClick();
			}
		});

		doubleButton.addActionListener(e -> {
			verifyDecision(Strategy.DOUBLE);
			enableDisableButtons(doubleButton);
			playerCompletedHands++;
			
			game.getTable().playerHit(currentHand);
			currentHand.setLastHandDecision(MapUtil.DECISION_DOUBLE);
			updateLabels();
			
			if(playerCompletedHands >= playerHands.size()) {
				for(int handNumber = 0; handNumber < playerHands.size(); handNumber++ ) {
					playAndSettleGame(handNumber, playerHands.get(handNumber), playerHands.get(handNumber).getLastHandDecision());
					updateLabels();
				}
			}
			
			if(playerCompletedHands < playerHands.size() ) {
				currentHand = playerHands.get(playerCompletedHands);
				enableDisableButtons(splitButton);
			}
		});
		

		stayButton.addActionListener(e -> {
			if(!game.getTable().getDealer().isBlackJack()) {
				verifyDecision(Strategy.STAY);
			}
			
			enableDisableButtons(stayButton);
			currentHand.setLastHandDecision(MapUtil.DECISION_STAY);
			playerCompletedHands++;
			
			updateLabels();
			
			if(playerCompletedHands >= playerHands.size()) {
				for(int handNumber = 0; handNumber < playerHands.size(); handNumber++ ) {
					playAndSettleGame(handNumber, playerHands.get(handNumber), playerHands.get(handNumber).getLastHandDecision());
					updateLabels();
				}
			}
			
			if(playerCompletedHands < playerHands.size() ) {
				currentHand = playerHands.get(playerCompletedHands);
				enableDisableButtons(splitButton);
			}
		});
		
		surrenderButton.addActionListener(e -> {
			verifyDecision(Strategy.SURRENDER);
			enableDisableButtons(surrenderButton);
			playerCompletedHands++;
			currentHand.setLastHandDecision(MapUtil.DECISION_SURRENDER);
			
			updateLabels();
			
			if(playerCompletedHands >= playerHands.size()) {
				for(int handNumber = 0; handNumber < playerHands.size(); handNumber++ ) {
					playAndSettleGame(handNumber, playerHands.get(handNumber), playerHands.get(handNumber).getLastHandDecision());
					updateLabels();
				}
			}
			
			if(playerCompletedHands < playerHands.size() ) {
				currentHand = playerHands.get(playerCompletedHands);
				enableDisableButtons(splitButton);
			}
		});
		
		splitButton.addActionListener(e -> {
			verifyDecision(Strategy.SPLIT);
			enableDisableButtons(splitButton);
			
			Hand splitHand = currentHand.splitHand();
			playerHands.add(splitHand);
			playerNumberOfHands++;
			
	        //create gameInfo for this split hand
			createGameInfo(playerNumberOfHands);
			
			//Bet for the split hand
			game.getTable().getBetting().bet(game.getTable().getBetting().getBettingProperties(), currentHand.getBet());
			tableBet.setTableBet(currentHand.getBet());
			splitHand.commitTableBet(currentHand.getBet()); 
			
			//If the split is splitting a pair of Ace, set the flag
			if(currentHand.getCards().get(0).getValue() == 1) {
				currentHand.setSplitedAceHand(true);
				splitHand.setSplitedAceHand(true);
			}
			updateLabels();
		});
		
		frame.pack();
		frame.setVisible(true);
	}
	
	public void verifyDecision(int playerDecision) {
		int strategyDecision = 	Player.getTheDecision(
				currentHand, 
				game.getTable().getPlayer(), 
				game.getTable().getDealer(), 
				game.getTable().getCardShoe(), 
				game.getTable().getBetting(), 
				game.getTable().getPlayer().getStrategy());
		
		if (strategyDecision != playerDecision) {
			message.setText("Decision should be " + Strategy.decisionToString(strategyDecision));
		}
	}
	
	public void playAndSettleGame(int handNumber, Hand hand, int playerDecision) {
		game.getTable().getDealer().turnFaceDownCardUp();
		game.getTable().dealerDrawCards();
		dealerScoreLabel.setText("Dealer Score: " + game.getTable().getDealer().score());
		gameInfos.get(handNumber).playerMessage.setText(settleGame(playerDecision, hand));
		
		//Somehow, this repaint() seems necessary. Otherwise
		//The dealer score sometimes display incorrectly, no idea why.
		this.repaint();
		
		//Important: It only reBet for Hand1
		if(handNumber == 0) {
			reBet(hand);
		}
		updateLabels();
		
		//turn on the betting flag
		this.isBettingPeriod = true;
	}
	
	public String settleGame(int playerDecision, Hand hand) {
		int playResult = Settler.settle(playerDecision, hand, 
						game.getTable().getDealer(), game.getTable().getCardShoe(), game.getTable().getBetting());
		
		String gameMessage = "";
		
		if (playResult == MapUtil.PLAYER_WIN) {
			gameMessage = "Player win $" + hand.getBet();
		} else if (playResult == MapUtil.PLAYER_DOUBLE_WIN) {
			gameMessage = "Player double win $" + hand.getBet() * 2;
		} else if (playResult == MapUtil.PLAYER_DOUBLE_LOOSE) {
			gameMessage = "Player double lost $" + hand.getBet() * 2;
		} else if (playResult == MapUtil.PLAYER_DOUBLE_PUSH) {
			gameMessage = "Player double PUSH";
		} else if (playResult == MapUtil.PLAYER_SURRENDER) {
			gameMessage = "Player Surrender, lost half bet $" + hand.getBet() * 0.5;
		} else if (playResult == MapUtil.DEALER_WIN) {
			gameMessage = "Player lost $" + hand.getBet();
		} else if (playResult == MapUtil.PUSH) {
			gameMessage = "PUSH";
		} else if (playResult == MapUtil.DEALER_BUST) {
			gameMessage = "Dealer Bust, Player win $" + hand.getBet();
		} else if (playResult == MapUtil.PLAYER_BUST) {
			gameMessage = "Player Bust, lost $" + hand.getBet();
		} else if (playResult == MapUtil.PLAYER_BLACKJACK_WIN) {
			gameMessage = "Player BlackJack win $" + hand.getBet() * 1.5;
		} else if (playResult == MapUtil.PLAYER_BLACKJACK_PUSH) {
			gameMessage = "Player BlackJack PUSH";
		} else if (playResult == MapUtil.DEALER_BLACKJACK_WIN) {
			gameMessage = "Dealer BlackJack, Player Lost $" + hand.getBet();
		} else if (playResult == MapUtil.PLAYER_BUST_DEALER_BLACKJACK) {
			gameMessage = "Player Bust, Dealer BlackJack, lost $" + hand.getBet();
		} else if (playResult == MapUtil.PLAYER_DOUBLE_LOOSE_DEALER_BLACKJACK) {
			gameMessage = "Player double, Dealer BlackJack, lost $" + hand.getBet() * 2;
		}
		
		game.getTable().getBjUtil().displayGameResult(hand, game.getTable().getDealer(), playResult);
		
		//display the New Game banner for next game.
		if (!game.getTable().isGuiStrategyTraining()) {
			logger.info("\n\n <<< NEW GAME " + 	++gamePlayed + " >>>");
		}else {
			logger.info("\n\n <<< NEW TRAINING GAME " + " >>>");
		}
		
		return gameMessage;
	}

	public void createGameInfo(int handNumber) {
        gameInfos.add(playerGameInfo.getPlayerGameInfo(handNumber-1));
		this.add(gameInfos.get(handNumber-1).playerScore);
		this.add(gameInfos.get(handNumber-1).playerBet);
		this.add(gameInfos.get(handNumber-1).playerMessage);
	}
	
	public void removeGameInfo(int handNumber) {
 		this.remove(gameInfos.get(handNumber-1).playerScore);
		this.remove(gameInfos.get(handNumber-1).playerBet);
		this.remove(gameInfos.get(handNumber-1).playerMessage);
        gameInfos.remove(handNumber-1);
	}
	
	public void setInfoDisplay(boolean displayInfo) {
		infoDecks.setVisible(displayInfo);
		infoNextCard.setVisible(displayInfo);
		infoPenetration.setVisible(displayInfo);
		infoHiLoCount.setVisible(displayInfo);
		infoTrueCount.setVisible(displayInfo);
		infoBetMultiplier.setVisible(displayInfo);
		infoBetAmount.setVisible(displayInfo);
	}
	
	public void enableDisableButtons(JButton button) {
		if(button == dealButton) {
			dealButton.setEnabled(false);
			hitButton.setEnabled(true);
			doubleButton.setEnabled(true);
			stayButton.setEnabled(true);
			surrenderButton.setEnabled(true);
			
			if (this.currentHand.isPair() && this.currentHand.getCards().size() == 2) {
				 splitButton.setEnabled(true);
			}else {
				splitButton.setEnabled(false);
			}
			
			enableBetButtons(false);
		}else if(button == hitButton){
			if(currentHand.getCards().size() != 2) {
				doubleButton.setEnabled(false);
				surrenderButton.setEnabled(false);
				splitButton.setEnabled(false);
			}else {
				doubleButton.setEnabled(true);
				surrenderButton.setEnabled(true);
				stayButton.setEnabled(true);
				if (this.currentHand.isPair()) {
					 splitButton.setEnabled(true);
				}else {
					splitButton.setEnabled(false);
				}
			}
		}else if(button == doubleButton){
			hitButton.setEnabled(false);
			dealButton.setEnabled(true);
			doubleButton.setEnabled(false);
			stayButton.setEnabled(false);
			surrenderButton.setEnabled(false);
			
			enableBetButtons(true);
		}else if(button == stayButton){
			
			hitButton.setEnabled(false);
			dealButton.setEnabled(true);
			doubleButton.setEnabled(false);
			stayButton.setEnabled(false);
			surrenderButton.setEnabled(false);
			
			enableBetButtons(true);
		} else if(button == surrenderButton){
			hitButton.setEnabled(false);
			dealButton.setEnabled(true);
			doubleButton.setEnabled(false);
			stayButton.setEnabled(false);
			surrenderButton.setEnabled(false);
			
			enableBetButtons(true);
		}else if(button == splitButton){
			dealButton.setEnabled(false);
			hitButton.setEnabled(true);
			doubleButton.setEnabled(false);
			stayButton.setEnabled(false);
			surrenderButton.setEnabled(false);
			splitButton.setEnabled(false);
			
			enableBetButtons(false);
		}
	}
	
	public void enableBetButtons(boolean enable) {
		bet1DollarButton.setEnabled(enable);
		bet5DollarButton.setEnabled(enable);
		bet10DollarButton.setEnabled(enable);
		bet25DollarButton.setEnabled(enable);
		bet50DollarButton.setEnabled(enable);
		bet100DollarButton.setEnabled(enable);
		bet500DollarButton.setEnabled(enable);
		clearBetButton.setEnabled(enable);
	}
	
	public void bet(double bet) {
		tableBet.addTableBet(bet);
		game.getTable().getBetting().bet(game.getTable().getBetting().getBettingProperties(), bet);
		updateLabels();
	}
	
	public void reBet(Hand hand) {
		//rebet the same amount of bet to Table Bet
		tableBet.setTableBet(hand.getBet());
		game.getTable().getBetting().bet(game.getTable().getBetting().getBettingProperties(), hand.getBet());
	}
	
	public void updateLabels() {
		//Info area
		String usedDecks = "Used Decks: " + (game.getTable().getCardShoe().getNumberOfDecks() - 
				game.getTable().getCardShoe().getPenetration());
		usedDecks = usedDecks.length() > 16 ? usedDecks.substring(0, 15) : usedDecks;
		usedCardCount.setText(usedDecks);
		
		infoDecks.setText("Decks: " + game.getTable().getCardShoe().getNumberOfDecks());
		infoNextCard.setText("Next Card: " + (game.getTable().getCardShoe().getUsedCardCount() + 1));
		infoHiLoCount.setText("HiLo Count: " + game.getTable().getCardShoe().getRunningCount());
		
		String pen = "Penetration: " + game.getTable().getCardShoe().getPenetration();
		pen = pen.length() > 18 ? pen.substring(0, 17) : pen;
		infoPenetration.setText(pen);
	
		String trueCount = "True Count: " + game.getTable().getCardShoe().getTrueCount();
		trueCount = trueCount.length() > 18 ? trueCount.substring(0, 17) : trueCount;
		infoTrueCount.setText(trueCount);
		
		String betMultiplier = "Bet Multiplier: " + game.getTable().getBetting().betMultiplier();
		betMultiplier = betMultiplier.length() > 20 ? betMultiplier.substring(0, 19) : betMultiplier;
		infoBetMultiplier.setText(betMultiplier);
		
		double calcBetAmount = game.getTable().getBetting().betMultiplier() * 
								game.getTable().getBetting().getBaseBetAmount(game.getTable().getBetting().getBettingProperties());
		String betAmount = "Bet Amount: $" + calcBetAmount;
		betAmount = betAmount.length() > 20 ? betAmount.substring(0, 19) : betAmount;
		infoBetAmount.setText(betAmount);
		
		playerBalance.setText("$" + game.getTable().getBetting().getCurrentBalance(game.getTable().getBetting().getBettingProperties()));
		
		for(int i = 0; i < gameInfos.size(); i++) {
			//This check is necessary because at the beginning of the game,
			//there is no Hand yet.
			if(playerHands.size() >=1 ) {
				gameInfos.get(i).playerScore.setText("Player Score: " + playerHands.get(i).score());
			}
			
			//1. Before dealing a card, the bet is held at TableBet.
			//   After dealing (playing period), the bet is at getBet().
			//2. Only Hand1 need to bet at the betting period
			if(this.isBettingPeriod ) {
				if(i == 0) {
					gameInfos.get(i).playerBet.setText("Bet: $" + tableBet.getTableBet());
				}
			}else {
				gameInfos.get(i).playerBet.setText("Bet: $" + playerHands.get(i).getBet());
				/*
				if (playerHands.get(i).getLastHandDecision() == mapUtil.DECISION_DOUBLE) {
				    gameInfos.get(i).playerBet.setText("Bet: $" + 2* playerHands.get(i).getBet());
				}else {
					gameInfos.get(i).playerBet.setText("Bet: $" + playerHands.get(i).getBet());
				}
				*/
			}
		}
		
		repaint();
	}
	
	public JLabel jLabel(String caption, int x, int y, int width, int height) {
		JLabel jLabel = new JLabel(caption);
		jLabel.setLocation(x, y);
		//jLabel.setFont(new Font("Serif", Font.PLAIN, 22));
		jLabel.setFont(new Font("Times", Font.PLAIN, 22));
		jLabel.setForeground(Color.white);
		jLabel.setSize(width, height);
		
		return jLabel;
	}
	
	public JButton jButton(String caption, int x, int y, int width, int height) {
		JButton jButton = new JButton(caption);
		jButton.setLocation(x, y);
		jButton.setSize(width, height);
		jButton.setFont(new Font("Times", Font.PLAIN, 18));
		jButton.setMargin(new Insets(0, 0, 0, 0));
		
		return jButton;
	}
	
	public JCheckBox jCheckBox(String caption, int x, int y, int width, int height) {
		JCheckBox jCheckBox = new JCheckBox(caption);
		jCheckBox.setLocation(x, y);
		jCheckBox.setSize(width, height);
		jCheckBox.setFont(new Font("Times", Font.PLAIN, 20));
		jCheckBox.setMargin(new Insets(0, 0, 0, 0));
		jCheckBox.setBackground(new Color(0, 102, 0));
		jCheckBox.setForeground(Color.WHITE);
		
		return jCheckBox;
	}
	
	//JRadioButton
	public JRadioButton jRadioButton(String caption, int x, int y, int width, int height) {
		JRadioButton jRadioButton = new JRadioButton(caption);
		jRadioButton.setLocation(x, y);
		jRadioButton.setSize(width, height);
		jRadioButton.setFont(new Font("Times", Font.PLAIN, 18));
		jRadioButton.setMargin(new Insets(0, 0, 0, 0));
		//setColor(new Color(0, 102, 0));
		jRadioButton.setBackground(new Color(0, 102, 0));
		jRadioButton.setForeground(Color.WHITE);
		
		return jRadioButton;
	}


	public void paintComponent(Graphics g) {
		// background 
		//g.setColor(new Color(0, 128, 0));
		g.setColor(new Color(0, 102, 0));
		g.fillRect(0, 0, getWidth(), getHeight());
		
		//draw dealt card for animation
		//drawCard(g, dealtCard, x, y);

		// draw dealer cards
		for (int i = 0; i < game.getTable().getDealer().getCards().size(); i++) {
			drawCard(g, game.getTable().getDealer().getCards().get(i), getDealerCardPosition(i).x,
					getDealerCardPosition(i).y);
		}
		
		//draw arrow for indicating which hand is current hand
		int arrowPosition = playerCompletedHands == playerNumberOfHands ?  playerCompletedHands - 1 : playerCompletedHands;
		drawArrow(g, getArrowPosition(arrowPosition).x, getArrowPosition(arrowPosition).y);
		
		//draw player cards for all playerHands
		for(int j = 0; j < playerHands.size(); j++) {
			for (int i = 0; i < playerHands.get(j).getCards().size(); i++) {
				drawCard(g, playerHands.get(j).getCards().get(i), getPlayerCardPosition(i, j).x,
						getPlayerCardPosition(i, j).y);
			}
		}
	}
	
	private void drawCard(Graphics g, Card card, int x, int y) {
		if (card == null) {
			//g.setColor(Color.BLACK);
			//g.drawRect(x, y, CARD_WIDTH, CARD_HEIGHT);
			return;
		} else {
			Image image = new ImageIcon(card.getImageURL()).getImage();
			g.drawImage(image, x, y, CARD_WIDTH, CARD_HEIGHT, null);
		}
	}
	
	private void drawArrow(Graphics g, int x, int y) {
		URL arrowURL = this.getClass().getClassLoader().getResource("cards/images/arrow3.png");
		Image image = new ImageIcon(arrowURL).getImage();
		g.drawImage(image, x, y, ARROW_WIDTH, ARROW_HEIGHT, null);
	}
	

	//This method is not used. It was created 
	//when testing animation
    public void moveCard(Card c) {
    	while(!c.movedToTarget()) {
    		c.moveCard();
    		
    		for (int i = 1; i < 100000000; i++) {
    			for (int j = 0; j < 100000000; j++) {
    				int k = i * j;
    			}
    		}
    	    repaint();
    	}
    }
 
	//This method is not used. It was created 
	//when testing animation
    @Override
    public void actionPerformed(ActionEvent e) {

    	if(x > final_x) { x -= 4; }
        if(y < final_y) {y += 4;}
        
        if (x <= final_x && y >= final_y) {
        	timer.stop();
        	this.inAnimation = false;
        }

        repaint();
    }

	public Coordinate getDealerCardPosition(int cardNumber) {
		Coordinate position = new Coordinate();

		if (cardNumber < 2) {
			position.setX(DEALER_CARD_START_X + SPACING + cardNumber * (CARD_WIDTH + SPACING));
		} else {
			position.setX(DEALER_CARD_START_X + SPACING + 2 * (CARD_WIDTH + SPACING) + 2 * SPACING
					+ (cardNumber - 2) * (CARD_WIDTH + SPACING) / 2);
		}

		position.setY(DEALER_CARD_START_Y);
		return position;
	}
	
	public Coordinate getPlayerCardPosition(int cardNumber, int handNumber) {
		Coordinate position = new Coordinate();

		if (cardNumber < 2) {
			position.setX(handNumber * PLAYER_HAND_SPACING + PLAYER_CARD_START_X + SPACING + cardNumber * (CARD_WIDTH + SPACING));
			position.setY(PLAYER_CARD_START_Y);
		} else {
			position.setX(handNumber * PLAYER_HAND_SPACING + PLAYER_CARD_START_X + SPACING + 
					CARD_HORIZONTAL_SPACING + (cardNumber - 2) * (CARD_WIDTH + SPACING) / 2);
			position.setY(PLAYER_CARD_START_Y + CARD_VERTICAL_SPACING);
		}

		return position;
	}
	
	public Coordinate getArrowPosition(int handNumber) {
		Coordinate position = new Coordinate();

			position.setX(handNumber * PLAYER_HAND_SPACING + PLAYER_CARD_START_X + (2*CARD_WIDTH + SPACING) - ARROW_WIDTH);
			position.setY(PLAYER_CARD_START_Y - 3 * SPACING + 2 - ARROW_HEIGHT);
		return position;
	}
	
	private class Coordinate {
		private int x;
		private int y;

		public void setX(int x) {
			this.x = x;
		}

		public void setY(int y) {
			this.y = y;
		}
	}
	
	private class TableBet{
		private double tableBet = 0;
		
		public void addTableBet(double bet) {
			this.tableBet += bet;
		}
		
		public void setTableBet(double bet) {
			this.tableBet = bet;
		}
		
		public double getTableBet() {
			return this.tableBet;
		}
		
		public double removeTableBet() {
			double orig_tableBet = this.tableBet;
			this.tableBet = 0;
			return orig_tableBet;
		}
	}
	
	private class PlayerGameInfo {
		JLabel playerScore;
		JLabel playerBet;
		JLabel playerMessage ;

		//A factory method to return PlayerGameInfo at the specified
		//Hand Number position. The position is coordinated with the
		//player hands cards positions.
		public  PlayerGameInfo getPlayerGameInfo(int handNumber) {
			PlayerGameInfo gameInfo = new PlayerGameInfo();
			gameInfo.playerScore = jLabel("Player Score:", handNumber * PLAYER_HAND_SPACING + PLAYER_LABEL_START_X, 
					PLAYER_LABEL_START_Y,JLABEL_WIDTH, JLABEL_HEIGHT);
			gameInfo.playerBet = jLabel("Bet:", handNumber * PLAYER_HAND_SPACING + PLAYER_LABEL_START_X, 
					CARD_HEIGHT * 3 + SPACING * 14, JLABEL_WIDTH, JLABEL_HEIGHT);
			gameInfo.playerMessage = jLabel("", handNumber * PLAYER_HAND_SPACING + PLAYER_LABEL_START_X, 
					CARD_HEIGHT * 3 + SPACING * 15 + JLABEL_HEIGHT, JLABEL_WIDTH * 4, JLABEL_HEIGHT);
			
			return gameInfo;
		}
	}
}