package br.edu.ifpb.foodstore.service.log.bridge.abstraction;

public interface LogService {
    public void debug(String message);
    public void info(String message);
    public void error(String message);
}
