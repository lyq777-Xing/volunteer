package utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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

    public void removeLineInFile(String filePath, int excludeLineNumber) throws IOException {
        try {
            // 创建BufferedReader对象
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            List<String> lines = new ArrayList<>();
            // 逐行读取文件内容
            String line;
            while ((line = reader.readLine()) != null) {
                // 将每一行内容添加到ArrayList中
                lines.add(line);
            }
            // 关闭文件流
            reader.close();
            lines.remove(excludeLineNumber);
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            // 逐行写入文件内容
            for (String l : lines) {
                writer.write(l);
                writer.newLine();
            }
            // 关闭文件流
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
