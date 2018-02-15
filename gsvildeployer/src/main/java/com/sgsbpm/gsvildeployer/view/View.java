package com.sgsbpm.gsvildeployer.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Comparator;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.sgsbpm.gsvildeployer.beans.TableBean;
import com.sgsbpm.gsvildeployer.model.Model;

public class View implements Observer
{
  private JFrame frame = null;
  private JPanel panel_1 = null;
  private JTable table = null;
  private JCheckBox txxmlCheckBox = null;
  private JCheckBox fdigiCheckBox = null;
  private JCheckBox immlrCheckBox = null;
  private JCheckBox indocCheckBox = null;
  private JCheckBox rxxmlCheckBox = null;
  private JCheckBox svimmCheckBox = null;
  private JCheckBox dspifCheckBox = null;
  private JCheckBox dspvrCheckBox = null;
  private JCheckBox dspvsCheckBox = null;
  private JButton compileAndDeployButton = null;
  private JButton deployConfigFilesButton = null;
  private JTextArea area = null;
  private Model model = null;
  private TableRowSorter<TableModel> sorter = null;
  private Comparator<String> stringComparator = null;
  
  public View(String title, Model model)
  {
    this.model = model;
    this.model.addObserver(this);
    this.stringComparator = new Comparator<String>() 
    {
		public int compare(String arg0, String arg1) 
		{
			return -arg0.compareTo(arg1);
		}
	};
    
    this.frame = new JFrame(title);
    this.frame.setSize(1700, 1300);
    this.frame.setLocationRelativeTo(null);
    
    this.frame.setDefaultCloseOperation(3);
    int SMALL_VERTICAL_SEPARATOR_SIZE = 5;
    int BIG_VERTICAL_SEPARATOR_SIZE = 15;
    
    JPanel panel = new JPanel();
    panel.setPreferredSize(new Dimension(this.frame.getWidth(), 50));
    this.frame.getContentPane().add(panel, "North");
    panel.setLayout(new FlowLayout(1, 5, 5));
    
    JLabel lblNewLabel = new JLabel("DEPLOY BATCH EE000 (SVIL)");
    lblNewLabel.setFont(new Font("Tahoma", 1, 18));
    panel.add(lblNewLabel);
    
    this.panel_1 = new JPanel();
    this.panel_1.setLayout(new BoxLayout(this.panel_1, 1));
    this.panel_1.setPreferredSize(new Dimension(150, 0));
    
    this.txxmlCheckBox = new JCheckBox("TXXML");
    this.txxmlCheckBox.setFont(new Font("Tahoma", 1, 12));
    this.txxmlCheckBox.setHorizontalTextPosition(4);
    this.panel_1.add(this.txxmlCheckBox);
    this.panel_1.add(Box.createVerticalStrut(5));
    
    this.fdigiCheckBox = new JCheckBox("FDIGI");
    this.fdigiCheckBox.setHorizontalAlignment(0);
    this.fdigiCheckBox.setFont(new Font("Tahoma", 1, 12));
    this.fdigiCheckBox.setHorizontalTextPosition(4);
    this.panel_1.add(this.fdigiCheckBox);
    this.panel_1.add(Box.createVerticalStrut(5));
    
    this.immlrCheckBox = new JCheckBox("IMMLR");
    this.immlrCheckBox.setHorizontalAlignment(0);
    this.immlrCheckBox.setFont(new Font("Tahoma", 1, 12));
    this.panel_1.add(this.immlrCheckBox);
    this.panel_1.add(Box.createVerticalStrut(5));
    
    this.indocCheckBox = new JCheckBox("INDOC");
    this.indocCheckBox.setHorizontalAlignment(0);
    this.indocCheckBox.setAlignmentY(0.5F);
    this.indocCheckBox.setFont(new Font("Tahoma", 1, 12));
    this.panel_1.add(this.indocCheckBox);
    this.panel_1.add(Box.createVerticalStrut(5));
    
    this.rxxmlCheckBox = new JCheckBox("RXXML");
    this.rxxmlCheckBox.setHorizontalAlignment(0);
    this.rxxmlCheckBox.setAlignmentY(0.5F);
    this.rxxmlCheckBox.setFont(new Font("Tahoma", 1, 12));
    this.panel_1.add(this.rxxmlCheckBox);
    this.panel_1.add(Box.createVerticalStrut(5));
    
    this.svimmCheckBox = new JCheckBox("SVIMM");
    this.svimmCheckBox.setHorizontalAlignment(0);
    this.svimmCheckBox.setFont(new Font("Tahoma", 1, 12));
    this.panel_1.add(this.svimmCheckBox);
    this.panel_1.add(Box.createVerticalStrut(5));
    
    this.dspifCheckBox = new JCheckBox("DSPIF");
    this.dspifCheckBox.setHorizontalAlignment(0);
    this.dspifCheckBox.setFont(new Font("Tahoma", 1, 12));
    this.panel_1.add(this.dspifCheckBox);
    this.panel_1.add(Box.createVerticalStrut(5));
    
    this.dspvrCheckBox = new JCheckBox("DSPVR");
    this.dspvrCheckBox.setHorizontalAlignment(0);
    this.dspvrCheckBox.setFont(new Font("Tahoma", 1, 12));
    this.panel_1.add(this.dspvrCheckBox);
    this.panel_1.add(Box.createVerticalStrut(5));
    
    this.dspvsCheckBox = new JCheckBox("DSPVS");
    this.dspvsCheckBox.setHorizontalAlignment(0);
    this.dspvsCheckBox.setFont(new Font("Tahoma", 1, 12));
    this.panel_1.add(this.dspvsCheckBox);
    this.panel_1.add(Box.createVerticalStrut(15));
    
    this.frame.getContentPane().add(this.panel_1, "West");
    
    JPanel panel_3 = new JPanel();
    this.panel_1.add(panel_3);
    
    this.compileAndDeployButton = new JButton("Comp.+Depl. Jar's");
    this.compileAndDeployButton.setPreferredSize(new Dimension(140, 35));
    panel_3.add(this.compileAndDeployButton);
    this.compileAndDeployButton.setHorizontalTextPosition(0);
    
    this.deployConfigFilesButton = new JButton("Depl. configs");
    this.deployConfigFilesButton.setPreferredSize(new Dimension(140, 35));
    panel_3.add(this.deployConfigFilesButton);
    this.deployConfigFilesButton.setHorizontalTextPosition(0);
    
    this.panel_1.add(Box.createVerticalStrut(5));
    
    JPanel panel_2 = new JPanel();
    panel_2.setLayout(new GridLayout(1, 1));
    this.table = new JTable();
    this.table.setModel(model);
    
    updateTableRowSorter();
    
    JScrollPane scrollPane = new JScrollPane(this.table);
    panel_2.add(scrollPane);
    this.frame.getContentPane().add(panel_2, "Center");
    
    this.area = new JTextArea();
    this.area.setFont(new Font("Verdana", 0, 12));
    this.area.setEditable(false);
    JScrollPane s = new JScrollPane(this.area);
    
    s.setPreferredSize(new Dimension(this.frame.getWidth(), this.frame.getHeight() / 2));
    s.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Note"));
    this.frame.getContentPane().add(s, "South");
    this.frame.setVisible(true);
    this.frame.pack();
  }
  
  public void update(Observable o, Object arg)
  {
    Model model = (Model)o;
    if ((arg instanceof Boolean))
    {
      this.table.setModel(new DefaultTableModel());
      this.table.setModel(model);
      if (((Boolean)arg).booleanValue()) {
        updateTableRowSorter();
      }
      updateNoteSectionWithBuildResults(model);
    }
    else
    {
      this.area.setText(this.area.getText().concat("\n").concat((String)arg));
    }
    this.frame.setVisible(true);
  }
  
  private void updateTableRowSorter()
  {
    this.sorter = new TableRowSorter(this.table.getModel());
    this.sorter.setComparator(2, this.stringComparator);
    this.table.setRowSorter(this.sorter);
    this.table.getRowSorter().toggleSortOrder(2);
    this.table.updateUI();
  }
  
  private void updateNoteSectionWithBuildResults(Model model)
  {
    StringBuilder builder = new StringBuilder();
    for (TableBean tableBean : model.getRows()) {
      if (tableBean.isToDisplayInNoteSection())
      {
        if (!tableBean.getCompileResult().equals("-")) {
          builder.append(tableBean.getCompileResult());
        }
        if (!tableBean.getDeployJarResult().equals("-")) {
          builder.append(tableBean.getDeployJarResult());
        }
        if (!tableBean.getDeployConfigResult().equals("-")) {
          builder.append(tableBean.getDeployConfigResult());
        }
        tableBean.setToDisplayInNoteSection(false);
      }
    }
    this.area.setText(this.area.getText().concat("\n").concat(builder.toString()));
    this.area.update(this.area.getGraphics());
  }
  
  public JCheckBox getTxxmlCheckBox()
  {
    return this.txxmlCheckBox;
  }
  
  public JCheckBox getFdigiCheckBox()
  {
    return this.fdigiCheckBox;
  }
  
  public JCheckBox getImmlrCheckBox()
  {
    return this.immlrCheckBox;
  }
  
  public JCheckBox getIndocCheckBox()
  {
    return this.indocCheckBox;
  }
  
  public JCheckBox getRxxmlCheckBox()
  {
    return this.rxxmlCheckBox;
  }
  
  public JCheckBox getSvimmCheckBox()
  {
    return this.svimmCheckBox;
  }
  
  public JCheckBox getDspifCheckBox()
  {
    return this.dspifCheckBox;
  }
  
  public JCheckBox getDspvrCheckBox()
  {
    return this.dspvrCheckBox;
  }
  
  public JCheckBox getDspvsCheckBox()
  {
    return this.dspvsCheckBox;
  }
  
  public JTable getTable()
  {
    return this.table;
  }
  
  public JButton getCompileAndDeployButton()
  {
    return this.compileAndDeployButton;
  }
  
  public JButton getDeployConfigFilesButton()
  {
    return this.deployConfigFilesButton;
  }
}
