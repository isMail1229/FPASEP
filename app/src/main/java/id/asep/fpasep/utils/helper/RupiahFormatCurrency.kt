package id.asep.fpasep.utils.helper

import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

object RupiahFormatCurrency {

    fun addCurrency(input: String): String {
        return "Rp. $input"
    }

    fun format(input: BigDecimal): String {
        return "Rp. ${thousandGrouping(input)}"
    }

    fun thousandGrouping(input: BigDecimal): String {
        val symbols = DecimalFormatSymbols()
        symbols.decimalSeparator = ','
        symbols.groupingSeparator = '.'

        val formatter = DecimalFormat.getNumberInstance() as DecimalFormat
        formatter.decimalFormatSymbols = symbols

        return formatter.format(input)
    }
}