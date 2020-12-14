package APAHelper;

public class InputHandler {
    
    CommandHandler commandImplementation;

    public InputHandler(){
        commandImplementation = new CommandHandler();
    }

    public void parse(String inputText){
        String[] commandList = inputText.split(" ", 2);
        commandType command; 

        switch(commandList[0]){
            case "/paperAnalytics":
                command = commandType.PAPERANALYTICS;
                commandImplementation.paperAnalytics(inputText);
                break;
            case "/quoteFormat":
                command = commandType.QUOTEFORMAT;
                commandImplementation.quoteFormat(inputText);
                break;
            case "/wordcount":
                command = commandType.WORDCOUNT;
                commandImplementation.wordCount();
                break;
            case "/wordcountQuotes":
                command = commandType.WORDCOUNTQUOTES;
                commandImplementation.wordCountQuotes();
                break;
            case "/text":
                command = commandType.TEXT;
                commandImplementation.text();
                break;
            case "/quotes":
                command = commandType.QUOTES;
                commandImplementation.quotes();
                break;
            case "/quit":
                command = commandType.QUIT();
                commandImplementation.quit();
                System.exit(0);
                break;
            default:
                
        }


    }

    public enum commandType{
        PAPERANALYTICS,
        QUOTEFORMAT,
        WORDCOUNT,
        WORDCOUNTQUOTES,
        TEXT,
        QUOTES,
        QUIT
    }

}
