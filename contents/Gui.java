package contents;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;

/*	TODO:
 * 		Add functionality for open/close
 * 
 * 		Add functionality to writer panel
 * 
 * 	RECENTLY ADDED:
 * 		Added a file chooser.
 * 
 * 		Added input validation for file IO.
 * 		Applied input validation to button handling.
 * 		Added error boxes if bad file paths are imported.
 * 
 * 		Added a main function to Gui.java 
 * 		***Doesn't need to be the entry point, but it works for now***
 */

@SuppressWarnings("serial")
public class Gui extends JFrame implements ActionListener{
	
	private FileOperator file;
	
	private JTabbedPane mainPane;
	private JPanel readCard;
	private JPanel viewCard;
	
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
	
	// Resources for the writeCard JPanel.
	private JPanel writeCard;
	private JLabel writeAddressLabel;
	private JTextArea writeFileAddress;
	private JButton btnWriteFC;
	private JButton btnExport;
	
	private JFileChooser fchDialogue;
	
	// The legal file extension.
	private static final String FEXT = ".JSON";
	
	// Program needed an entry point.
	public static void main(String args[]) {
		Gui gui = new Gui();
		gui.setVisible(true);
		System.out.println("Exiting!");
	}
	
	public Gui(){
		super("Scientific Data");
		
		instantiate();//should be first
		addElements();
		addListeners();
		setupWindow();//should be last
	}
	
	private void instantiate() {
		file = new FileOperator();
		mainPane = new JTabbedPane();
		readCard = new JPanel();
		writeCard = new JPanel();
		viewCard = new JPanel();
		
		fileAddress = new JTextArea(1, 20);
		siteId = new JTextArea(1, 20);
		siteOutput = new JTextArea(25, 50);
		
		findAddress = new JButton("Import");
		findId = new JButton("Find");
		btnReadFC = new JButton("Choose File");
		
		open = new JButton("Open");
		close = new JButton("Close");
		
		writeAddressLabel = new JLabel("File to Write:");
		writeFileAddress = new JTextArea(1,20);
		btnWriteFC = new JButton("Choose Write File");
		btnExport = new JButton("Export");

		collectionLabel = new JLabel("Site Collection:");
		readAddressLabel = new JLabel("Enter File Address:");
		idLabel = new JLabel("Enter Site ID:");
		
		// A FileChooser reduces the likelihood of user-error in selecting a file.
		fchDialogue = new JFileChooser();
		fchDialogue.setDialogTitle("Choose a file");
		
		// Set fchDialogue to use the working directory.
		fchDialogue.setCurrentDirectory(new File("."));
	}
	private void setupWindow() {
		
		setSize(630,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		mainPane.add("Read File", readCard);
		mainPane.add("Write File", writeCard);
		mainPane.add("View Site", viewCard);
		this.add(mainPane);
	}
	
	private void addElements() {
		readCard.add(readAddressLabel);
		readCard.add(fileAddress);
		readCard.add(btnReadFC);
		readCard.add(findAddress);
		
		writeCard.add(writeAddressLabel);
		writeCard.add(writeFileAddress);
		writeCard.add(btnWriteFC);
		writeCard.add(btnExport);
		
		viewCard.add(idLabel);
		viewCard.add(siteId);
		viewCard.add(findId);
		
		viewCard.add(siteOutput);
		viewCard.add(collectionLabel);
		viewCard.add(open);
		viewCard.add(close);
		
		
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
	}
	
	// Will return true if file given is accessible and has .json extension.
	private boolean validateInput(File file) {
		boolean ret = false;
		String buf;
		
		buf = file.getAbsolutePath().substring(5);
		
		if ((buf.compareToIgnoreCase(FEXT) == 0) && (file.exists())) {
			ret = true;
		}
		
		return ret;
	}
	
	// True if file is accessible and has .json extension.
	private boolean validateInput(String file) {
		return validateInput(new File(file));
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
		
		if (event.equals("Import")) {
			// Display an error if the file is bad, else open a file.
			if (validateInput(fileAddress.getText()))
				file.readFile(fileAddress.getText());
			else JOptionPane.showMessageDialog(this, "File either doesn't exist or isn't a .JSON file!", 
					"Whoops!", JOptionPane.ERROR_MESSAGE);
			
		} else if (event.equals("Find")) {
			// Display an error if the file is bad, else read from file.
			if (validateInput(siteId.getText()))
				siteOutput.setText(file.displaySite(siteId.getText()));
			else JOptionPane.showMessageDialog(this, "Invalid file!", 
					"Whoops!", JOptionPane.ERROR_MESSAGE);
			
		} else if (event.equals("Choose File")) {
			// Choose a file through a dialogue to limit user error selecting files.
			String buf;
			
			// Avoid a null pointer exception by checking whether a file was actually chosen.
			if (fchDialogue.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				buf = fchDialogue.getSelectedFile().getAbsolutePath();
				fileAddress.setText(buf);
			}
			
		} else if (event.equals("Open")) {
			System.out.println("Needs to be linked with function");
			
		} else if (event.equals("Close")) {
			System.out.println("Needs to be linked with function");
			
		} else if (event.equals("Output")){
			System.out.println("Needs to be linked with function");
			
		} else if (event.equals("Choose Write File")) {
			System.out.println("Not yet implemented.");

		} else if (event.equals("Export")) {
			System.out.println("Not yet implemented.");

		}
	}

}
