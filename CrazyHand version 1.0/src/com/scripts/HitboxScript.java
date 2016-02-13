package com.scripts;

import java.awt.Color;
import java.awt.Dimension;


import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import com.FileIO;
import com.HitboxEffect;


public class HitboxScript extends Script {
    	
	
    	JFormattedTextField baseKnockback ,
    			knockbackGrowth ,
    			weightKnockback ,
    			damage ,
    			shieldDamage ,
    			size ,
    			angle ,
    			zOff ,
    			yOff ,
    			xOff ,
    			sound ,
    			hurtboxInteraction ,
    			boneAttachment ,
    			hitboxId ,
    			hitboxInteraction ;
    	
    	public JComboBox<String> Attribute;
    	
        public HitboxScript(String n, int[] d, int l){
        	super(n,d,l);

        			baseKnockback = new JFormattedTextField(0);
        			knockbackGrowth = new JFormattedTextField(0);
        			weightKnockback = new JFormattedTextField(0);
        			damage = new JFormattedTextField(0);
        			shieldDamage = new JFormattedTextField(0);
        			size = new JFormattedTextField(0);
        			angle = new JFormattedTextField(0);
        			zOff = new JFormattedTextField(0);
        			yOff = new JFormattedTextField(0);
        			xOff = new JFormattedTextField(0);
        			sound = new JFormattedTextField(0);
        			hurtboxInteraction = new JFormattedTextField(0);
        			boneAttachment = new JFormattedTextField(0);
        			hitboxId = new JFormattedTextField(0);
        			hitboxInteraction = new JFormattedTextField(0);
        	
        	this.removeAll();
        	hexField=false;
        	init(n,d,l);
        	
        	String[] tmp = new String[5];
            for(int i = 0; i < 5; i++){
            	tmp[i]="SFX-" + i;
            }
            String[] tmp2 = new String[HitboxEffect.hitboxEffect.length];
            for(int i = 0; i < tmp2.length; i++){
            	tmp2[i]=HitboxEffect.hitboxEffect[i].name;
            }
            String[] tmp3 = new String[5];
            for(int i = 0; i < 5; i++){
            	tmp3[i]="Option " + (i+1);
            }
            
            

            
            //int t = data[3];
            //t=bit(t,8,getBit(data[2],0)); 
            //int t = this.setBits(23,31);
            damage.setValue(this.setBits(23,31));
            
            
            //t=data[5];
            //for(int i = 0 ; i < 8; i++){
            //	t=bit(t,8+i,getBit(data[4],i));
            //}
            size.setValue(this.setBits(32,47));
            
            
            int t = this.setBits(48,63);
            if(getBit(t,15)==1){
            	t-=65536;
            }
            zOff.setValue(t);
            
            t = this.setBits(64,79);
            if(getBit(t,15)==1){
            	t-=65536;
            }
            yOff.setValue(t);
            
            t = this.setBits(80,95);
            if(getBit(t,15)==1){
            	t-=65536;
            }
            xOff.setValue(t);
            
            angle.setValue(this.setBits(96,104));
            knockbackGrowth.setValue(this.setBits(105,113));
            weightKnockback.setValue(this.setBits(114,122));
            boneAttachment.setValue(this.setBits(14,20));
            baseKnockback.setValue(this.setBits(128,136));
            shieldDamage.setValue(this.setBits(143,149));
            sound.setValue(this.setBits(150,157));
            hurtboxInteraction.setValue(this.setBits(158,159));
            

            hitboxInteraction.setValue(this.setBits(126,127));
        	hitboxId.setValue(this.setBits(6,8));
            
        	//System.out.println(this.setBits(121, 124));
            
        	int elem = this.setBits(137,143);
            elem= bit(elem,0,0);
            elem= bit(elem,1,0);

            Attribute = new JComboBox<String>(tmp2);
            Attribute.setSelectedIndex(HitboxEffect.getSelect(elem));

            
            

            
            
            
            JPanel tempPanel= new JPanel();
            tempPanel.setLayout(new BoxLayout(tempPanel, BoxLayout.LINE_AXIS));
            
            //damage.getValue()
            tempPanel.add(new JLabel("Base Knockback: "));
            tempPanel.add(baseKnockback);
            tempPanel.add(Box.createHorizontalStrut(5));
            tempPanel.add(new JSeparator(SwingConstants.VERTICAL));
            tempPanel.add(Box.createHorizontalStrut(5));
            
            tempPanel.add(new JLabel("Knockback Growth: "));
            tempPanel.add(knockbackGrowth);
            tempPanel.add(Box.createHorizontalStrut(5));
            tempPanel.add(new JSeparator(SwingConstants.VERTICAL));
            tempPanel.add(Box.createHorizontalStrut(5));
            
            tempPanel.add(new JLabel("WDSK: "));
            tempPanel.add(weightKnockback);
            tempPanel.add(Box.createHorizontalStrut(5));
            tempPanel.add(new JSeparator(SwingConstants.VERTICAL));
            tempPanel.add(Box.createHorizontalStrut(5));
            
            tempPanel.add(new JLabel("Damage: "));
            tempPanel.add(damage);
            tempPanel.add(Box.createHorizontalStrut(5));
            tempPanel.add(new JSeparator(SwingConstants.VERTICAL));
            tempPanel.add(Box.createHorizontalStrut(5));
            
            tempPanel.add(new JLabel("Shield Damage: "));
            tempPanel.add(shieldDamage);
            tempPanel.add(Box.createHorizontalStrut(5));
            tempPanel.add(new JSeparator(SwingConstants.VERTICAL));
            tempPanel.add(Box.createHorizontalStrut(5));

            
            this.add(Box.createVerticalStrut(5));
            this.add(tempPanel);
            this.add(Box.createVerticalStrut(5));
            tempPanel= new JPanel();
            tempPanel.setLayout(new BoxLayout(tempPanel, BoxLayout.LINE_AXIS));
            
            tempPanel.add(new JLabel("Size: "));
            tempPanel.add(size);
            tempPanel.add(Box.createHorizontalStrut(5));
            tempPanel.add(new JSeparator(SwingConstants.VERTICAL));
            tempPanel.add(Box.createHorizontalStrut(5));
            
            tempPanel.add(new JLabel("Angle: "));
            tempPanel.add(angle);
            tempPanel.add(Box.createHorizontalStrut(5));
            tempPanel.add(new JSeparator(SwingConstants.VERTICAL));
            tempPanel.add(Box.createHorizontalStrut(5));
            
            tempPanel.add(new JLabel("X-Offset: "));
            tempPanel.add(xOff);
            tempPanel.add(Box.createHorizontalStrut(5));
            tempPanel.add(new JSeparator(SwingConstants.VERTICAL));
            tempPanel.add(Box.createHorizontalStrut(5));
            
            tempPanel.add(new JLabel("Y-Offset: "));
            tempPanel.add(yOff);
            tempPanel.add(Box.createHorizontalStrut(5));
            tempPanel.add(new JSeparator(SwingConstants.VERTICAL));
            tempPanel.add(Box.createHorizontalStrut(5));
            
            tempPanel.add(new JLabel("Z-Offset: "));
            tempPanel.add(zOff);
            tempPanel.add(Box.createHorizontalStrut(5));
            tempPanel.add(new JSeparator(SwingConstants.VERTICAL));
            tempPanel.add(Box.createHorizontalStrut(5));
            
            
            this.add(Box.createVerticalStrut(5));
            this.add(tempPanel);
            this.add(Box.createVerticalStrut(5));
            tempPanel= new JPanel();
            tempPanel.setLayout(new BoxLayout(tempPanel, BoxLayout.LINE_AXIS));
            
            tempPanel.add(new JLabel("Attribute: "));
            tempPanel.add(Attribute);
            tempPanel.add(Box.createHorizontalStrut(5));
            tempPanel.add(new JSeparator(SwingConstants.VERTICAL));
            tempPanel.add(Box.createHorizontalStrut(5));
            
            tempPanel.add(new JLabel("Bone: "));
            tempPanel.add(boneAttachment);
            tempPanel.add(Box.createHorizontalStrut(5));
            tempPanel.add(new JSeparator(SwingConstants.VERTICAL));
            tempPanel.add(Box.createHorizontalStrut(5));
            
            
            
            tempPanel.add(new JLabel("Sound FX: "));
            tempPanel.add(sound);
            tempPanel.add(Box.createHorizontalStrut(5));
            tempPanel.add(new JSeparator(SwingConstants.VERTICAL));
            tempPanel.add(Box.createHorizontalStrut(5));
            
            this.add(Box.createVerticalStrut(5));
            this.add(tempPanel);
            this.add(Box.createVerticalStrut(5));
            
            tempPanel= new JPanel();
            tempPanel.setLayout(new BoxLayout(tempPanel, BoxLayout.LINE_AXIS));
            
            
            
            tempPanel.add(new JLabel("Hurtbox Interaction: "));
            tempPanel.add(hurtboxInteraction);
            tempPanel.add(Box.createHorizontalStrut(5));
            tempPanel.add(new JSeparator(SwingConstants.VERTICAL));
            tempPanel.add(Box.createHorizontalStrut(5));
            
           
            
            
            tempPanel.add(new JLabel("Hitbox Interaction: "));
            tempPanel.add(hitboxInteraction);
            tempPanel.add(Box.createHorizontalStrut(5));
            tempPanel.add(new JSeparator(SwingConstants.VERTICAL));
            tempPanel.add(Box.createHorizontalStrut(5));
            
            
            //hitboxId.setPreferredSize(new Dimension(25,hitboxId.getPreferredSize().height));
            //hitboxId.setMaximumSize(hitboxId.getPreferredSize());
            //hitboxId.setMinimumSize(hitboxId.getPreferredSize());
            tempPanel.add(new JLabel("Hitbox ID: "));
            tempPanel.add(hitboxId);
            tempPanel.add(Box.createHorizontalStrut(5));
            tempPanel.add(new JSeparator(SwingConstants.VERTICAL));
            tempPanel.add(Box.createHorizontalStrut(5));
            
            
            this.add(Box.createVerticalStrut(5));
            this.add(tempPanel);
            this.add(Box.createVerticalStrut(5));
            
            
            
            
            
            
            this.setBackground(Color.WHITE);
            this.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
            
        }
        
        
        
        public void updateData(){

        	
        	this.saveBits(23,31,((Number)damage.getValue()).intValue());
        	this.saveBits(32,47,((Number)size.getValue()).intValue());
        	this.saveBits(6, 8, ((Number)hitboxId.getValue()).intValue());
        	
            int tmp = ((Number)zOff.getValue()).intValue();
            if(getBit(tmp,15)==1){
            	tmp+=65536;
            }
            this.saveBits(48,63,tmp);
            
            tmp = ((Number)yOff.getValue()).intValue();
            if(getBit(tmp,15)==1){
            	tmp+=65536;
            }
            this.saveBits(64,79,tmp);
            
            tmp = ((Number)xOff.getValue()).intValue();
            if(getBit(tmp,15)==1){
            	tmp+=65536;
            }
            this.saveBits(80,95,tmp);
            
        	this.saveBits(125,127,((Number)hitboxInteraction.getValue()).intValue());
        	
            this.saveBits(96,104,((Number)angle.getValue()).intValue());
            this.saveBits(105,113,((Number)knockbackGrowth.getValue()).intValue());
            this.saveBits(114,122,((Number)weightKnockback.getValue()).intValue());
            this.saveBits(14,20,((Number)boneAttachment.getValue()).intValue());
            this.saveBits(128,136,((Number)baseKnockback.getValue()).intValue());
            this.saveBits(143,149,((Number)shieldDamage.getValue()).intValue());
            this.saveBits(150,157,((Number)sound.getValue()).intValue());
            this.saveBits(158,159,((Number)hurtboxInteraction.getValue()).intValue());

            

        	int bit0=getBit(data[17],0),bit1=getBit(data[17],1);

        	int el = HitboxEffect.hitboxEffect[ Attribute.getSelectedIndex()].id;
        	el=bit(el,0,bit0);
        	el=bit(el,1,bit1);
        	this.saveBits(137,143,el);
  		
        }
      
    }
