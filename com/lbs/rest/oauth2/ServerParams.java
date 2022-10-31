/*    */ package com.lbs.rest.oauth2;
/*    */ 
/*    */ public class ServerParams
/*    */ {
/*  5 */   public static String CLIENT_ID = "oauth2_client_id";
/*  6 */   public static String CLIENT_SECRET = "oauth2_client_secret";
/*    */   
/*    */   public static final String LOGIN_JSP = "/oauthPages/oauthlogin.jsp";
/*    */   
/*    */   public static final String LOGIN_HTML_EMBEDDED_MENU_SERVICE = "/menu/login";
/*    */   
/*    */   public static final String LOGIN_HTML = "/oauthPages/oauthlogin.html";
/*    */   
/*    */   public static final String ACCESS_TOKEN_JSP = "/oauthPages/accesstoken.jsp";
/*    */   
/*    */   public static final String ERROR_JSP = "/oauthPages/oauth_error.jsp";
/*    */   
/*    */   public static final String ERROR_HTML = "/logo/oauthPages/oauth_error.html";
/*    */   
/*    */   public static final String AUTH_REST_URL = "/auth";
/*    */   
/*    */   public static final String AUTH_REST_URL_MENU_SERVICE = "/authmenu";
/*    */   public static final String REDIRECT_REST_URL = "/redirect";
/*    */   public static final String TOKEN_REST_URL = "/token";
/* 25 */   public static final Object TOKEN_VALIDATE = "/validate";
/* 26 */   public static final Object SERVLET_PATH = "/oauth2";
/*    */   public static final String LOGIN_URL = "/login";
/*    */   public static final String LOGOUT_URL = "/logout";
/* 29 */   public static final Object TOKEN_REFRESH = "/refreshToken";
/* 30 */   public static final Object TOKEN_SERVER_TO_SERVER = "/server2server";
/*    */   public static final String OAUTH_RESPONSE_TYPE = "response_type";
/*    */   public static final String OAUTH_CLIENT_ID = "client_id";
/*    */   public static final String OAUTH_CLIENT_SECRET = "client_secret";
/*    */   public static final String OAUTH_REDIRECT_URI = "redirect_uri";
/*    */   public static final String OAUTH_USERNAME = "username";
/*    */   public static final String OAUTH_PASSWORD = "password";
/*    */   public static final String OAUTH_ASSERTION_TYPE = "assertion_type";
/*    */   public static final String OAUTH_ASSERTION = "assertion";
/*    */   public static final String OAUTH_SCOPE = "scope";
/*    */   public static final String OAUTH_STATE = "state";
/*    */   public static final String OAUTH_GRANT_TYPE = "grant_type";
/*    */   public static final String OAUTH_HEADER_NAME = "OAuth";
/*    */   public static final String OAUTH_CODE = "code";
/*    */   public static final String OAUTH_ACCESS_TOKEN = "access_token";
/*    */   public static final String OAUTH_EXPIRES_IN = "expires_in";
/*    */   public static final String OAUTH_REFRESH_TOKEN = "refresh_token";
/*    */   public static final String SESSIONCODE = "sessionCode";
/*    */   public static final String OAUTH_TOKEN = "oauth_token";
/*    */   public static final String OAUTH_TOKEN_DRAFT_0 = "access_token";
/*    */   public static final String OAUTH_VERSION_DIFFER = "oauth_signature_method";
/*    */   public static final String FIRM_NO = "firmNr";
/*    */   public static final String LANGUAGE = "lang";
/*    */   public static final String USER_FULL_NAME = "fullname";
/*    */   public static final String USER_NAME = "username";
/*    */   public static final String USER_EMAIL = "mail";
/*    */   public static final String USER_NR = "usernr";
/*    */   public static final String SERVICEGUID = "serviceGUID";
/*    */   
/*    */   public static final class HttpMethod {
/*    */     public static final String POST = "POST";
/*    */     public static final String GET = "GET";
/*    */     public static final String DELETE = "DELETE";
/*    */     public static final String PUT = "PUT";
/*    */   }
/*    */   
/*    */   public static final class HeaderType {
/*    */     public static final String CONTENT_TYPE = "Content-Type";
/*    */     public static final String WWW_AUTHENTICATE = "WWW-Authenticate";
/*    */     public static final String AUTHORIZATION = "Authorization";
/*    */   }
/*    */   
/*    */   public static final class WWWAuthHeader {
/*    */     public static final String REALM = "realm";
/*    */   }
/*    */   
/*    */   public static final class ContentType {
/*    */     public static final String URL_ENCODED = "application/x-www-form-urlencoded";
/*    */     public static final String JSON = "application/json";
/*    */   }
/*    */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\rest\oauth2\ServerParams.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */