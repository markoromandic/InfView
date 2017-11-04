package actions;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JDialog;
import javax.swing.JTextArea;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.load.Dereferencing;
import com.github.fge.jsonschema.core.load.configuration.LoadingConfiguration;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchemaFactory;

import appCore.Core;

public class ValidateMetaScheme extends AbstractEditorAction
{

	@Override
	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub
		ProcessingReport report = null;
	    boolean result = false;
	    try {
	        JsonNode schemaNode = JsonLoader.fromFile(new File("meta_scheme/meta_meta_scheme.json"));
//	        JsonNode data = JsonLoader.fromFile(new File("meta_scheme/meta_scheme.json"));
	        JsonNode data = JsonLoader.fromString(Core.getInstance().getMainViewMa().getTextAreaEditor().getText());
	        LoadingConfiguration cfg = LoadingConfiguration.newBuilder().dereferencing(Dereferencing.INLINE).freeze();
	        JsonSchemaFactory factory = JsonSchemaFactory.newBuilder().setLoadingConfiguration(cfg).freeze();
	        com.github.fge.jsonschema.main.JsonSchema schema = factory.getJsonSchema(schemaNode);
	        report = schema.validate(data);
	        Core.getInstance().getMainViewMa().setButtonSaveStatus(true);
	    }catch (JsonParseException jpex) {
	        System.out.println("Error. "+jpex.getMessage());
	        JDialog dialog = new JDialog();
	        JTextArea textArea = new JTextArea("Error. "+jpex.getMessage());
	        textArea.setLineWrap(true);
	        textArea.setWrapStyleWord(true);
	        dialog.add(textArea);
	        dialog.setLocationRelativeTo(null);
	        dialog.setSize(400, 100);
	        dialog.setVisible(true);
	    } catch (ProcessingException pex) {  
	        System.out.println("Error. "+pex.getMessage());
	        JDialog dialog = new JDialog();
	        JTextArea textArea = new JTextArea("Error. "+pex.getMessage());
	        textArea.setLineWrap(true);
	        textArea.setWrapStyleWord(true);
	        dialog.add(textArea);
	        dialog.setLocationRelativeTo(null);
	        dialog.setSize(400, 100);
	        dialog.setVisible(true);
	    } catch (IOException err) {
	        System.out.println("Error. " + err);
	        JDialog dialog = new JDialog();
	        JTextArea textArea = new JTextArea("Error. "+err.getMessage());
	        textArea.setLineWrap(true);
	        textArea.setWrapStyleWord(true);
	        dialog.add(textArea);
	        dialog.setLocationRelativeTo(null);
	        dialog.setSize(400, 100);
	        dialog.setVisible(true);
	    }
	    
	    Core.getInstance().getModel().loadFromJSONToRoot();
	}

}
