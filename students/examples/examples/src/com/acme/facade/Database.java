/*
 * Copyright (C) 2014 - 2020 T.N.Silverman, All rights reserved.
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.acme.facade;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

/**
 * The class Database i a Facade to an RDBMS and simplifies the JDBC API usage
 * for clients
 *
 * Note: running this class requires running the setup script
 * 'sql/once-as-root.sql' and the mysql connector library
 * lib/mysql-connector-java-8.0.19.jar in the classpath
 */
public class Database implements IDataAccessObject {

	private volatile Connection conn;
	private String driver, url, user, password;
	public static final String MYSQL_DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String MYSQL_DBURL = "jdbc:mysql://localhost:3306/";

	private Database(String driver, String url, String user, String password) {
		super();
		this.driver = driver;
		this.url = url;
		this.user = user;
		this.password = password;
	}

	public static Database getDatabase(String driver, String url, String user, String password) {
		return new Database(driver, url, user, password);
	}

	public static Database getMySQLDatabase(String dbName, String user, String password) {
		return new Database(MYSQL_DRIVER, MYSQL_DBURL + dbName, user, password);
	}

	@Override
	public Connection getConnection() throws Exception {
		connect();
		return conn;
	}

	@Override
	public void connect() throws Exception {
		if (conn == null) {
			synchronized (this) {
				Class.forName(driver); // This will load the MySQL driver, each DB has its own driver
				conn = DriverManager
						.getConnection(url + "?user=" + user + "&password=" + password + "&serverTimezone=UTC"); // Setup
				// the connection with the DB
				System.out.printf("\nDatabase.connect() -> connected to '%s'%n", url);
			}
		}
	}

	@Override
	public void disconnect() throws Exception {
		if (conn != null && !conn.isClosed()) {
			try {
				conn.close();
				System.out.printf("\nDatabase.disconnect() -> disconnected from '%s'%n", url);
			} catch (SQLException sqle) {
				System.err.printf("Database problem: %s%n", sqle);
				sqle.printStackTrace();
			}
		}
	}

	@Override
	public ResultSet query(String sql) throws Exception {
		System.out.printf("\nDatabase.query() -> querying '%s'...%n", sql);
		Statement stmt = conn.createStatement(); // Statements allow to issue SQL queries to the database
		ResultSet rs = stmt.executeQuery(sql); // Result set gets the result of the SQL query
		return rs;
	}

	@Override
	public int execute(String sql) throws Exception {
		System.out.printf("%nDatabase.execute() -> executing '%s'...%n", sql);
		PreparedStatement stmt = conn.prepareStatement(sql);
		return stmt.executeUpdate();
	}

	@Override
	public String[] getTableNames() throws Exception {
		ResultSet rs = query("show tables");
		List<String> tables = new LinkedList<String>();
		while (rs.next()) {
			tables.add(rs.getString(1));
		}
		return tables.toArray(new String[0]);
	}

	@Override
	public void writeResutlSet(ResultSet rs) throws SQLException {
		ResultSetMetaData metaData = rs.getMetaData();
		int colNum = metaData.getColumnCount();
		int rowNum = 0;
		int[] width = new int[colNum + 1]; // array to store column widths
		StringBuilder sb = new StringBuilder(); // buffer to hold bar line
		// calculate column widths
		for (int i = 1; i <= colNum; i++) {
			// some drivers return -1 for getColumnDisplaySize( ); if so, we'll override
			// that with the column name length
			width[i] = metaData.getColumnDisplaySize(i);
			if (width[i] < metaData.getColumnName(i).length()) {
				width[i] = metaData.getColumnName(i).length();
			}
			// isNullable( ) returns 1/0, not true/false
			if (width[i] < 4 && metaData.isNullable(i) != 0) {
				width[i] = 4;
			}
			// fix timestamp size
			if (metaData.getColumnClassName(i).equals("java.sql.Timestamp")) {
				width[i] += 2;
			}
		}
		// construct +---+---... line
		sb.append("+");
		for (int i = 1; i <= colNum; i++) {
			for (int j = 0; j < width[i]; j++) {
				sb.append("-");
			}
			sb.append("+");
		}
		// print bar line, column headers, bar line
		System.out.println(sb.toString());
		System.out.print("|");
		for (int i = 1; i <= colNum; i++) {
			System.out.print(metaData.getColumnName(i));
			for (int j = metaData.getColumnName(i).length(); j < width[i]; j++) {
				System.out.print(" ");
			}
			System.out.print("|");
		}
		System.out.println();
		System.out.println(sb.toString());
		// print contents of result set
		while (rs.next()) {
			++rowNum;
			System.out.print("|");
			for (int i = 1; i <= colNum; i++) {
				String s = rs.getString(i);
				if (rs.wasNull()) {
					s = "NULL";
				}
				System.out.print(s);
				for (int j = s.length(); j < width[i]; j++) {
					System.out.print(" ");
				}
				System.out.print("|");
			}
			System.out.println();
		}
		// print bar line, and row count
		System.out.println(sb.toString());
		System.out.println(rowNum + (rowNum == 1 ? " row" : " rows") + " selected");
	}

	public static void main(String[] args) throws Exception {
		IDataAccessObject db = Database.getMySQLDatabase("mydatabase", "myuser", "mysecret");
		db.connect();
		System.out.println("Table Names:");
		for (String name : db.getTableNames())
			System.out.println(name);
		db.writeResutlSet(db.query("select * from COMMENTS"));
		int rows = db.execute("update COMMENTS set DATUM=now() where MYUSER='tnsilver'");
		System.out.println("Updated " + rows + (rows == 1 ? " row" : " rows") + "!");
		db.writeResutlSet(db.query("select * from COMMENTS"));
		db.disconnect();
	}
}
