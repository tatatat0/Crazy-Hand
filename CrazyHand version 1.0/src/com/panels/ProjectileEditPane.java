package com.panels;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import static com.Character.BOWSER_ID;
import static com.Character.FALCON_ID;
import static com.Character.DRMARIO_ID;
import static com.Character.DK_ID;
import static com.Character.FALCO_ID;
import static com.Character.FOX_ID;
import static com.Character.GANON_ID;
import static com.Character.GAMEWATCH_ID;
import static com.Character.GIGABOWSER_ID;
import static com.Character.JIGGLYPUFF_ID;
import static com.Character.KIRBY_ID;
import static com.Character.LINK_ID;
import static com.Character.LUIGI_ID;
import static com.Character.MARIO_ID;
import static com.Character.MARTH_ID;
import static com.Character.MASTERHAND_ID;
import static com.Character.MEWTWO_ID;
import static com.Character.NANA_ID;
import static com.Character.NESS_ID;
import static com.Character.PEACH_ID;
import static com.Character.PICHU_ID;
import static com.Character.PIKACHU_ID;
import static com.Character.POPO_ID;
import static com.Character.ROY_ID;
import static com.Character.SAMUS_ID;
import static com.Character.SANDBAG_ID;
import static com.Character.SHEIK_ID;
import static com.Character.WIREBOY_ID;
import static com.Character.WIREGIRL_ID;
import static com.Character.YOSHI_ID;
import static com.Character.YOUNGLINK_ID;
import static com.Character.ZELDA_ID;
import static com.Character.OTHER_ID;

import com.Character;
import com.scripts.HitboxScript;

public class ProjectileEditPane extends JPanel implements ActionListener {
	
	public ProjectileEditNode[] nodes = {
			//Bowser
			new ProjectileEditNode("Flame Breath", BOWSER_ID, 0x4110),
			
			//Dr. Mario
			new ProjectileEditNode("Pill", DRMARIO_ID, 0x3C08),
			
			//Fox
			new ProjectileEditNode("Laser", FOX_ID, 0x3EDC),
			new ProjectileEditNode("Phantasm(Ground)", FOX_ID, 0x40c4),
			new ProjectileEditNode("Phantasm(Air)", FOX_ID, 0x40e0),
			
			//Falco
			new ProjectileEditNode("Laser", FALCO_ID, 0x3F98),
			new ProjectileEditNode("Phantasm(Ground)", FALCO_ID, 0x4168),
			new ProjectileEditNode("Phantasm(Air)", FALCO_ID, 0x4184),
			//new ProjectileEditNode("Unused?(1)",FALCO_ID, 0x3FFC),
			
			//Game and watch
			new ProjectileEditNode("Sausage", GAMEWATCH_ID, 0x440C),
			
			//Popo
			new ProjectileEditNode("Ice Block", POPO_ID, 0x3B30),
			//new ProjectileEditNode("Blizzard", POPO_ID, 0x3BE8),
			
			//Nana
			new ProjectileEditNode("Ice Block", NANA_ID, 0x1A14),
			//new ProjectileEditNode("Blizzard", NANA_ID, 0x1ACC),
			
			//Kirby
			new ProjectileEditNode("Final Cutter", KIRBY_ID, 0x7B7C),
			
			//Luigi
			new ProjectileEditNode("Fireball", LUIGI_ID, 0x3AA4),
			
			//Link
			new ProjectileEditNode("Arrow", LINK_ID, 0x3FB4),
			new ProjectileEditNode("Bomb impact", LINK_ID, 0x4258),
			new ProjectileEditNode("Bomb explosion", LINK_ID, 0x4184),
			
			//Mario
			new ProjectileEditNode("Fireball", MARIO_ID, 0x3ACC),
			
			//Mewtwo
			new ProjectileEditNode("Shadowball stage 1", MEWTWO_ID, 0x3DD4),
			new ProjectileEditNode("Shadowball stage (2?)", MEWTWO_ID, 0x3E0C),
			new ProjectileEditNode("Shadowball stage (3?)", MEWTWO_ID, 0x3E44),
			new ProjectileEditNode("Shadowball stage (4?)", MEWTWO_ID, 0x3E98),
			new ProjectileEditNode("Disable hitbox", MEWTWO_ID, 0x3D00),
			
			//Ness
			new ProjectileEditNode("PK Flash", NESS_ID,0x3D94),
			new ProjectileEditNode("PK Fire(Pre-impact)", NESS_ID,0x3F3C),
			new ProjectileEditNode("PK Fire(Fire pillar)", NESS_ID,0x3F5C),
			new ProjectileEditNode("PK Thunder (Initial Spawn)", NESS_ID,0x4138),
			new ProjectileEditNode("PK Thunder (In-air)", NESS_ID,0x4138),
			
			//Peach
			new ProjectileEditNode("Turnip?", PEACH_ID, 0x4310),
			
			//Pichu
			new ProjectileEditNode("Thunder Jolt", PICHU_ID,0x3B64),
			new ProjectileEditNode("Lightning Bolt", PICHU_ID,0x3CE8),
			
			//Pikachu
			new ProjectileEditNode("Thunder Jolt", PIKACHU_ID,0x3CA4),
			new ProjectileEditNode("Lightning Bolt", PIKACHU_ID,0x3E28),
			
			//Samus
			new ProjectileEditNode("Charge Shot", SAMUS_ID,0x3ED4),
			new ProjectileEditNode("Homing Missile", SAMUS_ID,0x4070),
			new ProjectileEditNode("Smash Missile", SAMUS_ID,0x408C),
			new ProjectileEditNode("Bomb", SAMUS_ID,0x4170),
			
			//Sheik
			new ProjectileEditNode("Needles", SHEIK_ID,0x39C0),
			
			//Yoshi
			new ProjectileEditNode("Neutral-B grab hitbox 1", YOSHI_ID,0x361C),
			new ProjectileEditNode("Neutral-B grab hitbox 2", YOSHI_ID,0x3630),
			new ProjectileEditNode("Tossed egg", YOSHI_ID,0x3A90),
			new ProjectileEditNode("Down-B star", YOSHI_ID,0x3B74),
			
			//Young Link
			new ProjectileEditNode("Arrow", YOUNGLINK_ID,0x4190),
			new ProjectileEditNode("Bomb impact", YOUNGLINK_ID, 0x4434),
			new ProjectileEditNode("Bomb explosion", YOUNGLINK_ID, 0x445C),
			
			//Zelda
			new ProjectileEditNode("Din's Fire", ZELDA_ID,0x3FE4),
			
			
			//Other
			//new ProjectileEditNode("0xC7 Unowns", OTHER_ID,0xaca8),
			new ProjectileEditNode("0xC8 Lugia's Aeroblast", OTHER_ID,0xacc4),
			new ProjectileEditNode("0xCB Ho-oh's Flame", OTHER_ID,0xacf8),
			new ProjectileEditNode("0xCC Staryu's Star", OTHER_ID,0xad54),
	};
	
		//PlKp.dat @0x4110 - Flame Breath hitbox?
		//
		//PlDr.dat @0x3C08 - Pill hitbox?
		//
		//PlFx.dat @0x3EDC - Laser hitbox?
		//
		//PlFc.dat @0x3F98||0x3FFC - Laser hitbox?
		//
		//PlKb.dat @0x7B7C - Final Cutter hitbox?
		//PlKb.dat @0x2AB02 - Sleep(412 frames) hitbox... What could it be?
		//
		//PlLk.dat @0x3FB4 - Boomerang||Arrow hitbox?
		//PlLk.dat @0x
		//PlLk.dat @0x4258 - Bomb explosion hitbox?
		//PlLk.dat @0x4184 - Bomb impact hitbox?
		//
		//PlLg.dat @0x3AA4 - Fireball hitbox?
		//
		//PlMr.dat @0x3ACC - Fireball hitbox?
		//
		//PlGw.dat @0x440C - Sausage hitbox?
		//
		//PlMt.dat (@0x3DD4||0x3E0C) - Shadowball hitbox?
		//PlMt.dat (@0x3E44||0x3E98) - Shadowball charge hitbox?
		//PlMt.dat @0x3D00 - Disable hitbox?
		//PlMt.dat @0x
		//
		//
		//
	
	
	
	
	
	//Start.dol stuff
	
	//Base item spawn code? @0x26933C(speculated size of 0x3C bytes)
	//7C 08 02 A6 3C 80 80 4A 90 01 00 04 38 A4 0C 64 94 21 FF D0 93 E1 00 2C 3B E0 00 00 93 C1 00 28 93 A1 00 24 93 81 00 20 7C 7C 1B 78 80 85 00 1C 80 05 00 20 7C 04 00 00 40 80 00 14
	//
	
	//0x80275CB4 = pokeball spawn pokemon function?
	//
	
	//Start.dol @0x25D7F4 -- CSS stuff?
	
	
	public JComboBox<HitboxScript> projectilesList;
	
	public int selectedProjectile;
	
	public ProjectileEditPane(){
		super();
		this.setPreferredSize(new Dimension(600, 500));
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		String[] tmp = new String[1];
		ArrayList<String> tmpl = new ArrayList<String>();
		int cnt = 0;
		for(ProjectileEditNode node : nodes){
			tmpl.add(""+cnt);
			cnt++;
		}tmp = tmpl.toArray(tmp);
		
		projectilesList = new JComboBox(tmp);
		projectilesList.setSelectedIndex(0);
		projectilesList.setEditable(false);
		
		ComboBoxRenderer renderer = new ComboBoxRenderer();
		//renderer.setPreferredSize(new Dimension(64,58));
		projectilesList.setRenderer(renderer);
		projectilesList.setPreferredSize(new Dimension(700,70));
		projectilesList.setMaximumSize(projectilesList.getPreferredSize());
		projectilesList.addActionListener(this);
		projectilesList.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		add(projectilesList);
		add(Box.createVerticalStrut(20));
		
		for(ProjectileEditNode node : nodes){
			node.setPreferredSize(getPreferredSize());
			node.setMinimumSize(node.getPreferredSize());
		}
		
	}


	public void save() {
		nodes[selectedProjectile].save();
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		int newsel = ((JComboBox)arg0.getSource()).getSelectedIndex();
		this.remove(nodes[selectedProjectile]);
		selectedProjectile=newsel;
		this.add(nodes[selectedProjectile]);
		nodes[selectedProjectile].updateUI();
	}
	
	
	 class ComboBoxRenderer extends JLabel implements ListCellRenderer {
		
		public ComboBoxRenderer() {
			setOpaque(true);
			setHorizontalAlignment(LEFT);
			setVerticalAlignment(CENTER);
		}
		
		public Component getListCellRendererComponent(
				                    JList list,
				                    Object value,
				                    int index,
				                    boolean isSelected,
				                    boolean cellHasFocus) {
			
			int selectedIndex = Integer.parseInt(((String)value));
			
			if(selectedIndex<0){return this;}
			
			if (isSelected) {
				setBackground(list.getSelectionBackground());
				setForeground(list.getSelectionForeground());
			}
			else {
				setBackground(list.getBackground());
				setForeground(list.getForeground());
			}
			
			
			ImageIcon  icon;
			
			if(nodes[selectedIndex].charId == Character.OTHER_ID){
				icon = Character.otherIcon;
			}
			else{
				icon = Character.characters[nodes[selectedIndex].charId].characterIcon;
			}
		
			if (icon != null && icon.getImage() != null) {
				setFont(list.getFont());
				setIcon(icon);
			}
			setText(nodes[selectedIndex].prname);
			
				return this;
		}
				
	}
	
	
}
