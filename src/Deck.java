import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

public class Deck {
    private static final int DECK_SIZE = 52;

    private final LinkedList<Card> deck = new LinkedList<>();

    public Deck() {
        for (int count = 0; count < DECK_SIZE; count++)
        {
            deck.add(new Card(count / 13, count % 13));
        }
        Collections.shuffle(deck, new Random());
    }

    public Card dealCard() {
        return deck.pop();
    }
    public void addToBottom(Card card) {
        deck.addLast(card);
    }
    public void addToBottom(ArrayList<Card> cards) {
        deck.addAll(0, cards);
    }
    public int getDeckSize() {
        return deck.size();
    }
}
