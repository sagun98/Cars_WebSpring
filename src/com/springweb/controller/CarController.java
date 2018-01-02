package com.springweb.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.springweb.dao.CarDao;
import com.springweb.model.Car;

public class CarController extends HttpServlet{
	private static final long serialVersionUID =1L;
	private static String INSERT_OR_EDIT ="/car.jsp";
	private static String LIST_USER = "/listCar.jsp";
	private CarDao dao;
	ApplicationContext context;
	
	public CarController(){
		super();
		context = new ClassPathXmlApplicationContext("spring.xml");
		dao = (CarDao)context.getBean("CarDao");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String forward = "";
		String action = request.getParameter("action");
		
		if (action.equalsIgnoreCase("delete")) {
			int CarId = Integer.parseInt(request.getParameter("carId"));
			dao.deleteCar(CarId);
			forward = LIST_USER; 
			request.setAttribute("cars",dao.getAllCars());
		}
		
		else if (action.equalsIgnoreCase("edit")) {
			forward = INSERT_OR_EDIT;
			int CarId = Integer.parseInt(request.getParameter("carId"));
			Car car = dao.getCarById(CarId);
			request.setAttribute("car", car);
		}
		
		else if (action.equalsIgnoreCase("listCar")) {
			forward = LIST_USER; 
			request.setAttribute("cars", dao.getAllCars());
		}
		else {
			forward= INSERT_OR_EDIT;
		}
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}
	
}
