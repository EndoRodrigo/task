package com.endorodrigo.task;

import com.endorodrigo.task.presentation.SistemaTareasFx;
import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TaskApplication {

	public static void main(String[] args) {
		//SpringApplication.run(TaskApplication.class, args);
		Application.launch(SistemaTareasFx.class, args);
	}

}
