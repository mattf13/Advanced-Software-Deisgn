package org.satellite.command.postprocessor;

public interface PostProcessor {
   public byte[] postProcess(byte[] bytes) throws Exception;

}
