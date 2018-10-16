package com.coreservlets.doubletranslate;


public class Translator {
  private String apiKey, sourceLanguage, targetLanguage, textToTranslate, 
                 firstTranslation, secondTranslation;
  
  public Translator(String apiKey, String sourceLanguage,
                    String targetLanguage, String textToTranslate) {
    this.apiKey = apiKey;
    this.sourceLanguage = sourceLanguage;
    this.targetLanguage = targetLanguage;
    this.textToTranslate = textToTranslate;
  }

  public String getApiKey() {
    return(apiKey);
  }

  public String getSourceLanguage() {
    return(sourceLanguage);
  }

  public String getTargetLanguage() {
    return(targetLanguage);
  }

  public String getTextToTranslate() {
    return(textToTranslate);
  }

  public synchronized String getFirstTranslation() {
    if (firstTranslation == null) {
      firstTranslation = 
        TranslateUtils.translationString(apiKey, sourceLanguage, targetLanguage, textToTranslate);
    }
    return(firstTranslation);
  }

  public synchronized String getSecondTranslation() {
    if (secondTranslation == null) {
      String firstTranslation = getFirstTranslation(); // In case someone calls getSecondTranslation before getFirstTranslation
      if (TranslateUtils.isErrorMessage(firstTranslation)) {
        secondTranslation = firstTranslation; // Don't try to translate the error message; just reuse it instead
      } else {
        secondTranslation =
          TranslateUtils.translationString(apiKey, targetLanguage, sourceLanguage, firstTranslation);
      }
    }
    return(secondTranslation);
  }
}
