package org.satellite.command.postprocessor;

import java.io.ByteArrayOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Compressor implements PostProcessor{
    @Override
    public byte[] postProcess(byte[] bytes) throws Exception {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ZipOutputStream out = new ZipOutputStream(byteOut);
        out.putNextEntry(new ZipEntry("internal"));
        out.write(bytes);
        out.closeEntry();
        return byteOut.toByteArray();
    }
}
