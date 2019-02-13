package contents;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


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
	
	private JButton open;
	private JButton close;

	private JLabel collectionLabel;
	private JLabel addressLabel;
	private JLabel idLabel;
	
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
		viewCard = new JPanel();
		
		fileAddress = new JTextArea(1, 30);
		siteId = new JTextArea(1, 20);
		siteOutput = new JTextArea(25, 50);
		
		findAddress = new JButton("Import");
		findId = new JButton("Find");
		
		open = new JButton("Open");
		close = new JButton("Close");

		collectionLabel = new JLabel("Site Collection:");
		addressLabel = new JLabel("Enter File Address:");
		idLabel = new JLabel("Enter Site ID:");
	}
	private void setupWindow() {
		
		setSize(630,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		mainPane.add("Read File", readCard);
		mainPane.add("View Site", viewCard);
		this.add(mainPane);
	}
	
	private void addElements() {
		readCard.add(addressLabel);
		readCard.add(fileAddress);
		readCard.add(findAddress);
		
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
		open.addActionListener(this);
		close.addActionListener(this);
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
			file.readFile(fileAddress.getText());
		} else if (event.equals("Find")) {
			siteOutput.setText(file.displaySite(siteId.getText()));
		} else if (event.equals("Open")) {
			System.out.println("Needs to be linked with function");
		} else if (event.equals("Close")) {
			System.out.println("Needs to be linked with function");
		} else if (event.equals("Output")){
			System.out.println("Needs to be linked with function");
		}
	}

}
