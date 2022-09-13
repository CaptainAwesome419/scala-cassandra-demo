package com.babatunde
package repository

import model.Employee

import com.babatunde.exceptions.InvalidEmployeeException
import com.datastax.oss.driver.api.core.CqlSession
import com.datastax.oss.driver.api.core.cql.ResultSet

import scala.collection.mutable.ListBuffer
import scala.jdk.CollectionConverters.IterableHasAsJava
import scala.util.Random

class EmployeeRepository(session: CqlSession) {


  private def convertResultSetToEmployees(rows: ResultSet): List[Employee] = {
    var employees = new ListBuffer[Employee]
    rows.all().forEach(row => {
      var employee = Employee(id = row.getLong("id").toInt,
        name=  row.getString("name"),
        address = row.getString("address"),
        phone = row.getString("phoneNumber"),
        salary = row.getBigDecimal("salary").doubleValue())
        employees += employee
    })
    return employees.toList
  }

  def fetchAllEmployees(): List[Employee]={
    val rows: ResultSet = session.execute("select * from employee_details")
     convertResultSetToEmployees(rows);
  }

  def addNewEmployee(emp: Employee): Employee={
    val statement = session.prepare("insert into employee_details(id,name,address,phoneNumbers,salary) values(?,?,?,?,?)")
      .bind(Random.nextInt(),emp.name,emp.address,emp.phone,emp.salary)
    val result = session.execute(statement)
    convertResultSetToEmployees(result).head
  }

  def findByEmployeeId(id: Long)={
    val query = session.prepare("select * from employee_details where id = ?").bind(id);
    val row: ResultSet = session.execute(query);
    val employee =  convertResultSetToEmployees(row)
    if(employee.isEmpty)
      throw new InvalidEmployeeException("Invalid employee id supplied")
    employee.head
  }

  def deleteEmployeeById(id: Long):Unit={
    val query = session.prepare("delete from employee_details where id = ?").bind(id);
    session.execute(query);
  }

}
