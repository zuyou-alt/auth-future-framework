package auth.future.component.common.utils;

import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class DownloadUtil {
    private static final Logger log = LoggerFactory.getLogger(DownloadUtil.class);

    public static void downloadFileByPath(HttpServletResponse response, String filePath, boolean isOnLine){
        setResponse(response,null,filePath,"","",isOnLine);
        download(response,new File(filePath));
    }

    /**
     * 根据文件路径下载文件
     * @param response 相应体
     * @param filePath 文件路径
     * @param contentType 文件类型
     */
    public static void downloadFileByPath(HttpServletResponse response,String filePath,String contentType,boolean isOnLine){
        setResponse(response,null,filePath,contentType,"",isOnLine);
        download(response,new File(filePath));
    }


    /**
     * 根据文件路径下载文件
     * 指定文件名
     * @param response 相应体
     * @param filePath 文件路径
     * @param contentType 文件类型
     * @param fileName 文件名
     */
    public static void downloadFileByPath(HttpServletResponse response,String filePath,String contentType,String fileName,boolean isOnLine){
        setResponse(response,null,filePath,contentType,fileName,isOnLine);
        download(response,new File(filePath));
    }

    /**
     * 下载文件
     * @param response 相应体
     * @param file 文件路径
     */
    public static void downloadFile(HttpServletResponse response,File file){
        setResponse(response,file,"","","",false);
        download(response,file);
    }

    /**
     * 下载文件
     * @param response 相应体
     * @param file 文件路径
     * @param contentType 文件类型
     */
    public static void downloadFile(HttpServletResponse response,File file,String contentType,boolean isOnLine){
        setResponse(response,file,"",contentType,"",isOnLine);
        download(response,file);
    }


    /**
     * 下载文件 指定文件名
     * @param response 相应体
     * @param file 文件
     * @param contentType 文件类型
     */
    public static void downloadFile(HttpServletResponse response,File file,String contentType,String fileName,boolean isOnLine){
        setResponse(response,file,"",contentType,fileName,isOnLine);
        download(response,file);
    }

    /**
     * 下载文件 指定文件名
     * @param response 相应体
     * @param inputStream 文件
     * @param contentType 文件类型
     */
    public static void downloadFileByStream(HttpServletResponse response,FileInputStream inputStream,String contentType,String fileName,boolean isOnLine){
        setResponse(response,contentType,fileName,isOnLine);
        download(response,inputStream);
    }

    /**
     * 下载文件 指定文件名
     * @param response 相应体
     * @param data 文件字节
     * @param contentType 文件类型
     */
    public static void downloadFileByByte(HttpServletResponse response,byte[] data,String contentType,String fileName,boolean isOnLine){
        setResponse(response,contentType,fileName,isOnLine);
        download(response,data);
    }



    /**
     * 设置请求头
     * @param response 相应对象
     * @param file 文件，如果不传，则使用文件路径加载
     * @param filePath 文件路径
     * @param contentType 文件头
     * @param fileName 文件名称
     */
    private static void setResponse(HttpServletResponse response,File file,String filePath,String contentType,String fileName,boolean isOnLine){
        if (file==null) file = new File(filePath);
        fileName = StringUtils.hasText(fileName) ? fileName: file.getName();
        setResponse(response,contentType,fileName,isOnLine);
    }

    /**
     * 设置请求头
     * @param response 相应对象
     * @param contentType 文件头
     * @param fileName 文件名称
     */
    private static void setResponse(HttpServletResponse response,String contentType,String fileName,boolean isOnLine){
        contentType = StringUtils.hasText(contentType) ? contentType: "application/octet-stream";
        response.setCharacterEncoding("UTF-8");
        response.setContentType(contentType);

        try {
            if (isOnLine){

                response.setHeader("Accept-Ranges","bytes"); //添加这个响应头，视频才可以快进
                response.setHeader("Content-Disposition", "inline;filename=" + URLEncoder.encode(fileName, String.valueOf(StandardCharsets.UTF_8)));
            }else {

                response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, String.valueOf(StandardCharsets.UTF_8)));
            }
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

    }

    private static void download(HttpServletResponse response,File file){
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            download(response,fileInputStream);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private static void download(HttpServletResponse response,byte[] data){
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
        download(response,byteArrayInputStream);
    }

    /** 文件下载工具类 */
    private static void download(HttpServletResponse response,InputStream inputStream){
        byte[] buffer = new byte[1024];
        try {
            BufferedInputStream bis = new BufferedInputStream(inputStream);
            OutputStream os = response.getOutputStream();
            response.setHeader("Content-Length", String.valueOf(inputStream.available())); // 内容长度
            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }
            log.info("File download successfully!");
        } catch (IOException e) {
            log.error("File download failed !",e);
        }
    }
}
