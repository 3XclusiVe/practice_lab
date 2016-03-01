package de.vogella.osgi.firstbundle.internal;

import java.util.List;
import java.util.function.Consumer;

import com.example.e4.rcp.todo.model.*;
import com.example.e4.rcp.todo.services.*;

public class MyThread extends Thread {
  private volatile boolean active = true;
  
  private Consumer<List<Todo>> consumer = MyThread::printTodo;

  public void run() {
    while (active) {
      System.out.println("Hello OSGi console");
      try { 
    	 
        TodoServiceFactory.getInstance().getTodos(consumer);   
    	
        Thread.sleep(5000);
      } catch (Exception e) {
        System.out.println("Thread interrupted " + e.getMessage());
      }
    }
  }
  
  private static void printTodo(List<Todo> list) {
      for(Todo todo : list) {
    	  System.out.println(todo);
      }
  } 

  public void stopThread() {
    active = false;
  }
} 