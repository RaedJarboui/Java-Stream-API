package com.stream.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.stream.api.models.Employe;

@SpringBootApplication
public class JavaStreamApiApplication {
	

	public static void main(String[] args) {
		List<Employe> list = Arrays.asList(new Employe("emp3","emp3",27),new Employe("emp1","emp1",26),new Employe("emp2","emp2",25),new Employe("emp2","emp2",24));
		filterEmployeWithAge(list);
		SpringApplication.run(JavaStreamApiApplication.class, args);
	}
	
	public static void filterEmployeWithAge(List<Employe> list) {
		List<Employe> filteredList = list.stream().filter(emp->emp.getAge()<25).collect(Collectors.toList());
		System.out.println("filteredList : "+filteredList);
	}
	
	public static void skipFirstEmploye(List<Employe> list) {
		List<Employe> skipEmploye = list.stream().skip(1).toList();
		System.out.println("skipEmploye : "+skipEmploye);

	}
	
	public static void countEmployes(List<Employe> list) {
		System.out.println("employes count : "+list.stream().count());
	}
	
	public static void limitEmploye(List<Employe> list) {
		List<Employe> emp = list.stream().limit(1).toList();
		if(!emp.isEmpty()) System.out.println("limited list :"+emp);
	}
	
	public static void sortEmploye(List<Employe>list) {
		List<Employe> sortedEmployes = list.stream().sorted((emp1,emp2)->{
			return emp1.getAge() - emp2.getAge();
		}).collect(Collectors.toList());
		
		List<Employe> sortedEmployes2= list.stream().sorted(Comparator.comparing(Employe::getAge).reversed()).toList();
	}
	
	public static void mapEemploye(List<Employe> list) {
		List<Integer> emps = list.stream().map(Employe::getAge).collect(Collectors.toList());
	}
	
	public static void matchEmployes(List<Employe> list) {
		boolean match1 = list.stream().anyMatch(emp->emp.getFirstName().equalsIgnoreCase("emp1"));
		boolean match2 = list.stream().allMatch(emp->emp.getFirstName().startsWith("emp"));
		boolean match3 = list.stream().noneMatch(emp->emp.getLastName().contains("emp"));
	}
	
	public static void ListToArray(List<Employe> list) {
		Employe[] emp = new Employe[(list.size())];
		Employe[] array = list.toArray(emp);
		System.out.println("array of employes : "+array);
		
	}
	
	public static void distinctEmployes(List<Employe> emps) {
		emps.stream().distinct().toList();
	}
	
	public static void findEmployes(List<Employe> emps) {
		emps.stream().filter(emp->emp.getFirstName().equals(emp.getLastName())).findAny();
		emps.stream().filter(emp->emp.getFirstName().indexOf("emp") !=-1).findFirst();
	}
	
	public static void compareEmployeAge(List<Employe> emps) {
		//max
		emps.stream().max((emp1,emp2)->{
			return emp1.getAge() - emp2.getAge();
		});
				
		//min
		
		emps.stream().min((emp1,emp2)->{
			return emp2.getAge() - emp1.getAge();
		});
	}
	
	public static void loopEmployes(List<Employe> emps) {
		emps.stream().forEach(emp->System.out.println("employe : "+emp));
	}
	
	
	public static void groupByEmploye(List<Employe> list) {
		Map<String,List<Employe>> map = list.stream().collect(
				Collectors.groupingBy(Employe::getFirstName)
				);
		
		map.forEach((key,value)->{
			System.out.println("map key :"+key);
			System.out.println("map value :"+value);

		});
	}
		
		public static void removeFromEmployes(List<Employe> emps) {
			emps.removeIf(emp->emp.getAge() < 25);
			List<Employe> emps2 = Arrays.asList(new Employe("emp3","emp3",27));
			emps.removeAll(emps2);
			emps.clear();
		}
		
		public static void dropWhile(List<Employe> emps) {
			emps.stream().dropWhile(emp->emp.getAge() <25).collect(Collectors.toList());
		}
		
		public static void takewhile(List<Employe> emps) {
			emps.stream().takeWhile(emp->emp.getAge() > 25).toList();
		}
		
		public static void peekEmployes(List<Employe> emps) {
			Stream.of(1,2,3,4,5,6,7,8,9).filter(num->num > 5).peek(e->System.out.println(e));

		}
		
		public static void flatMapEmployes() {
			List<List<Integer>> listOfLists = Arrays.asList(
					  Arrays.asList(1, 2, 3),
					  Arrays.asList(4, 5),
					  Arrays.asList(6, 7, 8)
					);
			
			List<Integer> listInt =	listOfLists.stream().flatMap(list->list.stream().filter(num->num > 5)).toList();
		}
		
	
	public static void partionningEmployes(List<Employe> emps) {
		
		Map<Boolean, List<Employe>> map = emps.stream().collect(Collectors.partitioningBy(
				emp->emp.getAge() > 25
				));
		
	}
		
		
		
		
	
	
	
	
	
	
	

}
