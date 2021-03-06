package ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.RowLayout;
//import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import java.io.File;
import java.util.Vector;

import tools.Debug;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;
import org.eclipse.swt.layout.GridData;

public class MainWin {

	protected Shell shlComplexNetwork;
	private Text textFileFilter;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MainWin window = new MainWin();
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
		shlComplexNetwork.open();
		shlComplexNetwork.layout();
		while (!shlComplexNetwork.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlComplexNetwork = new Shell();
		shlComplexNetwork.setSize(811, 527);
		shlComplexNetwork.setText("complex network");
		shlComplexNetwork.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(shlComplexNetwork, SWT.BORDER | SWT.SMOOTH);
		
		Composite compositeFile = new Composite(sashForm, SWT.NONE);
		compositeFile.setLayout(new GridLayout(1, false));
		
		Composite compositeOpen = new Composite(compositeFile, SWT.NONE);
		compositeOpen.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		compositeOpen.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Button btnNewButton = new Button(compositeOpen, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selectFiles("doc");
			
			}
		});
		btnNewButton.setText("open");
		
		textFileFilter = new Text(compositeOpen, SWT.BORDER);
		
		Composite compositeFileList = new Composite(compositeFile, SWT.NONE);
		GridData gd_compositeFileList = new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1);
		gd_compositeFileList.widthHint = 195;
		gd_compositeFileList.heightHint = 426;
		compositeFileList.setLayoutData(gd_compositeFileList);
		compositeFileList.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		List list = new List(compositeFileList, SWT.BORDER);
		
		Composite compositeOperation = new Composite(sashForm, SWT.NONE);
		compositeOperation.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		TabFolder tabFolder = new TabFolder(compositeOperation, SWT.NONE);
		
		TabItem tbtmNewItem = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem.setText("property");
		
		TabItem tbtmNewItemPropertyLinkPrediction = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItemPropertyLinkPrediction.setText("link prediction");
		sashForm.setWeights(new int[] {212, 572});

	}
	
	public File[] selectFiles(String keyword) {
			
		 try {									

			String[] fileNames =null;
			
			
			FileDialog	dialog = new FileDialog(shlComplexNetwork, SWT.MULTI);
			
				
				dialog.setText("请选择要打开的文件，按住Shift连续多选，按住Ctrl间隔多选");
				dialog.setFilterExtensions(new String[] { "*."+keyword });
				dialog.setFilterNames(new String[] { "*."+keyword,
						"All Files (*.*)" });
				//return last file name
				String saveFileName=dialog.open();//返回最后一个选择文件的全路径
				 if (saveFileName == null) {
		             // User has cancelled, so quit and return
		         //	MessageBox mg = new MessageBox(dialog.getParent(),
		          //           SWT.ICON_WARNING| SWT.YES);
		         //	mg.setText("Tips");
		          //   mg.setMessage("没有指定要打开的文件，或取消了打开文件！");
		          //   boolean done=mg.open() == SWT.YES;
				 }
				 // root 
				String path = dialog.getFilterPath();//返回选择的路径，
				     
				//all file name
				
				fileNames = dialog.getFileNames();
				Vector<File> files=new Vector<File>();
				for(String name:fileNames){
					files.add(new File(path + name));
				}
				
				return files.toArray(new File [1]);
		
			
			
			//return fileNames;
		} 
		 finally
		{
      
			// Display.getDefault().dispose();
	    }
	}
	
	
}
