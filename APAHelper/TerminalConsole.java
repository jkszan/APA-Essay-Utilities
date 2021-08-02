package APAHelper;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TerminalConsole extends JFrame{
    
    InputHandler handler;   //Reference to active InputHandler instance
    JTextArea inputText;    //The input text field
    JTextArea outputText;   //The output text field

    public TerminalConsole(InputHandler handler){

        //Creating a link back to the InputHandler instance that created this
        this.handler = handler;

        //Defining a handful of colors that we will use in our GUI using their Hex representations
        Color softBlue = Color.decode("#93CBDC");       //Blue
        Color pink = Color.decode("#FACBCB");           //Pink
        Color murkyPurple = Color.decode("#CAC0D8");    //Purple

        //Creating the necessary variables to initialize and control a GridBagLayout
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();

        //Creating a JPanel which will serve as the backboard of our layout, and setting it's layout accordingly
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(layout);
        mainPanel.setBackground(softBlue); //Setting the Panel's background color to blue
        
        //Setting some constant values for our layout
        constraints.gridx = 0;                              //Since we only plan to have a single column in our layout, constraints.gridx will always be 0
        constraints.insets = new Insets(10, 20, 10, 20);    //A basic insets value to make sure components never touch
        

        //Creating and adding our JLabel
        JLabel commandList = new JLabel("<html><body width = '300px'><pre class = 'tab'" + getCommandList() + "</pre></body></html>");  //Formatting String returned by getCommandList() using some HTML code
        constraints.gridy = 0;                              //Placing the JLabel in the uppermost slot
        constraints.fill = GridBagConstraints.HORIZONTAL;   //Setting JLabel to only fill horizontally
        mainPanel.add(commandList, constraints);            //Adding the JLabel to our JPanel


        //Creating our input text field
        JTextArea textArea = new JTextArea("Input Commands Here", 25, 70);  //Creating the JTextArea object 
        textArea.setLineWrap(true);     //Enabling line-wrap on our text-field
        textArea.setEditable(true);     //Setting the text field to be editable
        textArea.setBackground(pink);   //Setting a background color of pink for our text field
        textArea.setToolTipText("Enter Commands Here and Click Submit to Execute"); //Setting tooltip text

        //Creating and adding a JScrollPane that will take our input text-field as a viewport. This is done to accommodate papers of a very large size
        JScrollPane input = new JScrollPane(textArea);

        //Making it so there is always a vertical scrollbar and practically never a horizontal scrollbar due to linewrap being enabled
        input.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        input.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        //Setting layout constraints and adding the input JScrollPane to the JPanel
        constraints.gridy = 1;          //Making this the second highest component
        constraints.weighty = 0.6;      //Making this consume a proportionally large amount of extra vertical space
        constraints.weightx = 0.5;      //Making this consome a proportionally large amount of extra horizontal space
        constraints.fill = GridBagConstraints.BOTH;     //Setting this to fill both horizontally and vertically
        mainPanel.add(input, constraints);              //Adding the JScrollPane to the JPanel


        //Creating our output text field
        textArea = new JTextArea("Output", 25, 70); //Creating the JTextArea object 
        textArea.setLineWrap(true);     //Enabling line-wrap on our text-field
        textArea.setEditable(false);    //Setting the text field to be editable
        textArea.setBackground(pink);   //Setting a background color of pink for our text field

        //Creating and adding a JScrollPane that will take our input text-field as a viewport. This is done to accommodate outputs of a very large size
        JScrollPane output = new JScrollPane(textArea);

        //Making it so there is always a vertical scrollbar and practically never a horizontal scrollbar due to linewrap being enabled
        output.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        output.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        //Since most of our constraints are the same as the input text field above, this block only changes the vertical location and adds the JScrollPane to the JPanel
        constraints.gridy = 3;
        mainPanel.add(output, constraints);


        //Creating the JButton, which is connected to the instance variable submit
        JButton submit = new JButton();
        submit.setToolTipText("Submits an operation with the command above"); //Setting tooltip text 
        submit.setText("Submit");           //Setting button text
        submit.setBackground(murkyPurple);  //Setting button color
        submit.addActionListener(new ActionListener()
        {
        public void actionPerformed(ActionEvent e)
            {
                submitPressed();
            }
        });

        //Setting Layout Constraints and adding the JButton
        constraints.gridy = 2;              //Making this the third highest component
        constraints.weighty = 0;            //Setting to not take up extra space verically
        mainPanel.add(submit, constraints); //Adding the JButton to the JPanel
  

        //Initializing instance variables for both the input and output textfields for use in our other methods
        inputText = (JTextArea) input.getViewport().getView();
        outputText = (JTextArea) output.getViewport().getView();

        
        
        //Adding our formatted JPanel to our TerminalConsole instance (TerminalConsole being an extension of JFrame) and doing some final settings
        add(mainPanel);                          //Adding JPanel
        setTitle("APAHelper");                   //Setting window title to APAHelper
        setSize(1000, 1000);                     //Setting Default size
        setVisible(true);                        //Setting this (and all the components) to visible
        setDefaultCloseOperation(EXIT_ON_CLOSE); //Setting the default close operation
        

    }

    //Returns a HTML formatted command list string
    public String getCommandList(){

        String commandList = "<br>";
        commandList += "Command List: <br>";
        commandList += "/paperAnalytics [text]      | Analyzes the given text based on a variety of metrics, allowing the below commands to be run<br>";
        commandList += "        /wordcount          | Gives a wordcount of the supplied paper without counting quotations<br>";
        commandList += "        /wordcountQuotes    | Gives a wordcount of the supplied paper, quotes included<br>";
        commandList += "        /text               | Gives a copy of the text with quotes taken out<br>";
        commandList += "        /quotes             | Gives a list of quotes<br>";
        commandList += "/quit                       | Exits the program<br>";
        
        return commandList;
    }

    //Responds to the action of the submit button being pressed
    private void submitPressed(){
        handler.parse(inputText.getText().strip());
        inputText.setText("");
    }

    //Displays error text
    public void errDisplay(String error){
        outputText.setText("Error: " + error);
    }

    //Displays text
    public void display(String text){
        outputText.setText(text);
    }

    //Formats and displays an array of Strings
    public void display(String[] text){

        String listText ="";

        for(int i = 0; i < text.length; i++){
            listText += text[i] + "\n";
        }
        outputText.setText(listText);
    }

    //Terminates the program
    public void quit(){
        setVisible(false);
        dispose();
    }

}
