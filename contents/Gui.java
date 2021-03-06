package contents;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.DecimalFormat;
import javax.swing.*;
import javax.swing.text.NumberFormatter;

@SuppressWarnings("serial")
public class Gui extends JFrame implements ActionListener{
	
	private Controller control;
	
	private JTabbedPane mainPane;
	private JPanel readCard;
	private JPanel viewCard;
	private JPanel addReadingCard;
	private JPanel manageMemoryCard;
	
	private JScrollPane scroll;
	private JTextArea fileAddress;
	private JTextArea siteId;
	private JTextArea siteOutput;
	
	private JButton findAddress;
	private JButton findId;
	private JButton btnReadFC;
	
	private JButton open;
	private JButton close;

	private JLabel collectionLabel;
	private JLabel readAddressLabel;
	private JLabel idLabel;
	
	//Resources for manageMemoryCard JPanel.
	private JButton btnSaveMemory;
	private JButton btnDeleteMemory;
	
	// Resources for the writeCard JPanel.
	private JPanel writeCard, veryBottomWrite, subWriteBtm, subWriteTop;
	private JLabel writeAddressLabel;
	private JComboBox<String> cmbKey;
	private JTextArea txtWriteFileAddress;
	private JFormattedTextField txtMeasurement, txtSite, txtId;
	private JButton btnWriteFC, btnExport, btnAddAttribute;
	
	private JFileChooser fchDialogue;
	
	// The legal file extension.
	private static final String FEXT = ".JSON";
	private static final String FEXT1 = ".XML";
	
	
	public Gui(){
		super("Scientific Data");
		
		instantiate();//should be first
		addElements();
		addListeners();
		setupWindow();//should be last
	}
	
	private void instantiate() {
		control = new Controller();
		mainPane = new JTabbedPane();
		readCard = new JPanel();
		writeCard = new JPanel();
		viewCard = new JPanel();
		addReadingCard = new JPanel();
		manageMemoryCard = new JPanel();
		
		fileAddress = new JTextArea(1, 20);
		siteId = new JTextArea(1, 20);
		siteOutput = new JTextArea(25, 50);
		scroll = new JScrollPane(siteOutput);
		
		findAddress = new JButton("Import");
		
		findId = new JButton("Find/Refresh");
		btnReadFC = new JButton("Choose File");
		
		open = new JButton("Open");
		close = new JButton("Close");
		
		writeAddressLabel = new JLabel("File to Write:");
		txtWriteFileAddress = new JTextArea(1,20);
		btnWriteFC = new JButton("Choose Write File");
		btnExport = new JButton("Export");
		btnAddAttribute = new JButton("Add Entry");

		collectionLabel = new JLabel("Site Collection:");
		readAddressLabel = new JLabel("Enter File Address:");
		idLabel = new JLabel("Enter Site ID:");
		
		btnSaveMemory = new JButton("Save Memory");
		btnDeleteMemory = new JButton("Delete Memory");
		
		
		// A FileChooser reduces the likelihood of user-error in selecting a file.
		fchDialogue = new JFileChooser();
		fchDialogue.setDialogTitle("Choose a file");
		
		// Set fchDialogue to use the working directory.
		fchDialogue.setCurrentDirectory(new File("."));
		
		String buf[] = {"humidity", "particulate", "temp", "bar_press"};
		cmbKey = new JComboBox<String>(buf);
		
		txtMeasurement = new JFormattedTextField(new NumberFormatter(new DecimalFormat("########.####")));
		txtSite = new JFormattedTextField(new NumberFormatter(new DecimalFormat("######")));
		txtId = new JFormattedTextField();
		
		txtMeasurement.setColumns(20);
		txtSite.setColumns(20);
		txtId.setColumns(20);
		
		subWriteBtm = new JPanel();
		
		subWriteTop = new JPanel();
		subWriteBtm.setLayout(new GridLayout(2,1));
		veryBottomWrite = new JPanel();
		writeCard.setLayout(new GridLayout(3,1));
		veryBottomWrite.setLayout(new GridLayout(5,2));
		
	}
	private void setupWindow() {
		
		setSize(630,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		mainPane.add("Read File", readCard);
		mainPane.add("Write File", writeCard);
		mainPane.add("Add Reading", addReadingCard);
		mainPane.add("View Site", viewCard);
		mainPane.add("Manage Storage", manageMemoryCard);
		this.add(mainPane);
	}
	
	private void addElements() {
		readCard.add(readAddressLabel);
		readCard.add(fileAddress);
		readCard.add(btnReadFC);
		readCard.add(findAddress);
		
		viewCard.add(idLabel);
		viewCard.add(siteId);
		viewCard.add(findId);
		viewCard.add(scroll);
		viewCard.add(collectionLabel);
		viewCard.add(open);
		viewCard.add(close);
		
		veryBottomWrite.add(new JLabel("Site ID: "));
		veryBottomWrite.add(txtSite);
		veryBottomWrite.add(new JLabel("Reading ID: "));
		veryBottomWrite.add(txtId);
		veryBottomWrite.add(new JLabel("Reading Value: "));
		veryBottomWrite.add(txtMeasurement);
		veryBottomWrite.add(new JLabel("Reading Type: "));
		veryBottomWrite.add(cmbKey);
		veryBottomWrite.add(new JLabel());
		veryBottomWrite.add(btnAddAttribute);
		
		subWriteBtm.add(new JLabel());
		subWriteBtm.add(veryBottomWrite);
		subWriteTop.add(writeAddressLabel);
		subWriteTop.add(txtWriteFileAddress);
		subWriteTop.add(btnWriteFC);
		subWriteTop.add(btnExport);
		
		writeCard.add(subWriteTop);
		
		addReadingCard.add(subWriteBtm);
		
		manageMemoryCard.add(btnSaveMemory);
		manageMemoryCard.add(btnDeleteMemory);
		
		siteOutput.setEditable(false);
		
	}
	
	private void addListeners() {
		findAddress.addActionListener(this);
		findId.addActionListener(this);
		btnReadFC.addActionListener(this);
		open.addActionListener(this);
		close.addActionListener(this);
		btnExport.addActionListener(this);
		btnWriteFC.addActionListener(this);
		btnAddAttribute.addActionListener(this);
		btnSaveMemory.addActionListener(this);
		btnDeleteMemory.addActionListener(this);
	}
	
	
	private int validateFileType(File file) {
		//filetype = 0 {not valid}
		//filetype = 1 {.json}
		//filetype = 2 {.XML}
		int fileType = 0;
		String buf;
		
		buf = file.getAbsolutePath();
		buf = buf.substring(buf.lastIndexOf('.'));
		
		System.out.println(buf);
		
		if (file.exists() && ((buf.compareToIgnoreCase(FEXT) == 0))) fileType = 1;
		else if (file.exists() && (buf.compareToIgnoreCase(FEXT1) == 0)) fileType = 2;
		
		return fileType;
	}
	
	public void setFileAddressText(String output) {
		fileAddress.setText(output);
	}
	
	public void setSiteIdText(String output) {
		siteId.setText(output);
	}
	
	public void setSiteOuputText(String output) {
		siteOutput.setText(output);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String event = e.getActionCommand();
		String buf;
		
		if (event.equals("Import")) {
			int fileType = 0;
			String fileAddressString = fileAddress.getText();
			fileType = validateFileType(new File(fileAddressString));
			
			if (fileType == 1){	
				control.readJson(fileAddressString);
			} else if (fileType == 2) {
				control.readXML(fileAddressString);
			} else {
				JOptionPane.showMessageDialog(this, "File either doesn't exist or isn't a " +
						FEXT + " or " + FEXT1 + " file!", "Whoops!", JOptionPane.ERROR_MESSAGE);
			}
				
			
			
		} else if (event.equals("Find/Refresh")) {
			// Display an error if the site is null, else display site info.
			if (!siteId.getText().isEmpty())
				siteOutput.setText(control.displaySite(siteId.getText()));
			else JOptionPane.showMessageDialog(this, "Invalid site!", 
					"Whoops!", JOptionPane.ERROR_MESSAGE);
			
		} else if (event.equals("Choose File")) {
			// Choose a file through a dialogue to limit user error selecting files.			
			// Avoid a null pointer exception by checking whether a file was actually chosen.
			if (fchDialogue.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				buf = fchDialogue.getSelectedFile().getAbsolutePath();
				fileAddress.setText(buf);
			}
			
		} else if (event.equals("Open")) {
			// Open all sites by an ID silently
			buf = siteId.getText();
			if (!buf.isEmpty())
				control.setSiteOpen(buf);
			
		} else if (event.equals("Close")) {
			// Close all sites by an ID silently
			buf = siteId.getText();
			if (!buf.isEmpty())
				control.setSiteClosed(buf);
			
		} else if (event.equals("Output")){
			// Will read a site's readings to the output box.
			buf = siteId.getText();
			if (!buf.isEmpty())
				siteOutput.setText(control.displaySite(buf));
			
		} else if (event.equals("Choose Write File")) {
			// Allow specifying a file to write by graphical dialogue
			if (fchDialogue.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				buf = fchDialogue.getSelectedFile().getAbsolutePath();
				txtWriteFileAddress.setText(buf);
			}

		} else if (event.equals("Export")) {
			// Print out a file if the address is valid. Inform the user of results with a message.
			buf = txtWriteFileAddress.getText();
			if(control.writeJson(buf)) JOptionPane.showMessageDialog(this, "File written successfully.", 
					"Success!", JOptionPane.INFORMATION_MESSAGE);
			else JOptionPane.showMessageDialog(this, "File was not written successfully.", 
					"Whoops!", JOptionPane.ERROR_MESSAGE);
			
		} else if (event.equals("Add Entry")) {
			// Add an entry to the current file if site is open for recording.
			if(txtSite.getText().isEmpty() || txtId.getText().isEmpty() || txtMeasurement.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this, "At Least One Input Was Left Empty.", 
						"Whoops!", JOptionPane.ERROR_MESSAGE);
			} else { control.addEntry(txtSite.getText(), txtId.getText(),
				cmbKey.getSelectedItem().toString(), txtMeasurement.getText(),
				String.valueOf((System.currentTimeMillis() / 1000)));
				JOptionPane.showMessageDialog(this, "Reading Added Successfully.", 
						"Success!", JOptionPane.INFORMATION_MESSAGE);
			}
			
		} else if(event.equals("Save Memory"))
		{
			if(control.saveToMemory())
				JOptionPane.showMessageDialog(this, "Successfully saved your progress.", 
					"Save Confirmation", JOptionPane.PLAIN_MESSAGE);
			else
				JOptionPane.showMessageDialog(this, "Failed to save your progress.", 
						"SAVE FAILURE", JOptionPane.ERROR_MESSAGE);
			
		} else if(event.equals("Delete Memory"))
		{
			JOptionPane.showMessageDialog(this, "This isn't done yet.", 
					"Whoops!", JOptionPane.ERROR_MESSAGE);
		}
	}

}
