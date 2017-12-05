package edu.msudenver;

/**
 * Object which extend Command can allow the Artisan Code Quality Extension to invoke operations
 * from Tools without knowing any of tools' interfaces.
 * 
 * @author Artisan Software
 * @version 0.1.0
 */
public abstract class Command {

  public abstract void execute();
}
