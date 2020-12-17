package APAHelper;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class TerminalConsole{
    
    String commandList, welcomeString;
    BufferedWriter consoleWriter;
    InputHandler textHandler;
    Boolean ongoing;

    public TerminalConsole(){

        consoleWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        textHandler = new InputHandler(this);
        ongoing = true;

    }

    public void displayCommandList(){

        try{
        consoleWriter.write("Command List:");
        consoleWriter.write("/quoteFormat [] [] []      | To Do: Implement");
        consoleWriter.write("/paperAnalytics [text]     | Analyzes the given text based on a variety of metrics, allowing the below commands to be run");
        consoleWriter.write("       /wordcount          | Gives a wordcount of the supplied paper without counting quotations");
        consoleWriter.write("       /wordcountQuotes    | Gives a wordcount of the supplied paper, quotes included");
        consoleWriter.write("       /text               | Gives a copy of the text with quotes taken out");
        consoleWriter.write("       /quotes             | Gives a list of quotes");
        consoleWriter.write("/quit                      | Exits the program\n");
        consoleWriter.flush();
        }
        catch(IOException E){
            errDisplay("Unexpected Error Printing Command List");
        }

    }

    public void errDisplay(String error){
        System.err.println(error);
    }

    public void display(String text){
        System.out.println(text);
    }

    public void display(String[] text){
    
        try{
            for(int i = 0; i < text.length; i++){
                consoleWriter.write(text[i]);
            }
            consoleWriter.flush();
        }
        catch(IOException E){
            errDisplay("Unexpected Error Printing List");
        }
    }

}
