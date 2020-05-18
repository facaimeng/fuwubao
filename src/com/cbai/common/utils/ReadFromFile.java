package com.cbai.common.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.io.Reader;

import java.util.ArrayList;
import java.util.List;

public class ReadFromFile {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// TODO Auto-generated method stub
		String fileName = "F:ssc_1805150616.txt";
        
		// readFileByBytes(fileName);
		// readFileByChars(fileName);
		// readFileByLines(fileName);
		
		List<ResultBean> resultList = readFileByLinesReturnList(fileName);
		System.out.println("数据总条数:" + resultList.size());
		
		int guaCount = 0;
		int zhongCount = 0;
		
		int lianCount = 0;
		
		int louCount = 0;
		
		for(int i=0;i<resultList.size() - 1;i++){
			
			ResultBean beforeNumber = resultList.get(i);
			
			ResultBean affterNumber = resultList.get(i + 1);
			
			int[] beforeNumberArr = new int[5];
			
			beforeNumberArr[0] = beforeNumber.getWan();
			
			//万千不相等取千
			if(beforeNumber.getWan() != beforeNumber.getQian()){
			    beforeNumberArr[1] = beforeNumber.getQian();
			}else{
		        //万千相等取百位填充
				if(beforeNumber.getQian()!=beforeNumber.getBai()){
					beforeNumberArr[1] = beforeNumber.getBai();
				}else{
					//百千相等取十位填充
					if(beforeNumber.getBai()!=beforeNumber.getShi()){
						beforeNumberArr[1] = beforeNumber.getShi();
					}else{
						//十个相等取个位填充
						if(beforeNumber.getBai()!=beforeNumber.getShi()){
							beforeNumberArr[1] = beforeNumber.getGe();
						}else{
							//大豹子的时候随机取一个号来购买
							if(beforeNumber.getGe() != 0){
							    beforeNumberArr[1] = 0;
							}else{
								beforeNumberArr[1] = 1;
							}
						}
					}
				}
			}
			
			
			//beforeNumberArr[2] = beforeNumber.getBai();
			//beforeNumberArr[3] = beforeNumber.getShi();
			//beforeNumberArr[4] = beforeNumber.getGe();
			
			
			int[] affterNumberArr = new int[5];
			affterNumberArr[0] = affterNumber.getWan();
			affterNumberArr[1] = affterNumber.getQian();
			affterNumberArr[2] = affterNumber.getBai();
			affterNumberArr[3] = affterNumber.getShi();
			affterNumberArr[4] = affterNumber.getGe();
			
			
			boolean quanGua = true;
			for(int k = 0; k<2; k++){
				int beforeTemp = beforeNumberArr[k];
				for(int j = 0; j<5; j++){
					int affterTemp = affterNumberArr[j];
					if(beforeTemp == affterTemp){
						zhongCount = zhongCount + 1;
						quanGua = false;
					}
				}
			}
			if(quanGua){
				guaCount = guaCount + 2;
			}
			
		}
		
		System.out.println("挂：" + guaCount + "期");
		System.out.println("中：" + zhongCount + "期");
		
		//readFileByRandomAccess(fileName);
	}

	
	/**
	 * 
	 * 以行为单位读取文件，常用于读面向行的格式化文件
	 * 
	 */
	public static List<ResultBean> readFileByLinesReturnList(String fileName) {

		List<ResultBean> resultList = new ArrayList<ResultBean>();
		
		File file = new File(fileName);

		BufferedReader reader = null;

		try {

			System.out.println("以行为单位读取文件内容，一次读一整行：");

			reader = new BufferedReader(new FileReader(file));

			String tempString = null;

			int line = 1;

			// 一次读入一行，直到读入null为文件结束

			while ((tempString = reader.readLine()) != null) {

				ResultBean result = new ResultBean();
				// 显示行号
				//System.out.println("line " + line + ": " + tempString);
				//line++;
				
				String[] resultArr = tempString.split("-");
				result.setNumber(resultArr[0]);
				String[] numbers = resultArr[1].split(",");
				result.setWan(Integer.valueOf(numbers[0].trim()));
				result.setQian(Integer.valueOf(numbers[1].trim()));
				result.setBai(Integer.valueOf(numbers[2].trim()));
				result.setShi(Integer.valueOf(numbers[3].trim()));
				result.setGe(Integer.valueOf(numbers[4].trim()));
				
				
				resultList.add(result);
			}

			reader.close();

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			if (reader != null) {

				try {

					reader.close();

				} catch (IOException e1) {

				}

			}

		}
		
		return resultList;

	}
	
	
	
	
	
	
	/**
	 * 
	 * 随机读取文件内容
	 * 
	 */

	public static void readFileByRandomAccess(String fileName) {

		RandomAccessFile randomFile = null;

		try {

			System.out.println("随机读取一段文件内容：");

			// 打开一个随机访问文件流，按只读方式

			randomFile = new RandomAccessFile(fileName, "r");

			// 文件长度，字节数

			long fileLength = randomFile.length();

			// 读文件的起始位置

			int beginIndex = (fileLength > 4) ? 0 : 0;

			// 将读文件的开始位置移到beginIndex位置。

			randomFile.seek(beginIndex);

			byte[] bytes = new byte[10];

			int byteread = 0;

			// 一次读10个字节，如果文件内容不足10个字节，则读剩下的字节。

			// 将一次读取的字节数赋给byteread

			while ((byteread = randomFile.read(bytes)) != -1) {

				System.out.write(bytes, 0, byteread);

			}

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			if (randomFile != null) {

				try {

					randomFile.close();

				} catch (IOException e1) {

				}

			}

		}

	}

	/**
	 * 
	 * 以行为单位读取文件，常用于读面向行的格式化文件
	 * 
	 */
	public static void readFileByLines(String fileName) {

		File file = new File(fileName);

		BufferedReader reader = null;

		try {

			System.out.println("以行为单位读取文件内容，一次读一整行：");

			reader = new BufferedReader(new FileReader(file));

			String tempString = null;

			int line = 1;

			// 一次读入一行，直到读入null为文件结束

			while ((tempString = reader.readLine()) != null) {

				// 显示行号

				System.out.println("line " + line + ": " + tempString);

				line++;

			}

			reader.close();

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			if (reader != null) {

				try {

					reader.close();

				} catch (IOException e1) {

				}

			}

		}

	}

	/**
	 * 
	 * 以字符为单位读取文件，常用于读文本，数字等类型的文件
	 * 
	 */
	public static void readFileByChars(String fileName) {

		File file = new File(fileName);

		Reader reader = null;

		try {

			System.out.println("以字符为单位读取文件内容，一次读一个字节：");

			// 一次读一个字符

			reader = new InputStreamReader(new FileInputStream(file));

			int tempchar;

			while ((tempchar = reader.read()) != -1) {

				// 对于windows下，\r\n这两个字符在一起时，表示一个换行。

				// 但如果这两个字符分开显示时，会换两次行。

				// 因此，屏蔽掉\r，或者屏蔽\n。否则，将会多出很多空行。

				if (((char) tempchar) != '\r') {

					System.out.print((char) tempchar);

				}

			}

			reader.close();

		} catch (Exception e) {

			e.printStackTrace();

		}

		try {

			System.out.println("\n以字符为单位读取文件内容，一次读多个字节：");

			// 一次读多个字符

			char[] tempchars = new char[30];

			int charread = 0;

			reader = new InputStreamReader(new FileInputStream(fileName));

			// 读入多个字符到字符数组中，charread为一次读取字符数

			while ((charread = reader.read(tempchars)) != -1) {

				// 同样屏蔽掉\r不显示

				if ((charread == tempchars.length)

				&& (tempchars[tempchars.length - 1] != '\r')) {

					System.out.print(tempchars);

				} else {

					for (int i = 0; i < charread; i++) {

						if (tempchars[i] == '\r') {

							continue;

						} else {

							System.out.print(tempchars[i]);

						}

					}

				}

			}

		} catch (Exception e1) {

			e1.printStackTrace();

		} finally {

			if (reader != null) {

				try {

					reader.close();

				} catch (IOException e1) {

				}

			}

		}

	}

	/**
	 * 
	 * 以字节为单位读取文件，常用于读二进制文件，如图片、声音、影像等文件。
	 * 
	 */

	public static void readFileByBytes(String fileName) {
		File file = new File(fileName);

		InputStream in = null;

		try {

			System.out.println("以字节为单位读取文件内容，一次读一个字节：");

			// 一次读一个字节

			in = new FileInputStream(file);

			int tempbyte;

			while ((tempbyte = in.read()) != -1) {

				System.out.println(tempbyte);

			}

		} catch (Exception e) {

			// TODO: handle exception

			e.printStackTrace();

		}

		try {

			System.out.println("以字节为单位读取文件内容，一次读多个字节：");

			// 一次读多个字节

			byte[] tempbytes = new byte[100];

			int byteread = 0;

			in = new FileInputStream(fileName);

			ReadFromFile.showAvailableBytes(in);

			// 读入多个字节到字节数组中，byteread为一次读入的字节数

			while ((byteread = in.read(tempbytes)) != -1) {

				System.out.write(tempbytes, 0, byteread);// 好方法，第一个参数是数组，第二个参数是开始位置，第三个参数是长度

			}

		} catch (Exception e1) {

			e1.printStackTrace();

		} finally {

			if (in != null) {

				try {

					in.close();

				} catch (IOException e1) {

				}

			}

		}

	}

	/**
	 * 
	 * 显示输入流中还剩的字节数
	 * 
	 */

	private static void showAvailableBytes(InputStream in) {

		try {

			System.out.println("当前字节输入流中的字节数为:" + in.available());

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

}
