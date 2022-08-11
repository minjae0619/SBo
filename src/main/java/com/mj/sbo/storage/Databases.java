package com.mj.sbo.storage;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mj.sbo.Main;
import com.mj.sbo.objects.login.LoginType;


public class Databases {

	static{
		File file = new File(Main.PROJECT_PATH + "\\Databases");
		if(!file.exists()) file.mkdir();


	}
	private final String TABLE_NAME = "list";
	String path = Main.PROJECT_PATH + "\\Databases\\databases.db";

	String createTable = "CREATE TABLE list (type TEXT PRIMARY KEY NOT NULL, id TEXT, password TEXT, loginType TEXT, serialNumber TEXT)";

	String checkTable = "PRAGMA table_info('list');";

	String checkRow = "SELECT * FROM list WHERE type = \"%s\";";

	String insertRow = "INSERT into list (type, id, password, loginType, serialNumber) VALUES (?, ?, ?, ?, ?);";

	String updateRow = "UPDATE list SET type = ?, id = ?, password = ?, loginType = ?, serialNumber = ? WHERE type = \"%s\";";

	String deleteRow = "DELETE FROM list WHERE type = \"%s\";";

	Connection connection;

	private static Databases instance;
	public static Databases getInstance() {
		if(instance == null)
			instance = new Databases();
		return instance;
	}
	
	private void createFile(){
		File db = new File(path);
		if (!db.exists()) {
			try {
				db.createNewFile();
			} catch (IOException e1) {
			}
		}
	}
	private Databases() {
		try {
			createFile();
			connection = DriverManager.getConnection("jdbc:sqlite:" + path);
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(checkTable);
			if (!rs.next())
				stmt.executeUpdate(createTable);
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public void create(DatabaseType databaseType) {
		File db = new File(path);
		if (!db.exists()) {
			try {
				db.createNewFile();
			} catch (IOException e1) {
			}
		}
		boolean exists = false;
		try (Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(String.format(checkRow, databaseType.toString()))) {
			exists = rs.next();
			rs.close();
			stmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (exists)
			return;
		try (PreparedStatement prep = connection.prepareStatement(insertRow)) {
			connection.setAutoCommit(true);
			prep.setString(1, databaseType.toString());
			prep.setString(2, Main.view.id.getText());
			prep.setString(3, Main.view.password.getText());
			prep.setString(4, Main.login.getLoginType().toString());
			prep.setString(5, Main.view.serialNumber.getText());
			prep.executeUpdate();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void write(DatabaseType databaseType) {
		boolean exists = false;
		try (Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(String.format(checkRow, databaseType.toString()))) {
			exists = rs.next();
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (!exists) {
			create(databaseType);
			return;
		}
		update(databaseType);
	}
	

	public void update(DatabaseType databaseType) {
		try (PreparedStatement prep = connection.prepareStatement(String.format(updateRow, databaseType.toString()))) {
			connection.setAutoCommit(true);
			prep.setString(1, databaseType.toString());
			prep.setString(2, Main.view.id.getText());
			prep.setString(3, Main.view.password.getText());
			prep.setString(4, Main.login.getLoginType().toString());
			prep.setString(5, Main.view.serialNumber.getText());
			prep.executeUpdate();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public void read() {
		try {
			String sql = "SELECT * FROM "+ TABLE_NAME;
			Statement stat = connection.createStatement();
			ResultSet result = stat.executeQuery(sql);

			while (result.next()) {
				DatabaseType databaseType = DatabaseType.valueOf(result.getString("type"));
				if(databaseType == DatabaseType.INFO) {
					String id = result.getString("id");
					String password = result.getString("password");
					LoginType loginType = LoginType.valueOf(result.getString("loginType"));
					String serialNumber = result.getString("serialNumber");
					if (id != null) Main.view.id.setText(id);
					if (password != null) Main.view.password.setText(password);
					Main.view.comboBox.setSelectedItem(loginType.getName());
					if (serialNumber != null) Main.view.serialNumber.setText(serialNumber);
					Main.view.checkBox.setSelected(true);
				}

			}
			stat.close();
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();}
	}

	public void delete(DatabaseType databaseType) {
		try {
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(String.format(deleteRow, databaseType.toString()));
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
