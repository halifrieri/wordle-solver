package wrdlesolver;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HLifrieri
 */
public class WrdleSolver {
    /**
     * @param args the command line arguments
     */
     public static ArrayList<String> words = new ArrayList<String>();
     public static String S1,S2,S3,S4,S5;
     public static String C1,C2,C3,C4,C5;
     public static String[] C = new String[5];
     public static String[] S = new String[5];
    public static void main(String[] args)throws IOException  {
        filler();
        SwingUtilities.invokeLater(() -> {
            try {
                createAndShowGUI();
            } catch (IOException ex) {
                Logger.getLogger(WrdleSolver.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    private static void createAndShowGUI() throws IOException {
        JFrame frame = new JFrame("Letter Color Popup");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(6, 3));

        JLabel[] letterLabels = new JLabel[5];
        JTextField[] letterFields = new JTextField[5];
        JComboBox<String>[] colorComboBoxes = new JComboBox[5];

        for (int i = 0; i < 5; i++) {
            letterLabels[i] = new JLabel("Enter Letter " + (i + 1) + ":");
            letterFields[i] = new JTextField();
            colorComboBoxes[i] = new JComboBox<>(new String[]{"Grey", "Yellow", "Green"});

            frame.add(letterLabels[i]);
            frame.add(letterFields[i]);
            frame.add(colorComboBoxes[i]);
        }

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] letters = new String[5];
                String[] colors = new String[5];

                for (int i = 0; i < 5; i++) {
                    letters[i] = letterFields[i].getText();
                    colors[i] = (String) colorComboBoxes[i].getSelectedItem();
                }

                // Store letters in separate variables
                S[0] = letters[0];
                S[1] = letters[1];
                S[2] = letters[2];
                S[3]= letters[3];
                S[4] = letters[4];

                // Store colors in separate variables
                C[0] = colors[0];
                C[1] = colors[1];
                C[2] = colors[2];
                C[3] = colors[3];
                C[4] = colors[4];
                try {
                    solver();
                } catch (IOException ex) {
                    Logger.getLogger(WrdleSolver.class.getName()).log(Level.SEVERE, null, ex);
                }
                // Display the stored values
                }
        });
       
        frame.add(submitButton);
        frame.setVisible(true);}
         
         public static void filler() throws IOException {
          // Create a URL object for the website
          // Create a URL object for the website
        URL website = new URL("http://bert.stuy.edu/pbrooks/spring2022/materials/nextcs-2/Wordle/sensible_words.txt");
 
        // Get the input stream for the website
        InputStream inputStream = website.openStream();
 
        // Create a buffered reader for the input stream
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
 
        // Read the text from the website
        String line;
       
        while ((line = bufferedReader.readLine()) != null) {words.add(line); }}
            // Check if the word is found in the line
          public static void solver()throws IOException{  
           for (int i=words.size()-1;i>=0;i--){
               
            if ((words.get(i).contains(S[0])==false&&C[0].equals("Yellow"))
              ||(words.get(i).contains(S[1])==false&&C[1].equals("Yellow"))
              ||(words.get(i).contains(S[2])==false&&C[2].equals("Yellow"))
              ||(words.get(i).contains(S[3])==false&&C[3].equals("Yellow"))
              ||(words.get(i).contains(S[4])==false&&C[4].equals("Yellow")))
            {words.remove(i);   }}
           for (int i=words.size()-1;i>=0;i--){
               
            if ((words.get(i).contains(S[0])&&C[0].equals("Grey"))
              ||(words.get(i).contains(S[1])&&C[1].equals("Grey"))
              ||(words.get(i).contains(S[2])&&C[2].equals("Grey"))
              ||(words.get(i).contains(S[3])&&C[3].equals("Grey"))
              ||(words.get(i).contains(S[4])&&C[4].equals("Grey")))
            {words.remove(i);   }}
           
           for (int j=words.size()-1;j>=0;j--){boolean sentinal=true;  
               for (int k=0;k<5;k++){  if(C[k].equals("Green")&&
                    words.get(j).substring(k,k+1).equals(S[k])==false){sentinal=false;}
                                    }
                if(sentinal==false){words.remove(j);  }
                                }
           
             for (int j=words.size()-1;j>=0;j--){boolean sent=true;  
               for (int k=0;k<5;k++){  if(C[k].equals("Yellow")&&
                    words.get(j).substring(k,k+1).equals(S[k])){sent=false;}
                                    }
                if(sent==false){words.remove(j);  }
                                }
           
          System.out.println(words); }}
