package geoutilities.popup.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import geoutilities.Activator;
import geoutilities.services.GeocodeService;

public class AddressToCoordinatesAction implements IObjectActionDelegate {

	private Shell shell;

	public AddressToCoordinatesAction() {
		super();
	}

	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		shell = targetPart.getSite().getShell();
	}

	public void run(IAction action) {
		String selectedText = getSelectedText();
		if(!selectedText.isEmpty()){
			MessageDialog.openInformation(shell,
					"Coordinates", 
					GeocodeService.getCoordinatesFromAddress(selectedText));
		}
		
	}
	
	private String getSelectedText(){
		// get active editor.
		IEditorPart editorPart = Activator.getDefault().getWorkbench()
										  .getActiveWorkbenchWindow()
										  .getActivePage()
										  .getActiveEditor();
		//
		IEditorSite editorSite = editorPart.getEditorSite();
		if(editorSite != null){
			// get selection provider
			ISelectionProvider selectionProvider = editorSite.getSelectionProvider();
			if(selectionProvider != null){
				// get selection
				ISelection selection = selectionProvider.getSelection();
				if(!selection.isEmpty()){
					// string with selected Text
					return ((ITextSelection) selection).getText();
				}
			}
		}
		return "";
	}

	public void selectionChanged(IAction action, ISelection selection) {
	}

}
