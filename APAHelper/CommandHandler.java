package APAHelper;
import java.util.Queue;
import java.util.LinkedList;

public class CommandHandler {

    String[] text, quotes;
    Queue<String> textQueue, quoteQueue;
    InputHandler inputSupplier;

    public CommandHandler(InputHandler inputSupplier){
        this.inputSupplier = inputSupplier;
    }
    public void paperAnalytics(String input){
        String[] inputList = input.split(" ");
        textQueue = new LinkedList<String>();
        quoteQueue = new LinkedList<String>();
        for(int i = 0; i < inputList.length; i++){
            if(inputList[i].charAt(0) == '('){
                i = quotesSubroutine(inputList, i);
            }
            else{
                textQueue.add(inputList[i]);
            }
        }
        text = new String[textQueue.size()-1];
        for(int j = 0; j < textQueue.size(); j++){
            text[j] = textQueue.poll();
        }

        quotes = new String[quoteQueue.size()-1];
        for(int k = 0; k < quoteQueue.size(); k++){
            quotes[k] = quoteQueue.poll();
        }


        display("Completed Analytics Successfully!");
    }

    private int quotesSubroutine(String[] inputList, int i){
        String quote = "";
        while(inputList[i].charAt(inputList[i].length()-1) != ')'){
            quote += inputList[i];
            i++;
        }
        quoteQueue.add(quote);
        return i;
    }

    public void quoteFormat(String input){
        return;
    }

    public void wordCount(){
        display("Word Count: " + Integer.toString(text.length));
    }

    public void wordCountQuotes(){
        display("Word Count (With Quotes): " + Integer.toString(text.length+quotes.length));
    }

    public void text(){
        String strText = "";

        for(int i = 0; i < text.length; i++){
            strText += " " + text[i];
        }
        inputSupplier.display(strText);
    }

    public void quotes(){
        inputSupplier.display(this.quotes);
    }

    private void display(String text){
        inputSupplier.display(text);
    }

    private void display(String[] text){
        inputSupplier.display(text);
    }
}
