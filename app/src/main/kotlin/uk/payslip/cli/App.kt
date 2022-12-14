/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package uk.payslip.cli

import uk.gov.hmrc.calculator.*
import uk.gov.hmrc.calculator.model.PayPeriod.*
import uk.gov.hmrc.calculator.model.CalculatorResponsePayPeriod
import uk.gov.hmrc.calculator.model.TaxYear

//import com.google.gson.Gson
//import com.google.gson.GsonBuilder

class App {
    companion object {
        fun payslip(
            taxCode : String,
            taxYear : TaxYear,
            wages : Double
            ):CalculatorResponsePayPeriod{
            val calculator = Calculator(
                taxCode = taxCode,               // Required
                wages = wages,                 // Required
                payPeriod = MONTHLY,              // Required
                isPensionAge = false,            // Optional (Default: false)
                howManyAWeek = null,             // Optional (Default: null)
                taxYear = taxYear // Optional (Default: Current Tax Year)
            )
            return calculator.run().monthly
        }
        fun print(
            taxCode : String,
            taxYear : TaxYear,
            monthly_wage : Double,
            extra_pay : Double
        ) {
            val response = payslip(taxCode,taxYear,monthly_wage + extra_pay)
            val pension_ratio = 0.035
            println("Salary            = £ %.2f".format(monthly_wage))
            println("Extra pay         = £ %.2f".format(extra_pay))
            println("Tax to Pay        = £ %.2f".format(response.taxToPay))
            println("Employees NI      = £ %.2f".format(response.employeesNI))
            val pension_contrib = monthly_wage * pension_ratio * 0.8
            println("Pension Contriib. = £ %.2f".format(pension_contrib))
            println( "Total Deductions = £ %.2f".format(response.totalDeductions + pension_contrib))

            println( "Take Home = £ %.2f".format(response.takeHome - pension_contrib))
        }
    }
}

fun main() {
    //val gson = GsonBuilder().setPrettyPrinting().create();

    println("Current Tax Year:")
    println(TaxYear.currentTaxYear)

    val extras21: DoubleArray = doubleArrayOf(
        0.0,
        0.0,
        0.0,
        0.0,
        0.0,
        0.0,
        0.0,
        0.0,
        0.0,
        0.0,
        0.0,
        0.0
    )

    var old_monthly_wage = 1234.56


    for (i in 10..12) {
        println("Pay 2021-%d".format(i))
        App.print("1257L",TaxYear.TWENTY_TWENTY_ONE, old_monthly_wage, extras21[i-1])
        println()
    }


    println()
    println("Pay 2022")
    println()

    val extras22: DoubleArray = doubleArrayOf(
        0.0,
        0.0,
        0.0,
        0.0,
        0.0,
        0.0,
        0.0,
        0.0,
        0.0,
        0.0,
        0.0,
        0.0
    )

    for (i in 1..3) {
        println("Pay 2022-%d".format(i))
        App.print("1257L",TaxYear.TWENTY_TWENTY_ONE, old_monthly_wage, extras22[i-1])
        println()
    }

    var new_monthly_wage = 1234.56

    for (i in 4..6) {
        println("Pay 2022-%d".format(i))
        App.print("1257L",TaxYear.TWENTY_TWENTY_TWO, new_monthly_wage, extras22[i-1])
        println()
    }

    for (i in 7..8) {
        println("Pay 2022-%d".format(i))
        App.print("1257L",TaxYear.TWENTY_TWENTY_TWO_REVISED, new_monthly_wage, extras22[i-1])
        println()
    }


    //println(gson.toJson(response))

}
