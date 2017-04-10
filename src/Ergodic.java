import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
 * 
 */

/**
 * @author           Administrator
 * @copyright        wgcwgc
 * @date             2017��4��10��
 * @time             ����3:15:52
 * @project_name     sinyuee_tools_excel_hebing
 * @package_name     
 * @file_name        Ergodic.java
 * @type_name        Ergodic
 * @enclosing_type   
 * @tags             
 * @todo             
 * @others           
 *
 */

public class Ergodic
{
	private static String Path = "C:\\Users\\Administrator\\Desktop\\test.xls";
//	private static String Path = "C:\\Users\\Administrator\\Desktop\\��ʳ���-ÿ������-����Դ.xls";
//	private static String ConfigPath = "C:\\Users\\Administrator\\Desktop\\��ʳ���-������������.xls";
//	private static String NewPath = "C:\\Users\\Administrator\\Desktop\\��ʳ���-ÿ������-3��.xls";
	private static String ConfigPath = "C:\\Users\\Administrator\\Desktop\\test.xls";
	private static String NewPath = "C:\\Users\\Administrator\\Desktop\\out.xls";
	
	
//	static String [][] writetable = new String [99999] [57];
//	static String [][][] table = new String [57][99999][57];
	
	/**
	 * @param args
	 */
	public static void main(String [] args)
	{
//		Path = args[0] + "����Դ.xls";
//		NewPath = args[0] + "�ϲ��������.xls";
//		ConfigPath = args[0] + "������������.xls";
		
		jxl.Workbook readwb = null;
		try
		{
			InputStream instream = new FileInputStream(Path);
			readwb = Workbook.getWorkbook(instream);
			int countSheet = readwb.getNumberOfSheets();
			int readSheet = countSheet - 1;
			// Sheet���±��Ǵ�0��ʼ
			// ��ȡ��һ��Sheet��
			String [][][] table = inputMemory(readwb , readSheet);
			String [][] configname = getConfigData(ConfigPath);
			String [][] writetable = new String [readwb.getSheet(0).getRows()] [57];
			System.out.println("**����׼������" + readSheet + "�ű�");
			for(int k = 0 ; k < readSheet ; k ++ )
			{
				Sheet readsheet = readwb.getSheet(k);
				int rsRows = readsheet.getRows();
				int rsColumn = readsheet.getColumns();
				System.out.println("**��ʼ������" + ( k + 1 ));
				switch(k)
				{
					case 0 :
					{
//						for(int i = 3 ; i < rsRows ; i ++ )
//						{
//							
//							writetable[i][0] = getDate(table[k][i][0]);// ����
//							writetable[i][1] = changeNameDataEye(configname ,
//									table[k][i][1]);// ��������
//							writetable[i][2] = table[k][i][2];// �豸����
//							writetable[i][3] = getdatafromtable(table ,
//									table[k][i][0] , 0 , table[k][i][1] , 17 ,
//									21);// �����û���
//							writetable[i][4] = getdatafromtable(table ,
//									table[k][i][0] , 0 , table[k][i][1] , 17 ,
//									18);// ���ո���
//							writetable[i][5] = getdatafromtable(table ,
//									table[k][i][0] , 0 , table[k][i][1] , 7 ,
//									10);// ��������
//							writetable[i][6] = getdatafromtable(table ,
//									table[k][i][0] , 0 , table[k][i][1] , 7 ,
//									12);// ��������
//							writetable[i][7] = getdatafromtable(table ,
//									table[k][i][0] , 0 , table[k][i][1] , 7 ,
//									14);// ��������
//						}
						
						for(int i = 0 ; i < rsRows ; i ++ )
						{
							for(int j = 0 ; j < rsColumn ; j ++ )
							{
								writetable[i][j] = table[k][i][j];
								if( ( 8 == j) && i != 0)
								{
									writetable[i][j] = dealData(table[k][i][j]);
								}
							}
						}
						break;
					}
					case 1 :
					{
						for(int i = 1 ; i < rsRows ; i ++ )
						{
							// System.out.println("���棺��" + (k+1) + "�ű�\t����:" +
							// table[k][i][0] + "\t����:" + table[k][i][6] +
							// "\tû����dataeye���ҵ���");
							String d_time = getDate(table[k][i][0]);// ����
							if(d_time == null)
							{
								System.out.println("���棺��" + ( k + 1 ) + "�ű���"
										+ ( i + 1 ) + "�У�����Ϊ��");
								continue;
							}
							String q_name = changeName(configname ,
									table[k][i][6]);// ��������
							if(q_name == null)
							{
								System.out.println("���棺��" + ( k + 1 ) + "�ű���"
										+ ( i + 1 ) + "�У�������Ϊ��");
								continue;
							}
							int curRow = getCurRowfromWriteTable(writetable ,
									d_time , q_name);
							if(curRow == 0)
							{
								System.out.println("���棺��" + ( k + 1 )
										+ "�ű�\t����:" + table[k][i][0] + "\t����:"
										+ table[k][i][6] + "\tû����dataeye���ҵ���");
							}
							
							writetable[curRow][8] = String
									.valueOf(getNumber(writetable[curRow][8])
											+ getNumber(table[k][i][9]));// ������
							writetable[curRow][9] = String
									.valueOf(getNumber(writetable[curRow][9])
											+ getNumber(table[k][i][10]));// �����û���
							writetable[curRow][10] = String
									.valueOf(getNumber(writetable[curRow][10])
											+ getNumber(table[k][i][11]));// ���Ѵ���
							writetable[curRow][11] = String
									.valueOf(getNumber(writetable[curRow][11])
											+ getNumber(table[k][i][26]));// ��arppu
							
						}
						break;
					}
					case 2 :
					{
						for(int i = 1 ; i < rsRows ; i ++ )
						{
							String d_time = getDate(table[k][i][0]);// ����
							if(d_time == null)
							{
								System.out.println("���棺��" + ( k + 1 ) + "�ű���"
										+ ( i + 1 ) + "�У�����Ϊ��");
								continue;
							}
							String q_name = changeName(configname ,
									table[k][i][3]);// ��������
							if(q_name == null)
							{
								System.out.println("���棺��" + ( k + 1 ) + "�ű���"
										+ ( i + 1 ) + "�У�������Ϊ��");
								continue;
							}
							
							int curRow = getCurRowfromWriteTable(writetable ,
									d_time , q_name);
							if(curRow == 0)
							{
								System.out.println("���棺��" + ( k + 1 )
										+ "�ű�\t����:" + table[k][i][0] + "\t����:"
										+ table[k][i][3] + "\tû����dataeye���ҵ���");
							}
							
							writetable[curRow][12] = String
									.valueOf(getNumber(writetable[curRow][12])
											+ getNumber(table[k][i][8]));// ������
							writetable[curRow][13] = String
									.valueOf(getNumber(writetable[curRow][13])
											+ getNumber(table[k][i][11]));// �����û���
							writetable[curRow][14] = String
									.valueOf(getNumber(writetable[curRow][14])
											+ getNumber(table[k][i][12]));// ���Ѵ���
							writetable[curRow][15] = String
									.valueOf(getNumber(writetable[curRow][15])
											+ getNumber(table[k][i][13]));// ��arppu
							
						}
						break;
					}
					case 3 :
					{
						for(int i = 1 ; i < rsRows ; i ++ )
						{
							String d_time = getDate(table[k][i][1]);// ����
							if(d_time == null)
							{
								System.out.println("���棺��" + ( k + 1 ) + "�ű���"
										+ ( i + 1 ) + "�У�����Ϊ��");
								continue;
							}
							String q_name = changeName(configname ,
									table[k][i][2]);// ��������
							if(q_name == null)
							{
								System.out.println("���棺��" + ( k + 1 ) + "�ű���"
										+ ( i + 1 ) + "�У�������Ϊ��");
								continue;
							}
							int curRow = getCurRowfromWriteTable(writetable ,
									d_time , q_name);
							if(curRow == 0)
							{
								System.out.println("���棺��" + ( k + 1 )
										+ "�ű�\t����:" + table[k][i][1] + "\t����:"
										+ table[k][i][2] + "\tû����dataeye���ҵ���");
							}
							
							writetable[curRow][16] = String
									.valueOf(getNumber(writetable[curRow][16])
											+ getNumber(table[k][i][6]));// ��Ϸ����
							writetable[curRow][17] = String
									.valueOf(getNumber(writetable[curRow][17])
											+ getNumber(table[k][i][10]));// �����û���
							writetable[curRow][18] = String
									.valueOf(getNumber(writetable[curRow][18])
											+ getNumber(table[k][i][13]));// arppu
							
						}
						break;
					}
					case 4 :
					{
						for(int i = 1 ; i < rsRows ; i ++ )
						{
							String d_time = getDate(table[k][i][1]);// ����
							if(d_time == null)
							{
								System.out.println("���棺��" + ( k + 1 ) + "�ű���"
										+ ( i + 1 ) + "�У�����Ϊ��");
								continue;
							}
							String q_name = changeName(configname ,
									table[k][i][0]);// ��������
							if(q_name == null)
							{
								System.out.println("���棺��" + ( k + 1 ) + "�ű���"
										+ ( i + 1 ) + "�У�������Ϊ��");
								continue;
							}
							int curRow = getCurRowfromWriteTable(writetable ,
									d_time , q_name);
							if(curRow == 0)
							{
								System.out.println("���棺��" + ( k + 1 )
										+ "�ű�\t����:" + table[k][i][1] + "\t����:"
										+ table[k][i][0] + "\tû����dataeye���ҵ���");
							}
							
//						writetable[curRow][19] = String.valueOf(getNumber(writetable[curRow][19]) 
//									+ getNumber(table[k][i][2]));//������
							writetable[curRow][19] = writetable[curRow][2];
							writetable[curRow][20] = String
									.valueOf(getNumber(writetable[curRow][20])
											+ getNumber(table[k][i][5]));// �ճ�ֵ���
							writetable[curRow][21] = String
									.valueOf(getNumber(writetable[curRow][21])
											+ getNumber(table[k][i][6]));// ��ֵ����
							writetable[curRow][22] = String
									.valueOf(getNumber(writetable[curRow][22])
											+ getNumber(table[k][i][7]));// ����arppu
						}
						
						break;
					}
					case 5 :
					{
						for(int i = 1 ; i < rsRows ; i ++ )
						{
							String d_time = getDate(table[k][i][1]);// ����
							if(d_time == null)
							{
								System.out.println("���棺��" + ( k + 1 ) + "�ű���"
										+ ( i + 1 ) + "�У�����Ϊ��");
								continue;
							}
							String q_name = changeName(configname ,
									table[k][i][0]);// ��������
							if(q_name == null)
							{
								System.out.println("���棺��" + ( k + 1 ) + "�ű���"
										+ ( i + 1 ) + "�У�������Ϊ��");
								continue;
							}
							int curRow = getCurRowfromWriteTable(writetable ,
									d_time , q_name);
							if(curRow == 0)
							{
								System.out.println("���棺��" + ( k + 1 )
										+ "�ű�\t����:" + table[k][i][1] + "\t����:"
										+ table[k][i][0] + "\tû����dataeye���ҵ���");
							}
							writetable[curRow][19] = writetable[curRow][2];
							writetable[curRow][20] = String
									.valueOf(getNumber(writetable[curRow][20])
											+ getNumber(table[k][i][2]));// �ճ�ֵ���
							writetable[curRow][21] = String
									.valueOf(getNumber(writetable[curRow][21])
											+ getNumber(table[k][i][3]));// ��ֵ����
							
						}
						
						break;
					}
					case 6 :
					{
						for(int i = 2 ; i < rsRows ; i ++ )
						{
							String d_time = getDate(table[k][i][1]);// ����
							if(d_time == null)
							{
								System.out.println("���棺��" + ( k + 1 ) + "�ű���"
										+ ( i + 1 ) + "�У�����Ϊ��");
								continue;
							}
							String q_name = changeName(configname ,
									table[k][i][0]);// ��������
							if(q_name == null)
							{
								System.out.println("���棺��" + ( k + 1 ) + "�ű���"
										+ ( i + 1 ) + "�У�������Ϊ��");
								continue;
							}
							int curRow = getCurRowfromWriteTable(writetable ,
									d_time , q_name);
							if(curRow == 0)
							{
								System.out.println("���棺��" + ( k + 1 )
										+ "�ű�\t����:" + table[k][i][1] + "\t����:"
										+ table[k][i][0] + "\tû����dataeye���ҵ���");
							}
							writetable[curRow][19] = writetable[curRow][2];
							
//						writetable[curRow][19] = String.valueOf(getNumber(writetable[curRow][19]) 
//									+ getNumber(table[k][i][2]));//������
							writetable[curRow][20] = String
									.valueOf(getNumber(writetable[curRow][20])
											+ getNumber(table[k][i][12]));// �ճ�ֵ���
							writetable[curRow][21] = String
									.valueOf(getNumber(writetable[curRow][21])
											+ getNumber(table[k][i][13]));// ��ֵ����
							
						}
						
						break;
					}
					case 7 :
					{
						for(int i = 1 ; i < rsRows ; i ++ )
						{
							String d_time = getDate(table[k][i][1]);// ����
							if(d_time == null)
							{
								System.out.println("���棺��" + ( k + 1 ) + "�ű���"
										+ ( i + 1 ) + "�У�����Ϊ��");
								continue;
							}
							String q_name = changeName(configname ,
									table[k][i][0]);// ��������
							if(q_name == null)
							{
								System.out.println("���棺��" + ( k + 1 ) + "�ű���"
										+ ( i + 1 ) + "�У�������Ϊ��");
								continue;
							}
							int curRow = getCurRowfromWriteTable(writetable ,
									d_time , q_name);
							if(curRow == 0)
							{
								System.out.println("���棺��" + ( k + 1 )
										+ "�ű�\t����:" + table[k][i][1] + "\t����:"
										+ table[k][i][0] + "\tû����dataeye���ҵ���");
							}
							writetable[curRow][19] = writetable[curRow][2];
//						writetable[curRow][19] = String.valueOf(getNumber(writetable[curRow][19]) 
//									+ getNumber(table[k][i][2]));//������
							writetable[curRow][20] = String
									.valueOf(getNumber(writetable[curRow][20])
											+ getNumber(table[k][i][4]));// �ճ�ֵ���
							writetable[curRow][21] = String
									.valueOf(getNumber(writetable[curRow][21])
											+ getNumber(table[k][i][5]));// ��ֵ����
							
						}
						
						break;
					}
					case 8 :
					{
						for(int i = 1 ; i < rsRows ; i ++ )
						{
							String d_time = getDate(table[k][i][1]);// ����
							if(d_time == null)
							{
								System.out.println("���棺��" + ( k + 1 ) + "�ű���"
										+ ( i + 1 ) + "�У�����Ϊ��");
								continue;
							}
							String q_name = changeName(configname ,
									table[k][i][0]);// ��������
							if(q_name == null)
							{
								System.out.println("���棺��" + ( k + 1 ) + "�ű���"
										+ ( i + 1 ) + "�У�������Ϊ��");
								continue;
							}
							int curRow = getCurRowfromWriteTable(writetable ,
									d_time , q_name);
							if(curRow == 0)
							{
								System.out.println("���棺��" + ( k + 1 )
										+ "�ű�\t����:" + table[k][i][1] + "\t����:"
										+ table[k][i][0] + "\tû����dataeye���ҵ���");
							}
							
							writetable[curRow][19] = writetable[curRow][2];
//						writetable[curRow][19] = String.valueOf(getNumber(writetable[curRow][19]) 
//									+ getNumber(table[k][i][2]));//������
							writetable[curRow][20] = String
									.valueOf(getNumber(writetable[curRow][20])
											+ getNumber(table[k][i][3]));// �ճ�ֵ���
						}
						
						break;
					}
					case 9 :
					case 10 :
					case 11 :
					{
						for(int i = 1 ; i < rsRows ; i ++ )
						{
							String d_time = getDate(table[k][i][1]);// ����
							if(d_time == null)
							{
								System.out.println("���棺��" + ( k + 1 ) + "�ű���"
										+ ( i + 1 ) + "�У�����Ϊ��");
								continue;
							}
							String q_name = changeName(configname ,
									table[k][i][0]);// ��������
							if(q_name == null)
							{
								System.out.println("���棺��" + ( k + 1 ) + "�ű���"
										+ ( i + 1 ) + "�У�������Ϊ��");
								continue;
							}
							int curRow = getCurRowfromWriteTable(writetable ,
									d_time , q_name);
							if(curRow == 0)
							{
								System.out.println("���棺��" + ( k + 1 )
										+ "�ű�\t����:" + table[k][i][1] + "\t����:"
										+ table[k][i][0] + "\tû����dataeye���ҵ���");
							}
							
							writetable[curRow][19] = writetable[curRow][2];
//						writetable[curRow][19] = String.valueOf(getNumber(writetable[curRow][19]) 
//									+ getNumber(table[k][i][2]));//������
							writetable[curRow][20] = String
									.valueOf(getNumber(writetable[curRow][20])
											+ getNumber(table[k][i][3]));// �ճ�ֵ���
							writetable[curRow][21] = String
									.valueOf(getNumber(writetable[curRow][21])
											+ getNumber(table[k][i][4]));// ��ֵ����
//						writetable[curRow][22] = String.valueOf(Double.parseDouble(writetable[curRow][22]) 
//									+ Double.parseDouble(table[k][i][7].replaceAll(",", "")));//����arppu
						}
						
						break;
					}
					default :
						break;
				}
				
			}
			outXls(readwb , table , writetable , readSheet);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			readwb.close();
		}
		
	}
	
	/**
	 * @param string
	 * @return 
	 */
	private static String dealData(String string)
	{
		string.trim();
		string.replaceAll("," , "");
		System.out.println("string :" + string);
		// ��ʽ��
		String [] endString = string.split("-");
		for(int i = 0 ; i < endString.length ; i ++ )
		{
			endString[i].trim();
		}
		if(endString[0].contains("86"))
		{
			if(endString.length > 2)
			{
				if(endString[2].length() == 11)
				{
					return endString[2].toString();
				}
				if(endString[2].length() == 8 || endString[2].length() == 7)
				{
					if(endString[1].startsWith("1")
							|| endString[1].length() == 4)
					{
						return "'" + endString[1] + endString[2];
					}
					else
						return "'0" + endString[1] + endString[2];
				}
			}
			else
			{
				return endString[1];
			}
			
		}
		
		return string;
		// TODO Auto-generated method stub
		
	}
	
	public static String getDate(String ss)
	{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat df2 = new SimpleDateFormat("yyyy/MM/dd");
		Date date = null;
		
		try
		{
			date = df.parse(ss);
		}
		catch(Exception e)
		{
			try
			{
				date = df2.parse(ss);
			}
			catch(Exception e2)
			{
				try
				{
					if(Long.valueOf(ss).longValue() > 99999)
					{
						return ss.substring(2 , 4).trim() + "-"
								+ ss.substring(4 , 6).trim() + "-"
								+ ss.substring(6 , 8).trim();
					}
					Long l = ( Long.valueOf(ss).longValue() - 25569 ) * 86400000;
					date = new Date(l);
				}
				catch(Exception e3)
				{
					// System.out.println(ss + "�������ڣ�");
					return null;
				}
				
			}
			
		}
		return df.format(date).substring(2);
	}
	
	public static double getNumber(String ss)
	{
		try
		{
			return Double.parseDouble(ss.replaceAll("," , ""));
		}
		catch(Exception e)
		{
			// System.out.println("�������֣�");
			return 0;
		}
	}
	
	public static String [][] getConfigData(String ss)
	{
		jxl.Workbook readwb = null;
		String [][] config_ids = null;
		try
		{
			InputStream instream = new FileInputStream(ss);
			readwb = Workbook.getWorkbook(instream);
//			int countSheet = readwb.getNumberOfSheets();
			// Sheet���±��Ǵ�0��ʼ
			// ��ȡ��һ��Sheet��
			Sheet readsheet = readwb.getSheet(0);
			int rsRows = readsheet.getRows();
			int rsCells = readsheet.getColumns();
			config_ids = new String [rsRows] [];
			for(int k = 0 ; k < rsRows ; k ++ )
			{
				config_ids[k] = new String [rsCells];
				for(int i = 0 ; i < rsCells ; i ++ )
				{
					config_ids[k][i] = readsheet.getCell(i , k).getContents();
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			readwb.close();
		}
		System.out.println("**��ȡ���ñ���������Ƴɹ�");
		return config_ids;
	}
	
	public static String [][][] inputMemory(Workbook readwb , int readSheet)
	{
		// ���ص��ڴ���
		String [][][] table = new String [readSheet] [] [];
		for(int i = 0 ; i < readSheet ; i ++ )
		{
			Sheet st = readwb.getSheet(i);
			int r = st.getRows();
			int c = st.getColumns();
			table[i] = new String [r] [c];
			for(int j = 0 ; j < r ; j ++ )
			{
				for(int k = 0 ; k < c ; k ++ )
				{
					table[i][j][k] = st.getCell(k , j).getContents();
				}
			}
		}
		System.out.println("**�ѱ������д���ڴ�ɹ�");
		return table;
	}
	
	public static String getdatafromtable(String [][][] table , String id ,
			int i_id , String name , int i_name , int num)
	{
		String data = "0";
		for(int i = 0 ; i < table[0].length ; i ++ )
		{
			if(table[0][i][i_id].equalsIgnoreCase(id)
					&& table[0][i][i_name].equalsIgnoreCase(name))
			{
				data = table[0][i][num];
				break;
			}
		}
		return data;
	}
	
	public static int getCurRowfromWriteTable(String [][] writetable ,
			String id , String name)
	{
		int curRow = 0;
		for(int i = 3 ; i < writetable.length ; i ++ )
		{
			if(writetable[i][0] == null || writetable[i][1] == null)
			{
				// System.out.println("-------DataEye ��" + i + "�������⣡");
				continue;
			}
			
			if(writetable[i][0].equalsIgnoreCase(id)
					&& ( writetable[i][1].equalsIgnoreCase(name) ))
			{
				curRow = i;
				break;
			}
			
		}
//		if(curRow == 0){
//			System.out.println(id + name + "����δ�ҵ�");
//		}
		return curRow;
	}
	
	public static String changeName(String [][] configname , String ss)
	{
		for(int i = 0 ; i < configname.length ; i ++ )
		{
			for(int j = 0 ; j < configname[i].length ; j ++ )
			{
				if(ss.equalsIgnoreCase(configname[i][j]))
				{
					// System.out.println(ss+ "����:" + configname[i][j]);
					return configname[i][0];
				}
			}
		}
		// System.out.println(ss+ "δ����");
		return ss;
	}
	
	public static String changeNameDataEye(String [][] configname , String ss)
	{
		for(int i = 0 ; i < configname.length ; i ++ )
		{
			for(int j = 0 ; j < configname[i].length ; j ++ )
			{
				if(ss.equalsIgnoreCase(configname[i][j]))
				{
					return configname[i][0];
				}
			}
		}
		System.out.println("DataEye �� " + ss + "û���������������ҵ�");
		return ss;
	}
	
	public static void outXls(Workbook readwb , String [][][] table ,
			String [][] writetable , int readSheet)
			throws RowsExceededException , WriteException
	{
		// �����Ѿ�������Excel������,�����µĿ�д���Excel������
		jxl.write.WritableWorkbook wwb;
		try
		{
			wwb = Workbook.createWorkbook(new File(NewPath) , readwb);
			// ��ȡ
			jxl.write.WritableSheet ws = wwb.getSheet(readSheet);
			
			// д��
			for(int i = 0 ; i < writetable.length ; i ++ )
			{
				for(int j = 0 ; j < writetable[i].length ; j ++ )
				{
					String str = writetable[i][j];
					try
					{
						double d = Double.parseDouble(str);
						Number number = new Number(j , i , d);
						ws.addCell(number);
					}
					catch(Exception e)
					{
						Label label = new Label(j , i , str);
						ws.addCell(label);
					}
				}
				
			}
			
			System.out.println("**ȫ���������������");
			// д��Excel����
			wwb.write();
			wwb.close();
			System.out.println("**�Ѿ������µı����ݣ��򿪿����ɣ�");
		}
		catch(IOException e1)
		{
			e1.printStackTrace();
		}
		
	}
}
