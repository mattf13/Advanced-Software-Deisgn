package org.satellite.command.postprocessor;

public class CompositePostProcessor implements PostProcessor{
    private PostProcessor first;
    private PostProcessor second;

    public CompositePostProcessor(PostProcessor first, PostProcessor second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public byte[] postProcess(byte[] bytes) throws Exception {
        byte[] firstResult = first.postProcess(bytes);
        return second.postProcess(firstResult);
    }
}
