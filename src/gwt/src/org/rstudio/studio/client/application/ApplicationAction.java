/*
 * ApplicationAction.java
 *
 * Copyright (C) 2009-12 by RStudio, Inc.
 *
 * Unless you have received this program directly from RStudio pursuant
 * to the terms of a commercial license agreement with RStudio, then
 * this program is licensed to you under the terms of version 3 of the
 * GNU Affero General Public License. This program is distributed WITHOUT
 * ANY EXPRESS OR IMPLIED WARRANTY, INCLUDING THOSE OF NON-INFRINGEMENT,
 * MERCHANTABILITY OR FITNESS FOR A PARTICULAR PURPOSE. Please refer to the
 * AGPL (http://www.gnu.org/licenses/agpl-3.0.txt) for more details.
 *
 */

package org.rstudio.studio.client.application;

import java.util.List;
import java.util.Map;

import org.rstudio.core.client.StringUtil;
import org.rstudio.core.client.URIUtils;

import com.google.gwt.http.client.URL;
import com.google.gwt.user.client.Window;

public class ApplicationAction
{
   public static final String QUIT = "quit";
   public static final String NEW_PROJECT = "new_project";
   public static final String OPEN_PROJECT = "open_project";
   public static final String SWITCH_PROJECT = "switch_project";
   
   
   public static String addAction(String url, String action)
   {
      return URIUtils.addQueryParam(url, ACTION_PARAMETER, action);  
   }
   
   public static boolean hasAction()
   {
      return getAction().length() > 0;
   }
   
   public static boolean isQuit()
   {
      return isAction(QUIT);
   }
   
   public static boolean isNewProject()
   {
      return isAction(NEW_PROJECT);
   }
   
   public static boolean isOpenProject()
   {
      return isAction(OPEN_PROJECT);
   }
   
   public static boolean isSwitchProject()
   {
      return isAction(SWITCH_PROJECT);
   }
   
   public static String getId()
   {
      return StringUtil.notNull(
          Window.Location.getParameter(ID_PARAMETER));
   }
   
   public static String getRemainingQueryString()
   {  
      // get query params and remove our built in ones
      Map<String, List<String>> params = Window.Location.getParameterMap();
      params.remove(ACTION_PARAMETER);
      params.remove(ID_PARAMETER);
      
      // build the query string
      StringBuilder url =  new StringBuilder();
      String prefix = "";
      for (Map.Entry<String, List<String>> entry : params.entrySet()) {
        for (String val : entry.getValue()) {
          url.append(prefix)
              .append(URL.encodeQueryString(entry.getKey()))
              .append('=');
          if (val != null) {
            url.append(URL.encodeQueryString(val));
          }
          prefix = "&";
        }
      }
     
      // return string
      return url.toString();
   }
   
   private static boolean isAction(String action)
   {
      return action.equals(getAction());
   }
   
   private static String getAction()
   {
      return StringUtil.notNull(
          Window.Location.getParameter(ACTION_PARAMETER));
   }
   
   private static final String ACTION_PARAMETER = "action";
   private static final String ID_PARAMETER = "id";
   
}
