package com.myweb.servlet;
import java.io.*;   
import java.text.SimpleDateFormat;   
import java.util.*;   
import javax.servlet.ServletException;   
import javax.servlet.http.*;   
import org.apache.commons.fileupload.FileItem;   
import org.apache.commons.fileupload.FileItemFactory;   
import org.apache.commons.fileupload.disk.DiskFileItemFactory;   
import org.apache.commons.fileupload.servlet.ServletFileUpload;   
public class uploader extends HttpServlet {   
    private static String baseDir;// CKEditor的根目录   

    private static boolean enabled = false;// 是否开启CKEditor上传   

    private static SimpleDateFormat dirFormatter;// 目录命名格式:yyyyMM   
    private static SimpleDateFormat fileFormatter;// 文件命名格式:yyyyMMddHHmmssSSS   
    /**  
     * Servlet初始化方法  
     */  
    public void init() throws ServletException {   
     
        // 格式化目录和文件命名方式   
        dirFormatter = new SimpleDateFormat("yyyyMMdd");   
        fileFormatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");   
        // 从web.xml中获取根目录名称   
        baseDir = getInitParameter("baseDir");   
        // 从web.xml中获取是否可以进行文件上传   
        enabled = (new Boolean(getInitParameter("enabled"))).booleanValue();   
        if (baseDir == null)   
            baseDir = "/UpLoadFiles/";   
        String realBaseDir = getServletContext().getRealPath(baseDir);   
        File baseFile = new File(realBaseDir);   
        if (!baseFile.exists()) {   
            baseFile.mkdirs();   
        }   

    }   
    public void doGet(HttpServletRequest request, HttpServletResponse response)   
            throws ServletException, IOException {   
        doPost(request, response);   
    }   
    public void doPost(HttpServletRequest request, HttpServletResponse response)   
            throws ServletException, IOException {   
     
        response.setContentType("text/html; charset=UTF-8");   
        response.setHeader("Cache-Control", "no-cache");   
        PrintWriter out = response.getWriter();   
        // 从请求参数中获取上传文件的类型：File/Image/Flash   
        String typeStr = request.getParameter("Types");   
        if (typeStr == null) {   
            typeStr = "File";   
        }   
        Date dNow = new Date();   
        // 设定上传文件路径   
        String currentPath = baseDir + typeStr + "/"  
                + dirFormatter.format(dNow);   
        // 获得web应用的上传路径   
        String currentDirPath = getServletContext().getRealPath(currentPath);   
        // 判断文件夹是否存在，不存在则创建   
        File dirTest = new File(currentDirPath);   
        if (!dirTest.exists()) {   
            dirTest.mkdirs();   
        }   
        // 将路径前加上web应用名   
        currentPath = request.getContextPath() + currentPath;   
        // 文件名和文件真实路径   
        String newName = "";   
        String fileUrl = "";   
        if (enabled) {   
            // 使用Apache Common组件中的fileupload进行文件上传   
            FileItemFactory factory = new DiskFileItemFactory();   
            ServletFileUpload upload = new ServletFileUpload(factory);   
            try {   
                List items = upload.parseRequest(request);   
                Map fields = new HashMap();   
                Iterator iter = items.iterator();   
                while (iter.hasNext()) {   
                    FileItem item = (FileItem) iter.next();   
                    if (item.isFormField())   
                        fields.put(item.getFieldName(), item.getString());   
                    else  
                       fields.put(item.getFieldName(), item);   
                }   
                // CEKditor中file域的name值是upload   
                FileItem uplFile = (FileItem) fields.get("upload");   
                // 获取文件名并做处理   
                String fileNameLong = uplFile.getName();  
                fileNameLong = fileNameLong.replace('\\', '/');   
                String[] pathParts = fileNameLong.split("/");   
                String fileName = pathParts[pathParts.length - 1];   
                // 获取文件后缀名
                String ext = getExtension(fileName);   
                // 设置上传文件名   
                fileName = fileFormatter.format(dNow) + "." + ext;   
                // 获取文件名(无扩展名)   
                String nameWithoutExt = getNameWithoutExtension(fileName);   
              
                File pathToSave = new File(currentDirPath, fileName);   
                fileUrl = currentPath + "/" + fileName;   
                  //判断类型是否合法开始
                   if(extIsAllowed(typeStr,ext))
                   {
                        int counter = 1;   
	                    while (pathToSave.exists()) 
	                    {   
	                        newName = nameWithoutExt + "_" + counter + "." + ext;   
	                        fileUrl = currentPath + "/" + newName;   
	                        pathToSave = new File(currentDirPath, newName);   
	                        counter++;   
	                    }   
                    uplFile.write(pathToSave);   
                   }
                   
                    //判断类型是否合法结束
            } catch (Exception ex) {   
            	 System.out.println("类型不匹配");
            	 //callback=3;
            	
            }   
        } else {   
            
        }   
        // CKEditorFuncNum是回调时显示的位置，这个参数必须有   
        String callback = request.getParameter("CKEditorFuncNum");   
        out.println("<script type=\"text/javascript\">");   
        out.println("window.parent.CKEDITOR.tools.callFunction(" + callback   
                + ",'" + fileUrl + "',''" + ")");   
        out.println("</script>");   
        out.flush();   
        out.close();   
       
    }   
    /**  
     * 获取文件名的方法  
     */  
    private static String getNameWithoutExtension(String fileName) {   
        return fileName.substring(0, fileName.lastIndexOf("."));   
    }   
    /**  
     * 获取扩展名的方法  
     */  
    private String getExtension(String fileName) {   
        return fileName.substring(fileName.lastIndexOf(".") + 1);   
    }   
    /**  
     * 字符串像ArrayList转化的方法  
     */  
    private ArrayList stringToArrayList(String str) {   
       
        String[] strArr = str.split("\\|");   
        ArrayList tmp = new ArrayList();   
        if (str.length() > 0) {   
            for (int i = 0; i < strArr.length; ++i) {   
              
                tmp.add(strArr[i].toLowerCase());   
            }   
        }   
        return tmp;   
    }   
    /**  
     * 判断扩展名是否允许的方法  
     */  
    private boolean extIsAllowed(String fileType, String ext) {  
    	//可以根据自己需求自定义
    	boolean fage=false;
    	String strext=getInitParameter("AllowedExtensionsImage");
        String[] strNum=strext.split(",");
        int j=0;
        for(int i=0;i<strNum.length;i++)
        {
        	
        	if(ext.equals(strNum[i]))
        	{
        		j++;
        	}
        }
        System.out.println("J="+j);
        if(j>0)
        {
        	fage=true;
        }
        else
        {
        	fage=false;
        }
    	
    	return fage;
    }
}  
