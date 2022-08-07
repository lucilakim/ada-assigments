/*16.	Una compañía de transporte cuenta con cinco choferes, de los cuales se conoce: nombre, horas trabajadas cada día de la semana (seis días) y sueldo por hora. Realice un algoritmo que: 

a.	Calcule el total de horas trabajadas a la semana para cada trabajador.
b.	Calcule el sueldo semanal para cada uno de ellos.
c.	Calcule el total que pagará la empresa.
d.	Indique el nombre del trabajador que labora más horas el día lunes.
e.	Imprima un reporte con todos los datos anteriores.
 */

class DriversSalaryCalculator {
    public static void main(String[] args) {
        System.out.println("======================================================== ");
        System.out.println("");
        System.out.println("Welcome to the Weekly Salary Calculator per Employee! ");
        System.out.println("");

        //-------------------------- INPUTS AND VARIABLES--------------------------------

        final int DAYS_WORKED = 6;  // Constant --> days worked
        final String[] DAYS = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

        System.out.print("Enter the number of drivers: ");                       
        int numberOfDrivers = readAnInteger();              // Input --> Number of drivers 
        System.out.println("");

        
        String[] driversName = getDriversNames(numberOfDrivers); // Input --> Array with drivers Name
        System.out.println("");

        float[][] hoursWorkedPerDay = fillAMatrix(driversName, DAYS); // Input --> Array with the hours worked each day
        System.out.println("");
        
        // a - Calculate the total hours worked per week for each driver --> to Output        
        float[] totalHoursWorked = calculateTotalHoursWorked(hoursWorkedPerDay);  // Output   

        // b - Calculate hourly wage --> to Output
        float[] hourlyWage = calculateHourlyWage(driversName); // Input --> hourly wage
        float[] weeklySalaryPerDriver = calculateWeeklySalary(totalHoursWorked, hourlyWage); // Output
        System.out.println("");

        // c - Calculate total Company Payable --> to Output
        float totalCompanyPayable = calculateCompanyTotalPayable(weeklySalaryPerDriver); // Output

        // d - Calculate drivers Name worked more on Monday  --> to Output
        int indexWorkedMoreOnMonday  = getIndexWhoWorkedMostOnMonday(hoursWorkedPerDay); 
        float hoursOfDriverWhoWorkedMost = getHoursWhoWorkedMostOnMonday(hoursWorkedPerDay);
        String driversNameWorkedMoreOnMonday = getWhoWorkedMostOnMonday(driversName, indexWorkedMoreOnMonday);



        //----------------- OUTPUTS -----------------
        
        // E (a / b) - Printing hours and salaries for each driver 
        printHoursAndSalaryOfEachDriver(driversName, totalHoursWorked, weeklySalaryPerDriver);
        System.out.println("");
        
        // C - Printing The total amount to be paid by the company
        System.out.println("c.   The total amount to be paid by the company in salaries is: $" + totalCompanyPayable + ".-");
        System.out.println("");

        // D - Printing the drivers Name worked more on Monday
        System.out.println("d.   The name of the driver who worked more on Monday is: " + driversNameWorkedMoreOnMonday + " and he worked " + hoursOfDriverWhoWorkedMost + " hours.");
        System.out.println("");


        System.out.println("========================================================= ");
    } 

    //-------------------------METHODS----------------------------
    
    // Method to reading an integer
    static int readAnInteger() {
        int stringToInteger = Integer.parseInt(System.console().readLine());
        return stringToInteger;
    }

    // Method of obtaining drivers names
    static String[] getDriversNames(int numberOfDrivers) {
        String[] driversName = new String[numberOfDrivers];

        for(int i = 0; i < numberOfDrivers; ++i) {
            System.out.print("Enter the name of the driver " + (i+1) + ": ");
            driversName[i] = readString();
        }
        return driversName;
    }

    // Method for reading strings
    static String readString(){
        String stringRead = System.console().readLine();
        return stringRead;
    }
    

    // Method to fill a matrix
    static float[][] fillAMatrix(String[] names, String[] days){
        float[][] matrix = new float[names.length][days.length];

        for(int i = 0; i < names.length; ++i) {
            for(int j = 0; j < days.length; ++j) {
                System.out.print("Enter hours worked by driver " + names[i] + " on day " + days[j] + " : " );
                matrix[i][j] = Integer.parseInt(System.console().readLine());
            }
            System.out.println("");
        }

        return matrix;
    }

    // a - Method --> Calculate the total hours worked per week for each driver

    static float[] calculateTotalHoursWorked(float[][] hoursWorkedPerDay) { // Enters an array, returns a vector
        int nRows = hoursWorkedPerDay.length; // Rows
        int nColumns = hoursWorkedPerDay[0].length; // Columns
        float[] totalHoursWorked = new float[nRows]; // Output --> vector
        float sumRows = 0.0F; // Counter

        for (int i = 0; i < nRows; ++i) {
            for (int j = 0; j < nColumns; ++j) {
                sumRows += hoursWorkedPerDay[i][j];
            }
            totalHoursWorked[i] = sumRows;
            sumRows = 0;
        }

        return totalHoursWorked; // Output --> vector
    }

    // b -------- Method ----> for calculating weekly salary per driver --------
    // Method Read the hourly wage
    static float[] calculateHourlyWage (String[] names) {
        float[] hourlyWage = new float[names.length];

        for (int i = 0; i < names.length; ++i) {
            System.out.print("Enter driver's " + names[i] + " hourly wage: $");
            hourlyWage[i] =  readFloat();
        }
        return hourlyWage;
    }

    // Method to read a float
    static float readFloat () {
        float stringToFloat = 0.0F;

        stringToFloat = Float.parseFloat(System.console().readLine());

        return stringToFloat; 
    }

    // Method for calculating weekly salary per driver
    static float[] calculateWeeklySalary(float[] hours, float[] salary) {
        float[] weeklySalary = new float[hours.length];
        
        for (int i = 0; i < hours.length; ++i) {
            weeklySalary[i] = hours[i] * salary[i];
        }

        return weeklySalary;
    }

    // c -------- Method ----> for calculating the total payable of the company ------- 
    // Method for calculating the total payable of the company 
    static float calculateCompanyTotalPayable(float[] weeklySalaryPerDriver) {
        float totalCompanyPayable = 0.0F;

        for (int i = 0; i < weeklySalaryPerDriver.length; ++i) {
            totalCompanyPayable += weeklySalaryPerDriver[i];
        }

        return totalCompanyPayable;
    }

    // d -------- Method ----> to get the Name of the driver who worked the most on monday
    // to get the index of worked the most on monday 
    static int getIndexWhoWorkedMostOnMonday (float[][] hoursWorkedPerDay) {
        int workedMostOnMonday = 0;

        for (int i = 0; i < hoursWorkedPerDay.length; ++i) {
            for (int j = 0; j < 1; ++j) {
                if (hoursWorkedPerDay[i][j] > workedMostOnMonday) {
                    workedMostOnMonday = i;
                }   
            }
        }
        return workedMostOnMonday;
    }
    // Method to get the NAME of the person who worked the most on monday
    static String getWhoWorkedMostOnMonday(String[] name, int index) {
        String nameWorkMostOnMOnday = "";

        for (int i = 0; i < name.length; ++i) {
            if (i == index) {
                nameWorkMostOnMOnday = name[i];
            }
        }
        return nameWorkMostOnMOnday;
    }
    // the hours
    static float getHoursWhoWorkedMostOnMonday (float[][] hoursWorkedPerDay) {
        float hoursWorkedMostOnMonday = 0;

        for (int i = 0; i < hoursWorkedPerDay.length; ++i) {
            for (int j = 0; j < 1; ++j) {
                if (hoursWorkedPerDay[i][j] > hoursWorkedMostOnMonday) {
                    hoursWorkedMostOnMonday = hoursWorkedPerDay[i][j];
                }   
            }
        }
        return hoursWorkedMostOnMonday;
    }

    // e -------- Method ----> to print the hours and salary of each driver
    static void printHoursAndSalaryOfEachDriver(String[] driversName, float[] totalHoursWorked, float[] weeklySalaryPerDriver) {
        for (int i = 0; i < totalHoursWorked.length; ++i) {
            System.out.println("Driver " + (i+1) + " " + driversName[i] + ", works " + totalHoursWorked[i] + " hours, and his salary is: $" + weeklySalaryPerDriver[i] + ".-" );
        }

    }

}
