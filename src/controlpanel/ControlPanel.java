
package controlpanel;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.*;
import java.awt.event.*;  
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ControlPanel {
    
    
    static boolean fileFound;
    static String fileName;
    
    static JFrame frame;
    
    static JTextField location,searchfile;
    static JTextArea results;
    
    public static void main(String[] args) {
        //System.out.println("Hi Emon");
        
        frame=new JFrame("Control Panel"); 
        frame.setSize(800,800);  
        //f.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new FlowLayout());
        
        JLabel controlPanel= new JLabel("                                         Control Panel                                                         ");

        JButton shutdownButton=new JButton("Shut Down");  
        shutdownButton.setBounds(50,100,100,50);  
        
        JButton restartButton=new JButton("Restart");  
        restartButton.setBounds(50,100,100,50); 
        
        
        JButton cancelButton=new JButton("Cancel");  
        cancelButton.setBounds(50,100,100,50);
        
        JLabel drivelocationtext= new JLabel("                                     Enter Drive path........                                                   ");
       
        location=new JTextField();
        location.setPreferredSize( new Dimension( 700, 40 ) );
        
        JLabel searchfieldtext= new JLabel("                                Enter the File Name.....                                                    ");
        
        searchfile=new JTextField();
        searchfile.setPreferredSize( new Dimension( 700, 40 ) );
        
        JLabel Empty= new JLabel("                                                 ");
        JLabel Empty1= new JLabel("                                                ");
        
        JButton searchButton=new JButton("Search"); 
        searchButton.setBounds(50,100,100,50);
        JButton searchWithoutExtension=new JButton("Search without Extension");
        searchWithoutExtension.setBounds(50,100,100,50);
        JButton reset=new JButton("Reset");
        
        
        
        JLabel searchResult= new JLabel("                                                       Search result will be in Here......                                                       ");
        
        results=new JTextArea(10,60);
        results.setEditable(false);
     
        frame.add(controlPanel);
        frame.add(shutdownButton);

        frame.add(restartButton);

        frame.add(cancelButton);

        frame.add(drivelocationtext);
        frame.add(location);
        frame.add(searchfieldtext);
        frame.add(searchfile);
        frame.add(Empty);
        frame.add(searchButton);
        frame.add(searchWithoutExtension);
        frame.add(reset);
        frame.add(Empty1);
        frame.add(searchResult);
        frame.add(results);
        
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true); 
        
        
        shutdownButton.addActionListener(new ActionListener(){  
        @Override
        public void actionPerformed(ActionEvent e){  
            try {
                Runtime.getRuntime().exec("shutdown -s -t 12");
            } catch (IOException ex) {
                
            }
        }  
        });  
        
        restartButton.addActionListener(new ActionListener(){  
        @Override
        public void actionPerformed(ActionEvent e){  
            try {
                Runtime.getRuntime().exec("shutdown -r -t 12");
            } catch (IOException ex) {
                
            }
        }  
        });  
        
        
        cancelButton.addActionListener(new ActionListener(){  
        @Override
        public void actionPerformed(ActionEvent e){  
            try {
                Runtime.getRuntime().exec("shutdown -a");
            } catch (IOException ex) {
                
            }
        }  
        });
        
        
        searchButton.addActionListener(new ActionListener(){  
        @Override
        public void actionPerformed(ActionEvent e){  
            File loc=new File(location.getText());
            fileName=searchfile.getText();
            searchFile(loc);
            
            
        }  
        });
        
        reset.addActionListener(new ActionListener(){  
        @Override
        public void actionPerformed(ActionEvent e){  
            location.setText("");
            searchfile.setText("");
            results.setText("");
            fileName="";
            fileFound=false;
            
        }  
        });
        /*searchWithoutExtension.addActionListener(new ActionListener(){  
        @Override
        public void actionPerformed(ActionEvent e){  
            File loc=new File(location.getText());
            fileName=searchfile.getText();
            searchFilewithoutExt(loc);
            
            
        }  
        });*/
        
    }
    
    public static void searchFile(File f)
    {  
       try
       {
           if((f.getName().equalsIgnoreCase(fileName) ||  f.getName().toLowerCase().startsWith(fileName.toLowerCase()))||(f.getName().toLowerCase().endsWith(fileName.toLowerCase())))
                {    
                    //System.out.println("file found " + f.getAbsolutePath()); 
                    fileFound=true;
                    results.setText(f.getAbsolutePath());
                }
           else if(f.isDirectory())
            {
                File [] fi = f.listFiles();
                for(int i=0;i<fi.length;i++)
                {
                    if(fileFound==true) 
                    {
                        break;
                    }  
                    //System.out.println(fi[i].getName());
                    searchFile(fi[i]);
                }
            }
            else
            {
                results.setText("File Not found");
                
            }
       }
        catch(Exception e)
        {
            
        }
     }
    /*public static void searchFilewithoutExt(File f)
    {  
       String[] paths=new String[500];
       int j=0;
       try
       {
           //System.out.println(f);
           if(f.isDirectory())
            {
                File [] fi = f.listFiles();
                for(int i=0;i<fi.length;i++)
                { 
                    //System.out.println(fi[i].getName());
                    searchFilewithoutExt(fi[i]);
                }
            }
           else if(f.toString().contains(fileName)){
               paths[j]=f.toString();
               //System.out.println(f);
               j++;
           }

       }
        catch(Exception e)
        {
            
        }
       for(int k=0; k<paths.length; k++){
           System.out.println(paths[k]);
       }
     }*/

    
}
