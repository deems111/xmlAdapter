package Util;

import java.io.File;
import java.util.List;

public class Utility {
   public static List getConnectionSettings(){
      return null;
    }

   public static boolean fileExist (String  filePath, String fileType) {
      return new File(filePath).isFile() && filePath.endsWith(fileType);
   }
}
