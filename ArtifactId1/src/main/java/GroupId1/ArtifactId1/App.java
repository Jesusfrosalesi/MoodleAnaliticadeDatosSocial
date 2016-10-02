package GroupId1.ArtifactId1;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import opennlp.tools.*;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.InvalidFormatException;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws InvalidFormatException, IOException
    {
        System.out.println( "Iniciando extraccion de data" );
        String csvFile = "C:/Users/jesus/workspace/ArtifactId1/src/main/java/GroupId1/ArtifactId1/dataAD.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        String[] pattersToCompare = {"para","la","tesis"};
        String patternNavite = "grandes avances";
        
        List<String[]> listOfData = new ArrayList();
        try
        {
            br = new BufferedReader(new FileReader(csvFile));
            int numberLine = 0;
            while ((line = br.readLine()) != null) 
            {
            	numberLine++;
                String[] country = line.split(cvsSplitBy);// use comma as separator

                if(country[2].indexOf(patternNavite) > 0)
                	System.out.println("la linea que tiene esa coincidencia es: " + numberLine );

                listOfData.add(country);
            }

        }
        catch (FileNotFoundException e) 
        {
            e.printStackTrace();
        }
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        finally 
        {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
     
        
        ///////here i should tokenizer
        
       List<String[]> listOfDataTokenized = new ArrayList();	
        	
       ///////	the most important code
	   for (String[] strings : listOfData)
       {
			String[] stringTokensD = tokenizerData(strings[2]);
			 System.out.println(stringTokensD[1]);
			listOfDataTokenized.add(stringTokensD);
       }
	   
	  /*Algoritm to compare */
	   List<Integer> numberLines = new ArrayList<Integer>();
	   for (int i = 0; i < listOfDataTokenized.size(); i++) 
	   {
		    String[] comparing = listOfDataTokenized.get(i);
		    System.out.println("empezo la comparacion");
		    
		    if(findData(pattersToCompare,comparing) == true)
		    	numberLines.add(i);
		}	
	   
	   

		System.out.println("tamaño array"); 
		System.out.println(numberLines.size()); 
		for (int a : numberLines)
		{
			System.out.println("los indices del archivo csv donde hubo coincidencia fueron");
			System.out.println(a + " ");
			
		}
			   

    }
    

    public static String[] tokenizerData(String data) throws InvalidFormatException, IOException 
    {
    	InputStream is = new FileInputStream("C:/Users/jesus/workspace/ArtifactId1/src/main/java/GroupId1/ArtifactId1/en-token.bin");
   	 
    	TokenizerModel model = new TokenizerModel(is);
     
    	Tokenizer tokenizer = new TokenizerME(model);
     
    	String tokens[] = tokenizer.tokenize(data);
       
    	is.close();
    	
       return tokens; 
    }
    
    public static Boolean findData(String[] patterns, String[] dataTokenized)
    {
    	/*for(int i = 0; i < patterns.length; i++)
    	{
    		for(int j = 0; j < dataTokenized.length; j++)
    		{
    			if(patterns[i].equals(dataTokenized[j]) == false && i != 0)
     			{
     				i = 0;
     				j = j - 1;
     			}
    			else if(patterns[i].equals(dataTokenized[j]) == true)
    			{	
    				if(i == patterns.length - 1)
    					return true;
    				i++;
    			}
    			
    		}
    	}*/
		return false;
    }
}
