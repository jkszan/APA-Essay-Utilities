package APAHelper;
import javax.swing.*;
import java.awt.*;

public class TerminalConsole extends JFrame{
    
    JTextArea inputText;
    JTextArea outputText;
    JButton submit;

    public TerminalConsole(){

        //consoleWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();
        Color mustard = Color.decode("#FCD757");
        Color fancyOrange = Color.decode("#FC7A57");
        Color niceGrey = Color.decode("#5E5B52");
        Color maroon = Color.decode("#800000");

        setLayout(layout);
        setTitle("APAHelper");
        constraints.gridx = 0;
        constraints.insets = new Insets(10, 20, 10, 20);
        constraints.fill = GridBagConstraints.HORIZONTAL;

        JLabel commandList = new JLabel("<html><body width = '300px'><pre class = 'tab'" + getCommandList() + "</pre></body></html>");
        commandList.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        commandList.setPreferredSize(new Dimension(1000,400));
        commandList.setBackground(niceGrey);
        commandList.setForeground(niceGrey);
        constraints.gridy = 0;
        constraints.weightx = 1;
        constraints.anchor = GridBagConstraints.CENTER;
        add(commandList, constraints);


        JTextArea textArea = new JTextArea("Input", 25, 70);
        JScrollPane input;
        textArea.setLineWrap(true);
        textArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        textArea.setEditable(true);
        textArea.setBackground(mustard);
        textArea.setForeground(maroon);

        input = new JScrollPane(textArea);
        input.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        input.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        input.setPreferredSize(new Dimension(300, 500));
        constraints.gridy = 1;
        constraints.weighty = 0.6;
        constraints.weightx = 0.5;
        constraints.fill = GridBagConstraints.BOTH;
        add(input, constraints);

        submit = new JButton();
        submit.setToolTipText("Submits an operation with the command above");
        submit.setText("Submit");
        submit.setBackground(fancyOrange);
        constraints.gridy = 2;
        constraints.weighty = 0;
        constraints.weightx = 0;
        add(submit, constraints);

        textArea = new JTextArea("Output", 25, 70);
        JScrollPane output;
        textArea.setLineWrap(true);
        textArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        textArea.setEditable(false);
        textArea.setBackground(mustard);
        textArea.setForeground(maroon);

        output = new JScrollPane(textArea);
        output.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        output.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        output.setBackground(niceGrey);
        output.setForeground(niceGrey);

        constraints.gridy = 3;
        constraints.weighty = 0.6;
        constraints.weightx = 0.5;
        add(output, constraints);
        

        inputText = (JTextArea) input.getViewport().getView();
        outputText = (JTextArea) output.getViewport().getView();
        
        setForeground(niceGrey);
        setBackground(niceGrey);
        setSize(1000, 1000);  
        setPreferredSize(getSize());  
        setVisible(true);  
        setDefaultCloseOperation(EXIT_ON_CLOSE); 
        

    }

    public String getCommandList(){

        String commandList = "<br>";
        commandList += "Command List: <br>";
        commandList += "/quoteFormat [] [] []       | To Do: Implement<br>";
        commandList += "/paperAnalytics [text]      | Analyzes the given text based on a variety of metrics, allowing the below commands to be run<br>";
        commandList += "        /wordcount          | Gives a wordcount of the supplied paper without counting quotations<br>";
        commandList += "        /wordcountQuotes    | Gives a wordcount of the supplied paper, quotes included<br>";
        commandList += "        /text               | Gives a copy of the text with quotes taken out<br>";
        commandList += "        /quotes             | Gives a list of quotes<br>";
        commandList += "/quit                       | Exits the program<br>";
        
        return commandList;
    }

    public void errDisplay(String error){
        System.err.println(error);
    }

    public void display(String text){
        System.out.println(text);
    }

    public void display(String[] text){
    /*
        try{
            for(int i = 0; i < text.length; i++){
                consoleWriter.write(text[i]);
            }
            consoleWriter.flush();
        }
        catch(IOException E){
            errDisplay("Unexpected Error Printing List");
        }*/
    }

}
