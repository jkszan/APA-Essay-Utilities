package APAHelper.Code;
import java.util.Queue;
import java.util.LinkedList;

public class CommandHandler {

    //Arrays text and quotes store the text of the paper and quotes of the paper for use in other functions
    String[] text, quotes;  
    
    //outputConsole serves as a reference back to the active instance of TerminalConsole to allow it to display information
    TerminalConsole outputConsole;

    //Constructor for CommandHandler
    public CommandHandler(TerminalConsole outputConsole){
        this.outputConsole = outputConsole;             //Initializing outputConsole to the active instance of TerminalConsole
    }

    //Method that handles the paperAnalytics command
    public void paperAnalytics(String input){

        //Splitting the input word by word to make it iterable
        String[] inputList = input.split(" ");

        //Initializing queues for both the text and quotes to make the processing simpler
        Queue<String> textQueue, quoteQueue;
        textQueue = new LinkedList<String>();
        quoteQueue = new LinkedList<String>();

        //This loop checks for the beginning of a quote. If it detects a quote it sends the index
        //to the quotesSubroutine helper function, else it adds the word to the textQueue
        for(int i = 0; i < inputList.length; i++){
            if(inputList[i].contains("(")){
                i = quotesSubroutine(inputList, i, quoteQueue);
            }
            else{
                textQueue.add(inputList[i]);
            }
        }

        //Creates a final array for the text and empties the queue into it
        text = new String[textQueue.size()];
        for(int j = 0; j < text.length; j++){
            text[j] = textQueue.poll();
        }

        //Creates a final array for the quotes and empties the queue into it
        quotes = new String[quoteQueue.size()];
        for(int k = 0; k < quotes.length; k++){
            quotes[k] = quoteQueue.poll();
        }

        //Displays a message to signal that the process was successfully completed
        display("Completed Analytics Successfully!");
    }

    //A subroutine that handles an instance of a quote in the analysed paper
    private int quotesSubroutine(String[] inputList, int i, Queue<String> quoteQueue){
        
        //This loop stores the entirety of a quote as one string as to not clutter output when calling quotes
        String quote = "";
        while(!inputList[i].contains(")")){
            quote += inputList[i];
            i++;
        }
        quote += inputList[i];
        quoteQueue.add(quote);
        return i;
    }

    //Displays the count of the words only in the analysed paper
    public void wordCount(){
        display("Word Count: " + Integer.toString(text.length));
    }

    //Displays the count of both the words and quotes in the analysed paper
    public void wordCountQuotes(){
        display("Word Count (With Quotes): " + Integer.toString(text.length+quotes.length));
    }

    //Method that handles the text command and displays expected output
    public void text(){
        String strText = "";

        //This loop reformats the int[] text into a more easily readable string version that will be displayed
        for(int i = 0; i < text.length; i++){
            strText += " " + text[i];
        }
        display(strText);
    }

    //Method that handles the quotes command and displays expected output
    public void quotes(){
        display(this.quotes);
    }

    //Delegation function that calls display(String) in TerminalConsole
    private void display(String text){
        outputConsole.display(text);
    }

    //Delegation function that calls display(String[]) in TerminalConsole
    private void display(String[] text){
        outputConsole.display(text);
    }
}
