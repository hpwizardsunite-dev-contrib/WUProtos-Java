package org.wuprotos.protos;

import WUProtos.Data.GameDataWrapperOuterClass.GameDataWrapper;
import com.google.protobuf.util.JsonFormat;

import java.io.*;


public class GameDataWrapperReader {

    public GameDataWrapper read(InputStream is) throws Exception {
        if (is == null) {
            throw new IllegalArgumentException("File not found");
        }
        GameDataWrapper response = GameDataWrapper.parseFrom(is);
        System.out.println("Templates Read Success! Count : " + response.getMessagesCount());
        return response;
    }
    public void writeJSON(GameDataWrapper response, OutputStream os) throws IOException {
        JsonFormat.Printer printer = JsonFormat.printer();
        try (OutputStreamWriter writer = new OutputStreamWriter(os)) {
            printer.appendTo(response, writer);
        }
    }

    public static void main(String... args) throws Exception {
        String inputFile = "GameDataWrapper.bytes";
        String outputFile="GameDataWrapper.json";
        if (args.length != 2) {
            System.out.println("java -jar WUProtos-Java.jar [inputfile] [outputfile]");
            System.out.println("Using default input and output files.");
        } else {
            inputFile = args[0];
            outputFile=args[1];
        }
        GameDataWrapperReader reader = new GameDataWrapperReader();
        try (FileInputStream fis = new FileInputStream(inputFile)) {
            GameDataWrapper response = reader.read(fis);
            try (FileOutputStream fos = new FileOutputStream(outputFile)) {
                reader.writeJSON(response, fos);
            }
        }
    }
}
