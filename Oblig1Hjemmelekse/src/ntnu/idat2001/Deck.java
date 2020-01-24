package ntnu.idat2001;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * A Class which creates a deck of 52 cards
 * The tasks i was given:
 * a) Lag en klasse Deck som oppretter en fullstendig kort-stokk (52 kort)
 * b) Lag en metode "assign" i Deck som plukker tilfeldig n kort fra kortstokket og returnerer disse
 * i en Collection. "n" er et tall mellom 1 og 52 som sendes inn som parameter til assign
 * funksjonen.
 * c) Skriv et uttrykk med filter og forEach som skriver ut alle spar-kort (suit = 'S').
 * d) Skriv et uttrykk med filter og collect som samler alle hjerter-kort (suit = 'H') i en ny liste.
 * e) Skriv et uttrykk med map som gir en ny list med kortenes kortfarge.
 * f) Skriv et uttrykk med reduce som gir summen av kortverdiene (face).
 * g) Skriv et uttrykk med anyMatch som sier om spar dame finnes i lista.
 * h) Skriv et uttrykk som sier om lista er en poker-flush, dvs. har fem kort hvor alle har samme
 * kortfarge.
 * @date Last update: 24.01.2020
 * @author Martin Iversen
 * @Version 1.0
 */

public class Deck {
    /**
     * The fields of the class
     */
    private ArrayList<Card> DeckList; //Initializes an arrayList containing the playing cards
    private List<Card> CopyList; // Initializes a copy of the DeckList
    private List<Card> ResultList; // Initializes a List containing the results of the assign() method
    private List<Card> HeartList; // Initializes a List containing all the cards of the Suit Hearts

    /**
     * The constructor of the class Deck
     * Makes use of the arrayList from the fieild
     * Uses the addFullDeck method which fills the deck with cards
     */
    public Deck() {
        DeckList = new ArrayList<Card>();
        addFullDeck();
        assign(0);
        printSuit('H');
        listHearts();
        sortColor("black");
        addFaces();
        checkQueen();
        checkFlush();
    }

    /**
     * A method which adds 52 cards all with a number from 1 to 13 in the categories Hearts, Spades, Clover and Routes
     */
    public void addFullDeck() {
        for (int i = 0; i < 14; i++) {
            DeckList.add(new Card('H', i));
            DeckList.add(new Card('S', i));
            DeckList.add(new Card('C', i));
            DeckList.add(new Card('R', i));
        }
    }

    /**
     * A method which takes an int parameter n and returns n number of random cards and adds them to a resultlist
     *
     * @param n the number of cards you want
     * @return returns n num random number of cards
     */
    public List<Card> assign(int n) {
        CopyList = DeckList;
        ResultList = new ArrayList<>();
        Collections.shuffle(CopyList);
        CopyList.stream().distinct().limit(n).forEach(Card -> {
            System.out.println(Card.getDetails());
            ResultList.add(Card);
        });
        return ResultList;
    }

    /**
     * A method which prints a given suit from the deckList
     *
     * @param Suit The type of suit you want printed
     */
    public void printSuit(char Suit) {
        DeckList.stream().distinct().forEach(Card ->{if(Card.getSuit()==Suit){
        System.out.println(Card.getDetails());
        }
        });
    }

    /**
     * A method which sorts all the H suits (hearts) in a separate list
     * @return returns the HeartList
     */
    public List<Card> listHearts(){
        HeartList = new ArrayList<>();
        HeartList=DeckList;
        HeartList.stream().distinct().forEach(Card ->{
            if(Card.getSuit()=='H')
            {
            System.out.print(Card.getDetails());
            Collectors.toList();
            }
        });
        return HeartList;
    }

    /**
     * A method which sorts the cards in the deckList in a given color Red or Black, red being hearts and routes
     * And black being Spades and Clubs
     * @param color a given color
     */
    public void sortColor(String color){
        DeckList.stream().distinct().forEach(Card->{
            if(color.toLowerCase() == "red"){
                if(Card.getSuit()=='H'||Card.getSuit()=='R'){
                    System.out.println(Card.getDetails());
                }
            }
            else if(color.toLowerCase()=="black"){
                if(Card.getSuit()=='S'||Card.getSuit()=='C'){
                    System.out.println(Card.getDetails());
                }
            }
        });
    }

    /**
     * A method which takes all the faces of the cards in the deck and adds all the faces together
     */
    public void addFaces(){
        System.out.println("The total value of the faces in the card deck not counting the joker is "
                +DeckList.stream().distinct().map(Card->Card.getFace()).reduce(0,(total, count)->  total+count));
    }

    /**
     * A method which checks whether or not the queen of spades is in the deck
     */
    public void checkQueen(){
        if(DeckList.stream().anyMatch(q->q.getSuit()=='S'&& q.getFace()==12)==true){
            System.out.println("The queen of spades is in the deckList");
        }
        else{
            System.out.println("The deck does not contain the queen of spades");
        }
    }

    /**
     * A method which shuffles a deck, picks 5 cards and checks whether or not
     * they all have the same color by creating 2 lists, 1 for black cards and 1 for red cards
     * Then it checks whether or not the black and red lists contain 5 cards, if yes it prints a message
     */
    public void checkFlush(){
        assign(5);
        ArrayList<Card> flushRed = new ArrayList<>();
        ArrayList<Card> flushBlack = new ArrayList<>();
        ResultList.forEach(Card->{
            if(Card.getSuit()=='H'&& Card.getSuit()=='R'){
                flushRed.add(Card);
            }
            else if(Card.getSuit()=='H'&& Card.getSuit()=='R'){
                flushBlack.add(Card);
            }
        });
        if(flushRed.size()==5){
            System.out.println("Congratulations your 5 cards are all of the color red!");
        }
        else if(flushBlack.size()==5){
            System.out.println("Congratulations your 5 cards are all of the color black!");
        }
        else{
            System.out.println("Sorry your cards are not of the same color, your cards are printed above");
        }
    }
}