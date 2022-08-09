package ai.ecma.warehouseexam.utils;

import java.io.File;

public class CommonUtils {


    public static File getOutFile(String name) {
        String path = AppConstant.UPLOAD_FILE_PATH + "/" + name;
        return new File(path);
    }
}
