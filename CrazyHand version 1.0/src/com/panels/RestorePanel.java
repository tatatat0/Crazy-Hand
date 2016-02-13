package com.panels;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.text.Format;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import com.Character;
import com.FileIO;
import com.MeleeEdit;
import com.Options;

public class RestorePanel extends JPanel {
	public JButton btn102, btn102All, btn101, btn101All, btn100, btn100All,
			btnPAL, btnPALAll, randoBtn;
	public JCheckBox rawBox,advFSMBox;
	
	public JTextField randomSeed,FSMPointerLocBox,FSMListPointerLocBox;

	public RestorePanel() {
		super();
		Box horizontalBox = Box.createHorizontalBox();
		Box verticalBox = Box.createVerticalBox();
		
		verticalBox.add(Box.createVerticalStrut(15));

		btn102 = new JButton("Restore This Character to v1.02 Defaults");
		btn102.setActionCommand("Restore");
		btn102.addActionListener(new L102());
		verticalBox.add(btn102);
		verticalBox.add(Box.createVerticalStrut(15));

		btn102All = new JButton("Restore ALL Characters to v1.02 Defaults");
		btn102All.setActionCommand("Restore");
		btn102All.addActionListener(new L102All());
		verticalBox.add(btn102All);
		verticalBox.add(Box.createVerticalStrut(15));

		btn101 = new JButton("Restore This Character to v1.01 Defaults");
		btn101.setActionCommand("Restore");
		// 102Btn.addActionListener(new 102BtnListener());
		// this.add(btn101);

		btn101All = new JButton("Restore ALL Characters to v1.01 Defaults");
		btn101All.setActionCommand("Restore");
		// 102Btn.addActionListener(new 102BtnListener());
		// this.add(btn101All);

		btn100 = new JButton("Restore This Character to v1.00 Defaults");
		btn100.setActionCommand("Restore");
		// 102Btn.addActionListener(new 102BtnListener());
		// this.add(btn100);

		btn100All = new JButton("Restore ALL Characters to v1.00 Defaults");
		btn100All.setActionCommand("Restore");
		// 102Btn.addActionListener(new 102BtnListener());
		// this.add(btn100All);

		btnPAL = new JButton("Restore This Character to   PAL Defaults");
		btnPAL.setActionCommand("Restore");
		// 102Btn.addActionListener(new 102BtnListener());
		// this.add(btnPAL);

		btnPALAll = new JButton("Restore ALL Characters to   PAL Defaults");
		btnPALAll.setActionCommand("Restore");
		// 102Btn.addActionListener(new 102BtnListener());
		// this.add(btnPALAll);
		
		LFSM fsml = new LFSM();

		rawBox = new JCheckBox("Use raw data for subactions");
		// rawBox.setMnemonic(KeyEventIdentifier.VK_C);
		rawBox.setSelected(false);
		verticalBox.add(rawBox);
		
		advFSMBox = new JCheckBox("Use default start point for FSM list");
		advFSMBox.setSelected(Options.advancedFsmOpt);
		advFSMBox.setActionCommand("fsmopt");
		advFSMBox.addActionListener(fsml);
		
		//verticalBox.add(advFSMBox);

		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		

		
		randomSeed = new JTextField();
		randomSeed.setEditable(true);
		
		randomSeed.setPreferredSize(new Dimension(100,randomSeed.getPreferredSize().height));
		randomSeed.setMaximumSize(randomSeed.getPreferredSize());
		
		
		FSMPointerLocBox = new JTextField();
		FSMPointerLocBox.setEditable(true);
		
		FSMPointerLocBox.setPreferredSize(new Dimension(100,randomSeed.getPreferredSize().height));
		FSMPointerLocBox.setMaximumSize(randomSeed.getPreferredSize());
		FSMPointerLocBox.setText(Options.advancedFsmLocation);
		FSMPointerLocBox.setActionCommand("fsmtb");
		FSMPointerLocBox.addActionListener(fsml);
		
		FSMListPointerLocBox = new JTextField();
		FSMListPointerLocBox.setEditable(true);
		
		FSMListPointerLocBox.setPreferredSize(new Dimension(100,randomSeed.getPreferredSize().height));
		FSMListPointerLocBox.setMaximumSize(randomSeed.getPreferredSize());
		FSMListPointerLocBox.setText(Options.advancedFsmListLocation);
		FSMListPointerLocBox.setActionCommand("fsmltb");
		FSMListPointerLocBox.addActionListener(fsml);
		
		//verticalBox.add(FSMPointerLocBox);
		//verticalBox.add(FSMListPointerLocBox);
		
		//randomSeed.setMinimumSize(randomSeed.getPreferredSize());
		/*
		openISOBtn = new JButton("Open Another ISO...");
		openISOBtn.addActionListener(new OpenISOAction());
		this.add(openISOBtn);
		*/
		//this.add(Box.createHorizontalGlue());
		
		//randoBtn.add(Box.createHorizontalGlue());
		
		
		verticalBox.add(Box.createVerticalStrut(10));
		horizontalBox.add(verticalBox);
		this.add(horizontalBox);
		//this.add(b);


		

		
		this.add(Box.createVerticalStrut(300));
		this.add(new JLabel("  Hidden message! :O"));

		this.setPreferredSize(new Dimension(700, 300));
	}
	
	
	
	
}

class L102 implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		try {

			File backupCopyFile = new File("def/102/Pl"
					+ Character.characters[MeleeEdit.selected].id + ".dat");

			FileIO.loadedISOFile.reload();

			FileIO.isoFileSystem.replaceFile(
					FileIO.isoFileSystem.getCurrentFile(),
					Files.readAllBytes(backupCopyFile.toPath()));
			FileIO.loadedISOFile.close();

			MeleeEdit.subactionPanel.update();

		} catch (IOException e1) {
			e1.printStackTrace();
		}

		// FileIO.copy("def/102/Pl" +
		// Character.characters[MeleeEdit.selected].id + ".dat","root/Pl" +
		// Character.characters[MeleeEdit.selected].id + ".dat");
		System.out.println("Restore Complete!");
	}
}

class L102All implements ActionListener {
	public void actionPerformed(ActionEvent e) {

		try {
			FileIO.loadedISOFile.reload();
		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
		}

		for (int i = 0; i < Character.characters.length; i++) {

			File backupCopyFile = new File("def/102/Pl"
					+ Character.characters[i].id + ".dat");

			try {

				FileIO.isoFileSystem.replaceFile(
						FileIO.isoFileSystem.getISOFile("Pl"
								+ Character.characters[i].id + ".dat"),
						Files.readAllBytes(backupCopyFile.toPath()));

			} catch (IOException e1) {
				e1.printStackTrace();
			}

		}

		FileIO.loadedISOFile.close();
		MeleeEdit.subactionPanel.update();

		System.out.println("Restore Complete!");
		// FileIO.declareAnims();
	}

	/*
	 * for(int i = 0; i < Character.characters.length; i++)
	 * FileIO.copy("def/102/Pl" + Character.characters[i].id + ".dat","root/Pl"
	 * + Character.characters[i].id + ".dat"); //FileIO.declareAnims();
	 * System.out.println("Restore All Complete!"); } /
	 */
}


class LFSM implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String cmd = arg0.getActionCommand().toLowerCase();
		if(cmd=="fsmopt"){
			Options.advancedFsmOpt=MeleeEdit.restorePane.advFSMBox.isEnabled();
		}
		else if(cmd=="fsmtb"){
			Options.advancedFsmLocation=MeleeEdit.restorePane.FSMPointerLocBox.getText();
		}
		else if(cmd=="fsmltb"){
			Options.advancedFsmListLocation=MeleeEdit.restorePane.FSMListPointerLocBox.getText();
		}
	}
	
}

class OpenISOAction implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		FileIO.loadISOFile();
	}
}


