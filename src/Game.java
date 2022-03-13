import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Game {
    private static final Map<Integer, String> rankNames = new HashMap<>() {{
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
    private static final Map<Integer, String> suitNames = new HashMap<>() {{
            put(0, "Clubs");
            put(1, "Spades");
            put(2, "Hearts");
            put(3, "Diamonds");
    }};
    private static final Deck deck1 = new Deck();
    private static final Deck deck2 = new Deck();
    static Card card1;
    static Card card2;

    public static void main(String[] args) {
        while (true) {
            if (deck1.getDeckSize() == 0 && deck2.getDeckSize() == 0) {
                System.out.println("Tie! \n");
            } else if (deck1.getDeckSize() == 0) {
                System.out.println("Player 1 has run out of cards! \n");
                System.out.println("Game over. Player 2 has won! \n");
                break;
            } else if (deck2.getDeckSize() == 0) {
                System.out.println("Player 2 has run out of cards! \n");
                System.out.println("Game over. Player 1 has won! \n");
                break;
            } else {
                card1 = deck1.dealCard();
                card2 = deck2.dealCard();
                printMove(card1, card2);
                if (card1.getRank() > card2.getRank()) {
                    deck1.addToBottom(card1);
                    deck1.addToBottom(card2);
                    System.out.println("Player 1 has beaten Player 2 this round and takes his card. \n");
                } else if (card1.getRank() < card2.getRank()) {
                    deck2.addToBottom(card1);
                    deck2.addToBottom(card2);
                    System.out.println("Player 2 has beaten Player 1 this round and takes his card. \n");
                } else {
                    System.out.println("WAR! \n");
                    ArrayList<Card> cardsAtStake = new ArrayList<>() {{
                        add(card1); add(card2);
                    }};
                    war(cardsAtStake);
                }
            }
        }
    }

    private static void war(ArrayList<Card> cardsAtStake) {
        for (int i = 0; i < 3; i++) {
            if (deck1.getDeckSize() == 0 && deck2.getDeckSize() == 0) {
                return;
            } else if (deck1.getDeckSize() == 0) {
                return;
            } else if (deck2.getDeckSize() == 0) {
                return;
            } else {
                Card card1 = deck1.dealCard();
                Card card2 = deck2.dealCard();
                printMove(card1, card2);
                cardsAtStake.add(card1);
                cardsAtStake.add(card2);
            }
        }
        int lastIdx1 = cardsAtStake.size() - 2;
        int lastIdx2 = cardsAtStake.size() - 1;
        Card cardLast1 = cardsAtStake.get(lastIdx1);
        Card cardLast2 = cardsAtStake.get(lastIdx2);
        if (cardLast1.getRank() > cardLast2.getRank()) {
            deck1.addToBottom(cardsAtStake);
            System.out.println("Player 1 has beaten Player 2 at war and takes all of the cards at stake. \n");
        } else if (cardLast1.getRank() < cardLast2.getRank()) {
            deck2.addToBottom(cardsAtStake);
            System.out.println("Player 2 has beaten Player 1 at war and takes all of the cards at stake. \n");
        } else {
            war(cardsAtStake);
        }
    }


    private static void printMove(Card card1, Card card2) {
           System.out.println(
                   "Player 1 has drawn the " + rankNames.get(card1.getRank()) + " of " +
                           suitNames.get(card1.getSuit()) + ".\n"
           );
            System.out.println(
                    "Player 2 has drawn the " + rankNames.get(card2.getRank()) + " of " +
                            suitNames.get(card2.getSuit()) + ".\n"
            );
    }
}
