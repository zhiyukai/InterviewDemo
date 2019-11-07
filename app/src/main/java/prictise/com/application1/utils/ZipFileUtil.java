package prictise.com.application1.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * @Author zhisiyi
 * @Date 2019.11.07 15:45
 * @Comment
 */
public class ZipFileUtil {

	private static final int buffer = 2048;

	// 将指定的zip文件解压到指定目录下，其中：zipName：文件名，targetDirName：需解压到的目录
	public static void upzipFile(String zipName, String targetDirName) {
		if (!targetDirName.endsWith(File.separator)) {
			targetDirName += File.separator;
		}
		FileOutputStream os = null;
		InputStream is = null;

		try {
			// 根据zip文件创建ZipFile对象，此类的作用是从zip文件读取条目
			ZipFile zipFile = new ZipFile(zipName);
			ZipEntry zn = null;
			String entryName = null;
			String targetFileName = null;
			byte[] buffer = new byte[4096];
			int bytes_read;
			Enumeration entrys = zipFile.entries(); // 获取ZIP文件里所有的文件条目的名字
			while (entrys.hasMoreElements()) { // 循环遍历所有的文件条目的名字
				zn = (ZipEntry) entrys.nextElement();
				entryName = zn.getName(); // 获得每一条文件的名字
				targetFileName = targetDirName + entryName;
				if (zn.isDirectory()) {
					new File(targetFileName).mkdirs(); // 如果zn是一个目录，则创建目录
					continue;
				} else {
					new File(targetFileName).getParentFile().mkdirs();// 如果zn是文件，则创建父目录
				}
				File targetFile = new File(targetFileName); // 否则创建文件
				System.out.println("正在创建文件：" + targetFile.getAbsolutePath());
				os = new FileOutputStream(targetFile);// 打开文件输出流
				is = zipFile.getInputStream(zn); // 从ZipFile对象中打开entry的输入流
				while ((bytes_read = is.read(buffer)) != -1) {
					os.write(buffer, 0, bytes_read);
				}
			}
			System.out.println("解压缩" + zipName + "成功！");
		} catch (IOException err) {
			System.err.println("解压缩" + zipName + "失败: " + err);
		} finally {
			try {
				os.close(); // 关闭流
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void unZip(String path) {
		int count = -1;
		String savepath = "";

		File file = null;
		InputStream is = null;
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;

		savepath = path.substring(0, path.lastIndexOf(".")) + File.separator; // 保存解压文件目录
		new File(savepath).mkdir(); // 创建保存目录
		ZipFile zipFile = null;
		try {
			zipFile = null;
			// new ZipFile(path, Charset.defaultCharset()); //解决中文乱码问题
			Enumeration<?> entries = null;
			// zipFile.getEntries();

			while (entries.hasMoreElements()) {
				byte buf[] = new byte[buffer];

				ZipEntry entry = (ZipEntry) entries.nextElement();

				String filename = entry.getName();
				boolean ismkdir = false;
				if (filename.lastIndexOf("/") != -1) { // 检查此文件是否带有文件夹
					ismkdir = true;
				}
				filename = savepath + filename;

				if (entry.isDirectory()) { // 如果是文件夹先创建
					file = new File(filename);
					file.mkdirs();
					continue;
				}
				file = new File(filename);
				if (!file.exists()) { // 如果是目录先创建
					if (ismkdir) {
						new File(filename.substring(0, filename.lastIndexOf("/"))).mkdirs(); // 目录先创建
					}
				}
				file.createNewFile(); // 创建文件

				is = zipFile.getInputStream(entry);
				fos = new FileOutputStream(file);
				bos = new BufferedOutputStream(fos, buffer);

				while ((count = is.read(buf)) > -1) {
					bos.write(buf, 0, count);
				}
				bos.flush();
				bos.close();
				fos.close();

				is.close();
			}

			zipFile.close();

		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				if (bos != null) {
					bos.close();
				}
				if (fos != null) {
					fos.close();
				}
				if (is != null) {
					is.close();
				}
				if (zipFile != null) {
					zipFile.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private static final int BUFF_SIZE = 1024 * 1024; // 1M Byte

	/**
	 * 批量压缩文件（夹）
	 *
	 * @param resFileList
	 *            要压缩的文件（夹）列表
	 * @param zipFile
	 *            生成的压缩文件
	 * @throws IOException
	 *             当压缩过程出错时抛出
	 */
	public static void zipFiles(Collection<File> resFileList, File zipFile) throws IOException {
		ZipOutputStream zipout = new ZipOutputStream(
				new BufferedOutputStream(new FileOutputStream(zipFile), BUFF_SIZE));
		for (File resFile : resFileList) {
			zipFile(resFile, zipout, "");
		}
		zipout.close();
	}

	/**
	 * 批量压缩文件（夹）
	 *
	 * @param resFileList
	 *            要压缩的文件（夹）列表
	 * @param zipFile
	 *            生成的压缩文件
	 * @param comment
	 *            压缩文件的注释
	 * @throws IOException
	 *             当压缩过程出错时抛出
	 */
	public static void zipFiles(Collection<File> resFileList, File zipFile, String comment) throws IOException {
		ZipOutputStream zipout = new ZipOutputStream(
				new BufferedOutputStream(new FileOutputStream(zipFile), BUFF_SIZE));
		for (File resFile : resFileList) {
			zipFile(resFile, zipout, "");
		}
		zipout.setComment(comment);
		zipout.close();
	}

	/**
	 * 解压缩一个文件
	 *
	 * @param zipFile
	 *            压缩文件
	 * @param folderPath
	 *            解压缩的目标目录
	 * @throws IOException
	 *             当解压缩过程出错时抛出
	 */
	public static void upZipFile(File zipFile, String folderPath) throws ZipException, IOException {
		File desDir = new File(folderPath);
		if (!desDir.exists()) {
			desDir.mkdirs();
		}
		ZipFile zf = new ZipFile(zipFile);
		for (Enumeration<?> entries = zf.entries(); entries.hasMoreElements();) {
			ZipEntry entry = ((ZipEntry) entries.nextElement());
			if (entry.isDirectory()) {

				continue;
			}
			InputStream in = zf.getInputStream(entry);
			String str = folderPath + File.separator + entry.getName();
			str = new String(str.getBytes(), "utf-8");
			File desFile = new File(str);
			if (!desFile.exists()) {
				File fileParentDir = desFile.getParentFile();
				if (!fileParentDir.exists()) {
					fileParentDir.mkdirs();
				}
				desFile.createNewFile();
			}
			OutputStream out = new FileOutputStream(desFile);
			byte buffer[] = new byte[BUFF_SIZE];
			int realLength;
			while ((realLength = in.read(buffer)) > 0) {
				out.write(buffer, 0, realLength);
			}
			in.close();
			out.close();
		}
	}

	/**
	 * 解压文件名包含传入文字的文件
	 *
	 * @param zipFile
	 *            压缩文件
	 * @param folderPath
	 *            目标文件夹
	 * @param nameContains
	 *            传入的文件匹配名
	 * @throws ZipException
	 *             压缩格式有误时抛出
	 * @throws IOException
	 *             IO错误时抛出
	 */
	public static ArrayList<File> upZipSelectedFile(File zipFile, String folderPath, String nameContains)
			throws ZipException, IOException {
		ArrayList<File> fileList = new ArrayList<File>();

		File desDir = new File(folderPath);
		if (!desDir.exists()) {
			desDir.mkdir();
		}

		ZipFile zf = new ZipFile(zipFile);
		for (Enumeration<?> entries = zf.entries(); entries.hasMoreElements();) {
			ZipEntry entry = ((ZipEntry) entries.nextElement());
			if (entry.getName().contains(nameContains)) {
				InputStream in = zf.getInputStream(entry);
				String str = folderPath + File.separator + entry.getName();
				str = new String(str.getBytes("utf-8"), "gbk");
				// str.getBytes("GB2312"),"8859_1" 输出
				// str.getBytes("8859_1"),"GB2312" 输入
				File desFile = new File(str);
				if (!desFile.exists()) {
					File fileParentDir = desFile.getParentFile();
					if (!fileParentDir.exists()) {
						fileParentDir.mkdirs();
					}
					desFile.createNewFile();
				}
				OutputStream out = new FileOutputStream(desFile);
				byte buffer[] = new byte[BUFF_SIZE];
				int realLength;
				while ((realLength = in.read(buffer)) > 0) {
					out.write(buffer, 0, realLength);
				}
				in.close();
				out.close();
				fileList.add(desFile);
			}
		}
		return fileList;
	}

	/**
	 * 获得压缩文件内文件列表
	 *
	 * @param zipFile
	 *            压缩文件
	 * @return 压缩文件内文件名称
	 * @throws ZipException
	 *             压缩文件格式有误时抛出
	 * @throws IOException
	 *             当解压缩过程出错时抛出
	 */
	public static ArrayList<String> getEntriesNames(File zipFile) throws ZipException, IOException {
		ArrayList<String> entryNames = new ArrayList<String>();
		Enumeration<?> entries = getEntriesEnumeration(zipFile);
		while (entries.hasMoreElements()) {
			ZipEntry entry = ((ZipEntry) entries.nextElement());
			entryNames.add(new String(getEntryName(entry).getBytes("GB2312"), "8859_1"));
		}
		return entryNames;
	}

	/**
	 * 获得压缩文件内压缩文件对象以取得其属性
	 *
	 * @param zipFile
	 *            压缩文件
	 * @return 返回一个压缩文件列表
	 * @throws ZipException
	 *             压缩文件格式有误时抛出
	 * @throws IOException
	 *             IO操作有误时抛出
	 */
	public static Enumeration<?> getEntriesEnumeration(File zipFile) throws ZipException, IOException {
		ZipFile zf = new ZipFile(zipFile);
		return zf.entries();

	}

	/**
	 * 取得压缩文件对象的注释
	 *
	 * @param entry
	 *            压缩文件对象
	 * @return 压缩文件对象的注释
	 */
	public static String getEntryComment(ZipEntry entry) throws UnsupportedEncodingException {
		return new String(entry.getComment().getBytes("GB2312"), "8859_1");
	}

	/**
	 * 取得压缩文件对象的名称
	 *
	 * @param entry
	 *            压缩文件对象
	 * @return 压缩文件对象的名称
	 */
	public static String getEntryName(ZipEntry entry) throws UnsupportedEncodingException {
		return new String(entry.getName().getBytes("GB2312"), "8859_1");
	}

	/**
	 * 压缩文件
	 *
	 * @param resFile
	 *            需要压缩的文件（夹）
	 * @param zipout
	 *            压缩的目的文件
	 * @param rootpath
	 *            压缩的文件路径
	 * @throws FileNotFoundException
	 *             找不到文件时抛出
	 * @throws IOException
	 *             当压缩过程出错时抛出
	 */
	private static void zipFile(File resFile, ZipOutputStream zipout, String rootpath)
			throws FileNotFoundException, IOException {
		rootpath = rootpath + (rootpath.trim().length() == 0 ? "" : File.separator) + resFile.getName();
		rootpath = new String(rootpath.getBytes(), "utf-8");
		if (resFile.isDirectory()) {
			File[] fileList = resFile.listFiles();
			for (File file : fileList) {
				zipFile(file, zipout, rootpath);
			}
		} else {
			byte buffer[] = new byte[BUFF_SIZE];
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(resFile), BUFF_SIZE);
			zipout.putNextEntry(new ZipEntry(rootpath));
			int realLength;
			while ((realLength = in.read(buffer)) != -1) {
				zipout.write(buffer, 0, realLength);
			}
			in.close();
			zipout.flush();
			zipout.closeEntry();
		}
	}

	// 第二种实现
	public static void zip(String src, String dest) throws IOException {
		// 提供了一个数据项压缩成一个ZIP归档输出流
		ZipOutputStream out = null;
		try {

			// DirTraversal.makeRootDirectory(dest);
			// File outFile = DirTraversal.getFilePath(dest,"cache.zip");

			File outFile = new File(dest);// 源文件或者目录
			File fileOrDirectory = new File(src);// 压缩文件路径
			out = new ZipOutputStream(new FileOutputStream(outFile));
			// 如果此文件是一个文件，否则为false。
			if (fileOrDirectory.isFile()) {
				zipFileOrDirectory(out, fileOrDirectory, "");
			} else {
				// 返回一个文件或空阵列。
				File[] entries = fileOrDirectory.listFiles();
				for (int i = 0; i < entries.length; i++) {
					// 递归压缩，更新curPaths
					zipFileOrDirectory(out, entries[i], "");
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			// 关闭输出流
			if (out != null) {
				try {
					out.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	private static void zipFileOrDirectory(ZipOutputStream out, File fileOrDirectory, String curPath)
			throws IOException {
		// 从文件中读取字节的输入流
		FileInputStream in = null;
		try {
			// 如果此文件是一个目录，否则返回false。
			if (!fileOrDirectory.isDirectory()) {
				// 压缩文件
				byte[] buffer = new byte[4096];
				int bytes_read;
				in = new FileInputStream(fileOrDirectory);
				// 实例代表一个条目内的ZIP归档
				ZipEntry entry = new ZipEntry(curPath + fileOrDirectory.getName());
				// 条目的信息写入底层流
				out.putNextEntry(entry);
				while ((bytes_read = in.read(buffer)) != -1) {
					out.write(buffer, 0, bytes_read);
				}
				out.closeEntry();
			} else {
				// 压缩目录
				File[] entries = fileOrDirectory.listFiles();
				for (int i = 0; i < entries.length; i++) {
					// 递归压缩，更新curPaths
					zipFileOrDirectory(out, entries[i], curPath + fileOrDirectory.getName() + "/");
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
			// throw ex;
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	public static void unzip(String zipFileName, String outputDirectory) throws IOException {
		ZipFile zipFile = null;
		try {
			zipFile = new ZipFile(zipFileName);
			Enumeration e = zipFile.entries();
			ZipEntry zipEntry = null;
			File dest = new File(outputDirectory);
			dest.mkdirs();
			while (e.hasMoreElements()) {
				zipEntry = (ZipEntry) e.nextElement();
				String entryName = zipEntry.getName();
				InputStream in = null;
				FileOutputStream out = null;
				try {
					if (zipEntry.isDirectory()) {
						String name = zipEntry.getName();
						name = name.substring(0, name.length() - 1);
						File f = new File(outputDirectory + File.separator + name);
						f.mkdirs();
					} else {
						int index = entryName.lastIndexOf("\\");
						if (index != -1) {
							File df = new File(outputDirectory + File.separator + entryName.substring(0, index));
							df.mkdirs();
						}
						index = entryName.lastIndexOf("/");
						if (index != -1) {
							File df = new File(outputDirectory + File.separator + entryName.substring(0, index));
							df.mkdirs();
						}
						File f = new File(outputDirectory + File.separator + zipEntry.getName());
						// f.createNewFile();
						in = zipFile.getInputStream(zipEntry);
						out = new FileOutputStream(f);
						int c;
						byte[] by = new byte[1024];
						while ((c = in.read(by)) != -1) {
							out.write(by, 0, c);
						}
						out.flush();
					}
				} catch (IOException ex) {
					ex.printStackTrace();
					throw new IOException("解压失败：" + ex.toString());
				} finally {
					if (in != null) {
						try {
							in.close();
						} catch (IOException ex) {
						}
					}
					if (out != null) {
						try {
							out.close();
						} catch (IOException ex) {
						}
					}
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
			throw new IOException("解压失败：" + ex.toString());
		} finally {
			if (zipFile != null) {
				try {
					zipFile.close();
				} catch (IOException ex) {
				}
			}
		}
	}

	public static void readZipFile(String file) throws Exception {
		ZipFile zf = new ZipFile(file);
		InputStream in = new BufferedInputStream(new FileInputStream(file));
		ZipInputStream zin = new ZipInputStream(in);
		ZipEntry ze;
		while ((ze = zin.getNextEntry()) != null) {
			if (ze.isDirectory()) {
			} else {
				// System.err.println("file - " + ze.getName() + " : "
				// + ze.getSize() + " bytes");
				String zeName = new String(ze.getName().getBytes("iso-8859-1"), "utf-8");
				System.out.println("获取文件名称：" + zeName);
				long size = ze.getSize();
				if (size > 0) {
					BufferedReader br = new BufferedReader(new InputStreamReader(zf.getInputStream(ze)));
					String line;
					while ((line = br.readLine()) != null) {
						// System.out.println(line);
					}
					br.close();
				}
			}
		}
		zin.closeEntry();
	}

	public static void unzipFileIntoDirectory(File archive, File destinationDir) throws Exception {
		final int BUFFER_SIZE = 1024;
		BufferedOutputStream dest = null;
		FileInputStream fis = new FileInputStream(archive);
		ZipInputStream zis = new ZipInputStream(new BufferedInputStream(fis));
		ZipEntry entry;
		// File destFile;
		while ((entry = zis.getNextEntry()) != null) {
			// destFile = FilesystemUtils.combineFileNames(destinationDir,
			// entry.getName());
			if (entry.isDirectory()) {
				// destFile.mkdirs();
				continue;
			} else {
				int count;
				byte data[] = new byte[BUFFER_SIZE];
				// destFile.getParentFile().mkdirs();
				FileOutputStream fos = new FileOutputStream(destinationDir);
				dest = new BufferedOutputStream(fos, BUFFER_SIZE);
				while ((count = zis.read(data, 0, BUFFER_SIZE)) != -1) {
					dest.write(data, 0, count);
				}
				dest.flush();
				dest.close();
				fos.close();
			}
		}
		zis.close();
		fis.close();
	}

	public static String ungzip(String compressedStr) {
		if (compressedStr == null) {
			return null;
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ByteArrayInputStream in = null;
		GZIPInputStream ginzip = null;
		byte[] compressed = null;
		String decompressed = null;
		try {
			compressed = compressedStr.getBytes("ISO-8859-1");
			in = new ByteArrayInputStream(compressed);
			ginzip = new GZIPInputStream(in);
			byte[] buffer = new byte[1024];
			int offset = -1;
			while ((offset = ginzip.read(buffer)) != -1) {
				out.write(buffer, 0, offset);
			}
			out.flush();
			decompressed = out.toString();
			if (ginzip != null)
				ginzip.close();
			if (in != null)
				in.close();
			if (out != null)
				out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return decompressed;
	}

	public static String getPrefix(String path) {
		int index = path.lastIndexOf('.');
		return path.substring(0, index);
	}

	public static void unGzip(String srcPath) {
		unGzip(new File(srcPath));

	}

	public static void unGzip(File src) {

		String path = getPrefix(src.getAbsolutePath());
		GZIPInputStream gzs = null;
		BufferedOutputStream bos = null;
		try {
			gzs = new GZIPInputStream(new FileInputStream(src));
			bos = new BufferedOutputStream(new FileOutputStream(path));

			byte[] buf = new byte[102400];
			int len = -1;
			while ((len = gzs.read(buf)) != -1) {
				bos.write(buf, 0, len);
			}
			bos.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// FileUtil.close(gzs, bos);
			if (gzs != null) {
				try {
					gzs.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}

	}

	public static void mutiputeFiles(String dirPath) {
		if (dirPath != null) {
			File fDir = new File(dirPath);
			if (fDir.isDirectory()) {
				File[] fList = fDir.listFiles();
				int fListLen = fList.length;
				for (int i = 0; i < fListLen; i++) {
					unGzip(fList[i]);
				}
			}
		}
	}

	public static void combindFile(String targetFile, String sourceDir) {
		if (!targetFile.endsWith("csv")) {
			return;
		}
		// 定义输出目录
		BufferedWriter bw = null;
		int folderConut = 0;
		int fileCount = 1;
		try {
			bw = new BufferedWriter(new FileWriter(targetFile));

			// 读取目录下的每个文件或者文件夹，并读取文件的内容写到目标文字中去
			File[] list = new File(sourceDir).listFiles();
			for (File file : list) {
				if (file.isFile()) {
					fileCount++;
					BufferedReader br = new BufferedReader(new FileReader(file));
					String line;
					while ((line = br.readLine()) != null) {
						bw.write(line);
						bw.newLine();
					}
//					bw.write("--------------第" + fileCount + "个文件-------------");
//					bw.newLine();
					bw.flush();
				} else {
					folderConut++;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("输入目录下文件个数为" + fileCount);
		System.out.println("输入目录下文件夹个数为" + folderConut);

	}

	public static void main(String[] args) {
		// String zipName =
		// "C:\\Users/zhishaoju\\Documents\\SunLands_File\\logs\\wangzhaosheng13_20191106\\a.zip";
		// String targetDirName =
		// "C:/Users/zhishaoju/Documents/SunLands_File/logs/wangzhaosheng13_20191106/A";
		// String zipName = "a.zip";
		// String targetDirName = "A/";
		try {
			// readZipFile(zipName);
			// test015();
			String zipName = "C:\\Users/zhishaoju\\Documents\\SunLands_File\\A.zip";
			String tName = "C:\\Users/zhishaoju\\Documents\\SunLands_File\\b";
			// unzipFileIntoDirectory(new File(zipName), new File(tName));
			// unGzip(zipName);
			String sourceDir = "C:\\Users/zhishaoju\\Documents\\SunLands_File\\logs\\wangzhaosheng13_20191106";
			String targetFile = "C:\\Users/zhishaoju\\Documents\\SunLands_File\\logs\\wangzhaosheng13_20191106.csv";
			// mutiputeFiles(sourceDir);

			combindFile(targetFile, sourceDir);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
