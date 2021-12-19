/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csd4149.database;

import com.google.gson.Gson;

import csd4149.mainClasses.SimpleUser;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mike
 */
public class EditSimpleUserTable {

	public void updateSimpleUserValue(String username, String field, String value)
			throws SQLException, ClassNotFoundException {
		Connection con = DB_Connection.getConnection();
		Statement stmt = con.createStatement();
		String update = "UPDATE users SET " + field + "='" + value + "' WHERE username = '" + username + "'";
		stmt.executeUpdate(update);
	}

	public void updateSimpleUserValue(String username, String field, int value)
			throws SQLException, ClassNotFoundException {
		Connection con = DB_Connection.getConnection();
		Statement stmt = con.createStatement();
		String update = "UPDATE users SET " + field + "=" + value + " WHERE username = '" + username + "'";
		stmt.executeUpdate(update);
	}

	public void printSimpleUserDetails(String username, String password) throws SQLException, ClassNotFoundException {
		Connection con = DB_Connection.getConnection();
		Statement stmt = con.createStatement();

		ResultSet rs;
		try {
			rs = stmt.executeQuery(
					"SELECT * FROM users WHERE username = '" + username + "' AND password='" + password + "'");
			while (rs.next()) {
				System.out.println("===Result===");
				DB_Connection.printResults(rs);
			}

		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
	}

	public SimpleUser databaseToSimpleUser(String username) throws SQLException, ClassNotFoundException {
		Connection con = DB_Connection.getConnection();
		Statement stmt = con.createStatement();

		ResultSet rs;
		try {
			rs = stmt.executeQuery("SELECT * FROM users WHERE username = '" + username + "'");
			rs.next();
			String json = DB_Connection.getResultsToJSON(rs);
			Gson gson = new Gson();
			SimpleUser user = gson.fromJson(json, SimpleUser.class);
			return user;
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		return null;
	}

	public String databaseUserToJSON(String username, String password) throws SQLException, ClassNotFoundException {
		Connection con = DB_Connection.getConnection();
		Statement stmt = con.createStatement();

		ResultSet rs;
		try {
			rs = stmt.executeQuery(
					"SELECT * FROM users WHERE username = '" + username + "' AND password='" + password + "'");
			rs.next();
			String json = DB_Connection.getResultsToJSON(rs);
			return json;
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		return null;
	}

	public void createSimpleUserTable() throws SQLException, ClassNotFoundException {

		Connection con = DB_Connection.getConnection();
		Statement stmt = con.createStatement();

		String query = "CREATE TABLE users " + "(user_id INTEGER not NULL AUTO_INCREMENT, "
				+ "    username VARCHAR(30) not null unique," + "    email VARCHAR(40) not null unique,	"
				+ "    password VARCHAR(32) not null," + "    firstname VARCHAR(20) not null,"
				+ "    lastname VARCHAR(30) not null," + "    birthdate DATE not null,"
				+ "    gender  VARCHAR (7) not null," + "    amka VARCHAR (11) not null,"
				+ "    country VARCHAR(30) not null," + "    city VARCHAR(50) not null,"
				+ "    address VARCHAR(50) not null," + "    lat DOUBLE," + "    lon DOUBLE,"
				+ "    telephone VARCHAR(14) not null," + "    height INTEGER," + "    weight DOUBLE,"
				+ "   blooddonor BOOLEAN," + "   bloodtype VARCHAR(7) not null," + " PRIMARY KEY ( user_id))";
		stmt.execute(query);
		stmt.close();
	}

	/**
	 * Establish a database connection and add in the database.
	 *
	 * @throws ClassNotFoundException
	 */
	public void addNewSimpleUser(SimpleUser user) throws Exception {
			Connection con = DB_Connection.getConnection();

			Statement stmt = con.createStatement();

			String insertQuery = "INSERT INTO "
					+ " users (username,email,password,firstname,lastname,birthdate,gender,amka,country,city,address,"
					+ "lat,lon,telephone,height,weight,blooddonor,bloodtype)" + " VALUES (" + "'" + user.getUsername()
					+ "'," + "'" + user.getEmail() + "'," + "'" + user.getPassword() + "'," + "'" + user.getFirstname()
					+ "'," + "'" + user.getLastname() + "'," + "'" + user.getBirthdate() + "'," + "'" + user.getGender()
					+ "'," + "'" + user.getAmka() + "'," + "'" + user.getCountry() + "'," + "'" + user.getCity() + "',"
					+ "'" + user.getAddress() + "'," + "'" + user.getLat() + "'," + "'" + user.getLon() + "'," + "'"
					+ user.getTelephone() + "'," + "'" + user.getHeight() + "'," + "'" + user.getWeight() + "'," + "'"
					+ user.isBloodDonor() + "'," + "'" + user.getBloodtype() + "'" + ")";
			// stmt.execute(table);
			System.out.println(insertQuery);
			stmt.executeUpdate(insertQuery);
			System.out.println("# The user was successfully added in the database.");

			/* Get the member id from the database and set it to the member */
			stmt.close();
	}

	public boolean userExists(String username, String password) {
		try {
			Connection con = DB_Connection.getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs;
			rs = stmt.executeQuery(
					"SELECT * FROM users WHERE username = '" + username + "' AND password = '" + password + "'");
			return rs.next();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean usernameExists(String username) {
		try {
			Connection con = DB_Connection.getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs;
			rs = stmt.executeQuery("SELECT * FROM users WHERE username = '" + username + "'");
			return rs.next();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean emailExists(String email) {
		try {
			Connection con = DB_Connection.getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs;
			rs = stmt.executeQuery("SELECT * FROM users WHERE email = '" + email + "'");
			return rs.next();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean amkaExists(String amka) {
		try {
			Connection con = DB_Connection.getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs;
			rs = stmt.executeQuery("SELECT * FROM users WHERE amka = '" + amka + "'");
			return rs.next();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
