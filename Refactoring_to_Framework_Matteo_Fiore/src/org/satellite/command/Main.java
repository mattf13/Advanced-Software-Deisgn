package org.satellite.command;

import org.satellite.command.postprocessor.CompositePostProcessor;
import org.satellite.command.postprocessor.Compressor;
import org.satellite.command.postprocessor.Encryptor;
import org.satellite.command.postprocessor.PostProcessor;

public class Main {
    public static void main(String[] args) {
        //TurnCommand command = new TurnCommand("SLOW", 180, 60, 45);
        ForwardCommand forwardCommand = new ForwardCommand("FULL", 1000, "SECRET");
        PropertiesSerializer sps = new PropertiesSerializer(new Compressor());
        sps.generateFile("annotationcommand.zip", forwardCommand);
        //compressed + properties

        /*XMLSerializer cxs = new XMLSerializer(new Compressor());
        cxs.generateFile("command.zip", command);

        PropertiesSerializer sps = new PropertiesSerializer(new Encryptor(5));
        sps.generateFile("command.txt", command);
*/
    }
}