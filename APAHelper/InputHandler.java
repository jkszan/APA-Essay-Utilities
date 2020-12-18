package APAHelper;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class InputHandler {
    
    CommandHandler commandImplementation;
    TerminalConsole outputConsole;
    Boolean willContinue;

    public InputHandler(TerminalConsole console){
        outputConsole = console;
        commandImplementation = new CommandHandler(this);
        willContinue = true;
    }

    public void parse(String inputText){
        String[] commandList = inputText.split(" ", 2);

        switch(commandList[0]){
            case "/paperAnalytics":
                commandImplementation.paperAnalytics(commandList[1]);
                break;
            case "/quoteFormat":
                commandImplementation.quoteFormat(inputText);
                break;
            case "/wordcount":
                commandImplementation.wordCount();
                break;
            case "/wordcountQuotes":
                commandImplementation.wordCountQuotes();
                break;
            case "/text":
                commandImplementation.text();
                break;
            case "/quotes":
                commandImplementation.quotes();
                break;
            case "/quit":
                willContinue = false;
                break;
            default:
                outputConsole.errDisplay("Command " + commandList[0] + " not recognized");
        }
    }

    public void display(String text){
        outputConsole.display(text);
    }   

    public void display(String[] text){
        outputConsole.display(text);
    }

    public static void main(String[] args){

        InputHandler textHandler = new InputHandler(new TerminalConsole());

    }

}
