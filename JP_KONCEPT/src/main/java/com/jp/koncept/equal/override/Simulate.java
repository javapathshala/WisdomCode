/**
 * 
 */
package com.jp.koncept.equal.override;

import java.util.HashMap;
import java.util.Map;

/**
 * @author dimit.chadha
 * 
 */
public class Simulate {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Map<EmployeeId, Employee> employees = new HashMap<EmployeeId, Employee>();

		employees.put(new EmployeeId("100"), new Employee("fresher"));
		employees.put(new EmployeeId("101"), new Employee("member"));
		EmployeeId key = new EmployeeId("102");
		employees.put(key, new Employee("senior"));
		key.setId("104");
		System.out.println(employees);

		Employee emp = employees.get(new EmployeeId("102"));
		System.out.println(emp);// null without overriding hash code & equals

	}

}
