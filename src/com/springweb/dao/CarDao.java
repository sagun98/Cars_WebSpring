package com.springweb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement; 
import java.sql.ResultSet; 
import java.sql.SQLException;
import java.sql.Statement; 
import java.util.ArrayList; 
import java.util.List;
import com.springweb.model.Car;
import com.springweb.util.DbUtil;

public class CarDao {
	private Connection connection; 
	
	public CarDao() {
		connection = DbUtil.getConnection();
	}
	
	public void addCar(Car car) {
		try{
		PreparedStatement preparedStatement = connection.prepareStatement("insert into cars(manufacturer,model) values(?,?)");
		preparedStatement.setString(1,car.getManufacturer());
		preparedStatement.setInt(2, car.getModel());
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteCar(int carId) {
		try{
			PreparedStatement preparedStatement = connection.prepareStatement("delete from cars where carId=?");
			preparedStatement.setInt(1,carId);
			preparedStatement.executeUpdate();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
	}
	
	public void updateCar(Car car) {
		try{
			PreparedStatement preparedStatement = connection.prepareStatement("update cars set manufacturer=?,model=? where carId=?");
			preparedStatement.setString(1,car.getManufacturer());
			preparedStatement.setInt(1,car.getModel());
			preparedStatement.executeUpdate();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
	}
	
	public List<Car> getAllCars(){
		List<Car> cars = new ArrayList<Car>();
		try{
			Statement statement =connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from cars");
			while (rs.next()) {
				Car car = new Car();
				car.setCarId(rs.getInt("carId"));
				car.setManufacturer(rs.getString("manufacturer"));
				car.setModel(rs.getInt("model"));
				cars.add(car);
			}
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		return cars;
	}
	
	public Car getCarById(int carId) {
		Car car = new Car();
		try{
			PreparedStatement preparedStatement = connection.prepareStatement("select * from cars where carId=?");
			preparedStatement.setInt(1,carId);
			ResultSet rs = preparedStatement.executeQuery();
			
			if (rs.next()) {
				car.setCarId(rs.getInt("carId"));
				car.setManufacturer(rs.getString("manufacturer"));
				car.setModel(rs.getInt("model"));
				}
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		return car;
	}
}
