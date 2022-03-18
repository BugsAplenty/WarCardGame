import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Game {
    private static final Map<Integer, String> rankNames = new HashMap<Integer, String>() {{
            put(0, "Two");
            put(1, "Three");
            put(2, "Four");
            put(3, "Five");
            put(4, "Six");
            put(5, "Seven");
            put(6, "Eight");
            put(7, "Nine");
            put(8, "Ten");
            put(9, "Jack");
            put(10, "Queen");
            put(11, "King");
            put(12, "Ace");
    }};
    private static final Map<Integer, String> suitNames = new HashMap<Integer, String>() {{
            put(0, "Clubs");
            put(1, "Spades");
            put(2, "Hearts");
            put(3, "Diamonds");
    }};
    private static final Deck deck1 = new Deck();
    private static final Deck deck2 = new Deck();
    private static final ArrayList<Card> pot = new ArrayList<>();
    static Card card1;
    static Card card2;

    public static void main(String[] args) {
        while (true) {
            if (deck1.getDeckSize() == 0 && deck2.getDeckSize() == 0) {
                System.out.println("Tie! \n");
            } else if (deck1.getDeckSize() == 0) {
                JOptionPane.showMessageDialog(
                        null,
                        "Player 1 has run out of cards!\n Game over. Player 2 has won!\n"
                        );
//                System.out.println(
//                        """
//                        Player 1 has run out of cards!\s
//                        Game over. Player 2 has won!\s
//                        """
//
//                );
                break;
            } else if (deck2.getDeckSize() == 0) {
                JOptionPane.showMessageDialog(
                        null,
                        "Player 2 has run out of cards!\n Game over. Player 1 has won!\n"

                        );
//                System.out.println(
//                        """
//                        Player 2 has run out of cards!\s
//                        Game over. Player 1 has won!\s
//                        """
//                );
                break;
            } else {
                card1 = deck1.dealCard();
                card2 = deck2.dealCard();
                moveDialog();
                if (card1.getRank() > card2.getRank()) {
                    deck1.addToBottom(card1);
                    deck1.addToBottom(card2);
                    JOptionPane.showMessageDialog(
                            null,
                            "Player 1 has beaten Player 2 this round and takes his card. \n"
                    );
//                    System.out.println(
//                            "Player 1 has beaten Player 2 this round and takes his card. \n"
//                    );
                } else if (card1.getRank() < card2.getRank()) {
                    deck2.addToBottom(card1);
                    deck2.addToBottom(card2);
                    JOptionPane.showMessageDialog(
                            null,
                            "Player 2 has beaten Player 1 this round and takes his card. \n"
                    );
//                    System.out.println(
//                            "Player 2 has beaten Player 1 this round and takes his card. \n"
//                    );
                } else {
//                    System.out.println("WAR! \n");
                    JOptionPane.showMessageDialog(null, "WAR! \n");
                    pot.add(card1);
                    pot.add(card2);
                    war();
                    pot.clear();
                }
            }
        }
    }

    private static void war() {
        for (int i = 0; i < 3; i++) {
            if (deck1.getDeckSize() == 0 && deck2.getDeckSize() == 0) {
                return;
            } else if (deck1.getDeckSize() == 0) {
                return;
            } else if (deck2.getDeckSize() == 0) {
                return;
            } else {
                card1 = deck1.dealCard();
                card2 = deck2.dealCard();
                moveDialog();
                pot.add(card1);
                pot.add(card2);
            }
        }
        int lastIdx1 = pot.size() - 2;
        int lastIdx2 = pot.size() - 1;
        Card cardLast1 = pot.get(lastIdx1);
        Card cardLast2 = pot.get(lastIdx2);
        if (cardLast1.getRank() > cardLast2.getRank()) {
            deck1.addToBottom(pot);
            JOptionPane.showMessageDialog(
                    null,
                    "Player 1 has beaten Player 2 at war and takes all of the cards at stake. \n"
            );
//            System.out.println(
//                    "Player 1 has beaten Player 2 at war and takes all of the cards at stake. \n"
//            );
        } else if (cardLast1.getRank() < cardLast2.getRank()) {
            deck2.addToBottom(pot);
            JOptionPane.showMessageDialog(
                    null,
                    "Player 2 has beaten Player 1 at war and takes all of the cards at stake. \n"
            );
//            System.out.println(
//                    "Player 2 has beaten Player 1 at war and takes all of the cards at stake. \n"
//            );
        } else {
            JOptionPane.showMessageDialog(
                    null,
                    "War ended with tie. Another round of war begins."
            );
//            System.out.println(
//                    "War ended with tie. Another round of war begins."
//            );
            war();
        }
    }


    private static void moveDialog() {
        String rankString1 = rankNames.get(card1.getRank());
        String rankString2 = rankNames.get(card2.getRank());
        String suitString1 = suitNames.get(card1.getSuit());
        String suitString2 = suitNames.get(card2.getSuit());
        JOptionPane.showMessageDialog(
               null,
               "Player 1 has drawn the " + rankString1 + " of " + suitString1 + ".\n" +
                       "Player 2 has drawn the " + rankString2 + " of " + suitString2 + ".\n"
        );
//        System.out.println(
//                "Player 1 has drawn the " + rankString1 + " of " + suitString1 + ".\n" +
//                        "Player 2 has drawn the " + rankString2 + " of " + suitString2 + ".\n"
//        );
    }
}
