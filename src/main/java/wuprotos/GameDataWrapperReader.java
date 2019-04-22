package wuprotos;

import com.google.protobuf.util.JsonFormat;
import WUProtos.Data.Client.ClientGameDataWrapperOuterClass.ClientGameDataWrapper;
import WUProtos.Networking.Responses.DownloadGmTemplatesResponseOuterClass.DownloadGmTemplatesResponse;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;


public class GameDataWrapperReader {

    public ClientGameDataWrapper read() throws Exception {
        try (InputStream is = getClass().getResourceAsStream("/GameDataWrapper.bytes")) {
            ClientGameDataWrapper response = ClientGameDataWrapper.parseFrom(is);
            System.out.println("Templates Read Success! Count : " + response.getMessagesCount());
            return response;
        }
    }
    public void writeJSON(ClientGameDataWrapper response, OutputStream os) throws IOException {
        JsonFormat.Printer printer = JsonFormat.printer();
        try (OutputStreamWriter writer = new OutputStreamWriter(os)) {
            printer.appendTo(response, writer);
        }
    }

    public static void main(String... args) throws Exception {
        GameDataWrapperReader reader = new GameDataWrapperReader();
        ClientGameDataWrapper response = reader.read();
        reader.writeJSON(response, System.out);

    }
}
