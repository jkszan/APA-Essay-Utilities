package APAHelper;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.Scanner;

public class TerminalConsole{
    
    String commandList, welcomeString;
    BufferedWriter consoleWriter;
    InputHandler textHandler;
    Boolean ongoing;


    public TerminalConsole(){

        consoleWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        textHandler = new InputHandler();
        ongoing = true;

    }

    private void printWelcomeString(){
        try{
        consoleWriter.write("Hello and welcome to the APAHelper tool, to get information on a paper, please use the paperAnalytics command below\n");
        consoleWriter.flush();
        }
        catch(IOException E){
            System.err.println("Unexpected Error Printing Welcome Message");
        }
    }

    private void displayCommandList(){

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
            System.err.println("Unexpected Error Printing Command List");
        }

    }

    public static void main(String[] args){

        TerminalConsole output = new TerminalConsole();
        Scanner inputReader = new Scanner(System.in);

        output.printWelcomeString();
        while(output.ongoing){

            output.displayCommandList();
            output.textHandler.parse(inputReader.nextLine());

        }

    }



}
