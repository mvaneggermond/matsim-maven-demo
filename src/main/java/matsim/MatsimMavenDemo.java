package main.java.matsim;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.matsim.api.core.v01.Scenario;
import org.matsim.api.core.v01.population.Population;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.scenario.ScenarioUtils;

import java.io.*;

public class MatsimMavenDemo {

    public static void main(String[] args) {
        System.out.println("Running a demo"); // Display the string.
        System.out.println("Static context"); // Display the string.

        try{

            runStaticContext();

        }catch(Exception e)
        {
            System.out.println("Error " + e);
        }
        System.out.println("Initialized"); // Display the string.
        try{

            MatsimMavenDemo md = new MatsimMavenDemo();
            md.runInitializedContext();
        }catch(Exception e)
        {
            System.out.println("Error " + e);
        }


    }

    public MatsimMavenDemo()
    {
        super();
    }

    /*
    * Works only in a static context: MatsimMavenDemo.class.getClassLoader().
    * https://mkyong.com/java/java-getresourceasstream-in-static-method/
    */
    private static void runStaticContext() throws FileNotFoundException, IOException
    {
        // the stream holding the file content
        InputStream csvFile = MatsimMavenDemo.class.getClassLoader().getResourceAsStream("network.csv");

        InputStreamReader input = new InputStreamReader(csvFile);
        CSVParser csvParser = CSVFormat.EXCEL.withFirstRecordAsHeader().parse(input);
        for (CSVRecord record : csvParser) {
            String field_1 = record.get("from");
            String field_2 = record.get("to");
            String field_3 = record.get("cost");
        }

        Scenario scenario = ScenarioUtils.createScenario(ConfigUtils.createConfig());
        Population population = scenario.getPopulation();


    }

    /*
     * Initialized context: MatsimMavenDemo.class.getClassLoader().
     * https://mkyong.com/java/java-getresourceasstream-in-static-method/
     */
    private void runInitializedContext() throws FileNotFoundException, IOException
    {
        // the stream holding the file content
        InputStream csvFile = getClass().getClassLoader().getResourceAsStream("network.csv");

        this.readCSV(csvFile);


    }

    private void readCSV(InputStream csvFile)
    {
        InputStreamReader input = new InputStreamReader(csvFile);
        CSVParser csvParser = CSVFormat.EXCEL.withFirstRecordAsHeader().parse(input);
        for (CSVRecord record : csvParser) {
            String field_1 = record.get("from");
            String field_2 = record.get("to");
            String field_3 = record.get("cost");
        }

        Scenario scenario = ScenarioUtils.createScenario(ConfigUtils.createConfig());
        Population population = scenario.getPopulation();
    }

}
