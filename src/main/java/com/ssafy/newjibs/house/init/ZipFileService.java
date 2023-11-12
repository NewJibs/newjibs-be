package com.ssafy.newjibs.house.init;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.springframework.stereotype.Service;

@Service
public class ZipFileService {

	public void unzipResourceFile(String zipFilePath, String destDir) {
		File dir = new File(destDir);

		if (!dir.exists())
			dir.mkdirs();

		byte[] buffer = new byte[1024];
		try {
			InputStream fis = getClass().getClassLoader().getResourceAsStream(zipFilePath);
			if (fis == null) {
				throw new RuntimeException("cannot find zip file: " + zipFilePath);
			}
			ZipInputStream zis = new ZipInputStream(fis);
			ZipEntry ze = zis.getNextEntry();
			while (ze != null) {
				String fileName = ze.getName();
				File newFile = new File(destDir + File.separator + fileName);

				// create directory if needed
				new File(newFile.getParent()).mkdirs();

				// write file
				FileOutputStream fos = new FileOutputStream(newFile);
				int len;
				while ((len = zis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}
				fos.close();

				// move to next
				ze = zis.getNextEntry();
			}
			zis.closeEntry();
			zis.close();
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
