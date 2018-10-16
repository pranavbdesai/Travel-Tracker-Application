package com.coreservlets.doubletranslate;

/** Represents a language name (e.g., "Spanish" if the current display
 *  language is English) and the corresponding language code (e.g., "es"). Immutable.
 */

public class Language {
  private final String name, code;

  public Language(String name, String code) {
    this.name = name;
    this.code = code;
  }

  public String getName() {
    return(name);
  }

  public String getCode() {
    return(code);
  }
  
  @Override
  public String toString() {
    return(String.format("%s (%s)", name, code));
  }
}
