package utils;

import java.io.*;

/**
 * @author lyq
 * @time 2023/12/30 14:41
 */
public class LineUtils {
    public int getLines(String filePath) throws IOException {
        File file = new File(filePath);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        int lineCount = 0;
        String line;
        while ((line = reader.readLine()) != null) {
            // 统计行数
            lineCount++;
        }
        reader.close();
        return lineCount;
    }
}
