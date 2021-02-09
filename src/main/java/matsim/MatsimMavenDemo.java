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

        try{

            run();

        }catch(Exception e)
        {
            System.out.println("Error " + e);
        }
    }

    private static void run() throws FileNotFoundException, IOException
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

}
