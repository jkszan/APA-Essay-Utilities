package APAHelper.Code;

public class InputHandler{
    
    //Instances of CommandHandler and TerminalConsole
    CommandHandler commandImplementation;
    TerminalConsole outputConsole;

    //Constructor for InputHandler 
    public InputHandler(){
        outputConsole = new TerminalConsole(this);      //Creating an instance of TerminalConsole
        commandImplementation = new CommandHandler(outputConsole);  //Creating an instance of CommandHandler
    }

    //Parse function checks input command string for a registered command, if a command is identified
    //it is called in commandHandler, else it is flagged as an error and displayed as such in outputConsole
    public void parse(String inputText){
        String[] commandList = inputText.split(" ", 2);

        switch(commandList[0]){
            case "/paperAnalytics":
                commandImplementation.paperAnalytics(commandList[1]);
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
                outputConsole.quit();
                break;
            default:
                outputConsole.errDisplay("Command " + commandList[0] + " not recognized");
        }
    }

    //A delegation function that displays errors in TerminalConsole
    public void errDisplay(String text){
        outputConsole.errDisplay(text);
    }   

    public static void main(String[] args){

        InputHandler textHandler = new InputHandler();

    }

}
