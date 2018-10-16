package com.coreservlets.doubletranslate;


import java.io.*;
import java.net.URLEncoder;
import java.util.*;

import org.apache.http.client.*;
import org.json.*;

/** Some high-level utilities for getting translations using
 *  <a href="http://code.google.com/apis/language/translate/v2/using_rest.html">the RESTful
 *  interface to version 2 of the Google Translate API</a>.
 *  <p>
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on JSF 2.0, Ajax, Android, GWT, servlets,
 *  JSP, Spring, Hibernate/JPA, REST, and Java programming</a>.
 */

public class TranslateUtils {
  public static final String GOOGLE_TRANSLATE_TEMPLATE =
    "https://www.googleapis.com/language/translate/v2?key=%s&source=%s&target=%s&q=%s";
  public static final String GOOGLE_SUPPORTED_LANGUAGE_LIST_TEMPLATE =
    "https://www.googleapis.com/language/translate/v2/languages?key=%s&target=%s";
  public static final String ERROR_FORBIDDEN =
    "Translation not accepted by Google. Either the API key is invalid or " +
    "the app has exceeded Google's 100K character/day limit. To avoid the limit, " +
    "instead of running my app directly, download the source code from the " +
    "tutorial at http://www.coreservlets.com/ and put in your own API key.";
  public static final String ERROR_BAD_REQUEST =
    "Bad request. This probably means that you supplied a source language or " +
    "target language that Google does not support. For languages currently supported, see " +
    "http://code.google.com/apis/language/translate/v2/using_rest.html#supported-languages " +
    "Another possible cause is that Google changed the format of the request URL since this app " +
    "was built.";
  public static final String ERROR_BAD_CONNECTION =
    "Error connecting to code.google.com. Check your internet connection.";
  public static final String ERROR_JSON_PROBLEM =
    "Error parsing JSON result from Google. Perhaps Google changed the format of the " +
    "response since this app was last built? For the latest format of the response, check " +
    "http://code.google.com/apis/language/translate/v2/using_rest.html";
  private static final String[] ERROR_MESSAGE_ARRAY = 
    { ERROR_FORBIDDEN, ERROR_BAD_REQUEST, ERROR_BAD_CONNECTION, ERROR_JSON_PROBLEM };
  private static final List<String> ERROR_MESSAGE_LIST = Arrays.asList(ERROR_MESSAGE_ARRAY);
  
  /** Builds a URL for the <a href="http://code.google.com/apis/language/translate/v2/using_rest.html">the RESTful
   *  interface to version 2 of the Google Translate API</a> out of its constituent pieces.
   * 
   * @param apiKey the API key. To get your own, apply at <a href="http://code.google.com/apis/console">http://code.google.com/apis/console</a>
   * @param sourceLanguage the <a href="http://www.loc.gov/standards/iso639-2/php/langcodes-search.php">ISO 639.2 code</a> for the language
   *                       of the text to be translated. See <a href="http://code.google.com/apis/language/translate/v2/using_rest.html#supported-languages">
   *                       the Google API</a> for the languages currently supported.
   * @param targetLanguage the <a href="http://www.loc.gov/standards/iso639-2/php/langcodes-search.php">ISO 639.2 code</a> for the language
   *                       of the translation result. See <a href="http://code.google.com/apis/language/translate/v2/using_rest.html#supported-languages">
   *                       the Google API</a> for the languages currently supported.
   * @param textToTranslate the URL-encoded text to be translated
   * @return a String representing a JSON object with the translation inside.
   */
  
  public static String makeTranslateUrl(String apiKey, String sourceLanguage, 
                                        String targetLanguage, String textToTranslate) {
    // UTF-8 is a known good encoding type, so URLEncoder.encode will never actually throw UnsupportedEncodingException
    try {
      String address =
        String.format(GOOGLE_TRANSLATE_TEMPLATE, apiKey, sourceLanguage, targetLanguage, 
                      URLEncoder.encode(textToTranslate, "UTF-8"));
      return(address);
    } catch(UnsupportedEncodingException uee) {
      return(null);
    }
  }
  
  public static String translationJson(String apiKey, String sourceLanguage, 
                                       String targetLanguage, String textToTranslate) 
      throws IOException, ClientProtocolException {
    String url = makeTranslateUrl(apiKey, sourceLanguage, targetLanguage, textToTranslate);
    return(HttpUtils.urlContent(url));
  }
  
  public static String translationString(String apiKey, String sourceLanguage,
                                         String targetLanguage, String textToTranslate) {
    try {
      String jsonString = translationJson(apiKey, sourceLanguage, targetLanguage, textToTranslate);
      JSONObject jsonObject = new JSONObject(jsonString);
      String text =
        jsonObject.getJSONObject("data").getJSONArray("translations").getJSONObject(0).getString("translatedText");
      return(text);
    } catch(HttpResponseException hre) { 
      int errorCode = hre.getStatusCode();
      if (errorCode == 403) { // 403 is "Forbidden"
        return(ERROR_FORBIDDEN);
      } else { // Probably 400, "Bad Request"
        return(ERROR_BAD_REQUEST);
      }
    } catch(IOException ioe) {
      return(ERROR_BAD_CONNECTION);
    } catch(JSONException jse) {
      return(ERROR_JSON_PROBLEM);
    }
  }
  
  public static boolean isErrorMessage(String text) {
    return(ERROR_MESSAGE_LIST.contains(text));
  }
  
  /** The address used to get a JSON list of languages supported by Google Translate. */
  
  public static String makeSupportedLanguageListUrl(String apiKey, String targetLanguage) {
    String address =
      String.format(GOOGLE_SUPPORTED_LANGUAGE_LIST_TEMPLATE, apiKey,  targetLanguage); 
    return(address);
  }
  
  /** List of languages supported by Google Translate, with language names in the language specified by the targetLanguage code. */
  
  public static List<Language> supportedLanguages(String apiKey, String targetLanguage) {
    List<Language> availableLanguages = new ArrayList<Language>();
    try {
      String url = makeSupportedLanguageListUrl(apiKey, targetLanguage);
      String jsonString = HttpUtils.urlContent(url);
      JSONObject jsonObject = new JSONObject(jsonString);
      JSONArray jsonLanguages =
        jsonObject.getJSONObject("data").getJSONArray("languages");
      for(int i=0; i<jsonLanguages.length(); i++) {
        JSONObject jsonLanguage = jsonLanguages.getJSONObject(i);
        Language language = 
          new Language(jsonLanguage.getString("name"), jsonLanguage.getString("language"));
        availableLanguages.add(language);
      }
    } catch(Exception e) { 
      // Forbidden, Bad request, IOException, JSONException: return partial or empty language list
    }
    return(availableLanguages);
  }
  
  /** List of languages supported by Google Translate, with language names in English. */
  
  public static List<Language> supportedLanguages(String apiKey) {
    return(supportedLanguages(apiKey, "en"));
  }
  
  private TranslateUtils() {} // Uninstantiatable class: static methods only
}
