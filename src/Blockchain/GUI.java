package Blockchain;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

public class GUI {

	protected Shell shell;
	private Text txtEnterReceivingAddress;
	private Text txtEnterAmount;
	private Text txtEnterAmount_1;
	Wallet wal = new Wallet();
	Block newestBlock = actualChain.constructChain();
	String newestHash = newestBlock.hash;
	String startingHash = newestHash;
	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			actualChain.constructChain();
			GUI window = new GUI();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(600, 479);
		shell.setText("SWT Application");
		shell.setLayout(null);
		
		Label Balance = new Label(shell, SWT.NONE);
		Balance.setBounds(117, 29, 412, 20);
		Balance.setText("Balance: " + wal.balance);
		
		Label lblwalId = new Label(shell, SWT.NONE);
		lblwalId.setBounds(117, 55, 192, 20);		
		lblwalId.setText("wal ID: " + wal.Id);
		
		Label Address = new Label(shell, SWT.NONE);
		Address.setBounds(117, 81, 192, 20);		
		Address.setText("Address: " + wal.address);
		
		Label lblSend = new Label(shell, SWT.NONE);
		lblSend.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.BOLD));
		lblSend.setBounds(57, 150, 119, 37);
		lblSend.setText("Send:");
		
		txtEnterReceivingAddress = new Text(shell, SWT.BORDER);
		txtEnterReceivingAddress.setText("Enter Receiving Address");
		txtEnterReceivingAddress.setBounds(91, 193, 438, 26);
		
		txtEnterAmount = new Text(shell, SWT.BORDER);
		txtEnterAmount.setText("Enter Amount");
		txtEnterAmount.setBounds(91, 225, 438, 26);
		
		Button btnSend = new Button(shell, SWT.NONE);
		btnSend.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {				
				try {
					double rAddress = Double.parseDouble(txtEnterReceivingAddress.getText());
					double change = Double.parseDouble(txtEnterAmount.getText());
					System.out.println(rAddress);					
					newestBlock = new Block(change, newestBlock);
					newestHash = newestBlock.hash;										
					wal.balance = wal.changeBalance(-change, wal.balance);	
					Balance.setText("Balance: " + Double.toString(wal.balance));	
					wal.history.add(newestBlock);
					//new Block(change, getLastHash);
				}
				catch (Exception exc) {
					MessageDialog.openError(shell, "Error", "Order format incorrect");
					return;
				}
				return;
			}
		});
		btnSend.setBounds(439, 257, 90, 30);
		btnSend.setText("Send");
		
		
		
		
		
		//receiving portion
		Label lblReceive = new Label(shell, SWT.NONE);
		lblReceive.setText("Receive:");
		lblReceive.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.BOLD));
		lblReceive.setBounds(57, 282, 119, 37);
		
		txtEnterAmount_1 = new Text(shell, SWT.BORDER);
		txtEnterAmount_1.setText("Enter Amount");
		txtEnterAmount_1.setBounds(91, 325, 438, 26);
		
		Button btnReceive = new Button(shell, SWT.NONE);
		btnReceive.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {				
				try {					
					double change = Double.parseDouble(txtEnterAmount_1.getText());									
					wal.balance = wal.changeBalance(change, wal.balance);					
					Balance.setText("Balance: " + Double.toString(wal.balance));					
					//new Block(change, getLastHash);
				}
				catch (Exception exc) {
					MessageDialog.openError(shell, "Error", "Order format incorrect");
					return;
				}
				return;
			}
		});
		btnReceive.setBounds(439, 357, 90, 30);
		btnReceive.setText("Receive");
		
		
		
		
		//History portion
		Button btnHistory = new Button(shell, SWT.NONE);
		btnHistory.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (wal.history.size() == 0) {
					MessageDialog.openInformation(shell, "Hisotry", "No history");
				}
				for (int i = 0; i < wal.history.size(); i++)
					MessageDialog.openInformation(shell, "Hisotry", "Hash: " + wal.history.get(i).hash + "\n     Value: " +wal.history.get(i).value);
			}
		});
		btnHistory.setBounds(58, 114, 90, 30);
		btnHistory.setText("History");	
		
		
		
		//View block chain button
		Button btnViewBlockchain = new Button(shell, SWT.NONE);
		btnViewBlockchain.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {				
				Block curBlock = newestBlock; 
				String printList = curBlock.hash;
				while (curBlock.hash != startingHash) {
					curBlock = curBlock.previousHash;
					printList += curBlock.hash + "\n";
					
				}					
				MessageDialog.openInformation(shell, "Blockchain", printList);
			}
		});
		btnViewBlockchain.setBounds(389, 114, 140, 30);
		btnViewBlockchain.setText("View BlockChain");
		
	}
}

		
