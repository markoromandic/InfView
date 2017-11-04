package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.load.Dereferencing;
import com.github.fge.jsonschema.core.load.configuration.LoadingConfiguration;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;

import Structure.AbstractEntity;
import Structure.Attribute;
import Structure.Entity;
import Structure.Package;
import Structure.Storage;
import appCore.Core;
import constants.Constants;

public class MetaSchemeInterpreter 
{
	private ObjectMapper mapper = null;
	private Storage storage = null;
	private ArrayList<JSONArray> listRelations;
	private ArrayList<Structure.Entity> listCorespondingEntity;
	private ArrayList<String> searchingType;
	
//	public static int brRELACIJA = 0;
	
	public MetaSchemeInterpreter(){
		listCorespondingEntity = new ArrayList<>();
		listRelations = new ArrayList<>();
		searchingType = new ArrayList<>();
	}
	
	public Storage ucitaj()
	{

		BufferedReader br;
		
		
		try {
			
			
			br = new BufferedReader(new InputStreamReader(
					new FileInputStream(new File(Constants.TEST_META_SCHEME))));
			
			
			JSONTokener tokener = new JSONTokener(br);
			JSONObject o = new JSONObject(tokener);
			
			br.close();
			
			JSONArray array = o.getJSONArray("collection");
			
			Storage storage = new Storage();
			storage.setName(o.getString("name"));
			try{
				storage.setSQL(o.getBoolean("sql"));
			} catch(Exception e){
				storage.setSQL(false);
			}
			try{
				storage.setUsername(o.getString("username"));
			} catch(Exception e){
			}
			try{
				storage.setPassword(o.getString("password"));
			} catch(Exception e){
			}
			try{
			storage.setUrl(o.getString("url"));
			} catch(Exception e){}
			
			for(int i=0; i<array.length(); i++){
				JSONObject curObj = array.getJSONObject(i);

				if(curObj.getString("type").equals("Package")){
					Structure.Package newPackage = new Structure.Package();
					newPackage.setName(curObj.getString("name"));
					try{
					newPackage.setUrl(curObj.getString("url"));
					} catch(Exception e){}
					storage.addChild(newPackage);
					goTP(curObj, newPackage);
				}
				else if(curObj.getString("type").equals("Entity")){
					Structure.Entity newEntity = new Structure.Entity();
					try{
					newEntity.setCode(curObj.getString("code"));
					} catch(Exception e){}
					newEntity.setName(curObj.getString("name"));
					try{
					newEntity.setUrl(curObj.getString("url"));
					} catch(Exception e){}
					newEntity.setDataBaseType(curObj.getString("database_type"));
					if(newEntity.getDataBaseType().equals("ind")){
						newEntity.setUrlOver(curObj.getString("url_over"));
						newEntity.setUrlTree(curObj.getString("url_tree"));
					}
					storage.addChild(newEntity);
					goTE(curObj, newEntity);
				}
			}
			
			return storage;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public ObjectMapper getMapper()
	{
		return mapper;
	}

	private void goTP(JSONObject jp, Structure.Package pck){
		JSONArray array = jp.getJSONArray("collection");
		for(int i=0; i<array.length(); i++){
			JSONObject curObj = array.getJSONObject(i);
			if(curObj.getString("type").equals("Package")){
				Structure.Package newPackage = new Structure.Package();
				try{
				newPackage.setUrl(curObj.getString("url"));
				} catch (Exception e){}
				newPackage.setName(curObj.getString("name"));
				pck.addChild(newPackage);
				goTP(curObj, newPackage);
			}
			else if(curObj.getString("type").equals("Entity")){
				Structure.Entity newEntity = new Structure.Entity();
				try{
					newEntity.setCode(curObj.getString("code"));
					} catch(Exception e){}
				newEntity.setName(curObj.getString("name"));
				try{
				newEntity.setUrl(curObj.getString("url"));
				} catch(Exception e){}
				newEntity.setDataBaseType(curObj.getString("database_type"));
				if(newEntity.getDataBaseType().equals("ind")){
					newEntity.setUrlOver(curObj.getString("url_over"));
					newEntity.setUrlTree(curObj.getString("url_tree"));
				}
				pck.addChild(newEntity);
				goTE(curObj, newEntity);
			}
		}
	}
	
	private void goTE(JSONObject je, Structure.Entity ent){
		JSONArray arrayAttributes = je.getJSONArray("attributes");
		for(int i=0; i<arrayAttributes.length(); i++){
			JSONObject curObject = arrayAttributes.getJSONObject(i);
			Structure.Attribute newAttribute = new Structure.Attribute();
			newAttribute.setName(curObject.getString("name"));
			newAttribute.setValue(curObject.getString("value_type"));
			try{
				newAttribute.setCode(curObject.getString("code"));
			} catch(Exception e){}
			try{
				newAttribute.setPrimary(curObject.getBoolean("primary-key"));
			} catch(Exception e){
				newAttribute.setPrimary(false);
			}
			try{
				newAttribute.setLength(curObject.getLong("length"));
			} catch(Exception e){
				
			}
			try{
			newAttribute.setMandatory(curObject.getBoolean("mandatory"));
			} catch(Exception e){
				
			}
			
			ent.addChildAttribute(newAttribute);
		}
//		System.out.println(je.getString("name"));
//		System.out.println("Ima li me za mene:"+ent.getName());
		if(je.getJSONArray("relations")!=null && je.getJSONArray("relations").length()!=0){
//			System.out.println("USAO SAM ZA RELATIONS! Evo ovako. Entity PAPA:"+ent.getName());
			
			listRelations.add(je.getJSONArray("relations"));
			listCorespondingEntity.add(ent);
//			System.out.println("DATABASE TYPE IS THIS:"+ent.getDataBaseType());
			searchingType.add(ent.getDataBaseType());
		}
//		JSONArray arrayRelations = je.getJSONArray("relations");
//		for(int i=0; i<arrayRelations.length(); i++){
//			//What to do with relations
//			JSONObject curObject = arrayRelations.getJSONObject(i);
//			Structure.Relation newRelation = new Structure.Relation();
//			newRelation
//		}
	}
	Entity referedEntityTarget;
	public void fillRelations(){ 
	
		for(int j = 0; j<listRelations.size(); j++){
			JSONArray arrayRelations = listRelations.get(j);
//			System.out.println("EntityThatYouWillAttach: "+listCorespondingEntity.get(i).getName);
			for(int i=0; i<arrayRelations.length(); i++){
//				//What to do with relations
				JSONObject curObject = arrayRelations.getJSONObject(i);
				Structure.Relation newRelation = new Structure.Relation();
				String referedEntity = (String)curObject.getJSONArray("referedEntity").get(0);
				ArrayList<String> refered = new ArrayList<>();
				ArrayList<String> reference = new ArrayList<>();
				for(int z=0; z<curObject.getJSONArray("refered").length(); z++){
					refered.add((String)curObject.getJSONArray("refered").get(z));
					reference.add((String)curObject.getJSONArray("reference").get(z));
				}
//				Entity referedEntityTarget;
				ArrayList<Attribute> referedTarget = new ArrayList<>();
				ArrayList<Attribute> referenceTarget = new ArrayList<>();;
//				referedTarget = new Attribute();
//				referenceTarget = new Attribute();
				
				
				ArrayList<AbstractEntity> abstractListOfStorageChildren = ((Storage) Core.getInstance().getModel().getTreeModel().getRoot()).getChildren();
				for(AbstractEntity abstractEntity: abstractListOfStorageChildren){
					if(abstractEntity instanceof Package){
//						System.out.println("THIS IS THE NAME OF PACKAGE:"+((Package)abstractEntity).getName());
						if(findFromPackage(((Package)abstractEntity), referedEntity, j)){
							break;
						}
						else{
							continue;
						}
					}
					Entity newEnt = (Entity) abstractEntity;
//					System.out.println("Should be entity");
					if(newEnt.getName().equals(referedEntity) && searchingType.get(j).equals(newEnt.getDataBaseType())){
						referedEntityTarget = newEnt;
						break;
					} else if(newEnt.getCode().equals(referedEntity) && searchingType.get(j).equals(newEnt.getDataBaseType())){
						referedEntityTarget = newEnt;
						break;
					}
				}
//				System.out.println(curObject.getString("code"));
//				System.out.println(referedEntity);
//				System.out.println(refered);
//				System.out.println(reference);
//				System.out.println(referedEntityTarget);
				
				//OVO JE DODAT TRY DA POKSUA DA PRORADI
//				try{
				//Prodji kroz atribute
//				System.out.println("This is the PAPA entity:"+listCorespondingEntity.get(i).getName()+" ;This is CHILD entity:"+referedEntityTarget.getName());
				for(String referedString : refered){
					for(Attribute curAtribute : referedEntityTarget.getChildrenAttributes()){
						
	//					System.out.println("SYSO MALA!");
						
						if(curAtribute.getName().equals(referedString)) referedTarget.add(curAtribute);
	//					if(curAtribute.getName().equals(reference)) referenceTarget = curAtribute;
					}
				}
				for(String referenceString : reference){
					for(Attribute curAtribute : listCorespondingEntity.get(i).getChildrenAttributes()){
						
	//					System.out.println("SYSO MALA!");
						
						if(curAtribute.getName().equals(referenceString)) referenceTarget.add(curAtribute);
					}
				}
				
				newRelation.setChildEntity(referedEntityTarget);
				newRelation.setRefered(referedTarget);
				newRelation.setReference(referenceTarget);
				
				listCorespondingEntity.get(j).getChildrenRelations().add(newRelation);
//				brRELACIJA++;
//				System.out.println(brRELACIJA);
			}
		}
	}
	
	private boolean findFromPackage(Package curPackage, String referedEntity, int index){
		ArrayList<AbstractEntity> abstractListOfPackageChildren = curPackage.getChildren();
		for(AbstractEntity abstractEntity: abstractListOfPackageChildren){
			
			if(abstractEntity instanceof Package){
				if(findFromPackage(((Package)abstractEntity), referedEntity, index)){
					return true;
				}
				else{
					continue;
				}
			}
			Entity newEnt = (Entity) abstractEntity;
//			System.out.println("Naziv one koje prolazim: "+ newEnt.getName() + " ; Naziv one koju trazim: " + referedEntity);
			if(newEnt.getName().equals(referedEntity) && searchingType.get(index).equals(newEnt.getDataBaseType())){
				referedEntityTarget = newEnt;
				return true;
			}
		}
		return false;
	}
	
	public void loadFromJSONEditor(String json)
	{
		try 
		{
			JSONObject o = new JSONObject(json);
			
			JSONArray array = o.getJSONArray("collection");
			
			storage = new Storage();
			storage.setName(o.getString("name"));
			try{
				System.out.println("ILL TRY NOW!");
				storage.setSQL(o.getBoolean("sql"));
			} catch(Exception e){
//				GRESKAA
				System.out.println("KME KME KMEEEEE");
				storage.setSQL(false);
			}
			try{
				storage.setUsername(o.getString("username"));
			} catch(Exception e){
			}
			try{
				storage.setPassword(o.getString("password"));
			} catch(Exception e){
			}
			
			for(int i=0; i<array.length(); i++)
			{
				JSONObject curObj = array.getJSONObject(i);
				if(curObj.getString("type").equals("Package"))
				{
					Structure.Package newPackage = new Structure.Package();
					newPackage.setName(curObj.getString("name"));
					try{
					newPackage.setUrl(curObj.getString("url"));
					} catch(Exception e){}
					storage.addChild(newPackage);
					goTP(curObj, newPackage);
				}
				else if(curObj.getString("type").equals("Entity"))
				{
					Structure.Entity newEntity = new Structure.Entity();
					try{
						newEntity.setCode(curObj.getString("code"));
						} catch(Exception e){}
					newEntity.setName(curObj.getString("name"));
					try{
					newEntity.setUrl(curObj.getString("url"));
					} catch (Exception e){}
					newEntity.setDataBaseType(curObj.getString("database_type"));
					if(newEntity.getDataBaseType().equals("ind")){
						newEntity.setUrlOver(curObj.getString("url_over"));
						newEntity.setUrlTree(curObj.getString("url_tree"));
					}
					storage.addChild(newEntity);
					goTE(curObj, newEntity);
				}
			}

		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public Storage loadToTree(String path)
	{

		BufferedReader br;
		
		try 
		{
			
			
			br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(path))));
			
			
			JSONTokener tokener = new JSONTokener(br);
			JSONObject o = new JSONObject(tokener);
			
			br.close();
			
			JSONArray array = o.getJSONArray("collection");
			
			Storage storage = new Storage();
			storage.setName(o.getString("name"));
			try{
			storage.setUrl(o.getString("url"));
			} catch(Exception e){}
			try{
				System.out.println("ILL TRY NOW!");
				storage.setSQL(o.getBoolean("sql"));
			} catch(Exception e){
//				GRESKAA
				System.out.println("KME KME KMEEEEE");
				storage.setSQL(false);
			}
			try{
				storage.setUsername(o.getString("username"));
			} catch(Exception e){
			}
			try{
				storage.setPassword(o.getString("password"));
			} catch(Exception e){
			}
			
			for(int i=0; i<array.length(); i++)
			{
				JSONObject curObj = array.getJSONObject(i);
				if(curObj.getString("type").equals("Package"))
				{
					Structure.Package newPackage = new Structure.Package();
					newPackage.setName(curObj.getString("name"));
					try{
					newPackage.setUrl(curObj.getString("url"));
					} catch (Exception e){}
					storage.addChild(newPackage);
					goTP(curObj, newPackage);
				}
				else if(curObj.getString("type").equals("Entity"))
				{
					Structure.Entity newEntity = new Structure.Entity();
					try{
						newEntity.setCode(curObj.getString("code"));
						} catch(Exception e){}
					newEntity.setName(curObj.getString("name"));
					try{
					newEntity.setUrl(curObj.getString("url"));
					} catch(Exception e){}
					newEntity.setDataBaseType(curObj.getString("database_type"));
					if(newEntity.getDataBaseType().equals("ind")){
						newEntity.setUrlOver(curObj.getString("url_over"));
						newEntity.setUrlTree(curObj.getString("url_tree"));
					}
					storage.addChild(newEntity);
					goTE(curObj, newEntity);
				}
			}
			return storage;
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public Storage getStorage()
	{
		return storage;
	}

	public Object loadJSONForView(String path)
	{
		mapper = new ObjectMapper();
		
		File fajl = new File(path);
		
		try 
		{
			Scanner skener = new Scanner(fajl);
			StringBuilder sbilderz = new StringBuilder();
			while(skener.hasNextLine())
			{
				sbilderz.append(skener.nextLine());
			}
			
			try 
			{
				Object metashema = mapper.readValue(sbilderz.toString(), Object.class);
				return metashema;
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			skener.close();
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
	public boolean validate(String editedJSON)
	{
		ProcessingReport report = null;
		
		try
		{
			JsonNode schemaNode = JsonLoader.fromFile(new File(Constants.METAMETA_SCHEME));
			
			JsonNode data = JsonLoader.fromString(editedJSON);
	        LoadingConfiguration cfg = LoadingConfiguration.newBuilder().dereferencing(Dereferencing.INLINE).freeze();
	        JsonSchemaFactory factory = JsonSchemaFactory.newBuilder().setLoadingConfiguration(cfg).freeze();
	        JsonSchema schema = factory.getJsonSchema(schemaNode);
	        report = schema.validate(data);
	        loadFromJSONEditor(editedJSON);
	        
	        return true;
		}
		catch (IOException eh)
		{
			eh.printStackTrace();
			return false;
		}
		catch (ProcessingException e) 
		{
			e.printStackTrace();
			return false;
		}
	}
}
