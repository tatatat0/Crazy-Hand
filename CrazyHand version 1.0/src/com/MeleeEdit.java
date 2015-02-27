package com;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;




import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class MeleeEdit extends JPanel implements ActionListener {
    

	
	

	public static final int MENU_ATTRIBUTES=0,MENU_ATTACKS=1,MENU_SPECIAL_MOVES=2,MENU_ALL=3,MENU_OTHER=4;
	
	
	

    public static int selected=0, selectedSubaction=0,selectedMenu=0;
    
    
    
    
    
    
    
    public static String[] options = {
    	"Attributes",
    	"Subactions (Attacks only)",
    	"Subactions (Special moves)",
    	"Subactions (All)",
    	"Other",
    	//"Special Moves",
    	//"Frames Speed Modifiers",
    	//
    };

    public static JFrame frame;
    public JButton saveButton;
    public static JTable attributeTable;
    public JScrollPane aPane,scripts;
    public JComboBox charList,subactionList,subactionList2,specialList;
    public JComboBox optionList;
    public JPanel comboPane,scriptPanel,restorePane,specialPanel;







	public static JPanel scriptInner;

    public MeleeEdit() {
        super(new BorderLayout());
        
        
        String[] tmp = new String[Character.characters.length];
        for(int i = 0; i < tmp.length; i++){
        	tmp[i]=Character.characters[i].name;
        }
        
        String[] tmp2 = new String[SubAction.subActions.length];
        for(int i = 0; i < tmp2.length; i++){
        	tmp2[i]=SubAction.subActions[i].name;
        }
        String[] tmp3 = new String[SubAction.subActionsAll.length];
        for(int i = 0; i < tmp3.length; i++){
        	tmp3[i]=SubAction.subActionsAll[i].name;
        }
        
        charList = new JComboBox(tmp);
        charList.setSelectedIndex(0);
        charList.addActionListener(new CharListener());
        charList.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        
        optionList = new JComboBox(options);
        optionList.setSelectedIndex(0);
        optionList.addActionListener(new OptionListener());
        optionList.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        
        subactionList = new JComboBox(tmp2);
        subactionList.setSelectedIndex(0);
        subactionList.addActionListener(new SubactionListener());
        subactionList.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        
        subactionList2 = new JComboBox(tmp3);
        subactionList2.setSelectedIndex(0);
        subactionList2.addActionListener(new SubactionListener());
        subactionList2.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        
        comboPane = new JPanel();
        comboPane.setLayout(new BoxLayout(comboPane, BoxLayout.LINE_AXIS));
        comboPane.add(charList);
        comboPane.add(Box.createHorizontalStrut(5));
        comboPane.add(optionList);
        comboPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        
        saveButton = new JButton("save");
        saveButton.setActionCommand("save");
        saveButton.addActionListener(new SaveListener());
        
        
 
        attributeTable = new JTable(new AttributeTable());
        //attributeTable.setPreferredScrollableViewportSize(new Dimension(700, 600));
        attributeTable.setFillsViewportHeight(true);
        attributeTable.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        
        

       scriptInner = new JPanel();
       scriptInner.setLayout(new BoxLayout(scriptInner, BoxLayout.PAGE_AXIS));
       
       FileIO.readScripts();
       
       
       
       //j.setPreferredSize(new Dimension(300,400);

       
        
 
        //Create the scroll pane and add the table to it.
        aPane = new JScrollPane(attributeTable);
        aPane.setPreferredSize(new Dimension(700,500));
        
        restorePane = new RestorePanel();
        
        scripts = new JScrollPane(scriptInner);
        scripts.setPreferredSize(new Dimension(700,600));
        scripts.getVerticalScrollBar().setUnitIncrement(10);
        
        
        
        scriptPanel = new JPanel();
        scriptPanel.setLayout(new BoxLayout(scriptPanel, BoxLayout.PAGE_AXIS));
        
        scriptPanel.add(subactionList);
        scriptPanel.add(scripts);



        specialPanel = new JPanel();
        
        add(comboPane, BorderLayout.PAGE_START);
        add(aPane, BorderLayout.CENTER);
        add(saveButton,BorderLayout.PAGE_END);
        
        
        
        
    }
    
    
    public void refreshSpecialMoves()
    {
    	String[] tmp3 = new String[SpecialMovesList.getListForCharacter(MeleeEdit.selected).length];
        for(int i = 0; i < tmp3.length; i++){
        	tmp3[i]=SpecialMovesList.getListForCharacter(MeleeEdit.selected)[i].getLocalizedName();
        }
        
        specialList = new JComboBox(tmp3);
        specialList.setSelectedIndex(0);
        specialList.addActionListener(new SubactionListener());
        specialList.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        
        
        
        specialPanel.setLayout(new BoxLayout(specialPanel, BoxLayout.PAGE_AXIS));
        specialPanel.removeAll();
    	specialPanel.add(specialList);
        specialPanel.add(scripts);
    }
    
    

    class SaveListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	FileIO.save();
        	System.out.println("Save Complete!");
        	
        	
        	for(Script script: Script.scripts){
        		script.save();
        	}
        	
        	
        	
        	
        }
    }
    class CharListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	JComboBox cb = (JComboBox)e.getSource();
            selected = cb.getSelectedIndex();
            
            
            FileIO.init();
            FileIO.readScripts();
            FileIO.setPosition(Character.characters[MeleeEdit.selected].offset);
            for(int i = 0; i < Attribute.attributes.length; i ++){
        		attributeTable.setValueAt(FileIO.readFloat(), i, 1);
        	}
            System.out.println("Character Selection Updated");
        }
    }
    class OptionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	JComboBox cb = (JComboBox)e.getSource();
            selectedMenu = cb.getSelectedIndex();
            
            removeAll();
            add(comboPane, BorderLayout.PAGE_START);
            add(saveButton,BorderLayout.PAGE_END);
            
            if(selectedMenu==MENU_ATTRIBUTES){

            	add(aPane, BorderLayout.CENTER);
            	//comboPane.remove(subactionList);
            }
            if(selectedMenu==MENU_ATTACKS){
            	scriptPanel.remove(subactionList2);
                scriptPanel.remove(scripts);
                scriptPanel.remove(specialPanel);
                scriptPanel.add(subactionList);
                scriptPanel.add(scripts);
            	

            	add(scriptPanel, BorderLayout.CENTER);
            	//comboPane.add(subactionList);
            }
            if(selectedMenu==MENU_SPECIAL_MOVES){
            	scriptPanel.remove(subactionList);
                scriptPanel.remove(scripts);
                scriptPanel.remove(subactionList2);
                
                refreshSpecialMoves();
                
                scriptPanel.add(specialPanel);
                scriptPanel.add(scripts);
                

            	add(scriptPanel, BorderLayout.CENTER);
            	//comboPane.add(subactionList);
            }
            if(selectedMenu==MENU_ALL){
            	
            	scriptPanel.remove(subactionList);
                scriptPanel.remove(scripts);
                scriptPanel.remove(specialPanel);
                scriptPanel.add(subactionList2);
                scriptPanel.add(scripts);
                

            	add(scriptPanel, BorderLayout.CENTER);
            	//comboPane.add(subactionList);
            }
            if(selectedMenu==MENU_OTHER){
            	remove(saveButton);
            	add(restorePane, BorderLayout.CENTER);
            }
            
            
            
            frame.pack();
            System.out.println("Option Selection Updated");
        }
    }
    public class SubactionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	JComboBox cb = (JComboBox)e.getSource();

        	selectedSubaction = cb.getSelectedIndex();
            
            FileIO.readScripts();
        }
    }
    

    
 
    class AttributeTable extends AbstractTableModel {
        public String[] columnNames = {"Attribute","Value","Info", };
        public Object[][] data = initGrid();
        public Object[][] initGrid(){
        	FileIO.setPosition(Character.characters[MeleeEdit.selected].offset);
        	Object[][] tmp = new Object[Attribute.attributes.length][3];
        	for(int i = 0; i < Attribute.attributes.length; i ++){
        		tmp[i][0] = Attribute.attributes[i].name;
        		tmp[i][1] = FileIO.readFloat();
        		if(Attribute.attributes[i].name.equals("????"))
        			tmp[i][2] = "Don't modify.";
        		else
        			tmp[i][2] = Attribute.attributes[i].info;
        	}
        	return tmp;
        } 
        public int getColumnCount() {	
            return columnNames.length;
        }
        public int getRowCount() {
            return data.length;
        }
        public String getColumnName(int col) {
            return columnNames[col];
        }
        public Object getValueAt(int row, int col) {
            return data[row][col];
        }
        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }
        public boolean isCellEditable(int row, int col) {
            if(col==1)
            	return true;
            else
            	return false;   
        }  
        public void setValueAt(Object value, int row, int col) {
        	if(value==null)
        		return;
            data[row][col] = value;
            fireTableCellUpdated(row, col);
        }
    }

 
    public static void main(String[] args) throws IOException {
    	
    	FileIO.init();
    	
    	SpecialMovesList.load();
    	
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	//Create and set up the window.
                frame = new JFrame("Crazy Hand v1.0");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                ImageIcon img = new ImageIcon("img/hand.png");
                frame.setIconImage(img.getImage());
         
                //Create and set up the content pane.
                MeleeEdit contentPane = new MeleeEdit();
                
                //
                //
                
                contentPane.setOpaque(true);
                frame.setContentPane(contentPane);

                //Display the window.
                frame.pack();
                frame.setVisible(true);
                
                
            }
        });
    }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		//not used, but required
		
		
	}
	
	public static void setScripts(){
		int i = -1;
	       for(Script script: Script.scripts){
	    	   i++;
	    	   
	    	   
	    	   scriptInner.add(Script.scripts.get(i));
	    	   
	    	   byte[] tempData = {
	    			   4,53,6
	    	   };
	    	   //j.add(new HitboxScript(tempData));


	    	   JSeparator sep = new JSeparator(SwingConstants.HORIZONTAL);
	    	   sep.setBackground(Color.BLUE);
	    	   scriptInner.add(sep);
	    	   //JPanel box = Box.createVerticalStrut(10);
	    	   scriptInner.add(Box.createVerticalStrut(30));
	    	   
	    	   sep = new JSeparator(SwingConstants.HORIZONTAL);
	           sep.setBackground(Color.BLUE);
	    	   scriptInner.add(sep);
	    	   //j.add(Box.createVerticalStrut(5));

	       }
	       frame.pack();
	}
}