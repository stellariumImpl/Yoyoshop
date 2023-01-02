package com.lanou.yoyo.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 这是数据库的工具类, 主要是提供数据库的一些操作方法 例如: 通用的增删改查 通用的查询
 * 
 * @author 28993
 *
 */
public class DBUtils {
	// 在加载类的时候,就把 数据连接池创建好
	private static DataSource ds = new ComboPooledDataSource();

	// 提供一个私有构造方法, 目的是不想让别人new对象.
	private DBUtils() {
	}

	/**
	 * 获取一个连接 !!注意!! 用到事务的时候,才需要使用此方法, 如果不用事务,无需使用此方法 获取的连接需要关闭(放回数据库连接池中)
	 * 
	 * @return
	 */
	public static Connection getConnection() {
		Connection con = null;
		try {
			con = ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	/**
	 * 关闭一个连接 !!注意!! 用到事务的时候,才需要使用此方法, 如果不用事务,无需使用此方法 本方法用于关闭 本类中 Connection
	 * getConnection() 返回的连接对象
	 * 
	 * @param con
	 */
	public static void close(Connection con) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 通用的增, 删, 改方法
	 * 
	 * @param sql        增删改sql语句
	 * @param parameters sql语句中?的值
	 * @return 返回sql语句执行之后,影响的行数
	 */
	public static int update(String sql, Object... parameters) {
		int row = 0;
		try (Connection con = ds.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
			// 给sql语句中的?赋值
			for (int i = 0; i < parameters.length; i++) {
				stmt.setObject(i + 1, parameters[i]);
			}
			row = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return row;
	}

	/**
	 * 插入一条数据,并返回id的值
	 * @param sql     插入的sql语句
	 * @param parameters         sql语句中?的值
	 * @return 返回插入数据时生成的id
	 */
	public static int insertAndGetGeneratedKey(String sql, Object... parameters) {
		int id = 0;
		try (Connection con = ds.getConnection();
				PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			// 给sql语句中的?赋值
			for (int i = 0; i < parameters.length; i++) {
				stmt.setObject(i + 1, parameters[i]);
			}
			int row = stmt.executeUpdate();
			if (row != 0) {// 如果执行成功
				ResultSet resultSet = stmt.getGeneratedKeys();
				resultSet.next();
				id = resultSet.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

	/**
	 * 事务版--通用的增, 删, 改方法
	 * 
	 * @param sql        增删改sql语句
	 * @param con        连接对象
	 * @param parameters sql语句中?的值
	 * @return 返回sql语句执行之后,影响的行数
	 */
	public static int update(String sql, Connection con, Object... parameters) {
		int row = 0;
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			// 给sql语句中的?赋值
			for (int i = 0; i < parameters.length; i++) {
				stmt.setObject(i + 1, parameters[i]);
			}
			row = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return row;
	}

	/**
	 * 通用查询方法
	 * 
	 * @param sql              查询的sql语句
	 * @param clazz            希望封装成什么类型 例如希望封装成 Student类型,参数写成 Student.class
	 * @param underlineToCamel 是否需要把表中字段带_的转换成小驼峰名称 true表示转, false表示不转
	 * @param parameters       sql语句中?的值
	 * @return 返回一个List,list是clazz对应类型的对象
	 */
	public static <T> List<T> query(String sql, Class<T> clazz, boolean underlineToCamel, Object... parameters) {
		List<T> list = new ArrayList<>();
		try (Connection con = ds.getConnection(); PreparedStatement stmt = con.prepareStatement(sql);) {
			// 给sql语句中的?赋值
			for (int i = 0; i < parameters.length; i++) {
				stmt.setObject(i + 1, parameters[i]);
			}
			// 获取结果集
			ResultSet resultSet = stmt.executeQuery();

			// 结果集的元数据
			ResultSetMetaData rsmd = resultSet.getMetaData();
			// 获取查询的列数
			int columnCount = rsmd.getColumnCount();
			// 创建一个String数组,存放查询的所有列的列名
			String[] columnLabelArr = new String[columnCount];
			for (int i = 1; i <= columnCount; i++) {
				// 获取列的标签名(列名)
				String columnLabel = rsmd.getColumnLabel(i);
				columnLabelArr[i - 1] = columnLabel;
			}
			// 遍历查询结果
			while (resultSet.next()) {
				// 通过反射创建对象!
				Constructor<T> constructor = clazz.getDeclaredConstructor();
				constructor.setAccessible(true);// 跳过访问权限检查
				T obj = constructor.newInstance();

				for (int i = 0; i < columnLabelArr.length; i++) {
					// 通过下标获取列名
					String columnLabel = columnLabelArr[i];
					// 列对应的值
					Object value = resultSet.getObject(columnLabel);

					// 通过反射获取类中的Field
					if (underlineToCamel) {
						// 下划线转小驼峰
						columnLabel = underlineToCamel(columnLabel);
					}
					Field field = clazz.getDeclaredField(columnLabel);
					field.setAccessible(true);// 跳过访问权限的检查
					// 通过反射给属性赋值
					if (value != null) {
						field.set(obj, value);
					}
				}
				list.add(obj);
			}
			resultSet.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 事务版--通用查询方法
	 * 
	 * @param sql              查询的sql语句
	 * @param con              连接对象
	 * @param clazz            希望封装成什么类型 例如希望封装成 Student类型,参数写成 Student.class
	 * @param underlineToCamel 是否需要把表中字段带_的转换成小驼峰名称 true表示转, false表示不转
	 * @param parameters       sql语句中?的值
	 * @return 返回一个List,list是clazz对应类型的对象
	 */
	public static <T> List<T> query(String sql, Connection con, Class<T> clazz, boolean underlineToCamel,
			Object... parameters) {
		List<T> list = new ArrayList<>();
		try (PreparedStatement stmt = con.prepareStatement(sql);) {
			// 给sql语句中的?赋值
			for (int i = 0; i < parameters.length; i++) {
				stmt.setObject(i + 1, parameters[i]);
			}
			// 获取结果集
			ResultSet resultSet = stmt.executeQuery();

			// 结果集的元数据
			ResultSetMetaData rsmd = resultSet.getMetaData();
			// 获取查询的列数
			int columnCount = rsmd.getColumnCount();
			// 创建一个String数组,存放查询的所有列的列名
			String[] columnLabelArr = new String[columnCount];
			for (int i = 1; i <= columnCount; i++) {
				// 获取列的标签名(列名)
				String columnLabel = rsmd.getColumnLabel(i);
				columnLabelArr[i - 1] = columnLabel;
			}
			// 遍历查询结果
			while (resultSet.next()) {
				// 通过反射创建对象!
				Constructor<T> constructor = clazz.getDeclaredConstructor();
				constructor.setAccessible(true);// 跳过访问权限检查
				T obj = constructor.newInstance();

				for (int i = 0; i < columnLabelArr.length; i++) {
					// 通过下标获取列名
					String columnLabel = columnLabelArr[i];
					// 列对应的值
					Object value = resultSet.getObject(columnLabel);

					// 通过反射获取类中的Field
					if (underlineToCamel) {
						// 下划线转小驼峰
						columnLabel = underlineToCamel(columnLabel);
					}
					Field field = clazz.getDeclaredField(columnLabel);
					field.setAccessible(true);// 跳过访问权限的检查
					// 通过反射给属性赋值
					if (value != null) {
						field.set(obj, value);
					}
				}
				list.add(obj);
			}
			resultSet.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 通用查询方法
	 * 
	 * @param sql        查询的sql语句
	 * @param clazz      希望封装成什么类型 例如希望封装成 Student类型,参数写成 Student.class
	 * @param parameters sql语句中?的值
	 * @return 返回一个List,list是clazz对应类型的对象
	 */
	public static <T> List<T> query(String sql, Class<T> clazz, Object... parameters) {
		return query(sql, clazz, true, parameters);
	}

	/**
	 * 事务版---通用查询方法
	 * 
	 * @param sql        查询的sql语句
	 * @param con        连接对象
	 * @param clazz      希望封装成什么类型 例如希望封装成 Student类型,参数写成 Student.class
	 * @param parameters sql语句中?的值
	 * @return 返回一个List,list是clazz对应类型的对象
	 */
	public static <T> List<T> query(String sql, Connection con, Class<T> clazz, Object... parameters) {
		return query(sql, con, clazz, true, parameters);
	}

	/**
	 * 查询单个值的通用查询
	 * 
	 * @param sql              查询的sql语句
	 * @param clazz            希望封装成的类型
	 * @param underlineToCamel 是否需要把表中字段带_的转换成小驼峰名称 true表示转, false表示不转
	 * @param parameters       sql语句中?的值
	 * @return 如果没有查到返回null,如果查到了返回第一个结果
	 */
	public static <T> T queryOne(String sql, Class<T> clazz, boolean underlineToCamel, Object... parameters) {
		List<T> list = query(sql, clazz, underlineToCamel, parameters);
		return list.size() == 0 ? null : list.get(0);
	}

	/**
	 * 事务版---查询单个值的通用查询
	 * 
	 * @param sql              查询的sql语句
	 * @param con              连接对象
	 * @param clazz            希望封装成的类型
	 * @param underlineToCamel 是否需要把表中字段带_的转换成小驼峰名称 true表示转, false表示不转
	 * @param parameters       sql语句中?的值
	 * @return 如果没有查到返回null,如果查到了返回第一个结果
	 */
	public static <T> T queryOne(String sql, Connection con, Class<T> clazz, boolean underlineToCamel,
			Object... parameters) {
		List<T> list = query(sql, con, clazz, underlineToCamel, parameters);
		return list.size() == 0 ? null : list.get(0);
	}

	/**
	 * 查询单个值的通用查询
	 * 
	 * @param sql        查询的sql语句
	 * @param clazz      希望封装成的类型
	 * @param parameters sql语句中?的值
	 * @return 如果没有查到返回null,如果查到了返回第一个结果
	 */
	public static <T> T queryOne(String sql, Class<T> clazz, Object... parameters) {
		List<T> list = query(sql, clazz, true, parameters);
		return list.size() == 0 ? null : list.get(0);
	}

	/**
	 * 事务版---查询单个值的通用查询
	 * 
	 * @param sql        查询的sql语句
	 * @param con        连接对象
	 * @param clazz      希望封装成的类型
	 * @param parameters sql语句中?的值
	 * @return 如果没有查到返回null,如果查到了返回第一个结果
	 */
	public static <T> T queryOne(String sql, Connection con, Class<T> clazz, Object... parameters) {
		List<T> list = query(sql, con, clazz, true, parameters);
		return list.size() == 0 ? null : list.get(0);
	}

	/**
	 * 把下划线命名转换为小驼峰法命名
	 * 
	 * @param underline
	 * @return
	 */
	private static String underlineToCamel(String underline) {
		// 创建一个可变字符串.
		StringBuilder sb = new StringBuilder(underline);
		// 遍历字符串中的每个字符
		for (int i = 0; i < sb.length(); i++) {
			char c = sb.charAt(i);
			if (c == '_' && i != sb.length() - 1) {
				// 如果是下划线,找到它下个字符
				char nextChar = underline.charAt(i + 1);
				// 把这个字符变成大写
				nextChar = Character.toUpperCase(nextChar);
				// 替换字符
				sb.setCharAt(i + 1, nextChar);
			}
		}
		String str = sb.toString();

		return str.replace("_", "");
	}

	/**
	 * 通用查询方法
	 * 
	 * @param sql        查询的sql语句
	 * @param parameters sql语句中?的值
	 * @return 返回一个List,list的元素是Map类型
	 */
	public static List<Map<String, Object>> query(String sql, Object... parameters) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try (Connection con = ds.getConnection(); PreparedStatement stmt = con.prepareStatement(sql);) {
			// 给sql语句中的?赋值
			for (int i = 0; i < parameters.length; i++) {
				stmt.setObject(i + 1, parameters[i]);
			}
			// 执行查询,获取结果集
			ResultSet resultSet = stmt.executeQuery();
			// 获取结果集元数据
			ResultSetMetaData rsmd = resultSet.getMetaData();
			// 获取查询的列数
			int columnCount = rsmd.getColumnCount();
			// 创建一个String数组,存放列名
			String[] columnNames = new String[columnCount];
			for (int i = 1; i <= columnCount; i++) {
				// 通过列号 获取列名
				String columnLabel = rsmd.getColumnLabel(i);
				columnNames[i - 1] = columnLabel;
			}
			// 遍历结果集
			while (resultSet.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				// 遍历每一列
				for (int i = 0; i < columnNames.length; i++) {
					// 获取列名
					String columnName = columnNames[i];
					// 通过列名获取值
					Object value = resultSet.getObject(columnName);
					// 把列名和值存入map中
					map.put(columnName, value);
				}
				list.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 事务版---通用查询方法
	 * 
	 * @param sql        查询的sql语句
	 * @param con        连接对象
	 * @param parameters sql语句中?的值
	 * @return 返回一个List,list的元素是Map类型
	 */
	public static List<Map<String, Object>> query(String sql, Connection con, Object... parameters) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try (PreparedStatement stmt = con.prepareStatement(sql);) {
			// 给sql语句中的?赋值
			for (int i = 0; i < parameters.length; i++) {
				stmt.setObject(i + 1, parameters[i]);
			}
			// 执行查询,获取结果集
			ResultSet resultSet = stmt.executeQuery();
			// 获取结果集元数据
			ResultSetMetaData rsmd = resultSet.getMetaData();
			// 获取查询的列数
			int columnCount = rsmd.getColumnCount();
			// 创建一个String数组,存放列名
			String[] columnNames = new String[columnCount];
			for (int i = 1; i <= columnCount; i++) {
				// 通过列号 获取列名
				String columnLabel = rsmd.getColumnLabel(i);
				columnNames[i - 1] = columnLabel;
			}
			// 遍历结果集
			while (resultSet.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				// 遍历每一列
				for (int i = 0; i < columnNames.length; i++) {
					// 获取列名
					String columnName = columnNames[i];
					// 通过列名获取值
					Object value = resultSet.getObject(columnName);
					// 把列名和值存入map中
					map.put(columnName, value);
				}
				list.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 通用查询方法
	 * 
	 * @param sql        查询的sql语句
	 * @param parameters sql语句中?的值
	 * @return 返回一个Map类型
	 */
	public static Map<String, Object> queryOne(String sql, Object... parameters) {
		List<Map<String, Object>> list = query(sql, parameters);
		return list.size() == 0 ? null : list.get(0);
	}

	/**
	 * 事务版---通用查询方法
	 * 
	 * @param sql        查询的sql语句
	 * @param con        连接对象
	 * @param parameters sql语句中?的值
	 * @return 返回一个Map类型
	 */
	public static Map<String, Object> queryOne(String sql, Connection con, Object... parameters) {
		List<Map<String, Object>> list = query(sql, con, parameters);
		return list.size() == 0 ? null : list.get(0);
	}

}
