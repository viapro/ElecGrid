package com.viapro.elec.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.struts2.ServletActionContext;

public class FileUploadUtil {

	/**
	 * @Name:saveFile
	 * @Description:
	 * @Author:ViaPro
	 * @Version:V1.00
	 * @Create Date:2013-10-7 AM10:22:55
	 * @Parameters:
	 * @Return:String
	 */
	public static String saveFile(File upload) {
		String basePath = ServletActionContext.getServletContext().getRealPath("/upload");
		String datePath = new SimpleDateFormat("\\yyyy\\MM\\dd\\").format(new Date());
		String fileName = UUID.randomUUID().toString()+".doc";
		File directory = new File(basePath+datePath);
		if(!directory.exists()) directory.mkdirs();
		File targetFile = new File(basePath+datePath+fileName);
		upload.renameTo(targetFile);
		return targetFile.getAbsolutePath();
	}

}
