import com.babatunde.config.DbConfig
import com.babatunde.model.Employee
import com.babatunde.repository.EmployeeRepository

import scala.io.StdIn.readLine
import scala.sys.exit

object Main {

  val empRepo: EmployeeRepository = new EmployeeRepository(DbConfig.getSession());

  def displayOptions(): Unit ={
    println("1. View all employees")
    println("2. Get employee details")
    println("3. add new employee")
    println("4. delete employee details")
  }
  def main(args: Array[String]): Unit = {

    println("Please enter a number for what you want to do: ")
    displayOptions();

    val optInt =  readLine().toIntOption;
    if(optInt.isEmpty) {
      println("Invalid selection made. Please rerun application")
      exit(0)
    }
    val selectedOption = optInt.get match {
      case 1 =>{
        empRepo.fetchAllEmployees().foreach(e => println(e))
        exit(0)
      }
      case 2 => {
        print("Enter the id of employee: ")
        val empId = readLine().toIntOption
        if(empId.isEmpty)
          println("Invalid user id entered... exiting application")
        println(empRepo.findByEmployeeId(empId.get))
      }
      case 3 => {
        println("Employee name : ")
        val empName = readLine();
        println("Employee address : ")
        val address = readLine();
        println("Employee phoneNumbers (Can be comma separated) : ")
        val phoneNumber = readLine();
        println("Employee salary : ")
        val salary = readLine().toDouble
        val employee = Employee(scala.util.Random.nextInt(),empName,address, phoneNumber, salary)
        print(empRepo.addNewEmployee(employee))
        exit(0)
      }
      case 4 => {
        print("Enter the id of employee: ")
        val empId = readLine().toIntOption
        if(empId.isEmpty)
          println("Invalid user id entered... exiting application")
        println(empRepo.deleteEmployeeById(empId.get))
        exit(0)
      }
      case _ => println("Invalid selection made");exit
    }
    exit(0);




    //    var emp1: Employee = new Employee(1,"Babatunde", "23, Everywhere road",Set("9090092920","90987673887"),9000);
//    try(val session = DbConfig.getSession()){
//      session.
//      println(emp1.addNewEmployee())

//    val empRepo: EmployeeRepository = new EmployeeRepository(DbConfig.getSession());
//
//    val emp: Employee = Employee(2,"test","test",List("01234","56789"),400);
//    empRepo.addNewEmployee(emp);
//
//    println(empRepo.findByEmployeeId(2));

    //empRepo.deleteEmployeeById(1);

    //empRepo.fetchAllEmployees().foreach(emp=>println(emp))
  }
}