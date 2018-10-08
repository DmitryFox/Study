package utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Dmitry [ ______ ]
 * @version 07.10.2018 19:33
 */
public class FileUtil {
	/**
	 * The method returns the list of files in a directory.
	 *
	 * @param dir The directory.
	 * @param suffix The file name suffix.
	 * @param isRecursive Is include dirs.
	 * @return List of files.
	 */
	public static List<File> getFileList(File dir, String suffix, boolean isRecursive) {
		File[] files = dir.listFiles();
		if (files != null && files.length != 0) {
			List<File> fileList = new ArrayList<>(files.length);
			for (File file : files) {
				if (file.isDirectory() && isRecursive)
					fileList.addAll(getFileList(file, suffix, true));
				else if (file.isFile() && file.getName().endsWith(suffix))
					fileList.add(file);
			}

			return fileList;
		}

		return Collections.emptyList();
	}
}
